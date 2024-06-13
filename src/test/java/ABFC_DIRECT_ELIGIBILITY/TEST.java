//package ABFC_DIRECT_ELIGIBILITY;
//
//import static io.restassured.RestAssured.given;
//
//import java.util.HashMap;
//
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import BS_Upload.Upload_BS;
//import GenericUtilities.DataBaseUtility;
//import LoanCode_Generater.Create_New_Loancode;
//import Policy.Policies;
//import io.restassured.response.ValidatableResponse;
//
//public class TEST {
//	
//	Create_New_Loancode lc = new Create_New_Loancode();
//	Policies policy = new Policies();
//	Upload_BS bs = new Upload_BS();
//	String loancode;
//	String eligibility="https://console-staging.flexiloans.com/eligibility/v2";
//	
//	@BeforeClass
//	public void db() throws Throwable 
//	{
//		
//		DataBaseUtility.connectToDB();
//		loancode=lc.IP_Qalified();
//		bs.BS_Upload(loancode);
//		policy.ABFL_Direct_Policy(loancode);
//	
//	}
//	
//	@DataProvider(name = "final_grade")
//	public String[] dpMethod1() 
//	{
//		return new String[]
//				{"D","A","B","C","D","E","NA"};
//	}
//
//
//	@Test
//	public void ABFL_DIRECT_GRADE_A() throws InterruptedException 
//	{
//		
//		String query="update risk_grading_final set final_grade='A' where loan_code='"+loancode+"'";
//		DataBaseUtility.executeUpdateQuery(query);
//		
//		Thread.sleep(30000);
//		HashMap hash= new HashMap();
//		hash.put("loan_code", loancode);
//
//		ValidatableResponse repo = given().contentType("application/json").body(hash).when().post(eligibility).then().log().all();
//		String retur=repo.extract().body().asPrettyString();
//
//
//		String actual_capped_banking_eligibility_Value = repo.extract().body().jsonPath().getString("grouped.ABFL.DIRECT[1].capped_banking_eligibility");
//		System.out.println(actual_capped_banking_eligibility_Value.toString());
//		String expected_capped_banking_eligibility_value="0";
//		Assert.assertEquals(actual_capped_banking_eligibility_Value, expected_capped_banking_eligibility_value);
//        System.out.println(loancode);
//		
//		
//	}
//	
//	
//
//}
