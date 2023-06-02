package com.accelerate.web.dto;

import java.util.Date;

public class AssignmentResponse {

    private int cinodeId;
    private String description;
    private Date deadlineDate;
    private String title;
    private boolean allowRemote;
    private Date startDate;
    private Date endDate;
    private String city;
    private String displayName;

    private Date announcedDate;

    public AssignmentResponse() {
    }

    public AssignmentResponse(int cinodeId, String description, Date deadlineDate, String title, boolean allowRemote, Date startDate, Date endDate, String city, String displayName, Date announcedDate) {
        this.cinodeId = cinodeId;
        this.description = description;
        this.deadlineDate = deadlineDate;
        this.title = title;
        this.allowRemote = allowRemote;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.displayName = displayName;
        this.announcedDate = announcedDate;
    }

    public int getCinodeId() {
        return cinodeId;
    }

    public void setCinodeId(int cinodeId) {
        this.cinodeId = cinodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
