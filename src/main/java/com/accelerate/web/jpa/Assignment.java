package com.accelerate.web.jpa;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "assignment")
public class Assignment {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int surrogateId;
    @Id
    @Column(name = "cinodeId")
    private int cinodeId;

    @Column(name = "action")
    private String action;

    @Column(name = "deadlinedate")
    private Date deadlineDate;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "allowremote")
    private boolean allowRemote;

    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "enddate")
    private Date endDate;

    @Column(name = "city")
    private String city;

    @Column(name = "displayname")
    private String displayName;

    @Column(name = "announceddate")
    private Date announcedDate;


    public Assignment() {
    }
    public Assignment(int surrogateId, int cinodeId, String action, Date deadlineDate, String title, String description, boolean allowRemote, Date startDate, Date endDate, String city, String displayName, Date announcedDate) {
        this.surrogateId = surrogateId;
        this.cinodeId = cinodeId;
        this.action = action;
        this.deadlineDate = deadlineDate;
        this.title = title;
        this.description = description;
        this.allowRemote = allowRemote;
        this.startDate = startDate;
        this.endDate = endDate;
        this.announcedDate = announcedDate;
       // this.location = location;
    }

    public int getSurrogateId() {
        return surrogateId;
    }

    public void setSurrogateId(int surrogateId) {
        this.surrogateId = surrogateId;
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
}
