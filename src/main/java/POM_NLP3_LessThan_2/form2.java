package POM_NLP3_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form2 {
	
	@FindBy(id="mat-input-1")
	public WebElement LoanAmountFld;
	
	
	@FindBy(xpath="//span[.='A Business man']")
	public WebElement BusinessManIcon;
	
	@FindBy(id="mat-input-2")
	public WebElement FullNameTxtFld;
	
	
	@FindBy(id="mat-input-3")
	public WebElement EmailIdFld;
	
	@FindBy(id="mat-input-4")
	public WebElement CorporationDateFld;
	

	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	@FindBy(xpath="//body/app-root/div/div/div/div/div/app-auto-offer/div/div/div/button[@color='primary']/span[1]")
	public WebElement ProceedBtn;

	
	
	public WebElement getLoanAmountFld() {
		return LoanAmountFld;
	}



	public WebElement getBusinessManIcon() {
		return BusinessManIcon;
	}



	public WebElement getEmailIdFld() {
		return EmailIdFld;
	}



	public WebElement getCorporationDateFld() {
		return CorporationDateFld;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}



	
	public form2(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	
	
	// Business Logic
	
	
	
	public String LoanAmount(String loan) 
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
		FullNameTxtFld.sendKeys();
		return name;
	}
	public String EmailID(String id) 
	{
		EmailIdFld.sendKeys(id);
		return id;
	}
	
	public String CorporationDate(String date) 
	{
		CorporationDateFld.sendKeys(date);
		return date;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	
	

}
