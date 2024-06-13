package POM_AA_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtility;

public class OfferlandingPage {
	
	
	WebDriver driver;
	
	
	//Deceleration
	
	@FindBy(id="mat-input-0")
	public WebElement enterNobileField;
	
	
	@FindBy(xpath="//button[.=' Get Started! ']")
	public WebElement GetStartrtedBtn;
	
	
	// Initialization
	
	public OfferlandingPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	// utilization

	public WebElement getEnterNobileField() {
		return enterNobileField;
	}

	public WebElement getClickOnLoginTypeBtn() {
		return GetStartrtedBtn;
	}
	
	
	// Business Library
	
	
	public void EnterMobileField(String no) 
	{
		enterNobileField.sendKeys(no);
	}
	
	public void GetStartedBtn() 
	{
		GetStartrtedBtn.click();
	}
	
	
	

}
