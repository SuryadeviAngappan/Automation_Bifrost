package NLP;

import java.awt.AWTException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;


public class NLP_3_dynamic_flow extends BaseClass{

	
	public String sale;
	public String Sale="2 L - 4 L ";
	
JavascriptExecutor js= (JavascriptExecutor)BaseClass.driver;
	
/*	@Test(priority=1)
	public void landing() throws Throwable 
	{
		POM_NLP3_GreaterThan_2.landing lp = new landing(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.NameTxtFld);
		lp.Name("VENKATADRI GAS AGENCY");
		lp.MobileNo(jUtil.random9NoFlipkart());
		lp.Next();
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].scrollIntoView(true);",lp.HowOldBusinessIcon);
		lp.HowOldBusiness();
		lp.CurrentAcc();
		lp.GSTRegisterd();
		((JavascriptExecutor)BaseClass.driver).executeScript("arguments[0].scrollIntoView(true);",lp.AvgSaleBtn);
		lp.MonthlySale();
	    sale=lp.MobileNofld.getText();
		lp.Eligibility();
		
		
		if(sale.equalsIgnoreCase(Sale)) 
		{
		
			Thread.sleep(5000);
			
			NLP3_GreaterThan2Lakh_flow pp = new NLP3_GreaterThan2Lakh_flow();
			
			//pp.Form2();
			pp.SetuPage();
			pp.Statement();
			pp.document();
			//pp.form_3();
			pp.form4();
			pp.form5();
			
			System.out.println(" NLP-3 MTO > 2 Lakh dynamic PreSanction Flow Completed ,  ThankYou!!");
			
			Thread.sleep(5000);
			
			BaseClass.driver.close();
			
		}
		else 
		{
		    Thread.sleep(5000);
			
			NLP3_LessThan2Lakh ll = new NLP3_LessThan2Lakh();
			
		//	ll.DB();
			ll.Otp();
			ll.form2();
			ll.form3();
			ll.document();
			ll.form4();
			ll.setu();
			ll.Bank_Statement();
			ll.form5();
			ll.Bank_Account();
				

			System.out.println(" NLP-3 MTO < 2 Lakh dynamic PreSanction Flow Completed ,  ThankYou!!");
			
			Thread.sleep(5000);
			
			BaseClass.driver.close();
			
			
			
		}
		
	}
	*/
	
}
