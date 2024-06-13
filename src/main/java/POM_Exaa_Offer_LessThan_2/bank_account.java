package POM_Exaa_Offer_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class bank_account {

	
	
	

	@FindBy(id="mat-input-18")
	public WebElement AccountNoFld;
	
	@FindBy(id="mat-input-19")
	public WebElement ConformAccountNoFld;
	
	@FindBy(id="mat-input-20")
	public WebElement IfSCCodeFld;
	
	@FindBy(id="mat-select-value-3")
	public WebElement AccountTypeDropDown;
	
    @FindBy(xpath="//span[.='Current']")
    public WebElement CurrentAccIcon;
    
    @FindBy(id="mat-input-21")
	public WebElement BenificaryNameFld;
    
    @FindBy(xpath="//span[.=' Continue ']")
    public WebElement ContinueBtn;
    
    
    
    
    public bank_account(WebDriver driver) 
    {
    	PageFactory.initElements(driver, this);
    }




	public WebElement getAccountNoFld() {
		return AccountNoFld;
	}




	public WebElement getConformAccountNoFld() {
		return ConformAccountNoFld;
	}




	public WebElement getIfSCCodeFld() {
		return IfSCCodeFld;
	}




	public WebElement getAccountTypeDropDown() {
		return AccountTypeDropDown;
	}




	public WebElement getCurrentAccIcon() {
		return CurrentAccIcon;
	}




	public WebElement getBenificaryNameFld() {
		return BenificaryNameFld;
	}




	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
    
    
	// Business Logic
	
	
	public String AccountNo(String no) 
	{
		AccountNoFld.sendKeys(no);
		return no;
	}
	
	public String ConformAccountNo(String no) 
	{
		ConformAccountNoFld.sendKeys(no);
		return no;
	}
	
	public String IfSCCode(String code) 
	{
		IfSCCodeFld.sendKeys(code);
		return code;
	}
	
	public String AccountType() throws InterruptedException 
	{
		AccountTypeDropDown.click();
		Thread.sleep(500);
		CurrentAccIcon.click();
		String text=CurrentAccIcon.getText();
		return text;
		
	}
	
	public String BenificaryName(String name) 
	{
		BenificaryNameFld.sendKeys(name);
		return name;
	}
	
    
    public void Continue() 
    {
    	ContinueBtn.click();
    }
	
	
}
