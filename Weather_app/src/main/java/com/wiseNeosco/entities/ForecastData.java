package com.wiseNeosco.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "forecast_data")
public class ForecastData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "forecast_id")
    private Long forecastId;

    @Column(name = "cod")
    private String cod;

    @Column(name = "message")
    private int message;

    @Column(name = "cnt")
    private int cnt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "forecast_id")
    private List<ForecastItem> list;

    @Embedded
    private City city;
    
    private LocalDateTime timestamp;
    
}
