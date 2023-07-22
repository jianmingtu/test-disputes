package ca.bc.gov.iamp.justindisputesapi.exception;

import java.util.Collections;
import java.util.List;

public class ApiRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private List<String> errors;

    public ApiRuntimeException(Throwable cause, String error) {
        super(cause);
        errors = Collections.singletonList(error);
    }

    public List<String> getErrors() {
        return errors;
    }
}
