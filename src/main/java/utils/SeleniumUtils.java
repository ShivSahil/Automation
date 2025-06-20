package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import logging.WrappedReportLogger;
import report.ExtentFactory;

public class SeleniumUtils {

	public static void navgateToUrl(WebDriver driver, String url, String title, int timeOut) {
		try {
			// in case of chrome debugger do nothing
			if (!url.equalsIgnoreCase("START CHROME DEBUGGER")) {
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				new WebDriverWait(driver, timeOut).until(ExpectedConditions.titleContains(title));
				ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "User able to navigate to URL:" + url);
			}
		}
		catch (Exception e) {
			WrappedReportLogger.fatal("Unable to navigate to URL:" + url);
			Assert.fail("Unable to navigate to URL:" + url);
			ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "User unable to navigate to URL:" + url);
		}
	}
	

}
