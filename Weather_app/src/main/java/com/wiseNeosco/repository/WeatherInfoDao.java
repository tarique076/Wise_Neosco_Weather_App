package com.wiseNeosco.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wiseNeosco.entities.WeatherInfo;

public interface WeatherInfoDao extends JpaRepository<WeatherInfo, Long>{

	public List<WeatherInfo> findByName(String city);
	
	@Query("select w from WeatherInfo w where w.name = :city and w.dt >= :epochTime")
	public Optional<WeatherInfo> findByCityAndTime(String city, Long epochTime);
}
