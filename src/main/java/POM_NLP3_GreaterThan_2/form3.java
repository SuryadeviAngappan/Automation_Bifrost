package POM_NLP3_GreaterThan_2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.BaseClass;

public class form3 {


	@FindBy(xpath="//label[@for='option_full_name_verify_1']//span[contains(text(),'Yes')]")
	public WebElement FullName_Yes;

	@FindBy(xpath="(//span[contains(text(),'Yes')])[2]")
	public WebElement CurrentAdd_Yes;

	@FindBy(xpath="//label[@for='option_homeRented_Owned']")
	public WebElement ResidenceOwned_Yes;

	@FindBy(xpath="//span[.=' Confirm & Continue ']")
	public WebElement Continue;



	public form3(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}



	public WebElement getFullName_Yes() {
		return FullName_Yes;
	}



	public WebElement getCurrentAdd_Yes() {
		return CurrentAdd_Yes;
	}



	public WebElement getResidenceOwned_Yes() {
		return ResidenceOwned_Yes;
	}



	public WebElement getContinue() {
		return Continue;
	}


	public String FullName() 
	{
		FullName_Yes.click();
		String text=FullName_Yes.getText();
		return text;

	}
	
	public String CurrentAdd() 
	{
		CurrentAdd_Yes.click();
		String text=CurrentAdd_Yes.getText();
		return text;
	}

	public String ResidenceOwned() 
	{
		ResidenceOwned_Yes.click();
		String text=ResidenceOwned_Yes.getText();
		return text;
	}
	
	public void Continue() 
	{
		Continue.click();
	}


}
