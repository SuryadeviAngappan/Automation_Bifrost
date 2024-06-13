package POM_Flipkart_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

import Listner.ListnerClass;


public class Landing_Page_1 {

	
	//Declaration
	
	@FindBy(id="mobile-no")
	public WebElement MobileNoTxtField;
	
	@FindBy(id="btn-container")
	public WebElement CheckLoanEligibilityBtn;
	

	// Initialization
	
	public Landing_Page_1(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	// Utilization
	
	public WebElement getMobileNoTxtField() {
		return MobileNoTxtField;
	}


	public WebElement getCheckLoanEligibilityBtn() {
		return CheckLoanEligibilityBtn;
	}
	
	
	
	// business Logic
		
	
	public String MobileNo(String no) 
	{
		MobileNoTxtField.sendKeys(no);
		return no;
	}
	
	
	public void LoanElgibility() 
	{
		CheckLoanEligibilityBtn.click();
		
		
	}
	
}
