package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    private final WeatherService weatherService;

    public TemperatureController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/delhi")
    public WeatherService.TemperatureResponse delhi() {
        return weatherService.getTemperature("Delhi,IN");
    }

    @GetMapping("/new-york")
    public WeatherService.TemperatureResponse newYork() {
        return weatherService.getTemperature("New York,US");
    }

    @GetMapping("/{city}")
    public WeatherService.TemperatureResponse city(@PathVariable String city) {
        return weatherService.getTemperature(city);
    }

    @GetMapping("/cities")
    public List<WeatherService.TemperatureResponse> multipleCities() {
        return List.of(
                weatherService.getTemperature("Delhi,IN"),
                weatherService.getTemperature("New York,US"),
                weatherService.getTemperature("London,GB"),
                weatherService.getTemperature("Tokyo,JP")
        );
    }
}