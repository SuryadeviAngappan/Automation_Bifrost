package PARTNERS_Flow;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import Listner.ListnerClass;
import POM_NLP1_GreaterThan_2.UploadDocument;
import POM_Pine_Labs.*;
import Pract.Doc;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;

@Listeners(Listner.ListnerClass.class)
public class Xiaomi extends BaseClass{

	
	
	
	DocumentUpload upload= new DocumentUpload();
	String no;
	public  String loancode;
	Doc doc = new Doc();
	
	
	@BeforeClass
	public void launchURL() throws Throwable 
	{

		String URL=ObjectReaders.readers.getXiaomi();
		driver.get(URL);
	}

	
	
	
	@Test(priority=1)
	public void Xiaomi_landingPage() 
	{
		
		landing_Page lp = new landing_Page(BaseClass.driver);
		
		no=lp.MobileNo(jUtil.random9NoFlipkart());
		ListnerClass.reportLog(" The Mobile No ="+no);
		lp.CheckLoanEligibility();
		
	}
	
	@Test(priority=2)
	public void form1() throws Throwable 
	{
		form1 lp = new form1(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Full_Name);
		String name=lp.FullName(ObjectReaders.readers.get_FullName());
		ListnerClass.reportLog(" Customer's Full Name = "+name);
		lp.Continue();
	}
	
	
	@Test(priority=3)
	public void form2() throws Throwable 
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

	}
	
	
	
	@Test(priority=3)
	public void pull_CKYC() throws Throwable 
	{
		doc.aadhar(loancode);
		
	}
	
	
	@Test(priority=4)
	public void form3() throws InterruptedException 
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
		
	}
	
	

	@Test(priority=6)
	public void BankStatement() throws Throwable 
	{

		BankStatement lp = new BankStatement(BaseClass.driver);

		String name=ObjectReaders.readers.get_FullName();
		String query3="update loan_applicant_detail set name='"+name+"' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query3); 
		wUtil.waitAndClickOnElement(lp.UploadManuallyBtn);
		wUtil.scrollToParticularElement(lp.SelectBankStatement);
		lp.ClickOnUploadBtn();
		lp.ClickOnChooseDocumentBtn(upload.documentWorkFlow(Filepath.BankStatementFilePath));

		ListnerClass.reportLog(" BankStatement Uploaded Successfully !!");
	}

	@Test(priority=7)
	public void pob_document_upload() throws InterruptedException 
	{

		UploadDocument lp = new UploadDocument(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.gst);
		lp.GST_Certficate(upload.documentWorkFlow(Filepath.GSTCertficate)); 


	}
	
	@Test(priority=8)
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
	public void form5() throws InterruptedException
	{
		form5 lp = new form5(BaseClass.driver);
		
		
		wUtil.waitForElementToBeVisible(driver, lp.Nature_of_Business);
		String nature=lp.BusinessNature();
		ListnerClass.reportLog(" Nature Of Customer's Business ="+nature);
		String product=lp.ProductYouSell();
		ListnerClass.reportLog(" Product Sell's ="+nature);
		lp.Continue();
		wUtil.waitAndClickOnProceedBtn(lp.Proceed);

		ListnerClass.reportLog("The loancode is = "+loancode);
		ListnerClass.reportLog(" Xiaomi Presanction Journey Completed  , Thank You!!");

		
	}

		
	
}
