package LoanCode_Generater;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import GenericUtilities.DataBaseUtility;
import GenericUtilities.WebDriverUtility;
import PropertyFileConfig.ObjectReaders;

public class Create_New_Loancode {
	
     DataBaseUtility dbutil = new DataBaseUtility();
	
    
	@Test
	public static String IP_Qalified() throws Throwable
	{
		//String loancode=
		
    DataBaseUtility.connectToDB(); 
	WebDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	
	driver.get("https://loancodes-stage.flexiloans.com/");
	WebElement elemen=driver.findElement(By.id("script"));
	WebDriverUtility.handleDropDown(elemen, 0);
	
	WebElement element = driver.findElement(By.xpath("//button[@type='submit']"));
	WebDriverUtility.waitAndClickOnElement(element);
	
	WebElement code=driver.findElement(By.xpath("(//tbody//tr//td)[2]"));
	String loancode=code.getText();
	String name=ObjectReaders.readers.get_FullName();
	
    String query1="update loan_business_detail set legal_status='partnership' where loan_code='"+loancode+"'";
	//DataBaseUtility.executeUpdateQuery(query1);
	String query="update flexiloans_staging_db.loan_applicant_detail set name ='"+name+"' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query);
	String query2="update loan_finance_detail set monthly_total_sales='210000' where loan_code='"+loancode+"'";
	DataBaseUtility.executeUpdateQuery(query2);
	
	Thread.sleep(5000);
	driver.close();
	//DataBaseUtility.closeDB();
	
	return loancode;
	
	}	
	
}
