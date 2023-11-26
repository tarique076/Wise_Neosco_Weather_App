package com.wiseNeosco.services;

import com.wiseNeosco.entities.WeatherInfo;

public interface WeatherService {

	public WeatherInfo getWeatherData(String cityName);
}
