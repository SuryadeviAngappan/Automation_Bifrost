package POM_NLP3_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class bank_account {

	
	
	
	@FindBy(id="mat-input-7")
	public WebElement BankAccNoFld;
	
	
	@FindBy(id="mat-input-8")
	public WebElement ConformBankAccNoFld;
	
	@FindBy(id="mat-input-9")
	public WebElement IFSCFld;
	
	@FindBy(id="mat-select-value-3")
	public WebElement AccountTypeBtn;
	
	@FindBy(xpath="//span[.='Current']")
	public WebElement CurrentAccFld;
	
	@FindBy(id="mat-input-10")
	public WebElement BenificaryFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	
	public bank_account(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getBankAccNoFld() {
		return BankAccNoFld;
	}



	public WebElement getConformBankAccNoFld() {
		return ConformBankAccNoFld;
	}



	public WebElement getIFSCFld() {
		return IFSCFld;
	}



	public WebElement getAccountTypeBtn() {
		return AccountTypeBtn;
	}



	public WebElement getCurrentAccFld() {
		return CurrentAccFld;
	}



	public WebElement getBenificaryFld() {
		return BenificaryFld;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	
	
	public String BankAccNo(String no) 
	{
		BankAccNoFld.sendKeys(no);
		return no;
	}
	
	public void ConformBankAccNo(String no) 
	{
		ConformBankAccNoFld.sendKeys(no);
	}
	
	public String IFSC(String no) 
	{
		IFSCFld.sendKeys(no);
		return no;
	}
	
	public String AccountType() throws InterruptedException 
	{
		AccountTypeBtn.click();
		Thread.sleep(1000);
		CurrentAccFld.click();
		String text=CurrentAccFld.getText();
		return text;
	}
	
	public String  Benificary(String name) 
	{
		BenificaryFld.sendKeys(name);
		return name;
	}
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
	
	
}
