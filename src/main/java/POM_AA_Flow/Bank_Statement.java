package POM_AA_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Bank_Statement {

	
	// Deceleration
	
	@FindBy(xpath="//span[.='Upload manually']")
	public WebElement UploadManuallyBtn;
	
	@FindBy(xpath="//button[.='Upload']")
	public WebElement UploadBtn;
	
	@FindBy(xpath="//button[.='Choose document']")
	public WebElement ChooseDocumentBtn;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	@FindBy(xpath="//span[.=' Verify ']")
	public WebElement VerifyBtn;
	
	// Initialization
	
	

	public Bank_Statement(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	// Utilization

	public WebElement getUploadManuallyBtn() {
		return UploadManuallyBtn;
	}

	public WebElement getUploadBtn() {
		return UploadBtn;
	}

	public WebElement getChooseDocumentBtn() {
		return ChooseDocumentBtn;
	}

	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	public WebElement getVerifyBtn() {
		return VerifyBtn;
	}

	
	// Business Logic
	
	
	public void ClickOnUploadManuallyBtn() 
	
	{
		UploadManuallyBtn.click();
	}
	
	public void ClickOnUploadBtn()
	
	{
		UploadBtn.click();
	}	
	
	public void ClickOnChooseDocumentBtn(String BankStatement) 
	{
		ChooseDocumentBtn.sendKeys(BankStatement+" bank statement path");
	}	
	
	
	public void ClickOnContinueBtn() 
	{
		ContinueBtn.click();
	}
	
	
	public void ClickOnVerify() 
	{
		VerifyBtn.click();
	}
	
	
}
