package ca.bc.gov.iamp.justindisputesapi.model.ords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BaseEntityTest {

    private static String testString = "Test";

    @Test
    public void testFields() {
        BaseEntity testEntity = new BaseEntity();

        testEntity.setStatus(testString);
        Assertions.assertEquals(testEntity.getStatus(), testString);

    }
}
