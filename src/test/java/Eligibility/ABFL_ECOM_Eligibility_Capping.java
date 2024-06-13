package Eligibility;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BS_Upload.Upload_BS;
import GenericUtilities.DataBaseUtility;
import LoanCode_Generater.Create_New_Loancode;
import Policy.Policies;
import io.restassured.response.ValidatableResponse;

public class ABFL_ECOM_Eligibility_Capping {
	
	
	
	String url="https://console-staging.flexiloans.com/policy/check-policies/";
	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";
	Create_New_Loancode lc = new Create_New_Loancode();
	Upload_BS bs = new Upload_BS();
	DataBaseUtility dbUtil = new DataBaseUtility();
	Policies policy = new Policies();
	String loancode;
	String query;

	
	public void ABFL_ECOM(String loancode) throws SQLException  // Done
	{


		String query1= "UPDATE `loan_application_metadata` SET `is_topup` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2= "UPDATE `loan_application` SET `partner_code` = 'f2b0e8972c476' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3 ="UPDATE `loan_business_detail` SET `year_of_incorporation` = '2021' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4 ="UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}

	@BeforeClass
	public void db() throws Throwable 
	{
		dbUtil.connectToDB();
		loancode=lc.IP_Qalified();
		bs.BS_Upload(loancode);
		ABFL_ECOM_Eligibility_Capping abfl_ECOM_Eligibility_Capping = new ABFL_ECOM_Eligibility_Capping();
		abfl_ECOM_Eligibility_Capping.ABFL_ECOM(loancode);
		System.out.println(loancode);
		Thread.sleep(40000);

	}
	
	
	
	@Test
	public void RG_A() throws InterruptedException 
	{

		query="update risk_grading_final set final_grade='A' where loan_code='"+loancode+"'";
		try{dbUtil.executeUpdateQuery(query);}
		catch(Exception e) {e.printStackTrace();}


		String payout_data="update flexiloans_staging_db.loan_merchant_performance_detail set loan_code='"+loancode+"' where id IN (2590,2591,2592,2593,2594,2595)";
		dbUtil.executeUpdateQuery(payout_data);
		
		
		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_ECOM[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		if(capping <= 1500000) 
		{
			System.out.println(capping + "  Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}
	
	
	
	
	
	
	
	
	
	

}
