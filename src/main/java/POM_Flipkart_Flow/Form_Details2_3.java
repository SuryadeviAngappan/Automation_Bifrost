package POM_Flipkart_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Form_Details2_3 {

	
	// Declaration
	
	@FindBy(xpath="//span[.='Male']")
	public WebElement GenderIcon;
	
	@FindBy(id="mat-input-3")
	public WebElement DOBField;
	
	@FindBy(id="mat-input-4")
    public WebElement ResidencePincode;
	
	@FindBy(id="mat-input-5")
	public WebElement PanNoTxtField;
	
	@FindBy(xpath="//span[.=' Show loan offer ']")
	public WebElement ShowLoanOfferBtn;
	
	
	@FindBy(xpath="//body/app-root/div/div/div/div/div/app-auto-offer/div/div/div/button[@color='primary']/span[1]")
	public WebElement ProceedBtn;
	
	
	// Initialization
	
	
	public Form_Details2_3(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	// Utilization
	
	public WebElement getGenderIcon() {
		return GenderIcon;
	}



	public WebElement getDOBField() {
		return DOBField;
	}



	public WebElement getResidencePincode() {
		return ResidencePincode;
	}



	public WebElement getPanNoTxtField() {
		return PanNoTxtField;
	}



	public WebElement getShowLoanOfferBtn() {
		return ShowLoanOfferBtn;
	}
	
	
	
	// Business logic
	
	public String  Gender() 
	{
		GenderIcon.click();
		String text=GenderIcon.getText();
		return text;
	}
	
	public String  DOB(String dob) 
	{
		DOBField.sendKeys(dob);
		return dob;
	}
	
	public String  ResidencePinCode(String code) 
	{
		ResidencePincode.sendKeys(code);
		return code;
	}
	
	public String PanNo(String pan) 
	{
		PanNoTxtField.sendKeys(pan);
		return pan;
	}
	
	public void  ShowLoanOffer() 
	{
		ShowLoanOfferBtn.click();
	}
	
	
	
	
}
