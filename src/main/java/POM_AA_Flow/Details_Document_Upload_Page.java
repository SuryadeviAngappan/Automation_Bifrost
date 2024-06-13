package POM_AA_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Details_Document_Upload_Page {

	// Declaration
	
	
	@FindBy(xpath="//span[.=' Upload ']")
	public WebElement UploadBtn;
	
	@FindBy(xpath="//span[text()=' GST Registration Certificate ']")
	public WebElement GstRegistrationCertificateIcon;
	
	@FindBy(xpath="//button[@type='button']")
	public WebElement SubmitBtn;
	
	@FindBy(xpath="//span[text()=' Shops & Establishment Registration Certificate ']")
	public WebElement  ShopsAndEstablishmentRegistrationCertificate; 
	
	
	
	// Initialization
	
	public Details_Document_Upload_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	
	// Utilization

	


	public WebElement getUploadBtn() {
		return UploadBtn;
	}



	public WebElement getGstRegistrationCertificateIcon() {
		return GstRegistrationCertificateIcon;
	}

	
	public WebElement getSubmitBtn() 
	{
		return SubmitBtn;
	}


	public WebElement getShopsAndEstablishmentRegistrationCertificate() {
		return ShopsAndEstablishmentRegistrationCertificate;
	}
	
	
	// Business Logic
	
	
	
	public void ClickOnUploadBtn() 
	{
		UploadBtn.click();
	}
	
	public void GstRegistrationCertificateIcon(String path) 
	{
		GstRegistrationCertificateIcon.sendKeys(path);
	}
	
	public void ClickOnSubmitBtn() 
	{
		SubmitBtn.click();
	}
	
	public void ShopsAndEstablishmentRegistrationCertificate() 
	{
		ShopsAndEstablishmentRegistrationCertificate.click();
	}
}
