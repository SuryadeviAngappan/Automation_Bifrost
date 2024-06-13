package POM_NLP3_GreaterThan_2;

import java.io.File;
import PropertyFileConfig.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import GenericUtilities.WebDriverUtility;
import PropertyFileConfig.ObjectReaders;

public class BankStatement {
	
	// Deceleration

		@FindBy(xpath="//span[.='Upload manually']")
		public WebElement UploadManuallyBtn;

		@FindBy(xpath="//button[.='Select bank statement']")
		public WebElement SelectBankStatement;

		@FindBy(xpath="//input[@type='file']")
		public WebElement ChooseDocumentBtn;

		@FindBy(xpath="//span[.=' Continue ']")
		public WebElement ContinueBtn;

		@FindBy(xpath="//span[.=' Verify and upload']")
		public WebElement VerifyBtn;




		// Initialization

		public BankStatement( WebDriver driver)
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
			//SelectBankStatement.click();
			((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();", SelectBankStatement);
		}	

		public void ClickOnChooseDocumentBtn(String file) throws Throwable 
		{

			((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')", ChooseDocumentBtn);
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
