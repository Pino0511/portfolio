package it.jacopo.app.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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
        List<Map<String, Object>> rawCountryData = countryClient.getCountryInfo(countryName);
        if (rawCountryData == null || rawCountryData.isEmpty()) {
            throw new RuntimeException("Paese non trovato: " + countryName);
        }

        CountryDTO countryDTO = mapToCountryDTO(rawCountryData.get(0));

        // Estrazione coordinate in modo sicuro (evita ClassCastException)
        if (countryDTO.getCapitalInfo() == null || countryDTO.getCapitalInfo().get("latlng") == null) {
            throw new RuntimeException("Coordinate non disponibili per: " + countryDTO.getCapital());
        }

        List<?> rawLatlng = (List<?>) countryDTO.getCapitalInfo().get("latlng");
        if (rawLatlng.size() < 2) {
            throw new RuntimeException("Coordinate non valide per: " + countryDTO.getCapital());
        }

        double lat = ((Number) rawLatlng.get(0)).doubleValue();
        double lon = ((Number) rawLatlng.get(1)).doubleValue();

        // Chiamata meteo
        Map<String, Object> weatherData = weatherClient.getCurrentWeather(lat, lon, true);
        if (weatherData == null || !weatherData.containsKey("current_weather")) {
            throw new RuntimeException("Dati meteo non disponibili per: " + countryDTO.getCapital());
        }

        Map<String, Object> current = (Map<String, Object>) weatherData.get("current_weather");
        double temperature = ((Number) current.get("temperature")).doubleValue();
        int weatherCode = ((Number) current.get("weathercode")).intValue();
        ZonedDateTime retrievedAt = ZonedDateTime.parse(current.get("time") + "Z");

        // Recupero o creazione entity
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

        // Composizione DTO di ritorno
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
            throw new RuntimeException("Paese non trovato: " + countryName);
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

    // Metodo privato riutilizzabile anche per getAllCountriesWeather()
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
        if (data.containsKey("name")) {
            Map<String, Object> nameMap = (Map<String, Object>) data.get("name");
            dto.setName((String) nameMap.get("common"));
        }
        dto.setCapital((List<String>) data.get("capital"));
        dto.setPopulation(((Number) data.get("population")).longValue());

        Map<String, Object> rawCurrencies = (Map<String, Object>) data.get("currencies");
        if (rawCurrencies != null) {
            Map<String, String> currencies = rawCurrencies.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> {
                                Map<String, Object> val = (Map<String, Object>) e.getValue();
                                return val.get("name") != null ? val.get("name").toString() : "";
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
