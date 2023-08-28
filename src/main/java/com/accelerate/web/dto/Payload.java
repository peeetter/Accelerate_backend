package com.accelerate.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {
    @JsonProperty("DeadlineDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date deadlineDate;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("AllowRemote")
    private boolean allowRemote;
    @JsonProperty("StartDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date startDate;
    @JsonProperty("EndDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date endDate;

    @JsonProperty("Location")
    private Location location;

    @JsonProperty("AnnouncedDate")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date announcedDate;

    @JsonProperty("AnnouncerCompanyName")
    private String announcerCompanyName;

    public Payload() {

    }

    public Payload(Date deadlineDate, String title, String description, boolean allowRemote, Date startDate, Date endDate, Location location, Date announcedDate, String announcerCompanyName) {
        this.deadlineDate = deadlineDate;
        this.title = title;
        this.description = description;
        this.allowRemote = allowRemote;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.announcedDate = announcedDate;
        this.announcerCompanyName = announcerCompanyName;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllowRemote() {
        return allowRemote;
    }

    public void setAllowRemote(boolean allowRemote) {
        this.allowRemote = allowRemote;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getAnnouncedDate() {
        return announcedDate;
    }

    public void setAnnouncedDate(Date announcedDate) {
        this.announcedDate = announcedDate;
    }

    public String getAnnouncerCompanyName() {
        return announcerCompanyName;
    }

    public void setAnnouncerCompanyName(String announcerCompanyName) {
        this.announcerCompanyName = announcerCompanyName;
    }
}
