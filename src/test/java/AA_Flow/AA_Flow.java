package AA_Flow;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;

import POM_AA_Flow.*;
import PropertyFileConfig.ObjectReaders;


//@Listeners(GenericUtilities.ExtentReport.class)
public class AA_Flow extends BaseClass {

	WebDriver driver;
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	
	
	
	@Test(priority=0)
    public void OfferlandingPage() throws Throwable 
	{
	     String URL=ObjectReaders.readers.getNLP_1();
		
		driver.get(URL);
		OfferlandingPage lp = new OfferlandingPage(driver);
		lp.EnterMobileField(jUtil.randomMobileNo());
		
		
		lp.GetStartedBtn();
		
	}
	
	
	@Test(priority=1)
	public void FormOne() 
	{
		
		Details_1_Page dp = new Details_1_Page(driver);
		dp.AvgMonthlySalesField(jUtil.randomMonthlySalegreaterthan2());
		dp.GstYes();
		dp.ClickOnConBTn();
	}
	
	
	@Test(priority=2)
	public void FormTwo_GreaterThan_2Lak() 
	{
		
		Details_2_Page dp = new Details_2_Page(driver);
		dp.EnterFullName("CHILKEWAR MEDICAL AND DISTRIBUTORS");
	    dp.EnterEmailId(jUtil.randomEmailId());
	    dp.ClickOnContinueBtn();
		
	}
	
	@Test(priority=3)
	public void AABank_Page() 
	{
		AA_bankPage_Try_AnotherMethod aa = new AA_bankPage_Try_AnotherMethod(driver);
		aa.TryAnotherMethod();
	}
	
	
	@Test(priority=4)
	public void bank_statement() throws InterruptedException 
	{
		
		Bank_Statement bs = new Bank_Statement(driver);
		bs.ClickOnUploadManuallyBtn();
		
		
		jse.executeAsyncScript("arguments[0].click();", bs.UploadBtn);
		bs.ClickOnUploadBtn();
		bs.ClickOnChooseDocumentBtn("./RealAccount4Apr.pdf");
		Thread.sleep(100000);
		bs.ClickOnVerify();
		bs.ClickOnContinueBtn();
	}
	
	@Test(priority=5)
	public void FormTwo() 
	{
		
		LoneRequirment lr = new LoneRequirment(driver);
		lr.LoanAmount(jUtil.randomMonthlySaleLesserthan2());
		lr.BusinessManIcon();
		lr.ClickOnContinueBtn();
	}
	
	
	@Test(priority=6)
	public void FormThree() 
	{
		
		Details_3_Page dp = new Details_3_Page(driver);
		dp.MaleIcon();
		dp.DOBField("30091997");
		dp.PinCodeField("400001");
		jse.executeScript("window.scrollBy(0,1000)", "");
		dp.CurrentAccountIcon();
		dp.BussinessRegisterd();
		dp.Continue();
			
		
	}
	
	@Test(priority=7)
	public void  FormFour() 
	{
		
		Details_4_Page dp = new Details_4_Page(driver);
		dp.PanNo(jUtil.generateRandomPAN());
		dp.BusinessPinCode("400001");
		jse.executeScript("window.scrollBy(0,1000)", "");
		dp.RentedResident();
		dp.RentedBusiness();
		dp.HowOldIsBusiness();
		dp.clickOncontinue();
			
		
	}
	
	
	@Test(priority=8)
	public void FormFive() 
	{
	
		Details_5_Page dp = new Details_5_Page(driver);
		dp.NatureOfBusiness();
		dp.ProductDropDown();
		dp.SearchBox();
		dp.ClickSubmitBtn();
		dp.BusinessName(jUtil.randome5string());
		dp.ShopNoAndStreetName(jUtil.randome5string());
		dp.Locality(jUtil.randome5string());
		dp.FlatNo(jUtil.randome5string());
		dp.Village(jUtil.randome5string());
		dp.ClickOncontinueBtn();
		
		
		
	}
	
	
	@Test(priority=9)
	public void Upload_Document()
	{
		
		Details_Document_Upload_Page  dp = new Details_Document_Upload_Page(driver);
		dp.ClickOnUploadBtn();
		dp.GstRegistrationCertificateIcon("/Users/fllap0570_maheshnarvane/eclipse-workspace/Project_01/Documents/dave-ruck-OqaavL4MpJQ-unsplash.jpg");
		
		System.out.println(" AA_Flow PreSanction Journey Completed,  Thank You!!");
		System.out.println(" This Folw is for Monthly sale> 2000000 ");
	}
	
	
	
}
