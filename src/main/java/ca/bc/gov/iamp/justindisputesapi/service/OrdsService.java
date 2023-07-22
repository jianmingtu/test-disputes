package ca.bc.gov.iamp.justindisputesapi.service;

import ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ProcessStatusEntity;

public interface OrdsService {

    void createCitationDispute(DisputeEntity dispute);

    void updateCitationDispute(DisputeStatusEntity disputeStatusEntity);

    CourtResultsEntity getCourtResults();

    void notifyProcessStatus(ProcessStatusEntity processStatusEntity);

}
