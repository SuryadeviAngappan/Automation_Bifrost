package POM_NLP6_GreaterThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class landing {
	

	
	@FindBy(id="mobile-no")
	public WebElement MobileNOTxtFld;
	
	@FindBy(xpath="//span[.='Yes']")
	public WebElement gstIcon;
	
	@FindBy(xpath="//select[@class='form-control sales-drpdwn']")
	public WebElement AvgMonthlySaleIcon;
	
	
//	@FindBy(id="next-cta")
//	public WebElement NextBtn;
	
	
//	@FindBy(id="createBusiness2")
//	public WebElement HowOldBusinessIcon;
//	
//	@FindBy(id="currentAccount1")
//	public WebElement CurrentAccIcon;
//	
	@FindBy(id="btn-text")
	public WebElement CheckLoanEligibilityBtn;
	
	
	
	
	public landing(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
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
	
//	public WebElement getNextBtn() 
//	{
//		return NextBtn;
//	}
	
//	public WebElement getHowOldBusinessIcon() 
//	{
//		return HowOldBusinessIcon;
//	}
//	
//	public WebElement getCurrentAccIcon() 
//	{
//		return CurrentAccIcon;
//	}
//	
//	
	
	// Business Logic
	
	
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
		//AvgMonthlySaleIcon.click();
        Select s = new Select(AvgMonthlySaleIcon);
        s.getOptions().get(3).click();
		String text=AvgMonthlySaleIcon.getText();
		return text;
	}
	
//	public void Next() 
//	{
//		NextBtn.click();
//	}
	
//	public void HowOldBusiness() 
//	{
//		HowOldBusinessIcon.click();
//	}
//	
//	public void CurrentAccIcon() 
//	{
//		CurrentAccIcon.click();
//	}
//
	public void CheckLoanEligibility() 
	{
		CheckLoanEligibilityBtn.click();
	}

}
