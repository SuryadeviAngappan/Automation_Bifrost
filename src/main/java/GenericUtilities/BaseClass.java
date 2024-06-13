
package GenericUtilities;


import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities.*;
import PropertyFileConfig.ObjectReaders;
import PropertyFileConfig.ConfigReaders;
import PropertyFileConfig.PropertyFileReaders;


import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import Listner.ListnerClass;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class consists of all basic configuration annotations of testNG
 * to perform common functionalities.
 * @author fllap0570_maheshnarvane
 */


public class BaseClass{

	public DataBaseUtility_Dev dbUtil_Dev = new DataBaseUtility_Dev();
	public DataBaseUtility_prod  dbUtilprod= new DataBaseUtility_prod();
	public  DataBaseUtility dbUtil = new DataBaseUtility();
	public ExcelFileUtility eUtil = new ExcelFileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public ReportUtility reportUtil = new ReportUtility();
	public static JavaUtility jUtil = new JavaUtility();
	public VerificationUtility verificationUtil = new VerificationUtility();
	public static WebDriver driver = null;
	public ListnerClass listner = new ListnerClass();
	/*ClassName_MethodName*/


	@BeforeSuite()
	public void bsConfig() throws Throwable
	{
		dbUtil.connectToDB();
		// dbUtilprod.connectToDB();
		//dbUtil_Dev.connectToDB();


		Reporter.log("--- db conection successfull ---",true);



	}


	/*		@Parameters("BROWSER")//it will read value from suite xml file use when we want to do Compatability testing only
		@BeforeTest //use when u want to do parallel execution only
		@BeforeMethod

	 */		
	@BeforeClass(alwaysRun=true)
	//@BeforeMethod
	public void bcConfig(/*String BROWSER*/) throws Throwable

	{


		String BROWSER = ObjectReaders.readers.getBrowser();

		if (BROWSER.equalsIgnoreCase("chrome")) 
		{

		//	System.setProperty("webdriver.chrome.driver", "/src/test/resources/chromedriver");
			//System.setProperty("webdriver.chrome.driver", "/Users/fllap0570_maheshnarvane/Downloads/chromedriver-mac-arm64/chromedriver");

		//	WebDriverManager.chromiumdriver().setup();
		//	 driver = new ChromeDriver(); 
			ChromeOptions option = new ChromeOptions();
//			option.addArguments("--incognito");
//			//option.addArguments("--headless");
			option.addArguments("--disable-dev-shm-usage");
			option.addArguments("--start-maximized");
			driver = new ChromeDriver(option);
//			driver = new ChromeDriver();
			driver.manage().window().maximize();
			Reporter.log(BROWSER+" --- browser launched---", true);

		}
		else if( BROWSER.equalsIgnoreCase("FIREFOX"))
		{

			driver = new FirefoxDriver();
			Reporter.log(BROWSER+" --- browser launched---", true);
		}
		else
		{
			System.out.println("invalid browser name");
		}


	}


	// @AfterClass(alwaysRun=true)
	public void closebrowser() 
	{
		driver.close();
	}



	@AfterSuite
	public void CloseTheDB() throws SQLException 
	{


		dbUtil.closeDB();
		//dbUtilprod.closeDB();
		//dbUtil_Dev.closeDB();
		Reporter.log("----db Closed successfull----", true);


	}


















}
