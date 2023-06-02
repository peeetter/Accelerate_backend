package com.accelerate.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {

    @JsonProperty("Id")
    private int cinodeId;
    @JsonProperty("Action")
    private String action;

    public Meta() {

    }

    public Meta(int cinodeId, String action) {
        this.cinodeId = cinodeId;
        this.action = action;
    }

    public int getCinodeId() {
        return cinodeId;
    }

    public void setCinodeId(int cinodeId) {
        this.cinodeId = cinodeId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
