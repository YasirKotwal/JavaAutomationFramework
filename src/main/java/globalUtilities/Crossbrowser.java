package globalUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class Crossbrowser {
	
	WebDriver driver;

	@Test(enabled = false)
	@Parameters("browser")
	
	public void launchBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/home/yasir/workspace/JavaAutomationFramework/Drivers/chromedriverLinux");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", "/home/yasir/workspace/JavaAutomationFramework/Drivers/chromedriverLinux");

			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.get("http://www.learn-automation.com");
		
		System.out.println(driver.getTitle());
	}
	
}
