package com.wiseNeosco.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wiseNeosco.entities.WeatherInfo;
import com.wiseNeosco.repository.WeatherDao;
import com.wiseNeosco.repository.WeatherInfoDao;

@Service
public class WeatherServicesImpl implements WeatherService {

	@Autowired
	private WeatherInfoDao weatherInfoDao;

	@Autowired
	private WeatherDao weatherDao;

	private final String apiKey = "2da8712c74522b83fb219d4fe93f361e";
	private final String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

	@Override
	public WeatherInfo getWeatherData(String cityName) {

		// Getting epoch seconds for 30 minutes before current time.
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = LocalDateTime.now().minusMinutes(30).atZone(zoneId).toInstant();
		Long epochTime = instant.getEpochSecond();

		// Fetching data from database where time is greater than 30 minutes prior to
		// current time.
		Optional<WeatherInfo> weatherDataOpt = weatherInfoDao.findByCityAndTime(cityName, epochTime);

		// If data is present in db, return the data from db.
		if (weatherDataOpt.isPresent() && weatherDataOpt.get() != null) {
			return weatherDataOpt.get();
		}

		// If data not in db, fetch data from OpenWeather API.
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format(apiUrl, cityName, apiKey);
		WeatherInfo weatherData = restTemplate.getForObject(url, WeatherInfo.class);

		// Save the retrieved weather data to the database
		if (weatherData != null) {
			if (!weatherData.getName().equalsIgnoreCase(cityName)) {
				weatherData.setName(cityName);
			}
			weatherData = weatherInfoDao.save(weatherData);
		}

		return weatherData;
	}

	@Override
	public WeatherInfo getWeatherDataByLatLong(Double lat, Double lon) {

		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid="
				+ apiKey + "&units=metric";
		// Getting epoch seconds for 30 minutes before current time.
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = LocalDateTime.now().minusMinutes(30).atZone(zoneId).toInstant();
		Long epochTime = instant.getEpochSecond();

		// Fetching data from database where time is greater than 30 minutes prior to
		// current time.
		lat = Math.round(lat * 10000.0) / 10000.0;
		lon = Math.round(lon * 10000.0) / 10000.0;
		List<WeatherInfo> weatherDataOpt = weatherInfoDao.findByLatAndTime(lat, lon,
				epochTime);

		// If data is present in db, return the data from db.
		if (!weatherDataOpt.isEmpty() && weatherDataOpt.get(0) != null) {
			return weatherDataOpt.get(0);
		}

		// If data not in db, fetch data from OpenWeather API.
		RestTemplate restTemplate = new RestTemplate();
		WeatherInfo weatherData = restTemplate.getForObject(apiUrl, WeatherInfo.class);

		// Save the retrieved weather data to the database
		if (weatherData != null) {

			weatherData = weatherInfoDao.save(weatherData);
		}

		return weatherData;
	}

}
