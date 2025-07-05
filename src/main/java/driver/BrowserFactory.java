package driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import configuration.ConfigurationManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import logging.WrappedReportLogger;
import report.ExtentFactory;


public class BrowserFactory {

	private static Properties prop = ConfigurationManager.init_prop();
	
	
	// Creating a separate WebDriver instance per thread
	// Keeping each test's WebDriver instance isolated from others
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	

	public WebDriver init_driver(String browser) throws MalformedURLException {
		if (browser.equalsIgnoreCase("Chrome")) {
			setChromeDriver();
			getDriver().manage().deleteAllCookies();
		}
		
		else if (browser.equalsIgnoreCase("RemoteChrome")) {
			setRemoteChromeDriver();
			getDriver().manage().deleteAllCookies();
		}
		// My innovation: this lets you run from middle of feature file************************************************
		else if (browser.equalsIgnoreCase("ChromeDebugger")) {
			setChromeDebuggerDriver();
		} 
		else if (browser.equalsIgnoreCase("HeadlessChrome")) {
			setHeadlessChromeDriver();
			getDriver().manage().deleteAllCookies();
		}
		else {
			WrappedReportLogger.fatal("Please pass the valid browser value: " + browser);
		Assert.fail("Please pass the valid browser value: " + browser);
		ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "Please pass the valid browser value: " + browser);
		}

		
		if (getDriver() == null) {
            WrappedReportLogger.fatal("WebDriver is null after initialization for browser: " + browser);
            ExtentFactory.getInstance().getExtentTest().log(Status.FAIL,
                    "WebDriver is null after initialization for browser: " + browser);
            Assert.fail("WebDriver is null. Initialization failed.");
        } else {
            WrappedReportLogger.trace("Browser initialized successfully: " + browser);
            ExtentFactory.getInstance().getExtentTest().log(Status.PASS,
                    "Browser initialized successfully: " + browser);
        }
		
		return getDriver();
	}
	
	
	
	// synchronized: it adds a lock to ensure only one thread executes this method at a time.
	// i am  overly defensive, because ThreadLocal.get() is already thread-safe.
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public void setChromeDriver() {
		WrappedReportLogger.trace("Starting Chrome Browser");
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.silentOutput", "true");
		WebDriver driver = new ChromeDriver();
		// zooming out
		driver.get("chrome://settings/");
		((JavascriptExecutor) driver).executeScript("chrome.settingsPrivate.setDefaultZoom(0.80);");
		tlDriver.set(driver);
		getDriver().manage().window().maximize();
		WrappedReportLogger.trace("Started Chrome Browser");
		ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Started Chrome Browser");
	}
	
	public void setRemoteChromeDriver() throws MalformedURLException {
		WrappedReportLogger.trace("Starting Remote Chrome Browser");
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();

        WebDriver driver = new RemoteWebDriver(new URL(prop.getProperty("seleniumGridAddress")), options);
        tlDriver.set(driver);
        getDriver().manage().window().maximize();
        WrappedReportLogger.trace("Started Remote Chrome Browser");
		ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Started Chrome Browser");
	}

	public void setHeadlessChromeDriver() {
		WrappedReportLogger.trace("Starting Headless Chrome Browser");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.silentOutput", "true");
		tlDriver.set(new ChromeDriver(options));
		getDriver().manage().window().maximize();
		WrappedReportLogger.trace("Started Headless Chrome Browser");
		ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Started Headless Chrome Browser");
	}

	public void setChromeDebuggerDriver() {
		WrappedReportLogger.trace("Starting Chrome Debugger Instance");
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", prop.getProperty("debuggerAddress"));
		tlDriver.set(new ChromeDriver(options));
		WrappedReportLogger.trace("Started Chrome Debugger Instance");
	}


}
