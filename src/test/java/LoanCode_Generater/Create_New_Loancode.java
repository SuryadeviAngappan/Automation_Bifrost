package LoanCode_Generater;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import GenericUtilities.DataBaseUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.Custom_Token;
import io.restassured.response.ValidatableResponse;

import static io.restassured.response.ValidatableResponse.*;
import static io.restassured.RestAssured.*;


public class Create_New_Loancode {
	
     DataBaseUtility dbutil = new DataBaseUtility();
     JavaUtility jutil = new JavaUtility();
     Custom_Token access_token= new Custom_Token();
    
	
    
	@Test
	public static String IP_Qalified() throws Throwable
	{
		//String loancode=
		
    DataBaseUtility.connectToDB(); 
	WebDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	
	driver.get("https://loancodes-stage.flexiloans.com/");
	WebElement elemen=driver.findElement(By.id("script"));
	WebDriverUtility.handleDropDown(elemen, 0);
	
	WebElement element = driver.findElement(By.xpath("//button[@type='submit']"));
	WebDriverUtility.waitAndClickOnElement(element);
	
	WebElement code=driver.findElement(By.xpath("(//tbody//tr//td)[2]"));
	String loancode=code.getText();
	String name=ObjectReaders.readers.get_FullName();
	
    String query1="update loan_business_detail set legal_status='partnership' where loan_code='"+loancode+"'";
	//DataBaseUtility.executeUpdateQuery(query1);
	String query="update flexiloans_staging_db.loan_applicant_detail set name ='"+name+"' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query);
	String query2="update loan_finance_detail set monthly_total_sales='210000' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query2);
	
	Thread.sleep(5000);
	driver.close();
	//DataBaseUtility.closeDB();
	
	return loancode;
	
	}	
	@Test
    public String Fresh_Lead() 
    {
        String token=access_token.generateToken("Basic MzBoNjlhaW9xdGYycm9pcjhicGo5ODI5Mmc6c3ZxaWtuY3VnbDNtOGRnNTd0MmJ0OGxmNGhqbWowc2JoZGdndWlyaTluOHMwZ2h2MGxi");
        
        String email=jutil.randomEmailId();
        String PanNo=jutil.randomePan();
        String MobileNo=jutil.offerMobileNo();
        
        
        String body="{\n"
                + "    \"first_name\": \"Rajat\",\n"
                + "    \"last_name\": \"Agarwal\",\n"
                + "    \"email\": \""+email+"\",\n"
                + "    \"mobile_no\": \""+MobileNo+"\",\n"
                + "    \"loanApplication\": {\n"
                + "        \"amount\": 50000,\n"
                + "        \"loanApplicant\": {\n"
                + "            \"dob\": \"1990-07-01\",\n"
                + "            \"gender\": \"Male\",\n"
                + "            \"pan_no\": \"EOPPA8922G\",\n"
                + "            \"pincode\": \"431515\",\n"
                + "            \"city\": \"Suchitra Junction\",\n"
                + "            \"state\": \"Telangana\"\n"
                + "        },\n"
                + "        \"loanFinance\": {\n"
                + "            \"monthly_total_sales\": 199999\n"
                + "        }\n"
                + "    }\n"
                + "}";
        
        ValidatableResponse response = given().contentType("application/json").header("Authorization",token).body(body).when().post("https://console-staging.flexiloans.com/unified/lead").then().log().all();
    //return loancode;
        String loancode=response.extract().body().jsonPath().getString("data.loanCode");
        
        return loancode;
    
    }
	
}
