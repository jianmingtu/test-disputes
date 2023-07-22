package ca.bc.gov.iamp.justindisputesapi.service;

import ca.bc.gov.iamp.justindisputesapi.api.model.CitationAugmentationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.CourtResults;
import ca.bc.gov.iamp.justindisputesapi.api.model.ProcessStatus;

public interface CitationDisputesService {

    // DESC-2 - Change to void return type
    void createCitationDispute(CitationAugmentationType citationDispute);

    // DESC-2 - Change to void return type
    void updateCitationDispute(CitationAugmentationType citationDispute);

    CourtResults getDisputeCourtResults();

    void notifyProcessStatus(ProcessStatus processStatus);
}
