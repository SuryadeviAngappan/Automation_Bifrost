package POM_Flow_ga;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DigiLocker {
	
	
	
	@FindBy(xpath="//body//doc-upload-popup//div//div//div//div//button[@color='primary']")
	public WebElement Okey;
	
	
	@FindBy(id="aadhaar_1")
	public WebElement Aadhaar_No;
	
	
	@FindBy(id="enter_captcha")
	public WebElement Captcha;
	
	@FindBy(id="button")
	public WebElement Next;
	
	@FindBy(id="otp_1")
	public WebElement Otp;
	
	
	@FindBy(id="v_pin")
	public WebElement Digilocker_Pin;
	
	@FindBy(id="button")
	public WebElement Continue;
	
	@FindBy(id="authorizedbtn")
	public WebElement Allow;
	
	//authorizedbtn
	
	
	
	public DigiLocker(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOkey() {
		return Okey;
	}

	public WebElement getAadhaar_No() {
		return Aadhaar_No;
	}

	public WebElement getCaptcha() {
		return Captcha;
	}

	public WebElement getNext() {
		return Next;
	}

	public WebElement getOtp() {
		return Otp;
	}

	public WebElement getDigilocker_Pin() {
		return Digilocker_Pin;
	}

	public WebElement getContinue() {
		return Continue;
	}

	public WebElement getAllow() {
		return Allow;
	}
	
	
	public void Okey() 
	{
		Okey.click();
	}
	
	public String Aadhaar_No(String no) 
	{
		Aadhaar_No.sendKeys(no);
		return no;
	}
	
	public String Captcha(String no) 
	{
		Captcha.sendKeys(no);
		return no;
	}
	
	public void Next() 
	{
		Next.click();
	}
	
	public String Otp(String no) 
	{
		Otp.sendKeys(no);
		return no;
	}
	
	public void Continue() 
	{
		Continue.click();
	}
	
	public void Digilocker_Pin() 
	{
		Digilocker_Pin.sendKeys("123456");
	}
	
	public void Allow() 
	{
		Allow.click();
	}

}
