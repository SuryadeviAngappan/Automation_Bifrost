package POM_NLP1_GreaterThan_2;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class form2 {

	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
	
	@FindBy(xpath="//input[@autocomplete='pan-card']")
	public WebElement PanNoFld;
	
	
	@FindBy(xpath="//span[.=' Confirm & Continue ']")
	public WebElement ContinueBtn;
	
	@FindBy(xpath="//input[@autocomplete='on']")
	public WebElement DOB;
	
	@FindBy(xpath="//input[@autocomplete='postal-code']")
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
