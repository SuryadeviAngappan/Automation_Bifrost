package POM_Default_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form1 {
	
	
	@FindBy(id="mat-input-0")
	public WebElement MonthlySaleFld;
	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement GSTIcon;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	public form1(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getMonthlySaleFld() {
		return MonthlySaleFld;
	}


	public WebElement getGSTIcon() {
		return GSTIcon;
	}


	public WebElement getContinueBtn() {
		return ContinueBtn;
	}

	
	// Business Logic
	
	public String MonthlySale(String sale) 
	{
		MonthlySaleFld.sendKeys(sale);
		return sale;
	}
	
	public String GST() 
	{
		GSTIcon.click();
		String text=GSTIcon.getText();
		return text;
	}
	
    public void Continue() 
    {
    	ContinueBtn.click();
    }
	
}
