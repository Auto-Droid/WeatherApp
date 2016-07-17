package com.sourabhkarkal.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sourabhkarkal on 12/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AstronomyDTO {

    String sunrise;
    String sunset;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
