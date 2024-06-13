package POM_LOS;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;
import PropertyFileConfig.ObjectReaders;

public class landing_page_LOS{

	
	@FindBy(xpath="//span[.='Sign in with FlexiLoans Email ID']")
	public WebElement SignInBtn;
	
	
	@FindBy(id="identifierId")
	public WebElement EmailFld;
	
	@FindBy(xpath="//input[@name='Passwd']")
	public WebElement PasswordFld;
	
	@FindBy(xpath="//span[.='Next']")
	public WebElement NextBtn;
			
	
	@FindBy(xpath="//h6[normalize-space()='Documents']")
    public WebElement DocumentIcon;	
	
	@FindBy(xpath=" //span[normalize-space()='Upload Docs']")
	public WebElement UploadDocsIcon;
	
	@FindBy(xpath="//div[@class='row row-padding']")
	public WebElement SelectFile;
	
	@FindBy(xpath="//input[@type='file']")
	public WebElement file; 
	
	@FindBy(xpath=" //select[@name='documentType']")
	public WebElement SelectDocumentCategory;
	
	@FindBy(xpath="//div[@ng-if=\"uploadData.documentArray[$index].category == 'bank_statement'\"]")
	public WebElement BankStatementOption;
	
	@FindBy(id="sel1")
	public WebElement SelectDocumentType;
	
	@FindBy(xpath="//option[.='Bank statement of last 6 months ( Current Account )']")
	public WebElement BankStatementOfLastSixMonthsOption;
	
	
	@FindBy(xpath="//input[@name='Add-Document']")
	public WebElement AddDocumentBtn;
	
	
	
	
	public landing_page_LOS(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}




	public WebElement getSignInBtn() {
		return SignInBtn;
	}




	public WebElement getDocumentIcon() {
		return DocumentIcon;
	}




	public WebElement getUploadDocsIcon() {
		return UploadDocsIcon;
	}




	public WebElement getSelectFile() {
		return SelectFile;
	}




	public WebElement getSelectDocumentCategory() {
		return SelectDocumentCategory;
	}




	public WebElement getBankStatementOption() {
		return BankStatementOption;
	}




	public WebElement getSelectDocumentType() {
		return SelectDocumentType;
	}




	public WebElement getBankStatementOfLastSixMonthsOption() {
		return BankStatementOfLastSixMonthsOption;
	}




	public WebElement getAddDocumentBtn() {
		return AddDocumentBtn;
	}
	
	
	
	
	public void SignIn() 
	{
		SignInBtn.click();
	}
	
	public void Document() 
	{
		DocumentIcon.click();
	} 
	
	
	public void UploadDocs() 
	{
		UploadDocsIcon.click();
	}
	
	public void SelectFile() throws Throwable 
	{
		
		String path =ObjectReaders.readers.BankStatement();
		JavascriptExecutor jse = (JavascriptExecutor)BaseClass.driver;
		
		Thread.sleep(3000);
		((JavascriptExecutor) BaseClass.driver).executeScript("var x=  document.createElement('INPUT');x.setAttribute('type', 'file'); x.setAttribute('onchange','this.form.submit()');x.setAttribute('hidden', 'true'); arguments[0].appendChild(x);",SelectFile);
		Thread.sleep(3000);
		file.sendKeys(path);
	}
	
	
	
	
	
/*# get the button element
ele = driver.find_element_by_id("card-uploader")
# add a hidden file input ( might have to change the onchange event based on the events associated to the button in above line as you don't have a form)
driver.execute_script("var x=  document.createElement('INPUT');x.setAttribute('type', 'file'); x.setAttribute('onchange','this.form.submit()');x.setAttribute('hidden', 'true'); arguments[0].appendChild(x);",ele)
# send the file path here ( this should upload the file)
driver.find_element_by_xpath("//input[@type='file']").send_keys("C:\\Users\\Dhaval Bhimajiyani\\Documents\\Lightshot\\Screenshot_207.png")
*/
}
