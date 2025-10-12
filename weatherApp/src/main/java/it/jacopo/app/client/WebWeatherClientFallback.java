package it.jacopo.app.client;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class WebWeatherClientFallback implements IWebWeatherClient {

    @Override
    public Map<String, Object> getCurrentWeather(double latitude, double longitude, boolean currentWeather) {
        // fallback: restituisci mappa vuota per indicare nessun dato meteo
        return Collections.emptyMap();
    }
}
