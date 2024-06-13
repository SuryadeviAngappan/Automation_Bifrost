package GenericUtilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.*;


public  class ExtentReport implements ITestListener {

	public  static ExtentTest test;
    public  static ExtentReports reports;
	public static ExtentSparkReporter htmlReporter;
	

    
	public void onStart()  {


		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();

		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/ExtentReports/ExtentReports/"+dtf.format(now)+".html");//specify location of the report
		
		reports=new ExtentReports();

		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("Host name","localhost");
		reports.setSystemInfo("Environemnt","Staging-QA");
		reports.setSystemInfo("user","suryadevi_angappan");

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);

		reports.flush();
	}
	
	
	public void onFinish() 
	{
		
		Reporter.log("execution complete", true);
		reports.flush();
	}


}