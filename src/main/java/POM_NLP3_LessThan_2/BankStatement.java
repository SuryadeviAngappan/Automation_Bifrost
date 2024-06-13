package POM_NLP3_LessThan_2;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import PropertyFileConfig.ObjectReaders;

public class BankStatement {

	
	
	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
	
	
	    // Deceleration
	
		@FindBy(xpath="//span[.='Upload manually']")
		public WebElement UploadManuallyBtn;
		
		@FindBy(xpath="//button[.='Select bank statement']")
		public WebElement SelectBankStatement;
		
		@FindBy(xpath="//input[@type='file']")
		public WebElement ChooseDocumentBtn;
		
		@FindBy(xpath="//span[.=' Continue ']")
		public WebElement ContinueBtn;
			
		@FindBy(xpath="//span[.=' Verify ']")
		public WebElement VerifyBtn;
		
		
		
		
		// Initialization

		public BankStatement (WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}
		
		
		// Utilization

		public WebElement getUploadManuallyBtn() {
			return UploadManuallyBtn;
		}

		public WebElement getUploadBtn() {
			return SelectBankStatement;
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
		
		public void ClickOnUploadBtn()
		
		{
			SelectBankStatement.click();
		}	
		
		public void ClickOnChooseDocumentBtn(String file) throws Throwable 
		{
			
		
			Thread.sleep(2000);
			((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')", ChooseDocumentBtn);
			Thread.sleep(2000);
			ChooseDocumentBtn.sendKeys(file);
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
