package POM_Default_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form5 {

	

	@FindBy(xpath="//span[.='Male']")
	public WebElement GenderIcon;
	
	@FindBy(id="mat-input-3")
	public WebElement DOBFld;
	
	@FindBy(id="mat-input-4")
	public WebElement PinCodeFld;
	
	@FindBy(id="mat-input-5")
	public WebElement FlatFld;
	
	@FindBy(id="mat-input-6")
	public WebElement AreaFld;
	
	
	@FindBy(id="mat-input-3")
	public WebElement EmailFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	// Initialization
	
	public form5(WebDriver driver) 
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


	
	public WebElement getContinueBtn() 
	{
		return ContinueBtn;
	}
	
	
	// Business Logic
	
	
	public String Gender() 
	{
		GenderIcon.click();
		String gender=GenderIcon.getText();
		return gender;
	}
	
	public String DOB(String date) 
	{
		DOBFld.sendKeys(date);
		return date;
	}
	
	public String PinCode(String pin ) 
	{
		PinCodeFld.sendKeys(pin);
		return pin;
	}
	
	public String Flat(String no) 
	{
		FlatFld.sendKeys(no);
		return no;
	}
	
	public String Area(String area) 
	{
		AreaFld.sendKeys(area);
		return area;
	}
	
	
	
	public String Email(String id) 
	{
		EmailFld.sendKeys(id);
		return id;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	
}