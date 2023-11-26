package com.wiseNeosco.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Sys {

	private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;

}

