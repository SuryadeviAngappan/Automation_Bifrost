package Pract;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import GenericUtilities.DataBaseUtility;
import io.restassured.response.ValidatableResponse;

public class bre {
	
	static DataBaseUtility dbUtil = new DataBaseUtility();

	@Test
	public void main() throws Throwable {
	
		dbUtil.connectToDB();
		String loancode="6530c2aconk98";
		String url="https://console-staging.flexiloans.com/scoringservice/bre/epimoney";
	
	String query1=" UPDATE loan_application SET partner_code = '1c41176794537' WHERE (code = '"+loancode+"')";
	dbUtil.executeUpdateQuery(query1);
	String query2="  INSERT INTO flexiloans_staging_db.risk_grading_final ( loan_code , final_grade, risk_type,is_thick_cibil) VALUES ('"+loancode+"', 'A', 'B','1')";
	dbUtil.executeUpdateQuery(query2);
	String query3=" UPDATE bank_db_staging.bank_analysis_default_abb SET abb_median_sigma = '-26000' WHERE (loan_code = '"+loancode+"')";
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

	HashMap map = new HashMap();
	map.put("loan_code", loancode);
	//Thread.sleep(15000);
	
	ValidatableResponse res = given().body(map).contentType("application/json").when().post(url).then().log().all();
	String response = res.extract().body().asPrettyString();
	
	dbUtil.closeDB();
	
	}
}
