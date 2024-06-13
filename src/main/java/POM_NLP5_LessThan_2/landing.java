package POM_NLP5_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing {
	
	
	
	@FindBy(id="user-name")
	public WebElement FullNameTxtFld;
	
	@FindBy(id="mobile-nlp-3")
	public WebElement MobileNOTxtFld;
	
	@FindBy(id="gst")
	public WebElement gstIcon;
	
	@FindBy(id="monthlySales1")
	public WebElement AvgMonthlySaleIcon;
	
	
	@FindBy(id="next-cta")
	public WebElement NextBtn;
	
	
	@FindBy(id="createBusiness2")
	public WebElement HowOldBusinessIcon;
	
	@FindBy(id="currentAccount1")
	public WebElement CurrentAccIcon;
	
	@FindBy(id="nlp-btn-text")
	public WebElement CheckLoanEligibilityBtn;
	
	
	
	
	
	
	
	
	
	public landing (WebDriver driver) 
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
