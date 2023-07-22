package ca.bc.gov.iamp.justindisputesapi.model.ords;

import org.junit.jupiter.api.Test;

public class ProcessStatusEntityTest {

    private static final String testString = "Test";

    @Test
    public void testFields() {
        ProcessStatusEntity processStatusEntity = new ProcessStatusEntity();
        processStatusEntity.setpSuccess(testString);
    }

}
