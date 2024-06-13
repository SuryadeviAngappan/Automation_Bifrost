package POM_Flow_AHL_app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing {

	
	
	@FindBy(id="mobile-no")
	public  WebElement mobile_no;
	
	
	@FindBy(xpath="//span[.='Check loan Eligibility']")
	public WebElement Continue;
	
	
	
	public landing(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getMobile_no() {
		return mobile_no;
	}



	public void setMobile_no(WebElement mobile_no) {
		this.mobile_no = mobile_no;
	}



	public WebElement getContinue() {
		return Continue;
	}



	public void setContinue(WebElement continue1) {
		Continue = continue1;
	}
	
	
	
	public String  Mobile_No(String no) throws InterruptedException 
	{
		mobile_no.sendKeys(no);
		Thread.sleep(2000);
		String text=mobile_no.getText();
		Thread.sleep(2000);
		return text;
	}
	
	public void Continue() 
	{
		Continue.click();
	}
	
	
}
