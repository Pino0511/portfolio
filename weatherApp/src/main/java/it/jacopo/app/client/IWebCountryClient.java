package it.jacopo.app.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restCountriesClient", url = "https://restcountries.com/v3.1", fallback = WebCountryClientFallback.class)
public interface IWebCountryClient {

    // Ritorna lista di mappe (non DTO) per evitare problemi deserializzazione
    @GetMapping("/name/{country}?fullText=true")
    List<Map<String, Object>> getCountryInfo(@PathVariable("country") String country);
}


