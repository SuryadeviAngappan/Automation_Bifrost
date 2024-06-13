package POM_NLP1_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form3 {

	@FindBy(xpath="//span[.='Male']")
	public WebElement GenderIcon;
	
	@FindBy(id="mat-input-6")  
	public WebElement DOBFld;
	
	@FindBy(id="mat-input-7")  //6
	public WebElement PincodeFld;
	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement CurrentAccIcon;
	
	
	@FindBy(id="mat-input-8")  //7
	public WebElement PanNoFld;
	
	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement CurrentAccountIcon;

	
	@FindBy(xpath="//span[.='Manufacturer']")
	public WebElement NatureOfBusiness;
	
	@FindBy(id="mat-select-value-1")
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
	
	@FindBy(xpath="//body/app-root/div/div/div/div/div/app-auto-offer/div/div/div/button[@color='primary']/span[1]")
	public WebElement ProceedBtn;
	
	public form3(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getGenderIcon() {
		return GenderIcon;
	}



	public WebElement getDOBFld() {
		return DOBFld;
	}



	public WebElement getPincodeFld() {
		return PincodeFld;
	}



	public WebElement getCurrentAccIcon() {
		return CurrentAccIcon;
	}



	public WebElement getPanNoFld() {
		return PanNoFld;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	// Business Logic
	
	public String Gender() 
	{
		GenderIcon.click();
		String text=GenderIcon.getText();
		return text;
		
	}
	
	public String  DOB(String dob) 
	{
		DOBFld.sendKeys(dob);
		return dob;
	}
	
	public String Pincode(String code) 
	{
		PincodeFld.sendKeys(code);
		return code;
	}
	
	public String CurrentAcc() 
	{
		CurrentAccIcon.click();
		String text=CurrentAccIcon.getText();
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
		Thread.sleep(2000);
		Product.click();
		String text=Product.getText();
		Thread.sleep(2000);
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
	
	
	public String CurrentAccount() 
	{
		CurrentAccountIcon.click();
		String text=CurrentAccountIcon.getText();
		return text;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	
}
