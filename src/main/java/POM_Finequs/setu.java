package POM_Finequs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class setu {

	
	
JavascriptExecutor jse = (JavascriptExecutor)BaseClass.driver;
	
	
	@FindBy(xpath="//button[.='Cancel']")
	public WebElement CancleBtn;
	
	@FindBy(xpath="//button[.='Cancel data sharing']")
	public WebElement CancleDataSharingBtn;
	
	@FindBy(xpath="//label[.='I do not want to share my data with Flexiloans-sandbox']")
	public WebElement CancleOption;
	
	
	
	public setu(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getCancleBtn() {
		return CancleBtn;
	}



	public WebElement getCancleDataSharingBtn() {
		return CancleDataSharingBtn;
	}



	public WebElement getCancleOption() {
		return CancleOption;
	}
	
	
	
	public void Cancle() 
	{
		CancleBtn.click();
		
	}
	
	public void Option() 
	{
		CancleOption.click();
	}
	
	public void CancleDataSharing() 
	{
		CancleDataSharingBtn.click();
	}
	
}
