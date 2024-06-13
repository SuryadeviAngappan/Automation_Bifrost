package Task;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;

public class BAN_233 {
	
	DataBaseUtility dbutil = new DataBaseUtility();
	
	@Test
	public void LOS() throws Throwable 
	{
		
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

		driver.switchTo().window(originalHandle); //
		Thread.sleep(20000);
		driver.navigate().to("https://leads-staging.flexiloans.com/applications/64991e707hvzs");
		Thread.sleep(4000);
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)","");
		driver.findElement(By.cssSelector("div[data-ng-class=\"{'active' : selectedTab == 'documentsCheck'}\"] div[data-ng-class=\"{'green' : docTab.status == 'Approved','red' : docTab.status == 'Rejected','yellow' : docTab.status == 'Review','none' : docTab.status == 'Clear'}\"] h6")).click();
        Thread.sleep(4000);
		driver.findElement(By.cssSelector("button[data-ng-click='documentUpload()']")).click(); 
        Thread.sleep(4000);
        WebElement ele = driver.findElement(By.xpath("//div[.='Drop or Select Multiple Files from here 1']"));
        
        ele.click();
     
        String path="/Users/fllap0570_maheshnarvane/git/CJP_Mahesh/Project_01/src/test/resources/Files/axis bank current_bs.pdf";
        Thread.sleep(4000);
//		Runtime.getRuntime().exec("osascript "+"/Users/fllap0221/Documents/upload.scpt");
		
		Runtime rntime = Runtime.getRuntime();
		String appletscriptiCommandd = "tell app \"System Events\"\n" +
		
			"keystroke \"G\" using {command down, shift down}\n" +
			
			"delay 3\n" +
			"keystroke \""+path+"\"\n" +
			"delay 3\n" +
			"keystroke return\n" +
			"delay 3\n" +
			"keystroke return\n" +
			"end tell";
		String[] argss = { "osascript", "-e", appletscriptiCommandd };
		Process processs = rntime.exec(argss);
        
        
//        Thread.sleep(2000);
//        ((JavascriptExecutor)driver).executeScript("var x=  document.createElement('INPUT');x.setAttribute('type', 'file'); x.setAttribute('onchange','this.form.submit()');x.setAttribute('hidden', 'true'); arguments[0].appendChild(x);",ele);
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("/Users/fllap0570_maheshnarvane/git/CJP_Mahesh/Project_01/src/test/resources/Files/axis bank current_bs.pdf");
      
     
	}
	

}
