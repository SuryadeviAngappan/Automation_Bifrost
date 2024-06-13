package Task;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Default_Flow.Default_Flow;
import GenericUtilities.BaseClass;

@Listeners(Listner.ListnerClass.class)
public class task  extends BaseClass{


	Default_Flow lp = new Default_Flow();
	String loancode;
	@Test
	public void cjp() throws Throwable 
	{

     	lp.launchURL();
		lp.landing_Defaulta_Flow();
		lp.form1();
		lp.form2();
		lp.form3();
		lp.form_2();
		lp.form_3();
		lp.setu();
		lp.BankStatement();

		//String query_no=dbUtil.getQuery("loancode", no);
		//loancode=dbUtil.ExecuteQuery(query_no);		
        System.out.println("LoanCode ="+loancode);
		driver.close();
	}

	@Test (dependsOnMethods = "cjp")
	public void los() throws InterruptedException 
	{

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
				Thread.sleep(5000);
				driver.findElement(By.name("Passwd")).sendKeys("Fmahi@583");
				driver.findElement(By.xpath("//span[text()='Next']")).click();

			}
			catch(Exception e) {} 
		}
        //Thread.sleep(2000);
		driver.switchTo().window(originalHandle);
		Thread.sleep(30000);
	    String url="https://leads-staging.flexiloans.com/applications/";
		driver.navigate().to(url+loancode);

	}



}
