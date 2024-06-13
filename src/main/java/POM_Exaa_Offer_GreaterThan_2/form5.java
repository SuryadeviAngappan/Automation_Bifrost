package POM_Exaa_Offer_GreaterThan_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class form5 {

	
	
	@FindBy(xpath="//span[.='Wholesaler']")
	public WebElement Nature_of_Business;
	
	@FindBy(id="mat-select-value-1")
	public WebElement SelectProductDropDown;

	@FindBy(xpath="//span[.='2 & 3 Wheelers']")
	public WebElement Product;

	@FindBy(xpath="//span[.='Submit']")
	public WebElement SubmitBtn;
	
	@FindBy(xpath="//span[.=' Continue to verify ']")
	public WebElement Continue;
	
	@FindBy(xpath="//span[text()='Proceed with ']")
	public WebElement Proceed;
	
	
	public form5(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	public void setNature_of_Business(WebElement nature_of_Business) {
		Nature_of_Business = nature_of_Business;
	}


	public void setSelectProductDropDown(WebElement selectProductDropDown) {
		SelectProductDropDown = selectProductDropDown;
	}


	public void setProduct(WebElement product) {
		Product = product;
	}


	public void setSubmitBtn(WebElement submitBtn) {
		SubmitBtn = submitBtn;
	}

	
	public String BusinessNature() 
	{
		Nature_of_Business.click();
		String text=Nature_of_Business.getText();
		return text;
	}

	public String ProductYouSell() throws InterruptedException 
	{
		SelectProductDropDown.click();
        Thread.sleep(3000);
        Product.click();
        String text= Product.getText();
        SubmitBtn.click();
        Thread.sleep(2000);
        return text;	

	}
	
	public void Continue() 
	{
		Continue.click();
	}
	
}
