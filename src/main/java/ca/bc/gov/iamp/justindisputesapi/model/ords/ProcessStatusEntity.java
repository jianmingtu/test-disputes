package ca.bc.gov.iamp.justindisputesapi.model.ords;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProcessStatusEntity extends BaseEntity implements Serializable {

    @JsonProperty("p_success")
    private String pSuccess;

    public String getpSuccess() {
        return pSuccess;
    }

    public void setpSuccess(String pSuccess) {
        this.pSuccess = pSuccess;
    }
}
