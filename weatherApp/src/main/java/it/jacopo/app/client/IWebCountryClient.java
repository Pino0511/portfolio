package it.jacopo.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "countryClient", url = "https://restcountries.com/v3.1")
public interface IWebCountryClient {

    @GetMapping(value = "/name/{name}", headers = "User-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
    List<Map<String, Object>> getCountryInfo(@PathVariable("name") String name);
}