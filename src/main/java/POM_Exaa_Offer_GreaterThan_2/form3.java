package POM_Exaa_Offer_GreaterThan_2;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class form3 {

	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;

	
	@FindBy(xpath="//label[@for='option_full_name_verify_1']//span[contains(text(),'Yes')]")
	public WebElement Is_This_Your_Full_Name;
	
	@FindBy(xpath="//label[@for='option_current_address_verify_1']//span[contains(text(),'Yes')]")
	public WebElement Is_This_Your_Current_Address;
	
	@FindBy(xpath="//span[normalize-space()='Owned']")
	public WebElement Residence;
	
	@FindBy(xpath="//span[.=' Confirm & Continue ']")
	public WebElement Continue;
	
	
	

	public form3(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getIs_This_Your_Full_Name() {
		return Is_This_Your_Full_Name;
	}



	public WebElement getIs_This_Your_Current_Address() {
		return Is_This_Your_Current_Address;
	}



	public WebElement getResidence() {
		return Residence;
	}



	public WebElement getContinue() {
		return Continue;
	}


	public String Is_This_Your_Full_Name() 
	{
		Is_This_Your_Full_Name.click();
		String add=Is_This_Your_Full_Name.getText();
		return add;
	}
	
	public String Is_This_Your_Current_Address() 
	{
		//Is_This_Your_Current_Address.click();
		js.executeScript("arguments[0].click();", Is_This_Your_Current_Address);
		String add=Is_This_Your_Current_Address.getText();
		return add;
	}
	
	public String Residence() 
	{
		//Residence.click();
		js.executeScript("arguments[0].click();", Residence);
		String res=Residence.getText();
		return res;
	}
	
	public void Continue() 
	{
		Continue.click();
	}

	
}
