package POM_NLP1_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class landingPage {

	
	
SoftAssert softAssert = new SoftAssert();
	
//Declaration


	@FindBy(id="mobile-nlp-3")
	public WebElement NLPMobileNoTxtField;
	
	
	@FindBy(xpath="//select[@class='form-control sales-drpdwn']")
	public WebElement NLPAvgMonthlySalesDropDown;
	
	
	@FindBy(id="gst")
	public WebElement NLPGstYesCheckBox;
	
	
	@FindBy(xpath="//span[.='No']")
	public WebElement NLPGstNoCheckBox;
	
	@FindBy(id="next-cta")
	public WebElement CheckLoanEligiblityBtn;
	
	
	@FindBy(id="user-name")
	public WebElement fullname;
	
	//less than 2 lakhs
	
	
	
	@FindBy(id="monthlySales1")
	public WebElement amount;
	
	
	
	
	
	// initialization
	
	public landingPage(WebDriver driver)
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
	
	public String GSTCheckYesBox() 
	{
		NLPGstYesCheckBox.click();
		String text=NLPGstYesCheckBox.getText();
		return text;
	}
	
	public String NLPGstNoCheckBox() 
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
