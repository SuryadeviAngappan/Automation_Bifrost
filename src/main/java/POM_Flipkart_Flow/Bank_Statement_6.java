package POM_Flipkart_Flow;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import PropertyFileConfig.ObjectReaders;

public class Bank_Statement_6 {

	
	

	
    JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
    
	
	
	// Deceleration
	
		@FindBy(xpath="//span[.='Upload manually']")
		public WebElement UploadManuallyBtn;
		
		@FindBy(xpath="//button[.='Select bank statement']")
		public WebElement SelectBankStatementBtn;
		
		@FindBy(xpath="//input[@type='file']")
		public WebElement ChooseDocumentBtn;
		
		@FindBy(xpath="//span[.=' Continue ']")
		public WebElement ContinueBtn;
		
		
		@FindBy(xpath="//span[.=' Verify ']")
		public WebElement VerifyBtn;
		
		// Initialization
		
		

		public Bank_Statement_6(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		
		// Utilization

		public WebElement getUploadManuallyBtn() {
			return UploadManuallyBtn;
		}

		public WebElement getUploadBtn() {
			return SelectBankStatementBtn;
		}

		public WebElement getChooseDocumentBtn() {
			return ChooseDocumentBtn;
		}

		public WebElement getContinueBtn() {
			return ContinueBtn;
		}
		
		
		public WebElement getVerifyBtn() {
			return VerifyBtn;
		}

		
		// Business Logic
		
		
		public void ClickOnUploadManuallyBtn() 
		
		{
			UploadManuallyBtn.click();
		}
		
		public void ClickOnUploadBtn() throws InterruptedException
		
		{
			Thread.sleep(2000);
			SelectBankStatementBtn.click();
		}	
		
		public void ClickOnChooseDocumentBtn( String path) throws Throwable 
		{
			Thread.sleep(1000);
			((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')", ChooseDocumentBtn);
			ChooseDocumentBtn.sendKeys(path);
			Thread.sleep(2000);
			ClickOnVerify();
			
			

		}	
		
		
		public void ClickOnContinueBtn() 
		{
			ContinueBtn.click();
		}
		
		
		public void ClickOnVerify() 
		{
			VerifyBtn.click();
		}


		
		
	
	
}
