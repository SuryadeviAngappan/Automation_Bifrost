package GenericUtilities;

import java.awt.AWTException;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Actions;
import java.awt.Robot;
import org.openqa.selenium.support.ui.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.*;


/**
 * This will contain all the generic method related WebDriver actions
 * @author fllap0570_maheshnarvane
 *
 */
public class WebDriverUtility {
	
	
	
       public static  WebDriver driver;

	/**
	 * 
	 * This method will maximize the window
	 * @param driver
	 */
	public static void maximiseWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method will wait for 8 seconds for the entire DOM to load
	 * @param driver
	 */
//	public static void waitForDOMLoad(WebDriver sdriver)
//	{
//		sdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//	}

	public static void waitForDOMLoad(WebDriver driver) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	/**
	 * this method will wait for an element to be visible
	 * @param driver
	 * @param element
	 */
	public static void waitForElementToBeVisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(140));
		wait.until(ExpectedConditions.visibilityOf(element));	
			
	}
	
	/**
	 * this method will wait for an element to be visible
	 * @param driver
	 * @param element
	 */
	
	
	public static void waitForContinueToBeVisible(WebDriver driver, List<WebElement> element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));	
			
	}
	
	/**
	 * This method will wait for an Proceed Btn to be clickable
	 * @param driver
	 * @param element
	 */
	
	public static void waitAndClickOnProceedBtn(WebElement element) throws InterruptedException 
	{
		
		int count =0;
		while(count<65)
		{
			try
			{
				element.click();
				break;
			}
			catch (Exception e) {
		
				Thread.sleep(2000);
				count++;
			}
		}
	}
	
	
	/**
	 * This method will wait for an element to be clickable
	 * @param driver
	 * @param element
	 */
	public static void waitForElementToBeClickable(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	/**
	 * This method will wait until url is load
	 * @param driver
	 * @param url
	 */
	
	public static void waitforurlToload(WebDriver driver , String url) 
	{
		WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		 wait.until(ExpectedConditions.urlContains(url));
				
	}
	/**
	 * this method is a custom wait where it will wait for the particular element
	 * @param element
	 * @throws InterruptedException
	 */
	public static void waitAndClickOnElement(WebElement element) throws InterruptedException
	{
		int count =0;
		while(count<123)
		{
			try
			{
				element.click();
				break;
			}
			catch (Exception e) {
		
				Thread.sleep(2000);
				count++;
			}
		}
	}
	
	
	/**
	 * this method is a custom wait where it will wait for the particular element
	 * @param element
	 * @throws InterruptedException
	 */
	public static void waitAndSendkeyOnElement(WebElement element,String key) throws InterruptedException
	{
		int count =0;
		while(count<123)
		{
			try
			{
				element.sendKeys(key);
				break;
			}
			catch (Exception e) {
		
				Thread.sleep(2000);
				count++;
			}
		}
	}
	
	
	/**
	 * This method will handle drop down using select class based on index
	 * @param element
	 * @param index
	 * @throws InterruptedException 
	 */
	public static void handleDropDown(WebElement element, int index) throws InterruptedException
	{
		Select s = new Select(element);
		Thread.sleep(1000);
		s.selectByIndex(index);
	}
	
	/**
	 * This method will handle drop down using select class based on visible text
	 * @param element
	 * @param visibleText
	 */
	public static void handleDropDown(WebElement element, String visibleText)
	{
		Select s = new Select(element);
		s.selectByVisibleText(visibleText);
	}
	
	
	public static String ckycno() 
	{
		
		String string=RandomStringUtils.randomNumeric(5); 
		String value="400000852037"+string;
		return value;
	}

	
	/**
	 * This method will handle drop down using select class based on value
	 * @param value
	 * @param element
	 */
	public static void handleDropDown(String value,WebElement element)
	{
		Select s = new Select(element);
		s.selectByValue(value);
	}
	
	/**
	 * This method will perform mouse hover action on a particular element
	 * @param driver
	 * @param element
	 */
	public static void mouseHoverOn(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method will double click on the page
	 * @param driver
	 * @param element
	 */
	public static void doubleClickOn(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 * This method will perform double click on a particular element
	 * @param driver
	 * @param element
	 */
	public static void doubleClickOn(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * This method will perform right click on the page
	 * @param driver
	 */
	public static void rightClickOn(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * this method will perform right click on a particular element
	 * @param driver
	 * @param element
	 */
	public static void rightClickOn(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop from source element location
	 * to target element location
	 * @param driver
	 * @param srcElement
	 * @param targetElement
	 */
	public static void dragAndDropOn(WebDriver driver, WebElement srcElement, WebElement targetElement)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(srcElement, targetElement).perform();
	}
	
	/**
	 * This method will press the enter key and release
	 * @throws AWTException
	 */
	public static void pressEnter() throws AWTException
	{
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	/**
	 * this method will accept the alert pop up
	 * @param driver
	 */
	public static void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will cancel the alert pop up
	 * @param driver
	 */
	public  static void dismisAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * this method will get the text of alert popUp
	 * @param driver
	 * @return
	 */
	public static String getTextOfAlert(WebDriver driver)
	{
		String text = driver.switchTo().alert().getText();
		return text;
	}
	
	/**
	 * This method will handle frame based on index
	 * @param driver
	 * @param index
	 */
	public void handleFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will handle frame based on name or id
	 * @param driver
	 * @param nameOrId
	 */
	public static  void handleFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method will handle frame based on frame element
	 * @param driver
	 * @param element
	 */
	public static void handleFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will switch the control back to immediate parent
	 * @param driver
	 */
	public static void toParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method will come out of all the frames
	 * @param driver
	 */
	public static void toDefaultWindow(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will switch from one window to another based on 
	 * partial window title
	 * @param driver
	 * @param partialWinTitle
	 */
	public static void switchToWindow(WebDriver driver, String partialWinTitle)
	{
		//Step 1: get all the window ids
		Set<String> windowIds = driver.getWindowHandles();
		
		//Step 2: iterate thru all the window ids
		Iterator<String> it = windowIds.iterator();
		
		//Step 3: navigate to each window and check the title
		while(it.hasNext())
		{
			//capture the individual window id
			String winId = it.next();
			String currentTtile = driver.switchTo().window(winId).getTitle();
			//compare the current window title with partial window title
			if(currentTtile.contains(partialWinTitle))
			{
				break;
			}
		}
	}
	

	/**
	 * this method will scroll down for 500 units
	 * @param driver
	 */
	public static void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		js.executeScript("window.scrollBy(0,5000)", "");
	}
	
	
	public static void scrollToParticularElement(WebElement element) 
	{
		
		((JavascriptExecutor) BaseClass.driver).executeScript("arguments[0].scrollIntoView(true);", element); 
	}
	
	
	/**
	 * this method will scroll until the element
	 * @param driver
	 * @param element
	 */
	public static void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)BaseClass.driver;
		//js.executeScript("arguments[0].scrollIntoView();", element);
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	
	/**
	 * this method will scroll until the element
	 * @param driver
	 * @param element
	 */
	
	public static void waitforDOMtoRefresh(WebDriver driver , WebElement element) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(null));
	}
	
		

}
