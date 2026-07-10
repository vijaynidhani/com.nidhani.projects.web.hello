package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class WeatherService {

    private final RestClient restClient;
    private final String apiKey;
    private final String baseUrl;

    public WeatherService(
            RestClient.Builder restClientBuilder,
            @Value("${weather.api.key:}") String apiKey,
            @Value("${weather.api.base-url:https://api.openweathermap.org/data/2.5}") String baseUrl
    ) {
        this.restClient = restClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Cacheable(value = "temperature", key = "#city")
    public TemperatureResponse getTemperature(String city) {
        if (apiKey == null || apiKey.isEmpty()) {
            return new TemperatureResponse(city, "API key not configured", null, null);
        }

        try {
            Map<String, Object> response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/weather")
                            .queryParam("q", city)
                            .queryParam("appid", apiKey)
                            .queryParam("units", "metric")
                            .build())
                    .retrieve()
                    .body(Map.class);

            if (response != null) {
                Map<String, Object> main = (Map<String, Object>) response.get("main");
                Double temp = main != null ? ((Number) main.get("temp")).doubleValue() : null;
                Double feelsLike = main != null ? ((Number) main.get("feels_like")).doubleValue() : null;
                return new TemperatureResponse(city, temp, feelsLike, "°C");
            }
        } catch (Exception e) {
            return new TemperatureResponse(city, "Error fetching weather: " + e.getMessage(), null, null);
        }

        return new TemperatureResponse(city, "City not found", null, null);
    }

    public record TemperatureResponse(
            String city,
            Object temperature,
            Object feelsLike,
            String unit
    ) {}
}