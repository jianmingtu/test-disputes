package ca.bc.gov.iamp.justindisputesapi.controller;

import ca.bc.gov.iamp.justindisputesapi.api.ApiApi;
import ca.bc.gov.iamp.justindisputesapi.api.model.CitationAugmentationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.CourtResults;
import ca.bc.gov.iamp.justindisputesapi.api.model.ProcessStatus;
import ca.bc.gov.iamp.justindisputesapi.service.CitationDisputesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.security.RolesAllowed;

@Controller
public class CitationDisputesController implements ApiApi {

    private final Logger log = LoggerFactory.getLogger(CitationDisputesController.class);

    private final CitationDisputesService citationDisputesService;

    public CitationDisputesController(CitationDisputesService citationDisputesService) {
        this.citationDisputesService = citationDisputesService;
    }

    @RolesAllowed("ROLE_CREATE_DISPUTE")
    public ResponseEntity<CitationAugmentationType> createCitationDispute(@RequestBody CitationAugmentationType citationDispute) {
        log.info("Received createCitationDispute call");
        citationDisputesService.createCitationDispute(citationDispute);

        // DESC-2 - create new object here instead of returning it from above method call
        return new ResponseEntity<>(new CitationAugmentationType(), HttpStatus.CREATED);
    }

    @RolesAllowed("ROLE_UPDATE_DISPUTE_STATUS")
    public ResponseEntity<CitationAugmentationType> updateCitationDispute(@RequestBody CitationAugmentationType citationDispute) {
        log.info("Received updateCitationDispute call");
        citationDisputesService.updateCitationDispute(citationDispute);

        // DESC-2 - create new object here instead of returning it from above method call
        return new ResponseEntity<>(new CitationAugmentationType(), HttpStatus.OK);
    }

    @RolesAllowed("ROLE_GET_COURT_RESULTS")
    public ResponseEntity<CourtResults> getDisputeCourtResults() {
        log.info("Received getDisputeCourtResults call");

        CourtResults courtResults = citationDisputesService.getDisputeCourtResults();
        if(courtResults.getItemQuantity() == null || courtResults.getItemQuantity() == 0) {
            return new ResponseEntity<>(courtResults, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(courtResults, HttpStatus.OK);
    }

    @RolesAllowed("ROLE_NOTIFY_PROCESS_STATUS")
    public ResponseEntity<Object> notifyProcessStatus(@RequestBody ProcessStatus processStatus) {
        log.info("Received notifyProcessStatus call");

        if(processStatus.getActivityStatus() == null) {
            log.info("Invalid request body: activityStatus is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        citationDisputesService.notifyProcessStatus(processStatus);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
