package Bifrost_Automation;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.util.Arrays;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import GenericUtilities.DataBaseUtility;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.Filepath;
import GenericUtilities.ThreadLocalClass;
import GenericUtilities.VerificationUtility;
import Listner.ListnerClass;
import LoanCode_Generater.Create_New_Loancode;
import WorkFlowLibrary.Custom_Token;
import io.restassured.response.ValidatableResponse;

@Listeners(Listner.ListnerClass.class)
public class BF_PD {
	
		VerificationUtility verificationUtil = new VerificationUtility();
		Create_New_Loancode lc = new Create_New_Loancode();
		DataBaseUtility dataBaseUtil =new DataBaseUtility();
		ExcelFileUtility excelFileUtility = new ExcelFileUtility();
//		String Bifrost="https://bifrost-dev.flexiloans.com/v1/lead/originator/";
		String Bifrost ="https://bifrost-dev.flexiloans.com/v1/lead/originator/625ec62b43kb5?colender_id=7";
//		String Pd="https://console-staging.flexiloans.com/personaldiscussion/pd-details/";
		String Pd="https://console-staging.flexiloans.com/personaldiscussion/pd-details/625ec62b43kb5";
		String loancode;
		String bifrost_value =null;
		String pd_value=null;
		String access_token_Pd;
		String access_token_BF;
		Custom_Token  ct = new Custom_Token();
	 
	        
				
			@BeforeClass
		public void preCondition() throws Throwable 
		{	
			DataBaseUtility.connectToDB();
//			loancode=lc.IP_Qalified();
			access_token_Pd=ct.generateToken("Basic M3JmYXVlZnRyNGhwbWc3OTNubDFqaDg4Y2g6OGJjY2drcDA0N2VxdnJhYmczNTJsZXZzZHJmYXZtZnMyOGc5NHBuNG02OTFvZGJxdGQ1");
			access_token_BF=ct.generateTokenBifost("Basic NG5scjUyYjJ2NnYwYmcydmdmZzJob2RsaHU6MTBlNW9yZDUwZW9nam1xNG4xNDhram41c292NDNuZXQ4OWtpaDM4cGh0dXJmOGh1MHBmZA==");
		}
			
		@Test
			public  void getRequestMethod() {
			
			ListnerClass.reportLog("TC_01 :"+" To verify the Bifrost and PD api key values, Getting the Response, validate the both response");
			ListnerClass.reportLog(" Test Loancode =625ec62b43kb5");
			
			//ValidatableResponse bifrostResponse = given().pathParam("loancode",loancode).queryParam("colender_id", 7).contentType("application/json").header("Authorization","Bearer "+access_token_BF).when().get(Bifrost+"{loancode}").then().log().all();

			ValidatableResponse bifrostResponse = given().contentType("application/json").header("Authorization","Bearer "+access_token_BF).when().get(Bifrost).then().log().all();
			System.out.println(bifrostResponse);
			
			ValidatableResponse pdResponse = given().contentType("application/json").header("authorization",access_token_Pd).when().get(Pd).then().log().all();
			System.out.println(pdResponse.toString());
			excelFileUtility.openExcelFile(Filepath.BiFrostExcelFile);
			
		for(int i=1;i<=excelFileUtility.getLastrownum("API_keys_mapping");i++) {
			try {
				bifrost_value=bifrostResponse.extract().body().jsonPath().getString(excelFileUtility.getExcelData("API_keys_mapping",i,0));
				System.out.println("Bifrost_value   :"  + bifrost_value.toString() );		
				if((excelFileUtility.getExcelData("API_keys_mapping",i,1).startsWith("message"))){
					pd_value=pdResponse.extract().body().jsonPath().getString(excelFileUtility.getExcelData("API_keys_mapping",i,1));
				} else if(excelFileUtility.getExcelData("API_keys_mapping",i,1).startsWith("select")) {
				    pd_value=dataBaseUtil.ExecuteQuery(excelFileUtility.getExcelData("API_keys_mapping",i,1)+" where loan_code ='625ec62b43kb5'");
				}else  {
					pd_value=excelFileUtility.getExcelData("API_keys_mapping",i,1);
				}
				
		
				ListnerClass.reportLog( "BF Api Key = " + excelFileUtility.getExcelData("API_keys_mapping",i,2) + " And    Value = " +bifrost_value + " && PD Api Key = " + excelFileUtility.getExcelData("API_keys_mapping",i,3) + " and Value = " +pd_value);

				if (bifrost_value == null) {
	            } else {
	                System.out.println("Bifrost key is valid.");
	            }
				if (pd_value == null) {
	            } else {
	                System.out.println("PD key is valid.");
	            }
				
	            if (bifrost_value.toString() != null && pd_value.toString() != null) {
	                if (bifrost_value.toString().equalsIgnoreCase(pd_value.toString())) {	
	        			//ListnerClass.reportLog( bifrost_value +" and  "+ pd_value +" are identical. ");
	        			//ListnerClass.reportLog("bifrost_value and pd_value are identical");

	                    System.out.println("Both keys are valid and the responses are identical.");
	                }}
	                
	                 else if((bifrost_value.toString() == null && pd_value.toString() == null)||(bifrost_value.toString() != null && pd_value.toString() == null)||(bifrost_value.toString() == null && pd_value.toString() != null)) {
	                	 
	                	 
	                 
	                    System.out.println("Both keys are valid but the responses are different.");
	        			//ListnerClass.reportLog("bifrost_value and pd_value are diffreent");
	        			//ListnerClass.TestFailure(result);
	        			ThreadLocalClass.gettestlevel().log(Status.FAIL, "Entry is not happening in loan_business_detail table.");
	                 }}catch(Exception e){}}}}
