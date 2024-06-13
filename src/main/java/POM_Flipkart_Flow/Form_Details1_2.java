package POM_Flipkart_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import GenericUtilities.WebDriverUtility;

public class Form_Details1_2 {

	
	
	// Declaration
	
	@FindBy(xpath="//input[@autocomplete='given-name']")
	public WebElement FullNameTxtFld;
	
	@FindBy(id="mat-input-1")
	public WebElement EmailTxtFld;
	
	@FindBy(xpath="//label[@for='option_loan_amount_1500000']")
	public WebElement LoanRequirmentIcon;
	
	@FindBy(id="mat-input-2")
	public WebElement BusinessNameTxtFld;
	
	@FindBy(xpath="//label[@for='option_homeRented_Owned']")
	public WebElement ResidenceRentedicon;
	
	@FindBy(xpath="//label[@for='option_shopRented_Owned']")
	public WebElement BusinessRentedicon;
	
	@FindBy(xpath="//span[.='Proprietorship']")
	public WebElement PropritershipIcon;
	
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	// initialization
	
	public Form_Details1_2(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	// utilization
	

	public WebElement getFullNameTxtFld() {
		return FullNameTxtFld;
	}



	public WebElement getEmailTxtFld() {
		return EmailTxtFld;
	}



	public WebElement getLoanRequirmentIcon() {
		return LoanRequirmentIcon;
	}



	public WebElement getBusinessNameTxtFld() {
		return BusinessNameTxtFld;
	}



	public WebElement getResidenceRentedicon() {
		return ResidenceRentedicon;
	}



	public WebElement getBusinessRentedicon() {
		return BusinessRentedicon;
	}



	public WebElement getPropritershipIcon() {
		return PropritershipIcon;
	}
	
	
	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	// Business Logic
	
	public String FullName(String name) 
	{
		FullNameTxtFld.sendKeys(name);
		return name;
	}
	
	public String Email(String email) 
	{
		EmailTxtFld.sendKeys(email);
		return email;
	}
	
	public String LoanRequirmentIcon() 
	{
		LoanRequirmentIcon.click();
		String text=LoanRequirmentIcon.getText();
		return text;
		
	}
	
	public String  BusinessName(String name) 
	{
		BusinessNameTxtFld.sendKeys(name);
		return name;
	}
	
	public String ResidenceRented() 
	{
		ResidenceRentedicon.click();
		String text=ResidenceRentedicon.getText();
		return text;
		
		
	}
	
	public String BusinessRented() 
	{
		BusinessRentedicon.click();
		String text=BusinessRentedicon.getText();
		return text;
	}
	
	public String Propritership() 
	{
		PropritershipIcon.click();
		String text=PropritershipIcon.getText();
		return text;

	}
	
	public void Continue() 
	{
		ContinueBtn.click();
		
	}
	
}
