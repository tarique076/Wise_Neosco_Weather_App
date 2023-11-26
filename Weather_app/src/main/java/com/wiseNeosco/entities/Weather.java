package com.wiseNeosco.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Weather {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int weatherId;
	
    private int id;
    private String main;
    private String description;
    private String icon;

//    @ManyToOne
//    @JoinColumn(name = "weather_info_id")
//    private WeatherInfo weatherInfo;
    
    @ManyToOne
    @JoinColumn(name = "forecast_item_id")
    private ForecastItem forecastItem;
}

