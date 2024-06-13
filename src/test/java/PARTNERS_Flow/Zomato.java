//package PARTNERS_Flow;
//
//import java.sql.SQLException;
//
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import GenericUtilities.BaseClass;
//import GenericUtilities.Filepath;
//import Listner.ListnerClass;
//import PropertyFileConfig.ObjectReaders;
//import WorkFlowLibrary.DocumentUpload;
//@Listeners(Listner.ListnerClass.class)
//public class Zomato extends BaseClass{
//
//	DocumentUpload upload= new DocumentUpload();
//	String no;
//	public  String loancode;
//
//	// pending 
//	@BeforeClass
//	public void launchURL() throws Throwable 
//	{
//
//		String URL=ObjectReaders.readers.getZomato();
//		driver.get(URL);
//	}
//
//
//	@Test(priority=1)
//	public void Zomato_landing_page() 
//	{
//		landing_Page lp = new landing_Page(BaseClass.driver);
//
//		no=lp.MobileNo(jUtil.random9NoFlipkart());
//		ListnerClass.reportLog(" The Mobile No ="+no);
//		lp.CheckLoanEligibility();
//	}
//
//	@Test(priority=2)
//	public void form1() 
//	{
//		form1 lp = new form1(BaseClass.driver);
//		
//		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.MonthlySaleTxtFld);
//		String sale=lp.MonthlySale(jUtil.randomMonthlySalegreaterthan2());
//		ListnerClass.reportLog(" Monthly Sale = "+sale);
//		String gst=lp.GstIcon();
//		ListnerClass.reportLog(" Gst registerd ? = "+gst);
//		lp.Continue();
//	}
//	
//	
//	@Test(priority=3)
//	public void form2() throws Throwable 
//	{
//         form2 lp = new form2(BaseClass.driver);		
//
//		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NameTxtField);
//		String name=lp.FullName(ObjectReaders.readers.get_FullName());
//		ListnerClass.reportLog("Custmer's Full_Name = "+name);
//		lp.Continue();
//
//	}
//	
//	
//	@Test(priority=4)
//	public void form3() throws InterruptedException 
//	{
//		form3 lp = new form3(BaseClass.driver);
//		
//		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NatureOfBusinesssIcon);
//		
//		String Nature=lp.NatureOfBusinesss();
//		ListnerClass.reportLog(" Nature Of The Business Is = "+ Nature);
//		String product=lp.SearchProductDropDown();
//		ListnerClass.reportLog(" Product Selected By Customer Is = "+product);
//		wUtil.scrollToParticularElement(lp.CurrentAcc);
//		String old=lp.HowOldBusiness();
//		ListnerClass.reportLog(" Business Vintage Is = "+old);
//		String pincode =lp.ShopPincode("400001");
//		ListnerClass.reportLog(" Pincode Of The Customer Is = "+pincode);
//		String acc=lp.CurrentAccount();
//		ListnerClass.reportLog(" Account Type = "+acc);
//		lp.Continue();
//		
//	}
//	
//	
//	@Test(priority=5)
//	public void Setu() throws InterruptedException 
//	{
//
//		Setu lp = new Setu(BaseClass.driver);
//
//		wUtil.waitAndClickOnElement(lp.CancleBtn);
//		lp.Option();
//		lp.CancleDataSharing();		
//		ListnerClass.reportLog(" Setu page Cancled, To Upload BS Manually");
//	}
//
//	@Test(priority=6)
//	public void BankStatement() throws Throwable 
//	{
//
//		BankStatement lp = new BankStatement(BaseClass.driver);
//
//		wUtil.waitAndClickOnElement(lp.UploadManuallyBtn);
//		wUtil.scrollToParticularElement(lp.SelectBankStatement);
//		lp.ClickOnUploadBtn();
//		lp.ClickOnChooseDocumentBtn(upload.documentWorkFlow(Filepath.BankStatementFilePath));
//
//		ListnerClass.reportLog(" BankStatement Uploaded Successfullt !!");
//	}
//
//	@Test(priority=7)
//	public void DocumentUpload() throws Throwable 
//	{
//		Upload_Document lp = new Upload_Document(BaseClass.driver);
//
//
//		wUtil.waitAndClickOnElement(lp.ContinueBtn);
//		Thread.sleep(1000);
//		String originalHandle=driver.getWindowHandle();
//
//		for(String handle : driver.getWindowHandles()) 
//		{
//			if (!handle.equals(originalHandle)) 
//			{
//				driver.switchTo().window(handle);
//				driver.close();
//			}
//		}
//		driver.switchTo().window(originalHandle);
//		wUtil.waitAndClickOnElement(lp.UploadBtn);
//		lp.PanCard(upload.documentWorkFlow(Filepath.DocumentFilePath));
//		wUtil.waitAndClickOnElement(lp.UploadBtn);
//		lp.ProofOfCurrentResidentialAddress(upload.documentWorkFlow(Filepath.DocumentFilePath));
//		wUtil.waitAndClickOnElement(lp.UploadBtn);
//		lp.ProofOfBusinessAddress(upload.documentWorkFlow(Filepath.DocumentFilePath));
//		
//		ListnerClass.reportLog(" Document's Uploaded Successfullt !!");
//		wUtil.waitAndClickOnProceedBtn(lp.ProceedBtn);
//		
//		
//	}
//	
//	@Test(priority=8)
//	public void form4() 
//	{
//		
//		form4 lp = new form4(BaseClass.driver);	
//
//		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.PropritershipIcon);
//		String BusinessName=lp.BusinessName(jUtil.randome5string());
//		ListnerClass.reportLog(" Business Name is = "+BusinessName);
//		String prop=lp.Propritership();
//		ListnerClass.reportLog("Business Registerd as = "+prop);
//		String no=lp.ShopNo(jUtil.random4numeric());
//		ListnerClass.reportLog(" Customer Shop-Number Is = "+ no);
//		String area=lp.Area(jUtil.randome5string());
//		ListnerClass.reportLog(" Customer Shop Area Is = "+area);
//		lp.Continue(); 
//		
//	}
//	
//	@Test(priority=9)
//	public void form5()
//	{
//		form5 lp = new form5(BaseClass.driver);
//		
//		
//		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.EmailIdTxtField);
//		String email=lp.EmailId(jUtil.randomEmailId());
//		ListnerClass.reportLog("Custmer's Email ID = "+email);
//		String pincode=lp.PinCode("400001");
//		ListnerClass.reportLog("Customer's Pincode Is = "+pincode);
//		String flat=lp.Flat(jUtil.random4numeric());
//		ListnerClass.reportLog(" Customer's Flat No Is = "+flat);
//		wUtil.scrollToParticularElement(lp.AreaFld);
//		String area=lp.Area(jUtil.randome5string());
//		ListnerClass.reportLog(" Customer's Area Is = "+area);
//		lp.Continue();
//
//		ListnerClass.reportLog(" Zomato Presanction Journey Completed  , Thank You!!");
//
//	}
//
//
//}
