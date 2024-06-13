package POM_flow_ahl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form4 {

	
	@FindBy(xpath="//label[@for='option_business_name_verify_1']")
	public WebElement Business_Name;
	
	@FindBy(xpath="//label[@for='option_business_current_address_verify_1']")
	public WebElement Business_Address;
	
	@FindBy(xpath="//span[normalize-space()='Owned']")
	public WebElement Owned;
	
	@FindBy(xpath="//span[normalize-space()='Confirm & Continue']")
	public WebElement Confirm;
	
	
	public form4(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getBusiness_Name() {
		return Business_Name;
	}


	public WebElement getBusiness_Address() {
		return Business_Address;
	}


	public WebElement getOwned() {
		return Owned;
	}


	public WebElement getConfirm() {
		return Confirm;
	}
	
	
	public String Business_Name() 
	{
		Business_Name.click();
		String text=Business_Name.getText();
		return text;
	}
	
	public String Business_Address() 
	{
		Business_Address.click();
		String text=Business_Address.getText();
		return text;
	}
	
	public String Owned() 
	{
		Owned.click();
		String text=Owned.getText();
		return text;
	}
	
	public void Confirm() 
	{
		Confirm.click();
	}
}
