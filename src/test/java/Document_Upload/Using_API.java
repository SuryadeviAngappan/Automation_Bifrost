package Document_Upload;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.Commanparameter;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import PropertyFileConfig.ObjectReaders;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;

public class Using_API extends BaseClass 

{
/*
	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
	String no;
	static String Loancode;
	
	@Test(priority=1)
	public void LOS() throws Throwable 
	{
		landing_page_1 lp = new landing_page_1(driver);

		WebDriverUtility.waitForElementToBeVisible(driver, lp.NLPMobileNoTxtField);
		no=lp.MobileNo(JavaUtility.random9NoFlipkart());
		lp.AVGMonthlySale();
		lp.GSTCheckYesBox();
		lp.CheckLoanEligibility();
		Thread.sleep(8000);
		String query=DataBaseUtility.getQuery("loancode", no);
	    Loancode=DataBaseUtility.ExecuteQuery(query);
	    System.out.println("Loancode  "+Loancode);
	    
		
		Form_Details2_Page_3 lp1 =  new Form_Details2_Page_3(driver);
		
		WebDriverUtility.waitForElementToBeVisible(driver, lp1.NameTxtField);
		lp1.FullName("THE REALITY PROJECT");
		lp1.EmailId(JavaUtility.randomEmailId());
		lp1.Continue();
		
		
		form_3 lp2 = new form_3(BaseClass.driver);
		
		WebDriverUtility.waitForElementToBeVisible(driver, lp2.NatureOfBusinesssIcon);
		((JavascriptExecutor) BaseClass.driver).executeScript("arguments[0].scrollIntoView(true);", lp2.NatureOfBusinesssIcon);
		lp2.BusinessName(JavaUtility.randome5string());
		lp2.NatureOfBusinesss();
		lp2.SearchProductDropDown();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lp2.HowOldBusinessIcon);
		lp2.HowOldBusiness();
		lp2.ShopPincode("400001");
		lp2.Continue();
		 
		Thread.sleep(3000);
		DataBaseUtility.closeDB();
		driver.close();
	}
	
	
	     @Test(priority = 2)
	     
		public  void UploadPDF(String documentCategory,String documentType) throws Throwable 
	{
		 
		 String filepath=ObjectReaders.readers.BankStatement();
		 String apiURI="https://console-staging.flexiloans.com/documentservice";
		 
	
		Response response= given().contentType("multipart/form-data").multiPart(Commanparameter.FILE,filepath)
                .multiPart(Commanparameter.DOCUMENT_CATEGORY, documentCategory)
                .multiPart(Commanparameter.LOAN_CODE, Loancode)
                .multiPart(Commanparameter.DOCUMENT_TYPE, documentType)
		        .when()
		        .post(apiURI);
		
		String body=response.getBody().asPrettyString();
		int statuscode=response.getStatusCode();
		
		System.out.println("body   ="+body);
		System.out.println("statuscode   ="+statuscode);
		
		        
		
	}
	*/
	
	
}
