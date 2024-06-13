package POM_Invoice;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import GenericUtilities.BaseClass;

public class vendor {


	@FindBy(xpath="//select[@name='CustomerId2']")
	public WebElement partner_company;

	@FindBy(xpath="//select[@name='LenderId']")
	public WebElement Lendor_company;

	@FindBy(id="InvoiceNumber")
	public WebElement Invoice_No;

	@FindBy(id="invoicevalueid")
	public WebElement Invoice_Value;

	@FindBy(id="filestyle-0")
	public WebElement ChooseFile;

	@FindBy(id="submit")
	public WebElement Submit;
	
	@FindBy(xpath="//input[@aria-controls='pendingApprovals']")
	public WebElement Search;

	@FindBy(xpath="//a[@data-toggle='dropdown']")
	public WebElement Icon;
	
	@FindBy(xpath="(//a[normalize-space()='Logout'])[1]")
	public WebElement Logout;
	


	public vendor(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getPartner_company() {
		return partner_company;
	}



	public WebElement getLendor_company() {
		return Lendor_company;
	}



	public WebElement getInvoice_No() {
		return Invoice_No;
	}



	public WebElement getInvoice_Value() {
		return Invoice_Value;
	}



	public WebElement getChooseFile() {
		return ChooseFile;
	}



	public void partner_company() 
	{
		Select s = new Select(partner_company);
        s.selectByVisibleText("Zetwerk Manufacturing");
	}

	public void Lendor_company() 
	{
		Select s = new Select(Lendor_company);
		s.selectByVisibleText("Epimoney");
	}

	public String Invoice_No(String no) 
	{
		Invoice_No.sendKeys(no);
		return no;
	}

	public String Invoice_Value(String no) 
	{
		Invoice_Value.sendKeys(no);
		return no;
	}

	public void ChooseFile(String file) 
	{
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].removeAttribute('style')", ChooseFile);
		ChooseFile.sendKeys(file);
	}

	public void Submit() 
	{
		Submit.click();
	}

	public String Search(String search) 
	{
		Search.sendKeys(search);
		return search;
	}
	
	public void Logout() throws InterruptedException 
	{
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();", Icon);
		Thread.sleep(2000);
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].click();", Logout);
		
	}
}
