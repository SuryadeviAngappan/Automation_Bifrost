package POM_AA_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Details_4_Page {

	// Declaration
	
	@FindBy(id="mat-input-2")
	public WebElement PanNoTxtFld;
	
	
	@FindBy(id="mat-input-3")
	public WebElement BusinessPinCodeTxtFld;
	
	
	@FindBy(xpath="//span[.='No']")
	public WebElement ResidentRentedIcon;
	
	
	@FindBy(xpath="//span[.='No']")
	public WebElement BusinessRentedIcon;
	
	
	@FindBy(xpath="//span[.='1-2 years']")
	public WebElement HowOldIsYourBusinessIcon;
	
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	// Initialization
	
	public Details_4_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	// Utilization

	public WebElement getPanNoTxtFld() {
		return PanNoTxtFld;
	}


	public WebElement getBusinessPinCodeTxtFld() {
		return BusinessPinCodeTxtFld;
	}


	public WebElement getResidentRentedIcon() {
		return ResidentRentedIcon;
	}


	public WebElement getBusinessRentedIcon() {
		return BusinessRentedIcon;
	}


	public WebElement getHowOldIsYourBusinessIcon() {
		return HowOldIsYourBusinessIcon;
	}


	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	
	// Business Logic
	
	
	public void PanNo(String panNo) 
	{
		PanNoTxtFld.sendKeys(panNo);
	}
	
	public void BusinessPinCode(String pincode) 
	{
		BusinessPinCodeTxtFld.sendKeys(pincode);
	}
	
	public void RentedResident() 
	{
		ResidentRentedIcon.click();
	}
	
	public void RentedBusiness() 
	{
		BusinessRentedIcon.click();
	}
	
	public void HowOldIsBusiness() 
	{
		HowOldIsYourBusinessIcon.click();
	}
	
	public void clickOncontinue() 
	{
		ContinueBtn.click();
	}
	
	
}
