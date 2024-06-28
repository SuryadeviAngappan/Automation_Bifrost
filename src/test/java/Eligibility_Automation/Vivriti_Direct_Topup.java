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
public class Vivriti_Direct_Topup {
	VerificationUtility verificationUtil = new VerificationUtility();
	ReportUtility reportUtil = new ReportUtility();
	String check_policy="https://console-staging.flexiloans.com/policy/check-policies/";
	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";
	Doc doc = new Doc();
	Create_New_Loancode lc = new Create_New_Loancode();
	String loancode = lc.Fresh_Lead();
	
	public void Vivriti_Direct_Topup(String loancode) 
	{

		String query="UPDATE loan_application_metadata AS lam\n"
				+ "JOIN loan_application AS la ON la.code = lam.loan_code\n"
				+ "JOIN loan_applicant_detail AS lad ON lam.loan_code = lad.loan_code\n"
				+ "SET lam.is_topup = '1',\n"
				+ "    lam.reference_loan_code = '665068a039gr4',\n"
				+ "    la.partner_code = '9c7bfcd45af46',\n"
				+ "    la.application_status = 'IP_FRESH_REGISTRATION',\n"
				+ "    lad.uid = '13e29405-30e9-4940-9810-c9b2b824aa07',\n"  
				+ "    lad.cibil_score = '730'\n"
				+ "WHERE lam.loan_code = '"+loancode+"'\n"
				+ "    AND la.code = '"+ loancode +"'\n"
				+ "    AND lad.loan_code = '"+ loancode +"';";

		DataBaseUtility.executeUpdateQuery(query);
		
		
		String insertquery1 = "INSERT INTO flexiloans_staging_db.cre_rule_status ( loan_code, cre_reject_status, rule7_gold_loan_count, rule6_numRecentAccount, rule7_other_loan_count, rule1_cibil_score_v3, rule1_cibil_score_result, rule2_overdue_amount, rule2_overdue_result, rule2_business_overdue_amount, rule2_credit_card_overdue_amount, rule2_secured_overdue_amount, rule2_business_overdue_amount_v3, rule2_credit_card_overdue_amount_v3, rule2_overdue_amount_v3, rule2_secured_overdue_amount_v3, rule3_negative_trade_result, rule3_negative_trades, rule4_negative_dpd_count, rule4_negative_dpd_result, rule4_negative_tradeline_count, rule5_recent_dpd, rule5_recent_dpd_result, rule5_total_dpd, rule5_dpd_count, rule5_overall_overdue_amount, rule6_past_negative_dpd_count, rule6_past_negative_trade_count, rule6_past_recent_result, rule6_recent_negative_dpd_count, rule6_recent_positive_trade_count, rule7_Gold_loan_result, rule8_unsecBL_acc_6M_count, rule8_unsecBL_enq_6M_count, rule8_unsecBL_gt8Enq_noAcc_result, rule12_BL_PL_HL_Auto_loan_count, rule12_BL_PL_HL_Auto_loan_result, success, uid, created_at, updated_at) VALUES ( '"+ loancode +"', 0, 0, 7, 27, '733', 0, 0, 1, '0', '0', '0', '1448', '0', '1448', '0', '0', '0', '0', '0', '1', '0', '1', '0', '3', '14482', '5', '0', '0', '10', '7', '0', '0', '1', '0', '1', '0', '1', '13e29405-30e9-4940-9810-c9b2b824aa07', '2023-06-29 16:24:41', '2024-06-14 14:33:31');";
		DataBaseUtility.executeUpdateQuery(insertquery1);
		ValidatableResponse res = given().when().get(check_policy+loancode).then().log().all();
		
		String insertquery2 = " INSERT INTO flexiloans_staging_db.risk_grading_final (loan_code, final_grade, risk_type, is_thick_cibil) VALUES ('"+ loancode +"', 'A', 'C', '1');";
		DataBaseUtility.executeUpdateQuery(insertquery2);
		ValidatableResponse response = given().when().get(check_policy+loancode).then().log().all();


	}



	@BeforeClass
	public void preCondition() throws Throwable 
	{

		DataBaseUtility.connectToDB();
		//loancode=lc.IP_Qalified();
		doc.BS(loancode);
		Thread.sleep(40000);
		Vivriti_Direct_Topup(loancode);
}


	@DataProvider
	public String[] final_grade()
	{

		return new String[] {"A","B","C","D","E","NA"};
	}
	
	
	
	/*
	 * To verify the final capping if policy is Vivriti_Direct_TOPup , ALL risk grade , no experiment, ABB & BTO
	 */

	
	@Test(dataProvider = "final_grade")
	public void TC_01(String final_grade)  throws SQLException {

		ListnerClass.reportLog("TC_01 :"+" To verify the final capping if policy is Vivriti_Direct_TOPup , ALL risk grade , no experiment, ABB & BTO");
		ListnerClass.reportLog(" Test Loancode ="+loancode);
		
		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
		try{DataBaseUtility.executeUpdateQuery(query);}
		catch(Exception e) {e.printStackTrace();}


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
		

		
		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
		double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);

		String actual_capped_abb_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_abb_eligibility");
		double abb_capping= Double.parseDouble(actual_capped_abb_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);
		String FixedCapping = "1500000";
		String FixedCapping1 = "1000000";


		
		if (abb_capping <= 1500000.00 && (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") )) {
			ListnerClass.reportLog("ABB_Capping = " + abb_capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
			System.out.println("ABB_Capping = " + abb_capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
		}else if (abb_capping <= 1000000.00 && (grade.equalsIgnoreCase("E")||grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") ||grade.equalsIgnoreCase("NA") )) {
			ListnerClass.reportLog("ABB_Capping = " + abb_capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping1+ "  Eligibility is getting calculated properly..!!!");
			System.out.println("ABB_Capping = " + abb_capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}

		// BTO Capping

		double gradeACapping = 1500000.00;
		double gradeBCapping = 1500000.00;
		double gradeCCapping = 1500000.00;
		double gradeDCapping = 1500000.00;
		double gradeECapping = 1500000.00;
		double gradeNACapping = 1500000.00;
		


		if ((grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B")  ||
				grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) && abb_capping <= gradeNACapping) {
			ListnerClass.reportLog("BTO Capping = " + BTO_Capping + " And Grade = " + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
			System.out.println("BTO Capping = " + BTO_Capping + " And Grade = " + grade +   "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}

	}
	/*
	 * When experiment is Both Rented, Policy Vivriti_Direct_TOPup
	 */

	@Test(dataProvider = "final_grade")
	public void TC_02(String final_grade) throws SQLException 
	{
		
		ListnerClass.reportLog("TC_02 :"+" To verify that When experiment is Both Rented, Policy Vivriti_Direct_TOPup");


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

		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
		double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);
		
		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String eligibilityMessage = "";
		String FixedCapping = "1000000";
		String FixedCapping1 = "800000";
		String FixedCapping2 = "600000";


		switch (grade.toUpperCase()) {
		case "A":
			if (capping <= 1000000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade = " + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
				ListnerClass.reportLog(eligibilityMessage);

			}
			break;
		case "B":
			if (capping <= 800000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade =" + grade + " when fixed capping =" +FixedCapping1+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog(eligibilityMessage);
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		case "C":
		case "D":
		case "E":
		case "NA":
			if (capping <= 600000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping2+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog(eligibilityMessage);
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		default:
			ListnerClass.reportLog("Invalid grade");
			System.out.println("Invalid grade");
		}
		System.out.println("Capping = " + capping + " And " + "Grade =" + grade + " " + eligibilityMessage);
	}
	
	/*
	 * When experiment is Both Cautious Profile,ABB, Policy Vivriti_Direct_TOPup
	 */
	@Test(dataProvider = "final_grade")
	public void TC_03(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_03 :"+" To verify that When experiment is Both Cautious Profile,ABB, Policy Vivriti_Direct_TOPup");
		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_abb_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);
		
		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String eligibilityMessage = "";
		String FixedCapping = "1500000";
		String FixedCapping1 = "1000000";


		switch (grade.toUpperCase()) {
		case "A":
		case "B":
			if (capping <= 150000.00) {
				
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "sxelect experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog(eligibilityMessage);
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		case "C":
		case "D":
		case "E":
		case "NA":
			if (capping <= 1000000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping1+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog(eligibilityMessage);
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		default:
			ListnerClass.reportLog("Invalid grade");
			System.out.println("Invalid grade");
		}
		System.out.println("Capping = " + capping + " And " + "Grade =" + grade + " " + eligibilityMessage);
	}
	
	/*
	 * When experiment is Both Cautious Profile,BTO, Policy Vivriti_Direct_TOPup
	 */
	@Test(dataProvider = "final_grade")
	public void TC_04(String final_grade) throws SQLException 
	{
	ListnerClass.reportLog("TC_04 :"+" To verify that When experiment is Both Cautious Profile,BTO, Policy Vivriti_Direct_TOPup");
	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);

	String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();

	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
	Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

	

	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);


	String eligibilityMessage = "";
	
	String FixedCapping = "1500000";

	if ((final_grade.equalsIgnoreCase("A") || final_grade.equalsIgnoreCase("B")  ||
			final_grade.equalsIgnoreCase("C") || final_grade.equalsIgnoreCase("D") || final_grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) && capping <=1500000.00 ) {
		ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
		System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
		String exp = DataBaseUtility.ExecuteQuery(select_query);
	} else {
		ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
		System.out.println("Eligibility is not getting calculated properly..!!!");
	}	
	}
	
	/*
	 * When experiment is Both Toxic Profile, Policy Vivriti_Direct_TOPup
	 */
	@Test(dataProvider = "final_grade")
	public void TC_05(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_05 :"+" When experiment is Both Toxic Profile, Policy Vivriti_Direct_TOPup");
	
	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);

	String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();

	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
	Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);
	
	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);

	String eligibilityMessage = "";
	String FixedCapping = "1000000";

	if ((final_grade.equalsIgnoreCase("A") || final_grade.equalsIgnoreCase("B")  ||
			final_grade.equalsIgnoreCase("C") || final_grade.equalsIgnoreCase("D") || final_grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) && capping <=1000000.00 ) {
		ListnerClass.reportLog("Capping = " + capping + " And " + "Grade =" + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");

		System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
		String exp = DataBaseUtility.ExecuteQuery(select_query);
	} else {
		ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
		System.out.println("Eligibility is not getting calculated properly..!!!");
	}	
	}
	/*
	 * When experiment is  Cautious Location, ABB,Policy Vivriti_Direct_TOPup
	 */
	@Test(dataProvider = "final_grade")
	public void TC_06(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_06 :"+" To verify that When experiment is  Cautious Location, ABB,Policy Vivriti_Direct_TOPup");

		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_abb_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String eligibilityMessage = "";
		String FixedCapping = "1500000";
		String FixedCapping1 = "1000000";


		switch (grade.toUpperCase()) {
		case "A":
		case "B":
			if (capping <= 150000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog(eligibilityMessage);
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		case "C":
		case "D":
		case "E":
		case "NA":
			if (capping <= 1000000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping1+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog(eligibilityMessage);
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		default:
			ListnerClass.reportLog("Invalid grade");
			System.out.println("Invalid grade");
		}
//		System.out.println("Capping = " + capping + " And " + "Grade =" + grade + " " + eligibilityMessage);
	}
	/*
	 * When experiment is  Cautious Location, BTO,Policy Vivriti_Direct_TOPup
	 */
	@Test(dataProvider = "final_grade")
	public void TC_07(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_07 :"+" When experiment is  Cautious Location, BTO,Policy Vivriti_Direct_TOPup");

	String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
	DataBaseUtility.executeUpdateQuery(query);

	String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(profile);

	HashMap hash= new HashMap();
	hash.put("loan_code", loancode);

	ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
	String retur=repo.extract().body().asPrettyString();

	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
	Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);
	
	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);	

	String eligibilityMessage = "";
	String FixedCapping = "1500000";

	if ((final_grade.equalsIgnoreCase("A") || final_grade.equalsIgnoreCase("B")  ||
			final_grade.equalsIgnoreCase("C") || final_grade.equalsIgnoreCase("D") || final_grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) && capping <=1500000.00 ) {
		ListnerClass.reportLog("Capping = " + capping + " And " + "Grade =" + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");

		System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
		String exp = DataBaseUtility.ExecuteQuery(select_query);
	} else {
		ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
		System.out.println("Eligibility is not getting calculated properly..!!!");
	}	
	}

	/*
	 * When experiment is Salaried, Policy Vivriti_Direct_TOPup
	 */

	@Test(dataProvider = "final_grade")
	public void TC_08(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_08 :"+" When experiment is Salaried, Policy Vivriti_Direct_TOPup");


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

		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
		double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);
		
		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String eligibilityMessage = "";
		String FixedCapping = "1000000";
		String FixedCapping1 = "800000";
		String FixedCapping2 = "600000";


		switch (grade.toUpperCase()) {
		case "A":
			if (capping <= 1000000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog(eligibilityMessage);
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		case "B":
			if (capping <= 800000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping1+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog(eligibilityMessage);
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		case "C":
		case "D":
		case "E":
		case "NA":
			if (capping <= 600000.00) {
				ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping2+ "  Eligibility is getting calculated properly..!!!");
				System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
				String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
				String exp = DataBaseUtility.ExecuteQuery(select_query);
			} else {
				ListnerClass.reportLog(eligibilityMessage);
				eligibilityMessage = "Eligibility is not getting calculated properly..!!!";
			}
			break;
		default:
			ListnerClass.reportLog("Invalid grade");
			System.out.println("Invalid grade");
		}
		System.out.println("Capping = " + capping + " And " + "Grade =" + grade + " " + eligibilityMessage);
	}
	/*
	 * When ODCC, Policy Vivriti_Direct_TOPup (only ABB)
	 * 
	 */
	@Test(dataProvider = "final_grade")

	public void TC_09(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_09 :"+" When ODCC, Policy Vivriti_Direct_TOPup (only ABB)");

		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_abb_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);
		
		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String eligibilityMessage = "";
		String FixedCapping = "1000000";

		if ((final_grade.equalsIgnoreCase("A") || final_grade.equalsIgnoreCase("B")  ||
				final_grade.equalsIgnoreCase("C") || final_grade.equalsIgnoreCase("D") || final_grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) && capping <=1000000.00 ) {
			ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
			System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
			String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
			String exp = DataBaseUtility.ExecuteQuery(select_query);
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}	

	}
	
	/*
	 *When Experiment is Saving Account, Policy Vivriti_Direct_TOPup
	 * 
	 */
	@Test(dataProvider = "final_grade")

	public void TC_10(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_10 :"+" To verify that When Experiment is Saving Account, Policy Vivriti_Direct_TOPup");

		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
		Double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);
		
		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String eligibilityMessage = "";
		String FixedCapping = "500000";

		if ((final_grade.equalsIgnoreCase("A") || final_grade.equalsIgnoreCase("B")  ||
				final_grade.equalsIgnoreCase("C") || final_grade.equalsIgnoreCase("D") || final_grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) && capping <=500000.00 ) {
			ListnerClass.reportLog("Capping = " + capping + " And " + "Grade = " + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
			System.out.println("Capping = " + capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
			String select_query = "select experiment_name,experiment_name from loan_application where code='" + loancode + "'";
			String exp = DataBaseUtility.ExecuteQuery(select_query);
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}	

	}
	
	/*
	 *Max capping where “no experiment” is applied (excl DSA,) where Business vintage is > 3 years & one property is owned	
	 * 
	 */
	@Test(dataProvider = "final_grade")

	public void TC_11(String final_grade) throws SQLException 
	{
		ListnerClass.reportLog("TC_11 :"+" To verify that Max capping where “no experiment” is applied (excl DSA,) where Business vintage is > 3 years & one property is owned");

		
		String query="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"' ";
		DataBaseUtility.executeUpdateQuery(query);

		String profile="update loan_application set experiment_name='CAUTIOUS_PROFILE_V2.0' ,  experiment_id='50' where code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(profile);

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

		String actual_capped_banking_eligibility_Value = ""+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
		double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);

		String actual_capped_abb_eligibility_Value = ""+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_abb_eligibility");
		double abb_capping= Double.parseDouble(actual_capped_abb_eligibility_Value);
		
		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String eligibilityMessage = "";
		String FixedCapping = "2000000";
		String FixedCapping1 = "1500000";

		
		//ABB Capping
		
		if (abb_capping <= 2000000.00 && (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") )) {
			ListnerClass.reportLog("ABB_Capping = " + abb_capping + " And " + "Grade =" + grade + " when fixed capping =" +FixedCapping+ "  Eligibility is getting calculated properly..!!!");
			System.out.println("ABB_Capping = " + abb_capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		}else if (abb_capping <= 1500000.00 && (grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D"))) {
			ListnerClass.reportLog("ABB_Capping = " + abb_capping + " And " + "Grade =" + grade + " when fixed capping =" +FixedCapping1+"  Eligibility is getting calculated properly..!!!");
			System.out.println("ABB_Capping = " + abb_capping + " And " + "Grade =" + grade + "  Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}
		
		//BTO Cappping
		
		if ((grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B")  ||
				grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D")) && BTO_Capping <= 2000000.00) {
			ListnerClass.reportLog("BTO Capping = " + BTO_Capping + " And Grade = " + grade + " when fixed capping =" +FixedCapping+ "Eligibility is getting calculated properly..!!!");
			System.out.println("BTO Capping = " + BTO_Capping + " And Grade = " + grade + "Eligibility is getting calculated properly..!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility is not getting calculated properly..!!!");
		}		
	}

/*
 * When experiment is Both Rented, Policy Vivriti_Direct_TOPup and account is NTC
 */

@Test(dataProvider = "final_grade")
public void TC_12(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_12 :"+" To verify that When experiment is Both Rented, Policy Vivriti_Direct_TOPup and account is NTC");

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

		
		String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
		double BTO_Capping= Double.parseDouble(actual_capped_banking_eligibility_Value);



		String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
		String grade=DataBaseUtility.ExecuteQuery(final_g);

		String cibi="select cibil_score from flexiloans_staging_db.loan_applicant_detail where loan_code='"+loancode+"'";
		String cibil_score=DataBaseUtility.ExecuteQuery(cibi);

		String query_2="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query_2);
		String FixedCapping = "500000";


		if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA")) 
		{
			boolean isEligible = (cibil_score.equals("-1") || cibil_score.equals("0") || cibil_score.equals("200") || cibil_score.equals("300")) && (BTO_Capping <= 500000.00);

			if (isEligible) {
				ListnerClass.reportLog("IF Risk_Garde IS '" + grade + "' And Cibil_Score IS '" + cibil_score + "' Experiment Is Both_Rented Then Final_BTO_Capping IS '" + BTO_Capping + "' when fixed capping = '"+FixedCapping+"' ... Eligibility Capping is calculated Properly..!!!!");
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
 * When experiment is Both Rented, Policy Vivriti_Direct_TOPup and account is Thin cibil
 */

@Test(dataProvider = "final_grade")
public void TC_13(String final_grade) throws SQLException 

{
	ListnerClass.reportLog("TC_13 :"+" To verify that When experiment is Both Rented, Policy Vivriti_Direct_TOPup and account is Thin cibil");

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
	

	String actual_capped_banking_eligibility_Value = " "+repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
	double capping= Double.parseDouble(actual_capped_banking_eligibility_Value);


	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);
	String FixedCapping = "500000";


	if (grade.equalsIgnoreCase("A") || grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") || grade.equalsIgnoreCase("NA") && (capping<=500000.00)) 
	{
		ListnerClass.reportLog("IF Risk_Garde IS '" + grade + " Than Final_BTO_Capping IS '" + capping + "' when fixed capping = '"+FixedCapping+"' ... Eligibility Capping is calculated Properly..!!!!");
		System.out.println("IF Risk_Garde IS '" + grade + " Than Final_BTO_Capping IS '" + capping + "' ... Eligibility Capping is calculated Properly..!!!!");
		} else {
			ListnerClass.reportLog("Eligibility is not getting calculated properly..!!!");
			System.out.println("Eligibility Capping is not getting calculated properly..!!!");
		}
	} 

	
/*
 * When experiment is Both Rented, Policy Vivriti_Direct_TOPup and account is ODCC
 */

@Test(dataProvider = "final_grade")
public void TC_14(String final_grade) throws SQLException 
{
	ListnerClass.reportLog("TC_14 :"+" To verify that When experiment is Both Rented, Policy Vivriti_Direct_TOPup and account is ODCC");

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
	
	String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.'Vivriti Capital'.VIVRITI_DIRECT_TOPUP[0].capped_banking_eligibility");
	double capping=Double.parseDouble(actual_capped_banking_eligibility_Value);

	String final_g="select final_grade from flexiloans_staging_db.risk_grading_final where loan_code='"+loancode+"'";
	String grade=DataBaseUtility.ExecuteQuery(final_g);
	String FixedCapping = "1000000";


	
	
	if(grade.equalsIgnoreCase("A") ||grade.equalsIgnoreCase("B") || grade.equalsIgnoreCase("C") || grade.equalsIgnoreCase("D") || grade.equalsIgnoreCase("E") && (capping <=1000000.00)) 
	{
		ListnerClass.reportLog( " Risk Grade Is '"+grade+"'  And Experiment Is Both Rented Then ODCC Capping Is '"+capping+"' when fixed capping = '"+FixedCapping+"'  ... Eligibility is getting calculated Properly.!!! ");
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



