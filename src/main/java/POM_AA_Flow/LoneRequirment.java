package POM_AA_Flow;
//package POM_flow_AA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoneRequirment {

	// Declaration
	
	@FindBy(id="mat-input-0")
   	public WebElement loanAmtxtField;
	
	@FindBy(xpath="//span[.='A Business man']")
	public WebElement YouareBusinessmanIcon;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ClickOnContinueBtn;	
	
	
	// Initialization
	
	public LoneRequirment(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
		
	
	// Utilization
	
	public void LoanAmount(String name) 
	{
		loanAmtxtField.sendKeys(name);
	}
	
	public void BusinessManIcon() 
	{
		YouareBusinessmanIcon.click();
	}
	
	public void ClickOnContinueBtn() 
	{
		ClickOnContinueBtn.click();
	}
	

}
