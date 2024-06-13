package PARTNERS_Flow;

import java.awt.AWTException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import POM_Finequs.*;
import GenericUtilities.BaseClass;

public class finequs extends BaseClass{
	
	
	
	JavascriptExecutor js= (JavascriptExecutor)BaseClass.driver;
	
	@Test
	public void landing() 
	{
		landing_1 lp = new landing_1(BaseClass.driver);
		lp.MobileNo("8605583583");
		lp.CheckloanEligibility();
		
	}
	
	@Test
	public void otp() 
	{
		otp_2 lp = new otp_2(BaseClass.driver);
		
		lp.otp("");
		lp.Verify();
	}
	
	@Test
	public void form3() throws InterruptedException 
	{
		
		form_3 lp = new form_3(BaseClass.driver);
		
		lp.BusinessName(jUtil.randome5string());
		lp.NatureOfBusinesss();
		lp.SearchProductDropDown();
		lp.HowOldBusiness();
		lp.ShopPincode("400001");
		lp.Continue();
		
	}
	
	@Test
	public void setu() throws InterruptedException 
	{
		Thread.sleep(25000);
		
		setu lp = new setu(BaseClass.driver);
		
		lp.Cancle();
		lp.Option();
		lp.CancleDataSharing();
	}
	
	
	@Test
	public void BankStatement() throws InterruptedException 
	{
		BankStatement lp = new BankStatement(BaseClass.driver);
		
		lp.ClickOnUploadManuallyBtn();
		lp.ClickOnUploadBtn();
		lp.ClickOnChooseDocumentBtn();
	}

	@Test
	public void Document() throws Throwable, AWTException 
	{
		Document_Upload lp = new Document_Upload(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.ContinueBtn);
		lp.ContinueBtn.click();
		lp.PanCard();
		Thread.sleep(6000);
		lp.ProofOfCurrentResidentialAddress();
		Thread.sleep(3000);
		lp.ProofOfBusinessAddress();
		Thread.sleep(3000);
		lp.ProofOfFinancials();
		
	}
	
	@Test
	public void form4() 
	{
		form_4 lp = new form_4(BaseClass.driver);
		lp.Propritership();
		lp.ShopNo(jUtil.random4numeric());
		lp.Area(jUtil.randome5string());
		lp.Continue();
	}
	
	@Test
	public void form5() throws InterruptedException 
	{
		form_5 lp = new form_5(BaseClass.driver);
		
		lp.Area(jUtil.randome5string());
		lp.Continue();
		
		
		
		System.out.println(" Finequs PreSanctionJourney Completed  , ThankYou!!");
		Thread.sleep(5000);
		driver.close();
		
		
		
		
		
		
	}
}
