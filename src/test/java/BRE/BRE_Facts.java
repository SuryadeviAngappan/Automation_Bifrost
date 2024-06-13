package BRE;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import GenericUtilities.DataBaseUtility;
import io.restassured.response.ValidatableResponse;

public class BRE_Facts {


	DataBaseUtility dbUtil = new DataBaseUtility();


	public void Ecom_Partner_Risk_Grade_A(String loancode) throws Throwable 
	{

		String query1=" UPDATE loan_application SET partner_code = '1c41176794537' WHERE (code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1); 
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='A' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE bank_db_staging.bank_analysis_default_abb SET abb_median_sigma = '14000' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4=" UPDATE bank_db_staging.bank_analysis_data_points SET bto_trend_ratio = '-0.6' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE bank_db_staging.bank_analysis_data_points SET emi_bounce_count = '2' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);
		String query8="INSERT INTO flexiloans_staging_db.custom_required_documents (loan_code, documents) VALUES ('"+loancode+"', '{}')";
		dbUtil.executeUpdateQuery(query8);

	}


	public void Ecom_Partner_Risk_Grade_B(String loancode) 
	{

		String query1=" UPDATE loan_application SET partner_code = '1c41176794537' WHERE (code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1); 
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE bank_db_staging.bank_analysis_default_abb SET abb_median_sigma = '14000' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4=" UPDATE bank_db_staging.bank_analysis_data_points SET bto_trend_ratio = '-0.6' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE bank_db_staging.bank_analysis_data_points SET emi_bounce_count = '2' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);
		String query8="INSERT INTO flexiloans_staging_db.custom_required_documents (loan_code, documents) VALUES ('"+loancode+"', '{}')";
		dbUtil.executeUpdateQuery(query8);	

	}


	public void Ecom_Partner_Risk_Grade_c(String loancode) 
	{

		String query1=" UPDATE loan_application SET partner_code = '1c41176794537' WHERE (code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1); 
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='C' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE bank_db_staging.bank_analysis_default_abb SET abb_median_sigma = '14000' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4=" UPDATE bank_db_staging.bank_analysis_data_points SET bto_trend_ratio = '-0.6' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE bank_db_staging.bank_analysis_data_points SET emi_bounce_count = '2' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);
		String query8="INSERT INTO flexiloans_staging_db.custom_required_documents (loan_code, documents) VALUES ('"+loancode+"', '{}')";
		dbUtil.executeUpdateQuery(query8);

	} 


	public void Ecom_Partner_Risk_Grade_D(String loancode) 
	{

		String query1=" UPDATE loan_application SET partner_code = '1c41176794537' WHERE (code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1); 
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='D' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE bank_db_staging.bank_analysis_default_abb SET abb_median_sigma = '14000' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4=" UPDATE bank_db_staging.bank_analysis_data_points SET bto_trend_ratio = '-0.6' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE bank_db_staging.bank_analysis_data_points SET emi_bounce_count = '2' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);
		String query8="INSERT INTO flexiloans_staging_db.custom_required_documents (loan_code, documents) VALUES ('"+loancode+"', '{}')";
		dbUtil.executeUpdateQuery(query8);

	}


	public void Ecom_Partner_Risk_Grade_E(String loancode) 
	{

		String query1=" UPDATE loan_application SET partner_code = '1c41176794537' WHERE (code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1); 
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='E' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE bank_db_staging.bank_analysis_default_abb SET abb_median_sigma = '14000' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4=" UPDATE bank_db_staging.bank_analysis_data_points SET bto_trend_ratio = '-0.6' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6=" UPDATE bank_db_staging.bank_analysis_data_points SET credit_implying_sale_count = '1' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE bank_db_staging.bank_analysis_data_points SET emi_bounce_count = '2' WHERE (loan_code = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query7);
		String query8="INSERT INTO flexiloans_staging_db.custom_required_documents (loan_code, documents) VALUES ('"+loancode+"', '{}')";
		dbUtil.executeUpdateQuery(query8);

	}


	public void Direct_Risk_Grade_A(String loancode) 
	{

		String query1="UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='A' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);
	}


	public void Direct_Risk_Grade_B(String loancode) 
	{

		String query1="UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);

	}



	public void Direct_Risk_Grade_C(String loancode) 
	{

		String query1="UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='C' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);	

	}


	public void Direct_Risk_Grade_D(String loancode) 
	{

		String query1="UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='D' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);

	}


	public void Direct_Risk_Grade_E(String loancode) 
	{

		String query1="UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='E' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);

	}



	public void POS_Risk_Grade_A(String loancode) 
	{

		String query1="UPDATE `loan_application` SET `partner_code` = '0b16a2206f607' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='A' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);

	}


	public void POS_Risk_Grade_B(String loancode) 

	{
		String query1="UPDATE `loan_application` SET `partner_code` = '0b16a2206f607' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);

	}


	public void POS_Risk_Grade_C(String loancode) 

	{
		String query1="UPDATE `loan_application` SET `partner_code` = '0b16a2206f607' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='C' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);
	}


	public void POS_Risk_Grade_D(String loancode) 

	{
		String query1="UPDATE `loan_application` SET `partner_code` = '0b16a2206f607' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='D' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);
	}


	public void POS_Risk_Grade_E(String loancode) 

	{
		String query1="UPDATE `loan_application` SET `partner_code` = '0b16a2206f607' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='E' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `disbursal_count` = '5' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);
		String query6="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `emi_bounce_charge_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query6);
		String query7=" UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '3482.27' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query7);
		String query8=" UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `inward_return_charge_ratio` = '0.07' WHERE (`loan_code` = '"+loancode+"');";
		dbUtil.executeUpdateQuery(query8);
		String query9="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `credit_implying_sale_count` = '2' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query9);
		String query10="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `non_cash_transactions_debit` = '130' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query10);
	}

	public void FoodTech_Risk_Grade_A(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '5e70d0f77rpgm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='A' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '13000' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);

	}

	public void FoodTech_Risk_Grade_B(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '5e70d0f77rpgm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '13000' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);

	}


	public void FoodTech_Risk_Grade_C(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '5e70d0f77rpgm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='C' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '13000' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);

	}


	public void FoodTech_Risk_Grade_D(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '5e70d0f77rpgm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='D' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '13000' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);

	}


	public void FoodTech_Risk_Grade_E(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '5e70d0f77rpgm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='E' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_default_abb` SET `abb_median_sigma` = '13000' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);

	}


	public void Top_Up_Direct_Risk_Grade_A(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='A' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `loan_application_metadata` SET `is_topup` = '1' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);

	}

	public void Top_Up_Direct_Risk_Grade_B(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `loan_application_metadata` SET `is_topup` = '1' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);

	}


	public void Top_Up_Direct_Risk_Grade_C(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='C' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `loan_application_metadata` SET `is_topup` = '1' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);

	}


	public void Top_Up_Direct_Risk_Grade_D(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='D' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `loan_application_metadata` SET `is_topup` = '1' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);

	}


	public void Top_Up_Direct_Risk_Grade_E(String loancode) 
	{

		String query1=" UPDATE `loan_application` SET `partner_code` = '6110ebf88rhlm' WHERE (`code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query1);
		String query2="update flexiloans_staging_db.risk_grading_final set final_grade='E' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query2);
		String query22="update flexiloans_staging_db.risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query22);
		String query222="update flexiloans_staging_db.risk_grading_final set is_thick_cibil='1' where loan_code='"+loancode+"'";
		dbUtil.executeUpdateQuery(query222);
		String query3="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `total_monthly_bto` = '14050' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query3);
		String query4="UPDATE `bank_db_staging`.`bank_analysis_data_points` SET `bto_trend_ratio` = '-0.06' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query4);
		String query5="UPDATE `loan_application_metadata` SET `is_topup` = '1' WHERE (`loan_code` = '"+loancode+"')";
		dbUtil.executeUpdateQuery(query5);


	}



}
