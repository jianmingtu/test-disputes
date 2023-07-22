package ca.bc.gov.iamp.justindisputesapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackages = "ca.bc.gov.iamp")
public class CustomRestExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(CustomRestExceptionHandler.class);

    @ExceptionHandler({ServiceUnavailableException.class})
    public ResponseEntity<ApiError> handleServiceAvailabilityErrors(Exception ex) {
        return sendError(ex, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ResponseEntity<ApiError> sendError(Exception ex, HttpStatus httpStatus) {
        ApiError apiError;
        ApiRuntimeException apiEx = (ApiRuntimeException) ex;
        apiError = new ApiError(httpStatus, apiEx.getLocalizedMessage(), apiEx.getErrors());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
