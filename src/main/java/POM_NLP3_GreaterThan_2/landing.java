package POM_NLP3_GreaterThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing {
	
	
	@FindBy(id="user-name")
	public WebElement NameTxtFld;
	
	@FindBy(id="mobile-nlp-3")
	public WebElement MobileNofld;
	
	@FindBy(id="next-cta")
	public WebElement NextBtn;
	
	@FindBy(xpath="//div[@class='form-check']//label[.='1+ Years']")
	public WebElement HowOldBusinessIcon;
	
	@FindBy(/*id="currentAccount1"*/xpath="//div[@class='form-check mr-18 ml-10']//label[.='Yes'][1]")
	public WebElement CurrentAccIcon;
	
	@FindBy(/*id="gst"*/xpath="//div[@class='form-check mr-18 ml-10']//label[.='Yes'][1]")
	public WebElement GstRegisterd;

	@FindBy(xpath="//div[@class='form-check mr-18']//label[.='2 L - 4 L'][1]")
	public WebElement AvgSaleBtn;
	
	@FindBy(id="loan-eligibility")
	public WebElement ChekLoanEligibilityBtn;
	
	
	
	public landing(WebDriver driver) 
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
	
	
	public String HowOldBusiness() 
	{
		HowOldBusinessIcon.click();
		String text=HowOldBusinessIcon.getText();
		return text;
	}
	
	public String CurrentAcc() 
	{
		CurrentAccIcon.click();
		String text=CurrentAccIcon.getText();
		return text;
		
	}
	
	public String GSTRegisterd() 
	{
		
		GstRegisterd.click();
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
