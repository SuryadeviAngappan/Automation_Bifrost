package POM_NLP1_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form4 {

	
	@FindBy(id="mat-input-9")
	public WebElement PinCodeFld;
	
	@FindBy(xpath="//label[@for='option_homeRented_Owned']")
	public WebElement ResidenceRentedIcon;
	
	@FindBy(xpath="//label[@for='option_shopRented_Owned']")
	public WebElement BusinessRentedIcon;
	
	@FindBy(id="mat-input-1")
	public WebElement BusinessNameFld;
	
	@FindBy(xpath="//span[.='Proprietorship']")
	public WebElement BusinessRegisterd;
	
	@FindBy(id="mat-input-2")
	public WebElement ShopNoFld;
	
	@FindBy(id="mat-input-3")
	public WebElement BusinessAreaFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	public form4(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getPinCodeFld() {
		return PinCodeFld;
	}


	public WebElement getResidenceRentedIcon() {
		return ResidenceRentedIcon;
	}


	public WebElement getBusinessRentedIcon() {
		return BusinessRentedIcon;
	}


	public WebElement getContinieBtn() {
		return ContinueBtn;
	}
	
	
	// Business Logic
	
	public String PinCode(String code) 
	{
		PinCodeFld.sendKeys(code);
		return code;
	}
	
	public String ResidenceRented() 
	{
		ResidenceRentedIcon.click();
		String text=ResidenceRentedIcon.getText();
		return text;
		
	}
	
	public String BusinessRented()
	{
		BusinessRentedIcon.click();
		String text=BusinessRentedIcon.getText();
		return text;
	}
	
	public String BusinessName(String name) 
	{
		BusinessNameFld.sendKeys(name);
		return name;
	}
	
	public String BusinessRegisted() 
	{
		BusinessRegisterd.click();
		String text=BusinessRegisterd.getText();
		return text;
	}
	
	public String ShopNo(String no) 
	{
		ShopNoFld.sendKeys(no);
		return no;
	}
	
	public String BusinessArea(String area) 
	{
		BusinessAreaFld.sendKeys(area);
		return area;
	}
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	
	
	
}
