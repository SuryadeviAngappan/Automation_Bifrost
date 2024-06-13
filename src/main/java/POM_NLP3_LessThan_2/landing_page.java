package POM_NLP3_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing_page {

	
	@FindBy(id="user-name")
	public WebElement NameTxtFld;
	
	@FindBy(id="mobile-nlp-3")
	public WebElement MobileNofld;
	
	@FindBy(id="next-cta")
	public WebElement NextBtn;
	
	@FindBy(xpath="//input[@value='Greater than 1 year']")
	public WebElement HowOldBusinessIcon;
	
	@FindBy(id="currentAccount1")
	public WebElement CurrentAccIcon;
	
	@FindBy(id="gst")
	public WebElement GstRegisterd;

	@FindBy(id="monthlySales1")
	public WebElement AvgSaleBtn;
	
	@FindBy(id="loan-eligibility")
	public WebElement ChekLoanEligibilityBtn;
	
	
	
	public landing_page(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getNameTxtFld() {
		return NameTxtFld;
	}



	public WebElement getMobileNofld() {
		return MobileNofld;
	}



	public WebElement getNextBtn() {
		return NextBtn;
	}



	public WebElement getHowOldBusinessIcon() {
		return HowOldBusinessIcon;
	}



	public WebElement getCurrentAccIcon() {
		return CurrentAccIcon;
	}



	public WebElement getGstRegisterd() {
		return GstRegisterd;
	}



	public WebElement getAvgSaleBtn() {
		return AvgSaleBtn;
	}



	public WebElement getChekLoanEligibilityBtn() {
		return ChekLoanEligibilityBtn;
	}
	
	
	
	
	public String Name(String name) 
	{
		NameTxtFld.sendKeys(name);
		return name;
	}
	
	public String MobileNo(String no) 
	{
		MobileNofld.sendKeys(no);
		return no;
		
	}
	
	public void Next() 
	{
		NextBtn.click();
	}
	
	
	public String HowOldBusiness() throws InterruptedException 
	{
		HowOldBusinessIcon.click();
		Thread.sleep(1000);
		String text=HowOldBusinessIcon.getText();
		return text;
	}
	
	public String  CurrentAcc() throws InterruptedException 
	{
		CurrentAccIcon.click();
		Thread.sleep(1000);
		String text=CurrentAccIcon.getText();
		return text;
		
	}
	
	public String GSTRegisterd() throws InterruptedException 
	{
		
		GstRegisterd.click();
		Thread.sleep(1000);
		String text=GstRegisterd.getText();
		return text;
	}
	
	public String MonthlySale()
	{
		AvgSaleBtn.click();
		String text=AvgSaleBtn.getText();
		return text;
		
	}
	
	public void Eligibility() 
	{
		ChekLoanEligibilityBtn.click();
	}
	
}
