package it.jacopo.app.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import feign.FeignException;
import it.jacopo.app.client.IWebCountryClient;
import it.jacopo.app.client.IWebWeatherClient;
import it.jacopo.app.entity.CountryWeather;
import it.jacopo.app.model.CountryDTO;
import it.jacopo.app.model.CountryWeatherDTO;
import it.jacopo.app.model.WeatherDTO;
import it.jacopo.app.repository.CountryWeatherRepository;

@Service
public class CountryWeatherServiceImpl implements ICountryWeatherService {

    private final IWebCountryClient countryClient;
    private final IWebWeatherClient weatherClient;
    private final CountryWeatherRepository repository;

    public CountryWeatherServiceImpl(IWebCountryClient countryClient, IWebWeatherClient weatherClient,
            CountryWeatherRepository repository) {
        this.countryClient = countryClient;
        this.weatherClient = weatherClient;
        this.repository = repository;
    }

    @Override
    public CountryWeatherDTO getCountryWeather(String countryName) {
        List<Map<String, Object>> rawCountryData;

        // 1. Chiamata sicura al Feign Client per recuperare il paese
        try {
            rawCountryData = countryClient.getCountryInfo(countryName);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paese non trovato nell'API esterna: " + countryName);
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Errore di comunicazione con il servizio paesi REST Countries");
        } catch (Exception e) {
            // Intercetta eventuali fallimenti di deserializzazione Jackson se l'API risponde con un Oggetto JSON anziché Array
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossibile recuperare i dati per il paese: " + countryName);
        }

        if (rawCountryData == null || rawCountryData.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nessun dato trovato per: " + countryName);
        }

        CountryDTO countryDTO = mapToCountryDTO(rawCountryData.get(0));

        // 2. Estrazione coordinate con controlli di sicurezza su null
        if (countryDTO.getCapitalInfo() == null || countryDTO.getCapitalInfo().get("latlng") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coordinate geografiche non disponibili per la capitale di: " + countryDTO.getName());
        }

        List<?> rawLatlng = (List<?>) countryDTO.getCapitalInfo().get("latlng");
        if (rawLatlng.size() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coordinate incomplete per: " + countryDTO.getName());
        }

        double lat = ((Number) rawLatlng.get(0)).doubleValue();
        double lon = ((Number) rawLatlng.get(1)).doubleValue();

        // 3. Chiamata meteo sicura
        Map<String, Object> weatherData;
        try {
            weatherData = weatherClient.getCurrentWeather(lat, lon, true);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Errore durante il recupero dei dati meteo da OpenMeteo");
        }

        if (weatherData == null || !weatherData.containsKey("current_weather")) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Dati meteo attuali non disponibili per: " + countryDTO.getName());
        }

        Map<String, Object> current = (Map<String, Object>) weatherData.get("current_weather");
        double temperature = ((Number) current.get("temperature")).doubleValue();
        int weatherCode = ((Number) current.get("weathercode")).intValue();
        ZonedDateTime retrievedAt = ZonedDateTime.parse(current.get("time") + "Z");

        // 4. Recupero o creazione entity
        Optional<CountryWeather> existing = repository.findByCountryIgnoreCase(countryDTO.getName());
        CountryWeather entity;
        if (existing.isPresent()) {
            entity = existing.get();
            entity.setTemperature(temperature);
            entity.setWeatherCode(weatherCode);
            entity.setRetrievedAt(retrievedAt);
        } else {
            entity = new CountryWeather();
            mapDTOToEntity(countryDTO, temperature, weatherCode, retrievedAt, entity);
        }

        repository.save(entity);

        // 5. Composizione DTO di ritorno
        WeatherDTO weatherDTO = new WeatherDTO(temperature, weatherCode, retrievedAt);

        CountryWeatherDTO dto = new CountryWeatherDTO();
        dto.setCountry(countryDTO);
        dto.setWeather(weatherDTO);
        dto.setVisited(entity.getVisited());
        dto.setNotes(entity.getNotes());
        dto.setRating(entity.getRating());

        return dto;
    }

    @Override
    public CountryWeatherDTO updateCountryData(String countryName, CountryWeatherDTO updatedData) {
        Optional<CountryWeather> existingOpt = repository.findByCountryIgnoreCase(countryName);
        if (existingOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                "Impossibile aggiornare: il paese '" + countryName + "' non è presente nel database. Esegui prima la GET /country-weather/" + countryName);
        }
        CountryWeather entity = existingOpt.get();

        if (updatedData.getVisited() != null)
            entity.setVisited(updatedData.getVisited());
        if (updatedData.getNotes() != null)
            entity.setNotes(updatedData.getNotes());
        if (updatedData.getRating() != null)
            entity.setRating(updatedData.getRating());

        repository.save(entity);

        return buildDTOFromEntity(entity);
    }

    private CountryWeatherDTO buildDTOFromEntity(CountryWeather entity) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName(entity.getCountry());
        countryDTO.setPopulation(entity.getPopulation());

        if (entity.getCapital() != null) {
            countryDTO.setCapital(List.of(entity.getCapital()));
        }
        if (entity.getCurrency() != null) {
            countryDTO.setCurrencies(Map.of(entity.getCurrency(), entity.getCurrency()));
        }
        if (entity.getFlagPng() != null) {
            countryDTO.setFlags(Map.of("png", entity.getFlagPng()));
        }

        WeatherDTO weatherDTO = new WeatherDTO(
                entity.getTemperature(),
                entity.getWeatherCode(),
                entity.getRetrievedAt());

        CountryWeatherDTO dto = new CountryWeatherDTO();
        dto.setCountry(countryDTO);
        dto.setWeather(weatherDTO);
        dto.setVisited(entity.getVisited());
        dto.setNotes(entity.getNotes());
        dto.setRating(entity.getRating());

        return dto;
    }

    private CountryDTO mapToCountryDTO(Map<String, Object> data) {
        CountryDTO dto = new CountryDTO();
        if (data.containsKey("name") && data.get("name") instanceof Map) {
            Map<?, ?> nameMap = (Map<?, ?>) data.get("name");
            dto.setName((String) nameMap.get("common"));
        }
        if (data.containsKey("capital") && data.get("capital") instanceof List) {
            dto.setCapital((List<String>) data.get("capital"));
        }
        if (data.containsKey("population") && data.get("population") != null) {
            dto.setPopulation(((Number) data.get("population")).longValue());
        }

        Map<String, Object> rawCurrencies = (Map<String, Object>) data.get("currencies");
        if (rawCurrencies != null) {
            Map<String, String> currencies = rawCurrencies.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> {
                                Map<String, Object> val = (Map<String, Object>) e.getValue();
                                return val != null && val.get("name") != null ? val.get("name").toString() : "";
                            }));
            dto.setCurrencies(currencies);
        }

        dto.setFlags((Map<String, Object>) data.get("flags"));
        dto.setCapitalInfo((Map<String, Object>) data.get("capitalInfo"));
        return dto;
    }

    private void mapDTOToEntity(CountryDTO countryDTO, double temperature, int weatherCode, ZonedDateTime retrievedAt,
            CountryWeather entity) {
        entity.setCountry(countryDTO.getName());
        if (countryDTO.getCapital() != null && !countryDTO.getCapital().isEmpty()) {
            entity.setCapital(countryDTO.getCapital().get(0));
        }
        entity.setPopulation(countryDTO.getPopulation());
        if (countryDTO.getCurrencies() != null && !countryDTO.getCurrencies().isEmpty()) {
            entity.setCurrency(countryDTO.getCurrencies().keySet().iterator().next());
        }
        if (countryDTO.getFlags() != null) {
            entity.setFlagPng((String) countryDTO.getFlags().get("png"));
        }
        entity.setTemperature(temperature);
        entity.setWeatherCode(weatherCode);
        entity.setRetrievedAt(retrievedAt);
    }

    @Override
    public List<CountryWeatherDTO> getAllCountriesWeather() {
        List<CountryWeather> entities = repository.findAll();
        return entities.stream()
                .map(this::buildDTOFromEntity)
                .collect(Collectors.toList());
    }
}