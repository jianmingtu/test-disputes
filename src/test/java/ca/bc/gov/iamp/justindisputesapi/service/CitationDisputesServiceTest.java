package ca.bc.gov.iamp.justindisputesapi.service;

import ca.bc.gov.iamp.justindisputesapi.api.model.CitationAugmentationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.CourtResults;
import ca.bc.gov.iamp.justindisputesapi.api.model.IdentificationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.ProcessStatus;
import ca.bc.gov.iamp.justindisputesapi.api.model.Verdicts;
import ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ResultSetEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntityTest.createTestEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CitationDisputesServiceTest {

    private static final String testString = "test";

    private CitationDisputesServiceImpl sut;

    @Mock
    private OrdsService ordsServiceMock;

    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);

        sut = new CitationDisputesServiceImpl(ordsServiceMock);
    }

    @Test
    public void getDisputeCourtResultsTest() {
        CourtResultsEntity testEntity = createTestEntity();
        Mockito.when(ordsServiceMock.getCourtResults()).thenReturn(testEntity);

        CourtResults returnedCourtResults = sut.getDisputeCourtResults();

        Assertions.assertEquals(returnedCourtResults.getItemQuantity(), testEntity.getNumberOfItems());

        List<Verdicts> verdictList = returnedCourtResults.getVerdicts();
        List<ResultSetEntity> resultSetEntityList = testEntity.getResultSet();

        for(int i = 0; i < resultSetEntityList.size(); i++) {
            Verdicts verdict = verdictList.get(i);
            ResultSetEntity resultSetEntity = resultSetEntityList.get(i);

            Assertions.assertEquals(verdict.getActivityIdentification(), resultSetEntity.getEventGuid());
            Assertions.assertEquals(verdict.getContraventionNumber(), resultSetEntity.getContraventionNumber());
            Assertions.assertEquals(verdict.getVerdictDate(), resultSetEntity.getApprDate());
            Assertions.assertEquals(verdict.getVerdictDescriptionText(), resultSetEntity.getFindFindingDsc());
            Assertions.assertEquals(verdict.getVerdictDispositionName(), resultSetEntity.getFindFindingCd());
        }
    }

    @Test
    public void notifyProcessStatusTest() {
        ProcessStatus processStatus = new ProcessStatus();
        processStatus.setActivityStatus(true);

        Assertions.assertDoesNotThrow(() -> sut.notifyProcessStatus(processStatus));
    }

    @Test
    public void createCitationDisputeNullActivityIdentification() {
        CitationAugmentationType citationDispute = new CitationAugmentationType();
        citationDispute.setActivityIdentification(null);

        Assertions.assertDoesNotThrow(() -> sut.createCitationDispute(citationDispute));
    }

    @Test
    public void createCitationDisputeNonNullActivityIdentification() {
        CitationAugmentationType citationDispute = new CitationAugmentationType();
        IdentificationType identificationType = new IdentificationType();
        identificationType.setIdentificationID(testString);
        citationDispute.setActivityIdentification(identificationType);

        Assertions.assertDoesNotThrow(() -> sut.createCitationDispute(citationDispute));
    }

    @Test
    public void updateCitationDisputeNullActivityIdentification() {
        CitationAugmentationType citationDispute = new CitationAugmentationType();
        citationDispute.setActivityIdentification(null);

        Assertions.assertDoesNotThrow(() -> sut.updateCitationDispute(citationDispute));
    }

    @Test
    public void updateCitationDisputeNonNullActivityIdentification() {
        CitationAugmentationType citationDispute = new CitationAugmentationType();
        IdentificationType identificationType = new IdentificationType();
        identificationType.setIdentificationID(testString);
        citationDispute.setActivityIdentification(identificationType);

        Assertions.assertDoesNotThrow(() -> sut.updateCitationDispute(citationDispute));
    }
}
