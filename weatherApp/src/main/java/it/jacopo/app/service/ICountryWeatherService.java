package it.jacopo.app.service;

import java.util.List;

import it.jacopo.app.model.CountryWeatherDTO;

public interface ICountryWeatherService {
    
    CountryWeatherDTO getCountryWeather(String country);
    
    CountryWeatherDTO updateCountryData(String country, CountryWeatherDTO updatedData);

    List<CountryWeatherDTO> getAllCountriesWeather();
}
