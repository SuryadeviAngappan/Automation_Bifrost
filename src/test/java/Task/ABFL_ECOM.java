package Task;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericUtilities.DataBaseUtility;
import Policy.Policies;
import Pract.Doc;
import io.restassured.response.ValidatableResponse;
import junit.framework.Assert;

public class ABFL_ECOM {

	String loancode;
	DataBaseUtility dbUtil = new DataBaseUtility();
	Doc doc = new Doc();
	Policies policy = new Policies();

	@BeforeClass
	public void connectDB() throws Throwable 
	{
		dbUtil.connectToDB();
	}

	@Test()
	public void policy() throws Throwable 
	{
		loancode="65d5934ec5vf0";
		doc.BS(loancode);
	}

	@DataProvider(name = "final_grade")
	public String[] dpMethod1() 
	{
		return new String[]
				{"E"};
	}

	@Test(/*dataProvider="final_grade"*/dependsOnMethods = "policy")
	public void Risk_Type_C_B(/*String final_grade*/) throws SQLException, InterruptedException 
	{
		policy.ABFL_ECOM(loancode);

		String query="update risk_grading_final set risk_type='B' where loan_code='"+loancode+"'";
		try{dbUtil.executeUpdateQuery(query);}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		Thread.sleep(40000);

		String query1="update risk_grading_final set final_grade='E' where loan_code='"+loancode+"'";
		try{dbUtil.executeUpdateQuery(query1);}
		catch(Exception e) 
		{
			e.printStackTrace();
		}


		String eli="https://console-staging.flexiloans.com/eligibility/v2";

		HashMap hash= new HashMap();
		hash.put("loan_code", loancode);

		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eli).then().log().all();

		String retur=repo.extract().body().asPrettyString();
		System.out.println("Loancode= "+loancode);


		String query2="select risk_grade_tenure from em_loan_eligibility_log where loan_code='"+loancode+"'";
		String tenure=dbUtil.ExecuteQuery(query2);
		int act_tenure=Integer.parseInt(tenure);
		System.out.println("tenureeee    "+tenure);
		int expected=12;
		Assert.assertEquals(expected, act_tenure);

	}

}
