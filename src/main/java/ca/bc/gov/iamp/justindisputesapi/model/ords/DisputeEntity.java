package ca.bc.gov.iamp.justindisputesapi.model.ords;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DisputeEntity extends BaseEntity implements Serializable {

    @JsonProperty("Status")
    private String createdStatus;

    @JsonProperty("ViolationTicketNo")
    private String violationTicketNo;

    @JsonProperty("DisputeFiledDate")
    private String disputeFiledDate;

    @JsonProperty("ServiceDate")
    private String serviceDate;

    @JsonProperty("ClientTypeCode")
    private String clientTypeCode;

    @JsonProperty("DriverSurname")
    private String driverSurname;

    @JsonProperty("DriverGiven1Name")
    private String driverGiven1Name;

    @JsonProperty("DriverGiven2Name")
    private String driverGiven2Name;

    @JsonProperty("DriverGiven3Name")
    private String driverGiven3Name;

    @JsonProperty("OrganizationName")
    private String organizationName;

    @JsonProperty("DriverLicenseNo")
    private String driverLicenseNo;

    @JsonProperty("DriverJurisdictionCode")
    private String driverJurisdictionCode;

    @JsonProperty("DriverMvbClientNo")
    private String driverMvbClientNo;

    @JsonProperty("DriverGender")
    private String driverGender;

    @JsonProperty("DriverBirthdate")
    private String driverBirthDate;

    @JsonProperty("AddressLine")
    private String addressLine;

    @JsonProperty("AddressLine2")
    private String addressLine2;

    @JsonProperty("AddressLine3")
    private String addressLine3;

    @JsonProperty("AddressCity")
    private String addressCity;

    @JsonProperty("AddressJurisdictionCode")
    private String addressJurisdictionCode;

    @JsonProperty("AddressCountryCode")
    private String addressCountryCode;

    @JsonProperty("AddressPostalCode")
    private String addressPostalCode;

    @JsonProperty("ViolationDate")
    private String violationDate;

    @JsonProperty("ViolationCity")
    private String violationCity;

    @JsonProperty("CountNumber")
    private String countNumber;

    @JsonProperty("IcbcAct")
    private String icbcAct;

    @JsonProperty("IcbcCountSection")
    private String icbcCountSection;

    @JsonProperty("CsbAct")
    private String csbAct;

    @JsonProperty("CsbSection")
    private String csbSection;

    @JsonProperty("CsbSubsection")
    private String csbSubsection;

    @JsonProperty("CsbParagraph")
    private String csbParagraph;

    @JsonProperty("CsbSubparagraph")
    private String csbSubparagraph;

    @JsonProperty("CsbCountClause")
    private String csbCountClause;

    @JsonProperty("TicketedAmt")
    private Double ticketedAmt;

    @JsonProperty("DisputeType")
    private String disputeType;

    @JsonProperty("VehicleJuridiction")
    private String vehicleJuridiction;

    @JsonProperty("VehiclePlateNumber")
    private String vehiclePlateNumber;

    @JsonProperty("VehicleOwnerNm")
    private String vehicleOwnerNm;

    @JsonProperty("VehicleType")
    private String vehicleType;

    @JsonProperty("VehicleColour")
    private String vehicleColour;

    @JsonProperty("HearingLocation")
    private String hearingLocation;

    @JsonProperty("HearingLocationCode")
    private String hearingLocationCode;

    @JsonProperty("EnforcementOfficerPin")
    private String enforcementOfficerPin;

    @JsonProperty("EnforcementOfficerName")
    private String enforcementOfficerName;

    @JsonProperty("EnforcementAgency")
    private String enforcementAgency;

    @JsonProperty("EnforcementAgencyCode")
    private String enforcementAgencyCode;

    @JsonProperty("AccidentFlag")
    private Boolean accidentFlag;

    @JsonProperty("WitnessOfficerPin")
    private String witnessOfficerPin;

    @JsonProperty("WitnessOfficerName")
    private String witnessOfficerName;

    @JsonProperty("CosFormNumber")
    private String cosFormNumber;

    @JsonProperty("EvtFormNumber")
    private String evtFormNumber;

    @JsonProperty("MreMinorVersion")
    private String mreMinorVersion;

    @JsonProperty("TicketXml")
    private String ticketXml;

    public String getViolationTicketNo() {
        return violationTicketNo;
    }

    public void setViolationTicketNo(String violationTicketNo) {
        this.violationTicketNo = violationTicketNo;
    }

    public String getDisputeFiledDate() {
        return disputeFiledDate;
    }

    public void setDisputeFiledDate(String disputeFiledDate) {
        this.disputeFiledDate = disputeFiledDate;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }

    public String getDriverGiven1Name() {
        return driverGiven1Name;
    }

    public void setDriverGiven1Name(String driverGiven1Name) {
        this.driverGiven1Name = driverGiven1Name;
    }

    public String getDriverGiven2Name() {
        return driverGiven2Name;
    }

    public void setDriverGiven2Name(String driverGiven2Name) {
        this.driverGiven2Name = driverGiven2Name;
    }

    public String getDriverGiven3Name() {
        return driverGiven3Name;
    }

    public void setDriverGiven3Name(String driverGiven3Name) {
        this.driverGiven3Name = driverGiven3Name;
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

    public String getDriverJurisdictionCode() {
        return driverJurisdictionCode;
    }

    public void setDriverJurisdictionCode(String driverJurisdictionCode) {
        this.driverJurisdictionCode = driverJurisdictionCode;
    }

    public String getDriverMvbClientNo() {
        return driverMvbClientNo;
    }

    public void setDriverMvbClientNo(String driverMvbClientNo) {
        this.driverMvbClientNo = driverMvbClientNo;
    }

    public String getDriverGender() {
        return driverGender;
    }

    public void setDriverGender(String driverGender) {
        this.driverGender = driverGender;
    }

    public String getDriverBirthDate() {
        return driverBirthDate;
    }

    public void setDriverBirthDate(String driverBirthDate) {
        this.driverBirthDate = driverBirthDate;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressJurisdictionCode() {
        return addressJurisdictionCode;
    }

    public void setAddressJurisdictionCode(String addressJurisdictionCode) {
        this.addressJurisdictionCode = addressJurisdictionCode;
    }

    public String getAddressCountryCode() {
        return addressCountryCode;
    }

    public void setAddressCountryCode(String addressCountryCode) {
        this.addressCountryCode = addressCountryCode;
    }

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public String getViolationDate() {
        return violationDate;
    }

    public void setViolationDate(String violationDate) {
        this.violationDate = violationDate;
    }

    public String getViolationCity() {
        return violationCity;
    }

    public void setViolationCity(String violationCity) {
        this.violationCity = violationCity;
    }

    public String getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(String countNumber) {
        this.countNumber = countNumber;
    }

    public String getIcbcAct() {
        return icbcAct;
    }

    public void setIcbcAct(String icbcAct) {
        this.icbcAct = icbcAct;
    }

    public String getIcbcCountSection() {
        return icbcCountSection;
    }

    public void setIcbcCountSection(String icbcCountSection) {
        this.icbcCountSection = icbcCountSection;
    }

    public String getCsbAct() {
        return csbAct;
    }

    public void setCsbAct(String csbAct) {
        this.csbAct = csbAct;
    }

    public String getCsbSection() {
        return csbSection;
    }

    public void setCsbSection(String csbSection) {
        this.csbSection = csbSection;
    }

    public String getCsbSubsection() {
        return csbSubsection;
    }

    public void setCsbSubsection(String csbSubsection) {
        this.csbSubsection = csbSubsection;
    }

    public String getCsbParagraph() {
        return csbParagraph;
    }

    public void setCsbParagraph(String csbParagraph) {
        this.csbParagraph = csbParagraph;
    }

    public String getCsbSubparagraph() {
        return csbSubparagraph;
    }

    public void setCsbSubparagraph(String csbSubparagraph) {
        this.csbSubparagraph = csbSubparagraph;
    }

    public String getCsbCountClause() {
        return csbCountClause;
    }

    public void setCsbCountClause(String csbCountClause) {
        this.csbCountClause = csbCountClause;
    }

    public Double getTicketedAmt() {
        return ticketedAmt;
    }

    public void setTicketedAmt(Double ticketedAmt) {
        this.ticketedAmt = ticketedAmt;
    }

    public String getDisputeType() {
        return disputeType;
    }

    public void setDisputeType(String disputeType) {
        this.disputeType = disputeType;
    }

    public String getVehicleJuridiction() {
        return vehicleJuridiction;
    }

    public void setVehicleJuridiction(String vehicleJuridiction) {
        this.vehicleJuridiction = vehicleJuridiction;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public String getVehicleOwnerNm() {
        return vehicleOwnerNm;
    }

    public void setVehicleOwnerNm(String vehicleOwnerNm) {
        this.vehicleOwnerNm = vehicleOwnerNm;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public String getHearingLocation() {
        return hearingLocation;
    }

    public void setHearingLocation(String hearingLocation) {
        this.hearingLocation = hearingLocation;
    }

    public String getHearingLocationCode() {
        return hearingLocationCode;
    }

    public void setHearingLocationCode(String hearingLocationCode) {
        this.hearingLocationCode = hearingLocationCode;
    }

    public String getEnforcementOfficerPin() {
        return enforcementOfficerPin;
    }

    public void setEnforcementOfficerPin(String enforcementOfficerPin) {
        this.enforcementOfficerPin = enforcementOfficerPin;
    }

    public String getEnforcementOfficerName() {
        return enforcementOfficerName;
    }

    public void setEnforcementOfficerName(String enforcementOfficerName) {
        this.enforcementOfficerName = enforcementOfficerName;
    }

    public String getEnforcementAgency() {
        return enforcementAgency;
    }

    public void setEnforcementAgency(String enforcementAgency) {
        this.enforcementAgency = enforcementAgency;
    }

    public String getEnforcementAgencyCode() {
        return enforcementAgencyCode;
    }

    public void setEnforcementAgencyCode(String enforcementAgencyCode) {
        this.enforcementAgencyCode = enforcementAgencyCode;
    }

    public Boolean getAccidentFlag() {
        return accidentFlag;
    }

    public void setAccidentFlag(Boolean accidentFlag) {
        this.accidentFlag = accidentFlag;
    }

    public String getWitnessOfficerPin() {
        return witnessOfficerPin;
    }

    public void setWitnessOfficerPin(String witnessOfficerPin) {
        this.witnessOfficerPin = witnessOfficerPin;
    }

    public String getWitnessOfficerName() {
        return witnessOfficerName;
    }

    public void setWitnessOfficerName(String witnessOfficerName) {
        this.witnessOfficerName = witnessOfficerName;
    }

    public String getCosFormNumber() {
        return cosFormNumber;
    }

    public void setCosFormNumber(String cosFormNumber) {
        this.cosFormNumber = cosFormNumber;
    }

    public String getEvtFormNumber() {
        return evtFormNumber;
    }

    public void setEvtFormNumber(String evtFormNumber) {
        this.evtFormNumber = evtFormNumber;
    }

    public String getMreMinorVersion() {
        return mreMinorVersion;
    }

    public void setMreMinorVersion(String mreMinorVersion) {
        this.mreMinorVersion = mreMinorVersion;
    }

    public String getTicketXml() {
        return ticketXml;
    }

    public void setTicketXml(String ticketXml) {
        this.ticketXml = ticketXml;
    }

    public String getCreatedStatus() {
        return createdStatus;
    }

    public void setCreatedStatus(String createdStatus) {
        this.createdStatus = createdStatus;
    }
}
