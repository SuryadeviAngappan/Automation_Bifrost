package Field_Validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import WorkFlowLibrary.DocumentUpload;

public class TC extends BaseClass  {



	DocumentUpload upload = new DocumentUpload();
	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
	String no;
	String loancode;
	String email;

/*	@Test(priority=1)
	public void TC_01() throws Throwable

	{
		landing lp = new landing(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NLPMobileNoTxtField);
		no=lp.MobileNo("0123703228");
		System.out.println("Mobile_NO  ="+no);
		char firstNo=no.charAt(0);

		int actual = Integer.parseInt(String.valueOf(firstNo));

		List<Integer> integers = Arrays.asList(6,7,8,9);
		int integerToFind = actual;
		lp.AVGMonthlySale();
		lp.GSTCheckYesBox();
		lp.CheckLoanEligibility();
		Thread.sleep(5000);
		
		Form_Details2_Page_3 lpm =  new Form_Details2_Page_3(BaseClass.driver);
		wUtil.waitForElementToBeVisible(BaseClass.driver, lpm.NameTxtField);


		if(integers.contains(integerToFind)) 
		{
			System.out.println(" Taking valid No !!");
		}

		else if	(lpm.EmailIdTxtField.isDisplayed()) 
		{

			Assert.fail("Taking invalid Numbers...     There is Bug at MobileNo field !!! ");

		}	

	}


	@Test(priority=2) 
	public void TC_02() throws Throwable 
	{


		landing_page_1 lp = new landing_page_1(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NLPMobileNoTxtField);
		no=lp.MobileNo("7103000013");

		lp.AVGMonthlySale();
		String gst=lp.NLPGstNoCheckBox();
		System.out.println("Gst registerd  ="+gst.toString());
		lp.CheckLoanEligibility();
		Thread.sleep(5000);
		String expected ="Yes";

		Form_Details2_Page_3 lpm =  new Form_Details2_Page_3(BaseClass.driver);
		wUtil.waitForElementToBeVisible(BaseClass.driver, lpm.NameTxtField);
		if(expected.equalsIgnoreCase(gst)) 
		{
			System.out.println(" You Should Select Gst Registerd Yes Icon");
		}
		else if(lpm.NameTxtField.isDisplayed()) 
		{

			Assert.fail(" There is bug at Gst Icon As it is taking for Non-Gst registerd Customer");
		}



	}


	@Test(priority=3)
	public void TC_03() throws InterruptedException 

	{


		landing_page_1 lp = new landing_page_1(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NLPMobileNoTxtField);
		no=lp.MobileNo("9277732411");
		System.out.println("Mobile_NO  ="+no);
		lp.AVGMonthlySale();
		lp.GSTCheckYesBox();
		lp.CheckLoanEligibility();


		Form_Details2_Page_3 lpm =  new Form_Details2_Page_3(BaseClass.driver);
		wUtil.waitForElementToBeVisible(BaseClass.driver, lpm.NameTxtField);
		lpm.FullName("Hellow World");
		String id=lpm.EmailId("ksj12m@mil.com");
		lpm.Continue();


		form_3 lpp = new form_3(BaseClass.driver);
		wUtil.waitForElementToBeVisible(BaseClass.driver, lpp.BusinessNameFld);

		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		//Creating a Matcher object
		Matcher matcher = pattern.matcher(id);
		if(id.matches(regex)) 
		{
			System.out.println("Given email-id is valid");
		} 
		else if(lpp.BusinessNameFld.isDisplayed()) 
		{
			Assert.fail("Given email-id is not valid     There is but at email ");

		}

	}



	@Test(priority=4)
	public void TC_04() throws Throwable 
	{


		landing_page_1 lp = new landing_page_1(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NLPMobileNoTxtField);
		no=lp.MobileNo("6377732411");
		System.out.println("Mobile_NO  ="+no);
		lp.AVGMonthlySale();
		lp.GSTCheckYesBox();
		lp.CheckLoanEligibility();


		Form_Details2_Page_3 lpm =  new Form_Details2_Page_3(BaseClass.driver);
		wUtil.waitForElementToBeVisible(BaseClass.driver, lpm.NameTxtField);
		lpm.FullName("Hellow World");
		String id=lpm.EmailId("ksj12m@mil.com");
		lpm.Continue();


		form_3 lpp = new form_3(BaseClass.driver);
		wUtil.waitForElementToBeVisible(BaseClass.driver, lpp.BusinessNameFld);
		lpp.BusinessName(jUtil.randome5string());
		lpp.NatureOfBusinesss();
		lpp.SearchProductDropDown();
		((JavascriptExecutor) BaseClass.driver).executeScript("arguments[0].scrollIntoView(true);", lpp.ShopPincodeFld);
		String text=lpp.LessThanYear();
		String expected ="Less than a year";
		lpp.ShopPincode("400001");
		lpp.Continue();


		Setu_4 lps = new Setu_4(BaseClass.driver);

		Thread.sleep(25000);
		if(text.equalsIgnoreCase(expected)) 
		{
			if(lps.CancleBtn.isDisplayed()) 
			{
				Assert.fail("Plz select proper option as it is accepting Less Than Year Business");
			}
		}

	} */

}
