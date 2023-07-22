package ca.bc.gov.iamp.justindisputesapi.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ApiErrorTest {

    private final static String testString = "test";

    @Test
    public void testFields() {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, testString, testString);

        apiError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setMessage(testString);

        List<String> errorList = new ArrayList<String>();
        errorList.add(testString);
        apiError.setErrors(errorList);

        Assertions.assertEquals(apiError.getStatusCode(), HttpStatus.BAD_REQUEST.value());
        Assertions.assertEquals(apiError.getStatus(), HttpStatus.BAD_REQUEST);
        Assertions.assertEquals(apiError.getMessage(), testString);
        Assertions.assertEquals(apiError.getErrors(), errorList);

    }
}
