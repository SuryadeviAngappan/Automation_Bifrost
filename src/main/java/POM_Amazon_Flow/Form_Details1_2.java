package POM_Amazon_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Form_Details1_2 {

	
	// Declaration
	
		@FindBy(id="mat-input-0")
		public WebElement FullNameTxtFld;
		
		@FindBy(id="mat-input-1")
		public WebElement EmailTxtFld;
		
		@FindBy(xpath="//span[.='1 - 2 lakhs']")
		public WebElement LoanRequirmentIcon;
		
		@FindBy(id="mat-input-2")
		public WebElement BusinessNameTxtFld;
		
		@FindBy(xpath="//span[.='No']")
		public WebElement ResidenceRentedicon;
		
		@FindBy(xpath="//span[.='No']")
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
		
		public void FullName(String name) 
		{
			FullNameTxtFld.sendKeys(name);
		}
		
		public void Email(String email) 
		{
			EmailTxtFld.sendKeys(email);
		}
		
		public void LoanRequirmentIcon() 
		{
			LoanRequirmentIcon.click();
		}
		
		public void BusinessName(String name) 
		{
			BusinessNameTxtFld.sendKeys(name);
		}
		
		public void ResidenceRented() 
		{
			ResidenceRentedicon.click();
		}
		
		public void BusinessRented() 
		{
			BusinessRentedicon.click();
		}
		
		public void Propritership() 
		{
			PropritershipIcon.click();
		}
		
		public void Continue() 
		{
			ContinueBtn.click();
		}
	
}
