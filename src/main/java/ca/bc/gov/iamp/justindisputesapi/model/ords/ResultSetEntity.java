package ca.bc.gov.iamp.justindisputesapi.model.ords;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResultSetEntity extends BaseEntity implements Serializable {

    @JsonProperty("event_guid")
    private String eventGuid;

    @JsonProperty("appr_date")
    private String apprDate;

    @JsonProperty("find_finding_cd")
    private String findFindingCd;

    @JsonProperty("find_finding_dsc")
    private String findFindingDsc;

    @JsonProperty("contravention_number")
    private String contraventionNumber;

    public String getEventGuid() {
        return eventGuid;
    }

    public void setEventGuid(String eventGuid) {
        this.eventGuid = eventGuid;
    }

    public String getApprDate() {
        return apprDate;
    }

    public void setApprDate(String apprDate) {
        this.apprDate = apprDate;
    }

    public String getFindFindingCd() {
        return findFindingCd;
    }

    public void setFindFindingCd(String findFindingCd) {
        this.findFindingCd = findFindingCd;
    }

    public String getFindFindingDsc() {
        return findFindingDsc;
    }

    public void setFindFindingDsc(String findFindingDsc) {
        this.findFindingDsc = findFindingDsc;
    }

    public String getContraventionNumber() {
        return contraventionNumber;
    }

    public void setContraventionNumber(String contraventionNumber) {
        this.contraventionNumber = contraventionNumber;
    }
}

