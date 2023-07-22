package ca.bc.gov.iamp.justindisputesapi.model.ords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CourtResultsEntityTest {

    private static String testString = "Test";

    @Test
    public void testFields() {
        CourtResultsEntity testEntity = createTestEntity();

        List<ResultSetEntity> testResultSetList = new ArrayList<ResultSetEntity>();

        ResultSetEntity resultSetEntity = new ResultSetEntity();
        testResultSetList.add(resultSetEntity);
        testResultSetList.add(resultSetEntity);
        testResultSetList.add(resultSetEntity);
        testResultSetList.add(resultSetEntity);
        testResultSetList.add(resultSetEntity);

        testEntity.setNumberOfItems(testResultSetList.size());
        testEntity.setResultSet(testResultSetList);
        testEntity.setStatusMessage(testString);
        testEntity.setStatus(testString);

        Assertions.assertEquals(testEntity.getNumberOfItems(), testResultSetList.size());
        Assertions.assertEquals(testEntity.getResultSet(), testResultSetList);
        Assertions.assertEquals(testEntity.getStatusMessage(), testString);
        Assertions.assertEquals(testEntity.getStatus(), testString);

    }

    public static CourtResultsEntity createTestEntity() {
        CourtResultsEntity testEntity = new CourtResultsEntity();

        List<ResultSetEntity> testResultSetList = new ArrayList<ResultSetEntity>();

        ResultSetEntity resultSetEntity = new ResultSetEntity();
        resultSetEntity.setEventGuid(testString);
        resultSetEntity.setApprDate(testString);
        resultSetEntity.setFindFindingCd(testString);
        resultSetEntity.setFindFindingDsc(testString);
        resultSetEntity.setContraventionNumber(testString);
        resultSetEntity.setStatus(testString);


        testResultSetList.add(resultSetEntity);
        testResultSetList.add(resultSetEntity);
        testResultSetList.add(resultSetEntity);
        testResultSetList.add(resultSetEntity);
        testResultSetList.add(resultSetEntity);

        testEntity.setNumberOfItems(testResultSetList.size());
        testEntity.setResultSet(testResultSetList);
        testEntity.setStatusMessage(testString);
        testEntity.setStatus(testString);

        return testEntity;
    }
}
