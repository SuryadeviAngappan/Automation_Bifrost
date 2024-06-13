package Eligibility_Automation;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericUtilities.DataBaseUtility;
import LoanCode_Generater.Create_New_Loancode;
import Pract.Doc;
import io.restassured.response.ValidatableResponse;

public class Eligibility_ECOM {
	
	
	String check_policy="https://console-staging.flexiloans.com/policy/check-policies/";
	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";
	Doc doc = new Doc();
	Create_New_Loancode lc = new Create_New_Loancode();
	String loancode;


	public void ECOM_Policy() 
	{

		String query="UPDATE loan_application_metadata AS lam\n"
				+ "JOIN loan_application AS la ON lam.loan_code = la.code\n"
				+ "JOIN loan_business_detail AS lbd ON la.code = lbd.loan_code\n"
				+ "SET \n"
				+ "    lam.is_topup = '0',\n"
				+ "    la.partner_code = '1c41176794537',\n"
				+ "    lbd.year_of_incorporation = '2021',\n"
				+ "    lbd.is_rco = '0'\n"
				+ "WHERE \n"
				+ "    lam.loan_code = '"+loancode+"'\n"
				+ "    AND la.code = '"+loancode+"'\n"
				+ "    AND lbd.loan_code = '"+loancode+"';";

		DataBaseUtility.executeUpdateQuery(query);
		ValidatableResponse res = given().when().get(check_policy+loancode).then().log().all();

	}

	
	public void Payout_data() 
	{
		
		String payout="UPDATE flexiloans_staging_db.loan_merchant_performance_detail\n"
				+ "SET loan_code = '"+loancode+"'\n"
				+ "WHERE id IN (1117,1118,1119,1120,1121,1122)";
		DataBaseUtility.executeUpdateQuery(payout);
	}
	
	@BeforeClass
	public void preCondition() throws Throwable 
	{

		DataBaseUtility.connectToDB();
		loancode= lc.IP_Qalified();
		ECOM_Policy();
		doc.BS(loancode);
		Thread.sleep(40000);
		Payout_data();

	}

	@DataProvider
	public String[] final_grade()
	{

		return new String[] {/*"A","B","C","D","E","NA"*/"B"};
	}
	
	
	/*
	 * To verify the final capping if policy is ECOM , ALL risk grade , no experiment, ABB , BTO , ECOM_Multiplier , ECOM_BANKING
	 */

	@Test(dataProvider = "final_grade")
	public void TC_01(String final_grade)  throws SQLException 
	{
		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
		try{DataBaseUtility.executeUpdateQuery(query);}
		catch(Exception e) {e.printStackTrace();}


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.ECOM[0].capped_banking_eligibility");
		double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);


		String actual_capped_abb_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.ECOM[0].capped_abb_eligibility");
		double abb_capping= Double.parseDouble(actual_capped_abb_eligibility_Value);
		
		String actual_capped_ECOM_multiplier_eligibility_value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.ECOM[0].capped_ecom_multiplier_eligibility");
		double ECOM_Multiplier= Double.parseDouble(actual_capped_ECOM_multiplier_eligibility_value);
		
		//String actual_capped_Payout_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.ECOM[0].capped_ecom_payout_eligibility");
		//double Payout_capping= Double.parseDouble(actual_capped_Payout_eligibility_Value);



		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		// BTO Capping
		
		if (BTO_Capping <= 2500000.00 && (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA"))) {
		    System.out.println("BTO Capping = " + BTO_Capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
		} else {
		    System.out.println("Eligibility is not getting calculated properly..!!!");
		}

		// ABB Capping

		if ((grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("NA")) && abb_capping <= 1500000.00 ||
			    (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E")) && abb_capping <= 1000000.00) {
			    System.out.println("ABB Capping = " + abb_capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
			} else {
			    System.out.println("Eligibility is not getting calculated properly..!!!");
			}

		// ECOM_Multiplier
		
		if (ECOM_Multiplier <= 2500000.00 && (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA"))) {
		    System.out.println("ECOM_Multiplier Capping = " + ECOM_Multiplier + " And Grade = " + grade + " Multiplier is getting calculated properly..!!!");
		} else {
		    System.out.println("Multiplier is not getting calculated properly..!!!");
		}

		
	}

	
	/*
	 * When experiment is  Cautious Location, Policy ECOM
	 */
	
	@Test(dataProvider = "final_grade")
	public void TC_02(String final_grade) throws SQLException 
	{
		
		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='Cautious Location', experiment_id='74' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.ECOM[0].capped_banking_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		double New_capping = 0;
		if (grade.equalsIgnoreCase("A")) {
			if (capping <= 1500000.00) {
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
			} else {
				System.out.println(" Eligibility is not getting calculated properly..!!!");
			}
		} else {
			if (grade.equalsIgnoreCase("B")) {
				New_capping = 0.15;
			} else if (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) {
				New_capping = 0.25;
			}

			New_capping *= capping;
			double Reduced_Capping = capping - New_capping;
			if (Reduced_Capping <= 1000000) {
				System.out.println("Capping = " + Reduced_Capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
			} else {
				System.out.println(" Eligibility is not getting calculated properly..!!!");
			}
		}
		
	}
	
	
	/*
	 * When experiment is Salaried, Policy ECOM
	 */
	
	@Test(dataProvider = "final_grade")
	public void TC_03(String final_grade) throws SQLException 
	{
		
		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='Salaried applicant', experiment_id='84' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.DIRECT[0].capped_banking_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		double newCapping = 0.0;
		double reducedCapping = 0.0;
		String message = "";

		if (grade.equalsIgnoreCase("A")) {
			if (capping <= 1000000.00) {
				message = "Eligibility is getting calculated properly..!!!";
			} else {
				message = "Eligibility is not getting calculated properly..!!!";
			}
		} else if (grade.equalsIgnoreCase("B")) {
			newCapping = capping * 0.15;
			reducedCapping = capping - newCapping;
			if (reducedCapping <= 800000) {
				message = "Eligibility is getting calculated properly..!!!";
			} else {
				message = "Eligibility is not getting calculated properly..!!!";
			}
		} else if (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("NA")) {
			newCapping = capping * 0.25;
			reducedCapping = capping - newCapping;
			if (reducedCapping <= 600000) {
				message = "Eligibility is getting calculated properly..!!!";
			} else {
				message = "Eligibility is not getting calculated properly..!!!";
			}
		} else if (grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E")) {
			newCapping = capping * 0.25;
			reducedCapping = capping - newCapping;
			if (reducedCapping <= 0.0) {
				message = "Eligibility is getting calculated properly..!!!";
			} else {
				message = "Eligibility is not getting calculated properly..!!!";
			}
		} else {
			message = "Invalid grade!";
		}

		System.out.println("Capping = " + reducedCapping + " And Grade = " + grade + " " + message);

		
	}
	
	
	
	/*
	 * When experiment is Rented RCO, Policy ECOM
	 */
	
	@Test(dataProvider = "final_grade")
	public void TC_04(String final_grade) throws SQLException 
	{
		
		

		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);	

		String profile="update loan_application set experiment_name='RENTED_DIRECT_V2.0' ,  experiment_id='52' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.ECOM[0].capped_banking_eligibility");
		double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);
		
		
		
		double cappingThreshold = 500000.00;
		double reductionPercentage = 0.0;

		if (grade.equalsIgnoreCase("A")) {
			if (capping <= cappingThreshold) {
				System.out.println("BTO Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
			} else {
				System.out.println("Eligibility is not getting calculated properly..!!!");
			}
		} else if (grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("NA")) {
			if (grade.equalsIgnoreCase("B")) {
				reductionPercentage = 0.15;
			} else {
				reductionPercentage = 0.25;
			}

			double newCapping = capping * reductionPercentage;
			double reducedCapping = capping - newCapping;

			if (reducedCapping <= cappingThreshold) {
				System.out.println("BTO Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
			} else {
				System.out.println("Eligibility is not getting calculated properly..!!!");
			}
		} else if (grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E")) {
			reductionPercentage = 0.25;

			double newCapping = capping * reductionPercentage;
			double reducedCapping = capping - newCapping;

			if (reducedCapping <= 0.0) {
				System.out.println("BTO Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
			} else {
				System.out.println("Eligibility is not getting calculated properly..!!!");
			}
	
		}
	}
	
	
	/*
	 * ODCC account
	 */
	
	
	@Test(dataProvider = "final_grade")
	public void TC_05(String final_grade) throws SQLException 
	{
		
		
		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);

		String query_1="update bank_db_staging.bank_auto_reject_summary_level set od_cc_flag='1' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query_1);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.ECOM[0].capped_abb_eligibility");
		double abb_capping= Double.parseDouble(actual_capped_banking_eligibility_Value);


		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		double reductionFactor = 0;

		if (grade.equalsIgnoreCase("A")) {
			reductionFactor = 0;
		} else if (grade.equalsIgnoreCase("B")) {
			reductionFactor = 0.15;
		} else if (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) {
			reductionFactor = 0.25;
		}

		double newCapping = abb_capping * reductionFactor;
		double reducedCapping = abb_capping - newCapping;

		if (reducedCapping <= 1000000) {
			System.out.println("ABB Capping = " + reducedCapping + " And Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}

		
	}
	
	
	
}
