package POM_Finequs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form_1 {

	
	@FindBy(id="mat-input-0")
	public WebElement MonthlySalefld;
	
	@FindBy(xpath="//label[@for='option_has_gst_1']")
	public WebElement GstIcon;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	

	public form_1(WebDriver driver) 
	{
	
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	
	public WebElement getMonthlySalefld() {
		return MonthlySalefld;
	}




	public WebElement getGstIcon() {
		return GstIcon;
	}




	public WebElement getContinueBtn() {
		return ContinueBtn;
	}




	
	public void MonthlySale(String sale) 
	{
		MonthlySalefld.sendKeys(sale);
	}
	
	public void Gst() 
	{
		GstIcon.click();
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
}
