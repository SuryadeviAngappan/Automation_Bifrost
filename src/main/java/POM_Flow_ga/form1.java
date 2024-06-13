package POM_Flow_ga;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form1 {
	
	
	@FindBy(id="mat-input-1")
	public WebElement FullName;
	
	
	
	public form1(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	
	public String Full_Name(String name) 
	{
		FullName.sendKeys(name);
		String text=FullName.getText();
		return text;
		
	}

}
