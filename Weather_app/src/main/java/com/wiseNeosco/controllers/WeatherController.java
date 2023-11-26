package com.wiseNeosco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiseNeosco.entities.WeatherInfo;
import com.wiseNeosco.services.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/{cityName}")
    public WeatherInfo getWeatherData(@PathVariable String cityName) {
        return weatherService.getWeatherData(cityName);
    }
}
