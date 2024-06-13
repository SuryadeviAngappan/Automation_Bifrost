package Default_Flow;

import java.sql.SQLException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import Listner.ListnerClass;
import POM_Default_Flow.*;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;



@Listeners(Listner.ListnerClass.class)
public class Default_Flow extends BaseClass{

	DocumentUpload upload = new DocumentUpload();
    String no;
	String otp;
	String loancode;


	@BeforeClass
	public void launchURL() throws Throwable 
	{
		String url= ObjectReaders.readers.getDefault();
		driver.get(url);
	}


	@Test(priority=1)
	public void landing_Defaulta_Flow() 
	{
		landing lp = new landing(BaseClass.driver); 

	    no=lp.MobileNo(jUtil.random9NoFlipkart());
		ListnerClass.reportLog(" Mobile No ="+no);
		lp.CheckLoanEligibility();
		 

	}

	@Test(priority=2)
	public void form1() 
	{

		form1 lp = new form1(BaseClass.driver);
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.MonthlySaleFld);
		String sale=lp.MonthlySale(jUtil.randomMonthlySalegreaterthan2());
		ListnerClass.reportLog(" Monthly Sale ="+sale);
		String gst=lp.GST();
		ListnerClass.reportLog(" GST Registerd ? ="+gst);
		lp.Continue();
	}


	@Test(priority=3)
	public void form2() throws Throwable 
	{
		form2 lp = new form2(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.FullNameFld);
		String name= lp.FullName(ObjectReaders.readers.get_FullName());
		ListnerClass.reportLog(" Customer's Full Name ="+name);
		lp.Continue();
	}

	@Test(priority=4)
	public void form3() throws InterruptedException 
	{

		form3 lp = new form3(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NatureOfBusinessIcon);
		String nature=lp.BusinessNature();
		ListnerClass.reportLog("Business Nature ="+nature);
		wUtil.scrollToParticularElement(lp.ContinueBtn);
		String product=lp.ProductYouSell();
		ListnerClass.reportLog(" Customer Selling Product ="+product);
		String busi=lp.HowOldBusiness();
		ListnerClass.reportLog(" Business vintage ="+busi);
		String code=lp.ShopPincode("431515");
		ListnerClass.reportLog(" Shop PinCode ="+code);
		String acc=lp.CurrentAcc();
		ListnerClass.reportLog(" Account Type ="+acc);
		lp.Continue();
	}

	
	@Test(priority=5)
	public void form_2() 
	{
		form2 lp = new form2(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.PanNoFld);
		String no=lp.PanNo("BTLPN0219B");
		ListnerClass.reportLog(" PanNo ="+no);
		lp.Continue();
		
	}

	
	@Test(priority=6)
	public void form_3() throws InterruptedException 
	{
		
		form3 lp = new form3(BaseClass.driver);
		
		wUtil.waitAndClickOnElement(lp.GenderIcon);
		String gender=lp.Gender();
		ListnerClass.reportLog(" Gender ="+gender);
		String dob=lp.DOB("30091997");
		ListnerClass.reportLog(" DOB ="+dob);
		String pin=lp.ResidencialPincode("431515");
		ListnerClass.reportLog(" Residencial PinCode ="+pin);
		lp.Continue();
			
	}
	
	
	@Test (priority=7)
	public void setu() throws Throwable 
	{
		setu lp = new setu(BaseClass.driver);

		wUtil.waitAndClickOnElement(lp.CancleBtn);
		lp.Option();
		lp.CancleDataSharing();		
		ListnerClass.reportLog(" Setu page Cancled, To Upload BS Manually");
	}


	@Test(priority=8)
	public void BankStatement() throws Throwable 
	{

		BankStatement lp = new BankStatement (BaseClass.driver);

		wUtil.waitAndClickOnElement(lp.UploadManuallyBtn);
		wUtil.scrollToParticularElement(lp.SelectBankStatement);
		lp.ClickOnUploadBtn();
		lp.ClickOnChooseDocumentBtn(upload.documentWorkFlow(Filepath.BankStatementFilePath));

		ListnerClass.reportLog(" BankStatement Uploaded Successfullt !!");
	}


	@Test(priority=9)
	public void DocumentUpload() throws Throwable 
	{
		UploadDocument lp = new UploadDocument(BaseClass.driver);


		wUtil.waitAndClickOnElement(lp.ContinueBtn);
		String originalHandle=driver.getWindowHandle();
		Thread.sleep(2000);
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

		ListnerClass.reportLog(" Document's Uploaded Successfullt !!");
		wUtil.waitAndClickOnProceedBtn(lp.ProceedBtn);

	}



	@Test(priority=10)
	public void form4() throws Throwable 
	{

		form4 lp = new form4(BaseClass.driver);	

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.BusinessNameFld);

		String name=lp.BusinessName(jUtil.randome5string());
		ListnerClass.reportLog("Business Name = "+name);
		String prop=lp.Propritership();
		ListnerClass.reportLog("Business Registerd as = "+prop);
		String no=lp.ShopNo(jUtil.random4numeric());
		ListnerClass.reportLog(" Customer Shop-Number Is = "+ no);
		String area=lp.Area(jUtil.randome5string());
		ListnerClass.reportLog(" Customer Shop Area Is = "+area);
		lp.Continue(); 



	}

	@Test(priority=11)
	public void form5() throws InterruptedException, SQLException 
	{

		form5 lp = new form5(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.EmailFld);
//		String gender=lp.Gender();
//		ListnerClass.reportLog(" Gender Of Customer Is = "+gender);
//		String dob=lp.DOB("30091997");
//		ListnerClass.reportLog(" DOB Of Customer Is = "+dob);
		String id=lp.Email(jUtil.randomEmailId());
		ListnerClass.reportLog(" Customer's Email-Id Is = "+id);
		String pincode=lp.PinCode("400001");
		ListnerClass.reportLog("Customer's Pincode Is = "+pincode);
		String flat=lp.Flat(jUtil.random4numeric());
		ListnerClass.reportLog(" Customer's Flat No Is = "+flat);
		wUtil.scrollToParticularElement(lp.AreaFld);
		String area=lp.Area(jUtil.randome5string());
		ListnerClass.reportLog(" Customer's Area Is = "+area);
		
		lp.Continue();

		//loancode
		String query_no=dbUtil.getQuery("loancode", no);
		String loancode=dbUtil.ExecuteQuery(query_no);
		ListnerClass.reportLog("The loancode is = "+loancode);

		ListnerClass.reportLog(" Default Presanction Journey Completed  , Thank You!!");



	} 




}
