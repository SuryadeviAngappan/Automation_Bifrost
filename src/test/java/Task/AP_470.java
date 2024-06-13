package Task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtilities.DataBaseUtility;
import GenericUtilities.WebDriverUtility;
import LoanCode_Generater.Create_New_Loancode;import groovyjarjarantlr4.v4.runtime.tree.xpath.XPath;

public class AP_470 {

	String loancode="64e89e3fao3l6";
	DataBaseUtility dbutil = new DataBaseUtility();
    
	@Test(priority=1)
	public void TC_01() throws Throwable 
	{

		dbutil.connectToDB(); 
		
		Create_New_Loancode lp = new Create_New_Loancode();
		
	//	loancode=lp.IP_Qalified();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://leads-staging.flexiloans.com/");
		String originalHandle=driver.getWindowHandle();
		driver.findElement(By.xpath("//button[@class='btn btn-round login-btn']")).click();
		for(String handle : driver.getWindowHandles()) 
		{
			try 
			{
				driver.switchTo().window(handle);
				driver.findElement(By.xpath("//input[@type='email']")).sendKeys("mahesh.narvane@flexiloans.com");
				driver.findElement(By.xpath("(//button[@jsname='LgbsSe'])[2]")).click();
				Thread.sleep(4000);
				driver.findElement(By.name("Passwd")).sendKeys("FMahi@583");
				driver.findElement(By.xpath("//span[text()='Next']")).click();

			}
			catch(Exception e) {} 
		}

		driver.switchTo().window(originalHandle); 
		Thread.sleep(20000);
		driver.navigate().to("https://leads-staging.flexiloans.com/applications/"+loancode);
		driver.findElement(By.xpath("//button[normalize-space()='Not Approved']")).click();
		driver.findElement(By.id("selectStatusTwo")).click();
		driver.findElement(By.xpath("//option[.='Rejected post approval']")).click();
		driver.findElement(By.id("selectStatusThree")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//option[@ng-repeat='statusThree in applicationStatusThree']")).click();
		driver.findElement(By.id("reason_0")).click();
		driver.findElement(By.id("remarks")).sendKeys("test");
		driver.findElement(By.xpath("//button[@data-ng-show='!challengerModelApprove || (challengerModelApprove && application_status_flag)']")).click();
		Thread.sleep(2000);
		String status=dbutil.ExecuteQuery("select application_status from loan_application where code='"+loancode+"'");
		String expected="REJECTED_POST_APPROVAL_S3";
		Assert.assertEquals(expected, status);
		Thread.sleep(2000);
		
		driver.navigate().refresh();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[normalize-space()='Not Approved']")).click();
		driver.findElement(By.id("selectStatusTwo")).click();
		driver.findElement(By.xpath("//option[@value='Lender Rejection']")).click();
		driver.findElement(By.id("selectStatusThree")).click();
		driver.findElement(By.xpath("//option[@value='Banking Norms Not Met']")).click();
		WebElement element=driver.findElement(By.xpath("//div[@data-ng-show='haveReasons']//div//div[1]//div[1]//label[1]"));
		
		if (element.isDisplayed()) 
		{
			System.out.println("  Name Revised Successfully ");
		}
		
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Not Approved']")).click();
		driver.findElement(By.id("selectStatusTwo")).click();
		driver.findElement(By.xpath("//option[@value='Lender Rejection']")).click();
		driver.findElement(By.id("selectStatusThree")).click();
		WebElement ele = driver.findElement(By.xpath("//option[@value='Bureau Record Criteria (BRC)']"));
		ele.click();
		if(ele.isDisplayed()) 
		{
			System.out.println(" CIBIL score norms not met option is visible");
		}
	
		
		dbutil.closeDB();

		driver.close();
		
		
	}
	
	
}
