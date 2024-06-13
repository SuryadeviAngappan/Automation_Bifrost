package POM_Exaa_Offer_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form3_4 {

	
	@FindBy(xpath="//span[.='Male']")
	public WebElement GenderIcon;
	
	@FindBy(id="mat-input-7")
	public WebElement DOBFld;
	
	@FindBy(id="mat-input-8")
	public WebElement PinCodeFld;
	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement CurrentAccountIcon;
	
	@FindBy(id="mat-input-9")
	public WebElement PanNoFld;
	
	
	@FindBy(xpath="//span[.='Manufacturer']")
	public WebElement NatureOfBusiness;
	
	@FindBy(id="mat-select-0")
	public WebElement DropDown;
	
	@FindBy(xpath="//span[.='Agricultural Equipments']")
	public WebElement Product;
	
	@FindBy(xpath="//span[.='Submit']")
	public WebElement SubmitBtn;
	
	@FindBy(xpath="//span[.='More than 5 years']")
	public WebElement HowOldBusiness;
	
	@FindBy(id="mat-input-0")
	public WebElement ShopPinCodeFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	public form3_4(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getGenderIcon() {
		return GenderIcon;
	}


	public WebElement getDOBFld() {
		return DOBFld;
	}


	public WebElement getPinCodeFld() {
		return PinCodeFld;
	}


	public WebElement getCurrentAccountIcon() {
		return CurrentAccountIcon;
	}


	public WebElement getPanNoFld() {
		return PanNoFld;
	}


	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	public String Gender() 
	{
		GenderIcon.click();
		String text=GenderIcon.getText();
		return text;
	}
	
	public String DOB(String dob) 
	{
		DOBFld.sendKeys(dob);
		return dob;
	}
	
	public String PinCode(String code) 
	{
		PinCodeFld.sendKeys(code);
		return code;
		
	}
	
	public String CurrentAccount() 
	{
		CurrentAccountIcon.click();
		String text=CurrentAccountIcon.getText();
		return text;
	}
	
	public String PanNo(String no) 
	{
		PanNoFld.sendKeys(no);
		return no;
	}
	
	public String NatureOfBusiness() 
	{
		NatureOfBusiness.click();
		String text=NatureOfBusiness.getText();
		return text;
	}
	
	public String Product() throws InterruptedException 
	{
		DropDown.click();
		Product.click();
		String text=Product.getText();
		SubmitBtn.click();
		Thread.sleep(2000);
		return text;
		
	}
	
	public String BusinessVintage() 
	{
		HowOldBusiness.click();
		String text=HowOldBusiness.getText();
		return text;
	}
	
	public String ShopPinCode(String code) 
	{
		ShopPinCodeFld.sendKeys(code);
		return code;
		
	}
	
	
	
	
	public void Continue() 
	{
		ContinueBtn.click();	
	}
	
	
}
