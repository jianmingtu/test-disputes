package ca.bc.gov.iamp.justindisputesapi.exception;

public class ServiceUnavailableException extends ApiRuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceUnavailableException(Throwable cause, String error) {
        super(cause, error);
    }

}