package hooks;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import configuration.ConfigurationManager;
import driver.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import logging.WrappedReportLogger;
import report.ExtentFactory;
import utils.Screenshot;
import utils.SeleniumUtils;

public class ApplicationHooksBackup {/*
										 * 
										 * private WebDriver driver; private Properties prop; private BrowserFactory
										 * browserfactory; private static String scenarioName; public static
										 * ThreadLocal<String> tScenarioName = new ThreadLocal<>();
										 * 
										 * // I have not used testng dependency but rather using cucumber-testng
										 * dependency // below instead of priority I am using order as @Before
										 * annotation came from // cucumber-java not testNG!!!
										 * 
										 * @Before(order = 1) public void beforeScenarios(Scenario scenario) {
										 * ExtentTest test = ExtentFactory.getReport().createTest(scenario.getName());
										 * ExtentFactory.getInstance().setExtentTest(test);
										 * 
										 * }
										 * 
										 * @Before(order = 2) public void setUpProperty() { prop =
										 * ConfigurationManager.init_prop(); }
										 * 
										 * @Before(order = 3) public void getScenarioName(Scenario senario) { /// I can
										 * use this in excel in future scenarioName = senario.getName();
										 * tScenarioName.set(scenarioName); }
										 * 
										 * @Before(order = 4) public void launchTheBrowser(Scenario scenario) {
										 * browserfactory = new BrowserFactory();
										 * WrappedReportLogger.trace("STARTING TEST SCENARIO : " +
										 * scenario.getName().toUpperCase()); if
										 * (prop.getProperty("enableChromeDebugger").equalsIgnoreCase("yes")) {
										 * 
										 * driver = browserfactory.init_driver("ChromeDebugger"); } else { // Fetch the
										 * browser parameter from testng.xml. our testNG is created on Run time in
										 * ReRunUtility class // note: you could have retrieved the value from using
										 * System.getProperty("browser1"); also String browserName =
										 * Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
										 * .getParameter("browser");
										 * 
										 * // Check if the browserName value is null OR the browserName did not get
										 * replaced by maven (e.g. -Dbrowser1=chrome) then retrieve value from config
										 * file if (browserName == null || browserName.equalsIgnoreCase("${browser1}"))
										 * { browserName = prop.getProperty("browser"); } driver =
										 * browserfactory.init_driver(browserName);
										 * ExtentFactory.getInstance().getExtentTest().log(Status.PASS,
										 * "Scenario is running on: " + browserName);
										 * 
										 * 
										 * } }
										 * 
										 * @Before(order = 5) public void login() { String url =
										 * prop.getProperty("url"); String loginTitle = prop.getProperty("loginTitle");
										 * if (!prop.getProperty("enableChromeDebugger").equalsIgnoreCase("yes")) {
										 * WrappedReportLogger.trace("Navigating to URL: " + url);
										 * SeleniumUtils.navgateToUrl(driver, url, loginTitle, 30);
										 * WrappedReportLogger.trace("Navigation to URL: " + url + " completed"); } else
										 * { WrappedReportLogger.
										 * trace("Skipping navigation - Chrome Debugger mode is enabled."); } }
										 * 
										 * @After(order = 1) public void afterScenario(Scenario scenario) throws
										 * IOException { // Assign tags from feature file to Extent Report if
										 * (!scenario.getSourceTagNames().isEmpty()) {
										 * ExtentFactory.getInstance().getExtentTest()
										 * .assignCategory(scenario.getSourceTagNames().toArray(new String[0])); }
										 * 
										 * if (scenario.isFailed()) { Screenshot.takeScreenShot(driver, scenario); }
										 * 
										 * else { ExtentFactory.getInstance().getExtentTest().log(Status.PASS,
										 * "SCENARIO COMPLETE"); }
										 * 
										 * // if your report doesnot flush then you will not see screenshot and no tags
										 * on the scenario ExtentFactory.getReport().flush(); }
										 */

}
