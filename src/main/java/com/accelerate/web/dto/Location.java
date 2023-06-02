package com.accelerate.web.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @JsonProperty("DisplayName")
    private String displayName;
    @JsonProperty("City")
    private String city;

    public Location() {

    }
    public Location(String displayName, String city) {
        this.displayName = displayName;
        this.city = city;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
