package POM_Pine_Labs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Setu {

	

	@FindBy(xpath="//button[.='Cancel']")
	public WebElement CancleBtn;
	
	@FindBy(xpath="//button[.='Cancel data sharing']")
	public WebElement CancleDataSharingBtn;
	
	@FindBy(xpath="//label[.='I do not want to share my data with Flexiloans-sandbox']")
	public WebElement CancleOption;
	
	
	
	public Setu(WebDriver driver) 
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
	
	
	
	public void Cancle() throws InterruptedException 
	{
		CancleBtn.click();
		Thread.sleep(1000);
		
	}
	
	public void Option() throws InterruptedException 
	{
		CancleOption.click();
		Thread.sleep(1500);
	}
	
	public void CancleDataSharing() throws InterruptedException 
	{
		CancleDataSharingBtn.click();
		Thread.sleep(1500);
	}
	
	
}
