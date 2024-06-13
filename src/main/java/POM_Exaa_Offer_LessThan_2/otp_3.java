package POM_Exaa_Offer_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class otp_3 {

	

	@FindBy(id="mat-input-1")
	public WebElement EnterOtpFld;
	
	@FindBy(xpath="//button[.='Verify OTP']")
	public WebElement VerifyBtn;
	
	
	public otp_3(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getEnterOtpFld() {
		return EnterOtpFld;
	}


	public WebElement getVerifyBtn() {
		return VerifyBtn;
	}

	
	public void EnterOtp(String otp) 
	{
		EnterOtpFld.sendKeys(otp);
	}
	
	public void Verify() 
	{
		VerifyBtn.click();
	}
	
	
	
}
