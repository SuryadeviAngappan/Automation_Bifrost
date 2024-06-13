package NLP1_Positive_Returnung_User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import NLP.NLP1_GreaterThan2Lakh_Flow;

import POM_NLP1_LessThan_2.Otp_page;


public class Returnung_User_GreaterThan2 extends BaseClass {
/*
	public String no;
	String otp;
	@Test(priority=1)
	public void positive() throws SQLException, Throwable 
	{

		landing_page_1 lp = new landing_page_1(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NLPMobileNoTxtField);
		no=lp.MobileNo(jUtil.random9NoFlipkart());
		System.out.println(no);
		Thread.sleep(1000);
		lp.AVGMonthlySale();
		lp.GSTCheckYesBox();
		lp.CheckLoanEligibility();

	Form_Details2_Page_3 lp1 =  new Form_Details2_Page_3(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp1.NameTxtField);
		lp1.FullName("THE REALITY PROJECT");
		lp1.EmailId(jUtil.randomEmailId());
		lp1.Continue();
		
		





	}

	@Test(priority=2)
	public void otp() throws SQLException, Throwable 
	{
		landing_page_1 lp = new landing_page_1(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NLPMobileNoTxtField);
		lp.MobileNo(no);
		System.out.println(no);
		Thread.sleep(1000);
		lp.AVGMonthlySale();
		lp.GSTCheckYesBox();
		lp.CheckLoanEligibility();

	
	
		String query=dbUtil.getQuery("otp", no);
		String otp=dbUtil.ExecuteQuery(query);
		
	
		
		
		Otp_page lp2 = new Otp_page(BaseClass.driver);

		//wUtil.waitForElementToBeVisible(BaseClass.driver, lp2.EnterOtpFld);
		Thread.sleep(80000);
		lp2.EnterOtp(otp);
		Thread.sleep(1000);
		lp2.Verify();
		
		

		Setu_4 lp3 = new Setu_4(BaseClass.driver);
		
		Thread.sleep(25000);
		lp3.Cancle();
		Thread.sleep(3000);
		lp3.Option();
		Thread.sleep(1000);
		lp3.CancleDataSharing();	
		
		

		
		driver.close();
		
		
	}
	*/
}
