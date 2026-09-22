package it.jacopo.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "weatherClient", url = "https://api.open-meteo.com/v1")
public interface IWebWeatherClient {

    @GetMapping(value = "/forecast", headers = "User-Agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
    Map<String, Object> getCurrentWeather(
        @RequestParam("latitude") double latitude,
        @RequestParam("longitude") double longitude,
        @RequestParam("current_weather") boolean currentWeather
    );
}