package POM_Exaa_Offer_LessThan_2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form2_3 {

	
	
	@FindBy(id="mat-input-3")
	public WebElement LoanAmountFld;
	
	@FindBy(xpath="//span[.='A Business man']")
	public WebElement BusinessManIcon;
		
	@FindBy(id="mat-input-4")
	public WebElement FullNameFld;
	
	@FindBy(id="mat-input-5")
	public WebElement EmailIdFld;
	
	@FindBy(id="mat-input-6")
	public WebElement DateOfIncorporationFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	@FindBy(xpath="//span[.='Proprietorship']")
	public WebElement PropritershipIcon;
	
	
	
	@FindBy(xpath="//body/app-root/div/div/div/div/div/app-auto-offer/div/div/div/button[@color='primary']/span[1]")
	public WebElement ProceedBtn;
	
	
	public  form2_3(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getLoanAmountFld() {
		return LoanAmountFld;
	}



	public WebElement getBusinessManIcon() {
		return BusinessManIcon;
	}



	public WebElement getFullNameFld() {
		return FullNameFld;
	}



	public WebElement getEmailIdFld() {
		return EmailIdFld;
	}



	public WebElement getDateOfIncorporationFld() {
		return DateOfIncorporationFld;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	
	// Business Logic
	
	public String  LoanAmount(String loan) 
	{
		LoanAmountFld.sendKeys(loan);
		return loan;
	}
	
	public String BusinessMan() 
	{
		BusinessManIcon.click();
		String text=BusinessManIcon.getText();
		return text;
		
	}
	
	public String FullName(String name) 
	{
		FullNameFld.sendKeys(name);
		return name;
	}
	
	public String EmailId(String id) 
	{
		EmailIdFld.sendKeys(id);
		return id;
	}
	
	public String DateOfIncorporation(String date) 
	{
		DateOfIncorporationFld.sendKeys(date);
		return date;
	}
	
	public String Propritership() 
	{
		PropritershipIcon.click();
		String text=PropritershipIcon.getText();
		return text;
		
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
}
