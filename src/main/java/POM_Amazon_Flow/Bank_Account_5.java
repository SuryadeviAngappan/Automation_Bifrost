package POM_Amazon_Flow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import GenericUtilities.WebDriverUtility;

public class Bank_Account_5 {


	// Declaration
	
	
	@FindBy(id="mat-input-12")
	public WebElement BankAccountNoTxtFld;
	
	@FindBy(id="mat-input-13")
	public WebElement ReEnterBankAccountNoTxtFld;
	
	@FindBy(id="mat-input-14")
	public WebElement IFSCCodeFld;
	
	@FindBy(id="mat-select-value-1")
	public WebElement AccountTypeDropDownBtn;
	
	@FindBy(id="mat-input-15")
	public WebElement BeneficiaryNameFld;
	
	@FindBy(xpath="//span[.=' Confirm ']")
	public WebElement ConfirmBtn;
	
	
	
	// Initialization
	
	public Bank_Account_5(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	// utilization
	

	public WebElement getBankAccountNoTxtFld() {
		return BankAccountNoTxtFld;
	}



	public WebElement getRenterBankAccountNoTxtFld() {
		return ReEnterBankAccountNoTxtFld;
	}



	public WebElement getIFSCCodeFld() {
		return IFSCCodeFld;
	}



	public WebElement getAccountTypeDropDownBtn() {
		return AccountTypeDropDownBtn;
	}



	public WebElement getBeneficiaryNameFld() {
		return BeneficiaryNameFld;
	}



	public WebElement getConfirmBtn() {
		return ConfirmBtn;
	}
	
	
	// Business Logic
	
	
	
	public void BankAccountNo(String no) 
	{
		BankAccountNoTxtFld.sendKeys(no);
	}
	
	public void ReEnterBankAccountNo(String no) 
	{
		ReEnterBankAccountNoTxtFld.sendKeys(no);
	}
	
	public void IFSCCode(String code) 
	{
		IFSCCodeFld.sendKeys(code);
	}
	
	public void AccountTypeDropDown() throws InterruptedException 
	{
		
		AccountTypeDropDownBtn.click();
		BaseClass.driver.findElement(By.xpath("//span[.='Current']")).click();
		

	}
	
	public void BeneficiaryName(String name) 
	{
		BeneficiaryNameFld.sendKeys(name);
	}
	
	public void Confirm() 
	{
		ConfirmBtn.click();
	}
	
	
	
}
