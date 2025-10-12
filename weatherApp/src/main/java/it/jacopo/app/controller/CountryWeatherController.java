package it.jacopo.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import it.jacopo.app.model.CountryWeatherDTO;
import it.jacopo.app.service.ICountryWeatherService;

@RestController
@RequestMapping("/country-weather")
public class CountryWeatherController {

    private final ICountryWeatherService service;

    public CountryWeatherController(ICountryWeatherService service) {
        this.service = service;
    }

    @GetMapping("/{country}")
    @Operation(
        summary = "Get weather and info for country",
        description = "Recupera i dati del paese e il meteo attuale.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Country not found"),
            @ApiResponse(responseCode = "502", description = "External API failure")
        }
    )
    public ResponseEntity<CountryWeatherDTO> getCountryWeather(@PathVariable String country) {
        CountryWeatherDTO dto = service.getCountryWeather(country);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{country}")
    @Operation(summary = "Update country data", description = "Aggiorna i dati visitati, note e rating del paese")
    public ResponseEntity<CountryWeatherDTO> updateCountryWeather(
            @PathVariable String country,
            @RequestBody Map<String, Object> updates) {

        Boolean visited = updates.get("visited") != null ? (Boolean) updates.get("visited") : null;
        String notes = updates.get("notes") != null ? (String) updates.get("notes") : null;
        Integer rating = null;
        if (updates.get("rating") != null) {
            // Se il valore arriva come Integer o Number
            Object ratingObj = updates.get("rating");
            if (ratingObj instanceof Integer) {
                rating = (Integer) ratingObj;
            } else if (ratingObj instanceof Number) {
                rating = ((Number) ratingObj).intValue();
            }
        }

        // Costruisci DTO con solo i campi aggiornati
        CountryWeatherDTO updatedData = new CountryWeatherDTO();
        updatedData.setVisited(visited);
        updatedData.setNotes(notes);
        updatedData.setRating(rating);

        CountryWeatherDTO dto = service.updateCountryData(country, updatedData);
        return ResponseEntity.ok(dto);

    }
    @GetMapping("/all")
    @Operation(summary = "Get all countries weather data", description = "Recupera i dati meteo di tutti i paesi")
    public ResponseEntity<List<CountryWeatherDTO>> getAllCountriesWeather() {
        List<CountryWeatherDTO> allCountries = service.getAllCountriesWeather();
        return ResponseEntity.ok(allCountries);
}
}
