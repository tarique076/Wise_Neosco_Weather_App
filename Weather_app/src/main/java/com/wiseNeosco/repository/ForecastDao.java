package com.wiseNeosco.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wiseNeosco.entities.ForecastData;

@Repository
public interface ForecastDao extends JpaRepository<ForecastData, Long>{

	@Query("select f from ForecastData f where f.city.name = :city and timestamp >= :timestamp")
	public Optional<ForecastData> findByTimeAndCity(String city, LocalDateTime timestamp);
}
