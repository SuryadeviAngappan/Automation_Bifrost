package NYKA;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.hc.core5.http.ContentType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.ThreadLocalClass;
import GenericUtilities.WebDriverUtility;
import Listner.ListnerClass;
import Nykaa_POM.form1;
import Pract.Doc;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.*;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.minidev.json.JSONObject;
//@Listeners(ListnerClass.class)


public class Nykaa_BNPL extends BaseClass {

	String access_token;
	public String loanCode;
	public Custom_Token reusablemethod = new Custom_Token();
	public LeadWorkFlowLib reusableMethod = new LeadWorkFlowLib();
	DataBaseUtility util = new DataBaseUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	Doc doc = new Doc();
	String lead_creation="https://console-staging.flexiloans.com/unified/lead";
	String url="https://console-staging.flexiloans.com/policy/check-policies/";
	String nykaa_offer="https://console-staging.flexiloans.com/offers/v1/loan-application/"; //654a142ciblw7/loan/offers/
    String nykaa="https://nykaa-stage.flexiloans.com/?src=nykaa&parid="	;

	@BeforeClass(alwaysRun = true)
	public void getToken() throws Throwable {
		access_token = reusablemethod.generateToken(ObjectReaders.readers.getNykaa_Token());
	}


	@Test(priority=1)
	public void TC_01() throws JsonMappingException, JsonProcessingException 
	{

		String no=jUtil.offerMobileNo();
		String json="{\"first_name\":\"test\",\"last_name\":\"data\",\"mobile_no\":\""+no+"\",\"loanApplication\":{\"partner_code\":\"5fe429a0sj16d\",\"amount\":0,\"loanBusiness\": {\"legal_status\":\"Proprietorship\",\"business_name\":\"Detectivess Co\",\"pincode\":\"431515\"}}}";
		ValidatableResponse res = given().contentType("application/json").header("Authorization",access_token).body(json).when().post(lead_creation).then().log().all();
		loanCode=res.extract().jsonPath().getString("data.loanCode");
		System.out.println(" Loancode Is = "+loanCode);

	}

	@Test(priority=2)
	public void Query() throws SQLException 
	{

		String query1=dbUtil.getQuery("BNPL_01",loanCode);
		dbUtil.executeUpdateQuery(query1);
		String query2=dbUtil.getQuery("BNPL_02",loanCode);
		dbUtil.executeUpdateQuery(query2);
		String query3=dbUtil.getQuery("BNPL_03",loanCode);
		dbUtil.executeUpdateQuery(query3);
		String query4=dbUtil.getQuery("BNPL_04",loanCode);
		dbUtil.executeUpdateQuery(query4);
		String query5=dbUtil.getQuery("BNPL_05",loanCode);
		dbUtil.executeUpdateQuery(query5);
		String query6=dbUtil.getQuery("BNPL_06",loanCode);
		dbUtil.executeUpdateQuery(query6);
		String query6_1=dbUtil.getQuery("BNPL_6_1",loanCode);
		dbUtil.executeUpdateQuery(query6_1);
		String query61=dbUtil.getQuery("BNPL_6_1_1",loanCode);
		dbUtil.executeUpdateQuery(query61);
		String query7=dbUtil.getQuery("BNPL_07",loanCode);
		dbUtil.executeUpdateQuery(query7);
		String query7_1=dbUtil.getQuery("BNPL_7_1",loanCode);
		dbUtil.executeUpdateQuery(query7_1);
		String query8=dbUtil.getQuery("BNPL_08",loanCode);
		dbUtil.executeUpdateQuery(query8);
		String query9=dbUtil.getQuery("BNPL_09",loanCode);
		dbUtil.executeUpdateQuery(query9);
		String query10=dbUtil.getQuery("BNPL_10",loanCode);
		dbUtil.executeUpdateQuery(query10);
		String query11=dbUtil.getQuery("BNPL_11",loanCode);
		dbUtil.executeUpdateQuery(query11);
		String query12=dbUtil.getQuery("BNPL_12",loanCode);
		dbUtil.executeUpdateQuery(query12);
		String query13=dbUtil.getQuery("BNPL_13",loanCode);
		dbUtil.executeUpdateQuery(query13);
		String query14=dbUtil.getQuery("BNPL_14",loanCode);
		dbUtil.executeUpdateQuery(query14);
		
	}

	@Test(priority=3)
	public void tagPolicy()
	{
		ValidatableResponse res = given().when().get(url+loanCode).then().log().all();
		String response = res.extract().body().asPrettyString();
		
	}

	@Test(priority=4)
	public void Hit_Offer() 
	{
		String body="{\n"
				+ "    \"policy\": \"BNPL\",\n"
				+ "    \"offered_min_cap_amount\": 10000,\n"
				+ "    \"offered_max_cap_amount\": 10000,\n"
				+ "    \"offered_min_cap_roi\": 1.65,\n"
				+ "    \"offered_max_cap_roi\": 1.65,\n"
				+ "    \"offered_min_cap_tenure\":12,\n"
				+ "    \"offered_max_cap_tenure\":12,\n"
				+ "    \"cycle_number\": 21,\n"
				+ "    \"cycle_type\":\"Days\",\n"
				+ "    \"pf_charges\": 0,\n"
				+ "    \"tenure_type\": \"Months\",\n"
				+ "    \"tenure_units\": 12,\n"
				+ "    \"metadata\": {\n"
				+ "        \"grace_period_type\": \"Days\",\n"
				+ "        \"grace_period_units\": 3\n"
				+ "    }\n"
				+ "}";

		ValidatableResponse res = given().header("client_code", "offers_front").contentType("application/json").body(body).when().post(nykaa_offer+loanCode+"/loan/offers/").then().log().all();
		String response=res.extract().asPrettyString();
		System.out.println("response");

	}
	
	@Test(priority=5)
	public void DigiLocker() throws Throwable 
	{
		doc.aadhar(loanCode);
	}
	
	
	@Test(priority=6)
	public void launch_Nykaa() throws InterruptedException 
	{
				
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get(nykaa+loanCode+"&journeyName=bnpl_v2_copy");
		
		form1 lp = new form1(driver);
				
		lp.DOB("27072000");
		lp.Email("PN@gmail.com");
		lp.Gender();
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
		lp.Residencial_pincode("431515");
		lp.Flat("GSadan, Parli");
		lp.Area("Parli");
		lp.Pan("btlpn0219b");
		lp.Continue();
		
		wutil.waitAndClickOnProceedBtn(lp.Accept_Offer);
		
						
	}



}


