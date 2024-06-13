package POM_Invoice;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class partner {

	
	@FindBy(xpath="//span[.='Transactions In Progress']")
	public WebElement Transection;
	
	@FindBy(xpath="//input[@aria-controls='pendingApprovals']")
	public WebElement Search;
	
	@FindBy(xpath="//span[.='Maker Approve']")
	public WebElement Maker_Approve;
	
	@FindBy(id="submit")
	public WebElement Submit;
	
	@FindBy(xpath="//input[@type='checkbox']")
	public WebElement CheckBox;
	
	@FindBy(xpath="//span[.='Checker Approve']")
	public WebElement Checker_Approve;
	
	@FindBy(xpath="//button[.='Save changes']")
	public WebElement Save_Btn;
	
	@FindBy(xpath="//input[@placeholder='Please Enter OTP']")
	public WebElement Otp;
	
	@FindBy(xpath="//button[.='Submit']")
	public WebElement Final_Submit;
	
	
	public partner(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getTransection() {
		return Transection;
	}



	public WebElement getSearch() {
		return Search;
	}



	public WebElement getMaker_Approve() {
		return Maker_Approve;
	}



	public WebElement getSubmit() {
		return Submit;
	}



	public WebElement getCheckBox() {
		return CheckBox;
	}



	public WebElement getChecker_Approve() {
		return Checker_Approve;
	}



	public WebElement getSave_Btn() {
		return Save_Btn;
	}
	
	
	public void Transection() 
	{
		Transection.click();;
	}
	
	public void Search(String key) 
	{
		Search.sendKeys(key);
	}
	
	public void Maker_Approve() 
	{
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();", Maker_Approve);
	}
	
	public void Submit() 
	{
		Submit.click();
	}
	
	public void CheckBox() 
	{
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();", CheckBox);

		//CheckBox.click();
	}
	
	public void Checker_Approve() 
	{
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();", Checker_Approve);

	}
	
	public void Save_Btn() 
	{
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();", Save_Btn);

	}
	
	public void Otp(String no) 
	{
		Otp.sendKeys(no);
	}
	
	
	public void Final_Submit() 
	{
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();", Final_Submit);
	}
	
	
	
	
	
	
}

