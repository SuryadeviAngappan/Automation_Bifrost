package POM_Finequs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form_3 {

	
	

	@FindBy(id="mat-input-4")
	public WebElement BusinessNameFld;
	
	@FindBy(xpath="//span[.='Wholesaler']")
	public WebElement NatureOfBusinesssIcon;
	
	@FindBy(id="mat-select-2")
	public WebElement SearchProductDropDownBtn;
	
	@FindBy(xpath="//input[@name='searchText']")
	public WebElement SearchFld;
	
	@FindBy(xpath="//span[.='2 & 3 Wheelers']")
	public WebElement product;
	
	@FindBy(xpath="//span[.='Submit']")
	public WebElement SubmiBtn;
	
	@FindBy(xpath="//span[.='More than 5 years']")
	public WebElement HowOldBusinessIcon;
	
	@FindBy(id="mat-input-5")
	public WebElement ShopPincodeFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	
	public form_3(WebDriver driver) 
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
	
	
	
	public void BusinessName(String name) 
	{
		BusinessNameFld.sendKeys(name);
	}
	
	public void NatureOfBusinesss() 
	{
		NatureOfBusinesssIcon.click();
	}
	
	public void SearchProductDropDown() throws InterruptedException 
	{
		SearchProductDropDownBtn.click();
		Thread.sleep(1000);
		SearchFld.sendKeys("2 & 3 Wheelers");
		Thread.sleep(1500);
		product.click();
		Thread.sleep(2000);
		SubmiBtn.click();
		
	}
	
	public void HowOldBusiness() 
	{
		
		HowOldBusinessIcon.click();
	}
	
	public void ShopPincode(String code) 
	{
		ShopPincodeFld.sendKeys(code);
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
}
