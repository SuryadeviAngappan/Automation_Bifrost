package POM_Exaa_Offer_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing_1 {

	
	
	// Declaration
	
		@FindBy(id="mat-input-0")
		public WebElement MobileNoTxtFld;
		
		@FindBy(xpath="//button[.=' Get Started! ']")
		public WebElement GetStartedBtn;
		
		
		
		// initialization
		
		public landing_1(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
		}


		// Utilization


		public WebElement getMobileNoTxtFld() {
			return MobileNoTxtFld;
		}



		public WebElement getGetStartedBtn() {
			return GetStartedBtn;
		}
		

		// Business Logic
		
		
		public String MobileNo(String no) 
		{
			MobileNoTxtFld.sendKeys(no);
			return no;
		}
		
		public void GetStarted() 
		{
		     GetStartedBtn.click();	
		} 
		
	
	
	
}
