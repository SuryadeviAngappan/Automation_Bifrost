package POM_Default_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form2 {

	
	
	@FindBy(id="mat-input-1")
	public WebElement FullNameFld;
	
	
	@FindBy(id="mat-input-3")
	public WebElement PanNoFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	public form2(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getFullNameFld() {
		return FullNameFld;
	}
	
	
	public WebElement getContinueBtn() {
		return ContinueBtn;
	}

	
	// Business Logic
	
	
	public String FullName(String name) 
	{
		FullNameFld.sendKeys(name);
		return name;
	}
	
	public String PanNo(String no) 
	{
		PanNoFld.sendKeys(no);
		return no;
	}
	
	 public void Continue() 
	    {
	    	ContinueBtn.click();
	    }
		
	
}
