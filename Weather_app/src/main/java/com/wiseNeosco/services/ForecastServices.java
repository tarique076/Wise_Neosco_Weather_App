package com.wiseNeosco.services;

import com.wiseNeosco.entities.ForecastData;

public interface ForecastServices {
	
	public ForecastData getForecastDataByLatLong(Double lat, Double lon);
	
	public ForecastData getForecastData(String cityName);
}
