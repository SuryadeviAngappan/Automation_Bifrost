package POM_Flipkart_Flow;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.github.dockerjava.api.model.Driver;

import GenericUtilities.BaseClass;
import GenericUtilities.WebDriverUtility;

public class Bank_Account_5  {

	JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
	
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
	
	@FindBy(xpath="//span[.='Current']")
	public WebElement CurrentAcc;
	
	
	
	
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
	
	
	
	public String BankAccountNo(String no) 
	{
		BankAccountNoTxtFld.sendKeys(no);
		return no;
	}
	
	public void ReEnterBankAccountNo(String no) 
	{
		ReEnterBankAccountNoTxtFld.sendKeys(no);
	}
	
	public String IFSCCode(String code) 
	{
		IFSCCodeFld.sendKeys(code);
		return code;
	}
	
	public String AccountTypeDropDown() throws InterruptedException 
	{
		
		AccountTypeDropDownBtn.click();
		Thread.sleep(1000);
		CurrentAcc.click();
		String text=CurrentAcc.getText();
		return text;

		
	}
	
	public String BeneficiaryName(String name) 
	{
		BeneficiaryNameFld.sendKeys(name);
		return name;
	}
	
	public void Confirm() 
	{
		ConfirmBtn.click();
	}
	
}
