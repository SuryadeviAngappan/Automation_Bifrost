package POM_AA_Flow;
//package POM_flow_AA;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//public class OTPPage {
//
//	
//	// Deceleration
//	
//	@FindBy(xpath="//input[@placeholder='Enter OTP']")
//	private WebElement EnterOtpTxtField;
//	
//	
//	@FindBy(xpath="//span[.='Resend OTP']")
//	private WebElement ResentOtpBtn;
//	
//	
//	@FindBy(xpath="//button[.='Verify OTP']")
//	private WebElement VerifyBtn;
//	
//	
//	// Initialization
//	
//	OTPPage(WebDriver driver)
//	{
//		PageFactory.initElements(driver, this);
//	}
//	
//	
//	
//	// Utilization
//	
//	public void EnterOtpIntoTxtfield(String otp) 
//	{
//		EnterOtpTxtField.sendKeys(otp);
//	}
//	
//	public void ResendOtpBtn() 
//	{
//		ResentOtpBtn.click();
//	}
//	
//	public void VerifyOtpBtn() 
//	{
//		VerifyBtn.click();
//	}
//	
//}
