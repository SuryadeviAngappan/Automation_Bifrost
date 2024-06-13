package POM_NLP3_LessThan_2;

import java.awt.AWTException;
import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import PropertyFileConfig.ObjectReaders;

public class Upload_Document {

	
	// Declaration
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ConrtinueBtn;
	
	
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
	
	@FindBy(xpath="//button[@class='mat-focus-indicator button-style py-1 button-width save mat-stroked-button mat-button-base mat-primary']")
	public WebElement ClickonContinueBtn;
	
	
	
	
	// Initialization
	
	
	public Upload_Document(WebDriver driver ) 
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
	
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",PanCard);
		PanCard.sendKeys(file);
		SubmitBtn.click();
		
	}
	
	public void ProofOfCurrentResidentialAddress(String file) throws Throwable 
	{
		
	
		AadhaarCard.click();
		FullSize.click();
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",UploadFrontSideOfAadhaar);
		UploadFrontSideOfAadhaar.sendKeys(file);
		SubmitBtn.click();
		
	}

	
	public void ProofOfBusinessAddress(String file) throws Throwable 
	{
		

		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",GSTRegistrationCertificate);
		GSTRegistrationCertificate.sendKeys(file);
		SubmitBtn.click();
		
		

	}
	
	public void ProofOfFinancials(String file) throws Throwable 
	{
		
	
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",IncomeTaxReturn);
		IncomeTaxReturn.sendKeys(file);
		SubmitBtn.click();
		
	}
	
	
	
}
