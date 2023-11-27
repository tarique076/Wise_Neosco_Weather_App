package com.wiseNeosco.services;

import com.wiseNeosco.entities.WeatherInfo;

public interface WeatherService {

	public WeatherInfo getWeatherDataByLatLong(Double lat, Double lon);
	
	public WeatherInfo getWeatherData(String cityName);
}
