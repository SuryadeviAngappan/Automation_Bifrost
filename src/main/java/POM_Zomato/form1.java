package POM_Zomato;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form1 {

	
	@FindBy(id="mat-input-1")
	public WebElement MonthlySaleTxtFld;

	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement GstRegisterdIcon;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	// Initialization
	
	
	public form1(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	// Utilization

	public WebElement getMonthlySaleTxtFld() {
		return MonthlySaleTxtFld;
	}


	public WebElement getGstRegisterdIcon() {
		return GstRegisterdIcon;
	}
	
	
	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	

	// Business Logic
	
	
	public String MonthlySale(String sale) 
	{
		MonthlySaleTxtFld.sendKeys(sale);
		return sale;
	}
	
	
	
	public String GstIcon() 
	{
		GstRegisterdIcon.click();
		String text=GstRegisterdIcon.getText();
		return text;
		
	}
	
	public void Continue() 
	{
	    ContinueBtn.click();
	}
	
}
