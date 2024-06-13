package POM_Open_B;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form3 {

	
	@FindBy(id="mat-input-4")
	public WebElement BusinessNameFld;
	
	@FindBy(xpath="//span[.='Wholesaler']")
	public WebElement NatureOfBusinesssIcon;
	
	@FindBy(id="mat-select-value-1")
	public WebElement SearchProductDropDownBtn;
	
	@FindBy(xpath="//input[@name='searchText']")
	public WebElement SearchFld;
	
	@FindBy(xpath="//span[.='2 & 3 Wheelers']")
	public WebElement product;
	
	@FindBy(xpath="//span[.='Submit']")
	public WebElement SubmiBtn;
	
	@FindBy(xpath="//span[.='2-5 years']")
	public WebElement HowOldBusinessIcon;
	
	@FindBy(xpath="//span[.='Less than a year']")
	public WebElement LessThanYear;
	
	@FindBy(id="mat-input-5")
	public WebElement ShopPincodeFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	
	public form3(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getBusinessNameFld() {
		return BusinessNameFld;
	}



	public WebElement getNatureOfBusinesssIcon() {
		return NatureOfBusinesssIcon;
	}



	public WebElement getSearchProductDropDownBtn() {
		return SearchProductDropDownBtn;
	}



	public WebElement getSearchFld() {
		return SearchFld;
	}



	public WebElement getProduct() {
		return product;
	}



	public WebElement getSubmiBtn() {
		return SubmiBtn;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	
	public String  BusinessName(String name) 
	{
		BusinessNameFld.sendKeys(name);
		return name;
	}
	
	public String NatureOfBusinesss() 
	{
		NatureOfBusinesssIcon.click();
		String text=NatureOfBusinesssIcon.getText();
		return text;
		
	}
	
	public String SearchProductDropDown() throws InterruptedException 
	{

		SearchProductDropDownBtn.click();
		product.click();
		Thread.sleep(2000);
		String prod=product.getText();
		SubmiBtn.click();
		Thread.sleep(2000);
		return prod;
		
	}
	
	public String HowOldBusiness() throws InterruptedException 
	{
		Thread.sleep(1000);
		HowOldBusinessIcon.click();
		String old=HowOldBusinessIcon.getText();
		return old;
		
	}
	
	public String ShopPincode(String code) 
	{
		ShopPincodeFld.sendKeys(code);
		return code;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	public String LessThanYear() 
	{
		LessThanYear.click();
		String text=LessThanYear.getText();
		return text;
	}
	
	
}
