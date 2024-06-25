package Eligibility_Automation;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.DataBaseUtility;
import GenericUtilities.ReportUtility;
import GenericUtilities.VerificationUtility;
import Listner.ListnerClass;
import LoanCode_Generater.Create_New_Loancode;
import Pract.Doc;
import io.restassured.response.ValidatableResponse;

@Listeners(Listner.ListnerClass.class)
public class Smfg_Direct {
	

	VerificationUtility verificationUtil = new VerificationUtility();
	ReportUtility reportUtil = new ReportUtility();
	String check_policy="https://console-staging.flexiloans.com/policy/check-policies/";
	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";
	Doc doc = new Doc();
//	Create_New_Loancode lc = new Create_New_Loancode();
	String loancode = "655ad295v27ju";
	

	public void Smfg_Directpolicy(String loancode) 
	{

		String query="UPDATE loan_application_metadata AS lam\n"
				+ "JOIN loan_application AS la ON la.code = lam.loan_code\n"
				+ "JOIN loan_business_detail AS lbd ON lbd.loan_code = la.code\n"
				+ "JOIN loan_applicant_detail AS lad ON lam.loan_code = lad.loan_code\n"
				+ "JOIN loan_finance_detail AS lfd ON lfd.loan_code = lad.loan_code\n"
				+ "JOIN bank_db_staging.bank_analysis_data_points AS bad ON bad.loan_code = lfd.loan_code\n"
				+ "SET lam.is_topup = '0',\n"
				+ "    la.source_of_lead = 'direct',\n"
				+ "    la.partner_code = '600c32152wnm3',\n"
				+ "    lbd.year_of_incorporation = '2022',\n"
				+ "    lbd.is_rco = '1',\n"
				+ "    lbd.legal_status = 'proprietorship',\n"
				+ "    lad.address_ownership_status = 'Owned',\n"
				+ "	   lbd.address_ownership_status = 'Owned',\n"
				+ "    lbd.date_of_incorporation = '2022-07-01',\n"
				+ "    bad.emi_bounce_count = '0',\n"
				+ "    lad.cibil_score = '700',\n"
				+ "    lad.uid = '13e29405-30e9-4940-9810-c9b2b824aa07',\n"
				+ "    lbd.serviceable_flag = '1',\n"
				+ "    lfd.monthly_total_sales = '500000'\n"
				+ "WHERE lam.loan_code = '"+loancode+"'\n"
				+ "    AND la.code = '"+loancode+"'\n"
				+ "    AND lbd.loan_code = '"+loancode+"'\n"
			    + "  AND lfd.loan_code = '"+loancode+"'\n"
			    + "  AND bad.loan_code = '"+loancode+"'\n"
			    + "    AND lad.loan_code = '"+loancode+"';";
		



		DataBaseUtility.executeUpdateQuery(query);
		ValidatableResponse res = given().when().get(check_policy+loancode).then().log().all();

	}
	@BeforeClass
	public void preCondition() throws Throwable 
	{

		DataBaseUtility.connectToDB();
//		loancode=lc.IP_Qalified();
		doc.BS(loancode);
		Thread.sleep(40000);
		Smfg_Directpolicy(loancode);


	}
	
	@DataProvider
	public String[] final_grade()
	{

		return new String[] {"A","B","C","D","E","NA"};
	}
	
	/*
	 * To verify the final capping if policy is SMFG_Direct , ALL risk grade , no experiment, ABB & BTO
	 */

	@Test(dataProvider = "final_grade")
	public void TC_01(String final_grade)  throws SQLException 
	{
		
		ListnerClass.reportLog("TC_01 :"+" To verify the final capping if policy is SMFG , ALL risk grade , no experiment, ABB & BTO");
		ListnerClass.reportLog(" Test Loancode ="+loancode);
		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
		try{DataBaseUtility.executeUpdateQuery(query);}
		catch(Exception e) {e.printStackTrace();}


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
	
		// verificationUtil.verifyResponseHeaders(repo, ResponseMessage.$200);

		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
		double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);

		String actual_capped_abb_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_abb_eligibility");
		double abb_Capping= Double.parseDouble(actual_capped_abb_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);


		// BTO Capping

		if (BTO_Capping <= 1500000.00 && (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("NA"))) {
			ListnerClass.reportLog("BTO Capping = " + BTO_Capping + " And Grade = " + grade  +  " Eligibility is getting calculated properly..!!!");
			System.out.println("BTO Capping = " + BTO_Capping + " And " + "Grade = " + grade +  "Eligibility is getting calculated properly..!!!");
		} else if (BTO_Capping <= 1000000.00 && grade.equalsIgnoreCase("E")) {
			ListnerClass.reportLog("BTO Capping = " + BTO_Capping + " And Grade = " + grade  +  " Eligibility is getting calculated properly..!!!");
			System.out.println("BTO Capping = " + BTO_Capping + " And " + "Grade =" + grade + "Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}


		// ABB Capping

		double gradeACapping = 1500000.00;
		double gradeBCapping = 1500000.00;
		double gradeCCapping = 1000000.00;
		double gradeDCapping = 1000000.00;
		double gradeECapping = 1000000.00;
		double gradeNACapping = 1000000.00;

		if ((grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B")) && abb_Capping <= gradeACapping ||
				(grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) && abb_Capping <= gradeNACapping) {
			ListnerClass.reportLog("ABB Capping = " + abb_Capping + " And Grade = " + grade  +  " Eligibility is getting calculated properly..!!!");
			System.out.println("ABB Capping = " + abb_Capping + " And Grade = " + grade  +  " Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}





	}

	/*
	 * When experiment is Both Rented, Policy SMFG_Direct
	 */

	@Test(dataProvider = "final_grade")
	public void TC_02(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_02 :"+" To verify that When experiment is Both Rented, Policy SMFG_Direct");

		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);	

		String profile="update loan_application set experiment_name='RENTED_DIRECT_V2.0' ,  experiment_id='52' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		String thick_cibi="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(thick_cibi);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		
		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
		double capping= Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);




		double newCapping;
		double reducedCapping = 0;
		String eligibilityMessage = "";

		switch (grade.toUpperCase()) {
		case "A":
			if (capping <= 1000000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
				eligibilityMessage = "Eligibility is getting calculated properly..!!!";
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		case "B":
			newCapping = capping * (15.0 / 100);
			reducedCapping = capping - newCapping;
			if (reducedCapping <= 800000) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
				eligibilityMessage = "Eligibility is getting calculated properly..!!!";
			} else {
				ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		case "C":
		case "D":
		case "E":
		case "NA":
			newCapping = capping * (25.0 / 100);
			reducedCapping = capping - newCapping;
			if (reducedCapping <= 600000) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
				eligibilityMessage = "Eligibility is getting calculated properly..!!!";
			} else {
				ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		default:
			System.out.println("Invalid grade");
		}
		System.out.println("Capping = " + reducedCapping + " And " + "Grade =" + grade + " " + eligibilityMessage);

	}
	




	/*
	 * When experiment is Both Cautious Profile, Policy SMFG_Direct
	 */

	@Test(dataProvider = "final_grade")
	public void TC_03(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_01 :"+" To verify that When experiment is Both Cautious Profile, Policy SMFG_Direct");

		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		
		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
		double capping= Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);


		double newCapping = 0.0;
		double reducedCapping = 0.0;

		if (grade.equalsIgnoreCase("A")) {
			if (capping <= 1500000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			} else {
				ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
				System.out.println("Eligibility is not getting calculated properly..!!!");
			}
		} else if (grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) {
			if (grade.equalsIgnoreCase("B")) {
				newCapping = capping * (15.0 / 100.0);
			} else {
				newCapping = capping * (25.0 / 100.0);
			}

			reducedCapping = capping - newCapping;

			if (grade.equalsIgnoreCase("B") && reducedCapping <= 1500000.0) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
			} else if (reducedCapping <= 1000000.0) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
			} else {
				ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
				System.out.println("Eligibility is not getting calculated properly..!!!");
			}
		} else {

			System.out.println("Invalid grade entered!");
		}
	}


/*
 * When experiment is Both Toxic Profile, Policy SMFG_Direct
 */

@Test(dataProvider = "final_grade")
public void TC_04(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_04 :"+" To verify that When experiment is Both Toxic Profile, Policy SMFG_Direct");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);

	String profile="update loan_application set experiment_name='Toxic credit_experiment-v1', experiment_id='107' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();

	
	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
	double capping= Double.parseDouble(actual_capped_banking_eligibility_Value);

	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);

	double cappingPercentage = 0;

	switch (grade.toUpperCase()) {
	case "A":
		if (capping <= 1000000.00) {
			ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			System.out.println("Capping = " + capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}
		break;
	case "B":
		cappingPercentage = 15;
	case "C":
	case "D":
	case "E":
	case "NA":
		if (cappingPercentage == 0) {
			cappingPercentage = 25;
		}
		double newCapping = capping * (cappingPercentage / 100);
		double reducedCapping = capping - newCapping;
		if (reducedCapping <= 1000000) {
			ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			System.out.println("Capping = " + reducedCapping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}
		break;
	default:
		System.out.println("Invalid grade entered!");
	}


}


/*
 * When experiment is  Cautious Location, Policy SMFG_Direct
 */

@Test(dataProvider = "final_grade")
public void TC_05(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_05 :"+" To verify that When experiment is  Cautious Location, Policy SMFG_Direct");
	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query);

	String profile="update loan_application set experiment_name='Cautious Location', experiment_id='74' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();
	
	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
	double capping= Double.parseDouble(actual_capped_banking_eligibility_Value);
	

	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);

	double New_capping = 0;
	if (grade.equalsIgnoreCase("A")) {
		if (capping <= 1500000.00) {
			ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
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
			ListnerClass.reportLog("Capping = " + Reduced_Capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			System.out.println("Capping = " + Reduced_Capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}
	}

}


/*
 * When experiment is Salaried, Policy SMFG_Direct
 */

@Test(dataProvider = "final_grade")
public void TC_06(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_06 :"+" To verify that When experiment is Salaried, Policy SMFG_Direct");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query);

	String profile="update loan_application set experiment_name='Salaried applicant', experiment_id='84' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();
		
	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
	double capping= Double.parseDouble(actual_capped_banking_eligibility_Value);

	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);

	double newCapping = 0.0;
	double reducedCapping = 0.0;
	String message = "";

	if (grade.equalsIgnoreCase("A")) {
		if (capping <= 1000000.00) {
			ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			message = "Eligibility is getting calculated properly..!!!";
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			message = "Eligibility is not getting calculated properly..!!!";
		}
	} else if (grade.equalsIgnoreCase("B")) {
		newCapping = capping * 0.15;
		reducedCapping = capping - newCapping;
		if (reducedCapping <= 800000) {
			ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			message = "Eligibility is getting calculated properly..!!!";
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			message = "Eligibility is not getting calculated properly..!!!";
		}
	} else if (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("NA")) {
		newCapping = capping * 0.25;
		reducedCapping = capping - newCapping;
		if (reducedCapping <= 600000) {
			ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			message = "Eligibility is getting calculated properly..!!!";
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			message = "Eligibility is not getting calculated properly..!!!";
		}
	} else if (grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E")) {
		newCapping = capping * 0.25;
		reducedCapping = capping - newCapping;
		if (reducedCapping <= 0.0) {
			ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			message = "Eligibility is getting calculated properly..!!!";
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			message = "Eligibility is not getting calculated properly..!!!";
		}
	} else {
		message = "Invalid grade!";
	}

	System.out.println("Capping = " + reducedCapping + " And Grade = " + grade + " " + message);


}

/*
 * When Experiment is Personal Loan, Policy SMFG_Direct 
 */

@Test(dataProvider = "final_grade")
public void TC_07(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_07 :"+" To verify that When Experiment is Personal Loan, Policy SMFG_Direct");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query);

	String profile="update loan_application set experiment_name='Personal Loans', experiment_id='46' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();
		
	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
	double capping= Double.parseDouble(actual_capped_banking_eligibility_Value);

	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);

	double cappingThreshold = 500000.00;
	double reductionPercentage = 0.0;

	if (grade.equalsIgnoreCase("A")) {
		if (capping <= cappingThreshold) {
			ListnerClass.reportLog("BTO Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			System.out.println("BTO Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
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
			ListnerClass.reportLog("BTO Capping = " + reducedCapping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			System.out.println("BTO Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}
	} else if (grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E")) {
		reductionPercentage = 0.25;

		double newCapping = capping * reductionPercentage;
		double reducedCapping = capping - newCapping;

		if (reducedCapping <= 0.0) {
			ListnerClass.reportLog("BTO Capping = " + reducedCapping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			System.out.println("BTO Capping = " + reducedCapping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}
	}


}

/*
 * When Experiment is Saving Account, Policy SMFG_Direct 
 */

@Test(dataProvider = "final_grade")
public void TC_08(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_08 :"+" To verify that When Experiment is Saving Account, Policy SMFG_Direct");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query);

	String profile="update loan_application set experiment_name='Saving account', experiment_id='85' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();

	
	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
	double capping= Double.parseDouble(actual_capped_banking_eligibility_Value);

	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);



	double New_capping = capping * (30.0 / 100.0);  
	double Reduced_Capping = capping - New_capping;

	if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("NA")) {
		if (Reduced_Capping <= 500000) {
			ListnerClass.reportLog("Capping = " + Reduced_Capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
			System.out.println("Capping = " + Reduced_Capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}
	} else if (grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E")) {
		if (Reduced_Capping <= 0.0) {
			ListnerClass.reportLog("Capping = " + Reduced_Capping + " And " + "Grade ="  + grade + "  Eligibility is getting calculated properly..!!!");
			System.out.println("Capping = " + Reduced_Capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println(" Eligibility is not getting calculated properly..!!!");
		}
	}
}



@DataProvider
public String[] Final_grade()
{

	return new String[] {"A","B","C","D"};
}


/*
 * Max capping where “no experiment” is applied (excl DSA,) where Business vintage is > 3 years & one property is owned
 */

@Test(dataProvider = "Final_grade")
public void TC_09(String Final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_09 :"+" To verify that Max capping where “no experiment” is applied (excl DSA,) where Business vintage is > 3 years & one property is owned");

	String query="update risk_grading_final set final_grade='"+Final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);

	String address_ownership_status_1=" UPDATE `loan_applicant_detail` SET `address_ownership_status` = 'Owned' WHERE `loan_code` = '"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(address_ownership_status_1);


	String Business_vintage=" UPDATE `loan_business_detail` SET `date_of_incorporation` = '2018-01-01' WHERE `loan_code` = '"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(Business_vintage);

	String partner="UPDATE  loan_application SET partner_code  = '1c41176794537'  WHERE ( code = '"+loancode+"')";
	DataBaseUtility.executeUpdateQuery(partner);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	
	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
	double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);
	
	String actual_capped_abb_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_abb_eligibility");
	double abb_Capping= Double.parseDouble(actual_capped_abb_eligibility_Value);

	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);

	// ABB Capping
	
	double gradeLimitA = 2000000.00;
	double gradeLimitB = 1500000.00;

	boolean isEligible = (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B")) && BTO_Capping <= gradeLimitA ||
	                     (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D")) && BTO_Capping <= gradeLimitB;

	if (isEligible) {
		ListnerClass.reportLog("BTO Capping = " + BTO_Capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
		System.out.println("BTO Capping = " + BTO_Capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
	} else {
		ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
	    System.out.println("Eligibility is not getting calculated properly..!!!");
	}

	
	// BTO Capping

	if (abb_Capping <= 2000000.00) {
	    if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D")) {
	    	ListnerClass.reportLog("ABB Capping = " + abb_Capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
	    	System.out.println("ABB Capping = " + abb_Capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
	    } else {
	    	ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
	        System.out.println("Eligibility is not getting calculated properly..!!!");
	    }
	} 



}


/*
 * Max Capping of NTC and Thin Cibil
 */

@Test(dataProvider = "final_grade")
public void TC_10(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_10 :"+" To verify that Max Capping of NTC and Thin Cibil");


	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);

	String query_1="update flexiloans_staging_db.loan_applicant_detail set cibil='000-1' where loan_code='"+loancode+"'"; // -1,0,200,300 boundry
	DataBaseUtility.executeUpdateQuery(query_1);

	String query_2="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='0' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query_2);


	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();
	
	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
	double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);



	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);

	if ((grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) && BTO_Capping <= 500000.00) {
		ListnerClass.reportLog("BTO Capping = " + BTO_Capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
		System.out.println("BTO Capping = " + BTO_Capping + " And Grade = " + grade + " Eligibility is getting calculated properly..!!!");
	} else {
		ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
	    System.out.println("Eligibility is not getting calculated properly..!!!");
	}


}

/*
 * NTC with limit 
 */

@Test(dataProvider = "final_grade")
public void TC_11(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_11 :"+" To verify that NTC with limit");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);

	String cibil[]={"-1","0","200","300"};

	for (String value:cibil) 
	{
		String query_1="update flexiloans_staging_db.loan_applicant_detail set cibil_score='"+value+"' where loan_code='"+loancode+"'"; 			
		DataBaseUtility.executeUpdateQuery(query_1);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		
		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
		double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);



		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String cibi="select cibil_score from flexiloans_staging_db.loan_applicant_detail where loan_code='"+loancode+"'";
		String cibil_score=DataBaseUtility.ExecuteQuery(cibi);

		String query_2="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query_2);




		if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) 
		{
			boolean isEligible = (cibil_score.equals("-1") || cibil_score.equals("0") || cibil_score.equals("200") || cibil_score.equals("300")) && (BTO_Capping <= 500000.00);

			if (isEligible) {
				ListnerClass.reportLog("IF Risk_Garde IS '" + grade + "' And Cibil_Score IS '" + cibil_score + "' Than Final_BTO_Capping IS '" + BTO_Capping + "' ... Eligibility Capping is calculated Properly..!!!!");
				System.out.println("IF Risk_Garde IS '" + grade + "' And Cibil_Score IS '" + cibil_score + "' Than Final_BTO_Capping IS '" + BTO_Capping + "' ... Eligibility Capping is calculated Properly..!!!!");
			} else {
				ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
				System.out.println("Eligibility Capping is not getting calculated properly..!!!");
			}
		} else {
			System.out.println("Invalid grade specified!");
		}

	}	
}


/*
 * When ODCC, Policy SMFG_Direct (only ABB)
 */

@Test(dataProvider = "final_grade")
public void TC_12(String final_grade) throws SQLException 
{

	ListnerClass.reportLog("TC_12 :"+" To verify that When ODCC, Policy SMFG_Direct (only ABB)");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);

	String query_1="update bank_db_staging.bank_auto_reject_summary_level set od_cc_flag='1' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query_1);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();

	String actual_capped_abb_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_abb_eligibility");
	double abb_capping= Double.parseDouble(actual_capped_abb_eligibility_Value);



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
		ListnerClass.reportLog("ABB Capping = " + reducedCapping + " And Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
		System.out.println("ABB Capping = " + reducedCapping + " And Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
	} else {
		ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
		System.out.println("Eligibility is not getting calculated properly..!!!");
	}


}


/*
 * When experiment is Both Rented, Policy SMFG_Direct and account is NTC
 */

@Test(dataProvider = "final_grade")
public void TC_13(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_13 :"+" To verify that When experiment is Both Rented, Policy SMFG_Direct and account is NTC");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);
	
	String cibil[]={"-1","0","200","300"};
	
	for (String value:cibil) 
	{
		String query_1="update flexiloans_staging_db.loan_applicant_detail set cibil_score='"+value+"' where loan_code='"+loancode+"'"; 
		DataBaseUtility.executeUpdateQuery(query_1);


     	String Both_rented="update loan_application set experiment_name='RENTED_DIRECT_V2.0' ,  experiment_id='51' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(Both_rented);
		
		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

		
		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
		double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);



		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String cibi="select cibil_score from flexiloans_staging_db.loan_applicant_detail where loan_code='"+loancode+"'";
		String cibil_score=DataBaseUtility.ExecuteQuery(cibi);

		String query_2="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query_2);


		if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) 
		{
			boolean isEligible = (cibil_score.equals("-1") || cibil_score.equals("0") || cibil_score.equals("200") || cibil_score.equals("300")) && (BTO_Capping <= 500000.00);

			if (isEligible) {
				ListnerClass.reportLog("IF Risk_Garde IS '" + grade + "' And Cibil_Score IS '" + cibil_score + "' Experiment Is Both_Rented Then Final_BTO_Capping IS '" + BTO_Capping + "' ... Eligibility Capping is calculated Properly..!!!!");
				System.out.println("IF Risk_Garde IS '" + grade + "' And Cibil_Score IS '" + cibil_score + "' Experiment Is Both_Rented Then Final_BTO_Capping IS '" + BTO_Capping + "' ... Eligibility Capping is calculated Properly..!!!!");
			} else {
				ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
				System.out.println("Eligibility Capping is not getting calculated properly..!!!");
			}
		} else {
			System.out.println("Invalid grade specified!");
		}

	}	
}


/*
 * When experiment is Both Rented, Policy SMFG_Direct and account is Thin cibil
 */

@Test(dataProvider = "final_grade")
public void TC_14(String final_grade) throws SQLException 

{
	ListnerClass.reportLog("TC_14 :"+" To verify that When experiment is Both Rented, Policy SMFG_Direct and account is Thin cibil");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);	

	String profile="update loan_application set experiment_name='RENTED_DIRECT_V2.0' ,  experiment_id='52' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	String thick_cibi="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='0' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(thick_cibi);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();
	

	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_banking_eligibility");
	double capping= Double.parseDouble(actual_capped_banking_eligibility_Value);


	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);

	if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA") && (capping<=500000.00)) 
	{
		ListnerClass.reportLog("IF Risk_Garde IS '" + grade + " Than Final_BTO_Capping IS '" + capping + "' ... Eligibility Capping is calculated Properly..!!!!");
		System.out.println("IF Risk_Garde IS '" + grade + " Than Final_BTO_Capping IS '" + capping + "' ... Eligibility Capping is calculated Properly..!!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility Capping is not getting calculated properly..!!!");
		}
	} 

	
/*
 * When experiment is Both Rented, Policy SMFG_Direct and account is ODCC
 */

@Test(dataProvider = "final_grade")
public void TC_15(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_01 :"+" To verify that When experiment is Both Rented, Policy SMFG_Direct and account is ODCC");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);	

	String thick_cibi="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(thick_cibi);
	
	String profile="update loan_application set experiment_name='RENTED_DIRECT_V2.0' ,  experiment_id='52' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	
	String query_1="update bank_db_staging.bank_auto_reject_summary_level set od_cc_flag='1' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query_1);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();
	String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.'SMFG India Credit'.SMFG_DIRECT[0].capped_abb_eligibility");
	double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);
	
	
	if(grade.equalsIgnoreCase("A") ||grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") && (capping <=1000000.00)) 
	{
		ListnerClass.reportLog( " Risk Grade Is '"+grade+"'  And Experiment Is Both Rented Then ODCC Capping Is '"+capping+"'  ... Eligibility is getting calculated Properly.!!! ");
		System.out.println( " Risk Grade Is '"+grade+"'  And Experiment Is Both Rented Then ODCC Capping Is '"+capping+"'  ... Eligibility is getting calculated Properly.!!! ");
	}
	else if(grade.equalsIgnoreCase("NA") && (capping<=00.00)) 
	{
		ListnerClass.reportLog( " Risk Grade Is '"+grade+"'  And Experiment Is Both Rented Then ODCC Capping Is '"+capping+"'  ... Eligibility is getting calculated Properly.!!! ");
		System.out.println( " Risk Grade Is '"+grade+"'  And Experiment Is Both Rented Then ODCC Capping Is '"+capping+"'  ... Eligibility is getting calculated Properly.!!! ");
	}
	else 
	{
		ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
		System.out.println(" Eligibility is not getting calculated properly");
	}
		
}


}

	
	
	
