package it.jacopo.app.model;

import java.util.List;
import java.util.Map;

public class CountryDTO {

    private String name;

    private List<String> capital;

    private long population;

    // Map semplificata: codice valuta -> nome valuta
    private Map<String, String> currencies;

    private Map<String, Object> flags;

    private Map<String, Object> capitalInfo;

    public CountryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public Map<String, String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, String> currencies) {
        this.currencies = currencies;
    }

    public Map<String, Object> getFlags() {
        return flags;
    }

    public void setFlags(Map<String, Object> flags) {
        this.flags = flags;
    }

    public Map<String, Object> getCapitalInfo() {
        return capitalInfo;
    }

    public void setCapitalInfo(Map<String, Object> capitalInfo) {
        this.capitalInfo = capitalInfo;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + name + "'" +
            ", capital='" + capital + "'" +
            ", population='" + population + "'" +
            ", currencies='" + currencies + "'" +
            ", flags='" + flags + "'" +
            ", capitalInfo='" + capitalInfo + "'" +
            "}";
    }

}
