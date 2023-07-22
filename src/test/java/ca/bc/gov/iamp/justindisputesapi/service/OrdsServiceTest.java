package ca.bc.gov.iamp.justindisputesapi.service;

import ca.bc.gov.iamp.justindisputesapi.config.OrdsProperties;
import ca.bc.gov.iamp.justindisputesapi.exception.ServiceUnavailableException;
import ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ProcessStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ResultSetEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntityTest.createTestEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrdsServiceTest {

    private static final String testString = "test";
    private static final String testUrl = "http://example.com";
    private static final String testEndpoint = "/endpoint";

    @Mock
    private RestTemplate mockRestTemplate;

    @Mock
    private OrdsProperties mockOrdsProperties;

    private OrdsService sut;

    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);

        sut = new OrdsServiceImpl(mockOrdsProperties, mockRestTemplate);
        Mockito.when(mockOrdsProperties.getUsername()).thenReturn(testString);
        Mockito.when(mockOrdsProperties.getPassword()).thenReturn(testString);
        Mockito.when(mockOrdsProperties.getBaseurl()).thenReturn(testUrl);
        Mockito.when(mockOrdsProperties.getGetDisputesEndpoint()).thenReturn(testEndpoint);
        Mockito.when(mockOrdsProperties.getNotifyProcessStatusEndpoint()).thenReturn(testEndpoint);
        Mockito.when(mockOrdsProperties.getCreateCitationDisputeEndpoint()).thenReturn(testEndpoint);
        Mockito.when(mockOrdsProperties.getUpdateCitationDisputeEndpoint()).thenReturn(testEndpoint);
    }

    @Test
    public void testGetCourtResultsSuccess() {
        CourtResultsEntity mockEntity = createTestEntity();
        ResponseEntity<CourtResultsEntity> mockResponse = new ResponseEntity<CourtResultsEntity>(mockEntity, HttpStatus.OK);

        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.GET), any(), eq(CourtResultsEntity.class)))
                .thenReturn(mockResponse);

        CourtResultsEntity result = sut.getCourtResults();
        Assertions.assertEquals(result.getNumberOfItems(), mockEntity.getNumberOfItems());
        Assertions.assertEquals(result.getStatusMessage(), mockEntity.getStatusMessage());
        Assertions.assertEquals(result.getStatus(), mockEntity.getStatus());

        List<ResultSetEntity> mockResultSetEntityList = mockEntity.getResultSet();
        List<ResultSetEntity> resultSetEntityList = result.getResultSet();
        for(int i = 0; i < mockResultSetEntityList.size(); i++) {
            ResultSetEntity mockResultSetEntity = mockResultSetEntityList.get(i);
            ResultSetEntity resultSetEntity = resultSetEntityList.get(i);

            Assertions.assertEquals(resultSetEntity.getEventGuid(), mockResultSetEntity.getEventGuid());
            Assertions.assertEquals(resultSetEntity.getContraventionNumber(), mockResultSetEntity.getContraventionNumber());
            Assertions.assertEquals(resultSetEntity.getApprDate(), mockResultSetEntity.getApprDate());
            Assertions.assertEquals(resultSetEntity.getFindFindingDsc(), mockResultSetEntity.getFindFindingDsc());
            Assertions.assertEquals(resultSetEntity.getFindFindingCd(), mockResultSetEntity.getFindFindingCd());
        }
    }

    @Test
    public void testGetCourtResultsServerException() {
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.GET), any(), eq(CourtResultsEntity.class)))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, testString));

        Assertions.assertThrows(ServiceUnavailableException.class, () -> {
            sut.getCourtResults();
        });
    }

    @Test
    public void testGetCourtResultsClientException() {
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.GET), any(), eq(CourtResultsEntity.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.I_AM_A_TEAPOT, testString));

        Assertions.assertThrows(ServiceUnavailableException.class, () -> {
            sut.getCourtResults();
        });
    }

    @Test
    public void testNotifyProcessStatusSuccess() {
        ProcessStatusEntity mockEntity = new ProcessStatusEntity();
        mockEntity.setStatus(testString);

        ResponseEntity<ProcessStatusEntity> mockResponse = new ResponseEntity<ProcessStatusEntity>(mockEntity, HttpStatus.OK);
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.POST), any(), eq(ProcessStatusEntity.class)))
                .thenReturn(mockResponse);

        ProcessStatusEntity testEntity = new ProcessStatusEntity();
        testEntity.setpSuccess("T");
        Assertions.assertDoesNotThrow(() -> sut.notifyProcessStatus(testEntity));
    }

    @Test
    public void testNotifyProcessStatusServerException() {
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.POST), any(), eq(ProcessStatusEntity.class)))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        ProcessStatusEntity testEntity = new ProcessStatusEntity();
        testEntity.setpSuccess("T");

        Assertions.assertThrows(ServiceUnavailableException.class, () -> sut.notifyProcessStatus(testEntity));
    }

    @Test
    public void testNotifyProcessStatusClientException() {
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.POST), any(), eq(ProcessStatusEntity.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.I_AM_A_TEAPOT));

        ProcessStatusEntity testEntity = new ProcessStatusEntity();
        testEntity.setpSuccess("T");

        Assertions.assertThrows(ServiceUnavailableException.class, () -> sut.notifyProcessStatus(testEntity));
    }

    @Test
    public void testCreateCitationDisputeSuccess() {
        DisputeEntity mockEntity = new DisputeEntity();
        mockEntity.setCreatedStatus(testString);

        ResponseEntity<DisputeEntity> mockResponse = new ResponseEntity<DisputeEntity>(mockEntity, HttpStatus.OK);
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.POST), any(), eq(DisputeEntity.class)))
                .thenReturn(mockResponse);

        DisputeEntity testEntity = new DisputeEntity();
        Assertions.assertDoesNotThrow(() -> sut.createCitationDispute(testEntity));
    }

    @Test
    public void testCreateCitationDisputeServerException() {
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.POST), any(), eq(DisputeEntity.class)))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        DisputeEntity testEntity = new DisputeEntity();
        Assertions.assertThrows(ServiceUnavailableException.class, () -> sut.createCitationDispute(testEntity));

    }

    @Test
    public void testCreateCitationDisputeClientException() {
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.POST), any(), eq(DisputeEntity.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.I_AM_A_TEAPOT));

        DisputeEntity testEntity = new DisputeEntity();
        Assertions.assertThrows(ServiceUnavailableException.class, () -> sut.createCitationDispute(testEntity));
    }

    @Test
    public void testUpdateCitationDisputeSuccess() {
        DisputeStatusEntity mockEntity = new DisputeStatusEntity();
        mockEntity.setUpdatedStatus(testString);

        ResponseEntity<DisputeStatusEntity> mockResponse = new ResponseEntity<DisputeStatusEntity>(mockEntity, HttpStatus.OK);
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.POST), any(), eq(DisputeStatusEntity.class)))
                .thenReturn(mockResponse);

        DisputeStatusEntity testEntity = new DisputeStatusEntity();
        Assertions.assertDoesNotThrow(() -> sut.updateCitationDispute(testEntity));
    }

    @Test
    public void testUpdateCitationDisputeServerException() {
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.POST), any(), eq(DisputeStatusEntity.class)))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        DisputeStatusEntity testEntity = new DisputeStatusEntity();
        Assertions.assertThrows(ServiceUnavailableException.class, () -> sut.updateCitationDispute(testEntity));
    }

    @Test
    public void testUpdateCitationDisputeClientException() {
        Mockito.when(mockRestTemplate.exchange(eq(testUrl + testEndpoint), eq(HttpMethod.POST), any(), eq(DisputeStatusEntity.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.I_AM_A_TEAPOT));

        DisputeStatusEntity testEntity = new DisputeStatusEntity();
        Assertions.assertThrows(ServiceUnavailableException.class, () -> sut.updateCitationDispute(testEntity));
    }
}
