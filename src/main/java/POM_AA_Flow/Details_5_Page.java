package POM_AA_Flow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtility;

public class Details_5_Page  {

	WebDriver driver;
	
	// Deceleration
	
	@FindBy(xpath="//span[.='Retailer']")
	public WebElement NatureOfBusinessRetailIcon;
	
	@FindBy(id="mat-select-value-1")
	public WebElement ProductYouSellDropDownBtn;
	
	@FindBy(xpath="//span[.='Submit']")
	public WebElement SubmitBtn;
	
	@FindBy(id="mat-input-4")
	public WebElement BusinessNameTxtFld;
	
	@FindBy(id="mat-input-6")
	public WebElement ShopNoAndStreetNameTxtFld;
	
	@FindBy(id="mat-input-7")
	public WebElement localityTxtFld;
	
	@FindBy(id="mat-input-9")
	public WebElement FlatNoTxtFld;
	
	@FindBy(id="mat-input-10")
	public WebElement VillageTxtFld;
	
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	// Initialization
	
	
	public Details_5_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	// Utilization
	
	
	public WebElement getNatureOfBusinessIcon() {
		return NatureOfBusinessRetailIcon;
	}

	public WebElement getProductYouSellDropDownBtn() {
		return ProductYouSellDropDownBtn;
	}

	public WebElement getSubmitBtn() 
	{
		return SubmitBtn;
	}
	
	public WebElement getBusinessNameTxtFld() {
		return BusinessNameTxtFld;
	}

	public WebElement getShopNoAndStreetNameTxtFld() {
		return ShopNoAndStreetNameTxtFld;
	}

	public WebElement getLocalityTxtFld() {
		return localityTxtFld;
	}

	public WebElement getFlatNoTxtFld() {
		return FlatNoTxtFld;
	}

	public WebElement getVillageTxtFld() {
		return VillageTxtFld;
	}

	public WebElement getContinueBtn() {
		return ContinueBtn;
	}

	
	// Business Logic
	
	public void NatureOfBusiness() 
	{
		NatureOfBusinessRetailIcon.click();
	}
	
	public void ProductDropDown() 
	{
		ProductYouSellDropDownBtn.click();
		
	}
	
	public void SearchBox() 
	{
		List<WebElement> elements = driver.findElements(By.xpath("/input[@name='searchText']"));
		
		String Expected = "Dsa";
		for(WebElement ele:elements) 
		{
			String text = ele.getText();
			if(text.equalsIgnoreCase(Expected)) 
			{
				ele.click();
			}
		}
	}
	
	public void ClickSubmitBtn() 
	{
		SubmitBtn.click();
	}
	
	public void BusinessName(String name) 
	{
		BusinessNameTxtFld.sendKeys(name);
	}
	
	public void ShopNoAndStreetName(String name) 
	{
		ShopNoAndStreetNameTxtFld.sendKeys(name);
	
	}
	
	public void Locality(String locality) 
	{
		localityTxtFld.sendKeys(locality);
	}
	
	public void FlatNo(String FlatNo) 
	{
		FlatNoTxtFld.sendKeys(FlatNo);
	}
	
	public void Village(String village) 
	{
		VillageTxtFld.sendKeys(village);
	}
	
	public void ClickOncontinueBtn() 
	{
		ContinueBtn.click();
	}
	
	
	

	
	
	
	
	
	
	
	
}
