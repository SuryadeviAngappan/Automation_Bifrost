package Policy;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Report;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import BS_Upload.Upload_BS;
import GenericUtilities.BaseClass;
import GenericUtilities.Commanparameter;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import LoanCode_Generater.*;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.Custom_Token;
import WorkFlowLibrary.DocumentUpload;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import lombok.var;

public class Policies{

	Custom_Token token = new Custom_Token();
	WebDriverUtility wUtil= new WebDriverUtility();
	DocumentUpload upload = new DocumentUpload();
	Create_New_Loancode create= new Create_New_Loancode();
	DataBaseUtility dbUtil = new DataBaseUtility();
	JavaUtility jUtil = new JavaUtility();
	Upload_BS bs = new Upload_BS();
	String  loancode;
	String no;
	String url="https://console-staging.flexiloans.com/policy/check-policies/";
	String payout="https://console-staging.flexiloans.com/performanceData/";
	String eli="https://console-staging.flexiloans.com/eligibility/v2";
	String penyDrop="https://los-stage-ecs.flexiloans.com/api/pennydrop/";

	@BeforeClass
	public void DB_Connection() throws SQLException, Throwable 
	{
		dbUtil.connectToDB();
		Reporter.log("--- db conection successfull ---",true);

		//loancode =create.IP_Qalified();   
		loancode="65a8fcbbp2cah";
		String query10="UPDATE loan_finance_detail SET monthly_total_sales='200000' WHERE loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query10);
		String query11="UPDATE loan_application SET amount='199000' WHERE code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query11);
		String query12="UPDATE loan_applicant_detail SET is_salaried='0', name='"+ObjectReaders.readers.get_FullName()+"', email='"+jUtil.randomEmailId()+"', gender='Male', dob='1997-09-30', address_pincode='400001', address_state='Maharashtra', address_city='Mumbai', is_current_account_available='1', pan_no='BTLPN0219B', address_ownership_status='Owned' WHERE loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query12);
		bs.BS_Upload(loancode);
		String query=" select pan_no from flexiloans_staging_db.loan_applicant_detail where loan_code = '"+loancode+"'";
		String pancard=dbUtil.ExecuteQuery(query);
		String number=RandomStringUtils.randomNumeric(5);
		String ckyc_number="400000852037"+number;


		String query1="insert into flexiloans_staging_db.ckyc_transactions(search_key, search_key_type, search_dob,\n"
				+ "ckyc_number, full_name, age, record_creation_date, record_updation_date,\n"
				+ "identity_document_s3_bucket, identify_document_s3_key, identiy_document_ext, is_active,\n"
				+ "meta_reference, status)\n"
				+ "value(\n"
				+ "'"+pancard+"',\n"
				+ "PAN,\n"
				+ "11-11-1990,\n"
				+ "'"+ckyc_number+"',\n"
				+ "MRS NIKHAT ISAK SUTAR,\n"
				+ "24,\n"
				+ "02-12-2021,\n"
				+ "02-12-2021,\n"
				+ "flexiloans-ckyc-stage,\n"
				+ "images/9496b3b0-2610-11ed-acc5-f75ff7167fac.jpg,\n"
				+ "jpg\n"
				+ "1,\n"
				+ "{loanCode:'"+loancode+"'} ,\n"
			    + "SUCCESSFULLY_PULLED)";
		dbUtil.executeUpdateQuery(query1);


		String set ="%"+loancode+"%";
		String set1="%"+loancode+"%";
		String query2 ="select ckyc_transaction_id from flexiloans_staging_db.ckyc_transactions where meta_reference like '"+set+"' order by created_at desc limit 1";
		String CKYC_transaction_id =dbUtil.ExecuteQuery(query2);
		System.out.println("CKYC_transaction_id = "+CKYC_transaction_id);
		String query3="select ckyc_number from flexiloans_staging_db.ckyc_transactions where meta_reference like '"+set1+"' order by created_at desc limit 1";
		String CKYC_number =dbUtil.ExecuteQuery(query3);
		System.out.println("ckyc_number = "+CKYC_number);

		String query4="insert into flexiloans_staging_db.ckyc_records(ckyc_transaction_id, ckyc_number, constitution_type, account_holder_type, prefix, first_name, last_name, middle_name, full_name, maiden_prefix, maiden_first_name, maiden_full_name, father_spouse, father_prefix, father_first_name, father_full_name, mother_prefix, mothers_first_name, mother_full_name, applicant_gender, dob, permanent_line_1, permanent_line_2, permanent_line_3, permanent_city, permanent_district, permanent_state, permanent_country, permanent_pincode, permanent_poa, perm_corres_same, correspondence_line_1, correspondence_line_2, correspondence_line_3, correspondence_city, correspondence_district, correspondence_state, correspondence_country, correspondence_pincode, personal_mobile_isd_code, personal_mobile_number, email, declaration_date, declaration_place, kyc_verification_date, document_submitted_type, kyc_name, kyc_designation, kyc_branch, kyc_emp_code, organisation_name, organisation_code, no_identity_details, no_related_person, number_of_images, is_active)\n"
				+ "value(\n"
				+ "'"+CKYC_transaction_id+"',\n"
				+ "\"'"+CKYC_number+"'\",\n"
				+ "\"01\",\n"
				+ "\"01\",\n"
				+ "\"MRS\",\n"
				+ "\"NIKHAT\",\n"
				+ "\"SUTAR\",\n"
				+ "\"ISAK\",\n"
				+ "\"MRS NIKHAT ISAK SUTAR\",\n"
				+ "\"MS\",\n"
				+ "\"Isak sutar\",\n"
				+ "\"MS Isak sutar\",\n"
				+ "\"01\",\n"
				+ "\"MR\",\n"
				+ "\"ISMAIL MALI\",\n"
				+ "\"MR ISMAIL MALI\",\n"
				+ "\"MRS\",\n"
				+ "\"NOORJAH MALI\",\n"
				+ "\"MRS NOORJAH MALI\",\n"
				+ "\"F\",\n"
				+ "\"10-12-1997\",\n"
				+ "\"S NO 12 LAXMI NAGAR NEAR MORE\",\n"
				+ "\"STD\",\n"
				+ "\"PUNE CITY\",\n"
				+ "\"Pune\",\n"
				+ "\"Pune\",\n"
				+ "\"MH\",\n"
				+ "\"IN\",\n"
				+ "\"411006\",\n"
				+ "\"09\",\n"
				+ "\"Y\",\n"
				+ "\"S NO 12 LAXMI NAGAR NEAR MORE\",\n"
				+ "\"STD\",\n"
				+ "\"PUNE CITY\",\n"
				+ "\"Pune\",\n"
				+ "\"Pune\",\n"
				+ "\"MH\",\n"
				+ "\"IN\",\n"
				+ "\"411006\",\n"
				+ "\"91\",\n"
				+ "\"8007181853\",\n"
				+ "\"nikhatsutar38@gmail.com\",\n"
				+ "\"10-11-2021\",\n"
				+ "\"Wagholi\",\n"
				+ "\"********\",\n"
				+ "\"02\",\n"
				+ "\"********\",\n"
				+ "\"********\",\n"
				+ "\"********\",\n"
				+ "\"********\",\n"
				+ "\"Epimoney Private Limited\",\n"
				+ "\"IN4449\",\n"
				+ "\"2\",\n"
				+ "\"0\",\n"
				+ "\"2\",\n"
				+ "1\n"
				+ ")";
		dbUtil.executeUpdateQuery(query4);

		String query5="select ckyc_record_id from flexiloans_staging_db.ckyc_records where ckyc_transaction_id = '"+CKYC_transaction_id+"' order by created_at desc limit 1;";
		String CKYC_recordID=dbUtil.ExecuteQuery(query5);


		String query6="insert into flexiloans_staging_db.ckyc_documents(ckyc_record_id, sequence_number, image_type, image_code, document_s3_bucket, document_s3_key, is_active)\n"
				+ "value(\n"
				+ "'"+CKYC_recordID+"',\n"
				+ "\"1\",\n"
				+ "\"pdf\",\n"
				+ "\"36\",\n"
				+ "\"flexiloans-ckyc-stage\",\n"
				+ "\"images/9540fd70-2610-11ed-acc5-f75ff7167fac.pdf\",\n"
				+ "1\n"
				+ "),\n"
				+ "(\n"
				+ "'"+CKYC_recordID+"',\n"
				+ "\"2\",\n"
				+ "\"jpg\",\n"
				+ "\"02\",\n"
				+ "\"flexiloans-ckyc-stage\",\n"
				+ "\"images/95414b90-2610-11ed-acc5-f75ff7167fac.jpg\",\n"
				+ "1\n"
				+ ")";
		dbUtil.executeUpdateQuery(query6);

		String query7="update flexiloans_staging_db.loan_applicant_detail\n"
				+ "set ckyc_transaction_id = '"+CKYC_transaction_id+"'\n"
				+ "where loan_code = \"'"+loancode+"'\";\n"
				+ "";
		dbUtil.executeUpdateQuery(query7);

		String query8="update loan_application set credit_officer = 'Mayur P' where code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query8);

	}


	@Test()
	public void ABFL_Direct_Policy() throws Throwable // Done
	{

	 //  String loancode="6603cafd87gq8";
		String qu="UPDATE flexiloans_staging_db.loan_applicant_detail set uid='cdcd20fb-2019-4531-b1ab-ed607b71227b' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(qu);
		String query1="	 UPDATE `loan_application_metadata` SET `is_topup` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2  = "UPDATE `loan_application` SET `source_of_lead` = 'direct' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3 = " UPDATE `loan_application` SET `partner_code` = '5e217472x2wb1' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4 ="UPDATE loan_business_detail SET `year_of_incorporation` = '2021' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5= "UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eli).then().log().all();

		String retur=repo.extract().body().asPrettyString();
		System.out.println("Loancode= "+loancode);


	}

	@Test()
	public void POS_policy() throws SQLException  // Done
	{

		String query1="UPDATE `loan_application_metadata` SET `is_topup` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2 ="UPDATE `loan_application` SET `partner_code` = '5911b47caa701' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3 ="UPDATE `loan_business_detail` SET `year_of_incorporation` = '2021' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4 ="UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();	

	}


	@Test()
	public void CAUTIOUS_LOCATION() throws SQLException  // Done
	{


		String query1="UPDATE `loan_application` SET `source_of_lead` = 'partner' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE `loan_application` SET `partner_code` = '1c41176794537' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3="UPDATE `loan_business_detail` SET `address_pin_code` = '394220', `address_city` = 'Surat' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();	


	}

	@Test()
	public void AUTO_LENDING_MTO_POLICY() throws SQLException, Throwable  // Done
	{


		String query1="INSERT INTO flexiloans_staging_db.cre_rule_status (loan_code, cre_reject_status,uid) VALUES ('"+loancode+"', '0' , '0')";
		dbUtil.executeUpdateQuery(query1);	
		String query2=	"INSERT INTO `flexiloans_staging_db`.`risk_grading_final` (`loan_code`, `final_grade`, `is_thick_cibil`) VALUES ('"+loancode+"', 'A', '1')";
		dbUtil.executeUpdateQuery(query2);
		String query3="INSERT INTO `flexiloans_staging_db`.`risk_grading` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'c')";
		dbUtil.executeUpdateQuery(query3);
		String query7=	"UPDATE `flexiloans_staging_db`.`loan_business_detail` SET `serviceable_flag` = '1' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);
		String query8=	"UPDATE `flexiloans_staging_db`.`loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `flexiloans_staging_db`.`loan_applicant_detail` SET `cibil_score` = '730' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);
		String query10=	"UPDATE `flexiloans_staging_db`.`loan_applicant_detail` SET `filled_by_experian` = '1', `experian_transaction_id` = '13' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query10);
		String query11=	"UPDATE `flexiloans_staging_db`.`loan_finance_detail` SET `monthly_total_sales` = '40000' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query11);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();


	}


	@Test()
	public void FOODTECH_AUTO_LENDING() throws SQLException // Done
	{

		String query1="UPDATE `loan_application` SET `partner_code` = '5e70d0f77rpgm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);	
		String query2="UPDATE `loan_applicant_detail` SET `address_pincode` = '411052' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);	
		String query3="UPDATE `loan_applicant_detail` SET `cibil_score` = '750' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);	
		String query4="UPDATE `loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);	
		String query5="UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);	
		String query6= "INSERT INTO `flexiloans_staging_db`.`risk_grading` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		dbUtil.executeUpdateQuery(query6);	
		String query7= "UPDATE `loan_applicant_detail` SET `address_ownership_status` = 'owned' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);	
		String query8="UPDATE `loan_business_detail` SET `address_pin_code` = '400001' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query8);	


		String insert1="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`, `unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"' , 'payout', '1', '2020-01-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert1);	
		String insert2="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '2', '2020-02-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert2);	
		String insert3="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '3', '2020-03-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert3);	
		String insert4="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '4', '2020-04-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert4);	
		String insert5="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '5', '2020-05-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert5);	
		String insert6="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '6', '2020-06-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert6);	




		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}



	@Test()
	public void ABFL_ECOM(String loancode) throws SQLException  // Done
	{


		String query1= "UPDATE `loan_application_metadata` SET `is_topup` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2= "UPDATE `loan_application` SET `partner_code` = '1c41176794537' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3 ="UPDATE `loan_business_detail` SET `year_of_incorporation` = '2021' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4 ="UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}


	@Test()
	public void VIVRITI_ECOM_AUTO_LENDING() throws Throwable  // Done
	{

		String query1=" UPDATE `loan_application_metadata` SET `is_topup` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2=" UPDATE `loan_application` SET `partner_code` = '600c32152wnm3' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3=" UPDATE loan_business_detail SET `year_of_incorporation` = '2021' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4=" UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();
	}


	@Test()
	public void FOODTECH() throws Throwable  // done
	{


		String query1="UPDATE `loan_application` SET `partner_code` = '5e70d0f77rpgm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE `loan_applicant_detail` SET `address_pincode` = '382250' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3="UPDATE `loan_applicant_detail` SET `cibil_score` = '750' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4=" UPDATE `loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="INSERT INTO `risk_grading` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `loan_applicant_detail` SET `address_ownership_status` = 'rented' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);
		String query8="UPDATE `loan_business_detail` SET `address_pin_code` = '792056' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `loan_business_detail` SET `address_ownership_status` = 'rented' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);
		String query10="INSERT INTO bank_db_staging.bank_account_summary (loan_code, first_date, last_date,`bank_id`,`account_name`,`account_no` , `account_type` , `email`, `pan`, `mobile_no`, `landline`, `address`, `bank_address`,`vendor`, `duplicate_transactions` ) VALUES ('"+loancode+"', '2022-01-01', '2022-08-01',0,0,0,0,0,0,0,0,0,0,1,0)";
		dbUtil.executeUpdateQuery(query10);

		String insert1="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`, `unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '1', '2020-01-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert1);	
		String insert2="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '2', '2020-02-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert2);	
		String insert3="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '3', '2020-03-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert3);	
		String insert4="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '4', '2020-04-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert4);	
		String insert5="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '5', '2020-05-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert5);	
		String insert6="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '6', '2020-06-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert6);	


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}



	@Test()
	public void VIVRITI_POS() throws SQLException  // Done
	{

		String query1="UPDATE `loan_application` SET `partner_code` = '59002aaed7a40' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE `loan_applicant_detail` SET `cibil_score` = '730' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3="UPDATE `loan_business_detail` SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="INSERT INTO bank_db_staging.bank_account_summary (loan_code, first_date, last_date,`bank_id`,`account_name`,`account_no` , `account_type` , `email`, `pan`, `mobile_no`, `landline`, `address`, `bank_address`,`vendor`, `duplicate_transactions` ) VALUES ('"+loancode+"', '2022-01-01', '2022-08-01',0,0,0,0,0,0,0,0,0,0,1,0)";
		dbUtil.executeUpdateQuery(query4);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}


	@Test()
	public void VIVRITI_DIRECT() throws Throwable  // done
	{

	//dbUtil.connectToDB();
    //String loancode="658d1933dzqm9";
		
		String query1="UPDATE flexiloans_staging_db.loan_application SET `partner_code` = '5e217472x2wb1' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE flexiloans_staging_db.loan_applicant_detail SET `cibil_score` = '730' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3=" UPDATE flexiloans_staging_db.loan_business_detail SET `is_rco` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="INSERT INTO bank_db_staging.bank_account_summary (loan_code, first_date, last_date,`bank_id`,`account_name`,`account_no` , `account_type` , `email`, `pan`, `mobile_no`, `landline`, `address`, `bank_address`,`vendor`, `duplicate_transactions` ) VALUES ('"+loancode+"', '2022-01-01', '2022-08-01',0,0,0,0,0,0,0,0,0,0,1,0)";
		//dbUtil.executeUpdateQuery(query4);

		Thread.sleep(5000);
		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();
	}


	@Test()
	public void VIVRITI_DELINQUENT_PINCODE() 
	{


		String query1 = "UPDATE `loan_application` SET `partner_code` = '5969727576129' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2 = "INSERT INTO `risk_grading` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		dbUtil.executeUpdateQuery(query2);
		String query3 = "INSERT INTO `risk_grading_final` (`loan_code`, `final_grade`) VALUES ('"+loancode+"','A')";
		dbUtil.executeUpdateQuery(query3);
		String query4 = "UPDATE loan_business_detail SET date_of_incorporation = '2020-01-01' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5 = " INSERT INTO `cre_rule_status` (`loan_code`,`cre_reject_status`, `uid`) VALUES ('"+loancode+"','0','0')";
		dbUtil.executeUpdateQuery(query5);
		String query6 = "UPDATE `loan_applicant_detail` SET `cibil_score` = '750' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7 = "UPDATE `loan_business_detail` SET `year_of_incorporation` = '2020' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);
		String query8 = "UPDATE `loan_business_detail` SET `loan_product_profile` = 'DOABLE' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query8);
		String query9 = "UPDATE `loan_business_detail` SET `address_pin_code` = '382250' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);
		String query10="INSERT INTO bank_db_staging.bank_account_summary (loan_code, first_date, last_date,`bank_id`,`account_name`,`account_no` , `account_type` , `email`, `pan`, `mobile_no`, `landline`, `address`, `bank_address`,`vendor`, `duplicate_transactions` ) VALUES ('"+loancode+"', '2022-01-01', '2022-08-01',0,0,0,0,0,0,0,0,0,0,1,0)";
		dbUtil.executeUpdateQuery(query10);
		String query11 = "INSERT INTO bank_db_staging.bank_analysis_data_points (`loan_code`, `bre_reject`) VALUES ('"+loancode+"', '0')";
		dbUtil.executeUpdateQuery(query11);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();


	}

	@Test()
	public void ECOM_AUTO_LENDING()  // WTD
	{

		String query1 ="UPDATE `loan_application` SET `source_of_lead` = 'partner' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2 ="UPDATE `loan_application` SET `partner_code` = 'f2b0e8972c476' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3 ="UPDATE `loan_business_detail` SET `address_pin_code` = '382250' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4 ="UPDATE loan_business_detail SET date_of_incorporation = '2020-01-01' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5 ="INSERT INTO `cre_rule_status` (`loan_code`,`cre_reject_status`,`uid`) VALUES ('"+loancode+"','0','0');";
		dbUtil.executeUpdateQuery(query5);
		String query6 ="UPDATE `loan_applicant_detail` SET `cibil_score` = '730' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7 ="INSERT INTO `risk_grading` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		dbUtil.executeUpdateQuery(query7);
		String query8 =" UPDATE `loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query8);



		String insert1="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`, `unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '1', '2020-01-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert1);	
		String insert2="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '2', '2020-02-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert2);	
		String insert3="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '3', '2020-03-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert3);	
		String insert4="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '4', '2020-04-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert4);	
		String insert5="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '5', '2020-05-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert5);	
		String insert6="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '6', '2020-06-01','15000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert6);	



		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();
	}

	@Test()
	public void Cautious_Profile() 
	{
		String query1="UPDATE  loan_business_detail SET loan_product_profile = 'Cautious'  WHERE  loan_code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE  loan_application SET experiment_id = '50'  WHERE  code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query3="UPDATE  loan_application SET partner_code  = '9c7bfcd45af46'  WHERE  code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE loan_business_detail SET date_of_incorporation = '2021-01-01' WHERE loan_code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query4);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();
	}


	@Test()
	public void Credit_Experiment_V1() 
	{

		String query1="UPDATE  loan_business_detail SET loan_product_profile = 'TOXIC'  WHERE  loan_code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE  loan_application SET partner_code  = '9c7bfcd45af46' WHERE  code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}


	@Test()
	public void VIVRITI_ECOM() 
	{

		String query1="INSERT INTO bank_db_staging.bank_account_summary (loan_code, first_date, last_date,`bank_id`,`account_name`,`account_no` , `account_type` , `email`, `pan`, `mobile_no`, `landline`, `address`, `bank_address`,`vendor`, `duplicate_transactions` ) VALUES ('"+loancode+"', '2022-01-01', '2022-08-01',0,0,0,0,0,0,0,0,0,0,1,0)";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE  loan_application SET partner_code  = 'f2b0e8972c476'  WHERE ( code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3=" UPDATE `loan_application_metadata` SET `is_topup` = '0' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE loan_business_detail SET date_of_incorporation = '2021-01-01' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE loan_applicant_detail SET cibil_score = '720' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE loan_business_detail SET is_rco='0'  WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();


	}

	@Test ()
	public void ECOM_DIRECT() 
	{

		String query1="INSERT INTO `bank_account_summary` (`loan_code`, `first_date`, `last_date`) VALUES ('"+loancode+"', '2022-01-01', '2022-08-01')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE bank_analysis_data_points SET bre_reject='0'  WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3="UPDATE  loan_application SET partner_code  = '5c80cc132ptaa'  WHERE ( code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE  loan_applicant_detail  SET serviceable_flag  = '1'  WHERE  (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE  pincode_profilel  SET collection_pincode_profile  = 'DOABLE'  WHERE ( pincode='440015')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE  loan_applicant_detail SET dob  = '1998-01-01'  WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7="UPDATE loan_business_detail SET date_of_incorporation = '2021-01-01' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE loan_business_detail SET date_of_incorporation = '2021-01-01' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE cre_rule_status SET cre_reject_status = '0' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);
		String query10=" UPDATE loan_business_detail SET loan_product_profile = 'Doable' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query10);
		String query11="UPDATE loan_business_detail SET is_rco= '1' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query11);
		String query12="UPDATE cre_rule_status SET cre_reject_status= '0' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query12);
		String query13="UPDATE loan_applicant_detail SET cibil_score= '750' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query13);



		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();



	}


	@Test()
	public void RENTED_RCO() 
	{

		String query1="UPDATE  loan_application SET partner_code  = '1c41176794537'  WHERE ( code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE loan_business_detail SET date_of_incorporation  = '2021-01-01' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3=" UPDATE loan_business_detail SET is_rco= '1' WHERE ( loan_code = '"+loancode+"')";		
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE loan_business_detail SET address_ownership_status = 'rented' WHERE ( loan_code = '"+loancode+"') ";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE loan_applicant_detail SET address_ownership_status = 'rented' WHERE ( loan_code = '"+loancode+"') ";
		dbUtil.executeUpdateQuery(query5);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}


	@Test()
	public void RENTED_DIRECT() 
	{

		String query1="UPDATE  loan_application SET partner_code  = '00aee70bc3840'  WHERE ( code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE loan_business_detail SET date_of_incorporation = '2021-01-01' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3="UPDATE loan_business_detail SET address_ownership_status= 'rented' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE loan_applicant_detail SET address_ownership_status = 'rented' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}


	@Test()
	public void ECOM_AUTO_LENDING_ACCEPTED() //WTD
	{


		String query1=" UPDATE loan_application_metadata  SET approval_policy = 'ECOM_AUTO_LENDING' WHERE loan_code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query1);


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}

	@Test()
	public void CAUTIOUS_LOCATION_DIRECT_POS()  //done
	{

		String query1="  UPDATE  loan_application SET partner_code  = '00aee70bc3840' WHERE  code ='"+loancode+"' ";
		dbUtil.executeUpdateQuery(query1);
		String query2=" UPDATE loan_business_detail SET date_of_incorporation = '2021-01-01' WHERE loan_code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query3=" UPDATE loan_business_detail SET address_state = 'BIHAR' WHERE loan_code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE loan_business_detail SET address_city = 'Hyderabad' WHERE loan_code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE  loan_application SET experiment_id  = '74' WHERE  code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query5);


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}

	@Test()
	public void FULLERTON_ECOM() 
	{


		String query1="UPDATE `loan_application` SET `source_of_lead` = 'partner' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE `loan_application` SET `partner_code` = 'f2b0e8972c476' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3="UPDATE `loan_applicant_detail` SET `dob` = '1972-11-16' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);



		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();



	}

	@Test()
	public void RAZORPAY_AUTO_LENDING() throws Throwable 
	{
		String query1="UPDATE `loan_application` SET `partner_code` = '6108f2105hdc1' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE `loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3="UPDATE loan_business_detail SET date_of_incorporation = '2020-01-01' WHERE ( loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);



		String insert1="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`, `unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '1', '2020-01-01','21000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert1);	
		String insert2="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '2', '2020-02-01','21000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert2);	
		String insert3="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '3', '2020-03-01','21000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert3);	
		String insert4="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '4', '2020-04-01','21000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert4);	
		String insert5="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '5', '2020-05-01','21000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert5);	
		String insert6="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '6', '2020-06-01','21000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert6);	



		String Token=token.generateToken(ObjectReaders.readers.getRazor_pay_Authorization());

		HashMap  map = new HashMap();
		map.put("Accept", "application/json");	
		map.put("Content-Type", "application/x-www-form-urlencoded");		
		map.put("Authorization", Token);		

		ValidatableResponse respo = given().headers(map).when().get(payout+loancode+"/partner_payout_data_points").then().log().all();
		String response1 = respo.extract().body().asPrettyString();


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();




	}


	@Test()
	public void AFFILIATE_CONVERSION() 
	{

		String query1="UPDATE `loan_business_detail` SET `has_gst_registration` = '1' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);	
		String query2=" UPDATE `loan_application` SET `application_status` = 'IP_QUALIFIED_FORM_PENDING' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3="Update loan_application set campaign_id ='588b47badcdcf' where code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query3);


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();
	}


	@Test()
	public void Gpay_Non_Banking_V1() 
	{

		String query="UPDATE `loan_application_metadata` SET `approval_policy` = 'POS_AUTO_LENDING'  WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();
	}


	@Test()
	public void JUMBOTAIL_AUTO_LENDING() throws Throwable 
	{


		String query1="UPDATE `loan_applicant_detail` SET `cibil_score` = '700' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="UPDATE `loan_application` SET `partner_code` = '5e1c45a4aqkwt' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3="UPDATE `loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);


		String insert1="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`, `unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '1', '2020-01-01','30000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert1);	
		String insert2="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '2', '2020-02-01','30000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert2);	
		String insert3="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '3', '2020-03-01','30000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert3);	
		String insert4="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '4', '2020-04-01','30000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert4);	
		String insert5="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '5', '2020-05-01','30000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert5);	
		String insert6="INSERT INTO `loan_merchant_performance_detail` (`loan_code`, `type`, `month`, `txn_date`,`value`,`year`,`unit`,`merchant_id`,`created_at`) VALUES ('"+loancode+"', 'payout', '6', '2020-06-01','30000','2020',0,0,0)";
		dbUtil.executeUpdateQuery(insert6);	



		String Token=token.generateToken(ObjectReaders.readers.getRazor_pay_Authorization());

		HashMap  map = new HashMap();
		map.put("Accept", "application/json");	
		map.put("Content-Type", "application/x-www-form-urlencoded");		
		map.put("Authorization", Token);		

		ValidatableResponse respo = given().headers(map).when().get(payout+loancode+"/partner_payout_data_points").then().log().all();
		String response1 = respo.extract().body().asPrettyString();


		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}

	@Test()
	public void POS_AUTO_LENDING() 
	{

		String query1=" INSERT INTO `risk_grading` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		dbUtil.executeUpdateQuery(query1);
		String query2=" UPDATE `loan_application` SET `partner_code` = '5e58f868xj4i2' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3=" UPDATE `loan_applicant_detail` SET `cibil_score` = '750' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4=" UPDATE `loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}


	@Test()
	public void FULLERTON_ECOM_AUTO_LENDING() 
	{
		String query1=" UPDATE `loan_application` SET `source_of_lead` = 'partner' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2=" UPDATE `loan_application` SET `partner_code` = 'f2b0e8972c476' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query2);
		String query3=" UPDATE `loan_applicant_detail` SET `cibil_score` = '750' WHERE (`loan_code` = '"+loancode+"')";	
		dbUtil.executeUpdateQuery(query3);
		String query4=" UPDATE `loan_business_detail` SET `product_service` = '[{\\\"type\\\":\\\"INDUSTRYPRODUCT\\\",\\\"products\\\":[{\\\"id\\\":5,\\\"margin\\\":5,\\\"table_id\\\":15,\\\"serviceability\\\":\\\"DOABLE\\\",\\\"product_service\\\":\\\"Groceries & Related Products\\\",\\\"industry_category\\\":\\\"FMCG\\\",\\\"nature_of_business\\\":\\\"Manufacturer\\\"}]}]' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="INSERT INTO `risk_grading` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `loan_business_detail` SET `legal_status` = 'proprietorship' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);

		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();

	}

	

	@Test(/*dependsOnMethods = "VIVRITI_DIRECT"*/)
	public void Add_PD() throws Throwable { 
		
		//dbUtil.connectToDB();
	   //  String loancode="66348e69x1pkj";
		
		String name= ObjectReaders.readers.get_FullName();
		String bname="Name at bank -"+name;


		Date dateWithoutTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String date=sdf.format(dateWithoutTime);
		String current_date=date.replaceAll("'", "");
		
		//String string="{\"loanCode\":\""+loancode+"\",\"updatedBy\":\"Vikram Jori\",\"updatedById\":923837,\"isSaved\":true,\"business\":{\"businessName\":\"Tots & Tots\",\"doc\":\"\",\"legalStatus\":\"Proprietorship\",\"natureBusiness\":\"Retailer\",\"yearBusiness\":5,\"platform\":\"PaisaBazaar\",\"noEmployee\":null,\"employeeCost\":\"\",\"gstin\":\"\",\"cin\":\"\",\"vat\":\"\",\"tin\":\"\",\"listElement\":\"\",\"listTypeElement\":\"\",\"industryListDetails\":[{\"listItem\":\"\",\"listType\":\"\",\"margin\":\"10.00\",\"nature_of_business\":\"Wholesaler\",\"id\":391}],\"salesTurnover\":\"\",\"monthlyPurchase\":\"\",\"profitMargin\":\"1000.00\",\"addBusinessDetails\":\"\",\"address\":\"\",\"pincode\":\"411033\",\"city\":\"Pune\",\"state\":\"Maharashtra\",\"cityServiceableFlag\":\"\",\"pincodeServiceableFlag\":\"1\",\"BAOwnership\":\"Owned\",\"vintage\":0,\"businessPan\":\"\",\"sellingProduct\":\"Accessories\",\"otherSellingProduct\":\"\",\"partnerDetails\":[],\"businessPremises\":[],\"premisesRelationshipType\":\"Father\",\"cgtsmeDetails\":{\"cgtsmeApplicable\":false,\"cgtsmeDocument\":\"\",\"uamNumber\":\"\",\"plDocument\":\"\",\"gstDocument\":\"\",\"noDocument\":\"\",\"remark\":\"\",\"turnover\":\"\",\"turnoverBand\":\"\",\"investmentInPlantAndMachinery\":\"\",\"investmentInPlantAndMachineryBand\":\"\",\"noOfEmployees\":\"\",\"noOfEmployeesBand\":\"\",\"yearOfDeclaration\":\"\",\"msmeCategory\":\"\"},\"businessAddress\":\" line 1 line 2 \"},\"workingCapital\":{\"tradeReceivables\":500000,\"tradePayables\":30000,\"inventoryValue\":120000,\"workingCapitalBlocked\":\"\",\"liability\":{\"input\":\"\"},\"majorClients\":\"\",\"majorSuppliers\":\"\",\"tradeCycle\":\"\",\"creditPeriodCreditor\":\"\",\"creditPeriodDebtors\":\"\",\"debtorsDays\":38.020833333333336,\"creditorDays\":983.2974137931035,\"inventoryDay\":3933.189655172414,\"majorClient\":[{\"name\":\"\",\"phoneNo\":\"\"},{\"name\":\"\",\"phoneNo\":\"\"}],\"majorSupplier\":[{\"name\":\"\",\"phoneNo\":\"\"},{\"name\":\"\",\"phoneNo\":\"\"}],\"netWorkingCapitalDeployed\":590000,\"ccOd\":\"\",\"workingCapitalCycle\":2988,\"remark\":\"\"},\"bankDetails\":{\"bto\":\"\",\"bankDetail\":[{\"bankName\":\"Union Bank Of India\",\"accNo\":\"576201010050736\",\"accType\":\"CURRENT\",\"ocbr\":0,\"icbr\":0,\"bankId\":59,\"medianBto\":300000,\"medianBalance\":300000,\"monthlyEmiBank\":\"\",\"isPrimary\":true,\"IFSCcode\":\"UBIN0556912\",\"isPrefilled\":1,\"isPennydrop\":true,\"pennydropError\":false,\"pennydropMessage\":\"Bank details validated successfully\",\"pennydropBankName\":\"Name at bank - Karthik Elayaperumal\",\"pennydropDisable\":true}],\"medianBalance\":\"\",\"medianBto\":\"\",\"ocbrConsolidated\":\"\",\"monthlyEmiBank\":\"\"},\"personalDetails\":{\"name\":\"Karthik Muttu Elayaperumal\",\"dob\":\"06-05-1995\",\"mobileNo\":6374567819,\"alternateMobileNo\":7912718289,\"email\":\"vikramj29797@gmail.com\",\"gender\":\"Male\",\"education\":\"Graduate\",\"maritalStatus\":\"Married\",\"shareholding\":\"\",\"address\":\"null Line1 Address Line2 Address\",\"pincode\":411033,\"city\":\"Pune\",\"state\":\"Maharashtra\",\"cityServiceableFlag\":\"1\",\"pincodeServiceableFlag\":\"1\",\"ownership\":\"Rented\",\"vintage\":4,\"relationshipType\":\"\",\"familyMember\":\"\",\"earningMember\":\"\",\"dependent\":\"\",\"aboutFamily\":\"\",\"assets\":[{\"asset\":\"Residence\",\"assetOwnership\":\"\",\"assetValue\":\"\",\"assetAddress\":\"null Line1 Address Line2 Address\"}],\"pan\":\"AYXPS3105K\",\"addressProof\":\"\",\"cibilScore\":825,\"trueScore\":0,\"kyc\":[{\"kycType\":\"\",\"kycInput\":\"\"}]},\"gaurantor\":{\"gaurantorInfo\":[{\"name\":\"Prem Sharma\",\"dob\":\"12-05-1993\",\"mobileNo\":\"9970235095\",\"alternateMobileNo\":\"\",\"email\":\"prem6776@gmail.com\",\"gender\":\"Male\",\"education\":\"\",\"maritalStatus\":\"\",\"address\":\"Pune\",\"occupation\":\"\",\"pan\":\"CXJPM6652A\",\"cibilScore\":\"\",\"trueScore\":\"\",\"pincode\":\"411033\",\"checked\":0,\"city\":\"Pune\",\"state\":\"Maharashtra\",\"kycID\":null,\"documentType\":null,\"relationship\":\"Partner\",\"relatedTo\":\"Karthik Elayaperumal\",\"cityServiceableFlag\":\"1\",\"pincodeServiceableFlag\":\"1\",\"code\":\"6350cb99hjmgn\",\"fathersName\":\"jhgasda\",\"isGuaCoappMobileNoSame\":false,\"isGuaCoappDuplicateMobileNo\":false,\"isApplicationMobileNoSame\":false,\"isGuarantorPANSame\":false,\"isGuarantorDuplicatePAN\":false,\"isApplicationPANSame\":false}]},\"coapplicant\":{\"coapplicantInfo\":[]},\"reference\":{\"creditorInfo\":[{\"name\":\"vmsdfs\",\"mobileNo\":2119182912,\"relation\":\"sis\",\"years\":2,\"occupation\":null,\"relationship\":\"\",\"referenceType\":\"Customer Declared\",\"verified\":0},{\"name\":\"dasacac\",\"mobileNo\":8319182912,\"relation\":\"bro\",\"years\":2,\"occupation\":null,\"relationship\":\"\",\"referenceType\":\"Customer Declared\",\"verified\":0}],\"coapplicantReq\":0,\"gaurantorReq\":1},\"financeDetails\":{\"annualBto\":\"\",\"avgMonthlyBto\":\"\",\"avgMonthlyBtoOnline\":\"\",\"avgMonthlyBtoOffline\":\"\",\"avgMonthlyBtoGst\":\"\",\"netProfitMarginPercentage\":1000,\"gstField\":\"\",\"auditedMonthlySales\":\"\",\"monthlyBto\":400000,\"monthlyBtoGst\":\"\",\"customerDeclared\":10212,\"purchase\":928.3636363636364,\"industryMargin\":\"\",\"businessMargin\":\"10.00\",\"creditMargin\":0,\"cashMargin\":0,\"saleType\":\"\",\"btoPercentage\":100,\"saleValue\":400000},\"caseSummary\":{\"responseLeverageScore\":[],\"caseStrengths\":[{\"strength\":[{\"label\":\"PD Comfort\",\"code\":\"APPROVED_HIGH_PD_COMFORT\",\"checked\":true,\"data\":[{\"code\":\"APPROVED_HIGH_PD_COMFORT_BUSINESS_STABILITY\",\"desc\":\"Business stability\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_MULTIPLE_SOURCES_OF_INCOME\",\"desc\":\"Multiple sources of income\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_OTHER_PHYSICAL_PD_COMFORT\",\"desc\":\"Other physical PD comfort\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_POSITIVE_REFERENCE_CHECK\",\"desc\":\"Positive reference checks\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_SOUND_BUSINESS_KNOWLEDGE_QUALIFIED_MANAGEMENT\",\"desc\":\"Sound business knowledge / Qualified Management\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_STRONG_ASSET_BASE_PER_DISCUSSION\",\"desc\":\"Strong asset base per discussion\",\"checked\":true}]},{\"label\":\"BRC/Track Record Comfort\",\"code\":\"APPROVED_BRC_TRACK_RECORD_COMFORT\",\"checked\":false,\"data\":[{\"code\":\"APPROVED_BRC_TRACK_RECORD_COMFORT_CIBIL_SCORE\",\"desc\":\"CIBIL Score\"},{\"code\":\"APPROVED_BRC_TRACK_RECORD_COMFORT_HISTORICALLY_CLEAN_ALL_LOAN_TRACKS\",\"desc\":\"Historically clean all Loan tracks\"},{\"code\":\"APPROVED_BRC_TRACK_RECORD_COMFORT_HISTORICALLY_CLEAN_UNSECURED_LOAN_TRACK\",\"desc\":\"Historically clean Unsecured Loan tracks\"}]},{\"label\":\"Strong Financials\",\"code\":\"APPROVED_STRONG_FINANCIALS\",\"checked\":false},{\"label\":\"Banking Comfort\",\"code\":\"APPROVED_BANKING_COMFORT\",\"checked\":false},{\"label\":\"Existing Flexiloans Client\",\"code\":\"APPROVED_EXISTING_FL_CLIENT_COMFORT\",\"checked\":false},{\"label\":\"ESCROW/Other Comfort\",\"code\":\"APPROVED_ESCROW_OTHER_COMFORTS\",\"checked\":false},{\"label\":\"Others\",\"code\":\"APPROVED_OTHERS\",\"checked\":false}],\"remarks\":\"mnbznmcbz\"}],\"caseConcerns\":[{\"remark\":\"\",\"concern\":[{\"label\":\"Over Leverage\",\"checked\":true},{\"label\":\"Weak/ Unproven Loan Track\",\"checked\":false},{\"label\":\"Recent Business decline\",\"checked\":false},{\"label\":\"Others\",\"checked\":false},{\"label\":\"High cash deposits\",\"checked\":false},{\"label\":\"NTC\",\"checked\":false},{\"label\":\"No unsecured loan track\",\"checked\":false},{\"label\":\"No stock\",\"checked\":false},{\"label\":\"Poor business set up\",\"checked\":false},{\"label\":\"Stretched Working capital cycle\",\"checked\":false},{\"label\":\"Low margin business\",\"checked\":false},{\"label\":\"No ownership of asset\",\"checked\":false}]}],\"sanctionConditions\":[{\"remarks\":\"bjsjcbs\",\"condition\":[{\"label\":\"CPV Required\",\"data\":[{\"label\":\"Residential\",\"checked\":true,\"data\":[{\"label\":\"null Line1 Address Line2 Address Pune Maharashtra 411033\",\"checked\":true}]},{\"label\":\"Office\",\"checked\":true,\"data\":[{\"label\":\" line 1 line 2  Pune Maharashtra 411033\",\"checked\":true}]}],\"checked\":true},{\"label\":\"GST returns for\",\"gstReturnFor\":\"\",\"checked\":false},{\"label\":\"One reference of relative and one reference of buyer/supplier\",\"checked\":false},{\"label\":\"Choose Guarantor\",\"checked\":false},{\"label\":\"Others\",\"checked\":false},{\"label\":\"Choose Co-applicant\",\"checked\":false},{\"label\":\"Ownership Proof Required\",\"data\":[{\"label\":\"Residential\",\"checked\":false},{\"label\":\"Business\",\"checked\":false}],\"checked\":false}]}],\"noCondition\":1,\"caseReferences\":[{\"name\":\"\",\"email\":\"\",\"mobileNo\":\"\",\"address\":\"\",\"relationship\":\"\"},{\"name\":\"\",\"email\":\"\",\"mobileNo\":\"\",\"address\":\"\",\"relationship\":\"\"}],\"approvalStatus\":\"\",\"approvalStatusReasonData\":[],\"eligibilityMethod\":\"BANKING_ABB\",\"policy\":\"VIVRITI_DIRECT\",\"finalEligibilityMethodAmount\":\"\"},\"sanctionInformation\":{\"product\":\"VCFL\",\"proposedAmount\":160000,\"proposedROI\":1.3,\"interestType\":\"Flat\",\"proposedTenure\":24,\"expectedMontlyEMI\":8747,\"CPAOfficer\":\"Mayur P\",\"PDOfficer\":\"Mayur P\",\"approvalDate\":\"20-10-2022\",\"disbursalDate\":\"23-10-2022\",\"interestedStartDate\":\"01-11-2022\",\"EMIStartDate\":\"01-12-2022\",\"pfPercentage\":\"\",\"differentialInterest\":0,\"declarationStatus\":1,\"insuranceApplied\":0,\"amountPercentage\":100,\"insuranceAmount\":0,\"partnerInsurance\":[],\"applyInsurance\":false,\"approverName\":\"\",\"insuranceWaiveRemark\":\"\",\"insuranceRemark\":\"\",\"applyDocumentCharges\":true,\"docCharge\":0,\"isEscrow\":false,\"interState\":false,\"intraState\":true,\"productCode\":\"\",\"processingFee\":9000,\"processingPercentage\":3,\"stampDutyPercentage\":0.2,\"stampDutyFee\":320,\"totalCharges\":9320,\"netDisbursalAmount\":150680,\"posSplitPercentage\":\"\",\"posSplitAmount\":\"\",\"otherRemarks\":\"\",\"differentialInterestCheck\":true,\"posSplitCheck\":false,\"posSplitAmountCheck\":false,\"POScharges\":0,\"posCheck\":false,\"escrowPartnerName\":\"\",\"prepaymentType\":\"normal\",\"prepaymentPercent\":5,\"insurerId\":0,\"reducingRate\":27.56,\"totalOutstanding\":0},\"loanInfo\":{\"endUsageLoan\":\"Working Capital\",\"monthlyObligation\":1200,\"activeLoans\":0,\"otherObligation\":\"\",\"partnerObligation\":[],\"remarks\":\"\"},\"propertyDetails\":{\"type_oyo_model\":{\"status\":1},\"min_guarantee\":{\"status\":1},\"premises\":{\"status\":1},\"ov_business\":{\"status\":1},\"ov_property\":{\"status\":1},\"lease_date\":{\"status\":1},\"rel_oyo\":{\"status\":1},\"rooms\":{\"status\":1},\"rooms_oyo\":{\"status\":1},\"occupancy\":{\"status\":1},\"est_occupancy\":{\"status\":1},\"cur_arr\":{\"status\":1},\"avg_arr\":{\"status\":1},\"facilities\":{\"status\":1},\"peak\":{\"status\":1},\"comptetitors\":{\"status\":1},\"proximity\":{\"status\":1},\"past_exp\":{\"status\":1},\"other_prop\":{\"status\":1},\"audit\":{\"status\":1},\"major_exp\":{\"status\":1},\"revenue\":{\"status\":1},\"currentBusiness\":{\"status\":1},\"rating\":{\"status\":1},\"walkinRevenue\":{\"status\":1},\"otherRevenue\":{\"status\":1},\"pd\":{\"status\":1},\"rent\":{\"status\":1},\"deposit\":{\"status\":1},\"relationship\":{\"status\":1},\"cost\":{\"status\":1}}}";
		   String string="  { \"loanCode\": \""+loancode+"\",\n"
        		+ "    \"updatedBy\": \"Mahesh Narvane\",\n"
        		+ "    \"updatedById\": 923809,\n"
        		+ "    \"isSaved\": true,\n"
        		+ "    \"business\": {\n"
        		+ "      \"businessName\": \"MNR GALAXY\",\n"
        		+ "      \"doc\": \"\",\n"
        		+ "      \"legalStatus\": \"Proprietorship\",\n"
        		+ "      \"natureBusiness\": null,\n"
        		+ "      \"yearBusiness\": 2,\n"
        		+ "      \"platform\": \"INDITRADE SCALERATOR LIMITED\",\n"
        		+ "      \"noEmployee\": null,\n"
        		+ "      \"employeeCost\": \"\",\n"
        		+ "      \"gstin\": \"36CIXPS3893Q1ZO\",\n"
        		+ "      \"cin\": \"\",\n"
        		+ "      \"vat\": \"\",\n"
        		+ "      \"tin\": \"\",\n"
        		+ "      \"listElement\": \"\",\n"
        		+ "      \"listTypeElement\": \"\",\n"
        		+ "      \"industryListDetails\": [\n"
        		+ "        {\n"
        		+ "          \"id\": \"\",\n"
        		+ "          \"listItem\": \"\",\n"
        		+ "          \"listType\": \"\",\n"
        		+ "          \"margin\": \"\"\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"salesTurnover\": \"\",\n"
        		+ "      \"monthlyPurchase\": \"\",\n"
        		+ "      \"profitMargin\": \"\",\n"
        		+ "      \"addBusinessDetails\": \"\",\n"
        		+ "      \"address\": \"\",\n"
        		+ "      \"pincode\": \"509001\",\n"
        		+ "      \"city\": \"Veerannapet\",\n"
        		+ "      \"state\": \"Telangana\",\n"
        		+ "      \"cityServiceableFlag\": \"\",\n"
        		+ "      \"pincodeServiceableFlag\": \"1\",\n"
        		+ "      \"BAOwnership\": \"Rented\",\n"
        		+ "      \"vintage\": 1,\n"
        		+ "      \"businessPan\": \"CIXPS3893Q\",\n"
        		+ "      \"sellingProduct\": null,\n"
        		+ "      \"otherSellingProduct\": \"\",\n"
        		+ "      \"partnerDetails\": [],\n"
        		+ "      \"businessPremises\": [],\n"
        		+ "      \"premisesRelationshipType\": \"\",\n"
        		+ "      \"drugLicense\": null,\n"
        		+ "      \"foodLicense\": null,\n"
        		+ "      \"cgtsmeDetails\": {\n"
        		+ "        \"cgtsmeApplicable\": false,\n"
        		+ "        \"cgtsmeDocument\": \"\",\n"
        		+ "        \"uamNumber\": \"\",\n"
        		+ "        \"plDocument\": \"\",\n"
        		+ "        \"gstDocument\": \"\",\n"
        		+ "        \"noDocument\": \"\",\n"
        		+ "        \"remark\": \"\",\n"
        		+ "        \"turnover\": \"\",\n"
        		+ "        \"turnoverBand\": \"\",\n"
        		+ "        \"investmentInPlantAndMachinery\": \"\",\n"
        		+ "        \"investmentInPlantAndMachineryBand\": \"\",\n"
        		+ "        \"noOfEmployees\": \"\",\n"
        		+ "        \"noOfEmployeesBand\": \"\",\n"
        		+ "        \"yearOfDeclaration\": \"\",\n"
        		+ "        \"msmeCategory\": \"\"\n"
        		+ "      },\n"
        		+ "      \"businessAddress\": \" 8-3-2, Mahabubnagar, BESIDE GOVT.HOSPITAL Raichur Mahabubnagar Road \"\n"
        		+ "    },\n"
        		+ "    \"workingCapital\": {\n"
        		+ "      \"tradeReceivables\": 1000000,\n"
        		+ "      \"tradePayables\": 1000000,\n"
        		+ "      \"inventoryValue\": 1000000,\n"
        		+ "      \"workingCapitalBlocked\": \"\",\n"
        		+ "      \"liability\": {\n"
        		+ "        \"input\": \"\"\n"
        		+ "      },\n"
        		+ "      \"majorClients\": \"\",\n"
        		+ "      \"majorSuppliers\": \"\",\n"
        		+ "      \"tradeCycle\": \"\",\n"
        		+ "      \"creditPeriodCreditor\": \"\",\n"
        		+ "      \"creditPeriodDebtors\": \"\",\n"
        		+ "      \"debtorsDays\": 101.3888888888889,\n"
        		+ "      \"creditorDays\": 141.94491759416977,\n"
        		+ "      \"inventoryDay\": 141.94491759416977,\n"
        		+ "      \"majorClient\": [\n"
        		+ "        {\n"
        		+ "          \"name\": \"\",\n"
        		+ "          \"phoneNo\": \"\"\n"
        		+ "        },\n"
        		+ "        {\n"
        		+ "          \"name\": \"\",\n"
        		+ "          \"phoneNo\": \"\"\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"majorSupplier\": [\n"
        		+ "        {\n"
        		+ "          \"name\": \"\",\n"
        		+ "          \"phoneNo\": \"\"\n"
        		+ "        },\n"
        		+ "        {\n"
        		+ "          \"name\": \"\",\n"
        		+ "          \"phoneNo\": \"\"\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"netWorkingCapitalDeployed\": 1000000,\n"
        		+ "      \"ccOd\": \"\",\n"
        		+ "      \"workingCapitalCycle\": 101,\n"
        		+ "      \"remark\": \"\"\n"
        		+ "    },\n"
        		+ "    \"bankDetails\": {\n"
        		+ "      \"bto\": \"\",\n"
        		+ "      \"bankDetail\": [\n"
        		+ "        {\n"
        		+ "          \"bankName\": \"HDFC Bank\",\n"
        		+ "          \"accNo\": \"00011020001772\",\n"
        		+ "          \"accType\": \"CURRENT\",\n"
        		+ "          \"ocbr\": 0,\n"
        		+ "          \"icbr\": 0,\n"
        		+ "          \"bankId\": 26,\n"
        		+ "          \"medianBto\": 704105,\n"
        		+ "          \"medianBalance\": 84068,\n"
        		+ "          \"monthlyEmiBank\": 0,\n"
        		+ "          \"isPrimary\": true,\n"
        		+ "          \"IFSCcode\": \"HDFC0000001\",\n"
        		+ "          \"isPrefilled\": 1,\n"
        		+ "          \"isPennydrop\": true,\n"
        		+ "          \"pennydropError\": false,\n"
        		+ "          \"pennydropMessage\": \"Bank details validated successfully\",\n"
        		+ "          \"pennydropBankName\": \"Name at bank - MNR GALAXY\",\n"
        		+ "          \"pennydropDisable\": true,\n"
        		+ "          \"parsingAccType\": \"\"\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"medianBalance\": \"\",\n"
        		+ "      \"medianBto\": \"\",\n"
        		+ "      \"ocbrConsolidated\": \"\",\n"
        		+ "      \"monthlyEmiBank\": \"\"\n"
        		+ "    },\n"
        		+ "    \"personalDetails\": {\n"
        		+ "      \"name\": \"KALEEL BASHA  SYED\",\n"
        		+ "      \"dob\": \"30-11-1978\",\n"
        		+ "      \"mobileNo\": {\n"
        		+ "        \"$numberLong\": \"8374363489\"\n"
        		+ "      },\n"
        		+ "      \"alternateMobileNo\": null,\n"
        		+ "      \"email\": \"mnrbatteries@gmail.com\",\n"
        		+ "      \"gender\": \"Male\",\n"
        		+ "      \"education\": null,\n"
        		+ "      \"maritalStatus\": null,\n"
        		+ "      \"shareholding\": \"\",\n"
        		+ "      \"address\": \"null H No 25-427-B Saleem Nagar Nandyal Nandyal Kurnool Raichur Mahabubnagar Road\",\n"
        		+ "      \"pincode\": 518501,\n"
        		+ "      \"city\": \"Veerannapet\",\n"
        		+ "      \"state\": \"Telangana\",\n"
        		+ "      \"cityServiceableFlag\": \"1\",\n"
        		+ "      \"pincodeServiceableFlag\": \"1\",\n"
        		+ "      \"ownership\": \"Owned\",\n"
        		+ "      \"vintage\": 2,\n"
        		+ "      \"relationshipType\": \"Self\",\n"
        		+ "      \"familyMember\": \"\",\n"
        		+ "      \"earningMember\": \"\",\n"
        		+ "      \"dependent\": \"\",\n"
        		+ "      \"aboutFamily\": \"\",\n"
        		+ "      \"assets\": [\n"
        		+ "        {\n"
        		+ "          \"asset\": \"Residence\",\n"
        		+ "          \"assetOwnership\": \"\",\n"
        		+ "          \"assetValue\": \"\",\n"
        		+ "          \"assetAddress\": \"null H No 25-427-B Saleem Nagar Nandyal Nandyal Kurnool Raichur Mahabubnagar Road\"\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"pan\": \"CIXPS3893Q\",\n"
        		+ "      \"addressProof\": \"\",\n"
        		+ "      \"cibilScore\": 830,\n"
        		+ "      \"trueScore\": 730,\n"
        		+ "      \"kyc\": [\n"
        		+ "        {\n"
        		+ "          \"kycType\": \"\",\n"
        		+ "          \"kycInput\": \"\"\n"
        		+ "        }\n"
        		+ "      ]\n"
        		+ "    },\n"
        		+ "    \"gaurantor\": {\n"
        		+ "      \"gaurantorInfo\": []\n"
        		+ "    },\n"
        		+ "    \"coapplicant\": {\n"
        		+ "      \"coapplicantInfo\": []\n"
        		+ "    },\n"
        		+ "    \"reference\": {\n"
        		+ "      \"creditorInfo\": [\n"
        		+ "        {\n"
        		+ "          \"name\": \"\",\n"
        		+ "          \"mobileNo\": \"\",\n"
        		+ "          \"relation\": \"\",\n"
        		+ "          \"years\": \"\",\n"
        		+ "          \"occupation\": \"\",\n"
        		+ "          \"relationship\": \"\",\n"
        		+ "          \"referenceType\": \"\",\n"
        		+ "          \"verified\": 0\n"
        		+ "        },\n"
        		+ "        {\n"
        		+ "          \"name\": \"\",\n"
        		+ "          \"mobileNo\": \"\",\n"
        		+ "          \"relation\": \"\",\n"
        		+ "          \"years\": \"\",\n"
        		+ "          \"occupation\": \"\",\n"
        		+ "          \"relationship\": \"\",\n"
        		+ "          \"referenceType\": \"\",\n"
        		+ "          \"verified\": 0\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"coapplicantReq\": 0,\n"
        		+ "      \"gaurantorReq\": 0\n"
        		+ "    },\n"
        		+ "    \"financeDetails\": {\n"
        		+ "      \"annualBto\": \"\",\n"
        		+ "      \"avgMonthlyBto\": \"\",\n"
        		+ "      \"avgMonthlyBtoOnline\": \"\",\n"
        		+ "      \"avgMonthlyBtoOffline\": \"\",\n"
        		+ "      \"avgMonthlyBtoGst\": \"\",\n"
        		+ "      \"netProfitMarginPercentage\": 40,\n"
        		+ "      \"gstField\": \"\",\n"
        		+ "      \"auditedMonthlySales\": \"\",\n"
        		+ "      \"monthlyBto\": \"704105\",\n"
        		+ "      \"monthlyBtoGst\": \"\",\n"
        		+ "      \"customerDeclared\": 300000,\n"
        		+ "      \"purchase\": 214285.7142857143,\n"
        		+ "      \"industryMargin\": \"\",\n"
        		+ "      \"businessMargin\": \"\",\n"
        		+ "      \"creditMargin\": 30,\n"
        		+ "      \"cashMargin\": 0,\n"
        		+ "      \"saleType\": \"GST\",\n"
        		+ "      \"btoPercentage\": 234.70166666666668,\n"
        		+ "      \"saleValue\": \"\"\n"
        		+ "    },\n"
        		+ "    \"caseSummary\": {\n"
        		+ "      \"responseLeverageScore\": \"\",\n"
        		+ "      \"caseStrengths\": [\n"
        		+ "        {\n"
        		+ "          \"strength\": [\n"
        		+ "            {\n"
        		+ "              \"label\": \"PD Comfort\",\n"
        		+ "              \"code\": \"APPROVED_HIGH_PD_COMFORT\",\n"
        		+ "              \"checked\": true,\n"
        		+ "              \"data\": [\n"
        		+ "                {\n"
        		+ "                  \"code\": \"APPROVED_HIGH_PD_COMFORT_BUSINESS_STABILITY\",\n"
        		+ "                  \"desc\": \"Business stability\",\n"
        		+ "                  \"checked\": true\n"
        		+ "                },\n"
        		+ "                {\n"
        		+ "                  \"code\": \"APPROVED_HIGH_PD_COMFORT_MULTIPLE_SOURCES_OF_INCOME\",\n"
        		+ "                  \"desc\": \"Multiple sources of income\",\n"
        		+ "                  \"checked\": false\n"
        		+ "                },\n"
        		+ "                {\n"
        		+ "                  \"code\": \"APPROVED_HIGH_PD_COMFORT_OTHER_PHYSICAL_PD_COMFORT\",\n"
        		+ "                  \"desc\": \"Other physical PD comfort\",\n"
        		+ "                  \"checked\": false\n"
        		+ "                },\n"
        		+ "                {\n"
        		+ "                  \"code\": \"APPROVED_HIGH_PD_COMFORT_POSITIVE_REFERENCE_CHECK\",\n"
        		+ "                  \"desc\": \"Positive reference checks\",\n"
        		+ "                  \"checked\": false\n"
        		+ "                },\n"
        		+ "                {\n"
        		+ "                  \"code\": \"APPROVED_HIGH_PD_COMFORT_SOUND_BUSINESS_KNOWLEDGE_QUALIFIED_MANAGEMENT\",\n"
        		+ "                  \"desc\": \"Sound business knowledge / Qualified Management\",\n"
        		+ "                  \"checked\": false\n"
        		+ "                },\n"
        		+ "                {\n"
        		+ "                  \"code\": \"APPROVED_HIGH_PD_COMFORT_STRONG_ASSET_BASE_PER_DISCUSSION\",\n"
        		+ "                  \"desc\": \"Strong asset base per discussion\"\n"
        		+ "                }\n"
        		+ "              ]\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"BRC/Track Record Comfort\",\n"
        		+ "              \"code\": \"APPROVED_BRC_TRACK_RECORD_COMFORT\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Strong Financials\",\n"
        		+ "              \"code\": \"APPROVED_STRONG_FINANCIALS\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Banking Comfort\",\n"
        		+ "              \"code\": \"APPROVED_BANKING_COMFORT\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Existing Flexiloans Client\",\n"
        		+ "              \"code\": \"APPROVED_EXISTING_FL_CLIENT_COMFORT\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"ESCROW/Other Comfort\",\n"
        		+ "              \"code\": \"APPROVED_ESCROW_OTHER_COMFORTS\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Others\",\n"
        		+ "              \"code\": \"APPROVED_OTHERS\",\n"
        		+ "              \"checked\": false\n"
        		+ "            }\n"
        		+ "          ],\n"
        		+ "          \"remarks\": \"ABC\"\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"caseConcerns\": [\n"
        		+ "        {\n"
        		+ "          \"remark\": \"\",\n"
        		+ "          \"concern\": [\n"
        		+ "            {\n"
        		+ "              \"label\": \"Over Leverage\",\n"
        		+ "              \"checked\": true\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Weak/ Unproven Loan Track\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Recent Business decline\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Others\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"High cash deposits\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"NTC\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"No unsecured loan track\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"No stock\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Poor business set up\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Stretched Working capital cycle\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Low margin business\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"No ownership of asset\",\n"
        		+ "              \"checked\": false\n"
        		+ "            }\n"
        		+ "          ],\n"
        		+ "          \"remarks\": \"ABC\"\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"sanctionConditions\": [\n"
        		+ "        {\n"
        		+ "          \"remarks\": \"ABC\",\n"
        		+ "          \"condition\": [\n"
        		+ "            {\n"
        		+ "              \"label\": \"CPV Required\",\n"
        		+ "              \"data\": [\n"
        		+ "                {\n"
        		+ "                  \"label\": \"Residential\",\n"
        		+ "                  \"checked\": false,\n"
        		+ "                  \"data\": [\n"
        		+ "                    {\n"
        		+ "                      \"label\": \"null H No 25-427-B Saleem Nagar Nandyal Nandyal Kurnool Raichur Mahabubnagar Road Veerannapet Telangana 518501\",\n"
        		+ "                      \"checked\": false\n"
        		+ "                    }\n"
        		+ "                  ]\n"
        		+ "                },\n"
        		+ "                {\n"
        		+ "                  \"label\": \"Office\",\n"
        		+ "                  \"checked\": true,\n"
        		+ "                  \"data\": [\n"
        		+ "                    {\n"
        		+ "                      \"label\": \" 8-3-2, Mahabubnagar, BESIDE GOVT.HOSPITAL Raichur Mahabubnagar Road  Veerannapet Telangana 509001\",\n"
        		+ "                      \"checked\": true\n"
        		+ "                    }\n"
        		+ "                  ]\n"
        		+ "                }\n"
        		+ "              ],\n"
        		+ "              \"checked\": true\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"VCIP Required\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"GST returns for\",\n"
        		+ "              \"gstReturnFor\": \"\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"One reference of relative and one reference of buyer/supplier\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Choose Guarantor\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Others\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Choose Co-applicant\",\n"
        		+ "              \"checked\": false\n"
        		+ "            },\n"
        		+ "            {\n"
        		+ "              \"label\": \"Ownership Proof Required\",\n"
        		+ "              \"data\": [\n"
        		+ "                {\n"
        		+ "                  \"label\": \"Residential\",\n"
        		+ "                  \"checked\": false\n"
        		+ "                },\n"
        		+ "                {\n"
        		+ "                  \"label\": \"Business\",\n"
        		+ "                  \"checked\": false\n"
        		+ "                }\n"
        		+ "              ],\n"
        		+ "              \"checked\": false\n"
        		+ "            }\n"
        		+ "          ]\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"noCondition\": 1,\n"
        		+ "      \"caseReferences\": [\n"
        		+ "        {\n"
        		+ "          \"name\": \"\",\n"
        		+ "          \"email\": \"\",\n"
        		+ "          \"mobileNo\": \"\",\n"
        		+ "          \"address\": \"\",\n"
        		+ "          \"relationship\": \"\"\n"
        		+ "        },\n"
        		+ "        {\n"
        		+ "          \"name\": \"\",\n"
        		+ "          \"email\": \"\",\n"
        		+ "          \"mobileNo\": \"\",\n"
        		+ "          \"address\": \"\",\n"
        		+ "          \"relationship\": \"\"\n"
        		+ "        }\n"
        		+ "      ],\n"
        		+ "      \"approvalStatus\": \"\",\n"
        		+ "      \"approvalStatusReasonData\": [],\n"
        		+ "      \"eligibilityMethod\": \"BANKING_BTO\",\n"
        		+ "      \"policy\": \"DIRECT\",\n"
        		+ "      \"finalEligibilityMethodAmount\": \"\"\n"
        		+ "    },\n"
        		+ "    \"sanctionInformation\": {\n"
        		+ "      \"product\": \"TLF\",\n"
        		+ "      \"proposedAmount\": 300000,\n"
        		+ "      \"proposedROI\": 2,\n"
        		+ "      \"interestType\": \"Flat\",\n"
        		+ "      \"proposedTenure\": 24,\n"
        		+ "      \"expectedMontlyEMI\": 18500,\n"
        		+ "      \"CPAOfficer\": \"MAHESH\",\n"
        		+ "      \"PDOfficer\": \"MAHESH\",\n"
        		+ "      \"approvalDate\": \"'"+current_date+"'\",\n"
        		+ "      \"disbursalDate\": \"25-09-2023\",\n"
        		+ "      \"interestedStartDate\": \"01-10-2023\",\n"
        		+ "      \"EMIStartDate\": \"01-11-2023\",\n"
        		+ "      \"pfPercentage\": \"\",\n"
        		+ "      \"differentialInterest\": 0,\n"
        		+ "      \"declarationStatus\": 1,\n"
        		+ "      \"insuranceApplied\": 0,\n"
        		+ "      \"amountPercentage\": 100,\n"
        		+ "      \"insuranceAmount\": 0,\n"
        		+ "      \"partnerInsurance\": [],\n"
        		+ "      \"applyInsurance\": false,\n"
        		+ "      \"approverName\": \"\",\n"
        		+ "      \"insuranceWaiveRemark\": \"\",\n"
        		+ "      \"insuranceRemark\": \"\",\n"
        		+ "      \"applyDocumentCharges\": true,\n"
        		+ "      \"docCharge\": 5900,\n"
        		+ "      \"isEscrow\": false,\n"
        		+ "      \"interState\": true,\n"
        		+ "      \"intraState\": false,\n"
        		+ "      \"productCode\": \"\",\n"
        		+ "      \"processingFee\": 0,\n"
        		+ "      \"processingPercentage\": 0,\n"
        		+ "      \"stampDutyPercentage\": 0.1,\n"
        		+ "      \"stampDutyFee\": 300,\n"
        		+ "      \"totalCharges\": 6200,\n"
        		+ "      \"netDisbursalAmount\": 293800,\n"
        		+ "      \"posSplitPercentage\": \"\",\n"
        		+ "      \"posSplitAmount\": \"\",\n"
        		+ "      \"otherRemarks\": \"\",\n"
        		+ "      \"differentialInterestCheck\": true,\n"
        		+ "      \"posSplitCheck\": false,\n"
        		+ "      \"posSplitAmountCheck\": false,\n"
        		+ "      \"POScharges\": 0,\n"
        		+ "      \"totalOutstanding\": 0,\n"
        		+ "      \"posCheck\": false,\n"
        		+ "      \"escrowPartnerName\": \"\",\n"
        		+ "      \"prepaymentType\": \"normal\",\n"
        		+ "      \"prepaymentPercent\": 5,\n"
        		+ "      \"insurerId\": 0,\n"
        		+ "      \"reducingRate\": 40.88\n"
        		+ "    },\n"
        		+ "    \"loanInfo\": {\n"
        		+ "      \"endUsageLoan\": \"Working Capital\",\n"
        		+ "      \"monthlyObligation\": 100000,\n"
        		+ "      \"activeLoans\": 0,\n"
        		+ "      \"otherObligation\": \"\",\n"
        		+ "      \"partnerObligation\": [],\n"
        		+ "      \"remarks\": \"\",\n"
        		+ "      \"external_emi_bounces\": 0\n"
        		+ "    },\n"
        		+ "    \"propertyDetails\": {\n"
        		+ "      \"type_oyo_model\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"min_guarantee\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"premises\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"ov_business\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"ov_property\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"lease_date\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"rel_oyo\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"rooms\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"rooms_oyo\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"occupancy\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"est_occupancy\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"cur_arr\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"avg_arr\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"facilities\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"peak\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"comptetitors\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"proximity\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"past_exp\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"other_prop\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"audit\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"major_exp\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"revenue\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"currentBusiness\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"rating\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"walkinRevenue\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"otherRevenue\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"pd\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"rent\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"deposit\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"relationship\": {\n"
        		+ "        \"status\": 1\n"
        		+ "      },\n"
        		+ "      \"cost\": {\n"
        		+ "        \"status\": 1 }}}"; 
		//String string="{\"loanCode\":\""+ loancode +"\",\"updatedBy\":\"Mahesh Narvane\",\"updatedById\":923844,\"isSaved\":true,\"business\":{\"businessName\":\"Test Data Business\",\"doc\":\"\",\"legalStatus\":\"Proprietorship\",\"natureBusiness\":\"Wholesaler\",\"yearBusiness\":23,\"platform\":\"EpiMoney\",\"noEmployee\":null,\"employeeCost\":\"\",\"gstin\":null,\"cin\":\"\",\"vat\":\"\",\"tin\":\"\",\"listElement\":\"\",\"listTypeElement\":\"\",\"industryListDetails\":[{\"listItem\":\"Two & Three Wheelers\",\"listType\":\"Wholesaler\",\"margin\":\"9.00\",\"nature_of_business\":\"Wholesaler\",\"id\":15}],\"salesTurnover\":\"\",\"monthlyPurchase\":\"\",\"profitMargin\":null,\"addBusinessDetails\":\"\",\"address\":\"\",\"pincode\":\"431515\",\"city\":\"Parli\",\"state\":\"Maharashtra\",\"cityServiceableFlag\":\"\",\"pincodeServiceableFlag\":\"1\",\"BAOwnership\":\"Owned\",\"vintage\":0,\"businessPan\":null,\"sellingProduct\":null,\"otherSellingProduct\":\"\",\"partnerDetails\":[],\"businessPremises\":[],\"premisesRelationshipType\":\"Father\",\"drugLicense\":null,\"foodLicense\":null,\"cgtsmeDetails\":{\"cgtsmeApplicable\":false,\"cgtsmeDocument\":\"\",\"uamNumber\":\"\",\"plDocument\":\"\",\"gstDocument\":\"\",\"noDocument\":\"\",\"remark\":\"\",\"turnover\":\"\",\"turnoverBand\":\"\",\"investmentInPlantAndMachinery\":\"\",\"investmentInPlantAndMachineryBand\":\"\",\"noOfEmployees\":\"\",\"noOfEmployeesBand\":\"\",\"yearOfDeclaration\":\"\",\"msmeCategory\":\"\"},\"businessAddress\":\" kjhahjdk jkhasjkdf Pune \"},\"workingCapital\":{\"tradeReceivables\":50000,\"tradePayables\":60000,\"inventoryValue\":120000,\"workingCapitalBlocked\":\"\",\"liability\":{\"input\":\"\"},\"majorClients\":\"\",\"majorSuppliers\":\"\",\"tradeCycle\":\"\",\"creditPeriodCreditor\":\"\",\"creditPeriodDebtors\":\"\",\"debtorsDays\":2.9589636331209364,\"creditorDays\":12.531672514780507,\"inventoryDay\":25.063345029561013,\"majorClient\":[{\"name\":\"\",\"phoneNo\":\"\"},{\"name\":\"\",\"phoneNo\":\"\"}],\"majorSupplier\":[{\"name\":\"\",\"phoneNo\":\"\"},{\"name\":\"\",\"phoneNo\":\"\"}],\"netWorkingCapitalDeployed\":110000,\"ccOd\":\"\",\"workingCapitalCycle\":15,\"remark\":\"\"},\"bankDetails\":{\"bto\":\"\",\"bankDetail\":[{\"bankName\":\"HDFC Bank\",\"accNo\":\"50200048138757\",\"accType\":\"CURRENT\",\"ocbr\":0,\"icbr\":0,\"bankId\":26,\"medianBto\":262746,\"medianBalance\":7908,\"monthlyEmiBank\":0,\"isPrimary\":true,\"IFSCcode\":\"HDFC0000103\",\"isPrefilled\":1,\"isPennydrop\":true,\"pennydropError\":false,\"pennydropMessage\":\"Bank details validated successfully\",\"pennydropBankName\":\""+bname+"\",\"pennydropDisable\":true,\"parsingAccType\":\"\"},{\"bankName\":\"Union Bank Of India\",\"accNo\":\"605901010050765\",\"accType\":\"CURRENT\",\"ocbr\":0,\"icbr\":0,\"bankId\":59,\"medianBto\":200487,\"medianBalance\":25325,\"monthlyEmiBank\":0,\"isPrimary\":0,\"isPrefilled\":1,\"isPennydrop\":\"\",\"pennydropError\":false,\"pennydropMessage\":\"\",\"pennydropBankName\":\"\",\"pennydropDisable\":\"\",\"parsingAccType\":\"Current Account\"}],\"medianBalance\":7908,\"medianBto\":513975,\"ocbrConsolidated\":0},\"personalDetails\":{\"name\":\""+name+"\",\"dob\":\"09-07-1996\",\"mobileNo\":6539831069,\"alternateMobileNo\":null,\"email\":\"vbhide@gmail.com\",\"gender\":\"Male\",\"education\":null,\"maritalStatus\":null,\"shareholding\":\"\",\"address\":\"null zbcs kjsncdkjds Pune\",\"pincode\":411033,\"city\":\"Thergaon\",\"state\":\"Maharashtra\",\"cityServiceableFlag\":\"1\",\"pincodeServiceableFlag\":\"1\",\"ownership\":\"Owned\",\"vintage\":0,\"relationshipType\":\"Self\",\"familyMember\":\"\",\"earningMember\":\"\",\"dependent\":\"\",\"aboutFamily\":\"\",\"assets\":[{\"asset\":\"Residence\",\"assetOwnership\":\"\",\"assetValue\":\"\",\"assetAddress\":\"null zbcs kjsncdkjds Pune\"}],\"pan\":\"ZDGPI8412J\",\"addressProof\":\"\",\"cibilScore\":\"800\",\"trueScore\":0,\"kyc\":[{\"kycType\":\"\",\"kycInput\":\"\"}]},\"gaurantor\":{\"gaurantorInfo\":[]},\"coapplicant\":{\"coapplicantInfo\":[]},\"reference\":{\"creditorInfo\":[{\"name\":\"\",\"mobileNo\":\"\",\"relation\":\"\",\"years\":\"\",\"occupation\":\"\",\"relationship\":\"\",\"referenceType\":\"\",\"verified\":0},{\"name\":\"\",\"mobileNo\":\"\",\"relation\":\"\",\"years\":\"\",\"occupation\":\"\",\"relationship\":\"\",\"referenceType\":\"\",\"verified\":0}],\"coapplicantReq\":0,\"gaurantorReq\":0},\"financeDetails\":{\"annualBto\":\"\",\"avgMonthlyBto\":\"\",\"avgMonthlyBtoOnline\":\"\",\"avgMonthlyBtoOffline\":\"\",\"avgMonthlyBtoGst\":\"\",\"netProfitMarginPercentage\":3,\"gstField\":\"\",\"auditedMonthlySales\":\"\",\"monthlyBto\":\"513975\",\"monthlyBtoGst\":\"\",\"customerDeclared\":150000,\"purchase\":145631.06796116504,\"industryMargin\":\"\",\"businessMargin\":3,\"creditMargin\":0,\"cashMargin\":0,\"saleType\":\"\",\"btoPercentage\":100,\"saleValue\":513975},\"caseSummary\":{\"responseLeverageScore\":\"\",\"caseStrengths\":[{\"strength\":[{\"label\":\"PD Comfort\",\"code\":\"APPROVED_HIGH_PD_COMFORT\",\"checked\":true,\"data\":[{\"code\":\"APPROVED_HIGH_PD_COMFORT_BUSINESS_STABILITY\",\"desc\":\"Business stability\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_MULTIPLE_SOURCES_OF_INCOME\",\"desc\":\"Multiple sources of income\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_OTHER_PHYSICAL_PD_COMFORT\",\"desc\":\"Other physical PD comfort\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_POSITIVE_REFERENCE_CHECK\",\"desc\":\"Positive reference checks\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_SOUND_BUSINESS_KNOWLEDGE_QUALIFIED_MANAGEMENT\",\"desc\":\"Sound business knowledge / Qualified Management\"},{\"code\":\"APPROVED_HIGH_PD_COMFORT_STRONG_ASSET_BASE_PER_DISCUSSION\",\"desc\":\"Strong asset base per discussion\",\"checked\":true}]},{\"label\":\"BRC/Track Record Comfort\",\"code\":\"APPROVED_BRC_TRACK_RECORD_COMFORT\",\"checked\":false},{\"label\":\"Strong Financials\",\"code\":\"APPROVED_STRONG_FINANCIALS\",\"checked\":false},{\"label\":\"Banking Comfort\",\"code\":\"APPROVED_BANKING_COMFORT\",\"checked\":false},{\"label\":\"Existing Flexiloans Client\",\"code\":\"APPROVED_EXISTING_FL_CLIENT_COMFORT\",\"checked\":false},{\"label\":\"ESCROW/Other Comfort\",\"code\":\"APPROVED_ESCROW_OTHER_COMFORTS\",\"checked\":false},{\"label\":\"Others\",\"code\":\"APPROVED_OTHERS\",\"checked\":false}],\"remarks\":\"gahjsdgaj\"}],\"caseConcerns\":[{\"remark\":\"\",\"concern\":[{\"label\":\"Over Leverage\",\"checked\":true},{\"label\":\"Weak/ Unproven Loan Track\",\"checked\":false},{\"label\":\"Recent Business decline\",\"checked\":false},{\"label\":\"Others\",\"checked\":false},{\"label\":\"High cash deposits\",\"checked\":false},{\"label\":\"NTC\",\"checked\":false},{\"label\":\"No unsecured loan track\",\"checked\":false},{\"label\":\"No stock\",\"checked\":false},{\"label\":\"Poor business set up\",\"checked\":false},{\"label\":\"Stretched Working capital cycle\",\"checked\":false},{\"label\":\"Low margin business\",\"checked\":false},{\"label\":\"No ownership of asset\",\"checked\":false}],\"remarks\":\"skjbvcsjdk\"}],\"sanctionConditions\":[{\"remarks\":\"sjkncjs\",\"condition\":[{\"label\":\"CPV Required\",\"data\":[{\"label\":\"Residential\",\"checked\":false,\"data\":[{\"label\":\"null zbcs kjsncdkjds Pune Thergaon Maharashtra 411033\",\"checked\":false}]},{\"label\":\"Office\",\"checked\":true,\"data\":[{\"label\":\" kjhahjdk jkhasjkdf Pune  Thergaon Maharashtra 411033\",\"checked\":true}]}],\"checked\":true},{\"label\":\"VCIP Required\",\"checked\":false},{\"label\":\"GST returns for\",\"gstReturnFor\":\"\",\"checked\":false},{\"label\":\"One reference of relative and one reference of buyer/supplier\",\"checked\":false},{\"label\":\"Choose Guarantor\",\"checked\":false},{\"label\":\"Others\",\"checked\":false},{\"label\":\"Choose Co-applicant\",\"checked\":false},{\"label\":\"Ownership Proof Required\",\"data\":[{\"label\":\"Residential\",\"checked\":false},{\"label\":\"Business\",\"checked\":false}],\"checked\":false}]}],\"noCondition\":1,\"caseReferences\":[{\"name\":\"\",\"email\":\"\",\"mobileNo\":\"\",\"address\":\"\",\"relationship\":\"\"},{\"name\":\"\",\"email\":\"\",\"mobileNo\":\"\",\"address\":\"\",\"relationship\":\"\"}],\"approvalStatus\":\"\",\"approvalStatusReasonData\":[],\"eligibilityMethod\":\"BANKING_BTO\",\"policy\":\"VIVRITI_DIRECT_TOPUP\",\"finalEligibilityMethodAmount\":\"\"},\"sanctionInformation\":{\"product\":\"VCFL\",\"proposedAmount\":310000,\"proposedROI\":1.3,\"interestType\":\"Flat\",\"proposedTenure\":24,\"expectedMontlyEMI\":16947,\"CPAOfficer\":\"Mayur P\",\"PDOfficer\":\"Mayur P\",\"approvalDate\":\"20-07-2023\",\"disbursalDate\":\"23-07-2023\",\"interestedStartDate\":\"01-08-2023\",\"EMIStartDate\":\"01-09-2023\",\"pfPercentage\":\"\",\"differentialInterest\":0,\"declarationStatus\":1,\"insuranceApplied\":0,\"amountPercentage\":100,\"insuranceAmount\":0,\"partnerInsurance\":[],\"applyInsurance\":false,\"approverName\":\"\",\"insuranceWaiveRemark\":\"\",\"insuranceRemark\":\"\",\"applyDocumentCharges\":true,\"docCharge\":5900,\"isEscrow\":false,\"interState\":false,\"intraState\":true,\"productCode\":\"\",\"processingFee\":14632,\"processingPercentage\":4.72,\"stampDutyPercentage\":0.1,\"stampDutyFee\":310,\"totalCharges\":20842,\"netDisbursalAmount\":20099,\"posSplitPercentage\":\"\",\"posSplitAmount\":\"\",\"otherRemarks\":\"\",\"differentialInterestCheck\":true,\"posSplitCheck\":false,\"posSplitAmountCheck\":false,\"POScharges\":266872.13,\"totalOutstanding\":269059,\"posCheck\":true,\"escrowPartnerName\":\"\",\"prepaymentType\":\"normal\",\"prepaymentPercent\":5,\"insurerId\":0,\"crossSellingProducts\":[],\"reducingRate\":27.56,\"interest\":2186.87,\"enableStampDutyChange\":true},\"loanInfo\":{\"endUsageLoan\":\"Working Capital\",\"monthlyObligation\":0,\"activeLoans\":1,\"otherObligation\":0,\"partnerObligation\":[],\"remarks\":\"\",\"external_emi_bounces\":0},\"propertyDetails\":{\"type_oyo_model\":{\"status\":1},\"min_guarantee\":{\"status\":1},\"premises\":{\"status\":1},\"ov_business\":{\"status\":1},\"ov_property\":{\"status\":1},\"lease_date\":{\"status\":1},\"rel_oyo\":{\"status\":1},\"rooms\":{\"status\":1},\"rooms_oyo\":{\"status\":1},\"occupancy\":{\"status\":1},\"est_occupancy\":{\"status\":1},\"cur_arr\":{\"status\":1},\"avg_arr\":{\"status\":1},\"facilities\":{\"status\":1},\"peak\":{\"status\":1},\"comptetitors\":{\"status\":1},\"proximity\":{\"status\":1},\"past_exp\":{\"status\":1},\"other_prop\":{\"status\":1},\"audit\":{\"status\":1},\"major_exp\":{\"status\":1},\"revenue\":{\"status\":1},\"currentBusiness\":{\"status\":1},\"rating\":{\"status\":1},\"walkinRevenue\":{\"status\":1},\"otherRevenue\":{\"status\":1},\"pd\":{\"status\":1},\"rent\":{\"status\":1},\"deposit\":{\"status\":1},\"relationship\":{\"status\":1},\"cost\":{\"status\":1}}}";
		String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6OTIzODQ0LCJzdWIiOiJtYWhlc2gubmFydmFuZUBmbGV4aWxvYW5zLmNvbSIsInVzZXJfbmFtZSI6Ik1haGVzaCBOYXJ2YW5lIiwicm9sZSI6WyJTdXBlciJdLCJzY29wZXMiOlsiYXBwbGljYXRpb24tc3RhdHVzLXdyaXRlIiwiYXBwbGljYXRpb24tc3RhdHVzLXJlYWQiLCJsb2FuLXJldmlldy1zdGF0dXMtcmVhZCIsImxvYW4tcmV2aWV3LXN0YXR1cy13cml0ZSIsImFwcGxpY2F0aW9uLWxpc3Qtd3JpdGUiLCJhcHBsaWNhdGlvbi1saXN0LXJlYWQiLCJhcHBsaWNhdGlvbi1idXNpbmVzcy1yZWFkIiwiYXBwbGljYXRpb24tcHJldmlvdXMtbG9hbi1yZWFkIiwicGFydG5lci1yZWFkIiwiYXBwbGljYXRpb24tbG9hbi1vZmZpY2VyLXJlYWQiLCJhcHBsaWNhdGlvbi1sb2FuLXB1cnBvc2UtcmVhZCIsImNvbW11bmljYXRpb24tcmVhZCIsImxlYWQtcmVhZCIsImxvYW4tcmVtaW5kZGF0ZS1idWxrLXdyaXRlIiwiYmFua3N0YXRlbWVudC1yZWFkIiwiYmFua3N0YXRlbWVudC1ydWxlLXJlYWQiLCJhcHBsaWNhdGlvbi1maW5hbmNlLXJlYWQiLCJjaWJpbC1yZWFkIiwiY2liaWwtcnVsZS1yZWFkIiwibGVhZC1tYW51YWxsZWFkLXdyaXRlIiwiYXBwbGljYXRpb24tc3RhdHVzLWRlbGV0ZSIsImFwcGxpY2F0aW9uLWxvYW4tZG9jcy13cml0ZSIsImFwcGxpY2F0aW9uLWJ1c2luZXNzLXdyaXRlIiwiYXBwbGljYXRpb24tZmluYW5jZS13cml0ZSIsImJhbmtzdGF0ZW1lbnQtbWV0YWRhdGEtd3JpdGUiLCJsZWFkLWFjdGl2aXR5LXJlYWQiLCJsZWFkLXdyaXRlIiwiYXBwbGljYXRpb24tY3JlYXRlLXdyaXRlIiwiY29tbXVuaWNhdGlvbi13cml0ZSIsImt5Yy1yZWFkIiwia3ljLXdyaXRlIiwia3ljLWRlbGV0ZSIsInNjb3JpbmctcmVhZCIsInBlcnNvbmFsLWRpc2N1c3Npb24td3JpdGUiLCJwZXJzb25hbC1kaXNjdXNzaW9uLXJlYWQiLCJjaWJpbC13cml0ZSIsImJhbmtzdGF0ZW1lbnQtcnVsZS13cml0ZSIsImRvY3VtZW50LXJlcXVlc3Qtd3JpdGUiLCJkb2N1bWVudC13cml0ZSIsImRvY3VtZW50LXJlYWQiLCJhcHBsaWNhdGlvbi1zdGF0dXMtYnVsay1yZWFkIiwiYXBwbGljYXRpb24tc3RhdHVzLWJ1bGstd3JpdGUiLCJsb2FuLWFtb3VudC1idWxrLXdyaXRlIiwibG9hbi1vZmZpY2VyLWJ1bGstd3JpdGUiLCJjcmVkaXQtb2ZmaWNlci1idWxrLXdyaXRlIiwibG9hbi1sZWFkZGF0ZS1idWxrLXdyaXRlIiwibG9hbi1wYXJ0bmVyLWJ1bGstd3JpdGUiLCJsb2FuLWFwcGxpY2F0aW9uY29kZS1idWxrLXdyaXRlIiwiY3VzdG9tZXItZG9jdW1lbnQtdXBsb2FkLXdyaXRlIiwiYXBwbGljYXRpb25fdmlldy11cGRhdGVfY29sZW5kaW5nX3BhcnRuZXItc3BhbiIsImJhbmstc2NvcmUtY2FyZC1yZWFkIiwiYXBwbGljYXRpb24tc2VuZC1lc2lnbi1lbWFuZGF0ZSIsInZpZGVvLWt5Yy1zdGF0dXMtY2hhbmdlIiwiYXBwbGljYXRpb25fdmlldy1hcHByb3ZlLWJ1dHRvbl9pblByb2dyZXNzIiwic2VuZF9yZW1pbmRlcnMtaHlwZXJsaW5rIiwid2hhdHNhcHAtYnVsay11cGxvYWQiLCJ1cGRhdGUtYmFuay1uYW1lIiwic2tpcC1ob3RsZWFkLWNoZWNrbGlzdCIsImFwcGxpY2F0aW9uLXN0YXR1cy1ob3QtbGVhZC13cml0ZSIsImNhbXRhYi1kZXRhaWxzLXJlYWQiLCJjaWJpbC1ydWxlLWVuZ2luZSIsImZvcmNlLXB1bGwtY2liaWwiLCJwZXJzb25hbC1kaXNjdXNzaW9uLW1hc3Rlci13cml0ZSIsInBlcnNvbmFsLWRpc2N1c3Npb24tc2FuY3Rpb24taW5mby1jb21wbGV0ZS13cml0ZSIsImV4cGVyaW1lbnQtdXBkYXRlIiwiYmFuay1zdGF0ZW1lbnQtcG9saWN5IiwiYXBwbGljYXRpb25fdmlldy1zdGF0dXMtaG90LWxlYWQtd3JpdGUtYnV0dG9uIiwicGVyc29uYWwtZGlzY3Vzc2lvbi1jcm0tcmVhZCIsInBlcnNvbmFsLWRpc2N1c3Npb24tY29tcGxldGUtcmVhZCIsInBhcnNlLWJhbmstc3RhdG1lbnRzIiwiZGVmZXJyYWwtd2FpdmVyLXBvbGljeSIsImJhbmstc2NvcmUtY2FyZC1yZWFkLWNwYSIsImFsbG93X3N0YXR1c190b19zcGVjaWZpY191c2VyIiwiYmFua2luZy1ydWxlLWVuZ2luZS1yZWFkIiwicGVyc29uYWwtZGlzY3Vzc2lvbi1jcm0td3JpdGUtYSIsImRlbGV0ZS1iYW5rLWFjY291bnQiLCJwYXJzZS1iYW5rLXN0YXRtZW50cy1taXNzaW5nLW1vbnRocyIsInBzLWRvYy1jaGVja2xpc3QtZm9yY2UtcG9saWN5IiwicGVyc29uYWwtZGlzY3Vzc2lvbi1zYW5jdGlvbi1pbmZvLWNybS13cml0ZSIsInBlcnNvbmFsLWRpc2N1c3Npb24tc2FuY3Rpb24taW5mby1yZWFkIiwicGVyc29uYWwtZGlzY3Vzc2lvbi1jcm0td3JpdGUiLCJwZXJzb25hbC1kaXNjdXNzaW9uLWd1YXJhbnRvci1hY2Nlc3MiLCJjaWJpbC11c2VyLWxvY2siLCJwYXJzZS1pbmRpdmlkdWFsLWJhbmstc3RhdG1lbnRzIiwiYmFuay1zdGF0ZW1lbnQtZm9yY2UtcGFyc2UtcG9saWN5IiwiYmFuay1zdGF0ZW1lbnQtZGVsZXRlLXBvbGljeSIsImRlbGV0ZS1iYW5rLWFuYWx5c2lzLXBvbGljeSIsImFkZC1yZWZlcmVuY2UtY3JtIiwiYXBwbGljYXRpb25fdmlldy1jaWJpbF92aWV3LXRhYiIsImFwcGxpY2F0aW9uX3ZpZXctYmFua19zdGF0ZW1lbnRfdmlldy10YWIiLCJsZWFkX3Bvb2wtcmVxdWVzdF9kb2N1bWVudC1oeXBlcmxpbmsiLCJhcHBsaWNhdGlvbl92aWV3LXVwZGF0ZV9jcmVkaXRfb2ZmaWNlci1zcGFuIiwibGVhZF9wb29sLXNlbmRfZW1haWwtaHlwZXJsaW5rIiwiYXBwbGljYXRpb25fdmlldy1hcHByb3ZlLWJ1dHRvbiIsImxlYWRfcG9vbC1zZW5kX3Ntcy1oeXBlcmxpbmsiLCJsZWFkX3Bvb2wtY2xpY2tfdG9fY2FsbC1oeXBlcmxpbmsiLCJhcHBsaWNhdGlvbl92aWV3LXNjb3JlX2JvYXJkLXRhYiIsImFwcGxpY2F0aW9uX3ZpZXctY2liaWxfcHVsbC10YWIiLCJhcHBsaWNhdGlvbl92aWV3LWJhbmtfc3RhdGVtZW50X2VkaXQtdGFiIiwibGVhZF9wb29sLXVwbG9hZF9kb2N1bWVudC1idXR0b24iLCJtZW51LW1hbnVhbF9sZWFkX3VwbG9hZC1idXR0b24iLCJtZW51LXJhdGluZ191cGxvYWQtYnV0dG9uIiwiYXBwbGljYXRpb25fdmlldy1hcHByb3ZlLWJ1dHRvbl9pblByb2dyZXNzIiwiYXBwbGljYXRpb25fdmlldy11cGRhdGVfZXhwZXJpbWVudC1zcGFuIiwiYXBwbGljYXRpb25fc2VuZC1zYW5jdGlvbi1idXR0b24iLCJhcHBsaWNhdGlvbl92aWV3LXVwZGF0ZV9jb2xlbmRpbmdfcGFydG5lci1zcGFuIiwiYXBwbGljYXRpb25fdmlldy1kaXNidXJzZV9sb2FuLWJ1dHRvbiIsImFwcGxpY2F0aW9uX3ZpZXctdXBkYXRlX2FwcGxpY2FudF9uYW1lLXNwYW4iLCJsZWFkX3Bvb2wtYnVsa19hY3Rpb24tYnV0dG9uIiwibWVudS1idWxrX2xlYWRfdXBsb2FkLWJ1dHRvbiIsImFwcGxpY2F0aW9uX3ZpZXctc2NvcmVfYm9hcmQtdHJ1ZXNjb3JlIiwiYXBwbGljYXRpb25fdmlldy1zY29yZV9ib2FyZC1hcHBsaWNhdGlvbl9zY29yZSIsImFwcGxpY2F0aW9uX3ZpZXctc2NvcmVfYm9hcmQtcG9zX3Njb3JlIiwiYXBwbGljYXRpb25fdmlldy1nZW5lcmF0ZV9sb2FuX2FncmVlbWVudC1idXR0b24iLCJtZW51LWRvd25sb2FkX2FwcGxpY2F0aW9uLWJ1dHRvbiIsImxlYWRfcG9vbC1ncmlkX2Rvd25sb2FkX2FwcGxpY2F0aW9uLWh5cGVybGluayIsImFwcGxpY2F0aW9uX3ZpZXctZG93bmxvYWRfYXBwbGljYXRpb24tYnV0dG9uIiwidXBkYXRlX2hhbmRsZWRfYnktZHJvcGRvd24iLCJiYW5rLXNjb3JlLWNhcmQtcmVhZCIsImFwcGxpY2F0aW9uLXNlbmQtZXNpZ24tZW1hbmRhdGUiLCJ2aWRlby1reWMtc3RhdHVzLWNoYW5nZSIsInNlbmRfcmVtaW5kZXJzLWh5cGVybGluayIsIndoYXRzYXBwLWJ1bGstdXBsb2FkIiwiZGVhY3RpdmF0ZV92aXJ0dWFsX2FjY291bnQtYnV0dG9uIiwidXBkYXRlLWJhbmstbmFtZSIsInNraXAtaG90bGVhZC1jaGVja2xpc3QiLCJhcHBsaWNhdGlvbl92aWV3LXN0YXR1cy1ob3QtbGVhZC13cml0ZS1idXR0b24iLCJjYW10YWItZGV0YWlscy1yZWFkIiwiY2liaWwtcnVsZS1lbmdpbmUiLCJmb3JjZS1wdWxsLWNpYmlsIiwicGVyc29uYWwtZGlzY3Vzc2lvbi1tYXN0ZXItd3JpdGUiLCJwZXJzb25hbC1kaXNjdXNzaW9uLXNhbmN0aW9uLWluZm8tY29tcGxldGUtd3JpdGUiLCJleHBlcmltZW50LXVwZGF0ZSIsImJ5cGFzc19hcHBfcmVzdHJpY3Rpb24tYnV0dG9uIiwiYmFuay1zdGF0ZW1lbnQtcG9saWN5IiwicGFyc2VfYmFua19zdGF0bWVudHMiLCJiYW5rX2FuYWx5c2lzX2NhdGVnb3JpZXMtYWRkLWJ1dHRvbiIsInNlbmRfZXNjcm93X21haWwtYnV0dG9uIiwicGVyc29uYWwtZGlzY3Vzc2lvbi1jcm0tcmVhZCIsInBlcnNvbmFsLWRpc2N1c3Npb24tY29tcGxldGUtcmVhZCIsInBhcnNlLWJhbmstc3RhdG1lbnRzIiwiZGVmZXJyYWwtd2FpdmVyLXBvbGljeSIsImJhbmstc2NvcmUtY2FyZC1yZWFkLWNwYSIsInBvc3RfcGRfc3RhdHVzX2NoYW5nZS1idXR0b24iLCJiYW5raW5nLXJ1bGUtZW5naW5lLXJlYWQiLCJwZXJzb25hbC1kaXNjdXNzaW9uLWNybS13cml0ZS1hIiwiZGVsZXRlLWJhbmstYWNjb3VudCIsInBhcnNlLWJhbmstc3RhdG1lbnRzLW1pc3NpbmctbW9udGhzIiwicHMtZG9jLWNoZWNrbGlzdC1mb3JjZS1wb2xpY3kiLCJwZXJzb25hbC1kaXNjdXNzaW9uLXNhbmN0aW9uLWluZm8tY3JtLXdyaXRlIiwicGVyc29uYWwtZGlzY3Vzc2lvbi1zYW5jdGlvbi1pbmZvLXJlYWQiLCJwZXJzb25hbC1kaXNjdXNzaW9uLWNybS13cml0ZSIsInBlcnNvbmFsLWRpc2N1c3Npb24tZ3VhcmFudG9yLWFjY2VzcyIsImNpYmlsLXVzZXItbG9jayIsImxtc191bmRvX2FwcHJvdmFsLWJ1dHRvbiIsInBhcnNlLWluZGl2aWR1YWwtYmFuay1zdGF0bWVudHMiLCJsb3Nfcm1fbmFtZV9zZWxlY3QiLCJiYW5rLXN0YXRlbWVudC1mb3JjZS1wYXJzZS1wb2xpY3kiLCJiYW5rLXN0YXRlbWVudC1kZWxldGUtcG9saWN5IiwiZGVsZXRlLWJhbmstYW5hbHlzaXMtcG9saWN5IiwidXBsb2FkLWNwLWNzdiIsImxvc19jaGFuZ2VfcGFydG5lcl9vcHRpb24iLCJhZGQtcmVmZXJlbmNlLWNybSJdLCJvYXV0aF90b2tlbiI6ImV5SjBlWEFpT2lKS1YxUWlMQ0poYkdjaU9pSlNVekkxTmlJc0ltcDBhU0k2SWpaalpUQXlaV000TnpOaVlqZzBaVE5sTXpnell6UTVNbVppTURBeFlUTTVNbU15WVRNd016RTNPRE5pTm1ZMVlqVXlNVGxqWW1NeVlXRTNaVFJoWW1GbE9UTTNOVGhoWVdRM09Ua3lOMkk0SW4wLmV5SmhkV1FpT2lJek9DSXNJbXAwYVNJNklqWmpaVEF5WldNNE56TmlZamcwWlRObE16Z3pZelE1TW1aaU1EQXhZVE01TW1NeVlUTXdNekUzT0ROaU5tWTFZalV5TVRsalltTXlZV0UzWlRSaFltRmxPVE0zTlRoaFlXUTNPVGt5TjJJNElpd2lhV0YwSWpveE56RTFOalk0TURrNExDSnVZbVlpT2pFM01UVTJOamd3T1Rnc0ltVjRjQ0k2TVRjME56SXdOREE1T0N3aWMzVmlJam9pT1RJek9EUTBJaXdpYzJOdmNHVnpJanBiSWlvaVhYMC5RTENwX19Dakl1aGNwbGozaTh0TmpzcVNWRXRENUloS1pMWnQ3MldhdUZpUVpRc0g4V2lzYkVNQTBuT3NkWWY4ZTh4X081Q25pbnpLRm1EaERGd3dZc2NyZmtCTWxxalVPMTV1NUxOcktUanNVeENJb3Ffa1BTY3M1MnJldjZMb2Z5T0RDQnhrQURjQUdNeEthYkxjSXoyZFdZRXJSTVdURkpjSUZxSm1tclFMVGRuOHQ1aUVMbmgza2ptMDZZdFdrQi00U1ZRRS1mZ2FCM2paR0NvdVYzR0t2Snd6THBPUW13RDA1SlN0UElyZ3RaVjhCVmFnQ01Sa19tcXJEd0d1d0FKUVVCMll0cFFJSV8xbV8tVkFqMDJBSzFDZldlSThTT2VrX0pKREstUy0xMUdkTW04UEVLQ0VyYjFCUy13MGxGVWpONEZ0bmVib0tFeXR6NzJlRHl3Q0tqemRFeXoxZmJJYmp0NVFseE1Sb21mY2tiS3lPdTE4QkJpQ1YyNEVSOW13MmozOHZmQTBTRzNFcGNPdU0xUWJCZUNFVFdiSWppcmxScktFenMwUnpXQ2piS3NCU1lZSXFqNU9xLXRJd2Jqd0lnUElsWkRGTDU2Vlp5WXBnOW01RDRFNnh0ZnFOYkNZcHlnWTllaGs0NFN2QzRkZ1BVR0w0dTJMRzVkbUdMUXlTZmJud3EwY1hIQWZaV244Vlo5SV9HV29OeWRDaG9PYkJJcmhhUlo2MzJ3ODZ4V1NOSDRkUG16clRkd1hUempGdHBKcW4tWnJBU2V1SHdQZ0RMS3JuVmI1LS1JRlpOV01fZUx1NGhBWkZJcEtFNXB5ejVUeFhkc2xzYlVueGVscDc5X3BoOFh2NmdCVktiRUU2dXBDcWwzcUdIaHVQS0JaaDRWcVpzcyIsIm9wY1JhbmRvbUNvZGUiOiJjcm0tNjY0MzA0ODJjMmMwOSIsImlzcyI6Imh0dHA6Ly9sb3Mtc3RhZ2luZy5mbGV4aWxvYW5zLmNvbS9hcGkvdG9rZW5sb2dpbiIsImlhdCI6MTcxNTY2ODA5OCwiZXhwIjoxNzE1Njg5Njk4LCJuYmYiOjE3MTU2NjgwOTgsImp0aSI6InM3N0ZCVnFkSlNQcWQyWlgifQ.uAEc10BOuCZXe4vdQQRT-ec2tcbAiUlyiZUkMwCNY5g";
		
		HashMap hm = new HashMap();
		hm.put("Accept-Encoding","gzip, deflate, br");
		hm.put("Accept-Language","en-GB,en-US;q=0.9,en;q=0.8");
		hm.put("Accept", "application/json");
		hm.put("Authorization", token);
		hm.put("Content-Type", "application/json;charset=UTF-8");
		hm.put("Connection", "keep-alive");
		hm.put("Origin", "https://leads-staging.flexiloans.com");
		hm.put("Referer", "https://leads-staging.flexiloans.com/");

		ValidatableResponse res = given().headers(hm).body(string).when().post("https://los-staging.flexiloans.com/los/pdlist/add").then().log().all();
//     	String respons=res.getBody().asPrettyString();
		
		HashMap penny = new HashMap();
		String auth_penny="Basic Um14bGVHbHNiMkZ1YzFCeWIyUTZVR1Z1Ym5sa2NtOXdWRzlyWlc0PQ==";
		penny.put("authority","los-stage-ecs.flexiloans.com");
		penny.put("authorization", auth_penny);
		
		ValidatableResponse penny_response = given().headers(penny).when().get(penyDrop+loancode).then().log().all();
		
		
		
		// Fetch PD API
		String url="https://los-staging.flexiloans.com/los/pdlist/fetch/";

		HashMap map= new HashMap<>();
		map.put("Authorization", token);
		map.put("Accept-Language", "en-US,en;q=0.9");
		map.put("Accept", "application/json");

		ValidatableResponse response = given().headers(map).when().get(url+loancode).then().log().all();
		//String respo = response.extract().body().asPrettyString();
		response.extract().body().asPrettyString();

		
		
		
		String Approve="{\"userId\":923837,\"loanCode\":\'"+loancode+"'\",\"leadCode\":\"65b9f065stphf\",\"applicationNumber\":\"PNF-TENEW-000-JAN24-00001\",\"approveObj\":{\"reasonData\":[],\"s3Code\":\"APPROVED_OTHERS_NONE\",\"approverRemarks\":\"vikram.jori\",\"escrowAvailable\":0,\"splitPer\":\"\",\"productTypeCode\":\"VCFL\",\"drawDownPenalty\":5,\"maxDrawDown\":10,\"minDrawDown\":\"\",\"maxDrawDownAmount\":160000,\"minDrawDownAmount\":50000,\"gracePeriod\":\"\",\"creditPeriod\":\"\"}}";
		Response Approve_response = given().headers(hm).body(string).when().post("https://los-stage-ecs.flexiloans.com/los/createLMSClientAndApplication");
		
		
		
		
		
		
		// Queries to run 
		String query="update loan_application set application_status = 'IP_COMPLETED_PD' where code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query);

		String query10="select customer_code from flexiloans_staging_db.loan_applicant_detail where loan_code = '"+loancode+"'";
		String code=dbUtil.ExecuteQuery(query10);
		String rand=RandomStringUtils.randomNumeric(6);
		String random="914"+rand;
		String rand1=RandomStringUtils.randomNumeric(7);
		String random1="91"+rand;


		String q="insert into status_activity values('"+random+"','64aea2fbuz5wm','5fd994a409731','2020-12-16\n"
				+ "10:31:24','"+code+"','IP_QUALIFIED_FORM_PENDING'\n"
				+ ",'partnerintegrationplatform','2','IP_COMPLETED_PD','loan_status_change')";
		//dbUtil.executeUpdateQuery(q);

		String q1="insert into status_activity values('"+random1+"','64aea2fbuz5wm','5fd994a409731','2020-12-16\n"
				+ "10:31:24','"+code+"','IP_COMPLETED_PD'\n"
				+ ",'partnerintegrationplatform','2','IP_COMPLETED_POST_PD','loan_status_change');\n"
				+ "";
		dbUtil.executeUpdateQuery(q1);


		String query4="update banking_details set status = 'Success', benificiary_name_matched = 1 where loan_code = '"+loancode+"'";
		dbUtil.executeUpdateQuery(query4);

		// Eligibility API
		String eli="https://console-staging.flexiloans.com/eligibility/v2";

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eli).then().log().all();

		String retur=repo.extract().body().asPrettyString();
		System.out.println("Loancode= "+loancode);

	}

	//@AfterClass
	public void Close_DB() throws SQLException 
	{
		dbUtil.closeDB();
		Reporter.log("--- db closes successfull ---",true);
		System.out.println("Loancode= "+loancode);
		BaseClass.driver.close();
	}


}
