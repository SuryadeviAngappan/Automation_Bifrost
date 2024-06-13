package POM_NLP5_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Otp {

	
	@FindBy(id="mat-input-0")
	public WebElement EnterOtp;

	
	@FindBy(xpath="//button[.='Verify OTP']")
	public WebElement VerifyBtn;
	
	
	public Otp (WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getEnterOtpFld() {
		return EnterOtp;
	}


	public WebElement getVerifyBtn() {
		return VerifyBtn;
	}

	
	public String EnterOtp(String otp) 
	{
		EnterOtp.sendKeys(otp);
		return otp;
	}
	
	
	
	public void Verify() 
	{
		VerifyBtn.click();
	}
	
	
	
}
