package it.jacopo.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.jacopo.app.entity.CountryWeather;

@Repository
public interface CountryWeatherRepository extends JpaRepository<CountryWeather, Integer> {
    
    Optional<CountryWeather> findByCountryIgnoreCase(String country);
}
