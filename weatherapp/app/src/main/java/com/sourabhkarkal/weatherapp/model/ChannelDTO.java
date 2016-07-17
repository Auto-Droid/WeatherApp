package com.sourabhkarkal.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sourabhkarkal on 12/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelDTO {

    UnitDTO units;
    String title;
    String link;
    String description;
    String lastBuildDate;
    LocationDTO location;
    WindDTO wind;
    AtmosphereDTO atmosphere;
    AstronomyDTO astronomy;
    ImageDTO image;
    ItemDTO item;

    public UnitDTO getUnits() {
        return units;
    }

    public void setUnits(UnitDTO units) {
        this.units = units;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public WindDTO getWind() {
        return wind;
    }

    public void setWind(WindDTO wind) {
        this.wind = wind;
    }

    public AtmosphereDTO getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(AtmosphereDTO atmosphere) {
        this.atmosphere = atmosphere;
    }

    public AstronomyDTO getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(AstronomyDTO astronomy) {
        this.astronomy = astronomy;
    }

    public ImageDTO getImage() {
        return image;
    }

    public void setImage(ImageDTO image) {
        this.image = image;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }
}
