package POM_AA_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Details_6_Page_Submit_Document {

	
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	Details_6_Page_Submit_Document(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getContinueBtn() 
	{
		return ContinueBtn;
	}
	
	
	
	public void ClickOnContinue() 
	{
		ContinueBtn.click();
	}
	
}
