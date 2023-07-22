package ca.bc.gov.iamp.justindisputesapi.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeoutPropertiesTest {

    private static final int testInt = 3000;

    @Test
    public void testFields() {

        TimeoutProperties timeoutProperties = new TimeoutProperties();
        timeoutProperties.setRead(testInt);
        timeoutProperties.setConnect(testInt);
        Assertions.assertEquals(timeoutProperties.getRead(), testInt);
        Assertions.assertEquals(timeoutProperties.getConnect(), testInt);

    }
}
