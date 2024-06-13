package POM_Finequs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form_4 {
	
	@FindBy(xpath="//span[.='Proprietorship']")
	public WebElement PropritershipIcon;
	
	@FindBy(id="mat-input-0")
	public WebElement ShopNoFld;
	
	@FindBy(id="mat-input-1")
	public WebElement AreaFld;
	
	@FindBy(xpath="//span[.=' Continue ']")
	public WebElement ContinueBtn;
	
	
	public form_4(WebDriver driver) 
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
	
	
	
	public void Propritership() 
	{
		PropritershipIcon.click();
	}
	
	public void ShopNo(String no) 
	{
		ShopNoFld.sendKeys(no);
	}
	
	public void Area(String area) 
	{
		AreaFld.sendKeys(area);
	}
	
	public void Continue() 
	{
	
		ContinueBtn.click();
	}

}
