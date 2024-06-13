package POM_Exaa_Offer_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form5 {
	
	@FindBy(xpath="//span[.='Male']")
	public WebElement Gender;
	
	@FindBy(id="mat-input-4")
	public WebElement DOB;
	
	@FindBy(id="mat-input-5")
	public WebElement PinCode;
	
	@FindBy(id="mat-input-6")
	public WebElement StreetNameFld;
	
	@FindBy(id="mat-input-7")
	public WebElement AreaFld;
	
	@FindBy(id="mat-input-8")
	public WebElement PanCard;
	
	@FindBy(id="mat-input-4")
	public WebElement EmailId;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
		
	
	
	public form5(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}




	


	public WebElement getGender() {
		return Gender;
	}


	public WebElement getDOB() {
		return DOB;
	}


	public WebElement getPinCode() {
		return PinCode;
	}



	public WebElement getStreetNameFld() {
		return StreetNameFld;
	}


	public WebElement getAreaFld() {
		return AreaFld;
	}


	public WebElement getPanCard() {
		return PanCard;
	}


	public WebElement getEmailId() {
		return EmailId;
	}

	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	
	// Business Logic
	
	
	public String Gender() 
	{
		Gender.click();
		String text=Gender.getText();
		return text;
	}
	
	public String DOB(String dob) throws InterruptedException 
	{
		DOB.sendKeys(dob);
		return dob;
		
	}
	
	public String PinCode(String name) 
	{
		PinCode.sendKeys(name);
		return name;
	}
	
	public String StreetName(String no) 
	{
		StreetNameFld.sendKeys(no);
		return no;
	}
	
	public String Area(String area) 
	{
		AreaFld.sendKeys(area);
		return area;
	}
	
	public String PanCard(String name) 
	{
		PanCard.sendKeys(name);
		return name;
	}
	
	public String  EmailId(String name) 
	{
		EmailId.sendKeys(name);
		return name;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	
	
}
