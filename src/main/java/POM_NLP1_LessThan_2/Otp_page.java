package POM_NLP1_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Otp_page{

	
	

	@FindBy(xpath="//input[@type='tel']")
	public WebElement EnterOtpFld;
	
	@FindBy(xpath="//button[.='Verify OTP']")
	public WebElement VerifyBtn;
	
	
	public Otp_page(WebDriver driver) 
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
