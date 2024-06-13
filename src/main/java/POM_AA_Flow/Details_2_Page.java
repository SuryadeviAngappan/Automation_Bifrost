package POM_AA_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Details_2_Page {

	
	//Deceleration
	
	
	
	@FindBy(id="mat-input-5")
	public WebElement FullNameTxtField;
	
	
	@FindBy(id="mat-input-6")
	public WebElement EmailTxtField;
	
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	//Initialization
	
	public Details_2_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	
	// Utilization

	
	public WebElement getFullNameTxtField() {
		return FullNameTxtField;
	}



	public WebElement getEmailTxtField() {
		return EmailTxtField;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	
	
	// Business Logic
	
	
	
	public void EnterFullName(String name) 
	{
		FullNameTxtField.sendKeys(name);
	}
	
	
	public void EnterEmailId(String EmailId) 
	{
		EmailTxtField.sendKeys(EmailId);
	}
	
	public void ClickOnContinueBtn() 
	{
		ContinueBtn.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
