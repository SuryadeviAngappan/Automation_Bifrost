package POM_Exaa_Offer_LessThan_2;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import PropertyFileConfig.ObjectReaders;

public class bank_statement {




	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;


	// Deceleration

	@FindBy(id="#manualUploadEnable2")
	public WebElement UploadManuallyBtn;

	@FindBy(xpath="//button[.='Select bank statement']")
	public WebElement UploadBtn;

	@FindBy(xpath="//input[@type='file']")
	public WebElement ChooseDocumentBtn;

	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;


	@FindBy(xpath="//span[.=' Verify ']")
	public WebElement VerifyBtn;

	// Initialization



	public bank_statement(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	// Utilization

	public WebElement getUploadManuallyBtn() {
		return UploadManuallyBtn;
	}

	public WebElement getUploadBtn() {
		return UploadBtn;
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


	// Business Log


	public void ClickOnUploadManuallyBtn() 

	{

		UploadManuallyBtn.click();
	}

	public void ClickOnUploadBtn() throws InterruptedException

	{
		Thread.sleep(2000);
		UploadBtn.click();
		Thread.sleep(1000);
	}	

	public void ClickOnChooseDocumentBtn(String file) throws Throwable 
	{



		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')", ChooseDocumentBtn);
		ChooseDocumentBtn.sendKeys(file);
		Thread.sleep(2000);
		VerifyBtn.click();


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
