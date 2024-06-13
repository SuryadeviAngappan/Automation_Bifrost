package Pract;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class New {

	public static void main(String[] args) {

		//System.setProperty("webdriver.chrome.driver", "/Users/fllap0570_maheshnarvane/Downloads/chromedriver_mac_arm64 (3)/chromedriver");
		System.setProperty("webdriver.chrome.driver", "/Users/fllap0570_maheshnarvane/git/CJP_Mahesh/Project_01/src/test/resources/chromedriver");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://loans-staging.flexiloans.com/?nlp=1");

	}

}
