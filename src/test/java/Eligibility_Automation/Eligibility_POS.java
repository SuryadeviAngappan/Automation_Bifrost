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

public class Eligibility_POS {


	String check_policy="https://console-staging.flexiloans.com/policy/check-policies/";
	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";
	Doc doc = new Doc();
	Create_New_Loancode lc = new Create_New_Loancode();
	String loancode;


	public void POS_Policy() 
	{

		String query="UPDATE loan_application_metadata AS lam\n"
				+ "JOIN loan_application AS la ON lam.loan_code = la.code\n"
				+ "JOIN loan_business_detail AS lbd ON la.code = lbd.loan_code\n"
				+ "SET \n"
				+ "    lam.is_topup = '0',\n"
				+ "    la.partner_code = '5911b47caa701',\n"
				+ "    lbd.year_of_incorporation = '2021',\n"
				+ "    lbd.is_rco = '0'\n"
				+ "WHERE lam.loan_code = '"+loancode+"' \n"
				+ "    AND la.code = '"+loancode+"'\n"
				+ "    AND lbd.loan_code = '"+loancode+"';";

		DataBaseUtility.executeUpdateQuery(query);
		ValidatableResponse res = given().when().get(check_policy+loancode).then().log().all();

	}

	@BeforeClass
	public void preCondition() throws Throwable 
	{

		DataBaseUtility.connectToDB();
		loancode=lc.IP_Qalified();
		POS_Policy();
		doc.BS(loancode);
		Thread.sleep(40000);

	}

	@DataProvider
	public String[] final_grade()
	{

		return new String[] {/*"A","B","C","D","E","NA"*/"B"};
	}


	/*
	 * To verify the final capping if policy is POS , ALL risk grade , no experiment, ABB & BTO
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

		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.POS[0].capped_banking_eligibility");
		double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);


		String actual_capped_abb_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.POS[0].capped_abb_eligibility");
		double abb_capping= Double.parseDouble(actual_capped_abb_eligibility_Value);



		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		if (BTO_Capping <= 1500000.00) {
		    if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D")) {
		        System.out.println("BTO Capping = " + BTO_Capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		    } else if (grade.equalsIgnoreCase("E")) {
		        System.out.println("BTO Capping = " + BTO_Capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		    } else {
		        System.out.println(" Eligibility is not getting calculated properly..!!!");
		    }
		} else {
		    System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

		if (abb_capping <= 1500000.00) {
		    if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B")) {
		        System.out.println("ABB Capping = " + abb_capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		    } else if (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) {
		        System.out.println("ABB Capping = " + abb_capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		    } else {
		        System.out.println(" Eligibility is not getting calculated properly..!!!");
		    }
		} else {
		    System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}


	/*
	 * When experiment is  Cautious Location, Policy POS
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
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.POS[0].capped_banking_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		double newCapping = 0.0;
		double reducedCapping = 0.0;
		boolean eligibility = false;

		switch (grade.toUpperCase()) {
		    case "A":
		        eligibility = (capping <= 1500000.00);
		        break;
		    case "B":
		    case "C":
		    case "D":
		    case "E":
		    case "NA":
		        newCapping = capping * (25.0 / 100.0);
		        reducedCapping = capping - newCapping;
		        eligibility = (reducedCapping <= 1000000.00);
		        break;
		}

		if (eligibility) {
		    System.out.println("BTO Capping  = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
		    System.out.println(" Eligibility is not getting calculated properly..!!!");
		}

	}

	/*
	 * When experiment is Salaried, Policy Direct
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
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.POS[0].capped_banking_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		double newCapping = 0;
		if (grade.equalsIgnoreCase("A")) {
		    if (capping <= 1000000.00) {
		        System.out.println("BTO Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		    } else {
		        System.out.println("Eligibility is not getting calculated properly..!!!");
		    }
		} else if (grade.equalsIgnoreCase("B")) {
		    newCapping = capping * 0.15;
		} else if (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("NA")) {
		    newCapping = capping * 0.25;
		} else if (grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E")) {
		    newCapping = 0;
		}

		double reducedCapping = capping - newCapping;
		if ((grade.equalsIgnoreCase("B") && reducedCapping <= 800000) || 
		    ((grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("NA")) && reducedCapping <= 600000) || 
		    (grade.equalsIgnoreCase("D") && reducedCapping <= 0) || 
		    (grade.equalsIgnoreCase("E") && reducedCapping <= 0)) {
		    System.out.println("BTO Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
		    System.out.println("Eligibility is not getting calculated properly..!!!");
		}


	}



	/*
	 * When experiment is Cautious Profile, Policy POS
	 */

	@Test(dataProvider = "final_grade")
	public void TC_04(String final_grade) throws SQLException 
	{

		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.POS[0].capped_banking_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);


		double reductionPercentage = 0;

		switch (grade.toUpperCase()) {
		    case "A":
		        if (capping <= 1500000.00) {
		            System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		        } else {
		            System.out.println(" Eligibility is not getting calculated properly..!!!");
		        }
		        break;
		    case "B":
		        reductionPercentage = 15.0 / 100;
		        break;
		    case "C":
		    case "D":
		    case "E":
		    case "NA":
		        reductionPercentage = 25.0 / 100;
		        break;
		}

		if (reductionPercentage != 0) {
		    double newCapping = capping * reductionPercentage;
		    double reducedCapping = capping - newCapping;
		    
		    if (reducedCapping <= 1000000) {
		        System.out.println("Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		    } else {
		        System.out.println(" Eligibility is not getting calculated properly..!!!");
		    }
		}

	}


	/*
	 * When ODCC, Policy POS (only ABB)
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

		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.POS[0].capped_abb_eligibility");
		double abb_capping= Double.parseDouble(actual_capped_banking_eligibility_Value);


		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);
		double cappingPercentage = 0;
		if (grade.equalsIgnoreCase("A")) {
		    if (abb_capping <= 1000000.00) {
		        System.out.println("ABB Capping = " + abb_capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
		    } else {
		        System.out.println("Eligibility is not getting calculated properly..!!!");
		    }
		} else if (grade.equalsIgnoreCase("B")) {
		    cappingPercentage = 15;
		} else if (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) {
		    cappingPercentage = 25;
		}

		if (cappingPercentage > 0) {
		    double newCapping = abb_capping * (cappingPercentage / 100);
		    double reducedCapping = abb_capping - newCapping;
		    if (reducedCapping <= 1000000.00) {
		        System.out.println("ABB Capping = " + reducedCapping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
		    } else {
		        System.out.println("Eligibility is not getting calculated properly..!!!");
		    }
		}


	}


	/*
	 * When experiment is Both Toxic Profile, Policy POS
	 */

	@Test(dataProvider = "final_grade")
	public void TC_06(String final_grade) throws SQLException 
	{

		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='Toxic credit_experiment-v1', experiment_id='107' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.POS[0].capped_banking_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		double reductionPercentage = 0.0;

		switch (grade.toUpperCase()) {
		    case "A":
		        if (capping <= 1000000.00) {
		            System.out.println("Capping = " + capping + " And Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		        } else {
		            System.out.println("Eligibility is not getting calculated properly..!!!");
		        }
		        break;

		    case "B":
		        reductionPercentage = 15.0;
		        break;

		    case "C":
		    case "D":
		    case "E":
		    case "NA":
		        reductionPercentage = 25.0;
		        break;

		    default:
		        System.out.println("Invalid grade entered.");
		}

		if (reductionPercentage > 0) {
		    double newCapping = capping * (reductionPercentage / 100);
		    double reducedCapping = capping - newCapping;

		    if (reducedCapping <= 1000000) {
		        System.out.println("Capping = " + reducedCapping + " And Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		    } else {
		        System.out.println("Eligibility is not getting calculated properly..!!!");
		    }
		}

	}


	/*
	 * When Experiment is Personal Loan, Policy POS 
	 */

	@Test(dataProvider = "final_grade")
	public void TC_07(String final_grade) throws SQLException 
	{

		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='Personal Loans', experiment_id='46' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.POS[0].capped_banking_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		double reductionPercentage = 0;
		switch (grade.toUpperCase()) {
		    case "A":
		        if (capping <= 500000.00) {
		            System.out.println("BTO Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		        } else {
		            System.out.println(" Eligibility is not getting calculated properly..!!!");
		        }
		        break;
		    case "B":
		        reductionPercentage = 15.0 / 100;
		        break;
		    case "C":
		    case "D":
		    case "E":
		    case "NA":
		        reductionPercentage = 25.0 / 100;
		        break;
		}

		if (!grade.equalsIgnoreCase("A")) {
		    double newCapping = capping * reductionPercentage;
		    double reducedCapping = capping - newCapping;

		    if ((grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E")) && reducedCapping <= 0) {
		        System.out.println("BTO Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		    } else if (reducedCapping <= 500000) {
		        System.out.println("BTO Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		    } else {
		        System.out.println(" Eligibility is not getting calculated properly..!!!");
		    }
		}



	}

}
