package WorkFlowLibrary;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class Custom_Token {

	
	public String generateToken(String Auth){
        Response res= given()
                .header("Authorization", Auth)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .formParam("grant_type", "client_credentials")
                .formParam("scopes", "*")
                .when().post("https://auth.flexiloans.com/oauth2/token").then().log().all().extract().response();

        String customToken = res.getBody().jsonPath().getJsonObject("access_token").toString();
        return customToken;
    }
	
	public String generateTokenBifost(String Auth){
        Response res= given()
                .header("Authorization", Auth)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .formParam("grant_type", "client_credentials")
                .formParam("scopes", "*")
                .when().post("https://heimdalldev.flexiloans.com/oauth2/token").then().log().all().extract().response();

        String customToken = res.getBody().jsonPath().getJsonObject("access_token").toString();
        return customToken;
    }
	
	
}
