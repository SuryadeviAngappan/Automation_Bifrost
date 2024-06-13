package POM_Zomato;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class otp {

	
	

	@FindBy(id="mat-input-0")
	public WebElement EnterOtp;
	
	@FindBy(xpath="//button[.='Verify OTP']")
	public WebElement VerifyBtn;
	
	
	public otp(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getEnterOtpFld() {
		return EnterOtp;
	}


	public WebElement getVerifyBtn() {
		return VerifyBtn;
	}

	
	public void EnterOtp(String otp) 
	{
		EnterOtp.sendKeys(otp);
	}
	
	public void Verify() 
	{
		VerifyBtn.click();
	}
	
}
