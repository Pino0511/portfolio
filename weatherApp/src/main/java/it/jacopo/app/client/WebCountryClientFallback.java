package it.jacopo.app.client;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class WebCountryClientFallback implements IWebCountryClient {

    @Override
    public List<Map<String, Object>> getCountryInfo(String country) {
        // fallback: restituisci lista vuota per indicare nessun dato trovato
        return Collections.emptyList();
    }
}
