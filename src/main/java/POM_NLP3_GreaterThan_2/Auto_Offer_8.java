package POM_NLP3_GreaterThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Auto_Offer_8 {

	
	
	@FindBy(xpath="//button[@class='mat-focus-indicator button-style button-rounded desk-btn mat-flat-button mat-button-base mat-primary']")
	public WebElement ProceedBtn;

	@FindBy(id="mat-input-0")
	public WebElement BusinessName;
	
	@FindBy(xpath="//span[.='Proprietorship']")
	public WebElement PropritershiopIcon;
	
	@FindBy(xpath="//span[.='More than 5 years']")
	public WebElement HowOldIsyourBusiness;
	
	@FindBy(id="mat-input-1")
	public WebElement ShopPinCodeFld;
	
	@FindBy(id="mat-input-2")
	public WebElement ShopNoAndStreetNameFld;
	
	@FindBy(id="mat-input-3")
	public WebElement Area_Locality_villageFlkd;
	
	@FindBy(xpath="//span[.= ' Continue ']")
	public WebElement ContinueBtn;
	
	
	// Initialization
	
	public Auto_Offer_8(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	// Utilization

	public WebElement getBusinessName() {
		return BusinessName;
	}


	public WebElement getPropritershiopIcon() {
		return PropritershiopIcon;
	}


	public WebElement getHowOldIsyourBusiness() {
		return HowOldIsyourBusiness;
	}


	public WebElement getShopPinCodeFld() {
		return ShopPinCodeFld;
	}


	public WebElement getShopPincodeAndStreetNameFld() {
		return ShopNoAndStreetNameFld;
	}


	public WebElement getArea_Locality_villageFlkd() {
		return Area_Locality_villageFlkd;
	}


	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	// Business Logic
	
	
	public String BusinessName(String name) 
	{
		BusinessName.sendKeys(name);
		return name;
	}
	
	public String  Propritership() 
	{
		PropritershiopIcon.click();
		String text=PropritershiopIcon.getText();
		return text;
	}
	
	public String HowOldBusiness() 
	{
		HowOldIsyourBusiness.click();
		String text=HowOldIsyourBusiness.getText();
		return text;
		
	}
	
	public String ShopPinCode(String code) 
	{
	    ShopPinCodeFld.sendKeys(code);
	    return code;
	}
	
	public String ShopNoAndStreetName(String name) 
	{
		ShopNoAndStreetNameFld.sendKeys(name);
		return name;
	}
	
	public String  Area_Locality_village(String area) 
	{
		Area_Locality_villageFlkd.sendKeys(area);
		return area;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	public void Proceed() 
	{
		ProceedBtn.click();
	}
	
	
	
	
	
	
	
}
