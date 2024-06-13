package NLP;

import java.awt.AWTException;
import java.sql.SQLException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import POM_NLP5_LessThan_2.*;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;
import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import Listner.ListnerClass;


@Listeners(Listner.ListnerClass.class)
public class NLP_5_LessThan2Lakh extends BaseClass{
	
	 DocumentUpload upload = new DocumentUpload();
	 String no;
	 String otp;
	 String loancode;
	
	 @BeforeClass
     public void launchURL() throws Throwable 
     {
   	 String url= ObjectReaders.readers.getNLP_5();
   	 driver.get(url);
     }
	 
	
	@Test(priority=1)
	public void landinglanding_Page_NLP5_MTO_L2() throws Throwable 
	{
		
		landing lp = new landing (BaseClass.driver);
		
		String name=lp.FullName(ObjectReaders.readers.get_FullName());
		ListnerClass.reportLog(" Customer Name = "+name);
		no=lp.MobileNO(jUtil.random9NoFlipkart());
		ListnerClass.reportLog(" Customer No = "+no);
		String gst=lp.Gst();
		ListnerClass.reportLog(" GST Registerd ? = "+gst);
		String avg=lp.AvgMonthlySale();
		ListnerClass.reportLog(" Customer AVG Sale = "+avg);
		lp.Next();
	    wUtil.waitForElementToBeVisible(BaseClass.driver, lp.HowOldBusinessIcon);
	    String old=lp.HowOldBusiness();
	    ListnerClass.reportLog(" How Old Buiness = "+old);
	    String acc=lp.CurrentAccIcon();
	    ListnerClass.reportLog(" Acc Type = "+acc);
	    lp.CheckLoanEligibility();
	   	
	}
	
	
	@Test(priority=2)
	public void OTP() throws InterruptedException, SQLException 
	{
		
		Otp lp = new Otp (BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.EnterOtp);
		String query=dbUtil.getQuery("otp", no);
		otp=dbUtil.ExecuteQuery(query);
		ListnerClass.reportLog(" OTP = "+otp);

		String Otp=lp.EnterOtp(otp);
		lp.Verify();
		
	}
	
	
	@Test(priority=3)
	public void form2() throws InterruptedException 
	{
		form2 lp = new form2 (BaseClass.driver);
		

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.LoanAmountFld);
		String amt=lp.LoanAmount("55000");
		ListnerClass.reportLog(" LoanAmount Required = "+amt);
		String busi=lp.BusinessMan();
		ListnerClass.reportLog(" Occupation = "+busi);
		//String name=lp.FullName(ObjectReaders.readers.get_FullName());
		//ListnerClass.reportLog(" Customer's Full Name = "+name);
		String email=lp.EmailID(jUtil.randomEmailId());
		ListnerClass.reportLog(" Email ID = "+email);
		wUtil.scrollToParticularElement(lp.ContinueBtn);
		String date=lp.CorporationDate("30091997");
		ListnerClass.reportLog(" Corporation Date = "+date);
		lp.Continue();
		
	}
	
	@Test(priority=4)
	public void form3() throws InterruptedException 
	{
		
		form3 lp = new form3(BaseClass.driver);
		
		
		wUtil.waitAndClickOnProceedBtn(lp.ProceedBtn);
		wUtil.waitAndClickOnElement(lp.GenderIcon);
		String gender=lp.Gender_NLP_3_L2();
		ListnerClass.reportLog("Gender = "+gender);
		String dob=lp.DOB("30091997");
		ListnerClass.reportLog("DOB = "+dob);
		String pin=lp.PinCode("400001");
		ListnerClass.reportLog("PinCode = "+pin);
		wUtil.scrollToParticularElement(lp.CurrentAccIcon);
		String acc=lp.CurrentAccount();
		ListnerClass.reportLog(" Customer Account Type = "+acc);
		String pan=lp.PanNo("BTLPN0219B");
		ListnerClass.reportLog(" Customer PanNo = "+pan);
		lp.Continue();
		
	}
	
	@Test(priority=5)
	public void document() throws Throwable 
	{
		
		Upload_Document lp = new Upload_Document(BaseClass.driver);
		
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

		ListnerClass.reportLog(" Document's Uploaded Successfully !!");
		
	}
	
	
	@Test(priority=6)
	public void form4() 
	{
		
		form4 lp = new form4 (BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.PincodeFld);

		String pincode=lp.Pincode("400001");
		ListnerClass.reportLog("Business Pincode = "+pincode);
		String rent=lp.ResidenceRented();
		ListnerClass.reportLog(" Residence Rented ? = "+rent);
		String rented=lp.BusinessRented();
		ListnerClass.reportLog("Business Rented ? = "+rented);
		lp.Continue();
	}
	
	
	@Test(priority=7)
	public void setu() throws Throwable 
	{
		Setu lp = new Setu (BaseClass.driver);
		
		wUtil.waitAndClickOnElement(lp.CancleBtn);
		lp.SelectOption();
		lp.CancleDataSharing();
		ListnerClass.reportLog(" Setu Page Cancled, To Upload BS Manually !!");
		
	}
	
	
	
	@Test(priority=8)
	public void form_3() throws InterruptedException 
	{
		
		form3 lp = new form3(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NatureOfBusiness);

		String nature=lp.NatureOfBusiness();
		ListnerClass.reportLog(" Nature Of Business = "+nature);
		String product=lp.Product();
		wUtil.scrollToParticularElement(lp.HowOldBusiness);
		ListnerClass.reportLog(" Product Sell = "+product);
		String vintage=lp.BusinessVintage();
		ListnerClass.reportLog(" Business Vintage = "+nature);
		wUtil.scrollToParticularElement(lp.ContinueBtn);
		String pincode=lp.ShopPinCode("431515");
		ListnerClass.reportLog(" Shop Pincode = "+pincode);
		String Account=lp.CurrentAccount();
		ListnerClass.reportLog(" Account Type = "+Account);
		lp.Continue();
		
	}
	
	
	@Test(priority=9)
	public void Bank_Statement() throws Throwable 
	{
		BankStatement lp = new BankStatement (BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.UploadManuallyBtn);
		
		wUtil.waitAndClickOnElement(lp.UploadManuallyBtn);
		wUtil.scrollToParticularElement(lp.SelectBankStatement);
		lp.ClickOnUploadBtn();
		lp.ClickOnChooseDocumentBtn(upload.documentWorkFlow(Filepath.BankStatementFilePath));

		ListnerClass.reportLog(" BankStatement Uploaded Successfully !!");
	}
	
	
	
	@Test(priority=10)
	public void form_4() 
	{
		
		form4 lp = new form4(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.BusinessNameFld);

		String name=lp.BusinessName(jUtil.randome5string());
		ListnerClass.reportLog(" Business Name = "+name);
		String registerd=lp.BusinessRegisted();
		ListnerClass.reportLog(" Business Registerd As ? = "+registerd);
		String no=lp.ShopNo(jUtil.random4numeric());
		ListnerClass.reportLog(" Business Shop No = "+no);
		String area=lp.BusinessArea(jUtil.randome5string());
		ListnerClass.reportLog(" Business Area = "+area);
		lp.Continue();
		
		
		
	}
	
	
	@Test(priority=11)
	public void form5() throws Throwable 
	{
		
		form5 lp = new form5(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.EmailIdTxtField);

//		String gender=lp.Gender();
//		ListnerClass.reportLog(" Gender Of Customer Is = "+gender);
//		String dob=lp.DOB("30091997");
//		ListnerClass.reportLog(" DOB Of Customer Is = "+dob);
	//	String email=lp.EmailId(jUtil.randomEmailId());
	//	ListnerClass.reportLog(" Customer's Email ID Is = "+email);
		String pincode=lp.PinCode("400001");
		ListnerClass.reportLog("Customer's Pincode Is = "+pincode);
		String flat=lp.Flat(jUtil.random4numeric());
		ListnerClass.reportLog(" Customer's Flat No Is = "+flat);
		wUtil.scrollToParticularElement(lp.AreaFld);
		String area=lp.Area(jUtil.randome5string());
		ListnerClass.reportLog(" Customer's Area Is = "+area);
//		String pan=lp.Pan("BTLPN0219B");
//		ListnerClass.reportLog(" Customer's Pan No Is = "+pan);
		
		lp.Continue();
		String query_no=DataBaseUtility.getQuery("loancode", no);
		String loancode=dbUtil.ExecuteQuery(query_no);
		ListnerClass.reportLog("The loancode is = "+loancode);
		ListnerClass.reportLog(" NLP-5 < 2lakh MTO Presanction Journey Completed  , Thank You!!");
		
		
		
	}
	
	
	//@AfterClass
	public void Query() throws Throwable 
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
