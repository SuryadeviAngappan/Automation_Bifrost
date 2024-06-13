package POM_Pine_Labs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class form2 {
	
	
	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;

	@FindBy(id="mat-input-3")
	public WebElement Pan_No;
	

	@FindBy(id="mat-input-4")
	public WebElement DOB;
	

	@FindBy(id="mat-input-5")
	public WebElement PinCode;
	

	@FindBy(xpath="//span[.='Male']")
	public WebElement Gender;
	
	
	@FindBy(xpath="//span[.=' Confirm & Continue ']")
	public WebElement Continue;
	
	
	
	public form2(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	
	// initialization
	
	public WebElement getPan_No() {
		return Pan_No;
	}


	public WebElement getDOB() {
		return DOB;
	}


	public WebElement getPinCode() {
		return PinCode;
	}


	public WebElement getGender() {
		return Gender;
	}


	public WebElement getContinue() {
		return Continue;
	}


	
	// Business Logic
	
	
	public String Pan_No(String name) 
	{
		Pan_No.sendKeys(name);
		return name;
	}
	
	
	public String DOB(String name) 
	{
		DOB.sendKeys(name);
		return name;
	}

	public String PinCode(String name) 
	{
		PinCode.sendKeys(name);
		return name;
	}
	
	
	public String Gender()
	{
		//Gender.click();
		js.executeScript("arguments[0].click();", Gender);
		String gender=Gender.getText();
		return gender;
	}
	
	public void Continue() 
	{
		Continue.click();
	}
	
	
	

}
