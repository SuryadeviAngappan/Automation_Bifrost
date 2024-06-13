package POM_Finequs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class otp_2 {

	
	
	@FindBy(id="mat-input-0")
	public WebElement otpFld;
	
	@FindBy(xpath="//button[.='Verify OTP']")
	public WebElement VerifyBtn;
	
	
	
	public otp_2(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getOtpFld() {
		return otpFld;
	}



	public WebElement getVerifyBtn() {
		return VerifyBtn;
	}
	
	
	public void otp(String otp) 
	{
		otpFld.sendKeys(otp);
	}
	
	public void Verify() 
	{
		VerifyBtn.click();
	}
}
