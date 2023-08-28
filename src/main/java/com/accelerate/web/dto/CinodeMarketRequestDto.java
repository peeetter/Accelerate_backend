package com.accelerate.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CinodeMarketRequestDto {

    @JsonProperty("Meta")
    private Meta meta;

    @JsonProperty("Payload")
    private Payload payload;

    public CinodeMarketRequestDto() {
    }

    public CinodeMarketRequestDto(Meta meta, Payload payload) {
        this.meta = meta;
        this.payload = payload;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}