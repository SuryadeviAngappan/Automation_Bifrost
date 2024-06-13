package Eligibility;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.util.HashMap;

import javax.print.Doc;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BS_Upload.Upload_BS;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.ThreadLocalClass;
import LoanCode_Generater.Create_New_Loancode;
import Policy.Policies;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import io.restassured.response.ValidatableResponse;

public class ABFL_DIRECT_Eligibility_Capping {

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
		//loancode=lc.IP_Qalified();
		loancode="65c080328mjbb";
		bs.BS_Upload(loancode);
		String uid="update loan_applicant_detail set uid='2af83173-12a4-4cab-bb39-eb9b7b07e0d2' where loan_code='"+loancode+"'";
	//	dbUtil.executeUpdateQuery(uid);
		System.out.println(loancode);
		//policy.ABFL_ECOM(loancode);
		given().when().get("https://console-staging.flexiloans.com/policy/check-policies/"+loancode).then().log().all();
		Thread.sleep(40000);

	}


	@Test(priority=2)
	public void RG_A() throws InterruptedException 
	{

		query="update risk_grading_final set final_grade='A' where loan_code='"+loancode+"'";
		try{dbUtil.executeUpdateQuery(query);}
		catch(Exception e) {e.printStackTrace();}


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
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

	@Test(priority=3)
	public void RG_B() throws InterruptedException 
	{

		query="update risk_grading_final set final_grade='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query);


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		if(capping <= 1500000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}



	@Test(priority=4)
	public void RG_C() throws InterruptedException 
	{

		query="update risk_grading_final set final_grade='C' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		if(capping <= 1000000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}

	@Test(priority=5)
	public void RG_D() throws InterruptedException 
	{

		query="update risk_grading_final set final_grade='D' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query);


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		if(capping <= 1000000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}
	}


	@Test(priority=6)
	public void RG_E() throws InterruptedException 
	{

		query="update risk_grading_final set final_grade='E' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query);


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		if(capping <= 1000000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}

	@Test(priority=7)
	public void RG_NA() throws InterruptedException 
	{

		query="update risk_grading_final set final_grade='NA' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query);


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		if(capping <= 1000000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}

	@DataProvider
	public String[] final_grade(){

		return new String []
				//{"A","B","C","D","E","NA"};
				{"B","D"};
	}


	/*
	 * To_Verify_the_final_ABB_BTO_capping_of_ABFL_DIRECT_policy_when_NTC
	 */

	@Test(dataProvider = "final_grade",priority = 8)
	public void TC_01(String final_grade)
	{

		query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		dbUtil.executeUpdateQuery(query);

		String cibil=" UPDATE loan_applicant_detail set cibil_score='000-1' where loan_code='"+loancode+"' ";
		dbUtil.executeUpdateQuery(cibil);

		
		String Business_vintage=" UPDATE `loan_business_detail` SET `date_of_incorporation` = '2019-01-01' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(Business_vintage);
		
		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		if(capping <= 500000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}

	/*
	 * To_Verify_the_final_ABB_BTO_capping_of_ABFL_DIRECT_policy_when_Thin_Cibil
	 */

	@Test(dataProvider = "final_grade",priority = 9)
	public void TC_02(String final_grade)
	{

		query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		dbUtil.executeUpdateQuery(query);

		String thik_cibil="UPDATE `risk_grading_final` SET `is_thick_cibil` = '0' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(thik_cibil);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		if(capping <= 500000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}

	/*
	 * To Verify the final ABB & BTO capping of ABFL_DIRECT policy when Business Vintage > 1.5 years
	 */



	@Test(dataProvider = "final_grade",priority = 10)
	public void TC_03(String final_grade) 
	{


		query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		dbUtil.executeUpdateQuery(query);

		String Business_vintage=" UPDATE `loan_business_detail` SET `date_of_incorporation` = '2019-01-01' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(Business_vintage);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);

		if(capping <= 1000000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}

	/*
	 * To Verify the final ABB & BTO capping of ABFL_DIRECT policy when both ownership status is rented
	 */
	@Test(dataProvider = "final_grade",priority = 11)
	public void TC_04(String final_grade) 
	{
		query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		dbUtil.executeUpdateQuery(query);

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

		if(capping <= 700000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}

	

	@DataProvider
	public String[] tc_05() 
	{
		return new String[] 
		{"A","B","C","D"};
				
	}


	/*
	 * To Verify the final ABB capping of ABFL_DIRECT policy where no experiments tagged , 
	 * Business Vintage is greater than 3 Years, any one property is owned, partner type should not be DSA
	 */


	@Test(dataProvider ="tc_05",priority = 12 )
	public void TC_05(String final_grade) throws SQLException 
	{

		query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		dbUtil.executeUpdateQuery(query);

		String address_ownership_status_1=" UPDATE `loan_applicant_detail` SET `address_ownership_status` = 'Owned' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(address_ownership_status_1);


		String Business_vintage=" UPDATE `loan_business_detail` SET `date_of_incorporation` = '2018-01-01' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(Business_vintage);

		String partner="UPDATE  loan_application SET partner_code  = '1c41176794537'  WHERE ( code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(partner);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);
		
		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=dbUtil.ExecuteQuery(final_g);

		if(grade.equalsIgnoreCase("A") && capping <= 2000000 || grade.equalsIgnoreCase("B") && capping <= 2000000 || grade.equalsIgnoreCase("C") && capping <= 1500000 || grade.equalsIgnoreCase("D") && capping <= 1500000 ) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}


	}
	
	@DataProvider
	public String[] tc_06() 
	{
		return new String[] 
				{"A","B","C","D"};
	}
	
	/*
	 * To Verify the final BTO capping of ABFL_DIRECT policy where no experiments tagged , 
	 * Business Vintage is greater than 3 Years, any one property is owned, partner type should not be DSA
	 */
	
	@Test(dataProvider = "tc_06",priority = 13)
	public void TC_06(String final_grade) throws SQLException 
	{
		
		query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		dbUtil.executeUpdateQuery(query);
		
		String Business_vintage=" UPDATE `loan_business_detail` SET `date_of_incorporation` = '2018-01-01' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(Business_vintage);

		String partner="UPDATE  loan_application SET partner_code  = '1c41176794537'  WHERE ( code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(partner);

		String address_ownership_status_1=" UPDATE `loan_applicant_detail` SET `address_ownership_status` = 'Rented' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(address_ownership_status_1);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_abb_eligibility");
		float capping=Integer.parseInt(actual_capped_banking_eligibility_Value);
		
		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=dbUtil.ExecuteQuery(final_g);

		if(grade.equalsIgnoreCase("A")&& capping<=2000000 || grade.equalsIgnoreCase("B") && capping<=2000000 || grade.equalsIgnoreCase("C") && capping<=1500000 || grade.equalsIgnoreCase("B") && capping<=1500000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

		
		
	}

	/*
	 * To Verify the final BTO capping of ABFL_DIRECT policy where no experiments tagged , 
	 * Business Vintage is greater than 3 Years, any one property is owned, partner type should not be DSA
	 */
	
	@Test(dataProvider = "tc_06",priority = 14)
	public void TC_07(String final_grade) throws SQLException 
	{
		
		query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		dbUtil.executeUpdateQuery(query);
		
		String Business_vintage=" UPDATE `loan_business_detail` SET `date_of_incorporation` = '2018-01-01' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(Business_vintage);

		String partner="UPDATE  loan_application SET partner_code  = '1c41176794537'  WHERE ( code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(partner);

		String address_ownership_status_1=" UPDATE `loan_applicant_detail` SET `address_ownership_status` = 'Rented' WHERE `loan_code` = '"+loancode+"' ";
		dbUtil.executeUpdateQuery(address_ownership_status_1);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.ABFL_DIRECT[0].capped_banking_eligibility");
		int capping=Integer.parseInt(actual_capped_banking_eligibility_Value);
		
		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=dbUtil.ExecuteQuery(final_g);

		if(grade.equalsIgnoreCase("A")&& capping<=2000000 || grade.equalsIgnoreCase("B") && capping<=2000000 || grade.equalsIgnoreCase("C") && capping<=1500000 || grade.equalsIgnoreCase("B") && capping<=1500000) 
		{
			System.out.println(capping +" Eligibility is getting calculated properly..!!!");
		}
		else 
		{
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}
	

}
