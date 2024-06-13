package POM_Exaa_Offer_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form1_2 {

	
	
	// Declaration
	
		@FindBy(id="mat-input-1")
		public static WebElement MonthlySaleTxtFld;
		
		
		@FindBy(xpath="//span[.='Yes']")
		public WebElement GstRegisterdIcon;
		
		@FindBy(xpath="//span[.=' Continue ']")
		public WebElement ContinueBtn;
		
		
		// Initialization
		
		
		public form1_2(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
		}


		// Utilization

		public WebElement getMonthlySaleTxtFld() {
			return MonthlySaleTxtFld;
			
		}


		public WebElement getGstRegisterdIcon() {
			return GstRegisterdIcon;
		}
		
		
		public WebElement getContinueBtn() {
			return ContinueBtn;
		}
		

		// Business Logic
		
		
		public String MonthlySale(String sale) 
		{
			MonthlySaleTxtFld.sendKeys(sale);
			return sale;
			
		}
		
		public void GstIcon() 
		{
			GstRegisterdIcon.click();
		}
		
		public void Continue() 
		{
		    ContinueBtn.click();
		}
	
}
