package POM_NLP1_GreaterThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class landing  {

	// Declaration
	
	
	@FindBy(id="mobile-nlp-3")
	public WebElement NLPMobileNoTxtField;
	
	
	@FindBy(xpath="//div[@class='form-check mr-18']//label[.='4 L - 10 L']")
	public WebElement NLPAvgMonthlySalesDropDown;
	
	
	@FindBy(/*id="gst"*/xpath="//div[@class='form-check mr-18 ml-10']//label[.='Yes'][1]")
	public WebElement NLPGstYesCheckBox;
	
	
	@FindBy(xpath="//span[.='No']")
	public WebElement NLPGstNoCheckBox;
	
	@FindBy(id="next-cta")
	public WebElement CheckLoanEligiblityBtn;
	
	
	@FindBy(id="user-name")
	public WebElement fullname;
	
	
	@FindBy(id="monthlySales3")
	public WebElement amount;
	
		
	
	// initialization
	
	public landing(WebDriver driver)
	{
		
		PageFactory.initElements(driver, this);
	}

	
	
	// utilization

	public WebElement getNLPMobileNoTxtField() 
	{
		return NLPMobileNoTxtField;
	}


	public WebElement getNLPAvgMonthlySalesDropDown() 
	{
		return NLPAvgMonthlySalesDropDown;
	}


	public String getNLPGstYesCheckBox()
	{
		String text =NLPGstYesCheckBox.getText();
		return text;
	}

	public WebElement getNLPGstNoCheckBox()
	{
		return NLPGstNoCheckBox;
	}


	public WebElement getCheckLoanEligiblityBtn()
	{
		return CheckLoanEligiblityBtn;
	}
	
	
	// Business Logic
	
	
	public String Name(String name) 
	{
		fullname.sendKeys(name);
		return name;
	}
	
	
	public String MobileNo(String no) 
	{
		NLPMobileNoTxtField.sendKeys(no);
		return no;
	}
	
	public String AVGMonthlySale() throws InterruptedException 
	{
		amount.click();
		String text =amount.getText();
		return text;
		
	}
	
	public String GSTCheckYesBox() throws InterruptedException 
	{
		NLPGstYesCheckBox.click();
		String text=NLPGstYesCheckBox.getText();
		return text;
	}
	
	public String NLPGstNoCheckBox() throws InterruptedException 
	{
		NLPGstNoCheckBox.click();
		String text=NLPGstNoCheckBox.getText();
		return text;
	}
	
	
	public void amount() 
	{
		amount.click();
	}
	
	public void CheckLoanEligibility() 
	{
		CheckLoanEligiblityBtn.click();
	}
	
	
	
	
}
