package com.wiseNeosco.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weather_info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long weatherInfoId;

	@Embedded
	private Coord coord;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "weatherInfoId")
	private List<Weather> weather;

	private String base;

	@Embedded
	private Main main;

	private int visibility;

	@Embedded
	private Wind wind;

	@Embedded
	private Clouds clouds;

	private long dt;

	@Embedded
	private Sys sys;

	private int timezone;
//    private long id;
	private String name;
	private int cod;

}
