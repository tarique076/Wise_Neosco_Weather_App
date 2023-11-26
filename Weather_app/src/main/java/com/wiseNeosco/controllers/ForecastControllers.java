package com.wiseNeosco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiseNeosco.entities.ForecastData;
import com.wiseNeosco.services.ForecastServices;

@RestController
@RequestMapping("/forecast")
public class ForecastControllers {

	@Autowired
	private ForecastServices forecastService;
	
	@GetMapping("/{cityName}")
	public ForecastData getForecastData(@PathVariable String cityName) {
		return forecastService.getForecastData(cityName);
	}
}
