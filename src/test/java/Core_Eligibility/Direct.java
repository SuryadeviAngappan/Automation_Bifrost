package Core_Eligibility;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import Eligibility.Egilibilities;
import GenericUtilities.DataBaseUtility;
import Policy.Policies;
import Pract.Doc;
import io.restassured.response.ValidatableResponse;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;
import net.minidev.json.JSONNavi;

public class Direct {


	Doc doc = new Doc();
	Policies policy = new Policies();
	String loancode/*="009bfed83c759"*/;
	Egilibilities eli = new Egilibilities();
	DataBaseUtility dbUtil = new DataBaseUtility();
	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";


	@Test(priority=0)
	public void Direct_BCF_ABB() throws Throwable 
	{
		dbUtil.connectToDB();
		String query1 = "Select el.loan_code from em_loan_eligibility_log el \n"
				+ "left join loan_application la  on el.loan_code=la.code \n"
				+ "where la.application_status like 'IP%' AND el.policy = 'ABFL_DIRECT' and el.median_bto is not NULL and el.foir is not null and el.abb_foir is not null order by el.created_at  ";
		loancode =dbUtil.ExecuteQuery(query1);
		System.out.println(loancode);

	}

	@DataProvider(name = "final_grade")
	public String[] dpMethod1() 
	{
		return new String[]
				{"D"/*"A","B","C","D","E","NA"*/};
	}

	@DataProvider(name="experiment_id")
	public String []dpMethod2()
	{
		return new String[] 
				//{"28","46","50","51","52","70","71","74","78","84","85","97","98","99","100","101","102","103","107"};
				//{"24","50","74","98","84","85","46"};
				{"21"};
	}

	//   @Test(priority=1)
	public void experiment(String experiment_id ) 
	{

		String query="update flexiloans_staging_db.loan_application set experiment_id='"+experiment_id+"";
		dbUtil.executeUpdateQuery(query);

	}

	@Test(dataProvider="final_grade",dependsOnMethods ="Direct_BCF_ABB",priority = 2)
	public void Direct_Eligibility(String final_grade) 
	{

		//		String query= "INSERT INTO `risk_grading_final` (`loan_code`, `final_grade`, `risk_type`) VALUES ('"+loancode+"', 'A', 'C');";
		//		DataBaseUtility.executeUpdateQuery(query);
		String query1="update risk_grading_final set final_grade='"+final_grade+"' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query1);
		String query2="update risk_grading_final set risk_type='C' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query2);
	}

	@Test(priority = 3,dataProvider="experiment_id")
	public void exp(String experiment_id) 
	{

		String query="update flexiloans_staging_db.loan_application set experiment_id='"+experiment_id+"'";
		dbUtil.executeUpdateQuery(query);		

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
		String retur=repo.extract().body().asPrettyString();


		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.EpiMoney.DIRECT[1].capped_banking_eligibility");
		System.out.println(actual_capped_banking_eligibility_Value.toString());
		String expected_capped_banking_eligibility_value="0";
		Assert.assertEquals(actual_capped_banking_eligibility_Value, expected_capped_banking_eligibility_value);
        System.out.println(loancode);
		
	}


}
