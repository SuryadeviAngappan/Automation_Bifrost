package POM_Pine_Labs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form1 {
	
	
	
	@FindBy(id="mat-input-1")
	public WebElement Full_Name;

	
	@FindBy(xpath="//span[.=' Confirm & Continue ']")
	public WebElement Continue;
	
	
	// Initialization
	
	
	public form1(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	// Utilization

	public WebElement getMonthlySaleTxtFld() {
		return Full_Name;
	}


	
	
	
	public WebElement getContinueBtn() {
		return Continue;
	}
	

	// Business Logic
	
	
	public String FullName(String sale) 
	{
		Full_Name.sendKeys(sale);
		return sale;
	}
	
	
	public void Continue() 
	{
	    Continue.click();
	}

}
