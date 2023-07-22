package ca.bc.gov.iamp.justindisputesapi.service;

import ca.bc.gov.iamp.justindisputesapi.config.OrdsProperties;
import ca.bc.gov.iamp.justindisputesapi.exception.ServiceUnavailableException;
import ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ProcessStatusEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class OrdsServiceImpl implements OrdsService {

    private final Logger log = LoggerFactory.getLogger(OrdsServiceImpl.class);

    private final OrdsProperties ordsProperties;

    private final RestTemplate restTemplate;

    public OrdsServiceImpl(OrdsProperties ordsProperties, RestTemplate restTemplate) {
        this.ordsProperties = ordsProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void createCitationDispute(DisputeEntity disputeEntity) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(ordsProperties.getUsername(), ordsProperties.getPassword());

            HttpEntity<DisputeEntity> request = new HttpEntity<DisputeEntity>(disputeEntity, headers);
            ResponseEntity<DisputeEntity> responseEntityResult = restTemplate.exchange(
                    ordsProperties.getBaseurl() + ordsProperties.getCreateCitationDisputeEndpoint(),
                    HttpMethod.POST,
                    request,
                    DisputeEntity.class
            );
            log.debug("ORDS status: {}", responseEntityResult.getBody().getCreatedStatus());
        } catch (HttpServerErrorException e) {
            log.error("Server exception: {}", e.getMessage());
            // DESC-1 - throw new exception
            throw new ServiceUnavailableException(e, "ORDS experienced an internal error (5xx)");
        } catch (HttpClientErrorException e) {
            log.error("Rest Exception: {}", e.getMessage());
            // DESC-1 - throw new exception
            throw new ServiceUnavailableException(e, "ORDS rejected the request (4xx)");
        }
    }

    @Override
    public void updateCitationDispute(DisputeStatusEntity disputeStatusEntity) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(ordsProperties.getUsername(), ordsProperties.getPassword());

            HttpEntity<DisputeStatusEntity> request = new HttpEntity<DisputeStatusEntity>(disputeStatusEntity, headers);
            ResponseEntity<DisputeStatusEntity> responseEntityResult = restTemplate.exchange(
                    ordsProperties.getBaseurl() + ordsProperties.getUpdateCitationDisputeEndpoint(),
                    HttpMethod.POST,
                    request,
                    DisputeStatusEntity.class
            );
            log.debug("ORDS status: {}", responseEntityResult.getBody().getUpdatedStatus());
        } catch (HttpServerErrorException e) {
            log.error("Server exception: {}", e.getMessage());
            // DESC-1 - throw new exception
            throw new ServiceUnavailableException(e, "ORDS experienced an internal error (5xx)");
        } catch (HttpClientErrorException e) {
            log.error("Rest Exception: {}", e.getMessage());
            // DESC-1 - throw new exception
            throw new ServiceUnavailableException(e, "ORDS rejected the request (4xx)");
        }
    }

    @Override
    public CourtResultsEntity getCourtResults() {
        CourtResultsEntity result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(ordsProperties.getUsername(), ordsProperties.getPassword());

            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<CourtResultsEntity> responseEntityResult = restTemplate.exchange(
                    ordsProperties.getBaseurl() + ordsProperties.getGetDisputesEndpoint(),
                    HttpMethod.GET,
                    request,
                    CourtResultsEntity.class
            );
            result = responseEntityResult.getBody();
        } catch (HttpServerErrorException e) {
            log.error("Server exception: {}", e.getMessage());
            // DESC-1 - throw new exception
            throw new ServiceUnavailableException(e, "ORDS experienced an internal error (5xx)");
        } catch (HttpClientErrorException e) {
            log.error("Rest Exception: {}", e.getMessage());
            // DESC-1 - throw new exception
            throw new ServiceUnavailableException(e, "ORDS rejected the request (4xx)");
        }

        return result;

    }

    @Override
    public void notifyProcessStatus(ProcessStatusEntity processStatusEntity) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(ordsProperties.getUsername(), ordsProperties.getPassword());

            HttpEntity<ProcessStatusEntity> request = new HttpEntity<ProcessStatusEntity>(processStatusEntity, headers);
            ResponseEntity<ProcessStatusEntity> response = restTemplate.exchange(
                    ordsProperties.getBaseurl() + ordsProperties.getNotifyProcessStatusEndpoint(),
                    HttpMethod.POST,
                    request,
                    ProcessStatusEntity.class
            );
            log.debug("ORDS Response: {}", response.getBody().getStatus());
        } catch (HttpServerErrorException e) {
            log.error("Server exception: {}", e.getMessage());
            // DESC-1 - throw new exception
            throw new ServiceUnavailableException(e, "ORDS experienced an internal error (5xx)");
        } catch (HttpClientErrorException e) {
            log.error("Rest Exception: {}", e.getMessage());
            // DESC-1 - throw new exception
            throw new ServiceUnavailableException(e, "ORDS rejected the request (4xx)");
        }
    }
}
