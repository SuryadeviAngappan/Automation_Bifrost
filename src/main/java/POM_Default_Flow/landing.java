package POM_Default_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing{
	
	
	
	@FindBy(id="mobile-no")
	public WebElement MobileFld;
	
	
	@FindBy(xpath="//span[.='Check loan Eligibility']")
	public WebElement CheckLoanEligibilityBtn;
	
	
	
	public landing(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getMobileFld() {
		return MobileFld;
	}



	public WebElement getCheckLoanEligibilityBtn() {
		return CheckLoanEligibilityBtn;
	}

	// Business Logic
	
	public String MobileNo(String no) 
	{
		MobileFld.sendKeys(no);
		return no;
	}
	
	
	public void CheckLoanEligibility() 
	{
		CheckLoanEligibilityBtn.click();
	}
	
	
	
	
}
