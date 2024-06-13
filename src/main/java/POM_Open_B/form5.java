package POM_Open_B;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form5 {

	
	


	@FindBy(xpath="//span[.='Male']")
	public WebElement GenderIcon;
	
	@FindBy(id="mat-input-8")
	public WebElement DOBFld;
	
	@FindBy(id="mat-input-9")
	public WebElement PinCodeFld;
	
	@FindBy(id="mat-input-10")
	public WebElement FlatFld;
	
	@FindBy(id="mat-input-11")
	public WebElement AreaFld;
	
	@FindBy(id="mat-input-12")
	public WebElement PanFld;
	
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




	public WebElement getPanFld() {
		return PanFld;
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
	
	
	public String  Pan(String pan)
	{
	     PanFld.sendKeys(pan);
	     return pan;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
}
