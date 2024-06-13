package POM_Flow_ga;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class form4 {
	
	
	
	@FindBy(xpath="//label[@for='option_business_name_verify_1']//span[contains(text(),'Yes')]")
	public WebElement BusinessName_Yes;
	
	@FindBy(xpath="(//label[@for='option_business_current_address_verify_1'])[1]")
	public WebElement Current_Business_Address;
	
	@FindBy(xpath="//span[normalize-space()='Owned']")
	public WebElement Owned_Business;
	
	@FindBy(xpath="//span[.=' Verify and upload ']")
	public WebElement ContinueBtn;
	
	


	public form4(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	
	
	
	public WebElement getBusinessName_Yes() {
		return BusinessName_Yes;
	}



	public WebElement getCurrent_Business_Address() {
		return Current_Business_Address;
	}



	public WebElement getOwned_Business() {
		return Owned_Business;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}


	
	public String BusinessName() 
	{
		BusinessName_Yes.click();
		String text=BusinessName_Yes.getText();
		return text;
	}
	
	public String Current_Business_Address() 
	{
		Current_Business_Address.click();
		String text=Current_Business_Address.getText();
		return text;
	}

	public String Owned_Business() 
	{
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();", Owned_Business);
		String text=Owned_Business.getText();
		return text;
	}

	
	public void Continue() 
	{
		ContinueBtn.click();
	}
}
