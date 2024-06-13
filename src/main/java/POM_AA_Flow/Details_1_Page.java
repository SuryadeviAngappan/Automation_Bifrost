package POM_AA_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Details_1_Page{

	
	
	// Deceleration
	
	@FindBy(id="mat-input-0")
	public WebElement avgMonthlySalesFld;
	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement GSTYesBtn;
	
	@FindBy(xpath="//span[@class='mat-button-wrapper']")
	public WebElement ClickOnContinueBtn;
	
	
	// initialization
	
	public Details_1_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	// Utilization
	
	public void AvgMonthlySalesField(String amount) 
	{
		avgMonthlySalesFld.sendKeys(amount);
	}
	
	public void  GstYes() 
	{
		GSTYesBtn.click();
	}
	
	public void ClickOnConBTn() 
	{
		ClickOnContinueBtn.click();
	}
	
	
}
