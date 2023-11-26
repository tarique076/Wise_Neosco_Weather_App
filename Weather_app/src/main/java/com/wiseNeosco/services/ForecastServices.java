package com.wiseNeosco.services;

import com.wiseNeosco.entities.ForecastData;

public interface ForecastServices {
	public ForecastData getForecastData(String cityName);
}
