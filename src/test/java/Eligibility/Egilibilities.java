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
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.Custom_Token;
import WorkFlowLibrary.DocumentUpload;
import io.restassured.response.ValidatableResponse;

public class Egilibilities {

	Policies poli = new Policies();
	Custom_Token token = new Custom_Token();
	DocumentUpload lp = new DocumentUpload();
	Upload_BS bs = new Upload_BS();
	Create_New_Loancode create= new Create_New_Loancode();
	String loancode;
	DataBaseUtility util = new DataBaseUtility();
	String url="https://console-staging.flexiloans.com/policy/check-policies/";
	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";
	String payout="https://console-staging.flexiloans.com/performanceData/";
	
	@BeforeClass
	public void BS() throws Throwable 
	{


		//loancode=create.IP_Qalified();
		loancode="63db901fs787n";
		bs.BS_Upload(loancode);

	}

	@Test
	public void ECOM_BCF_ABB() 
	{

		//String loancode="65434580o09dm";

		String query1="UPDATE `loan_application_metadata` SET `is_topup` = '0' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query1);
		String query2="UPDATE `loan_application` SET `partner_code` = '1c41176794537' WHERE (`code` = '"+loancode+"')";
		util.executeUpdateQuery(query2);
		String query3="UPDATE `loan_business_detail` SET `year_of_incorporation` = '2021' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query3);
		String query4="UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query4);
		String query5="INSERT INTO `risk_grading_final` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		util.executeUpdateQuery(query5);
		String query6="UPDATE bank_db_staging.bank_analysis_data_points SET `abb_emi_ratio` = '328000' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query6);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

	}

	@Test
	public void ECOM_BCF_ABB_ECOMMultiplier_NBECOMMultplier() throws Throwable 
	{


		String query1="UPDATE `loan_application_metadata` SET `is_topup` = '0' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query1);
		String query2="UPDATE `loan_application` SET `partner_code` = '1c41176794537' WHERE (`code` = '"+loancode+"')";
		util.executeUpdateQuery(query2);
		String query3="UPDATE `loan_business_detail` SET `year_of_incorporation` = '2021' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query3);
		String query4="UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query4);
		String query5="INSERT INTO `risk_grading_final` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		util.executeUpdateQuery(query5);
		String query6="UPDATE bank_db_staging.bank_analysis_data_points SET `abb_emi_ratio` = '328000' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query6);


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();


		String insert1="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`, `unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '1', '2020-01-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert1);	
		String insert2="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '2', '2020-02-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert2);	
		String insert3="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '3', '2020-03-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert3);	
		String insert4="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '4', '2020-04-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert4);	
		String insert5="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '5', '2020-05-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert5);	
		String insert6="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '6', '2020-06-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert6);	


		String Token=token.generateToken(ObjectReaders.readers.getRazor_pay_Authorization());
		HashMap  map = new HashMap();
		map.put("Accept", "application/json");	
		map.put("Content-Type", "application/x-www-form-urlencoded");		
		map.put("Authorization", Token);		

		ValidatableResponse respo = given().headers(map).when().get(payout+loancode+"/partner_payout_data_points").then().log().all();
		String response1 = respo.extract().body().asPrettyString();


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();


	}


	@Test
	public void BCF_ABB_Payout_Multplier() throws Throwable 
	{


		String query1="UPDATE `loan_application` SET `partner_code` = '5e70d0f77rpgm' WHERE (`code` = '"+loancode+"')";
		util.executeUpdateQuery(query1);
		String query2="UPDATE `loan_applicant_detail` SET `cibil_score` = '750' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query2);
		String query3="UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query3);
		String query4="INSERT INTO `flexiloans_staging_db`.`risk_grading` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		util.executeUpdateQuery(query4);
		String query5="UPDATE `loan_applicant_detail` SET `address_ownership_status` = 'owned' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query5);
		String query6=" UPDATE `loan_business_detail` SET `address_pin_code` = '792056' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query6);
		String query7="UPDATE `loan_business_detail` SET `address_ownership_status` = 'rented' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query7);


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();


		String insert1="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`, `unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '1', '2020-01-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert1);	
		String insert2="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '2', '2020-02-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert2);	
		String insert3="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '3', '2020-03-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert3);	
		String insert4="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '4', '2020-04-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert4);	
		String insert5="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '5', '2020-05-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert5);	
		String insert6="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '6', '2020-06-01','15000','2020',0,0,0)";
		util.executeUpdateQuery(insert6);	


		String Token=token.generateToken(ObjectReaders.readers.getRazor_pay_Authorization());
		HashMap  map = new HashMap();
		map.put("Accept", "application/json");	
		map.put("Content-Type", "application/x-www-form-urlencoded");		
		map.put("Authorization", Token);		

		ValidatableResponse respo = given().headers(map).when().get(payout+loancode+"/partner_payout_data_points").then().log().all();
		String response1 = respo.extract().body().asPrettyString();


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();


	}

	@Test
	public void BUREAU_ELIGIBILITY() throws SQLException, Throwable 
	{

		String query1="UPDATE `flexiloans_staging_db`.`risk_grading_final` SET `is_thick_cibil` = '0' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query1);
		String query2="INSERT INTO `flexiloans_staging_db`.`risk_grading_final` (`loan_code`, `final_grade`, `is_thick_cibil`) VALUES ('"+loancode+"', 'A', '1')";
		util.executeUpdateQuery(query2);
		String query3="UPDATE `flexiloans_staging_db`.`risk_grading` SET `final_grade` = 'B' WHERE  (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query3);
		String query4="INSERT INTO `flexiloans_staging_db`.`risk_grading` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		util.executeUpdateQuery(query4);
		String query5="UPDATE `flexiloans_staging_db`.`cre_rule_status` SET `cre_reject_status` = '0' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query5);
		String query6="INSERT INTO `flexiloans_staging_db`.`cre_rule_status` (`loan_code`, `cre_reject_status`,`uid`) VALUES ('"+loancode+"', '0','0')";
		util.executeUpdateQuery(query6);
		String query7="UPDATE `flexiloans_staging_db`.`loan_business_detail` SET `serviceable_flag` = '1' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query7);
		String query8="UPDATE `flexiloans_staging_db'.`loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query8);
		String query9="UPDATE `flexiloans_staging_db`.`loan_applicant_detail` SET `cibil_score` = '730' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query9);
		String query10="UPDATE `flexiloans_staging_db`.`loan_applicant_detail` SET `filled_by_experian` = '1', `experian_transaction_id` = '13' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query10);
		String query11="UPDATE `flexiloans_staging_db`.`loan_finance_detail` SET `monthly_total_sales` = '40000' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query11);
		//poli.AUTO_LENDING_MTO_POLICY();


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();
	}

	@Test
	public void BCF_ABB_POSPayout() throws Throwable 
	{


		String query1="UPDATE `loan_application` SET `partner_code` = '6108f2105hdc1' WHERE (`code` = '"+loancode+"')";
		util.executeUpdateQuery(query1);
		String query2="UPDATE `loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		util.executeUpdateQuery(query2);
		String query3="UPDATE loan_business_detail SET date_of_incorporation = '2020-01-01' WHERE loan_code = '"+loancode+"'";
		util.executeUpdateQuery(query3);



		String insert1="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`, `unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '1', '2020-01-01','20000','2020',0,0,0)";
		util.executeUpdateQuery(insert1);	
		String insert2="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '2', '2020-02-01','20000','2020',0,0,0)";
		util.executeUpdateQuery(insert2);	
		String insert3="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '3', '2020-03-01','20000','2020',0,0,0)";
		util.executeUpdateQuery(insert3);	
		String insert4="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '4', '2020-04-01','20000','2020',0,0,0)";
		util.executeUpdateQuery(insert4);	
		String insert5="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '5', '2020-05-01','20000','2020',0,0,0)";
		util.executeUpdateQuery(insert5);	
		String insert6="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '6', '2020-06-01','20000','2020',0,0,0)";
		util.executeUpdateQuery(insert6);	


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();


		String Token=token.generateToken(ObjectReaders.readers.getRazor_pay_Authorization());
		HashMap  map = new HashMap();
		map.put("Accept", "application/json");	
		map.put("Content-Type", "application/x-www-form-urlencoded");		
		map.put("Authorization", Token);		

		ValidatableResponse respo = given().headers(map).when().get(payout+loancode+"/partner_payout_data_points").then().log().all();
		String response1 = respo.extract().body().asPrettyString();


		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();

	}
	
	
	


}
