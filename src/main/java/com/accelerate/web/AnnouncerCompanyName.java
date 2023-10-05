package com.accelerate.web;

public enum AnnouncerCompanyName {
    FOREFRONT("Forefront");

    private final String announcerCompanyName;

    AnnouncerCompanyName(String value) {
        this.announcerCompanyName = value;
    }

    public String getValue() {
        return announcerCompanyName;
    }
}
