package com.sourabhkarkal.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by sourabhkarkal on 12/07/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDTO {

    String title;
    String width;
    String height;
    String link;
    String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
