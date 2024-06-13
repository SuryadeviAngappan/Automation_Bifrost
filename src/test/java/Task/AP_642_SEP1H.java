package Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import GenericUtilities.WebDriverUtility;

public class AP_642_SEP1H {

	public static void main(String[] args) throws InterruptedException {
		
	
	
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
	
	driver.findElement(By.xpath("//a[contains(@ng-if,\"isAllowedForRole('menu-bulk_lead_upload-button') || isAllowedForRole('menu-manual_lead_upload-button') || isAllowedForRole('menu-rating_upload-button') || isAllowedForRole('whatsapp-bulk-upload')\")]")).click();
	driver.findElement(By.xpath("//a[normalize-space()='Manual Lead']")).click();
	WebElement ele = driver.findElement(By.id("loan_purpose"));
	WebDriverUtility.handleDropDown(ele, 2);
	Thread.sleep(4000);
	
	WebElement element = driver.findElement(By.id("partner_name"));
	
	Select dropdown = new Select(element);

    // Get all options
    List<WebElement> allOptionsElement = dropdown.getOptions();

    // Creating a list to store drop down options
    List options = new ArrayList();


    for(WebElement optionElement : allOptionsElement)
    {
        options.add(optionElement.getText());
    }

    options.remove("Select partner");

    // Creating a temp list to sort
    List tempList = new ArrayList();
    tempList.addAll(options);

    Collections.sort(tempList);

    System.out.println("Sorted List "+ tempList);
    System.out.println("Sorted List "+ options);
    Assert.assertTrue(options.equals(tempList));
    driver.close();
    
  
//
//    boolean ifSortedAscending = options.equals(tempList);
//
//    if(ifSortedAscending)
//    {
//        System.out.println("List is sorted");
//    }
//    else
//        System.out.println("List is not sorted.");
//	
//	
	}
}
