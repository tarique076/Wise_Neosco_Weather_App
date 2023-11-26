package com.wiseNeosco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wiseNeosco.entities.Weather;

@Repository
public interface WeatherDao extends JpaRepository<Weather, Long>{

}
