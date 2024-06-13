package POM_NLP6_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Otp {
	
	// Declaration
	
	@FindBy(id="mat-input-0")
    public WebElement Otp;
	
	@FindBy(xpath="//button[.='Verify OTP']")
    public WebElement Verify;
	
	
	// Initialization
	
	public Otp(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		
	}

	// Utilization

	public WebElement getOtp() {
		return Otp;
	}


	public WebElement getVerify() {
		return Verify;
	}
	
	// Business Logic 
	
	
	public String Otp(String otp) 
	{
		Otp.sendKeys(otp);
		return otp;
	}
	
	public void Verify() 
	{
		Verify.click();
	}
	
	
	

}
