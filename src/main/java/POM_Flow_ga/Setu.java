package POM_Flow_ga;

import org.checkerframework.checker.interning.qual.FindDistinct;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class Setu {

	
	@FindBy(xpath="//span[.='Connect using OTP']")
	public WebElement Connect_using_otp;
	
	@FindBy(xpath="//div[.='What is OTP based consent?']")
	public WebElement OTP_Based;
	
	@FindBy(xpath="//span[.='Proceed']")
	public WebElement proceed;
	
	@FindBy(xpath="//button[.='Cancel']")
	public WebElement CancleBtn;
	
	@FindBy(xpath="//button[.='Cancel data sharing']")
	public WebElement CancleDataSharingBtn;
	
	@FindBy(xpath="//label[.='I do not want to share my data with Flexiloans-sandbox']")
	public WebElement CancleOption;
	
	
	
	
	// AA Flow
	
	
	
	@FindBy(xpath="//button[.='Login to Onemoney']")
	public WebElement LogintoOnemoney;
	
	@FindBy(xpath="//aside//button[@type='submit'][normalize-space()='Login']")
	public WebElement LoginBtn;
	
	@FindBy(xpath = "//body//div//div//div//div//div//div//div//div//div[1]//div[1]//div[2]//div[1]//*[name()='svg']")
    public WebElement SearchBtn;	
	
	@FindBy(xpath="//input[@placeholder='Search']")
	public WebElement Searchfield;
	
	@FindBy(xpath="//p[normalize-space()='Acme Bank Ltd.']")
	public WebElement bank;
	
	
	@FindBy(xpath="//button[normalize-space()='Fetch my accounts'][1]")
	public WebElement FetchAcc;
	
	
	@FindBy(xpath="//p[normalize-space()='Acme Bank Ltd.']")
	public WebElement AccDetail;
	
	
	@FindBy(xpath="//p[normalize-space()='Current account']")
	public WebElement currentAcc;
	
	@FindBy(xpath="//button[.='Verify accounts'][2]")
	public WebElement VerifyAcc;
	
	@FindBy(xpath="//body//div//main//div//aside//div//div//div//button[contains(text(),'Continue to approval')]")
	public WebElement ContinueApproval;
	
	@FindBy(xpath="//button[normalize-space()='Approve data sharing']")
	public WebElement ApproveDataSharing;
	
	@FindBy(xpath="//p[.='Do not want to share data?']")
	public WebElement Reject;
	
	
	public Setu(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getCancleBtn() {
		return CancleBtn;
	}



	public WebElement getCancleDataSharingBtn() {
		return CancleDataSharingBtn;
	}



	public WebElement getCancleOption() {
		return CancleOption;
	}
	
	
	// AA Flow
	
	
	public void LogintoOnemoney()
	{
		LogintoOnemoney.click();
	}
	
	public void Login() 
	{
		LoginBtn.click();
	}
	
	public void Bank() 
	{
		bank.click();
	}
	
	
	
	// Default flow
	
	public void Cancle() 
	{
		CancleBtn.click();
		
	}
	
	public void Option() 
	{
		CancleOption.click();
	}
	
	public void CancleDataSharing() 
	{
		CancleDataSharingBtn.click();
	}
	
	public void Search(String name) throws InterruptedException 
	{
		SearchBtn.click();
		Thread.sleep(1000);
		Searchfield.sendKeys(name);
	}
	
	
	public void FetchAccount() 
	{
		FetchAcc.click();
	}
	
	
	public void VerifyAccount() 
	{
	    JavascriptExecutor executor = (JavascriptExecutor) BaseClass.driver;
	    executor.executeScript("arguments[0].click();", VerifyAcc);


		//VerifyAcc.click();
	}
	
	public void ContinueApproval() 
	{
		ContinueApproval.click();
	}
	
	public void currentAcc() 
	{
		currentAcc.click();
	}
	
	public void ApproveDataSharing() 
	{
		ApproveDataSharing.click();
	}
	
	public void Connect_using_otp() throws InterruptedException 
	{
		Connect_using_otp.click();
		Thread.sleep(1000);
		OTP_Based.click();
		Thread.sleep(1000);
		proceed.click();
		Thread.sleep(1000);
	}
}
