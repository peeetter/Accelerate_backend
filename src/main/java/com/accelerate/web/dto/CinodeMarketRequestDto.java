package com.accelerate.web.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class CinodeMarketRequestDto {

    private int cinodeId;

    private String action;

    private Date deadlineDate;

    private String title;

    private String description;

    private boolean allowRemote;

    private Date startDate;

    private Date endDate;

    private String city;
    private String displayName;
    private Date announcedDate;
    private String announcerCompanyName;

    public CinodeMarketRequestDto() {
    }

    public CinodeMarketRequestDto(int cinodeId, String action, Date deadlineDate, String title, String description, boolean allowRemote, Date startDate, Date endDate, String city, String displayName, Date announcedDate, String announcerCompanyName) {
        this.cinodeId = cinodeId;
        this.action = action;
        this.deadlineDate = deadlineDate;
        this.title = title;
        this.description = description;
        this.allowRemote = allowRemote;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.displayName = displayName;
        this.announcedDate = announcedDate;
        this.announcerCompanyName = announcerCompanyName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
