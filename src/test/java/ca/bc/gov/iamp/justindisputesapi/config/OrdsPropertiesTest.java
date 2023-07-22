package ca.bc.gov.iamp.justindisputesapi.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrdsPropertiesTest {

    private final static String testString = "test";

    @Test
    public void testFields() {

        OrdsProperties ordsProperties = new OrdsProperties();
        ordsProperties.setUsername(testString);
        ordsProperties.setPassword(testString);
        ordsProperties.setBaseurl(testString);
        ordsProperties.setGetDisputesEndpoint(testString);
        ordsProperties.setNotifyProcessStatusEndpoint(testString);
        ordsProperties.setCreateCitationDisputeEndpoint(testString);
        ordsProperties.setUpdateCitationDisputeEndpoint(testString);
        Assertions.assertEquals(testString, ordsProperties.getUsername());
        Assertions.assertEquals(testString, ordsProperties.getPassword());
        Assertions.assertEquals(testString, ordsProperties.getBaseurl());
        Assertions.assertEquals(testString, ordsProperties.getGetDisputesEndpoint());
        Assertions.assertEquals(testString, ordsProperties.getNotifyProcessStatusEndpoint());
        Assertions.assertEquals(testString, ordsProperties.getCreateCitationDisputeEndpoint());
        Assertions.assertEquals(testString, ordsProperties.getUpdateCitationDisputeEndpoint());

    }
}
