package it.jacopo.app.model;

public class CountryWeatherDTO {

    private CountryDTO country;
    private WeatherDTO weather;

    private Boolean visited;
    private String notes;
    private Integer rating;

    // Getters e setters
    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public WeatherDTO getWeather() {
        return weather;
    }

    public void setWeather(WeatherDTO weather) {
        this.weather = weather;
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
}
