package POM_Flow_ga;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class otp {
	
	
	@FindBy(id="mat-input-1")
	public WebElement Otp;
	
	@FindBy(xpath="//button[.='Verify OTP']")
	public WebElement Verify_Otp;
	
	
	
	
	public otp(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		
	}
	
	
	public String OTP(String otp) 
	{
		Otp.sendKeys(otp);
		String text=Otp.getText();
		return text;

	}
	
	public void Verify_Otp() 
	{
		Verify_Otp.click();
	}

}
