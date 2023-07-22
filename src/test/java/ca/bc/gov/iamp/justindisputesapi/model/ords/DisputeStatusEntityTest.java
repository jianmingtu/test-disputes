package ca.bc.gov.iamp.justindisputesapi.model.ords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisputeStatusEntityTest {

    private static final String testString = "test";

    @Test
    public void testFields() {
        DisputeStatusEntity disputeStatusEntity = new DisputeStatusEntity();
        disputeStatusEntity.setUpdatedStatus(testString);
        disputeStatusEntity.setViolationTicketNo(testString);
        disputeStatusEntity.setCountNumber(testString);
        disputeStatusEntity.setClientTypeCode(testString);
        disputeStatusEntity.setActionType(testString);
        disputeStatusEntity.setActionDate(testString);
        disputeStatusEntity.setDisputantSurname(testString);
        disputeStatusEntity.setDisputantGiven1Name(testString);
        disputeStatusEntity.setDisputantGiven2Name(testString);
        disputeStatusEntity.setDisputantGiven3Name(testString);
        disputeStatusEntity.setOrganizationName(testString);
        disputeStatusEntity.setDriverLicenseNo(testString);
        disputeStatusEntity.setDriverMvbClientNo(testString);
        disputeStatusEntity.setStatus(testString);

        Assertions.assertEquals(testString, disputeStatusEntity.getUpdatedStatus());
        Assertions.assertEquals(testString, disputeStatusEntity.getViolationTicketNo());
        Assertions.assertEquals(testString, disputeStatusEntity.getCountNumber());
        Assertions.assertEquals(testString, disputeStatusEntity.getClientTypeCode());
        Assertions.assertEquals(testString, disputeStatusEntity.getActionType());
        Assertions.assertEquals(testString, disputeStatusEntity.getActionDate());
        Assertions.assertEquals(testString, disputeStatusEntity.getDisputantSurname());
        Assertions.assertEquals(testString, disputeStatusEntity.getDisputantGiven1Name());
        Assertions.assertEquals(testString, disputeStatusEntity.getDisputantGiven2Name());
        Assertions.assertEquals(testString, disputeStatusEntity.getDisputantGiven3Name());
        Assertions.assertEquals(testString, disputeStatusEntity.getOrganizationName());
        Assertions.assertEquals(testString, disputeStatusEntity.getDriverLicenseNo());
        Assertions.assertEquals(testString, disputeStatusEntity.getDriverMvbClientNo());
        Assertions.assertEquals(testString, disputeStatusEntity.getStatus());

    }
}
