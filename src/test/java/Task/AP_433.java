package Task;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AP_433 {

	DataBaseUtility dbutil = new DataBaseUtility();
	WebDriverUtility wUtil = new WebDriverUtility();
	

	/* check option is visible for super user
	 * */
	@Test(priority=1)
	public  void test_1() throws Throwable {

		String query="update employee_role_map set role_id='2' where employee_id='923844'";
		dbutil.connectToDB();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://leads-staging.flexiloans.com/");
		String originalHandle=driver.getWindowHandle();
		dbutil.executeUpdateQuery(query);
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

		driver.switchTo().window(originalHandle);
		Thread.sleep(15000);
		WebElement dropdown = driver.findElement(By.xpath("//button[@data-ng-show='data.bulkAction']"));
		wUtil.waitAndClickOnElement(dropdown);
		Thread.sleep(2000);
		
		List<WebElement> options = driver.findElements(By.xpath("//ul[@class='dropdown-menu  pull-left']//li//a"));
		String expected="Change Partner";

		for(WebElement option : options)
		{

			if(option.getText().equals(expected)) 
			{
				System.out.println(" Option is visible for super user !!! ");
				break;
				
			}
			
		}
		
		driver.findElement(By.id("userName")).click();
		driver.findElement(By.xpath("//a[.=' Logout']")).click();
		dbutil.closeDB();
		driver.close();


	}

	
	/* check option should not be visible to user exclude super user
	 * */
	@Test(priority=2)
	public void test_2() throws Throwable {

		String query="update employee_role_map set role_id='24' where employee_id='923844'";
		dbutil.connectToDB();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://leads-staging.flexiloans.com/");
		String originalHandle=driver.getWindowHandle();
		dbutil.executeUpdateQuery(query);
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

		driver.switchTo().window(originalHandle);
		Thread.sleep(15000);
		WebElement dropdown = driver.findElement(By.xpath("//button[@data-ng-show='data.bulkAction']"));
		wUtil.waitAndClickOnElement(dropdown);
		Thread.sleep(2000);
		
		List<WebElement> options = driver.findElements(By.xpath("//ul[@class='dropdown-menu  pull-left']//li//a"));
		String expected="Change Partner";

		for(WebElement option : options)
		{

			if(option.getText().equals(expected)) 
			{
				System.out.println(" Option is not visible for execluding super user !!!  ");
				break;
				
			}
			
		}
		driver.findElement(By.id("userName")).click();
		driver.findElement(By.xpath("//a[.=' Logout']")).click();
		dbutil.closeDB();
		driver.close();


	}

	
	

}




