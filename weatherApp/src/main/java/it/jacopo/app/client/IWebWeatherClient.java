package it.jacopo.app.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openMeteoClient", url = "https://api.open-meteo.com/v1", fallback = WebWeatherClientFallback.class)
public interface IWebWeatherClient {

    @GetMapping("/forecast")
    Map<String, Object> getCurrentWeather(
        @RequestParam("latitude") double latitude,
        @RequestParam("longitude") double longitude,
        @RequestParam("current_weather") boolean currentWeather
    );
}
