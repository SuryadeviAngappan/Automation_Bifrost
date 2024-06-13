package POM_Flow_AHL_app;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class form1 {

	
	
	
	@FindBy(xpath="//body//app-root//div//div//div//div//div//div//div//app-step1//div//dynamic-form//div//form//div//div//div//div//app-input//div//div//mat-form-field[@appearance='outline']//div//div//div//input[@type='text']")
	private WebElement Full_Name;
	
	@FindBy(xpath="//input[@type='tel']")
	private WebElement MTO;
	
	@FindBy(xpath="//label[@for='option_has_gst_1']")
	private WebElement Gst_Registerd;

	
	@FindBy(xpath="//span[normalize-space()='Continue']")
	private WebElement Continue;
	
	
	public form1(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getFull_Name() {
		return Full_Name;
	}


	public void setFull_Name(WebElement full_Name) {
		Full_Name = full_Name;
	}


	public WebElement getMTO() {
		return MTO;
	}


	public void setMTO(WebElement mTO) {
		MTO = mTO;
	}


	public WebElement getGst_Registerd() {
		return Gst_Registerd;
	}


	public void setGst_Registerd(WebElement gst_Registerd) {
		Gst_Registerd = gst_Registerd;
	}


	public WebElement getContinue() {
		return Continue;
	}


	public void setContinue(WebElement continue1) {
		Continue = continue1;
	}
	
	
	public String Full_Name(String name) 
	{
		Full_Name.sendKeys(name);
		String text=Full_Name.getText();
		return text;
	}
	
	public String MTO(String mto) 
	{
		MTO.sendKeys(mto);
		String text=MTO.getText();
		return text;
	}
	
	public String Gst_Registerd() 
	{
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();",Gst_Registerd );
		String text=Gst_Registerd.getText();
		return text;
	}
	

	public void Continue() 
	{
		Continue.click();
	}
}
