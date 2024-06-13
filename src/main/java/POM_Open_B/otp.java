package POM_Open_B;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class otp {
	
	

	@FindBy(id="mat-input-3")
	public WebElement EnterOtpFld_Open_Bank;
	
	@FindBy(xpath="//button[.='Verify OTP']")
	public WebElement VerifyBtn;
	
	
	public otp(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getEnterOtpFld() {
		return EnterOtpFld_Open_Bank;
	}


	public WebElement getVerifyBtn() {
		return VerifyBtn;
	}

	
	public void EnterOtp_Open_B(String otp) 
	{
		EnterOtpFld_Open_Bank.sendKeys(otp);
	}
	
	public void Verify() 
	{
		VerifyBtn.click();
	}

}
