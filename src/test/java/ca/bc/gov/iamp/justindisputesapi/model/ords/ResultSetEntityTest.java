package ca.bc.gov.iamp.justindisputesapi.model.ords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultSetEntityTest {
    private static String testString = "Test";

    @Test
    public void testFields() {
        ResultSetEntity resultSetEntity = new ResultSetEntity();

        resultSetEntity.setEventGuid(testString);
        resultSetEntity.setApprDate(testString);
        resultSetEntity.setFindFindingCd(testString);
        resultSetEntity.setFindFindingDsc(testString);
        resultSetEntity.setContraventionNumber(testString);
        resultSetEntity.setStatus(testString);

        Assertions.assertEquals(resultSetEntity.getEventGuid(), testString);
        Assertions.assertEquals(resultSetEntity.getApprDate(), testString);
        Assertions.assertEquals(resultSetEntity.getFindFindingCd(), testString);
        Assertions.assertEquals(resultSetEntity.getFindFindingDsc(), testString);
        Assertions.assertEquals(resultSetEntity.getContraventionNumber(), testString);
        Assertions.assertEquals(resultSetEntity.getStatus(), testString);

    }
}
