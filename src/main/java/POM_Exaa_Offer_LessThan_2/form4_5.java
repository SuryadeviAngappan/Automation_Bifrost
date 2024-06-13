package POM_Exaa_Offer_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form4_5 {

	
	
	@FindBy(id="mat-input-10")
	public WebElement PinCodeFld;	
	
	@FindBy(xpath="//label[@for='option_homeRented_Owned']")
	public WebElement ResidenceIcon;
	
	@FindBy(xpath="//label[@for='option_shopRented_Owned']")
	public WebElement BusinessIcon;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	@FindBy(id="mat-input-1")
	public WebElement BusinessNameFld;
	
	@FindBy(xpath="//span[.='Proprietorship']")
	public WebElement PropritershipIcon;
	
	@FindBy(id="mat-input-2")
	public WebElement ShopNoFld;
	
	@FindBy(id="mat-input-3")
	public WebElement AreaFld;
	
	
	
	
	public form4_5(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getPinCodeFld() {
		return PinCodeFld;
	}



	public WebElement getResidenceIcon() {
		return ResidenceIcon;
	}



	public WebElement getBusinessIcon() {
		return BusinessIcon;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	
	// Business Logic
	
	public String PinCode(String code) 
	{
		PinCodeFld.sendKeys(code);
		return code;
	}
	
	
	public String Residence() 
	{
		ResidenceIcon.click();
		String text=ResidenceIcon.getText();
		return text;
	}
	
	
	public String Business() 
	{
		BusinessIcon.click();
		String text=BusinessIcon.getText();
		return text;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	public String  BusinessName(String name) 
	{
		BusinessNameFld.sendKeys(name);
		return name;
	}
	
	
	public String Propritership() 
	{
		PropritershipIcon.click();
		String text=PropritershipIcon.getText();
		return text;
	}
	
	public String ShopNo(String no) 
	{
		ShopNoFld.sendKeys(no);
		return no;
	}
	
	public String Area(String area) 
	{
		AreaFld.sendKeys(area);
		return area;
	}
	
	
}
