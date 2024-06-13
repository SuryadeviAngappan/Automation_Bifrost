package POM_Pine_Labs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing_Page {
	
// Declaration
	
	@FindBy(id="mobile-no")
	public WebElement MobileNo;
	
	@FindBy(id="btn-text")
	public WebElement CheckLoanEligibilityBtn;

	
	
	
	// Initialization
	
	
	public landing_Page(WebDriver driver) 
	{
		
		PageFactory.initElements(driver, this);
	}
	
	
	// Business Logic
	
	
	public String MobileNo(String no) 
	{
		MobileNo.sendKeys(no);
		return no;
	}
	
	public void CheckLoanEligibility() 
	{
		CheckLoanEligibilityBtn.click();
	}
	
	
}
