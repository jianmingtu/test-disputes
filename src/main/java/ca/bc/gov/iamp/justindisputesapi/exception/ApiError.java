package ca.bc.gov.iamp.justindisputesapi.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private int statusCode;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, String error) {
        this(status, message, Arrays.asList(error));
    }

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.errors = errors;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
