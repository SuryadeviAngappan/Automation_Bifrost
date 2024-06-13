package POM_AA_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Details_3_Page {

	
	@FindBy(xpath="//span[.='Male']")
	public WebElement IamMaleIcon;
	
	
	@FindBy(id="mat-input-0")
	public WebElement DOBTxtField;
	
	
	@FindBy(id="mat-input-1")
	public WebElement PinCodeTxtField;
	
	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement CurrentAccountIcon;
	
	
	@FindBy(xpath="//span[.='Proprietorship']")
	public WebElement BusinessRegesterdAsPropritershipIcon;
	
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	
	public Details_3_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getIamMaleIcon() {
		return IamMaleIcon;
	}



	public WebElement getDOBTxtField() {
		return DOBTxtField;
	}



	public WebElement getPinCodeTxtField() {
		return PinCodeTxtField;
	}



	public WebElement getCurrentAccountIcon() {
		return CurrentAccountIcon;
	}



	public WebElement getBusinessRegesterdAsPropritershipIcon() {
		return BusinessRegesterdAsPropritershipIcon;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	
	
	// Business Logic
	
	public void MaleIcon() 
	{
		IamMaleIcon.click();
	}
	
	
	public void DOBField(String DOB) 
	{
		DOBTxtField.sendKeys(DOB);
	}
	
	
	public void PinCodeField(String pinCode) 
	{
		PinCodeTxtField.sendKeys(pinCode);
	}
	
	
	public void CurrentAccountIcon() 
	{
		CurrentAccountIcon.click();
	}
	
	
	public void BussinessRegisterd() 
	{
		BusinessRegesterdAsPropritershipIcon.click();
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	
	
	
	
	
	
	
	
	

	
	
	
}
