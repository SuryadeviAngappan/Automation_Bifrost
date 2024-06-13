package POM_NLP1_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class setu{
	
	

	@FindBy(xpath="//button[.='Cancel']")
	public WebElement CancleBtn;

	@FindBy(xpath="//button[.='Cancel data sharing']")
	public WebElement CancleDataSharingBtn;

	@FindBy(xpath="//label[.='I do not want to share my data with Flexiloans-sandbox']")
	public WebElement CancleOption;



	public setu (WebDriver driver) 
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



	public void CancleBtb() 
	{
		CancleBtn.click();
	}

	public void SelectOption() 
	{
		CancleOption.click();
	}

	public void CancleDataSharing() 
	{
		CancleDataSharingBtn.click();
	}


}
