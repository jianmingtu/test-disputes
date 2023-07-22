package ca.bc.gov.iamp.justindisputesapi.controller;
import ca.bc.gov.iamp.justindisputesapi.api.model.CitationAugmentationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.ProcessStatus;
import ca.bc.gov.iamp.justindisputesapi.api.model.Verdicts;

import ca.bc.gov.iamp.justindisputesapi.api.model.CourtResults;
import ca.bc.gov.iamp.justindisputesapi.service.CitationDisputesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CitationDisputesControllerTest {

    private CitationDisputesController sut;

    private static String testString = "Test";

    @Mock
    private CitationDisputesService citationDisputesServiceMock;

    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);

        sut = new CitationDisputesController(citationDisputesServiceMock);
    }

    @Test
    public void testGetCitationDisputesNoContent() {

        CourtResults noResults = new CourtResults();
        noResults.setItemQuantity(0);

        Mockito.when(citationDisputesServiceMock.getDisputeCourtResults()).thenReturn(noResults);

        ResponseEntity<CourtResults> response = sut.getDisputeCourtResults();

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void testGetCitationDisputesOk() {
        List<Verdicts> verdictsList = new ArrayList<Verdicts>();
        Verdicts verdict = new Verdicts();
        verdict.setActivityIdentification(testString);
        verdict.setContraventionNumber(testString);
        verdict.setVerdictDate(testString);
        verdict.setVerdictDescriptionText(testString);
        verdict.setVerdictDispositionName(testString);
        verdictsList.add(verdict);
        verdictsList.add(verdict);
        verdictsList.add(verdict);

        CourtResults results = new CourtResults();
        results.setItemQuantity(verdictsList.size());
        results.setVerdicts(verdictsList);

        Mockito.when(citationDisputesServiceMock.getDisputeCourtResults()).thenReturn(results);

        ResponseEntity<CourtResults> response = sut.getDisputeCourtResults();

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testNotifyProcessStatusOk() {
        ProcessStatus testProcessStatus = new ProcessStatus();
        testProcessStatus.setActivityStatus(true);

        ResponseEntity<Object> response = sut.notifyProcessStatus(testProcessStatus);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testNotifyProcessStatusBadRequest() {
        ProcessStatus testProcessStatus = new ProcessStatus();
        testProcessStatus.setActivityStatus(null);

        ResponseEntity<Object> response = sut.notifyProcessStatus(testProcessStatus);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateCitationDispute() {
        CitationAugmentationType citationDispute = new CitationAugmentationType();

        ResponseEntity<CitationAugmentationType> response = sut.createCitationDispute(citationDispute);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateCitationDispute() {
        CitationAugmentationType citationDispute = new CitationAugmentationType();

        ResponseEntity<CitationAugmentationType> response = sut.updateCitationDispute(citationDispute);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
