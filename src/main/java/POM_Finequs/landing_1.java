package POM_Finequs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing_1 {

	
	

	@FindBy(id="mobile-no")
	public WebElement MobileNoFld;
	
	@FindBy(id="btn-text")
	public WebElement CheckloanEligibilityBtn;
	
	
	
	public landing_1(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getMobileNoFld() {
		return MobileNoFld;
	}



	public WebElement getCheckloanEligibilityBtn() {
		return CheckloanEligibilityBtn;
	}
	
	
	
	
	public String MobileNo(String no) 
	{
		MobileNoFld.sendKeys(no);
		return no;
	}
	
	public void CheckloanEligibility() 
	{
		CheckloanEligibilityBtn.click();
	}
	
	
}
