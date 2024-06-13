package POM_NLP6_GreaterThan_2;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class form2 {

	

	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
	
	@FindBy(id="mat-input-1")
	public WebElement PanNoFld;
	
	
	@FindBy(xpath="//span[.=' Confirm & Continue ']")
	public WebElement ContinueBtn;
	
	@FindBy(id="mat-input-2")
	public WebElement DOB;
	
	@FindBy(id="mat-input-3")
	public WebElement PINCode;
	
	@FindBy(xpath="//span[.='Male']")
	public WebElement Gender;
	
	
	
	public form2(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getPanNoFld() {
		return PanNoFld;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}



	public WebElement getDOB() {
		return DOB;
	}



	public WebElement getPINCode() {
		return PINCode;
	}



	public WebElement getGender() {
		return Gender;
	}



	public String PanNo(String no) 
	{
		PanNoFld.sendKeys(no);
		String text=PanNoFld.getText();
		return text;
	}
	
	public String DOB(String dob) 
	{
		DOB.sendKeys(dob);
		String text=DOB.getText();
		return text;
	}
	

	public String PIN_CODE(String pin) 
	{
		PINCode.sendKeys(pin);
		String text=PINCode.getText();
		return text;
	}

	public String Gender() 
	{
	//	Gender.click();
		js.executeScript("arguments[0].click();", Gender);
		String text=Gender.getText();
		return text;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
}
