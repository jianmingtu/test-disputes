package ca.bc.gov.iamp.justindisputesapi.service;

import ca.bc.gov.iamp.justindisputesapi.api.model.CitationAugmentationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.CourtResults;
import ca.bc.gov.iamp.justindisputesapi.api.model.ProcessStatus;
import ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ProcessStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.utils.TransformationUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CitationDisputesServiceImpl implements CitationDisputesService {

    private final Logger log = LoggerFactory.getLogger(CitationDisputesServiceImpl.class);

    private final OrdsService ordsService;

    public CitationDisputesServiceImpl(OrdsService ordsService) {
        this.ordsService = ordsService;
    }

    // DESC-2 - Change to void return type
    @Override
    public void createCitationDispute(CitationAugmentationType citationDispute) {
        String ticketNumber = "";
        if (citationDispute.getActivityIdentification() != null) {
            ticketNumber = citationDispute.getActivityIdentification().getIdentificationID();
        }

        log.info("createCitationDispute started for ticket: {}", ticketNumber);
        DisputeEntity disputeEntity = TransformationUtility.transform(citationDispute);

        ordsService.createCitationDispute(disputeEntity);
        log.info("Successfully sent citation dispute {} to Justin", ticketNumber);
    }

    // DESC-2 - Change to void return type
    @Override
    public void updateCitationDispute(CitationAugmentationType citationDispute) {
        String ticketNumber = "";
        if (citationDispute.getActivityIdentification() != null) {
            ticketNumber = citationDispute.getActivityIdentification().getIdentificationID();
        }

        log.info("updateCitationDispute started for ticket: {}", ticketNumber);
        DisputeStatusEntity disputeStatusEntity = TransformationUtility.transformDisputeStatus(citationDispute);

        ordsService.updateCitationDispute(disputeStatusEntity);
        log.info("Successfully sent citation dispute {} status update to Justin", ticketNumber);
    }

    @Override
    public CourtResults getDisputeCourtResults() {
        CourtResultsEntity response = ordsService.getCourtResults();
        log.debug("ORDS status: {}", response.getStatus());
        log.info("Retrieved court dispute results");

        return TransformationUtility.transform(response);
    }

    @Override
    public void notifyProcessStatus(ProcessStatus processStatus) {
        log.debug("Notifying processing status: {}", processStatus.getActivityStatus());

        ProcessStatusEntity processStatusEntity = TransformationUtility.transform(processStatus);
        ordsService.notifyProcessStatus(processStatusEntity);

        log.info("Successfully reported data processing status");
    }
}
