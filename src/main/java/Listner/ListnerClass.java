package Listner;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import GenericUtilities.BaseClass;
import GenericUtilities.ExtentReport;
import GenericUtilities.ThreadLocalClass;
import GenericUtilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ListnerClass implements ITestListener{

	private static final ExtentReports xtendreport = null;
	public  static ExtentTest test;
	public  static ExtentReports reports;
	public static ExtentSparkReporter htmlReporter;
	
	


	@Override
	
	public void onStart(ITestContext context) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy__HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();

		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/ExtentReports/ExtentReports/"+dtf.format(now)+".html");//specify location of the report

		reports=new ExtentReports();
       // reports.crea
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("Host name","localhost");
		reports.setSystemInfo("Environemnt","Staging-QA");
		reports.setSystemInfo("user","SuryaDevi_Angappan");

		htmlReporter.config().setDocumentTitle("FexliLoan UI Automation Execution Report"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Execution Report"); // name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);

	
	}
	public void onBeforeClass(ITestClass testClass) {
		//ExtentTest child = classLevelLogger.get().createNode(method.getName());
		//ExtentTest parent = extent.createTest(getClass().getSimpleName());
		ExtentTest classlevel = reports.createTest(testClass.getName());
	
	}
	
	public void TestFailure(ITestResult result)
    {
        
        
        //testlevelLog().log(Status.FAIL, "Test case is failed"+""+result.getName());
        //test.log(Status.FAIL, result.getThrowable());
        String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        testlevelLog().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occurred:Click to see"
                + "</font>" + "</b >" + "</summary>" + result.getThrowable()+"\n"+excepionMessage.replaceAll(",", "<br>") + "</details>"
                + " \n");
        //test.log(Status.FAIL, result.getThrowable());
    
        reports.flush();
        
    }
	
	
	
	//@AfterSuite
	@Override()
	public void onFinish(ITestContext context) {
		Reporter.log("execution complete", true);
		reports.flush();
	}

	
	

	public void onAfterClass(ITestClass testClass)
	{
		
       reports.flush();
  
	}

	
	//@Override
	public void onTestStart(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		test = reports.createTest(methodName);
		test.log(Status.INFO,"test script execution started");


	}
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();		
		test.log(Status.PASS, methodName+" -- PASS");
        reports.flush();
	}

	//@Override
	@AfterMethod
	public  void onTestFailure(ITestResult result) 
	{


		if(ITestResult.FAILURE==result.getStatus())
		{
			//test.fail(" Failed Test Case", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64()).build());
			test.log(Status.FAIL, result.getThrowable());
			reports.flush();
		}

	}


	@Override
	public void onTestSkipped(ITestResult result) 
	{

		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName+" -- SKIP");
		test.log(Status.SKIP, result.getThrowable());


	}


	//Method for adding logs passed from test cases
	
	public static void reportLog(String message) 
	{    
		test.log(Status.INFO, message );//For extentTest HTML report
		Reporter.log(message);
	}



	/* Method to take screenshot without giving path
	 * 
	 */

	public String getBase64() 
	{
		return ((TakesScreenshot)BaseClass.driver).getScreenshotAs(OutputType.BASE64);
	}
	public  static ExtentTest testlevelLog()
    {
        return ThreadLocalClass.gettestlevel();
    }

	
	


}
