package OFFERS_FLOW;
import POM_Exaa_Offer_LessThan_2.*;


import POM_NLP1_LessThan_2.Otp_page;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import GenericUtilities.WebDriverUtility;
import Listner.ListnerClass;
import WorkFlowLibrary.*;


@Listeners(Listner.ListnerClass.class)
public class Offer_LessThan_2 extends BaseClass{

	DocumentUpload upload= new DocumentUpload();
	String no;
	String otp;
	String loancode;
	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;




	@BeforeClass
	public void launchURl() throws Throwable 
	{
		String url=ObjectReaders.readers.Offers();
		driver.get(url);
	}



	@Test(priority=1)
	public void landing_Offer_L2() throws Throwable 
	{
		landing_1 lp = new landing_1(BaseClass.driver);

		no=lp.MobileNo(jUtil.offerMobileNo());
		ListnerClass.reportLog(" The Mobile No = "+no);
		lp.GetStarted();

	}

	@Test(priority=2)
	public void form1() throws Throwable 
	{

		form1_2 lp = new form1_2(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.MonthlySaleTxtFld);
		lp.MonthlySale(jUtil.randomMonthlySaleLesserthan2());
		lp.GstIcon();
		lp.Continue();
	}


	@Test(priority=3)
	public void Otp() throws InterruptedException, SQLException 
	{
		Otp_page lp = new Otp_page(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.EnterOtpFld);
		
		String query=dbUtil.getQuery("otp", no);
		otp=dbUtil.ExecuteQuery(query);
		ListnerClass.reportLog(" OTP = "+otp);

		lp.EnterOtp(otp);
		lp.Verify();

	}

	@Test(priority=4)
	public void form2() throws Throwable 
	{

		form2_3 lp = new form2_3(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.LoanAmountFld);

		String loan=lp.LoanAmount("50000");
		ListnerClass.reportLog(" Loan Amount = "+loan);
		String business=lp.BusinessMan();
		ListnerClass.reportLog(" Customer's Occupation = "+business);
		wUtil.scrollToParticularElement(lp.FullNameFld);
		String name=lp.FullName(ObjectReaders.readers.get_FullName());
		ListnerClass.reportLog(" Customer's Name= "+name);
		wUtil.scrollToParticularElement(lp.EmailIdFld);
		String email=lp.EmailId(jUtil.randomEmailId());
		ListnerClass.reportLog(" Customer's Email Id= "+email);
		wUtil.scrollToParticularElement(lp.DateOfIncorporationFld);
		String date=lp.DateOfIncorporation("30091997");
		ListnerClass.reportLog(" Customer's Date Of Incorporation= "+date);
		String prop=lp.Propritership();
		ListnerClass.reportLog(" Customer's Business Form= "+prop);
		wUtil.scrollToParticularElement(lp.ContinueBtn);
		lp.Continue();
		wUtil.waitAndClickOnProceedBtn(lp.ProceedBtn);
	}

	@Test(priority=5)
	public void form3() throws InterruptedException 
	{

		form3_4 lp = new form3_4(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.GenderIcon);

		String gender=lp.Gender();
		ListnerClass.reportLog(" Customer's Gender= "+gender);
		String dob=lp.DOB("30091997");
		ListnerClass.reportLog(" Customer's DOB= "+dob);
		String pin=lp.PinCode("400001");
		ListnerClass.reportLog(" Customer's PinCode= "+pin);
		wUtil.scrollToParticularElement(lp.ContinueBtn);
		String acc=lp.CurrentAccount();
		ListnerClass.reportLog(" Customer's Account type= "+acc);
		String pan=lp.PanNo("BTLPN0219B");
		ListnerClass.reportLog(" Customer's PanNo= "+pan);
		lp.Continue();

	}


	@Test(priority=6)
	public void Document() throws Throwable 
	{
		document_upload lp = new document_upload(BaseClass.driver);

		wUtil.waitAndClickOnElement(lp.ClickonContinueBtn);
		Thread.sleep(2000);
		String originalHandle=driver.getWindowHandle();

		for(String handle : driver.getWindowHandles()) 
		{
			if (!handle.equals(originalHandle)) 
			{
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle);
		
		wUtil.waitAndClickOnElement(lp.UploadBtn);
		lp.PanCard(upload.documentWorkFlow(Filepath.DocumentFilePath));
		wUtil.waitAndClickOnElement(lp.UploadBtn);
		lp.ProofOfCurrentResidentialAddress(upload.documentWorkFlow(Filepath.DocumentFilePath));
		wUtil.waitAndClickOnElement(lp.UploadBtn);
		lp.ProofOfBusinessAddress(upload.documentWorkFlow(Filepath.DocumentFilePath));
		wUtil.waitAndClickOnElement(lp.UploadBtn);
		lp.ProofOfFinancials(upload.documentWorkFlow(Filepath.DocumentFilePath));

		ListnerClass.reportLog(" Document's Uploaded Succesfully !!");
	}

	@Test(priority=7)
	public void form4() throws InterruptedException 
	{
		form4_5 lp = new form4_5(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.PinCodeFld);
		String pin=lp.PinCode("400001");
		ListnerClass.reportLog(" Customer's PinCode= "+pin);
		String resi=lp.Residence();
		ListnerClass.reportLog(" Residence Rented ? = "+resi);
		String busi=lp.Business();
		ListnerClass.reportLog(" Bussiness Rented ? = "+busi);
		lp.Continue();

	}
	
	@Test(priority=8)
	public void setu() throws InterruptedException 
	{
		
		setu lp = new setu(BaseClass.driver);
		
		wUtil.waitAndClickOnElement(lp.CancleBtn);
		lp.SelectOption();
		lp.CancleDataSharing();

		ListnerClass.reportLog(" Setu page Cancled, To Upload BS Manually");
	}

	@Test(priority=9)
	public void form_3() throws InterruptedException 
	{
		
		form3_4 lp = new form3_4(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NatureOfBusiness);
		
		String nature=lp.NatureOfBusiness();
		ListnerClass.reportLog(" Nature Of Business = "+nature);
		String product=lp.Product();
		wUtil.scrollToParticularElement(lp.HowOldBusiness);
		ListnerClass.reportLog(" Product You Sell = "+product);
		String vintage=lp.BusinessVintage();
		ListnerClass.reportLog(" Business Vintage = "+nature);
		wUtil.scrollToParticularElement(lp.ContinueBtn);
		String pincode=lp.ShopPinCode("431515");
		ListnerClass.reportLog(" Shop Pincode = "+pincode);
		String Account=lp.CurrentAccount();
		ListnerClass.reportLog(" Account Type = "+Account);
		lp.Continue();
		
		
	}
	
	@Test(priority=10)
	public void bank_statement() throws Throwable
	{

		bank_statement lp = new bank_statement(BaseClass.driver);

		wUtil.waitAndClickOnElement(lp.UploadManuallyBtn);
		wUtil.scrollToParticularElement(lp.UploadBtn);
		lp.ClickOnUploadBtn();
		lp.ClickOnChooseDocumentBtn(upload.documentWorkFlow(Filepath.BankStatementFilePath));

		ListnerClass.reportLog(" BankStatement Uploaded Succesfully !!");

	}	

	@Test(priority=11)
	public void form_4() 
	{
		form4_5 lp = new form4_5(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.BusinessNameFld);
		String name=lp.BusinessName(jUtil.randome5string());
		ListnerClass.reportLog(" Business Name Is = "+name);
		String prop=lp.Propritership();
		ListnerClass.reportLog(" Business Registerd As ="+prop);
		String no=lp.ShopNo(jUtil.random4numeric());
		ListnerClass.reportLog(" Shop No ="+no);
		String area=lp.Area(jUtil.randome5string());
		ListnerClass.reportLog(" Area ="+no);
		lp.Continue();
		
		
	}

	@Test(priority=12)
	public void form5() throws InterruptedException 
	{
		form5 lp = new form5(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.EmailId);
//		String gender=lp.Gender();
//		ListnerClass.reportLog(" Customer's Gender= "+gender);
//		String DOB=lp.DOB("30091997");
//		ListnerClass.reportLog(" Customer's DOB= "+DOB);
		//String email=lp.EmailId(jUtil.randomEmailId());
		//ListnerClass.reportLog(" Customer's EmailId= "+email);
		String pin=lp.PinCode("411052");
		ListnerClass.reportLog(" Customer's PinCode= "+pin);
		wUtil.scrollToParticularElement(lp.ContinueBtn);
		String name=lp.StreetName(jUtil.randome5string());
		ListnerClass.reportLog(" Customer's StreetName = "+name);
		String area=lp.Area(jUtil.randome5string());
		ListnerClass.reportLog(" Customer's Shop Area= "+area);
//		String Pan=lp.PanCard("BTLPN0219B");
//		ListnerClass.reportLog(" Customer's PanCard No= "+Pan);
		
		lp.Continue();
	}

	/*@Test(priority=11)
	public void BankAccount() throws InterruptedException, SQLException 
	{

		bank_account lp = new bank_account(BaseClass.driver); 

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.AccountNoFld);
		String Acc=lp.AccountNo("62441635074");
		ListnerClass.reportLog(" Customer's Account No= "+Acc);
		lp.ConformAccountNo("62441635074");
		String ifsc=lp.IfSCCode("SBIN0020030");
		ListnerClass.reportLog(" Customer's IFSC No= "+ifsc);
		String type=lp.AccountType();
		ListnerClass.reportLog(" Customer's Account Type = "+type);
		String name=lp.BenificaryName(jUtil.randome5string());
		ListnerClass.reportLog(" Benificary Name = "+name);


		String query=dbUtil.getQuery("loancode", no);
		loancode=dbUtil.ExecuteQuery(query);
		ListnerClass.reportLog(" LoanCode ="+loancode);

		ListnerClass.reportLog(" Offers < 2Lakh MTO PreSanction Journey Completed , Thank You!!  ");



	} */

	@AfterClass
	public void query() throws Throwable 
	{

		Thread.sleep(5000);
		//mobile_n0
		String query_no=DataBaseUtility.getQuery("mobile_no", loancode);
		String mobile_no=dbUtil.ExecuteQuery(query_no);
		System.out.println("mobile_no  "+mobile_no);	
		Thread.sleep(1000);
		//name
		String query1=DataBaseUtility.getQuery("name", no);
		String name=dbUtil.ExecuteQuery(query1);
		System.out.println("name  ="+name);
		Thread.sleep(1000);
		//email
		String query2=DataBaseUtility.getQuery("email", no);
		String email=dbUtil.ExecuteQuery(query2);
		System.out.println("email  ="+email);
		Thread.sleep(1000);
		//dob
		String query3=DataBaseUtility.getQuery("dob", no);
		String dob=dbUtil.ExecuteQuery(query3);
		Thread.sleep(1000);
		System.out.println("dob  ="+dob);
		Thread.sleep(1000);
		//gender
		String query4=DataBaseUtility.getQuery("gender", no);
		String gender=dbUtil.ExecuteQuery(query4);
		System.out.println("gender  ="+gender);
		Thread.sleep(1000);
		//address_flat_no
		String query5=DataBaseUtility.getQuery("address_flat_no", no);
		String address_flat_no=dbUtil.ExecuteQuery(query5);
		System.out.println("address_flat_no  ="+address_flat_no);
		Thread.sleep(1000);
		//address_building
		String query6=DataBaseUtility.getQuery("address_building", no);
		String address_building=dbUtil.ExecuteQuery(query6);
		System.out.println("address_building  ="+address_building);
		Thread.sleep(1000);
		//address_area
		String query7=DataBaseUtility.getQuery("address_area", no);
		String address_area=dbUtil.ExecuteQuery(query7);
		System.out.println("address_area  ="+address_area);
		Thread.sleep(1000);
		//address_city
		String query8=DataBaseUtility.getQuery("address_city", no);
		String address_city=dbUtil.ExecuteQuery(query8);
		System.out.println("address_city  ="+address_city);
		Thread.sleep(1000);
		//address_state
		String query9=DataBaseUtility.getQuery("address_state", no);
		String address_state=dbUtil.ExecuteQuery(query9);
		System.out.println("address_state  ="+address_state);
		Thread.sleep(1000);
		//address_pincode
		String query10=DataBaseUtility.getQuery("address_pincode", no);
		String address_pincode=dbUtil.ExecuteQuery(query10);
		System.out.println("address_pincode  ="+address_pincode);
		Thread.sleep(1000);
		//address_ownership_status
		String query11=DataBaseUtility.getQuery("address_ownership_status", no);
		String address_ownership_status=dbUtil.ExecuteQuery(query11);
		System.out.println("address_ownership_status  ="+address_ownership_status);
		Thread.sleep(1000);
		//pan_no
		String query12=DataBaseUtility.getQuery("pan_no", no);
		String pan_no=dbUtil.ExecuteQuery(query12);
		System.out.println("pan_no  ="+pan_no);
		Thread.sleep(1000);
		//date_of_incorporation
		String query13=DataBaseUtility.getQuery("date_of_incorporation", loancode);
		String date_of_incorporation=dbUtil.ExecuteQuery(query13);
		System.out.println("date_of_incorporation  ="+date_of_incorporation);
		Thread.sleep(1000);
		//business_address_pincode
		String query14=DataBaseUtility.getQuery("business_address_pincode", loancode);
		String business_address_pincode=dbUtil.ExecuteQuery(query14);
		System.out.println("business_address_pincode  ="+business_address_pincode);
		Thread.sleep(1000);
		//business_address_building
		String query15=DataBaseUtility.getQuery("business_address_building", loancode);
		String business_address_building=dbUtil.ExecuteQuery(query15);
		System.out.println("business_address_building  ="+business_address_building);
		Thread.sleep(1000);
		//business_address_area
		String query16=DataBaseUtility.getQuery("business_address_area", loancode);
		String business_address_area=dbUtil.ExecuteQuery(query16);
		System.out.println("business_address_area  ="+business_address_area);
		Thread.sleep(1000);
		//business_address_city
		String query17=DataBaseUtility.getQuery("business_address_city", loancode);
		String business_address_city=dbUtil.ExecuteQuery(query17);
		System.out.println("business_address_city  ="+business_address_city);
		Thread.sleep(1000);
		//business_address_state
		String query18=DataBaseUtility.getQuery("business_address_state", loancode);
		String business_address_state=dbUtil.ExecuteQuery(query18);
		System.out.println("business_address_state  ="+business_address_state);
		Thread.sleep(1000);
		//drug_license_no
		String query19=DataBaseUtility.getQuery("drug_license_no", loancode);
		String drug_license_no=dbUtil.ExecuteQuery(query19);
		System.out.println("drug_license_no  ="+drug_license_no);
		Thread.sleep(1000);
		//fssai_no
		String query20=DataBaseUtility.getQuery("fssai_no", loancode);
		String fssai_no=dbUtil.ExecuteQuery(query20);
		System.out.println("fssai_no  ="+fssai_no);
		Thread.sleep(1000);
		//monthly_total_sales
		String query21=DataBaseUtility.getQuery("monthly_total_sales", loancode);
		String monthly_total_sales=dbUtil.ExecuteQuery(query21);
		System.out.println("monthly_total_sales  ="+monthly_total_sales);
		Thread.sleep(1000);
		//business_address_ownership_status
		String query22=DataBaseUtility.getQuery("business_address_ownership_status", loancode);
		String business_address_ownership_status=dbUtil.ExecuteQuery(query22);
		System.out.println("business_address_ownership_status  ="+business_address_ownership_status);
		Thread.sleep(1000);
		//business_vintage
		String query23=DataBaseUtility.getQuery("address_area", loancode);
		String business_vintage=dbUtil.ExecuteQuery(query23);
		System.out.println("business_vintage  ="+business_vintage);
		Thread.sleep(1000);
		//is_salaried
		String query24=DataBaseUtility.getQuery("is_salaried", loancode);
		String is_salaried=dbUtil.ExecuteQuery(query23);
		System.out.println("is_salaried  ="+is_salaried);
		Thread.sleep(1000);
		//nature_of_business
		String query25=DataBaseUtility.getQuery("nature_of_business", loancode);
		String nature_of_business=dbUtil.ExecuteQuery(query25);
		System.out.println("nature_of_business  ="+nature_of_business);
		Thread.sleep(1000);
		//selling_products
		String query26=DataBaseUtility.getQuery("selling_products", loancode);
		String selling_products=dbUtil.ExecuteQuery(query26);
		System.out.println("selling_products  ="+selling_products);
		Thread.sleep(1000);
		//is_current_account_available
		String query27=DataBaseUtility.getQuery("is_current_account_available", loancode);
		String is_current_account_available=dbUtil.ExecuteQuery(query27);
		System.out.println("is_current_account_available  ="+is_current_account_available);
		Thread.sleep(1000);
		//cibil_score
		String query28=DataBaseUtility.getQuery("cibil_score", loancode);
		String cibil_score=dbUtil.ExecuteQuery(query28);
		System.out.println("cibil_score  ="+cibil_score);
	}




}
