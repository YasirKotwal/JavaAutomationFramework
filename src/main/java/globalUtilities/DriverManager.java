package globalUtilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager extends Base {

	private static DriverManager driverManager = new DriverManager();

	public static DriverManager getInstance() {
		return driverManager;
	}

	/**
	 * Use this method to get an instance of Webdriver or RemoteWebDriver. When
	 * "GridMode" is ON, remotewebdriver is used & when "GridMode" is OFF, webdriver
	 * is used.
	 * 
	 * @return Webdriver
	 * @throws MalformedURLException
	 * @author rakesh
	 */
	public WebDriver getDriver() throws MalformedURLException {
		if (gridMode) {
			return new RemoteWebDriver(new URL("http://192.168.106.36:5555/wd/hub"), getBrowserCapabilities(browser));
		} else {
			switch (browser.toLowerCase()) {
			case "firefox":
				startGeckoDriver();
				FirefoxOptions options = new FirefoxOptions();
				options.setProfile(firefoxProfile());
				return new FirefoxDriver(options);

			case "chrome":
				startChromeDriver();
				return new ChromeDriver(chromeoptions());

			default:
				System.out.println("Selecting firefox as default browser.");
				startGeckoDriver();
				options = new FirefoxOptions();
				options.setProfile(firefoxProfile());
				return new FirefoxDriver(options);
			}
		}
	}

	/**
	 * Use this method to get an instance of Webdriver or RemoteWebDriver for
	 * custom desired capability. When "GridMode" is ON, remotewebdriver is used &
	 * when "GridMode" is OFF, webdriver is used.
	 * 
	 * @return Webdriver
	 * @throws MalformedURLException
	 * @author rakesh
	 */
	public WebDriver getDriver(DesiredCapabilities capability) throws MalformedURLException {
		if (gridMode) {
			return new RemoteWebDriver(new URL("http://192.168.106.36:5555/wd/hub"), getBrowserCapabilities(browser,capability));
		} else {
			switch (browser.toLowerCase()) {
			case "firefox":
				startGeckoDriver();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.merge(capability);
				return new FirefoxDriver(firefoxOptions);

			case "chrome":
				startChromeDriver();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.merge(capability);
				return new ChromeDriver(chromeOptions);

			default:
				System.out.println("Selecting firefox as default browser.");
				startGeckoDriver();
				FirefoxOptions defaultOptions = new FirefoxOptions();
				defaultOptions.merge(capability);
				return new FirefoxDriver(defaultOptions);
			}
		}
	}

	/**
	 * Used to tear down a given Instance of WebDriver.
	 * 
	 * @param driver
	 * @author rakesh
	 */
	public void removeDriver(WebDriver driver) {
		driver.quit();
	}

	/**
	 * Used to set GeckoDriver property before launching firefox browser.
	 * 
	 * @author rakesh
	 */
	public static void startGeckoDriver() {
		switch (operatingSystem.toLowerCase().split(" ")[0]) {
		case "windows":
			System.setProperty("webdriver.gecko.driver", "src//main//resources//drivers//geckodriver.exe");
			break;

		case "linux":
			System.setProperty("webdriver.gecko.driver", "src//main//resources//drivers//geckodriverLinux");
			break;

		case "mac os x":
			System.setProperty("webdriver.gecko.driver", "src/main//resources//drivers//geckodriverMac");
			break;

		default:
			System.out.println("Operating system not supported.");
			break;
		}
	}

	/**
	 * Used to set chromedriver property before launching Chrome browser.
	 * 
	 * @author rakesh
	 */
	public static void startChromeDriver() {
		switch (operatingSystem.toLowerCase().split(" ")[0]) {
		case "windows":
			System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriver.exe");
			break;

		case "linux":
			System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriverLinux");
			break;

		case "mac":
			System.setProperty("webdriver.chrome.driver", "src//main//resources//drivers//chromedriverMac");
			break;

		default:
			System.out.println("Operating system not supported.");
			break;
		}
	}

	/**
	 * Used to create a custom firefox profile.
	 * 
	 * @return FirefoxProfile
	 * @author rakesh
	 */
	public static FirefoxProfile firefoxProfile() {

		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.download.dir", testAdminReportPath);
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv;application/vnd.ms-excel");
		firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
		firefoxProfile.setPreference("geo.enabled", false);
		return firefoxProfile;
	}

	/**
	 * Used to create a custom profile|options in Chrome.
	 * 
	 * @return ChromeOptions
	 * @author rakesh
	 */
	public static ChromeOptions chromeoptions() {
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", testAdminReportPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		options.setCapability("locationContextEnabled", false);
		return options;
	}

	/**
	 * Based on browser choosen, this method returns the DesiredCapablity for the
	 * specified browser.
	 * 
	 * @param browserType
	 * @return DesiredCapabilities
	 * @author rakesh
	 */
	private static DesiredCapabilities getBrowserCapabilities(String browserType) {
		DesiredCapabilities cap = new DesiredCapabilities();
		switch (browserType) {
		case "firefox":
			System.out.println("Opening firefox driver");
			cap = DesiredCapabilities.firefox();
			break;
		case "chrome":
			System.out.println("Opening chrome driver");
			cap = DesiredCapabilities.chrome();
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			cap = DesiredCapabilities.firefox();
			break;
		}
		return cap;
	}
	
	/**
	 * Based on browser choosen & given desired capability, this method returns the DesiredCapablity for the
	 * specified browser.
	 * 
	 * @param browserType
	 * @return DesiredCapabilities
	 * @author rakesh
	 */
	private static DesiredCapabilities getBrowserCapabilities(String browserType, DesiredCapabilities capability) {
		DesiredCapabilities cap = new DesiredCapabilities();
		switch (browserType) {
		case "firefox":
			System.out.println("Opening firefox driver");
			cap = DesiredCapabilities.firefox();
			cap.merge(capability);
			break;
		case "chrome":
			System.out.println("Opening chrome driver");
			cap = DesiredCapabilities.chrome();
			cap.merge(capability);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			cap = DesiredCapabilities.firefox();
			cap.merge(capability);
			break;
		}
		return cap;
	}

}
