package POM_Pine_Labs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class form4 {
	
	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
	
	
	@FindBy(xpath="//label[@for='option_business_name_verify_1']//span[contains(text(),'Yes')]")
	public WebElement Is_This_The_Name_of_Your_Business;
	
	@FindBy(xpath="//label[@for='option_business_current_address_verify_1']//span[contains(text(),'Yes')]")
	public WebElement Is_This_Your_Business_Address;

	@FindBy(xpath="//span[normalize-space()='Owned']")
	public WebElement Business_State;
	
	@FindBy(xpath="//span[.=' Confirm & Continue ']")
	public WebElement Continue;
	
	
	
	public form4(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getIs_This_The_Name_of_Your_Business() {
		return Is_This_The_Name_of_Your_Business;
	}



	public WebElement getIs_This_Your_Business_Address() {
		return Is_This_Your_Business_Address;
	}



	public WebElement getBusiess_State() {
		return Business_State;
	}



	public WebElement getContinue() {
		return Continue;
	}


	public String Is_This_The_Name_of_Your_Business() 
	{
		Is_This_The_Name_of_Your_Business.click();
		String text=Is_This_The_Name_of_Your_Business.getText();
		return text;
	}
	
	public String Is_This_Your_Business_Address() 
	{
		Is_This_Your_Business_Address.click();
		String text=Is_This_Your_Business_Address.getText();
		return text;
	}

	public String Business_State() 
	{
		//Business_State.click();
		js.executeScript("arguments[0].click();", Business_State);
		String text=Business_State.getText();
		return text;
	}
	
	public void Continue() 
	{
		Continue.click();
	}
	

}
