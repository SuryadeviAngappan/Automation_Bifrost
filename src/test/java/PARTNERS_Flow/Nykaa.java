package PARTNERS_Flow;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class Nykaa {

	 String AccessToken;
	 String loancode;
	 String token;
	
	
	@Test(priority=1)
	public void Access_Token() 
	{
		
		 Response res= given()
	                .header("Authorization", "Basic NmJqdDY3MjJoMjQ3ODk0a2E4Z2Q0N290amI6MXFkbHB0ODlkdWhwNDBxaWtibWFzbGdobm5idmdlNXVhamZhODRhaWNlczhlb205YmhrdA==")
	                .header("contentType", "application/x-www-form-urlencoded")
	                .header("Accept", "application/json")
	                .formParam("grant_type", "client_credentials")
	                .formParam("scopes", "*")
	                .when().post("https://auth.flexiloans.com/oauth2/token").then().log().all().extract().response();

	        AccessToken = res.getBody().jsonPath().getJsonObject("access_token").toString();

	}

	
	@Test(dependsOnMethods = "Access_Token")
	public void lead_creation() 
	{
		
		
		String string="{\n"
				+ "    \"first_name\": \"Test\",\n"
				+ "    \"last_name\": \"Data\",\n"
				+ "    \"mobile_no\": \"1225637400\",\n"
				+ "    \"loanApplication\": {\n"
				+ "        \"amount\": 0,\n"
				+ "        \"loanBusiness\": {\n"
				+ "            \"business_name\": \"MK Farmers\",\n"
				+ "            \"address_line_1\": \"Ground floor,No. 16,Near 80 feet road,Nyanappanahalli, Hulimavu\",\n"
				+ "            \"city\": \"Bengaluru\",\n"
				+ "            \"state\": \"Karnataka\",\n"
				+ "            \"pincode\": \"517257\"\n"
				+ "        },\n"
				+ "        \"loanPersonalReferences\": [\n"
				+ "      {\n"
				+ "        \"name\": \"Rocket raccoon\",\n"
				+ "        \"mobile_no\": \"8877667766\",\n"
				+ "        \"profession\": \"CA\",\n"
				+ "        \"years_of_knowability\": 4,\n"
				+ "        \"email\": \"rocketraccun4664@example.com\",\n"
				+ "        \"relationship\": \"brother\",\n"
				+ "        \"interim_personal_reference_identifier\": \"first\",\n"
				+ "        \"guarantor\": 1\n"
				+ "      },\n"
				+ "       {\n"
				+ "        \"name\": \"Nebula\",\n"
				+ "        \"mobile_no\": \"8877667766\",\n"
				+ "        \"profession\": \"Psychologist\",\n"
				+ "        \"years_of_knowability\": 4,\n"
				+ "        \"email\": \"nebula563@example.com\",\n"
				+ "        \"relationship\": \"brother\",\n"
				+ "        \"interim_personal_reference_identifier\": \"second\",\n"
				+ "        \"guarantor\": 2\n"
				+ "      }\n"
				+ "        ],\n"
				+ "        \"loanMerchant\": {\n"
				+ "            \"partner_ref_no\": \"fl-26207284869\"\n"
				+ "        }\n"
				+ "    }\n"
				+ "}";
		
		
		String url="https://console-staging.flexiloans.com/unified/lead";
	
		
  Response res = given()
		         .header("Authorization",AccessToken)
		         .header("Content-Type","application/json")
		         .body(string)
		         .when().post(url).then().log().all().extract().response();
                
		 loancode=res.getBody().jsonPath().get("data.loanCode");
		 System.out.println("Loancode  =="+loancode);
		 token=res.getBody().jsonPath().get("data.token");
		 System.out.println("token  =="+token);
	}
	
	@Test(/*dependsOnMethods="lead_creation"*/)
	public void GET_CIBIL() 
	{
	      
		String url="https://console-staging.flexiloans.com/cibil/v2/bureau/getCibilUAT";
		
		HashMap map = new HashMap();
		map.put("loan_code",loancode);
		//map.put("action","CIBIL_CRE");
		     
		Response res = given()
		            //.header("Authorization",AccessToken)
		            .header("Content-Type", "application/json")
		            .body(map)
		            .when().post(url).then().log().all().extract().response();  
		 
		String print=res.asPrettyString();
		System.out.println(print);
		
	}
	
	
	@Test(dependsOnMethods="GET_CIBIL")
	public void policy_check() 
	{
		
		
		String url="https://console-staging.flexiloans.com/policy/check-policies/";
		
		ValidatableResponse res = given().when().get(url+loancode).then().log().all();
		String response = res.extract().body().asPrettyString();	
		
	}
	
}
