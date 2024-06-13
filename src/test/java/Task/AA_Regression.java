package Task;

import java.sql.SQLException;
import POM_NLP1_GreaterThan_2.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import Listner.ListnerClass;
import POM_flow_ahl.*;
import POM_flow_ahl.DigiLocker;
import POM_flow_ahl.form2;
import POM_flow_ahl.form3;
import POM_flow_ahl.form4;
import POM_flow_ahl.form5;
import POM_flow_ahl.landing;
import Pract.Doc;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;
@Listeners(Listner.ListnerClass.class)

public class AA_Regression extends BaseClass {

	Doc doc= new Doc();
	DataBaseUtility dbutil = new DataBaseUtility();
	DocumentUpload upload= new DocumentUpload();
	public  String no;
	public  String loancode;

	@BeforeClass
	public void launchURL() throws Throwable 
	{

		String URL=ObjectReaders.readers.getNLP_1();
		driver.get(URL);

	}


	@Test(priority=1)
	public void landing_page_flow_ahl() throws Throwable 
	{
		landing lp = new landing(BaseClass.driver);

		lp.Name(ObjectReaders.readers.get_FullName());
		no=lp.MobileNo("8605583583");
		System.out.println("Mobile No"+no);
		lp.GSTRegisterd();
		lp.MonthlySale();
		lp.Next();
	}


	@Test(priority=2)
	public void form2() throws SQLException 
	{
		form2 lp = new form2(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.PanNo);
		lp.PanNo("BTLPN0219B");
		lp.DOB("30091997");
		wUtil.scrollToParticularElement(lp.Gender);
		lp.PIN_No("431515");
		lp.Gender();
		String query_no=DataBaseUtility.getQuery("loancode", no);
		loancode=dbUtil.ExecuteQuery(query_no);
		System.out.println(" LoanCode = "+loancode);
		lp.Continue();


	}

	@Test(priority=3)
	public void poa_document_upload() throws InterruptedException 
	{

		DigiLocker lp = new DigiLocker(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Okey);

		String originalHandle=driver.getWindowHandle();
		lp.Okey();
		Thread.sleep(2000);
		for(String handle : driver.getWindowHandles()) 
		{
			if (!handle.equals(originalHandle)) 
			{
				driver.switchTo().window(handle);
				Thread.sleep(1000);
				lp.Aadhaar_No("811790024074");
				Thread.sleep(12000);
				lp.Next();
				Thread.sleep(18000);
				lp.Continue();
				lp.Digilocker_Pin();
				lp.Continue();
				lp.Allow();

			}
		}
		driver.switchTo().window(originalHandle);



	}

	@Test(priority=4)
	public void form3() throws SQLException 
	{

		form3 lp = new form3(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.FullName_Yes);
		lp.FullName();
		lp.CurrentAdd();
		lp.ResidenceOwned();
		lp.Continue();


	}



	@Test(priority=5)
	public void pob_document_upload() throws InterruptedException 
	{

		doc.Gst_Cerificate(loancode);
		String query="update loan_business_detail set gst_verified='1' where loan_code='"+loancode+"'";
		dbutil.executeUpdateQuery(query);

	}

	@Test(priority=5)
	public void Setu() throws InterruptedException, SQLException 
	{

		Setu lp = new Setu (BaseClass.driver);
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Connect_using_otp);
		lp.Connect_using_otp();
		wUtil.waitAndClickOnElement(lp.LogintoOnemoney);
		Thread.sleep(25000);
		lp.Login();
		wUtil.waitAndClickOnElement(lp.currentAcc);
		Thread.sleep(15000);
		lp.VerifyAccount();
		//wUtil.waitAndClickOnElement(lp.VerifyAcc);
		//Thread.sleep(2000);
		lp.ContinueApproval();
		lp.ApproveDataSharing();
		
		
	}
	
	
	@Test(priority=6)
	public void form4() 
	{

		form4 lp = new form4(BaseClass.driver);

		wUtil.waitForElementToBeVisible(driver, lp.Business_Name);		
		lp.Business_Name();
		lp.Business_Address();
		lp.Owned();
		lp.Confirm();		

	}

	@Test(priority=7)
	public void form5() throws InterruptedException, SQLException 
	{
		form5 lp = new form5(BaseClass.driver);

		wUtil.waitForElementToBeVisible(driver, lp.Nature_of_Business);

		lp.BusinessNature();
		lp.ProductYouSell();
		lp.Continue();
		wUtil.waitAndClickOnProceedBtn(lp.Proceed);



	}
	
	

@AfterClass

public void reset() throws SQLException 
{
	
	
	
	String query_no=DataBaseUtility.getQuery("loancode", no);
	String loancode=dbUtil.ExecuteQuery(query_no);
	System.out.println(" LoanCode  = "+loancode);
	
	String query1=dbUtil.getQuery("consent_id", loancode);
	String consent_id=dbUtil.ExecuteQuery(query1);
	System.out.println(" consent_id  = "+consent_id);
	
	String query2=dbUtil.getQuery("status_user_consent_details", consent_id);
	String status_user_consent_details=dbUtil.ExecuteQuery(query2);
	System.out.println(" status_user_consent_details  = "+status_user_consent_details);
	
	String query3=dbUtil.getQuery("status_fiu_transaction_session", consent_id);
	String status_fiu_transaction_session=dbUtil.ExecuteQuery(query3);
	System.out.println("status_fiu_transaction_session = "+status_fiu_transaction_session);
	
	String query4=dbUtil.getQuery("session_id", consent_id);
	String session_id =dbUtil.ExecuteQuery(query4);
	System.out.println("session_id = "+session_id);
			
	String query5=dbUtil.getQuery("Count_fip_transaction_details", session_id);
	String Count_fip_transaction_details = dbUtil.ExecuteQuery(query5);
	System.out.println("Count_fip_transaction_details  = "+ Count_fip_transaction_details);
	
	String query6=dbUtil.getQuery("aa_db_trn_cnt", session_id);
	String aa_db_trn_cnt = dbUtil.ExecuteQuery(query6);
	System.out.println("aa_db_trn_cnt  = "+aa_db_trn_cnt);
	
	String query7=dbUtil.getQuery("fl_bank_db_cnt", session_id);
	String fl_bank_db_cnt=dbUtil.ExecuteQuery(query7);
	System.out.println("fl_bank_db_cnt  = "+fl_bank_db_cnt);

    String query8=dbUtil.getQuery("bank_DB_transaction_count", loancode);
    String bank_DB_transaction_count=dbUtil.ExecuteQuery(query8);
    System.out.println("bank_DB_transaction_count  = "+bank_DB_transaction_count);
	
	
	String random=RandomStringUtils.randomAlphanumeric(8);
	String ran=RandomStringUtils.randomNumeric(9);
	
	String query="UPDATE flexiloans_staging_db.loan_aa_consent_details SET lead_code = '"+random+"' WHERE (loan_code = '"+loancode+"');";
	dbUtil.executeUpdateQuery(query);
	String query11 = "Set @MN = '8605583583'";
	dbUtil.executeUpdateQuery(query11);
	String query22="Set @random = '"+ran+"'";
	dbUtil.executeUpdateQuery(query22);
	String query33="update flexiloans_staging_db.loan_applicant_detail set mobile_no = @random where mobile_no = @MN";
	dbUtil.executeUpdateQuery(query33);
	String query44="update flexiloans_staging_db.lead set mobile_no = @random where mobile_no =@MN";
	dbUtil.executeUpdateQuery(query44);
	String query55="update flexiloans_staging_db.customer set mobile_no = @random where mobile_no =@MN";
	dbUtil.executeUpdateQuery(query55);
	
	
	
	}

}



