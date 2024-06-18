package Bifrost_Automation;

	import static io.restassured.RestAssured.given;

	import java.sql.SQLException;
	import java.util.HashMap;

	import org.junit.Before;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericUtilities.DataBaseUtility;
	import GenericUtilities.VerificationUtility;
	import Listner.ListnerClass;
	import LoanCode_Generater.Create_New_Loancode;
	import io.restassured.response.Validatable;
	import io.restassured.response.ValidatableResponse;
@Listeners(Listner.ListnerClass.class)
public class Bifrost_GetApi {
		VerificationUtility verificationUtil = new VerificationUtility();
		Create_New_Loancode lc = new Create_New_Loancode();
		DataBaseUtility dataBaseUtil =new DataBaseUtility();
		String Bifrost="https://bifrost-dev.flexiloans.com/v1/lead/originator/";
		String Pd="https://console-staging.flexiloans.com/personaldiscussion/pd-details/";
		String loancode;
		String originator_id="1";
		String originator_name="Epimoney";
		String product="Team";
		String partner_name="Epimoney";
		String repayment_frequency="Monthly";
		String employment_type="self_employed";
		 SoftAssert soft = new SoftAssert();
		
		
		
		@BeforeClass
		public void preCondition() throws Throwable 
		{

			DataBaseUtility.connectToDB();
			loancode=lc.IP_Qalified();
			
	}
		@Test
		public void Verify() throws SQLException
		{
			ValidatableResponse bifrostResponse = given().contentType("application/json").pathParam("colender_id", 7).when().get(Bifrost+loancode).then().log().all();
			String biFrostResponse=bifrostResponse.extract().body().asPrettyString();
			System.out.println(biFrostResponse);
			
			ValidatableResponse pdResponse = given().contentType("application/json").when().get(Pd+loancode).then().log().all();
			String PdResponse=pdResponse.extract().body().asPrettyString();
			System.out.println(PdResponse);
		
			
			//*Data*//

			
			String BiFrost_tenure = bifrostResponse.extract().body().jsonPath().getString("data.tenure");
			String PD_proposedTenure = pdResponse.extract().body().jsonPath().getString("data.sanctionInformation.proposedTenure");
			soft.assertEquals(BiFrost_tenure, PD_proposedTenure,"Bifrost Tenure and PD proposed Tenure  Not same");
				
				
				
				
			
			String Bifrost_proposed_roi=bifrostResponse.extract().body().jsonPath().getString("data.proposed_roi");
			String PD_proposedROI=pdResponse.extract().body().jsonPath().getString("data.sanctionInformation.proposedROI");
			soft.assertEquals(Bifrost_proposed_roi, PD_proposedROI,"Bifrost_proposed_roi and PD_proposedROI Not same");


			
			String Bifrost_proposed_emi=bifrostResponse.extract().body().jsonPath().getString("data.proposed_emi");
			String PD_expectedMontlyEMI=pdResponse.extract().body().jsonPath().getString("data.sanctionInformation.expectedMontlyEMI");
			soft.assertEquals(Bifrost_proposed_emi, PD_expectedMontlyEMI,"Bifrost_proposed_emi and PD_expectedMontlyEMI Not same");


			
			String Bifrost_originator_id=bifrostResponse.extract().body().jsonPath().getString("data.originator_id");
			soft.assertEquals(Bifrost_originator_id, originator_id,"Bifrost_originator_id and originator_id hardCode not same");

			
			String Bifrost_originator_name=bifrostResponse.extract().body().jsonPath().getString("data.originator_name");
			soft.assertEquals(Bifrost_originator_name, originator_name,"Bifrost_originator_name and originator_name hardCode not same");

			
			
			
			String Bifrost_originator_loan_code=bifrostResponse.extract().body().jsonPath().getString("data.originator_loan_code");
			String PD_loancode=pdResponse.extract().body().jsonPath().getString("message.data.loancode");
			soft.assertEquals(Bifrost_originator_loan_code, PD_loancode,"Bifrost_originator_loan_code and PD_loancode Not same");
			
			
			String Bifrost_colender_loan_code=bifrostResponse.extract().body().jsonPath().getString("data.colender_loan_code");
			String PD_data_loancode=pdResponse.extract().body().jsonPath().getString("message.data.loancode");
			soft.assertEquals(Bifrost_colender_loan_code, PD_data_loancode,"Bifrost_colender_loan_code and PD_data_loancode Not same");
			
			
			
			String Bifrost_proposed_amount=bifrostResponse.extract().body().jsonPath().getString("data.proposed_amount");
			String PD_proposedAmount=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.proposedAmount");
			soft.assertEquals(Bifrost_proposed_amount, PD_proposedAmount,"Bifrost_proposed_amount and PD_proposedAmount Not same");
			
			String Bifrost_alternate_colender_code=bifrostResponse.extract().body().jsonPath().getString("data.alternate_colender_code");
			String PD_loanCode=pdResponse.extract().body().jsonPath().getString("message.data.loanCode");
			soft.assertEquals(Bifrost_alternate_colender_code, PD_loanCode,"Bifrost_alternate_colender_code and PD_loanCode Not same");
			
			
			String Bifrost_product=bifrostResponse.extract().body().jsonPath().getString("data.product");
			soft.assertEquals(Bifrost_product, product,"Bifrost_product and product hard code Not same");

			
			
			String Bifrost_application_number=bifrostResponse.extract().body().jsonPath().getString("data.application_number");
		
			
			
			String Bifrost_partner_name=bifrostResponse.extract().body().jsonPath().getString("data.partner_name");
			soft.assertEquals(Bifrost_partner_name, partner_name,"Bifrost_partner_name and partner_name hard code Not same");
		
			String Bifrost_partner_type=bifrostResponse.extract().body().jsonPath().getString("data.partner_type");
			
			
			
			String Bifrost_escrow_available=bifrostResponse.extract().body().jsonPath().getString("data.escrow_available");
			String PD_isEscrow=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.isEscrow");
			soft.assertEquals(Bifrost_escrow_available, PD_isEscrow,"Bifrost_escrow_available and PD_isEscrow Not same");
			
			
			String Bifrost_credit_manager_name=bifrostResponse.extract().body().jsonPath().getString("data.credit_manager_name");
			
			
			String Bifrost_case_strengths=bifrostResponse.extract().body().jsonPath().getString("data.case_strengths");
			String Bifrost_case_concerns=bifrostResponse.extract().body().jsonPath().getString("data.case_concerns");
			
			String Bifrost_sanction_conditions=bifrostResponse.extract().body().jsonPath().getString("data.sanction_conditions");
			String PD_sanctionConditions=pdResponse.extract().body().jsonPath().getString("message.data.caseSummary.sanctionConditions");
			soft.assertEquals(Bifrost_sanction_conditions, PD_sanctionConditions,"Bifrost_sanction_conditions and PD_sanctionConditions Not same");
			
			
			String Bifrost_deviation=bifrostResponse.extract().body().jsonPath().getString("data.deviation");
			String PD_otherRemarks=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.otherRemarks");
			soft.assertEquals(Bifrost_deviation, PD_otherRemarks,"Bifrost_deviation and PD_otherRemarks Not same");
			
			
			String Bifrost_proposed_approval_date=bifrostResponse.extract().body().jsonPath().getString("data.proposed_approval_date");
			String PD_approvalDate=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.approvalDate");
			soft.assertEquals(Bifrost_proposed_approval_date, PD_approvalDate,"Bifrost_proposed_approval_date and PD_approvalDate Not same");
			
			
			String Bifrost_tentative_disbursal_date=bifrostResponse.extract().body().jsonPath().getString("data.tentative_disbursal_date");
			String PD_disbursalDate=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.disbursalDate");
			soft.assertEquals(Bifrost_tentative_disbursal_date, PD_disbursalDate,"Bifrost_tentative_disbursal_date and PD_disbursalDate Not same");

			String Bifrost_proposed_emi_start_date=bifrostResponse.extract().body().jsonPath().getString("data.proposed_emi_start_date");
			String PD_EMIStartDate=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.EMIStartDate");
			soft.assertEquals(Bifrost_proposed_emi_start_date, PD_EMIStartDate,"Bifrost_proposed_emi_start_date and PD_EMIStartDate Not same");
			
			
			String Bifrost_proposed_interest_start_date=bifrostResponse.extract().body().jsonPath().getString("data.proposed_interest_start_date");
			String PD_interestedStartDate=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.interestedStartDate");
			soft.assertEquals(Bifrost_proposed_interest_start_date, PD_interestedStartDate,"Bifrost_proposed_interest_start_date and PD_interestedStartDate Not same");
			
			
			String Bifrost_annual_reducing_rate=bifrostResponse.extract().body().jsonPath().getString("data.annual_reducing_rate");
			String PD_reducingRate=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.reducingRate");
			soft.assertEquals(Bifrost_annual_reducing_rate, PD_reducingRate,"Bifrost_proposed_interest_start_date and PD_reducingRate Not same");
			
			
			String Bifrost_interest_type=bifrostResponse.extract().body().jsonPath().getString("data.interest_type");
			String PD_interestType=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.interestType");
			soft.assertEquals(Bifrost_interest_type, PD_interestType,"Bifrost_interest_type and PD_interestType Not same");
			
			String Bifrost_total_processing_fee=bifrostResponse.extract().body().jsonPath().getString("data.total_processing_fee");
			String PD_processingFee=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.processingFee");
			soft.assertEquals(Bifrost_total_processing_fee, PD_processingFee,"Bifrost_total_processing_fee and PD_processingFee Not same");
			
			
			String Bifrost_processing_fee_percentage=bifrostResponse.extract().body().jsonPath().getString("data.processing_fee_percentage");
			String PD_processingPercentage=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.processingPercentage");
			soft.assertEquals(Bifrost_total_processing_fee, PD_processingPercentage,"Bifrost_total_processing_fee and PD_processingPercentage Not same");
			
			
			String Bifrost_stamp_duty_fee=bifrostResponse.extract().body().jsonPath().getString("data.stamp_duty_fee");
			String PD_stampDutyFee=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.stampDutyFee");
			soft.assertEquals(Bifrost_stamp_duty_fee, PD_stampDutyFee,"Bifrost_stamp_duty_fee and PD_stampDutyFee Not same");
			
			
			String Bifrost_stamp_duty_percentage=bifrostResponse.extract().body().jsonPath().getString("data.stamp_duty_percentage");
			String PD_stampDutyPercentage=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.stampDutyPercentage");
			soft.assertEquals(Bifrost_stamp_duty_percentage, PD_stampDutyPercentage,"Bifrost_stamp_duty_percentage and PD_stampDutyPercentage Not same");
			
			String Bifrost_insurance_premium_percentage=bifrostResponse.extract().body().jsonPath().getString("data.insurance_premium_percentage");
			String PD_insuranceApplied=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.insuranceApplied");
			soft.assertEquals(Bifrost_insurance_premium_percentage, PD_insuranceApplied,"Bifrost_insurance_premium_percentage and PD_insuranceApplied Not same");
			
			
			
			String Bifrost_documentation_fee=bifrostResponse.extract().body().jsonPath().getString("data.documentation_fee");
			String PD_docCharge=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.docCharge");
			soft.assertEquals(Bifrost_documentation_fee, PD_docCharge,"Bifrost_documentation_fee and PD_docCharge Not same");
			
			
			String Bifrost_differential_interest=bifrostResponse.extract().body().jsonPath().getString("data.differential_interest");
			String PD_differentialInterest=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.differentialInterest");
			soft.assertEquals(Bifrost_differential_interest, PD_differentialInterest,"Bifrost_differential_interest and PD_differentialInterest Not same");
			
			
			String Bifrost_total_charges=bifrostResponse.extract().body().jsonPath().getString("data.total_charges");
			String PD_totalCharges=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.totalCharges");
			soft.assertEquals(Bifrost_total_charges, PD_totalCharges,"Bifrost_total_charges and PD_totalCharges Not same");
			
			
			String Bifrost_net_disbursement_amount=bifrostResponse.extract().body().jsonPath().getString("data.net_disbursement_amount");
			String PD_netDisbursalAmount=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.netDisbursalAmount");
			soft.assertEquals(Bifrost_net_disbursement_amount, PD_netDisbursalAmount,"Bifrost_net_disbursement_amount and PD_netDisbursalAmount Not same");
			
			String Bifrost_repayment_frequency=bifrostResponse.extract().body().jsonPath().getString("data.repayment_frequency");
			soft.assertEquals(Bifrost_repayment_frequency, repayment_frequency,"Bifrost_repayment_frequency and repayment_frequency hard code Not same");

			
			
			String Bifrost_pos_split_amount=bifrostResponse.extract().body().jsonPath().getString("data.pos_split_amount");
			String PD_posSplitAmount=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.posSplitAmount");
			soft.assertEquals(Bifrost_pos_split_amount, PD_posSplitAmount,"Bifrost_pos_split_amount and PD_posSplitAmount Not same");
			
			
			String Bifrost_pos_split_percentage=bifrostResponse.extract().body().jsonPath().getString("data.pos_split_percentage");
			String PD_posSplitPercentage=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.posSplitPercentage");
			soft.assertEquals(Bifrost_pos_split_amount, PD_posSplitPercentage,"Bifrost_pos_split_percentage and PD_posSplitPercentage Not same");
			
			
			String Bifrost_other_charges=bifrostResponse.extract().body().jsonPath().getString("data.other_charges");
			
			String Bifrost_life_insurance_fee=bifrostResponse.extract().body().jsonPath().getString("data.life_insurance_fee");
			String PD_lifeInsuranceAmount=pdResponse.extract().body().jsonPath().getString("message.data.sanctionInformation.lifeInsurance.lifeInsuranceAmount");
			soft.assertEquals(Bifrost_life_insurance_fee, PD_lifeInsuranceAmount,"Bifrost_life_insurance_fee and PD_lifeInsuranceAmount Not same");
			
			
			String Bifrost_health_insurance_fee=bifrostResponse.extract().body().jsonPath().getString("data.health_insurance_fee");
			
			String Bifrost_chr_fee=bifrostResponse.extract().body().jsonPath().getString("data.chr_fee");
			
			
			//*company_details*//

			String Bifrost_business_name=bifrostResponse.extract().body().jsonPath().getString("data.company_details.business_name");
			String PD_businessName=pdResponse.extract().body().jsonPath().getString("message.data.business.businessName");
			soft.assertEquals(Bifrost_business_name, PD_businessName,"Bifrost_business_name and PD_businessName Not same");
			
			String Bifrost_employee_count=bifrostResponse.extract().body().jsonPath().getString("data.company_details.employee_count");
			String PD_noEmployee=pdResponse.extract().body().jsonPath().getString("message.data.business.noEmployee");
			soft.assertEquals(Bifrost_employee_count, PD_noEmployee,"Bifrost_employee_count and PD_noEmployee Not same");
			
			String Bifrost_business_pan=bifrostResponse.extract().body().jsonPath().getString("data.company_details.business_pan");
			String PD_businessPan=pdResponse.extract().body().jsonPath().getString("message.data.business.businessPan");
			soft.assertEquals(Bifrost_business_pan, PD_businessPan,"Bifrost_business_pan and PD_businessPan Not same");
			
			
			String Bifrost_full_address=bifrostResponse.extract().body().jsonPath().getString("data.company_details.full_address");
			String PD_businessAddress=pdResponse.extract().body().jsonPath().getString("message.data.business.businessAddress");
			soft.assertEquals(Bifrost_full_address, PD_businessAddress,"Bifrost_full_address and PD_businessAddress Not same");
			
			
			String Bifrost_address_pin_code=bifrostResponse.extract().body().jsonPath().getString("data.company_details.address_pin_code");
			String PD_pincode=pdResponse.extract().body().jsonPath().getString("message.data.business.pincode");
			soft.assertEquals(Bifrost_address_pin_code, PD_pincode,"Bifrost_address_pin_code and PD_pincode Not same");
			
			String Bifrost_date_of_incorporation=bifrostResponse.extract().body().jsonPath().getString("data.company_details.date_of_incorporation");
			
			
			String Bifrost_gst_no=bifrostResponse.extract().body().jsonPath().getString("data.company_details.gst_no");
			String PD_gstin=pdResponse.extract().body().jsonPath().getString("message.data.business.gstin");
			soft.assertEquals(Bifrost_gst_no, PD_gstin,"Bifrost_gst_no and PD_gstin Not same");
			
			String Bifrost_loan_product_code=bifrostResponse.extract().body().jsonPath().getString("data.company_details.loan_product_code");
			
			
			String Bifrost_industry_type=bifrostResponse.extract().body().jsonPath().getString("data.company_details.industry_type");
			String Bifrost_business_category=bifrostResponse.extract().body().jsonPath().getString("data.company_details.business_category");
			
			String Bifrost_address_ownership_status=bifrostResponse.extract().body().jsonPath().getString("data.company_details.address_ownership_status");
			
			String Bifrost_business_type=bifrostResponse.extract().body().jsonPath().getString("data.company_details.business_type");
			String PD_nature_of_business=pdResponse.extract().body().jsonPath().getString("message.data.business.industryListDetails.nature_of_business");
			soft.assertEquals(Bifrost_business_type, PD_nature_of_business,"Bifrost_business_type and PD_nature_of_business Not same");
			
			
			String Bifrost_legal_status=bifrostResponse.extract().body().jsonPath().getString("data.company_details.legal_status");
			String PD_legalStatus=pdResponse.extract().body().jsonPath().getString("message.data.business.legalStatus");
			soft.assertEquals(Bifrost_legal_status, PD_legalStatus,"Bifrost_legal_status and PD_legalStatus Not same");
			
			String Bifrost_address_state=bifrostResponse.extract().body().jsonPath().getString("data.company_details.address_state");
			String PD_state=pdResponse.extract().body().jsonPath().getString("message.data.business.state");
			soft.assertEquals(Bifrost_address_state, PD_state,"Bifrost_address_state and PD_state Not same");
			
			
			String Bifrost_address_city=bifrostResponse.extract().body().jsonPath().getString("data.company_details.address_city");
			String PD_city=pdResponse.extract().body().jsonPath().getString("message.data.business.city");
			soft.assertEquals(Bifrost_address_city, PD_city,"Bifrost_address_city and PD_city Not same");
			
			
			String Bifrost_premise_vintage=bifrostResponse.extract().body().jsonPath().getString("data.company_details.premise_vintage");
			String PD_vintage=pdResponse.extract().body().jsonPath().getString("message.data.business.vintage");
			soft.assertEquals(Bifrost_premise_vintage, PD_vintage,"Bifrost_premise_vintage and PD_vintage Not same");
			
			String Bifrost_relationship_with_owner=bifrostResponse.extract().body().jsonPath().getString("data.company_details.relationship_with_owner");
			String PD_premisesRelationshipType=pdResponse.extract().body().jsonPath().getString("message.data.business.premisesRelationshipType");
			soft.assertEquals(Bifrost_relationship_with_owner, PD_premisesRelationshipType,"Bifrost_relationship_with_owner and PD_premisesRelationshipType Not same");
			
			
			String Bifrost_cin_no=bifrostResponse.extract().body().jsonPath().getString("data.company_details.cin_no");
			String PD_cin=pdResponse.extract().body().jsonPath().getString("message.data.business.cin");
			soft.assertEquals(Bifrost_cin_no, PD_cin,"Bifrost_cin_no and PD_cin Not same");
			
			
		   String Bifrost_banking_monthly_bto=bifrostResponse.extract().body().jsonPath().getString("data.company_details.banking_monthly_bto");
			
			
		    String Bifrost_eligibility_median_bto=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_median_bto");
			String median_bto_Query ="select median_bto from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_median_bto,dataBaseUtil.ExecuteQuery(median_bto_Query),"median_bto not same");
			
			String Bifrost_eligibility_industry_margin=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_industry_margin");
			String eligibility_industry_margin ="select eligibility_industry_margin from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_industry_margin,dataBaseUtil.ExecuteQuery(eligibility_industry_margin),"eligibility_industry_margin not same");
			
		    String Bifrost_eligibility_net_income=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_net_income");
			String bto_net_income ="select bto_net_income from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_net_income,dataBaseUtil.ExecuteQuery(bto_net_income),"Bifrost_eligibility_net_income not same");
			
			String Bifrost_eligibility_foir=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_foir");
			String foir ="select foir from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_foir,dataBaseUtil.ExecuteQuery(foir),"Bifrost_eligibility_foir not same");
			
			
			String Bifrost_eligibility_roi=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_roi");
			String bto_roi ="select bto_roi from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_roi,dataBaseUtil.ExecuteQuery(bto_roi),"Bifrost_eligibility_roi not same");
			
			
			String Bifrost_eligibility_tenure=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_tenure");
			String customer_selected_tenure ="select customer_selected_tenure from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_tenure,dataBaseUtil.ExecuteQuery(customer_selected_tenure),"Bifrost_eligibility_tenure not same");
			
			String Bifrost_eligibility_abb_foir=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_abb_foir");
			String abb_foir ="select abb_foir from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_abb_foir,dataBaseUtil.ExecuteQuery(abb_foir),"Bifrost_eligibility_abb_foir not same");
	
	
			String Bifrost_eligibility_proposed_serviceable_emi=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_proposed_serviceable_emi");
			String bto_serviceable_emi ="select bto_serviceable_emi from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_proposed_serviceable_emi,dataBaseUtil.ExecuteQuery(bto_serviceable_emi),"Bifrost_eligibility_proposed_serviceable_emi not same");
			
			
		    String Bifrost_eligibility_abb_roi=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_abb_roi");
		    String abb_roi ="select abb_roi from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_abb_roi,dataBaseUtil.ExecuteQuery(abb_roi),"Bifrost_eligibility_abb_roi not same");
		    
		    
		    
			String Bifrost_abb_based_eligibility=bifrostResponse.extract().body().jsonPath().getString("data.company_details.abb_based_eligibility");
			String abb_customer_based_eligibility ="select abb_customer_based_eligibility from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_abb_based_eligibility,dataBaseUtil.ExecuteQuery(abb_customer_based_eligibility),"Bifrost_abb_based_eligibility not same");
			
			
			
			String Bifrost_eligibility_pos_multiplier=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_pos_multiplier");
			String pos_multiplier ="select pos_multiplier from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_pos_multiplier,dataBaseUtil.ExecuteQuery(pos_multiplier),"Bifrost_eligibility_pos_multiplier not same");
			
			String Bifrost_pos_loan_eligibility=bifrostResponse.extract().body().jsonPath().getString("data.company_details.pos_loan_eligibility");
			String industry_margin ="select industry_margin from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_pos_loan_eligibility,dataBaseUtil.ExecuteQuery(industry_margin),"Bifrost_pos_loan_eligibility not same");
			
			
			String Bifrost_eligibility_ecom_payout_sales=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_ecom_payout_sales");
			String pd_industry_margin ="select industry_margin from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_ecom_payout_sales,dataBaseUtil.ExecuteQuery(pd_industry_margin),"Bifrost_eligibility_ecom_payout_sales not same");
			
			
			String Bifrost_eligibility_ecom_payout_multiplier=bifrostResponse.extract().body().jsonPath().getString("data.company_details.eligibility_ecom_payout_multiplier");
			String pos_payout_multiplier ="select pos_payout_multiplier from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_eligibility_ecom_payout_multiplier,dataBaseUtil.ExecuteQuery(pos_payout_multiplier),"Bifrost_eligibility_ecom_payout_multiplier not same");
			
			
			String Bifrost_ecom_payout_loan_eligibility=bifrostResponse.extract().body().jsonPath().getString("data.company_details.ecom_payout_loan_eligibility");
			String ecom_payout_eligibility ="select ecom_payout_eligibility from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_ecom_payout_loan_eligibility,dataBaseUtil.ExecuteQuery(ecom_payout_eligibility),"Bifrost_ecom_payout_loan_eligibility not same");
			
		String Bifrost_business_industry_margin=bifrostResponse.extract().body().jsonPath().getString("data.company_details.business_industry_margin");
		
		
		String Bifrost_selling_product=bifrostResponse.extract().body().jsonPath().getString("data.company_details.selling_product");
		
		
		String Bifrost_median_bto_calculation=bifrostResponse.extract().body().jsonPath().getString("data.company_details.median_bto_calculation");
		String median_bto ="select median_bto from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
		soft.assertEquals(Bifrost_median_bto_calculation,dataBaseUtil.ExecuteQuery(median_bto),"Bifrost_median_bto_calculation not same");
		
		String Bifrost_foir_calculation=bifrostResponse.extract().body().jsonPath().getString("data.company_details.foir_calculation");
		String Pd_foir ="select foir from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
		soft.assertEquals(Bifrost_foir_calculation,dataBaseUtil.ExecuteQuery(Pd_foir),"Bifrost_foir_calculation not same");
		
		String Bifrost_existing_emi_calculation=bifrostResponse.extract().body().jsonPath().getString("data.company_details.existing_emi_calculation");
		String existing_emi ="select existing_emi from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
		soft.assertEquals(Bifrost_existing_emi_calculation,dataBaseUtil.ExecuteQuery(existing_emi),"Bifrost_existing_emi_calculation not same");
		

		String Bifrost_ecom_multiplier=bifrostResponse.extract().body().jsonPath().getString("data.company_details.ecom_multiplier");
		String ecom_multiplier ="select ecom_multiplier from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
		soft.assertEquals(Bifrost_ecom_multiplier,dataBaseUtil.ExecuteQuery(ecom_multiplier),"Bifrost_ecom_multiplier not same");
		
			String Bifrost_capped_abb_eligibility=bifrostResponse.extract().body().jsonPath().getString("data.company_details.capped_abb_eligibility");
			String capped_abb_eligibility ="select capped_abb_eligibility from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_capped_abb_eligibility,dataBaseUtil.ExecuteQuery(capped_abb_eligibility),"Bifrost_capped_abb_eligibility not same");
			
			
			String Bifrost_capped_banking_eligibility=bifrostResponse.extract().body().jsonPath().getString("data.company_details.capped_banking_eligibility");
			String capped_banking_eligibility ="select capped_banking_eligibility from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_capped_banking_eligibility,dataBaseUtil.ExecuteQuery(capped_banking_eligibility),"Bifrost_capped_banking_eligibility not same");
			
		String Bifrost_capped_ecom_multiplier_eligibility=bifrostResponse.extract().body().jsonPath().getString("data.company_details.capped_ecom_multiplier_eligibility");
		String capped_ecom_multiplier_eligibility ="select capped_ecom_multiplier_eligibility from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
		soft.assertEquals(Bifrost_capped_ecom_multiplier_eligibility,dataBaseUtil.ExecuteQuery(capped_ecom_multiplier_eligibility),"Bifrost_capped_ecom_multiplier_eligibility not same");
		
			String Bifrost_capped_ecom_payout_eligibility=bifrostResponse.extract().body().jsonPath().getString("data.company_details.capped_ecom_payout_eligibility");
			String capped_ecom_payout_eligibility ="select capped_ecom_payout_eligibility from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_capped_ecom_payout_eligibility,dataBaseUtil.ExecuteQuery(capped_ecom_payout_eligibility),"Bifrost_capped_ecom_payout_eligibility not same");
			
			
			String Bifrost_incorporation_date=bifrostResponse.extract().body().jsonPath().getString("data.company_details.incorporation_date");
			String date_of_incorporation ="select date_of_incorporation from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_incorporation_date,dataBaseUtil.ExecuteQuery(date_of_incorporation),"Bifrost_incorporation_date not same");
			
			
			String Bifrost_principal_outstanding=bifrostResponse.extract().body().jsonPath().getString("data.company_details.principal_outstanding");
			String principal_outstanding ="select principal_outstanding from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_principal_outstanding,dataBaseUtil.ExecuteQuery(principal_outstanding),"Bifrost_principal_outstanding not same");
			
			//*applicant_detail*//
			

		
			String Bifrost_code=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.code");
			String PD_code=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.code");
			soft.assertEquals(Bifrost_code, PD_code,"Bifrost_code and PD_code Not same");
			
			String Bifrost_first_name=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.first_name");
			String PD_name=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.name");
			soft.assertEquals(Bifrost_first_name, PD_name,"Bifrost_first_name and PD_name Not same");
			
			String Bifrost_email=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.email");
			String PD_email=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.email");
			soft.assertEquals(Bifrost_email, PD_email,"Bifrost_email and PD_email Not same");
			
			String Bifrost_mobile_no=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.mobile_no");
			String PD_mobileNo=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.mobileNo");
			soft.assertEquals(Bifrost_mobile_no, PD_mobileNo,"Bifrost_mobile_no and PD_mobileNo Not same");
			
			String Bifrost_whatsapp_no=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.whatsapp_no");
			String whatsapp_no ="select whatsapp_no from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_whatsapp_no,dataBaseUtil.ExecuteQuery(whatsapp_no),"Bifrost_whatsapp_no not same");
			
			String Bifrost_dob=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.dob");
			String PD_dob=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.dob");
			soft.assertEquals(Bifrost_dob, PD_dob,"Bifrost_dob and PD_dob Not same");
			
			String Bifrost_gender=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.gender");
			String PD_gender=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.gender");
			soft.assertEquals(Bifrost_gender, PD_gender,"Bifrost_gender and PD_gender Not same");
			
			String Bifrost_pan=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.pan");
			String PD_pan=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.pan");
			soft.assertEquals(Bifrost_pan, PD_pan,"Bifrost_pan and PD_pan Not same");
			
			String Bifrost_employment_type=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.employment_type");
			soft.assertEquals(Bifrost_employment_type, employment_type,"Bifrost_employment_type and employment_type Not same");

			
			String Bifrost_applicant_education_leve=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.education_level");
			String PD_personalDetails_education=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.education");
			soft.assertEquals(Bifrost_applicant_education_leve, PD_personalDetails_education,"Bifrost_applicant_education_leve and PD_personalDetails_education Not same");
			
			String Bifrost_marital_status=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.marital_status");
			String PD_maritalStatus=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.maritalStatus");
			soft.assertEquals(Bifrost_marital_status, PD_maritalStatus,"Bifrost_marital_status and PD_maritalStatus Not same");
	
			String Bifrost_no_of_dependents=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.no_of_dependents");
			String PD_dependent=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.dependent");
			soft.assertEquals(Bifrost_no_of_dependents, PD_dependent,"Bifrost_no_of_dependents and PD_dependent Not same");

			String Bifrost_is_spouse_employed=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.is_spouse_employed");
			String is_spouse_employed ="select is_spouse_employed from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_is_spouse_employed,dataBaseUtil.ExecuteQuery(is_spouse_employed),"Bifrost_is_spouse_employed not same");
			
			String Bifrost_spouse_annual_income=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.spouse_annual_income");
			String spouse_annual_income ="select spouse_annual_income from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_spouse_annual_income,dataBaseUtil.ExecuteQuery(spouse_annual_income),"Bifrost_spouse_annual_income not same");
			
			String Bifrost_applicant_address_pin_code=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.address_pin_code");
			String PD_personalDetails_pincode=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.pincode");
			soft.assertEquals(Bifrost_applicant_address_pin_code, PD_personalDetails_pincode,"Bifrost_applicant_address_pin_code and PD_personalDetails_pincode Not same");
			
			String Bifrost_applicant_address_city=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.address_city");
			String PD_personal_city=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.city");
			soft.assertEquals(Bifrost_applicant_address_city, PD_personal_city,"Bifrost_applicant_address_city and PD_personal_city Not same");
			
			
			String Bifrost_applicant_address_state=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.address_state");
			String PD_personal_state=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.state");
			soft.assertEquals(Bifrost_applicant_address_state, PD_state,"Bifrost_applicant_address_state and PD_state Not same");
			
			String Bifrost_applicant_address_ownership_status=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.address_ownership_status");
			String PD_ownership=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.ownership");
			soft.assertEquals(Bifrost_applicant_address_ownership_status, PD_ownership,"Bifrost_applicant_address_ownership_status and PD_ownership Not same");
			
			String Bifrost_cibil_score=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.cibil_score");
			String PD_cibilScore=pdResponse.extract().body().jsonPath().getString("message.data.personalDetails.cibilScore");
			soft.assertEquals(Bifrost_cibil_score, PD_cibilScore,"Bifrost_cibil_score and PD_cibilScore Not same");

			String Bifrost_pan_no_verified=bifrostResponse.extract().body().jsonPath().getString("data.applicant_detail.pan_no_verified");
			String is_pan_verified ="select is_pan_verified from flexiloans_staging_db.em_loan_eligibility_log where loan_code ='"+loancode+"'";
			soft.assertEquals(Bifrost_pan_no_verified,dataBaseUtil.ExecuteQuery(is_pan_verified),"Bifrost_pan_no_verified not same");
			

			//*guarantor_details*//

			String Bifrost_guarantor_code=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.code");
			String PD_gaurantor_code=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.code");
			soft.assertEquals(Bifrost_guarantor_code, PD_gaurantor_code,"Bifrost_guarantor_code and PD_gaurantor_code Not same");
			
			
			String Bifrost_guarantor_first_name=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.first_name");
			String PD_gaurantor_name=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.name");
			soft.assertEquals(Bifrost_guarantor_first_name, PD_gaurantor_name,"Bifrost_guarantor_first_name and PD_gaurantor_name Not same");
			
			String Bifrost_guarantor_email=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.email");
			String PD_gaurantor_email=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.email");
			soft.assertEquals(Bifrost_guarantor_email, PD_gaurantor_email,"Bifrost_guarantor_email and PD_gaurantor_email Not same");
			
			String Bifrost_guarantor_mobile_no=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.mobile_no");
			String PD_gaurantor_mobileNo=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.mobileNo");
			soft.assertEquals(Bifrost_guarantor_mobile_no, PD_gaurantor_mobileNo,"Bifrost_guarantor_mobile_no and PD_gaurantor_mobileNo Not same");
			
			String Bifrost_guarantor_dob=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.dob");
			String PD_gaurantor_dob=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.dob");
			soft.assertEquals(Bifrost_guarantor_dob, PD_gaurantor_dob,"Bifrost_guarantor_dob and PD_gaurantor_dob Not same");
			
			String Bifrost_guarantor_gender=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.gender");
			String PD_gaurantor_gender=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.gender");
			soft.assertEquals(Bifrost_guarantor_gender, PD_gaurantor_gender,"Bifrost_guarantor_gender and PD_gaurantor_gender Not same");
			
			String Bifrost_guarantor_pan=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.pan");
			String PD_gaurantor_pan=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.pan");
			soft.assertEquals(Bifrost_guarantor_pan, PD_gaurantor_pan,"Bifrost_guarantor_pan and PD_gaurantor_pan Not same");
			
			String Bifrost_guarantor_education_level=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.education_level");
			String PD_education=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.education");
			soft.assertEquals(Bifrost_guarantor_education_level, PD_education,"Bifrost_guarantor_education_level and PD_education Not same");
			
			
			String Bifrost_guarantor_marital_status=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.marital_status");
			String PD_gaurantor_maritalStatus=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.maritalStatus");
			soft.assertEquals(Bifrost_guarantor_marital_status, PD_gaurantor_maritalStatus,"Bifrost_guarantor_marital_status and PD_gaurantor_maritalStatus Not same");
			
			String Bifrost_guarantor_cibil_score=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.cibil_score");
			String PD_gaurantor_cibilScore=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.cibilScore");
			soft.assertEquals(Bifrost_guarantor_cibil_score, PD_gaurantor_cibilScore,"Bifrost_guarantor_cibil_score and PD_gaurantor_cibilScore Not same");
			
			String Bifrost_guarantor_fathers_name=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.fathers_name");
			
			String Bifrost_guarantor_address_city=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.address_city");
			String PD_gaurantor_city=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.city");
			soft.assertEquals(Bifrost_guarantor_address_city, PD_gaurantor_city,"Bifrost_guarantor_address_city and PD_gaurantor_city Not same");
			
			String Bifrost_guarantor_address_state=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.address_address_state");
			String PD_gaurantor_state=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.state");
			soft.assertEquals(Bifrost_guarantor_address_state, PD_gaurantor_state,"Bifrost_guarantor_address_state and PD_gaurantor_state Not same");
			
			
			String Bifrost_guarantor_kyc_id=bifrostResponse.extract().body().jsonPath().getString("data.guarantor_details.kyc_id");
			String PD_kycID=pdResponse.extract().body().jsonPath().getString("message.data.gaurantor.gaurantorInfo.kycID");
			soft.assertEquals(Bifrost_guarantor_kyc_id, PD_kycID,"Bifrost_guarantor_kyc_id and PD_kycID Not same");
			

			}
			
		}
			
			

			
			


