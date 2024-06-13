package POM_NLP5_GreaterThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Landing {
	
	

	@FindBy(id="user-name")
	public WebElement FullNameTxtFld;
	
	@FindBy(id="mobile-nlp-3")
	public WebElement MobileNOTxtFld;
	
	@FindBy(/*id="gst"*/xpath="//div[@class='form-check mr-18 ml-10']//label[.='Yes'][1]")
	public WebElement gstIcon;
	
	@FindBy(/*id="monthlySales2"*/xpath="//div[@class='form-check mr-18']//label[.='2 L - 4 L']")
	public WebElement AvgMonthlySaleIcon;
	
	
	@FindBy(id="next-cta")
	public WebElement NextBtn;
	
	
	@FindBy(/*id="createBusiness2"*/xpath="//div[@class='form-check']//label[.='1+ Years']")
	public WebElement HowOldBusinessIcon;
	
	@FindBy(/*id="currentAccount1"*/xpath=/*"//div[@class='form-check mr-18 ml-10']//label[.='Yes'][1]"*/"//div[4]//div[1]//div[1]//div[1]//input[1]")
	public WebElement CurrentAccIcon;
	
	@FindBy(id="nlp-btn-text")
	public WebElement CheckLoanEligibilityBtn;
	
	
	
	
	
	
	
	
	
	public Landing(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getFullNameTxtFld() {
		return FullNameTxtFld;
	}

	public WebElement getMobileNOTxtFld() {
		return MobileNOTxtFld;
	}


	public WebElement getGstIcon() {
		return gstIcon;
	}


	public WebElement getAvgMonthlySaleIcon() {
		return AvgMonthlySaleIcon;
	}
	
	public WebElement getNextBtn() 
	{
		return NextBtn;
	}
	
	public WebElement getHowOldBusinessIcon() 
	{
		return HowOldBusinessIcon;
	}
	
	public WebElement getCurrentAccIcon() 
	{
		return CurrentAccIcon;
	}
	
	
	
	// Business Logic
	
	public String FullName(String name) 
	{
		FullNameTxtFld.sendKeys(name);
	    return name;
	}
	
	public String MobileNO(String no) 
	{
		MobileNOTxtFld.sendKeys(no);
		return no;
		
	}
	
	public String Gst() 
	{
		gstIcon.click();
		String text=gstIcon.getText();
		return text;
	}
	
	public String AvgMonthlySale() 
	{
		AvgMonthlySaleIcon.click();
		String text=AvgMonthlySaleIcon.getText();
		return text;
	}
	
	public void Next() 
	{
		NextBtn.click();
	}
	
	public String HowOldBusiness() 
	{
		HowOldBusinessIcon.click();
		String text=HowOldBusinessIcon.getText();
		return text;
	}
	
	public String CurrentAccIcon() 
	{
		CurrentAccIcon.click();
		String text=CurrentAccIcon.getText();
		return text;
	}

	public void CheckLoanEligibility() 
	{
		CheckLoanEligibilityBtn.click();
	}

}
