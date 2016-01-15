package com.xebia.hrms.model;

/**
 * Created by jaspreet on 10/12/15.
 */
public class Property {
    private String userMail;

    private String password;

    private String templateLocationBirthday;

    private String templateLocationAnniversary;

    private String templateLocationConfirmation;

    private String probationConfirmationGuide;

    private String probationReviewForm;

    private String stuff;
    private String allIndiaEmailId;
    private String hrindiaEmailId;

    private String mahuaEmailId;

    public String getDiviyaEmailId() {

        return diviyaEmailId;
    }

    public void setDiviyaEmailId(String diviyaEmailId) {
        this.diviyaEmailId = diviyaEmailId;
    }

    private String diviyaEmailId;

    public String getMahuaEmailId() {
        return mahuaEmailId;
    }

    public void setMahuaEmailId(String mahuaEmailId) {
        this.mahuaEmailId = mahuaEmailId;
    }

    public int getConfirmationSchedulerDate() {
        return confirmationSchedulerDate;
    }

    public void setConfirmationSchedulerDate(int confirmationSchedulerDate) {
        this.confirmationSchedulerDate = confirmationSchedulerDate;
    }

    private int confirmationSchedulerDate;


    public String getXlsxfileLocation() {
        return xlsxfileLocation;
    }

    public void setXlsxfileLocation(String xlsxfileLocation) {
        this.xlsxfileLocation = xlsxfileLocation;
    }

    private String xlsxfileLocation;


    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTemplateLocationBirthday() {
        return templateLocationBirthday;
    }

    public void setTemplateLocationBirthday(String templateLocationBirthday) {
        this.templateLocationBirthday = templateLocationBirthday;
    }

    public String getTemplateLocationAnniversary() {
        return templateLocationAnniversary;
    }

    public void setTemplateLocationAnniversary(String templateLocationAnniversary) {
        this.templateLocationAnniversary = templateLocationAnniversary;
    }

    public String getTemplateLocationConfirmation() {
        return templateLocationConfirmation;
    }

    public void setTemplateLocationConfirmation(String templateLocationConfirmation) {
        this.templateLocationConfirmation = templateLocationConfirmation;
    }

    public String getProbationConfirmationGuide() {
        return probationConfirmationGuide;
    }

    public void setProbationConfirmationGuide(String probationConfirmationGuide) {
        this.probationConfirmationGuide = probationConfirmationGuide;
    }

    public String getProbationReviewForm() {
        return probationReviewForm;
    }

    public void setProbationReviewForm(String probationReviewForm) {
        this.probationReviewForm = probationReviewForm;
    }


    public String getStuff() {
        return stuff;
    }

    public void setStuff(String stuff) {
        this.stuff = stuff;
    }


    public String getAllIndiaEmailId() {
        return allIndiaEmailId;
    }

    public void setAllIndiaEmailId(String allIndiaEmailId) {
        this.allIndiaEmailId = allIndiaEmailId;
    }

    public String getHrindiaEmailId() {
        return hrindiaEmailId;
    }

    public void setHrindiaEmailId(String hrindiaEmailId) {
        this.hrindiaEmailId = hrindiaEmailId;
    }
}
