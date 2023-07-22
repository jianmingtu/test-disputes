package ca.bc.gov.iamp.justindisputesapi.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

public class CustomRestExceptionHandlerTest {

    @Test
    public void testSendApiRuntimeException() {
        HttpServerErrorException serverErrorException = new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        ServiceUnavailableException serviceUnavailableException = new ServiceUnavailableException(serverErrorException, "test Error");

        CustomRestExceptionHandler handler = new CustomRestExceptionHandler();
        ResponseEntity<ApiError> result = handler.handleServiceAvailabilityErrors(serviceUnavailableException);

        Assertions.assertEquals(serviceUnavailableException.getErrors(), result.getBody().getErrors());
    }

}
