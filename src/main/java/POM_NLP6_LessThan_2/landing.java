package POM_NLP6_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class landing {

	
	
	@FindBy(id="mobile-no")
	public WebElement MobileNOTxtFld;
	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement gstIcon;
	
	@FindBy(xpath="//select[@class='form-control sales-drpdwn']")
	public WebElement AvgMonthlySale;
	
	

	@FindBy(id="btn-text")
	public WebElement CheckLoanEligibilityBtn;
	
	
	
	
	public landing (WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	

	public WebElement getMobileNOTxtFld() {
		return MobileNOTxtFld;
	}


	public WebElement getGstIcon() {
		return gstIcon;
	}


	public WebElement getAvgMonthlySaleIcon() {
		return AvgMonthlySale;
	}
	

	
	// Business Logic
	
	public String MobileNO(String no) 
	{
		MobileNOTxtFld.sendKeys(no);
		return no;
	}
	
	public String Gst() 
	{
		gstIcon.click();
		String text=gstIcon.getText();
		return text;
	}
	
	public String AvgMonthlySale() 
	{
		Select s = new Select(AvgMonthlySale);
		s.getOptions().get(1).click();
		String ss="less than 2 lakhs";
		return ss;
		
	}
	

	public void CheckLoanEligibility() 
	{
		CheckLoanEligibilityBtn.click();
	}

	
}
