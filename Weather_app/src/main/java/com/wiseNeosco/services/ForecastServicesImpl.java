package com.wiseNeosco.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wiseNeosco.entities.ForecastData;
import com.wiseNeosco.repository.ForecastDao;

@Service
public class ForecastServicesImpl implements ForecastServices {

	@Autowired
	private ForecastDao fDao;

	private final String apiKey = "2da8712c74522b83fb219d4fe93f361e";
	private final String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=%s";

	@Override
	public ForecastData getForecastData(String cityName) {
		
		//Check if data is present in DB.
		Optional<ForecastData> forecastDataOpt = fDao.findByTimeAndCity(cityName, LocalDateTime.now().minusMinutes(30));
		
		//If data is present in DB, then return.
		if(forecastDataOpt.isPresent() && forecastDataOpt.get() != null) {
			return forecastDataOpt.get();
		}
		
		//If data is not present in DB, fetch from OpenWeather API.
		RestTemplate restTemplate = new RestTemplate();
        String url = String.format(apiUrl, cityName, apiKey);
        
        ForecastData forecastData = restTemplate.getForObject(url, ForecastData.class);
        
        if (forecastData != null) {
            // Save or update the forecast data in the database
        	forecastData.setTimestamp(LocalDateTime.now());
            fDao.save(forecastData);
        }
        
        return forecastData;
	}

}
