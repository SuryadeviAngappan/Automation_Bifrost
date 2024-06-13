package Eligibility;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BS_Upload.Upload_BS;
import GenericUtilities.DataBaseUtility;
import LoanCode_Generater.Create_New_Loancode;
import Policy.Policies;
import io.restassured.response.ValidatableResponse;

public class ABFL_DIRECT_Capping_With_Experiment {



	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";
	Create_New_Loancode lc = new Create_New_Loancode();
	Upload_BS bs = new Upload_BS();
	DataBaseUtility dbUtil = new DataBaseUtility();
	Policies policy = new Policies();
	String loancode;
	String query;


	@BeforeClass
	public void db() throws Throwable 
	{
		dbUtil.connectToDB();
		loancode=lc.IP_Qalified();
		bs.BS_Upload(loancode);
		String uid="update loan_applicant_detail set uid='2af83173-12a4-4cab-bb39-eb9b7b07e0d2' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(uid);
		System.out.println(loancode);
		given().when().get("https://console-staging.flexiloans.com/policy/check-policies/"+loancode).then().log().all();
		Thread.sleep(40000);

	}


	@DataProvider
	public String[] final_grade()
	{

		return new String[] {"A","B","C","D","E","NA"};
	}


	/*
	 * exp- both rented and risk_grade- A capping should be 10Lakh
	 */

	@Test(dataProvider = "final_grade")
	public void TC_01(String final_grade)  throws SQLException 
	{
		query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
		try{dbUtil.executeUpdateQuery(query);}
		catch(Exception e) {e.printStackTrace();}


		String address_ownership_status_1=" UPDATE `loan_applicant_detail` SET `address_ownership_status` = 'Rented' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(address_ownership_status_1);


		String address_ownership_status_2=" UPDATE `loan_business_detail` SET `address_ownership_status` = 'Rented' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(address_ownership_status_2);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=dbUtil.ExecuteQuery(final_g);

		if(grade.equalsIgnoreCase("A") && capping <= 1000000 || grade.equalsIgnoreCase("B") && capping <= 800000 || grade.equalsIgnoreCase("C") && capping <= 600000 || grade.equalsIgnoreCase("D") && capping <= 600000 || grade.equalsIgnoreCase("E") && capping <= 600000) 
		{
			System.out.println("Capping = "+capping+ " And "+ "Grade =" +grade+ "  Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}


	}


	/*
	 * 
	 */
	
	@Test
	public void TC_02() 
	{
		
	}
	


}
