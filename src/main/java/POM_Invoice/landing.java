package POM_Invoice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing {

	
	
	@FindBy(xpath="//input[@name='email']")
	public WebElement Email;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement Password;
	
	@FindBy(xpath="//span[.=' Sign in ']")
	public WebElement SignIn;
	
	
	public landing (WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getEmail() {
		return Email;
	}


	public WebElement getPassword() {
		return Password;
	}


	public WebElement getSignIn() {
		return SignIn;
	}
	
	
	public String Email(String email) 
	{
		Email.sendKeys(email);
		return email;
		
	}
	
	public String Password(String pass) 
	{
		Password.sendKeys(pass);
		return pass;
	}
	
	public void SignIn() 
	{
		SignIn.click();
	}
	
}
