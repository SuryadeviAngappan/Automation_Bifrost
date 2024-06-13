package POM_Flipkart_Flow;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.ModuleLayer.Controller;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import PropertyFileConfig.ObjectReaders;

public class Document_Upload_Page_7 {




    JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
 
    
	
	// Declaration
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ConrtinueBtn;
	
	
	@FindBy(xpath="//button[@class='mat-focus-indicator button-style py-1 button-width save mat-stroked-button mat-button-base mat-primary']")
	public WebElement ClickonContinueBtn;
	
	@FindBy(xpath="//input[@type='file']")
	public WebElement PanCard;
	
	@FindBy(xpath="//span[.=' Upload ']")
	public WebElement  UploadBtn;
	
	////span[text()=' Aadhaar ']
	////span[@class='cursor-pointer']
	
	@FindBy(xpath="//span[text()=' Aadhaar ']")
	public WebElement AadhaarCard;
	
	@FindBy(xpath="//span[@class='cursor-pointer']")
	public WebElement FullSize;
	
	@FindBy(id="uploadFullDoc")
	public WebElement UploadFrontSideOfAadhaar;

	@FindBy(id="gst_registration_option")
	public WebElement GSTRegistrationCertificate;
	
	
	@FindBy(id="itr_returns_option")
	public WebElement IncomeTaxReturn;
	
	@FindBy(xpath="//button[@type='button']")
	public WebElement SubmitBtn;
	
	
	
	
	
	
	// Initialization
	
	
	public Document_Upload_Page_7(WebDriver driver ) 
	{
		PageFactory.initElements(driver, this);
	}


	
	// Utilization

	public WebElement getConrtinueBtn() {
		return ConrtinueBtn;
	}



	public WebElement getPanCard() {
		return PanCard;
	}


	public WebElement getAadhaarCard() {
		return AadhaarCard;
	}



	public WebElement getFullSize() {
		return FullSize;
	}



	public WebElement getUploadFrontSideOfAadhaar() {
		return UploadFrontSideOfAadhaar;
	}



	public WebElement getProofOfBusinessAddressUploadBtn() {
		return UploadBtn;
	}




	public WebElement getGSTRegistrationCertificate() {
		return GSTRegistrationCertificate;
	}



	public WebElement getIncomeTaxReturn() {
		return IncomeTaxReturn;
	}



	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}

	
	// Business Logic
	
	
	

	public void Continue() 
	{
		ConrtinueBtn.click();
	}
	
	
	public void PanCard(String file) throws Throwable 
	{
		
	
		Thread.sleep(1000);
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",PanCard);
		Thread.sleep(1000);
		PanCard.sendKeys(file);
		Thread.sleep(1000);
		SubmitBtn.click();
	
	}
	
	public void ProofOfCurrentResidentialAddress(String file) throws Throwable 
	{
		
		
		
		
		Thread.sleep(1000);		
		AadhaarCard.click();
		Thread.sleep(1000); 
		FullSize.click();
		Thread.sleep(1000);
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",UploadFrontSideOfAadhaar);
		Thread.sleep(1000);
		UploadFrontSideOfAadhaar.sendKeys(file);
		Thread.sleep(1000);
		SubmitBtn.click();
		
	}

	
	public void ProofOfBusinessAddress(String file) throws Throwable 
	{
		
		
		
		Thread.sleep(1000);
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",GSTRegistrationCertificate);
		Thread.sleep(1000);
		GSTRegistrationCertificate.sendKeys(file);
		Thread.sleep(1000);
		SubmitBtn.click();
	
		

	}
	
	public void ProofOfFinancials(String file) throws Throwable 
	{
		
		
		
		Thread.sleep(1000);
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",IncomeTaxReturn);
		Thread.sleep(1000);
		IncomeTaxReturn.sendKeys(file);
		Thread.sleep(2000);
		SubmitBtn.click();
		
	}
	
	
}
