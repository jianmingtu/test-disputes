package ca.bc.gov.iamp.justindisputesapi.utils;

import ca.bc.gov.iamp.justindisputesapi.api.model.CitationAugmentationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.CourtResults;
import ca.bc.gov.iamp.justindisputesapi.api.model.DocumentType;
import ca.bc.gov.iamp.justindisputesapi.api.model.DriverLicenseType;
import ca.bc.gov.iamp.justindisputesapi.api.model.IncidentAugmentationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.LocationType;
import ca.bc.gov.iamp.justindisputesapi.api.model.ProcessStatus;
import ca.bc.gov.iamp.justindisputesapi.api.model.SubjectType;
import ca.bc.gov.iamp.justindisputesapi.api.model.VehicleType;
import ca.bc.gov.iamp.justindisputesapi.api.model.Verdicts;
import ca.bc.gov.iamp.justindisputesapi.model.ords.CourtResultsEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.DisputeStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ProcessStatusEntity;
import ca.bc.gov.iamp.justindisputesapi.model.ords.ResultSetEntity;

import java.util.ArrayList;

public final class TransformationUtility {

    private TransformationUtility() {}
    
    public static CourtResults transform(CourtResultsEntity courtResultsEntity) {
        CourtResults courtResults = new CourtResults();
        courtResults.setVerdicts(new ArrayList<>());

        if(courtResultsEntity.getResultSet() != null) {
            for(ResultSetEntity resultSetEntity : courtResultsEntity.getResultSet()) {
                if(resultSetEntity.getEventGuid() != null) {
                    Verdicts verdict = new Verdicts();

                    if(resultSetEntity.getEventGuid() != null) {
                        verdict.setActivityIdentification(resultSetEntity.getEventGuid());
                    }

                    if(resultSetEntity.getApprDate() != null) {
                        verdict.setVerdictDate(resultSetEntity.getApprDate());
                    }

                    if(resultSetEntity.getFindFindingCd() != null) {
                        verdict.setVerdictDispositionName(resultSetEntity.getFindFindingCd());
                    }

                    if(resultSetEntity.getFindFindingDsc() != null) {
                        verdict.setVerdictDescriptionText(resultSetEntity.getFindFindingDsc());
                    }

                    if(resultSetEntity.getContraventionNumber() != null) {
                        verdict.setContraventionNumber(resultSetEntity.getContraventionNumber());
                    }

                    courtResults.getVerdicts().add(verdict);
                }
            }
        }

        courtResults.setItemQuantity(courtResultsEntity.getNumberOfItems());

        return courtResults;
    }

    public static ProcessStatusEntity transform(ProcessStatus processStatus) {
        ProcessStatusEntity processStatusEntity = new ProcessStatusEntity();
        processStatusEntity.setpSuccess(processStatus.getActivityStatus() ? "T" : "F");
        return processStatusEntity;
    }

    public static DisputeEntity transform(CitationAugmentationType citationDispute) {
        DisputeEntity disputeEntity = new DisputeEntity();

        if (citationDispute.getActivityIdentification() != null) {
            disputeEntity.setViolationTicketNo(citationDispute.getActivityIdentification().getIdentificationID());
        }

        if (citationDispute.getDisputeFilingDate() != null) {
            disputeEntity.setDisputeFiledDate(citationDispute.getDisputeFilingDate().getDateText());
        }

        if (citationDispute.getServiceDate() != null) {
            disputeEntity.setServiceDate(citationDispute.getServiceDate().getDateText());
        }

        if (citationDispute.getActivityDate() != null) {
            disputeEntity.setViolationDate(citationDispute.getActivityDate().getDateText());
        }

        if (citationDispute.getCitationFineAmount() != null) {
            disputeEntity.setTicketedAmt(citationDispute.getCitationFineAmount().getAmount());
        }

        transformCitationSubject(citationDispute, disputeEntity);

        transformCitationLocation(citationDispute.getCitationIssuedLocation(), disputeEntity);

        transformCitationViolation(citationDispute.getCitationViolation(), disputeEntity);

        transformVehicle(citationDispute.getVehicle(), disputeEntity);

        transformCourtLocation(citationDispute.getCourtHearingLocation(), disputeEntity);

        transformCitationEnforcement(citationDispute, disputeEntity);

        transformCitationReport(citationDispute.getViolationReportDocument(), disputeEntity);

        return disputeEntity;
    }

    private static void transformCitationSubject(CitationAugmentationType citationDispute, DisputeEntity disputeEntity) {
        SubjectType subject = citationDispute.getCitationSubject();
        if (subject != null) {

            if (subject.getSubjectIdentification() != null) {
                disputeEntity.setClientTypeCode(subject.getSubjectIdentification().getIdentificationCategoryCode());
                disputeEntity.setDriverMvbClientNo(subject.getSubjectIdentification().getIdentificationID());

                if (subject.getRoleOfPerson() != null &&
                        "I".equals(subject.getSubjectIdentification().getIdentificationCategoryCode())) {

                    if (subject.getRoleOfPerson().getPersonName() != null) {
                        disputeEntity.setDriverSurname(subject.getRoleOfPerson().getPersonName().getPersonSurName());
                        disputeEntity.setDriverGiven1Name(subject.getRoleOfPerson().getPersonName().getPersonGivenName());
                        disputeEntity.setDriverGiven2Name(subject.getRoleOfPerson().getPersonName().getPersonGivenName2());
                        disputeEntity.setDriverGiven3Name(subject.getRoleOfPerson().getPersonName().getPersonGivenName3());
                    }

                    if (subject.getRoleOfPerson().getPersonBirthDate() != null) {
                        disputeEntity.setDriverBirthDate(subject.getRoleOfPerson().getPersonBirthDate().getDateText());
                    }
                    disputeEntity.setDriverGender(subject.getRoleOfPerson().getPersonSexCode());

                    transformDriversLicense(citationDispute.getCitationSubjectDriverLicense(), disputeEntity);
                }

                if (subject.getRoleOfOrganization() != null &&
                        "O".equals(subject.getSubjectIdentification().getIdentificationCategoryCode())) {
                    disputeEntity.setOrganizationName(subject.getRoleOfOrganization().getOrganizationName());
                }
            }
        }

        if (citationDispute.getCitationSubjectMailingAddress() != null) {
            disputeEntity.setAddressLine(citationDispute.getCitationSubjectMailingAddress().getAddressLine1());
            disputeEntity.setAddressLine2(citationDispute.getCitationSubjectMailingAddress().getAddressLine2());
            disputeEntity.setAddressLine3(citationDispute.getCitationSubjectMailingAddress().getAddressLine3());
            disputeEntity.setAddressCity(citationDispute.getCitationSubjectMailingAddress().getLocationCityName());
            disputeEntity.setAddressJurisdictionCode(citationDispute.getCitationSubjectMailingAddress().getLocationCanadianProvinceCode());
            disputeEntity.setAddressCountryCode(citationDispute.getCitationSubjectMailingAddress().getLocationCountryCode());
            disputeEntity.setAddressPostalCode(citationDispute.getCitationSubjectMailingAddress().getLocationPostalCode());
        }

    }

    private static void transformCitationLocation(LocationType location, DisputeEntity disputeEntity) {
        if (location != null && location.getAddress() != null) {
            disputeEntity.setViolationCity(location.getAddress().getLocationCityName());
        }
    }

    private static void transformCitationViolation(IncidentAugmentationType violation, DisputeEntity disputeEntity) {
        if (violation != null) {
            if (violation.getActivityIdentification() != null) {
                disputeEntity.setCountNumber(violation.getActivityIdentification().getIdentificationID());
            }
            disputeEntity.setIcbcAct(violation.getIcbcViolatedAct());
            disputeEntity.setIcbcCountSection(violation.getIcbcCompressedSection());

            disputeEntity.setCsbAct(violation.getViolatedAct());
            disputeEntity.setCsbSection(violation.getViolatedSection());
            disputeEntity.setCsbSubsection(violation.getViolatedSubSection());
            disputeEntity.setCsbParagraph(violation.getViolatedParagraph());
            disputeEntity.setCsbSubparagraph(violation.getViolatedSubParagraph());
            disputeEntity.setCsbCountClause(violation.getViolatedClause());

            disputeEntity.setDisputeType(violation.getDisputeType());

            disputeEntity.setAccidentFlag(violation.getIncidentTrafficAccidentInvolvedIndicator());
        }
    }

    private static void transformVehicle(VehicleType vehicle, DisputeEntity disputeEntity) {
        if (vehicle != null) {
            if (vehicle.getVehicleIdentification() != null) {
                disputeEntity.setVehiclePlateNumber(vehicle.getVehicleIdentification().getIdentificationID());
                if (vehicle.getVehicleIdentification().getIdentificationJurisdiction() != null) {
                    disputeEntity.setVehicleJuridiction(vehicle.getVehicleIdentification().getIdentificationJurisdiction().getJurisdictionText());
                }
            }

            if (vehicle.getItemOwner() != null && vehicle.getItemOwner().getEntityPerson() != null
                    && vehicle.getItemOwner().getEntityPerson().getPersonName() != null) {
                disputeEntity.setVehicleOwnerNm(vehicle.getItemOwner().getEntityPerson().getPersonName().getPersonFullName());
            }

            disputeEntity.setVehicleType(vehicle.getVehicleMakeCode());
            disputeEntity.setVehicleColour(vehicle.getItemColorDescriptionText());
        }
    }

    private static void transformCourtLocation(LocationType location, DisputeEntity disputeEntity) {
        if (location != null) {
            if (location.getAddress() != null) {
                disputeEntity.setHearingLocation(location.getAddress().getAddressFullText());
            }

            if (location.getLocationIdentification() != null) {
                disputeEntity.setHearingLocationCode(location.getLocationIdentification().getIdentificationID());
            }
        }
    }

    private static void transformCitationEnforcement(CitationAugmentationType citationDispute, DisputeEntity disputeEntity) {
        if (citationDispute.getCitationIssuingOfficial() != null) {
            if (citationDispute.getCitationIssuingOfficial().getEnforcementOfficialBadgeIdentification() != null) {
                disputeEntity.setEnforcementOfficerPin(citationDispute.getCitationIssuingOfficial().getEnforcementOfficialBadgeIdentification().getIdentificationID());
            }
            if (citationDispute.getCitationIssuingOfficial().getRoleOfPerson() != null && citationDispute.getCitationIssuingOfficial().getRoleOfPerson().getPersonName() != null) {
                disputeEntity.setEnforcementOfficerName(citationDispute.getCitationIssuingOfficial().getRoleOfPerson().getPersonName().getPersonFullName());
            }
        }

        if (citationDispute.getCitationWitnessingOfficial() != null) {
            if (citationDispute.getCitationWitnessingOfficial().getEnforcementOfficialBadgeIdentification() != null) {
                disputeEntity.setWitnessOfficerPin(citationDispute.getCitationWitnessingOfficial().getEnforcementOfficialBadgeIdentification().getIdentificationID());
            }
            if (citationDispute.getCitationWitnessingOfficial().getRoleOfPerson() != null && citationDispute.getCitationWitnessingOfficial().getRoleOfPerson().getPersonName() != null) {
                disputeEntity.setWitnessOfficerName(citationDispute.getCitationWitnessingOfficial().getRoleOfPerson().getPersonName().getPersonFullName());
            }
        }

        if (citationDispute.getCitationAgency() != null) {
            if (citationDispute.getCitationAgency().getOrganizationIdentification() != null) {
                disputeEntity.setEnforcementAgencyCode(citationDispute.getCitationAgency().getOrganizationIdentification().getIdentificationID());
            }
            disputeEntity.setEnforcementAgency(citationDispute.getCitationAgency().getOrganizationName());
        }
    }

    private static void transformCitationReport(DocumentType report, DisputeEntity disputeEntity) {
        if (report != null) {
            if (report.getDocumentBinary() != null &&
                    report.getDocumentBinary().getBase64BinaryObject() != null) {
                disputeEntity.setTicketXml(report.getDocumentBinary().getBase64BinaryObject());
            }

            if (report.getDocumentSoftware() != null) {
                disputeEntity.setMreMinorVersion(report.getDocumentSoftware().getSoftwareVersionNumberID());
            }

            disputeEntity.setCosFormNumber(report.getCertificateOfServiceFormNumber());
            disputeEntity.setEvtFormNumber(report.getElectronicViolationTicketingFormNumber());
        }
    }

    private static void transformDriversLicense(DriverLicenseType license, DisputeEntity disputeEntity) {
        if (license != null) {
            if (license.getDriverLicenseIdentification() != null) {
                disputeEntity.setDriverLicenseNo(license.getDriverLicenseIdentification().getIdentificationID());
                if (license.getDriverLicenseIdentification().getIdentificationJurisdiction() != null) {
                    disputeEntity.setDriverJurisdictionCode(license.getDriverLicenseIdentification().getIdentificationJurisdiction().getJurisdictionText());
                }
            }
        }
    }

    public static DisputeStatusEntity transformDisputeStatus(CitationAugmentationType citationDispute) {
        DisputeStatusEntity disputeStatusEntity = new DisputeStatusEntity();

        if (citationDispute.getActivityIdentification() != null) {
            disputeStatusEntity.setViolationTicketNo(citationDispute.getActivityIdentification().getIdentificationID());
        }

        transformCitationViolation(citationDispute.getCitationViolation(), disputeStatusEntity);

        transformCitationSubject(citationDispute, disputeStatusEntity);

        return disputeStatusEntity;
    }

    private static void transformCitationViolation(IncidentAugmentationType violation, DisputeStatusEntity disputeStatusEntity) {
        if (violation != null) {
            if (violation.getActivityIdentification() != null) {
                disputeStatusEntity.setCountNumber(violation.getActivityIdentification().getIdentificationID());
            }

            if (violation.getActivityStatus() != null) {
                disputeStatusEntity.setActionType(violation.getActivityStatus().getStatusText());

                if (violation.getActivityStatus().getStatusDate() != null) {
                    disputeStatusEntity.setActionDate(violation.getActivityStatus().getStatusDate().getDateText());
                }
            }
        }
    }

    private static void transformCitationSubject(CitationAugmentationType citationDispute, DisputeStatusEntity disputeStatusEntity) {
        SubjectType subject = citationDispute.getCitationSubject();
        if (subject != null) {

            if (subject.getSubjectIdentification() != null) {
                disputeStatusEntity.setClientTypeCode(subject.getSubjectIdentification().getIdentificationCategoryCode());
                disputeStatusEntity.setDriverMvbClientNo(subject.getSubjectIdentification().getIdentificationID());

                if (subject.getRoleOfPerson() != null &&
                        "I".equals(subject.getSubjectIdentification().getIdentificationCategoryCode())) {

                    if (subject.getRoleOfPerson().getPersonName() != null) {
                        disputeStatusEntity.setDisputantSurname(subject.getRoleOfPerson().getPersonName().getPersonSurName());
                        disputeStatusEntity.setDisputantGiven1Name(subject.getRoleOfPerson().getPersonName().getPersonGivenName());
                        disputeStatusEntity.setDisputantGiven2Name(subject.getRoleOfPerson().getPersonName().getPersonGivenName2());
                        disputeStatusEntity.setDisputantGiven3Name(subject.getRoleOfPerson().getPersonName().getPersonGivenName3());
                    }

                    transformDriversLicense(citationDispute.getCitationSubjectDriverLicense(), disputeStatusEntity);
                }

                if (subject.getRoleOfOrganization() != null &&
                        "O".equals(subject.getSubjectIdentification().getIdentificationCategoryCode())) {
                    disputeStatusEntity.setOrganizationName(subject.getRoleOfOrganization().getOrganizationName());
                }
            }
        }
    }

    private static void transformDriversLicense(DriverLicenseType license, DisputeStatusEntity disputeStatusEntity) {
        if (license != null) {
            if (license.getDriverLicenseIdentification() != null) {
                disputeStatusEntity.setDriverLicenseNo(license.getDriverLicenseIdentification().getIdentificationID());
            }
        }
    }
}
