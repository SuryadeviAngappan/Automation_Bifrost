package OFFERS_FLOW;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.*;
import Listner.ListnerClass;
import POM_Exaa_Offer_GreaterThan_2.*;
import Pract.Doc;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;

@Listeners(Listner.ListnerClass.class)
public class Offer_GreaterThan_2 extends BaseClass {

	DocumentUpload upload= new DocumentUpload();
	Doc doc = new Doc();
	String no;
	String loancode;
	WebDriverWait wait;
	JavascriptExecutor jse= (JavascriptExecutor)BaseClass.driver;


	@BeforeClass
	public void launchURl() throws Throwable 
	{
		String url=ObjectReaders.readers.Offers();
		driver.get(url);
	}


	@Test(priority=1)
	public void OfferlandingPage_MTO_G2() throws Throwable 
	{
		landing_Page lp = new landing_Page (BaseClass.driver);

		no=lp.MobileNo(jUtil.offerMobileNo());
		ListnerClass.reportLog(" The Mobile No = "+no);
		lp.CheckLoanEligibility();

	}


	@Test(priority=2)
	public void form1() throws Throwable
	{

		form1 lp = new form1(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Full_Name);

		String name=lp.FullName(ObjectReaders.readers.get_FullName());
		ListnerClass.reportLog(" Customer's Full Name Is = "+name);
		lp.Continue();
	}

	@Test(priority=3)
	public void form2() throws SQLException 
	{
		form2 lp = new form2(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Pan_No);


		String pan_no=lp.Pan_No("BTLPN0219B");
		ListnerClass.reportLog(" PanNo is = "+pan_no);
		String dob=lp.DOB("30091997");
		ListnerClass.reportLog(" DOB is = "+dob);
		String code=lp.PinCode("431515");
		ListnerClass.reportLog(" PinCode is = "+code);
		String gender=lp.Gender();
		ListnerClass.reportLog(" Gender is = "+gender);
		lp.Continue();
		String query_no=DataBaseUtility.getQuery("loancode", no);
		loancode=dbUtil.ExecuteQuery(query_no);
		System.out.println("The loancode is = "+loancode);
		ListnerClass.reportLog("The loancode is = "+loancode);

	}


	@Test(priority=4)
	public void pull_CKYC() throws Throwable 
	{
		doc.aadhar(loancode);

	}




	@Test(priority=5)
	public void form3() throws SQLException, InterruptedException 
	{

		form3 lp = new form3(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Is_This_Your_Full_Name);
		String name=lp.Is_This_Your_Full_Name();
		ListnerClass.reportLog(" Customers Full Name = "+name);
		String add=lp.Is_This_Your_Current_Address();
		ListnerClass.reportLog(" Customer's Current Address = "+add);
		String owned=lp.Residence();
		ListnerClass.reportLog(" Residence = "+owned);
		lp.Continue();
		Thread.sleep(18000);

	}



	@Test(priority=6)
	public void BankStatement() throws Throwable 
	{
		BankStatement lp = new BankStatement(BaseClass.driver);

		wUtil.waitForElementToBeVisible(driver, lp.UploadManuallyBtn);
		String name=ObjectReaders.readers.get_FullName();
		String query3="update loan_applicant_detail set name='"+name+"' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query3); 
		lp.ClickOnUploadManuallyBtn();
		lp.ClickOnUploadBtn();
		lp.ClickOnChooseDocumentBtn(upload.documentWorkFlow(Filepath.BankStatementFilePath));
	}



	@Test(priority=7)
	public void pob_document_upload() throws InterruptedException 
	{

		UploadDocument lp = new UploadDocument(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.gst);
		lp.GST_Certficate(upload.documentWorkFlow(Filepath.GSTCertficate)); 


	}


	@Test(priority = 8)
	public void form4() 
	{

		form4 lp = new form4(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Is_This_The_Name_of_Your_Business);

		String name=lp.Is_This_The_Name_of_Your_Business();
		ListnerClass.reportLog("Name Of Business ="+name);
		String add=lp.Is_This_Your_Business_Address();
		ListnerClass.reportLog("Current Business Address ="+add);
		String owned=lp.Business_State();
		ListnerClass.reportLog("Business State ="+owned);
		lp.Continue();


	}

	@Test(priority=9)
	public void form5() throws InterruptedException, SQLException 
	{
		form5 lp = new form5(BaseClass.driver);

		wUtil.waitForElementToBeVisible(driver, lp.Nature_of_Business);
		String nature=lp.BusinessNature();
		ListnerClass.reportLog(" Nature Of Customer's Business ="+nature);
		String product=lp.ProductYouSell();
		ListnerClass.reportLog(" Product Sell's ="+nature);
		lp.Continue();
		wUtil.waitAndClickOnProceedBtn(lp.Proceed);
		//loancode

		ListnerClass.reportLog("The loancode is = "+loancode);

		ListnerClass.reportLog(" Offers > 2lakh MTO Presanction Journey Completed  , Thank You!!");

	} 




}
