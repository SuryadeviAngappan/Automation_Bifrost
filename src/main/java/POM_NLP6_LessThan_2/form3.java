package POM_NLP6_LessThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form3 
{

	
	@FindBy(xpath="//span[text()='Female']")
	public WebElement GenderIcon;
	
	@FindBy(id="mat-input-5")
	public WebElement DOBFld;
	
	
	@FindBy(id="mat-input-6")
	public WebElement PinCodeFld;
	

	@FindBy(xpath="//span[.='Yes']")
	public WebElement CurrentAccIcon;
	
	@FindBy(id="mat-input-7")
	public WebElement PanNoFld;
	
	
	@FindBy(xpath="//span[.='Manufacturer']")
	public WebElement NatureOfBusiness;
	
	@FindBy(id="mat-select-value-1")
	public WebElement DropDown;
	
	@FindBy(xpath="//span[.='Advertising']")
	public WebElement Product;
	
	@FindBy(xpath="//span[.='Submit']")
	public WebElement SubmitBtn;
	
	@FindBy(xpath="//span[.='More than 5 years']")
	public WebElement HowOldBusiness;
	
	@FindBy(id="mat-input-0")
	public WebElement ShopPinCodeFld;
	
	
	
    @FindBy(xpath="//span[.=' Continue ']")
    public WebElement ContinueBtn;
    
    @FindBy(xpath="//body/app-root/div/div/div/div/div/app-auto-offer/div/div/div/button[@color='primary']/span[1]")
	public WebElement ProceedBtn;
    
    
    
    public form3(WebDriver driver) 
    {
    	PageFactory.initElements(driver, this);
    }





	public WebElement getGenderIcon() {
		return GenderIcon;
	}





	public WebElement getBOBFld() {
		return DOBFld;
	}





	public WebElement getPinCodeFld() {
		return PinCodeFld;
	}





	public WebElement getCurrentAccIcon() {
		return CurrentAccIcon;
	}





	public WebElement getPanNoFld() {
		return PanNoFld;
	}





	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
    // Business Logic
    
    
	
	 public String Gender_NLP_3_L2() 
	    {
	    	
	    	String text=GenderIcon.getText();
	    	return text;
	    }
	
	
    public String Gender() 
    {
    	GenderIcon.click();
    	String text=GenderIcon.getText();
    	return text;
    }
    
    public String DOB(String dob) 
    {
    	DOBFld.sendKeys(dob);
    	return dob;
    }
    
 
    
    public String PinCode(String code) 
    {
    	PinCodeFld.sendKeys(code);
    	return code;
    }
    
    
  
    public String CurrentAccount() 
    {
    	CurrentAccIcon.click();
    	String text=CurrentAccIcon.getText();
    	return text;
    }
    
    public String PanNo(String no) 
    {
    	PanNoFld.sendKeys(no);
    	return no;
    }
    
    public String NatureOfBusiness() 
	{
		NatureOfBusiness.click();
		String text=NatureOfBusiness.getText();
		return text;
	}
	
	public String Product() throws InterruptedException 
	{
		DropDown.click();
		Thread.sleep(2000);
		Product.click();
		String text=Product.getText();
		Thread.sleep(1000);
		SubmitBtn.click();
		Thread.sleep(2000);
		return text;
		
	}
	
	public String BusinessVintage() 
	{
		HowOldBusiness.click();
		String text=HowOldBusiness.getText();
		return text;
	}
	
	public String ShopPinCode(String code) 
	{
		ShopPinCodeFld.sendKeys(code);
		return code;
		
	}
	
    
    public void Continue() 
    {
    	ContinueBtn.click();
    }
    
    
	
	
}
