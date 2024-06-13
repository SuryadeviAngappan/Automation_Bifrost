package POM_AA_Flow;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AA_bankPage_Try_AnotherMethod {

	
	@FindBy(xpath="//button[.='Try Another Method']")
	public WebElement TryAnotherMethodBtn;
	
	
	
	
	
	
	


	public AA_bankPage_Try_AnotherMethod(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}





	public void TryAnotherMethod() 
	{
		TryAnotherMethodBtn.sendKeys(Keys.PAGE_DOWN);
		TryAnotherMethodBtn.click();
	}
	
	
	
	
	
}
