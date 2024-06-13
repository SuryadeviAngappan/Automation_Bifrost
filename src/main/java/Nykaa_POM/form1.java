package Nykaa_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form1 {


	// Declaration 

	@FindBy(id="mat-input-1")
	public WebElement DOB;

	@FindBy(id="mat-input-2")
	public WebElement Email;

	@FindBy(xpath="//span[.='Male']")
	public WebElement Gender;

	@FindBy(id="mat-input-3")
	public WebElement Residencial_pincode;

	@FindBy(id="mat-input-4")
	public WebElement Flat;

	@FindBy(id="mat-input-5")
	public WebElement Area;

	@FindBy(id="mat-input-6")
	public WebElement Pan;

	@FindBy(xpath="//button[.='Continue']")
	public WebElement Continue;

	@FindBy(xpath="//button[normalize-space()='Accept Offer']")
	public WebElement Accept_Offer;
	
	
	// initialization of variable

	public form1(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	
	
	// Business Logic

	public String DOB(String dob) 
	{
		DOB.sendKeys(dob);
		String text =DOB.getText();
		return text;
	}


	public String Email(String email) 
	{
		Email.sendKeys(email);
		String text=Email.getText();
		return text;
	}

	public String Gender() 
	{
		Gender.click();
		String text=Gender.getText();
		return text;
	}

	public String Residencial_pincode(String name) 
	{
		Residencial_pincode.sendKeys(name);
		String text=Residencial_pincode.getText();
		return text;
	}

	public String Flat(String lp) 
	{
		Flat.sendKeys(lp);
		String ml=Flat.getText();
		return ml;
	}

	public String Area(String area) 
	{
		Area.sendKeys(area);
		String dd=Area.getText();
		return dd;

	}

	public String Pan(String pan) 
	{
		Pan.sendKeys(pan);
		String PAN=Pan.getText();
		return PAN;
	}

	public void Continue() 
	{
		Continue.click();
	}

	


}
