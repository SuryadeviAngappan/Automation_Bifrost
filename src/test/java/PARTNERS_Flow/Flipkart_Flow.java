package PARTNERS_Flow;

import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import POM_Flipkart_Flow.*;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;

@Listeners(Listner.ListnerClass.class)
public class Flipkart_Flow extends BaseClass{

	
	DocumentUpload upload = new  DocumentUpload();
	JavascriptExecutor js= (JavascriptExecutor)BaseClass.driver;
	WebDriverWait wait;
	String no;
	String loancode;
	
	
	@BeforeClass
	public void launchURL() throws Throwable 
	{
     
		String url=ObjectReaders.readers.Flipkart();
		driver.get(url);
 
	}

	
	
	@Test(priority=1)
	public void landingPage_Flipkart() throws Throwable 
	{
		
		Landing_Page_1 lp = new Landing_Page_1(BaseClass.driver);
		
		no=lp.MobileNo(jUtil.random9NoFlipkart());
		ListnerClass.reportLog(" The Mobile No = "+no);
		lp.LoanElgibility();
		

	}
	
	@Test(priority=2)
	public void Form_One() throws Throwable 
	{
		Form_Details1_2 lp = new Form_Details1_2(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.FullNameTxtFld);
		String name=lp.FullName(ObjectReaders.readers.get_FullName());
		ListnerClass.reportLog(" Name Of Customer = "+name);
		String email=lp.Email(jUtil.randomEmailId());
		ListnerClass.reportLog(" Email Of Customer = "+email);
		String loan=lp.LoanRequirmentIcon();
		ListnerClass.reportLog(" Loan Requirment = "+loan);
		String businessname=lp.BusinessName(jUtil.randome5string());
		ListnerClass.reportLog(" Buiness Name = "+businessname);
		wUtil.scrollToParticularElement(lp.ResidenceRentedicon);
		String rent=lp.ResidenceRented();
		ListnerClass.reportLog(" Residence Rented ? = "+rent);
		wUtil.scrollToParticularElement(lp.BusinessRentedicon); 
		String busi=lp.BusinessRented();
		ListnerClass.reportLog(" Business Rented ? = "+busi);
		String prop=lp.Propritership();
		ListnerClass.reportLog(" Business Registerd As = "+prop);
		lp.Continue();
		
	}
	
	@Test(priority=3)
	public void Form_two() throws InterruptedException, SQLException 
	{
		Form_Details2_3 lp = new Form_Details2_3(BaseClass.driver);
		
		String query=dbUtil.getQuery("loancode", no);
		loancode=dbUtil.ExecuteQuery(query);
		ListnerClass.reportLog(" The LoanCode  = "+loancode);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.GenderIcon);
		String gender=lp.Gender();
		ListnerClass.reportLog(" Gender = "+gender);
		String dob=lp.DOB("30091997");
		ListnerClass.reportLog(" DOB = "+dob);
		String pin=lp.ResidencePinCode("400001");
		ListnerClass.reportLog(" Residence Pincode = "+pin);
		String pan=lp.PanNo("BTLPN0219B");
		ListnerClass.reportLog(" PanNo = "+pan);
		lp.ShowLoanOffer();
		wUtil.waitAndClickOnProceedBtn(lp.ProceedBtn);
	}
	
	
	@Test(priority=4)
	public void Form_three() throws InterruptedException 
	{	
		Form_Details3_4 lp = new Form_Details3_4(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.ResidencialAddressFld);
		String resi=lp.ResidencialAddress(jUtil.randome10string());
		ListnerClass.reportLog(" Residencial  Address = "+resi);
		String area=lp.Residencial_Area_Locality_Village(jUtil.randome10string());
		ListnerClass.reportLog(" Locality = "+area);
		String street=lp.ShopNo_StreetName(jUtil.randome10string());
		ListnerClass.reportLog(" Street Name = "+street);
		String locality=lp.Business_Area_Locality_Village(jUtil.randome10string());
		ListnerClass.reportLog(" Business Area = "+locality);
		String pin=lp.Shop_Picode("400001");
		ListnerClass.reportLog(" Shop PinCode= "+pin);
		lp.Continue();
			
	}
	
	@Test(priority=5)
	public void Bank_Account() throws InterruptedException 
	{
		Bank_Account_5 lp =new  Bank_Account_5(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.BankAccountNoTxtFld);
		String no=lp.BankAccountNo("62441635074");
		ListnerClass.reportLog(" Account No= "+no);
		lp.ReEnterBankAccountNo("62441635074");
		String ifsc=lp.IFSCCode("SBIN0020030");
		ListnerClass.reportLog(" IFSC No= "+ifsc);
		wUtil.scrollToParticularElement(lp.AccountTypeDropDownBtn);
		String acc=lp.AccountTypeDropDown();
		ListnerClass.reportLog(" Account Type= "+acc);
		String benificary=lp.BeneficiaryName(jUtil.randome10string());
		ListnerClass.reportLog(" Benificary Name= "+benificary);
		lp.Confirm();
		
		
	}
	
	
	@Test(priority=6)
	public void Bank_statement() throws Throwable 
	{
		Bank_Statement_6 lp = new Bank_Statement_6(BaseClass.driver);
	   
		wUtil.waitAndClickOnElement(lp.UploadManuallyBtn);
		wUtil.scrollToParticularElement(lp.SelectBankStatementBtn);
	    lp.ClickOnUploadBtn();
	    lp.ClickOnChooseDocumentBtn(upload.documentWorkFlow(Filepath.BankStatementFilePath));
		
	    ListnerClass.reportLog(" BankStatement Uploaded Successfully ");
		
		
	}
	
	@Test(priority=7)
	public void Document_upload() throws Throwable 
	{
		Document_Upload_Page_7 lp = new Document_Upload_Page_7(BaseClass.driver);
		
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
		Thread.sleep(2000);
		lp.PanCard(upload.documentWorkFlow(Filepath.DocumentFilePath));
		wUtil.waitAndClickOnElement(lp.UploadBtn);
		lp.ProofOfCurrentResidentialAddress(upload.documentWorkFlow(Filepath.DocumentFilePath));
		wUtil.waitAndClickOnElement(lp.UploadBtn);
		lp.ProofOfBusinessAddress(upload.documentWorkFlow(Filepath.DocumentFilePath));
		wUtil.waitAndClickOnElement(lp.UploadBtn);
		lp.ProofOfFinancials(upload.documentWorkFlow(Filepath.DocumentFilePath));
		
		ListnerClass.reportLog(" Document's Uploaded Successfully ");
		
		ListnerClass.reportLog(" Flipkart PreSanction Journey Completed,  Thank You!!");
		
		
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
