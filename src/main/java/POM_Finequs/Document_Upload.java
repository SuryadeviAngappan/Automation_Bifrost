package POM_Finequs;

import java.awt.AWTException;
import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class Document_Upload {

	
	File file = new File("/Users/fllap0570_maheshnarvane/git/CJP_Mahesh/Project_01/Documents/PanCard.png");
    JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
 
    
	
	// Declaration
	
	@FindBy(xpath="//button[@class='mat-focus-indicator button-style py-1 button-width save mat-stroked-button mat-button-base mat-primary single-btn']")
	public WebElement ContinueBtn;
	
	
	@FindBy(xpath="//input[@type='file']")   
	public WebElement PanCard;
	
	@FindBy(xpath="//span[.=' Upload ']")
	public WebElement  UploadBtn;
	
	
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
	
	
	public Document_Upload(WebDriver driver ) 
	{
		PageFactory.initElements(driver, this);
	}


	
	// Utilization

	public WebElement getConrtinueBtn() {
		return ContinueBtn;
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
		ContinueBtn.click();
	}
	
	
	public void PanCard() throws InterruptedException, AWTException 
	{
		UploadBtn.click();
		Thread.sleep(2000);
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",PanCard);
		Thread.sleep(2000);
		PanCard.sendKeys(file.getAbsolutePath());
		Thread.sleep(2000);
		SubmitBtn.click();
		Thread.sleep(10000);
	}
	
	public void ProofOfCurrentResidentialAddress() throws InterruptedException 
	{
		UploadBtn.click();
		Thread.sleep(2000);		
		AadhaarCard.click();
		Thread.sleep(2000); 
		FullSize.click();
		Thread.sleep(2000);
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",UploadFrontSideOfAadhaar);
		Thread.sleep(3000);
		UploadFrontSideOfAadhaar.sendKeys(file.getAbsolutePath());
		Thread.sleep(2000);
		SubmitBtn.click();
		Thread.sleep(10000);
	}

	
	public void ProofOfBusinessAddress() throws InterruptedException 
	{
		UploadBtn.click();
		Thread.sleep(1000);
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",GSTRegistrationCertificate);
		Thread.sleep(2000);
		GSTRegistrationCertificate.sendKeys(file.getAbsolutePath());
		Thread.sleep(2000);
		SubmitBtn.click();
		Thread.sleep(10000);
		

	}
	
	public void ProofOfFinancials() throws InterruptedException 
	{
		UploadBtn.click();
		Thread.sleep(1000);
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",IncomeTaxReturn);
		Thread.sleep(1000);
		IncomeTaxReturn.sendKeys(file.getAbsolutePath());
		Thread.sleep(2000);
		SubmitBtn.click();
		Thread.sleep(5000);
	}
	
	
	
}
