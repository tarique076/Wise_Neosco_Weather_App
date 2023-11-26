package com.wiseNeosco.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class City {

    @Column(name = "city_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Coord coord;

    @Column(name = "country")
    private String country;

    @Column(name = "population")
    private int population;

    @Column(name = "timezone")
    private int timezone;

    @Column(name = "sunrise")
    private long sunrise;

    @Column(name = "sunset")
    private long sunset;
}
