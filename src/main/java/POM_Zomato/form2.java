package POM_Zomato;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form2 {

	
	
	@FindBy(id="mat-input-2")
	public WebElement LoanAmount;
	

	@FindBy(xpath="//span[.='A Business man']")
	public WebElement BusinessMan;
	
	@FindBy(id="mat-input-3")
	public WebElement FullNameTxtField;
	
	@FindBy(id="mat-input-4")
	public WebElement EmailIdTxtField;
	
	
	
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement ContinueBtn;
	
	
	// initialization
	
	public form2(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	// Utilization
	
	
	public WebElement getLoanAmount() {
		return LoanAmount;
	}


	public WebElement getBusinessMan() {
		return BusinessMan;
	}


	public WebElement getFullNameTxtField() {
		return FullNameTxtField;
	}


	public WebElement getEmailIdTxtField() {
		return EmailIdTxtField;
	}


	public WebElement getContinueBtn() {
		return ContinueBtn;
	}

	
	// Business Logic
	
	
	public String LoanAmount(String name) 
	{
		LoanAmount.sendKeys(name);
		return name;
	}
	
	

	public String BusinessMan( ) 
	{
		BusinessMan.click();
		String id=BusinessMan.getText();
		return id;
	}
	
	
	public String FullName(String name) 
	{
		FullNameTxtField.sendKeys(name);
		return name;
	}
	
	
	public String EmailId(String id) 
	{
		EmailIdTxtField.sendKeys(id);
		return id;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	

}
