package POM_Finequs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form_5 {

	
	
	@FindBy(xpath="//span[.='Male']")
	public WebElement GenderIcon;
	
	@FindBy(id="mat-input-2")
	public WebElement DOBFld;
	
	@FindBy(id="mat-input-3")
	public WebElement PinCodeFld;
	
	@FindBy(id="mat-input-4")
	public WebElement FlatFld;
	
	@FindBy(id="mat-input-5")
	public WebElement AreaFld;
	
	@FindBy(id="mat-input-6")
	public WebElement PanFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	// Initialization
	
	public form_5(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


    // Utilization

	public WebElement getGenderIcon() {
		return GenderIcon;
	}




	public WebElement getDOBFld() {
		return DOBFld;
	}




	public WebElement getPinCodeFld() {
		return PinCodeFld;
	}




	public WebElement getFlatFld() {
		return FlatFld;
	}




	public WebElement getAreaFld() {
		return AreaFld;
	}




	public WebElement getPanFld() {
		return PanFld;
	}
	
	public WebElement getContinueBtn() 
	{
		return ContinueBtn;
	}
	
	
	// Business Logic
	
	
	public void Gender() 
	{
		GenderIcon.click();
	}
	
	public void DOB(String date) 
	{
		DOBFld.sendKeys(date);
	}
	
	public void PinCode(String pin ) 
	{
		PinCodeFld.sendKeys(pin);
	}
	
	public void Flat(String no) 
	{
		FlatFld.sendKeys(no);
	}
	
	public void Area(String area) 
	{
		AreaFld.sendKeys(area);
	}
	
	
	public void Pan(String pan)
	{
	     PanFld.sendKeys(pan);	
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
}
