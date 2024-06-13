package POM_Default_Flow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form4 {

	@FindBy(id="mat-input-0")
	public WebElement BusinessNameFld;
	
	@FindBy(xpath="//span[.='Proprietorship']")
	public WebElement PropritershipIcon;
	
	@FindBy(id="mat-input-1")
	public WebElement ShopNoFld;
	
	@FindBy(id="mat-input-2")
	public WebElement AreaFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	
	public form4(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getPropritershipIcon() {
		return PropritershipIcon;
	}



	public WebElement getShopNoFld() {
		return ShopNoFld;
	}



	public WebElement getAreaFld() {
		return AreaFld;
	}



	public WebElement getContinueBtn() {
		return ContinueBtn;
	}

	
	// Business Logic
	
	
	public String BusinessName(String name) 
	{
		BusinessNameFld.sendKeys(name);
		return name;
	}
	
	
	
	public String Propritership() 
	{
		PropritershipIcon.click();
		String text=PropritershipIcon.getText();
		return text;
	}
	
	public String ShopNo(String no) 
	{
		ShopNoFld.sendKeys(no);
		return no;
	}
	
	public String Area(String area) 
	{
		AreaFld.sendKeys(area);
		return area;
	}
	
	
	public void Continue() 
	{
		ContinueBtn.click();
	}
}
