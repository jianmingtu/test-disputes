package ca.bc.gov.iamp.justindisputesapi.model.ords;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DisputeStatusEntity extends BaseEntity implements Serializable {

    @JsonProperty("Status")
    private String updatedStatus;

    @JsonProperty("ViolationTicketNo")
    private String violationTicketNo;

    @JsonProperty("CountNumber")
    private String countNumber;

    @JsonProperty("ClientTypeCode")
    private String clientTypeCode;

    @JsonProperty("ActionType")
    private String actionType;

    @JsonProperty("ActionDate")
    private String actionDate;

    @JsonProperty("DisputantSurname")
    private String disputantSurname;

    @JsonProperty("DisputantGiven1Name")
    private String disputantGiven1Name;

    @JsonProperty("DisputantGiven2Name")
    private String disputantGiven2Name;

    @JsonProperty("DisputantGiven3Name")
    private String disputantGiven3Name;

    @JsonProperty("OrganizationName")
    private String organizationName;

    @JsonProperty("DriverLicenceNo")
    private String driverLicenseNo;

    @JsonProperty("DriverMvbClientNo")
    private String driverMvbClientNo;

    public String getUpdatedStatus() {
        return updatedStatus;
    }

    public void setUpdatedStatus(String updatedStatus) {
        this.updatedStatus = updatedStatus;
    }

    public String getViolationTicketNo() {
        return violationTicketNo;
    }

    public void setViolationTicketNo(String violationTicketNo) {
        this.violationTicketNo = violationTicketNo;
    }

    public String getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(String countNumber) {
        this.countNumber = countNumber;
    }

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }

    public String getDisputantSurname() {
        return disputantSurname;
    }

    public void setDisputantSurname(String disputantSurname) {
        this.disputantSurname = disputantSurname;
    }

    public String getDisputantGiven1Name() {
        return disputantGiven1Name;
    }

    public void setDisputantGiven1Name(String disputantGiven1Name) {
        this.disputantGiven1Name = disputantGiven1Name;
    }

    public String getDisputantGiven2Name() {
        return disputantGiven2Name;
    }

    public void setDisputantGiven2Name(String disputantGiven2Name) {
        this.disputantGiven2Name = disputantGiven2Name;
    }

    public String getDisputantGiven3Name() {
        return disputantGiven3Name;
    }

    public void setDisputantGiven3Name(String disputantGiven3Name) {
        this.disputantGiven3Name = disputantGiven3Name;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDriverLicenseNo() {
        return driverLicenseNo;
    }

    public void setDriverLicenseNo(String driverLicenseNo) {
        this.driverLicenseNo = driverLicenseNo;
    }

    public String getDriverMvbClientNo() {
        return driverMvbClientNo;
    }

    public void setDriverMvbClientNo(String driverMvbClientNo) {
        this.driverMvbClientNo = driverMvbClientNo;
    }
}
