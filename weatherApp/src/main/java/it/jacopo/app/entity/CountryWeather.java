package it.jacopo.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.ZonedDateTime;

@Entity
@Table(name = "country_weather")
public class CountryWeather {

    @Id
    private String country;

    private String capital;

    private long population;

    private String currency;

    private String flagPng;

    private Double temperature;

    private Integer weatherCode;

    private ZonedDateTime retrievedAt;

    private Boolean visited;

    private String notes;

    private Integer rating;

    public CountryWeather() {
    }

    public CountryWeather(String country, String capital, long population, String currency,
                                String flagPng, Double temperature, Integer weatherCode, ZonedDateTime retrievedAt,
                                Boolean visited, String notes, Integer rating) {
        this.country = country;
        this.capital = capital;
        this.population = population;
        this.currency = currency;
        this.flagPng = flagPng;
        this.temperature = temperature;
        this.weatherCode = weatherCode;
        this.retrievedAt = retrievedAt;
        this.visited = visited;
        this.notes = notes;
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFlagPng() {
        return flagPng;
    }

    public void setFlagPng(String flagPng) {
        this.flagPng = flagPng;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    public ZonedDateTime getRetrievedAt() {
        return retrievedAt;
    }

    public void setRetrievedAt(ZonedDateTime retrievedAt) {
        this.retrievedAt = retrievedAt;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" +
            ", country='" + getCountry() + "'" +
            ", capital='" + getCapital() + "'" +
            ", population='" + getPopulation() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", flagPng='" + getFlagPng() + "'" +
            ", temperature='" + getTemperature() + "'" +
            ", weatherCode='" + getWeatherCode() + "'" +
            ", retrievedAt='" + getRetrievedAt() + "'" +
            ", visited='" + getVisited() + "'" +
            ", notes='" + getNotes() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }
}
