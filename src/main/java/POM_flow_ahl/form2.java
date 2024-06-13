package POM_flow_ahl;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class form2 {

	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
	
	@FindBy(id="mat-input-0")
	public WebElement PanNo;
	
	
	@FindBy(id="mat-input-1")
	public WebElement DOB;
	
	
	@FindBy(id="mat-input-2")
	public WebElement PIN_No;
	
	
	@FindBy(xpath="//span[.='Male']")
	public WebElement Gender;
	
	@FindBy(xpath="(//span[normalize-space()='Confirm & Continue'])[1]")
	public WebElement Continue;
	
	
	
	public form2(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getPanNo() {
		return PanNo;
	}



	public WebElement getDOB() {
		return DOB;
	}



	public WebElement getPIN_No() {
		return PIN_No;
	}



	public WebElement getGender() {
		return Gender;
	}



	public WebElement getContinue() {
		return Continue;
	}
	
	
	public String PanNo(String no) 
	{
		PanNo.sendKeys(no);
		return no;
	}
	
	
	public String DOB(String dob) 
	{
		DOB.sendKeys(dob);
		return dob;
	}
	
	public String PIN_No(String pin) 
	{
		PIN_No.sendKeys(pin);
		return pin;
	}
	
	public String Gender() 
	{
		js.executeScript("arguments[0].click();", Gender);
		//Gender.click();
		String text=Gender.getText();
		return text;
	}
	
	public void Continue() 
	{
		Continue.click();
	}
	
}
