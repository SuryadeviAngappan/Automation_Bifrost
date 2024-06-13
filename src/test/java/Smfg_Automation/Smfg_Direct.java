package Smfg_Automation;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BS_Upload.Upload_BS;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.ReportUtility;
import GenericUtilities.VerificationUtility;
import LoanCode_Generater.Create_New_Loancode;
import Policy.Policies;
import Pract.Doc;
import WorkFlowLibrary.Custom_Token;
import WorkFlowLibrary.DocumentUpload;
import io.restassured.response.ValidatableResponse;

 
@Listeners(Listner.ListnerClass.class)


public class Smfg_Direct{
	

	Policies poli = new Policies();
	Custom_Token token = new Custom_Token();
	DocumentUpload lp = new DocumentUpload();
	Upload_BS bs = new Upload_BS();
	Create_New_Loancode create= new Create_New_Loancode();
	String loancode;
	DataBaseUtility util = new DataBaseUtility();
	String url="https://console-staging.flexiloans.com/policy/check-policies/";
	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";
	
	@BeforeClass
	public void BS() throws Throwable 
	{


	   loancode=create.IP_Qalified();
	   System.out.println("New_Loan_Code    : " +loancode );
		bs.BS_Upload(loancode);
		

	}
	
	@Test
	public void Smfg_Policy() 
	
	{
		
		String query1="UPDATE flexiloans_staging_db.loan_application SET source_of_lead = 'direct' WHERE (code = '"+loancode+"')";
		util.executeUpdateQuery(query1);
		String query2="UPDATE flexiloans_staging_db.loan_application SET partner_code = '600c32152wnm3' WHERE (code = '"+loancode+"')";
		util.executeUpdateQuery(query2);
		String query3="UPDATE flexiloans_staging_db.loan_business_detail SET legal_status = 'proprietorship' WHERE (loan_code = '"+loancode+"')";
		util.executeUpdateQuery(query3);
		String query4="UPDATE flexiloans_staging_db.loan_applicant_detail SET address_ownership_status = 'Owned' WHERE (loan_code = '"+loancode+"')";
		util.executeUpdateQuery(query4);
		String query5="UPDATE flexiloans_staging_db.loan_business_detail SET address_ownership_status = 'Owned' WHERE (loan_code = '"+loancode+"')";
		util.executeUpdateQuery(query5);
		String query6="UPDATE flexiloans_staging_db.loan_business_detail SET year_of_incorporation = '2022',date_of_incorporation = '2022-07-01' WHERE (loan_code = '"+loancode+"')";
		util.executeUpdateQuery(query6);
		String query7="UPDATE flexiloans_staging_db.loan_applicant_detail SET cibil_score = '700' WHERE (loan_code = '"+loancode+"')";
		util.executeUpdateQuery(query7);
		String query8="UPDATE flexiloans_staging_db.loan_applicant_detail SET uid = '6478a947-f6cf-438f-8957-771bb12e0606' WHERE (loan_code = '"+loancode+"')";
		util.executeUpdateQuery(query8);
		String query9="UPDATE flexiloans_staging_db.loan_business_detail SET serviceable_flag = '1' WHERE (loan_code = '"+loancode+"')";
		util.executeUpdateQuery(query9);
		String query10="UPDATE flexiloans_staging_db.loan_business_detail  SET is_rco = '1' WHERE (loan_code = '"+loancode+"')";
		util.executeUpdateQuery(query10);
		String query11="UPDATE flexiloans_staging_db.loan_application_metadata SET is_topup = '0', is_renewal = '1' WHERE (loan_code = '"+loancode+"')";
		util.executeUpdateQuery(query11);
		
		ValidatableResponse policyResponse = given().when().get(url+loancode).then().log().all();
		String policyResponseString = policyResponse.extract().body().asPrettyString();
		System.out.println(policyResponseString);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);
		
		ValidatableResponse eligibilityResponse = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String eligibilityResponseString=eligibilityResponse.extract().body().asPrettyString();
		
		System.out.println(eligibilityResponseString);
	}	
	}
		
		
		
		
		
		
		
		
		
		