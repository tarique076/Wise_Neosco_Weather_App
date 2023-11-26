package com.wiseNeosco.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "forecast_item")
public class ForecastItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "forecast_item_id")
    private Long forecastItemId;

    @Column(name = "dt")
    private long dt;

    @Embedded
    private Main main;

    @OneToMany(mappedBy = "forecastItem")
    private List<Weather> weather;

    @Embedded
    private Clouds clouds;

    @Embedded
    private Wind wind;

    @Column(name = "visibility")
    private int visibility;

    @Column(name = "pop")
    private int pop;

    @Embedded
    private Sys sys;

    @Column(name = "dt_txt")
    @JsonProperty(value = "dt_txt")
    private String dtTxt;

//    @ManyToOne
////    @JoinColumn(name = "forecast_id")
////    @JsonIgnore
//    private ForecastData forecastData;

}
