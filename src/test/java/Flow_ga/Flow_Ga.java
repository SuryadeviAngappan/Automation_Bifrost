package Flow_ga;

import java.sql.SQLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import Listner.ListnerClass;
import POM_Flow_ga.*;
import Pract.Doc;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;


@Listeners(Listner.ListnerClass.class)
public class Flow_Ga extends BaseClass

{

	DataBaseUtility dbutil = new DataBaseUtility();
	DocumentUpload upload= new DocumentUpload();
	Doc doc= new Doc();
	String Otp;
	public  String no;
	public  String loancode;


	@BeforeClass
	public void launchURL() throws Throwable 
	{

		String URL="https://loans-staging.flexiloans.com/?nlp=1&journeyName=flow_ga";/*ObjectReaders.readers.getNLP_1();*/
		driver.get(URL);

	}


	@Test(priority=1)
	public void landing_Page_NLP1_MTO_GT_2() throws Throwable 
	{

		landing lp = new landing(BaseClass.driver);	

		String name=lp.Name(ObjectReaders.readers.get_FullName());
		ListnerClass.reportLog(" Name Of Customer ="+name);
		no=lp.MobileNo(jUtil.random9NoFlipkart());
		ListnerClass.reportLog("The Number is ="+no);
		String Gst=lp.GSTCheckYesBox();
		ListnerClass.reportLog("Gst registerd ? = "+Gst);
		String sale=lp.AVGMonthlySale();
		ListnerClass.reportLog("The Average Monthly Sale is = "+sale);
		lp.CheckLoanEligibility();

	}

	@Test(priority=2)
	public void OTP() throws SQLException
	{
		otp lp = new otp(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver,lp.Otp);	

		String otpquery=dbUtil.getQuery("otp", no);
		Otp=dbUtil.ExecuteQuery(otpquery);
		ListnerClass.reportLog(" OTP For Customer's No.Is  = " +Otp);
		String otp=lp.OTP(Otp);
		lp.Verify_Otp();


	}

	@Test(priority=3)
	public void form2() throws SQLException 
	{
		form2 lp = new form2(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.PanNoFld);

		String pan_no=lp.PanNo("BTLPN0219B");
		ListnerClass.reportLog(" PanNo is = "+pan_no);
		String dob=lp.DOB("30091997");
		ListnerClass.reportLog(" DOB is = "+dob);
		String code=lp.PIN_CODE("431515");
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

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.FullName_Yes);
		String name=lp.FullName();
		ListnerClass.reportLog(" Customers Full Name = "+name);
		String add=lp.CurrentAdd();
		ListnerClass.reportLog(" Customer's Current Address = "+add);
		String owned=lp.ResidenceOwned();
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

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.BusinessName_Yes);

		String name=lp.BusinessName();
		ListnerClass.reportLog("Name Of Business ="+name);
		String add=lp.Current_Business_Address();
		ListnerClass.reportLog("Current Business Address ="+add);
		String owned=lp.Owned_Business();
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

		ListnerClass.reportLog(" flow_ga > 2lakh MTO Presanction Journey Completed  , Thank You!!");

	} 

	@AfterClass
	public void query() throws Throwable 
	{

		//loancode
		String query_no=DataBaseUtility.getQuery("loancode", no);
		String loancode=dbUtil.ExecuteQuery(query_no);
		ListnerClass.reportLog("The loancode is = "+loancode);
		//mobile_n0
		String query_no1=DataBaseUtility.getQuery("mobile_no", loancode);
		String mobile_no=dbUtil.ExecuteQuery(query_no1);
		System.out.println("mobile_no  "+mobile_no);	

		//name
		String query1=DataBaseUtility.getQuery("name", no);
		String name=dbUtil.ExecuteQuery(query1);
		System.out.println("name  ="+name);

		//email
		String query2=DataBaseUtility.getQuery("email", no);
		String email=dbUtil.ExecuteQuery(query2);
		System.out.println("email  ="+email);

		//dob
		String query3=DataBaseUtility.getQuery("dob", no);
		String dob=dbUtil.ExecuteQuery(query3);
		System.out.println("dob  ="+dob);

		//gender
		String query4=DataBaseUtility.getQuery("gender", no);
		String gender=dbUtil.ExecuteQuery(query4);
		System.out.println("gender  ="+gender);

		//address_flat_no
		String query5=DataBaseUtility.getQuery("address_flat_no", no);
		String address_flat_no=dbUtil.ExecuteQuery(query5);
		System.out.println("address_flat_no  ="+address_flat_no);

		//address_building
		String query6=DataBaseUtility.getQuery("address_building", no);
		String address_building=dbUtil.ExecuteQuery(query6);
		System.out.println("address_building  ="+address_building);

		//address_area
		String query7=DataBaseUtility.getQuery("address_area", no);
		String address_area=dbUtil.ExecuteQuery(query7);
		System.out.println("address_area  ="+address_area);

		//address_city
		String query8=DataBaseUtility.getQuery("address_city", no);
		String address_city=dbUtil.ExecuteQuery(query8);
		System.out.println("address_city  ="+address_city);

		//address_state
		String query9=DataBaseUtility.getQuery("address_state", no);
		String address_state=dbUtil.ExecuteQuery(query9);
		System.out.println("address_state  ="+address_state);

		//address_pincode
		String query10=DataBaseUtility.getQuery("address_pincode", no);
		String address_pincode=dbUtil.ExecuteQuery(query10);
		System.out.println("address_pincode  ="+address_pincode);

		//address_ownership_status
		String query11=DataBaseUtility.getQuery("address_ownership_status", no);
		String address_ownership_status=dbUtil.ExecuteQuery(query11);
		System.out.println("address_ownership_status  ="+address_ownership_status);

		//pan_no
		String query12=DataBaseUtility.getQuery("pan_no", no);
		String pan_no=dbUtil.ExecuteQuery(query12);
		System.out.println("pan_no  ="+pan_no);

		//date_of_incorporation
		String query13=DataBaseUtility.getQuery("date_of_incorporation", loancode);
		String date_of_incorporation=dbUtil.ExecuteQuery(query13);
		System.out.println("date_of_incorporation  ="+date_of_incorporation);

		//business_address_pincode
		String query14=DataBaseUtility.getQuery("business_address_pincode", loancode);
		String business_address_pincode=dbUtil.ExecuteQuery(query14);
		System.out.println("business_address_pincode  ="+business_address_pincode);

		//business_address_building
		String query15=DataBaseUtility.getQuery("business_address_building", loancode);
		String business_address_building=dbUtil.ExecuteQuery(query15);
		System.out.println("business_address_building  ="+business_address_building);

		//business_address_area
		String query16=DataBaseUtility.getQuery("business_address_area", loancode);
		String business_address_area=dbUtil.ExecuteQuery(query16);
		System.out.println("business_address_area  ="+business_address_area);

		//business_address_city
		String query17=DataBaseUtility.getQuery("business_address_city", loancode);
		String business_address_city=dbUtil.ExecuteQuery(query17);
		System.out.println("business_address_city  ="+business_address_city);

		//business_address_state
		String query18=DataBaseUtility.getQuery("business_address_state", loancode);
		String business_address_state=dbUtil.ExecuteQuery(query18);
		System.out.println("business_address_state  ="+business_address_state);

		//drug_license_no
		String query19=DataBaseUtility.getQuery("drug_license_no", loancode);
		String drug_license_no=dbUtil.ExecuteQuery(query19);
		System.out.println("drug_license_no  ="+drug_license_no);

		//fssai_no
		String query20=DataBaseUtility.getQuery("fssai_no", loancode);
		String fssai_no=dbUtil.ExecuteQuery(query20);
		System.out.println("fssai_no  ="+fssai_no);

		//monthly_total_sales
		String query21=DataBaseUtility.getQuery("monthly_total_sales", loancode);
		String monthly_total_sales=dbUtil.ExecuteQuery(query21);
		System.out.println("monthly_total_sales  ="+monthly_total_sales);

		//business_address_ownership_status
		String query22=DataBaseUtility.getQuery("business_address_ownership_status", loancode);
		String business_address_ownership_status=dbUtil.ExecuteQuery(query22);
		System.out.println("business_address_ownership_status  ="+business_address_ownership_status);

		//business_vintage
		String query23=DataBaseUtility.getQuery("business_vintage", loancode);
		String business_vintage=dbUtil.ExecuteQuery(query23);
		System.out.println("business_vintage  ="+business_vintage);

		//is_salaried
		String query24=DataBaseUtility.getQuery("is_salaried", loancode);
		String is_salaried=dbUtil.ExecuteQuery(query24);
		System.out.println("is_salaried  ="+is_salaried);

		//nature_of_business
		String query25=DataBaseUtility.getQuery("nature_of_business", loancode);
		String nature_of_business=dbUtil.ExecuteQuery(query25);
		System.out.println("nature_of_business  ="+nature_of_business);

		//selling_products
		String query26=DataBaseUtility.getQuery("selling_products", loancode);
		String selling_products=dbUtil.ExecuteQuery(query26);
		System.out.println("selling_products  ="+selling_products);

		//is_current_account_available
		String query27=DataBaseUtility.getQuery("is_current_account_available", loancode);
		String is_current_account_available=dbUtil.ExecuteQuery(query27);
		System.out.println("is_current_account_available  ="+is_current_account_available);

		//cibil_score
		String query28=DataBaseUtility.getQuery("cibil_score", loancode);
		String cibil_score=dbUtil.ExecuteQuery(query28);
		System.out.println("cibil_score  ="+cibil_score);

	}

}


