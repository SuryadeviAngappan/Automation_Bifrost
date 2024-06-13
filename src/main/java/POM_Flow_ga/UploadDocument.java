package POM_Flow_ga;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import GenericUtilities.WebDriverUtility;

public class UploadDocument {

	@FindBy(xpath="//span[normalize-space()='GST Registration Certificate']")
	public WebElement gst;
	
	@FindBy(xpath="//input[@type='file']")
	public WebElement GST_Registration;
	
	
    @FindBy(xpath="//span[.='Verify & Upload ']")
    public WebElement Upload;
	
	
	public UploadDocument(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getGST_Registration() {
		return GST_Registration;
	}


	public void GST_Certficate(String file) throws InterruptedException 
	{
		
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')",GST_Registration);
		GST_Registration.sendKeys(file);
		Thread.sleep(2000);
		Upload.click();
		
	}
	
	
	
}
