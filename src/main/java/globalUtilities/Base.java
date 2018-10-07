package globalUtilities;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import global.Utilities.customReports.TestcaseReport;

public abstract class Base {

	/**
	 * List of global project variables.
	 */
	protected static AtomicInteger counterMahadevpura = new AtomicInteger(0);
	protected static AtomicInteger counterIndependent = new AtomicInteger(0);
	protected static AtomicInteger counterPayment = new AtomicInteger(0);
	protected static AtomicInteger counterb2bIndependent = new AtomicInteger(0);
	protected static AtomicInteger counterDiffCityUser = new AtomicInteger(0);
	protected static AtomicInteger counterDSJIT5K = new AtomicInteger(0);
	protected static AtomicInteger counterDSJITNon5k = new AtomicInteger(0);
	public static final int retryCount = 3;
	public static WebDriverWait wait;
	public static Actions action;
//	public TestcaseReport report;
	public static Properties config;
	public static final int explicitWaitTime = 60;
	public static final String operatingSystem = System.getProperty("os.name");
	public static String testAdminReportPath = "null";
	public static String masterAdminReportPath = System.getProperty("user.dir") + "/adminReports/master/onDemand/";
	public static boolean isBeforeSuiteExecuted = false;
	public static String suiteStartTime = null;
	public static String testAdminReportPathSch;
	public static String masterJSONFilePath = System.getProperty("user.dir") + "/src/main/resources/JSON_File/";

	/**
	 * List of User Parameters
	 */
	public static final String groupToExecute;
	public static String browser;
	public static String serverName;
	public static final boolean isScreenshotRequired;
	public static final String buildNumber;
	public static boolean sendEmail;
	public static final String[] emailTo;
	public static boolean gridMode;
	public static String adminId;
	public static String adminPswd;


	public static String jobName;
	public static final String trimmedServerName;
	static {
		groupToExecute = System.getProperty("suiteName");
		browser = System.getProperty("browser");
		serverName = System.getProperty("serverName");
		isScreenshotRequired = Boolean.parseBoolean(System
				.getProperty("isScreenshotRequired"));
		buildNumber = System.getProperty("buildNumber");
		sendEmail = Boolean.parseBoolean(System.getProperty("sendEmail","false"));
		emailTo = System.getProperty("emailTo","qabot@bigbasket.com").split(",");
		gridMode = Boolean.parseBoolean(System.getProperty("gridMode"));
		jobName = System.getProperty("jobName");
		trimmedServerName = serverName.replace("bbadmin:bask3t%23%40!@", "");
		adminId = System.getProperty("adminId");
		adminPswd = System.getProperty("adminPswd");

	}

}
