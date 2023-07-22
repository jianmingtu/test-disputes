package ca.bc.gov.iamp.justindisputesapi.model.ords;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class CourtResultsEntity extends BaseEntity implements Serializable {

    @JsonProperty("number_of_records")
    private int numberOfItems;

    @JsonProperty("result_set")
    private List<ResultSetEntity> resultSet;

    @JsonProperty("status_message")
    private String statusMessage;

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public List<ResultSetEntity> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<ResultSetEntity> resultSet) {
        this.resultSet = resultSet;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
