package ca.bc.gov.iamp.justindisputesapi.utils;

import ca.bc.gov.iamp.justindisputesapi.api.model.CitationAugmentationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.CourtResults;
import ca.bc.gov.iamp.justindisputesapi.api.model.ProcessStatus;
import ca.bc.gov.iamp.justindisputesapi.api.model.Verdicts;
import ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ProcessStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ResultSetEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntityTest.createTestEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransformationUtilityTest {

    @Test
    public void testCourtResultsTransformation() {

        CourtResultsEntity courtResultsEntity = createTestEntity();
        CourtResults courtResults = TransformationUtility.transform(courtResultsEntity);

        Assertions.assertEquals(courtResults.getItemQuantity(), courtResultsEntity.getNumberOfItems());

        List<Verdicts> verdictList = courtResults.getVerdicts();
        List<ResultSetEntity> resultSetEntityList = courtResultsEntity.getResultSet();

        for(int i = 0; i < resultSetEntityList.size(); i++) {
            Verdicts verdict = verdictList.get(i);
            ResultSetEntity resultSetEntity = resultSetEntityList.get(i);

            Assertions.assertEquals(verdict.getActivityIdentification(), resultSetEntity.getEventGuid());
            Assertions.assertEquals(verdict.getContraventionNumber(), resultSetEntity.getContraventionNumber());
            Assertions.assertEquals(verdict.getVerdictDate(), resultSetEntity.getApprDate());
            Assertions.assertEquals(verdict.getVerdictDescriptionText(), resultSetEntity.getFindFindingDsc());
            Assertions.assertEquals(verdict.getVerdictDispositionName(), resultSetEntity.getFindFindingCd());
        }

    }

    @Test
    public void testNotifyProcessStatusTransformationTrue() {
        ProcessStatus processStatus = new ProcessStatus();
        processStatus.setActivityStatus(true);

        ProcessStatusEntity result = TransformationUtility.transform(processStatus);
        Assertions.assertEquals("T", result.getpSuccess());
    }

    @Test
    public void testNotifyProcessStatusTransformationFalse() {
        ProcessStatus processStatus = new ProcessStatus();
        processStatus.setActivityStatus(false);

        ProcessStatusEntity result = TransformationUtility.transform(processStatus);
        Assertions.assertEquals("F", result.getpSuccess());
    }

    @Test
    public void testCreateCitationDisputeTransformationI() {

        String jsonInput = "{\"activityIdentification\":{\"identificationID\":\"EZ04500019\"},\"activityDate\":{\"dateText\":\"2019-01-11\"},\"citationAgency\":{\"organizationIdentification\":{\"identificationID\":\"402\"},\"organizationName\":\"VICTORIA CITY MUN\"},\"citationIssuingOfficial\":{\"enforcementOfficialBadgeIdentification\":{\"identificationID\":\"9804161\"},\"roleOfPerson\":{\"personName\":{\"personFullName\":\"RHODES, CHRISTOPHER\"}}},\"citationSubject\":{\"subjectIdentification\":{\"identificationID\":\"451450\",\"identificationCategoryCode\":\"I\"},\"roleOfPerson\":{\"personBirthDate\":{\"dateText\":\"1992-01-05\"},\"personName\":{\"personGivenName\":\"CHARLES\",\"personGivenName2\":\"HARRY\",\"personGivenName3\":\"SAMIR\",\"personSurName\":\"HUNTER\"},\"personSexCode\":\"M\"},\"roleOfOrganization\":{\"organizationName\":\"Org\"}},\"citationIssuedLocation\":{\"address\":{\"locationCityName\":\"VICTORIA\"}},\"citationViolation\":{\"activityIdentification\":{\"identificationID\":\"1\"},\"violatedAct\":\"MVA\",\"violatedSection\":\"13\",\"violatedSubSection\":\"1\",\"violatedParagraph\":\"2\",\"violatedSubParagraph\":\"b\",\"violatedClause\":\"xyz\",\"icbcViolatedAct\":\"MVA\",\"icbcCompressedSection\":\"13.1B\",\"disputeType\":\"A\",\"incidentTrafficAccidentInvolvedIndicator\":false},\"citationFineAmount\":{\"amount\":138.0},\"serviceDate\":{\"dateText\":\"2019-01-11\"},\"disputeFilingDate\":{\"dateText\":\"2019-01-11\"},\"citationSubjectMailingAddress\":{\"addressLine1\":\"1288 Flying Scotsman Road\",\"addressLine2\":\"Highlands\",\"addressLine3\":\"ADDR3\",\"locationCityName\":\"VICTORIA\",\"locationCanadianProvinceCode\":\"BC\",\"locationPostalCode\":\"H1H2H3\",\"locationCountryCode\":\"CAN\"},\"citationSubjectDriverLicense\":{\"driverLicenseIdentification\":{\"identificationID\":\"1700202\",\"identificationJurisdiction\":{\"jurisdictionText\":\"BC\"}}},\"vehicle\":{\"itemColorDescriptionText\":\"GREEN\",\"itemOwner\":{\"entityPerson\":{\"personName\":{\"personFullName\":\"MOSS, STERLING\"}}},\"vehicleIdentification\":{\"identificationID\":\"BJV 896\",\"identificationJurisdiction\":{\"jurisdictionText\":\"BC\"}},\"vehicleMakeCode\":\"SD\"},\"courtHearingLocation\":{\"locationIdentification\":{\"identificationID\":\"1201\"},\"address\":{\"addressFullText\":\"VANCOUVER, B.C.\"}},\"citationWitnessingOfficial\":{\"enforcementOfficialBadgeIdentification\":{\"identificationID\":\"ZZ0025\"},\"roleOfPerson\":{\"personName\":{\"personFullName\":\"GUPTA, VIVEK\"}}},\"violationReportDocument\":{\"documentBinary\":{\"base64BinaryObject\":\"PD94bI=\"},\"documentSoftware\":{\"softwareVersionNumberID\":\"1.0\"},\"certificateOfServiceFormNumber\":\"MV6010(102018)\",\"electronicViolationTicketingFormNumber\":\"MV6000E(102018)\"}}";
        String expectedJsonOutput = "{\"ViolationTicketNo\":\"EZ04500019\",\"DisputeFiledDate\":\"2019-01-11\",\"ServiceDate\":\"2019-01-11\",\"ClientTypeCode\":\"I\",\"DriverSurname\":\"HUNTER\",\"DriverGiven1Name\":\"CHARLES\",\"DriverGiven2Name\":\"HARRY\",\"DriverGiven3Name\":\"SAMIR\",\"DriverLicenseNo\":\"1700202\",\"DriverJurisdictionCode\":\"BC\",\"DriverMvbClientNo\":\"451450\",\"DriverGender\":\"M\",\"DriverBirthdate\":\"1992-01-05\",\"AddressLine\":\"1288 Flying Scotsman Road\",\"AddressLine2\":\"Highlands\",\"AddressLine3\":\"ADDR3\",\"AddressCity\":\"VICTORIA\",\"AddressJurisdictionCode\":\"BC\",\"AddressCountryCode\":\"CAN\",\"AddressPostalCode\":\"H1H2H3\",\"ViolationDate\":\"2019-01-11\",\"ViolationCity\":\"VICTORIA\",\"CountNumber\":\"1\",\"IcbcAct\":\"MVA\",\"IcbcCountSection\":\"13.1B\",\"CsbAct\":\"MVA\",\"CsbSection\":\"13\",\"CsbSubsection\":\"1\",\"CsbParagraph\":\"2\",\"CsbSubparagraph\":\"b\",\"CsbCountClause\":\"xyz\",\"TicketedAmt\":138.0,\"DisputeType\":\"A\",\"VehicleJuridiction\":\"BC\",\"VehiclePlateNumber\":\"BJV 896\",\"VehicleOwnerNm\":\"MOSS, STERLING\",\"VehicleType\":\"SD\",\"VehicleColour\":\"GREEN\",\"HearingLocation\":\"VANCOUVER, B.C.\",\"HearingLocationCode\":\"1201\",\"EnforcementOfficerPin\":\"9804161\",\"EnforcementOfficerName\":\"RHODES, CHRISTOPHER\",\"EnforcementAgency\":\"VICTORIA CITY MUN\",\"EnforcementAgencyCode\":\"402\",\"AccidentFlag\":false,\"WitnessOfficerPin\":\"ZZ0025\",\"WitnessOfficerName\":\"GUPTA, VIVEK\",\"CosFormNumber\":\"MV6010(102018)\",\"EvtFormNumber\":\"MV6000E(102018)\",\"MreMinorVersion\":\"1.0\",\"TicketXml\":\"PD94bI=\"}";

        CitationAugmentationType citationAugmentationType = JsonUtility.toObject(jsonInput, CitationAugmentationType.class);

        DisputeEntity disputeEntity = TransformationUtility.transform(citationAugmentationType);

        String disputeEntityJson = JsonUtility.toJson(disputeEntity);

        Assertions.assertEquals(expectedJsonOutput, disputeEntityJson);
    }

    @Test
    public void testCreateCitationDisputeTransformationO() {

        String jsonInput = "{\"activityIdentification\":{\"identificationID\":\"EZ04500019\"},\"activityDate\":{\"dateText\":\"2019-01-11\"},\"citationAgency\":{\"organizationIdentification\":{\"identificationID\":\"402\"},\"organizationName\":\"VICTORIA CITY MUN\"},\"citationIssuingOfficial\":{\"enforcementOfficialBadgeIdentification\":{\"identificationID\":\"9804161\"},\"roleOfPerson\":{\"personName\":{\"personFullName\":\"RHODES, CHRISTOPHER\"}}},\"citationSubject\":{\"subjectIdentification\":{\"identificationID\":\"451450\",\"identificationCategoryCode\":\"O\"},\"roleOfPerson\":{\"personBirthDate\":{\"dateText\":\"1992-01-05\"},\"personName\":{\"personGivenName\":\"CHARLES\",\"personGivenName2\":\"HARRY\",\"personGivenName3\":\"SAMIR\",\"personSurName\":\"HUNTER\"},\"personSexCode\":\"M\"},\"roleOfOrganization\":{\"organizationName\":\"Org\"}},\"citationIssuedLocation\":{\"address\":{\"locationCityName\":\"VICTORIA\"}},\"citationViolation\":{\"activityIdentification\":{\"identificationID\":\"1\"},\"violatedAct\":\"MVA\",\"violatedSection\":\"13\",\"violatedSubSection\":\"1\",\"violatedParagraph\":\"2\",\"violatedSubParagraph\":\"b\",\"violatedClause\":\"xyz\",\"icbcViolatedAct\":\"MVA\",\"icbcCompressedSection\":\"13.1B\",\"disputeType\":\"A\",\"incidentTrafficAccidentInvolvedIndicator\":false},\"citationFineAmount\":{\"amount\":138.0},\"serviceDate\":{\"dateText\":\"2019-01-11\"},\"disputeFilingDate\":{\"dateText\":\"2019-01-11\"},\"citationSubjectMailingAddress\":{\"addressLine1\":\"1288 Flying Scotsman Road\",\"addressLine2\":\"Highlands\",\"addressLine3\":\"ADDR3\",\"locationCityName\":\"VICTORIA\",\"locationCanadianProvinceCode\":\"BC\",\"locationPostalCode\":\"H1H2H3\",\"locationCountryCode\":\"CAN\"},\"citationSubjectDriverLicense\":{\"driverLicenseIdentification\":{\"identificationID\":\"1700202\",\"identificationJurisdiction\":{\"jurisdictionText\":\"BC\"}}},\"vehicle\":{\"itemColorDescriptionText\":\"GREEN\",\"itemOwner\":{\"entityPerson\":{\"personName\":{\"personFullName\":\"MOSS, STERLING\"}}},\"vehicleIdentification\":{\"identificationID\":\"BJV 896\",\"identificationJurisdiction\":{\"jurisdictionText\":\"BC\"}},\"vehicleMakeCode\":\"SD\"},\"courtHearingLocation\":{\"locationIdentification\":{\"identificationID\":\"1201\"},\"address\":{\"addressFullText\":\"VANCOUVER, B.C.\"}},\"citationWitnessingOfficial\":{\"enforcementOfficialBadgeIdentification\":{\"identificationID\":\"ZZ0025\"},\"roleOfPerson\":{\"personName\":{\"personFullName\":\"GUPTA, VIVEK\"}}},\"violationReportDocument\":{\"documentBinary\":{\"base64BinaryObject\":\"PD94bI=\"},\"documentSoftware\":{\"softwareVersionNumberID\":\"1.0\"},\"certificateOfServiceFormNumber\":\"MV6010(102018)\",\"electronicViolationTicketingFormNumber\":\"MV6000E(102018)\"}}";
        String expectedJsonOutput = "{\"ViolationTicketNo\":\"EZ04500019\",\"DisputeFiledDate\":\"2019-01-11\",\"ServiceDate\":\"2019-01-11\",\"ClientTypeCode\":\"O\",\"OrganizationName\":\"Org\",\"DriverMvbClientNo\":\"451450\",\"AddressLine\":\"1288 Flying Scotsman Road\",\"AddressLine2\":\"Highlands\",\"AddressLine3\":\"ADDR3\",\"AddressCity\":\"VICTORIA\",\"AddressJurisdictionCode\":\"BC\",\"AddressCountryCode\":\"CAN\",\"AddressPostalCode\":\"H1H2H3\",\"ViolationDate\":\"2019-01-11\",\"ViolationCity\":\"VICTORIA\",\"CountNumber\":\"1\",\"IcbcAct\":\"MVA\",\"IcbcCountSection\":\"13.1B\",\"CsbAct\":\"MVA\",\"CsbSection\":\"13\",\"CsbSubsection\":\"1\",\"CsbParagraph\":\"2\",\"CsbSubparagraph\":\"b\",\"CsbCountClause\":\"xyz\",\"TicketedAmt\":138.0,\"DisputeType\":\"A\",\"VehicleJuridiction\":\"BC\",\"VehiclePlateNumber\":\"BJV 896\",\"VehicleOwnerNm\":\"MOSS, STERLING\",\"VehicleType\":\"SD\",\"VehicleColour\":\"GREEN\",\"HearingLocation\":\"VANCOUVER, B.C.\",\"HearingLocationCode\":\"1201\",\"EnforcementOfficerPin\":\"9804161\",\"EnforcementOfficerName\":\"RHODES, CHRISTOPHER\",\"EnforcementAgency\":\"VICTORIA CITY MUN\",\"EnforcementAgencyCode\":\"402\",\"AccidentFlag\":false,\"WitnessOfficerPin\":\"ZZ0025\",\"WitnessOfficerName\":\"GUPTA, VIVEK\",\"CosFormNumber\":\"MV6010(102018)\",\"EvtFormNumber\":\"MV6000E(102018)\",\"MreMinorVersion\":\"1.0\",\"TicketXml\":\"PD94bI=\"}";

        CitationAugmentationType citationDispute = JsonUtility.toObject(jsonInput, CitationAugmentationType.class);

        DisputeEntity disputeEntity = TransformationUtility.transform(citationDispute);

        String disputeEntityJson = JsonUtility.toJson(disputeEntity);

        Assertions.assertEquals(expectedJsonOutput, disputeEntityJson);
    }

    @Test
    public void testUpdateCitationDisputeTransformationI() {
        String jsonInput = "{\"activityIdentification\":{\"identificationID\":\"EZ04500019\"},\"citationSubject\":{\"subjectIdentification\":{\"identificationID\":\"451450\",\"identificationCategoryCode\":\"I\"},\"roleOfPerson\":{\"personName\":{\"personGivenName\":\"STARLING\",\"personGivenName2\":\"FRASER\",\"personGivenName3\":\"JACK\",\"personSurName\":\"MOSS\"}},\"roleOfOrganization\":{\"organizationName\":\"Org\"}},\"citationViolation\":{\"activityIdentification\":{\"identificationID\":\"1\"},\"activityStatus\":{\"statusDate\":{\"dateText\":\"2019-01-09\"},\"statusText\":\"PAID\"}},\"citationSubjectDriverLicense\":{\"driverLicenseIdentification\":{\"identificationID\":\"1700222\"}}}";
        String expectedJsonOutput = "{\"ViolationTicketNo\":\"EZ04500019\",\"CountNumber\":\"1\",\"ClientTypeCode\":\"I\",\"ActionType\":\"PAID\",\"ActionDate\":\"2019-01-09\",\"DisputantSurname\":\"MOSS\",\"DisputantGiven1Name\":\"STARLING\",\"DisputantGiven2Name\":\"FRASER\",\"DisputantGiven3Name\":\"JACK\",\"DriverLicenceNo\":\"1700222\",\"DriverMvbClientNo\":\"451450\"}";

        CitationAugmentationType citationDispute = JsonUtility.toObject(jsonInput, CitationAugmentationType.class);

        DisputeStatusEntity disputeStatusEntity = TransformationUtility.transformDisputeStatus(citationDispute);

        String jsonOutput = JsonUtility.toJson(disputeStatusEntity);

        Assertions.assertEquals(expectedJsonOutput, jsonOutput);
    }

    @Test
    public void testUpdateCitationDisputeTransformationO() {
        String jsonInput = "{\"activityIdentification\":{\"identificationID\":\"EZ04500019\"},\"citationSubject\":{\"subjectIdentification\":{\"identificationID\":\"451450\",\"identificationCategoryCode\":\"O\"},\"roleOfPerson\":{\"personName\":{\"personGivenName\":\"STARLING\",\"personGivenName2\":\"FRASER\",\"personGivenName3\":\"JACK\",\"personSurName\":\"MOSS\"}},\"roleOfOrganization\":{\"organizationName\":\"Org\"}},\"citationViolation\":{\"activityIdentification\":{\"identificationID\":\"1\"},\"activityStatus\":{\"statusDate\":{\"dateText\":\"2019-01-09\"},\"statusText\":\"PAID\"}},\"citationSubjectDriverLicense\":{\"driverLicenseIdentification\":{\"identificationID\":\"1700222\"}}}";
        String expectedJsonOutput = "{\"ViolationTicketNo\":\"EZ04500019\",\"CountNumber\":\"1\",\"ClientTypeCode\":\"O\",\"ActionType\":\"PAID\",\"ActionDate\":\"2019-01-09\",\"OrganizationName\":\"Org\",\"DriverMvbClientNo\":\"451450\"}";

        CitationAugmentationType citationDispute = JsonUtility.toObject(jsonInput, CitationAugmentationType.class);

        DisputeStatusEntity disputeStatusEntity = TransformationUtility.transformDisputeStatus(citationDispute);

        String jsonOutput = JsonUtility.toJson(disputeStatusEntity);

        Assertions.assertEquals(expectedJsonOutput, jsonOutput);
    }


}
