package AA_Flow;

import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.JavaUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Stepper_Route extends BaseClass {

	
	public static void main(String[] args) throws SQLException, Throwable {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://loans-staging.flexiloans.com/?nlp=1");
		WebElement ele=driver.findElement(By.id("mobile-no"));
		ele.sendKeys(JavaUtility.random9NoFlipkart());
		System.out.println(ele.toString());
		driver.findElement(By.xpath("//select[@class='form-control sales-drpdwn']")).click();
		driver.findElement(By.xpath("//option[.='2-4 lakhs']")).click();
		driver.findElement(By.xpath("//span[text()='Yes']")).click();
		driver.findElement(By.xpath("//span[text()='Check loan Eligibility']")).click();
		
		//String query = "select otp from flexiloans_staging_db.otp_user where mobile_no=" +a+ " order by verification_time desc;";
		
		//dbUtil.connectToDB();
		//dbUtil.executeQueryVerifyAndReturn(null)
		driver.findElement(By.id("mat-input-0")).sendKeys();
		driver.findElement(By.xpath("//button[.='Verify OTP']")).click();
		
		driver.findElement(By.id("mat-input-0")).sendKeys("STAR AGENCIES");
		driver.findElement(By.id("mat-input-1")).sendKeys("sjhvjds@gmail.com");
		
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.close();
		
		driver.navigate().to("\"https://loans-staging.flexiloans.com/?nlp=1\"");
	
		driver.findElement(By.id("mobile-no")).sendKeys("6003890286");
		driver.findElement(By.xpath("//select[@class='form-control sales-drpdwn']")).click();
		driver.findElement(By.xpath("//option[.='2-4 lakhs']")).click();
		driver.findElement(By.xpath("//span[text()='Yes']")).click();
		driver.findElement(By.xpath("//span[text()='Check loan Eligibility']")).click();
		
	//	driver.switchTo("https://loans-staging.flexiloans.com/?nlp=1");
		
		
		
	}
	
	
	
	
	
}
