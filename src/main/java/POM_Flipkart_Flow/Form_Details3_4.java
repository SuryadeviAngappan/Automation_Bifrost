package POM_Flipkart_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Form_Details3_4 {

	
	
	// Declaration
	
	@FindBy(id="mat-input-6")
	public WebElement ResidencialAddressFld;
	
	@FindBy(id="mat-input-7")
	public WebElement residencial_Area_Locality_VillageFld;
	
	@FindBy(id="mat-input-8")
	public WebElement PinCodeTxtField;
	
	@FindBy(id="mat-input-9")
	public WebElement ShopNo_streetNameTxtField;
	
	@FindBy(id="mat-input-10")
	public WebElement Business_Area_Locality_VillageTxtFld;
	
	@FindBy(id="mat-input-11")
	public WebElement ShopPincodeTxtFld;
	
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	// initialization
	
	public Form_Details3_4(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	
	// utilization
	
	
	public WebElement getResidencialAddressFld() {
		return ResidencialAddressFld;
	}


	public WebElement getArea_Locality_VillageFld() {
		return residencial_Area_Locality_VillageFld;
	}


	public WebElement getPinCodeTxtField() {
		return PinCodeTxtField;
	}


	public WebElement getShopNo_SstreetNameTxtField() {
		return ShopNo_streetNameTxtField;
	}


	public WebElement getArea_Locality_VillageTxtFld() {
		return Business_Area_Locality_VillageTxtFld;
	}


	public WebElement getShopPincodeTxtFld() {
		return ShopPincodeTxtFld;
	}


	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	// Business logic
	
	
	public String ResidencialAddress(String adr) 
	{
		ResidencialAddressFld.sendKeys(adr);
		return adr;
	}
	
	public String Residencial_Area_Locality_Village(String aa) 
	{
		residencial_Area_Locality_VillageFld.sendKeys(aa);
		return aa;
	}
	
	
	public String  PinCode(String code) 
	{
		PinCodeTxtField.sendKeys(code);
		return code;
	}
	
	public String ShopNo_StreetName(String name) 
	{
		ShopNo_streetNameTxtField.sendKeys(name);
		return name;
	}
	
	public String Business_Area_Locality_Village(String area) 
	{
		Business_Area_Locality_VillageTxtFld.sendKeys(area);
		return area;
	}
	
	public String Shop_Picode(String pin) 
	{
		ShopPincodeTxtFld.sendKeys(pin);
		return pin;
	}
	
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
}
