package Pract;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import GenericUtilities.BaseClass;

public class Goibibo {
	
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		
		String from ="Pune";
		String to="mumbai";
		
		String URL="https://www.goibibo.com/?utm_source=google&utm_medium=cpc&utm_campaign=DF-Brand-EM&utm_adgroup=Only+Goibibo&utm_term=%21SEM%21DF%21G%21Brand%21RSA%21108599293%216504095653%21602838584772%21e%21goibibo%21c%21&gad=1&gclid=Cj0KCQjw_O2lBhCFARIsAB0E8B8slijWVObAW_9QaTY2w2bE-yL18AG1Y-LSIz5ZXY69M4jm06zbT-waAkL7EALw_wcB";
		
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get(URL);
		
		driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
	    driver.findElement(By.xpath("//span[.='From']/ parent::div[@class='sc-12foipm-16 dwhdnN fswFld ']")).click();
	    Thread.sleep(1000);
	    
	    WebElement From = driver.findElement(By.xpath("//input[@type='text']"));
	    		   From.sendKeys(from);
	    		   Thread.sleep(2000);
	               From.sendKeys(Keys.RETURN);
	    Thread.sleep(2000);
	    
	  
	   // WebElement To = driver.findElement(By.xpath("//span[.='To']/ parent::div[@class='sc-12foipm-37 godvBN']"));
	    WebElement To = driver.findElement(By.xpath("//input[@type='text']"));
	               To.sendKeys(to);
	               Thread.sleep(1000);  
	               To.sendKeys(Keys.ENTER);         
	   Thread.sleep(1000);
	   
	   
	   driver.findElement(By.xpath("//div[@aria-label=('Thu Aug 31 2023')]")).click();
	   Thread.sleep(1000);
	   driver.findElement(By.xpath("//span[.='Done']")).click();
	   Thread.sleep(1000);
	   ////div[@class='sc-12foipm-72 wMrYl']/child::div/descendant::div/descendant::div/following-sibling::div/child::div/child::span[@xpath='1']
	   driver.findElement(By.xpath("//div[@class='sc-12foipm-72 wMrYl']//div[2]//div[1]//span[3]//*[name()='svg']")).click();
	   Thread.sleep(1000);
	   driver.findElement(By.xpath("//a[.='Done']")).click();
	   
	   System.out.println(" You have successfully booked your filght !!");
	    
	   driver.close();
	    
		
		
	}

	 
	
	  

}
