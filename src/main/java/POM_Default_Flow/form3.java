package POM_Default_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form3 {

	
	
	@FindBy(xpath="//span[.='Retailer']")
	public WebElement NatureOfBusinessIcon;

	@FindBy(id="mat-select-0")
	public WebElement SelectProductDropDown;

	@FindBy(xpath="//span[.='Abrasives & Grinding']")
	public WebElement Product;

	@FindBy(xpath="//span[.='Submit']")
	public WebElement SubmitBtn;

	@FindBy(xpath="//span[.='More than 5 years']")
	public WebElement HowOldBusiness;
	
	@FindBy(id="mat-input-2")
	public WebElement ShopPincode;
	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement CurrentAccIcon;
	
	@FindBy(xpath="//span[.='Male']")
	public WebElement GenderIcon;
	
	
	@FindBy(id="mat-input-4")
	public WebElement DOB;
	
	@FindBy(id="mat-input-5")
	public WebElement ResidencialPincode;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	

	
	// Initialization

	public form3(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	// Utilization

	public WebElement getNatureOfBusinessIcon() {
		return NatureOfBusinessIcon;
	}


	public WebElement getSelectProductDropDown() {
		return SelectProductDropDown;
	}



	public WebElement getProduct() {
		return Product;
	}


	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}


	public WebElement getHowOldBusiness() {
		return HowOldBusiness;
	}


	public WebElement getShopPincode() {
		return ShopPincode;
	}


	public WebElement getContinueBtn() {
		return ContinueBtn;
	}


	public WebElement getSumbitBtn() {
		return SubmitBtn;
	}


	// Business Logic


	public String BusinessNature() 
	{
		NatureOfBusinessIcon.click();
		String text=NatureOfBusinessIcon.getText();
		return text;
	}

	public String ProductYouSell() throws InterruptedException 
	{
		SelectProductDropDown.click();
        Product.click();
        String text= Product.getText();
        SubmitBtn.click();
        Thread.sleep(2000);
        return text;	

	}

	public String HowOldBusiness() 
	{
		HowOldBusiness.click();
		String text=HowOldBusiness.getText();
		return text;
	}

	public String ShopPincode(String code) 
	{
		ShopPincode.sendKeys(code);
		String pincode=ShopPincode.getText();
		return pincode;
	}
	
	public String CurrentAcc() 
	{
		CurrentAccIcon.click();
		String text=CurrentAccIcon.getText();
		return text;
	}
	
	
	public String Gender() 
	{
		String text=GenderIcon.getText();
		return text;
	}
	
	public String DOB(String dob) 
	{
		DOB.sendKeys(dob);
		return dob;
	}
	
	public String ResidencialPincode(String pin) 
	{
		ResidencialPincode.sendKeys(pin);
		return pin;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}



}
