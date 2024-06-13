package Flow_AHL_App;

import java.sql.SQLException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import Listner.ListnerClass;
import POM_Flow_AHL_app.*;
import Pract.Doc;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;

@Listeners(ListnerClass.class)
public class flow_ahl_app extends BaseClass{


	DocumentUpload upload = new DocumentUpload();
	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
	String loancode;
	String no;
	DataBaseUtility dbutil = new DataBaseUtility();
	Doc doc = new Doc();


	@BeforeClass
	public void launchURL() throws Throwable 
	{
		String url= ObjectReaders.readers.getFlow_AHL_app();
		driver.get(url);
	}

	@Test(priority=1)
	public void landing_Flow_AHL_App() throws InterruptedException 
	{
		landing lp = new landing(BaseClass.driver);

		no=lp.Mobile_No(jUtil.random9NoFlipkart());
		//ListnerClass.reportLog(" The Mobile No = "+no);
		//no=lp.mobile_no.getText();
		//System.out.println(" Mobile No = "+no);
		lp.Continue();

	}


	@Test(priority=2)
	public void form1() throws Throwable 
	{
		form1 lp = new form1(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.getFull_Name());

		String name=lp.Full_Name(ObjectReaders.readers.get_FullName());
		ListnerClass.reportLog("Customer's Full Name Is  = "+name);
		String mto=lp.MTO(jUtil.randomMonthlySalegreaterthan2());
		ListnerClass.reportLog("Customer's MTO Is  = "+mto);
		String yes=lp.Gst_Registerd();
		ListnerClass.reportLog(" Does Customer is GST Registerd ? = "+yes);
		lp.Continue();

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
		String query_no=DataBaseUtility.getQuery("loancode", no);
		loancode=dbUtil.ExecuteQuery(query_no);
		System.out.println("The loancode is = "+loancode);
		ListnerClass.reportLog("The loancode is = "+loancode);
		lp.Continue();          


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
		lp.FullName();
		lp.CurrentAdd();
		lp.ResidenceOwned();
		lp.Continue();

	}


	@Test(priority=6)
	public void BankStatement() throws Throwable 
	{
		BankStatement lp = new BankStatement(BaseClass.driver);

		wUtil.waitForElementToBeVisible(driver, lp.UploadManuallyBtn);
		String query="update loan_applicant_detail set name='SAJJAN STEEL' where loan_code='"+loancode+"'";
		dbutil.executeUpdateQuery(query);
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

		lp.BusinessName();
		lp.Current_Business_Address();
		lp.Owned_Business();
		lp.Continue();

	}

	@Test(priority=9)
	public void form5() throws InterruptedException, SQLException 
	{
		form5 lp = new form5(BaseClass.driver);

		wUtil.waitForElementToBeVisible(driver, lp.Nature_of_Business);
		lp.BusinessNature();
		lp.ProductYouSell();
		lp.Continue();
		wUtil.waitAndClickOnProceedBtn(lp.Proceed);
		//loancode

		ListnerClass.reportLog("The loancode is = "+loancode);
		ListnerClass.reportLog(" Flow_AHL_app Presanction Journey Completed  , Thank You!!");

	} 

}
