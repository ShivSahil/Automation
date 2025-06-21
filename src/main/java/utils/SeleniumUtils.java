package utils;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import configuration.ConfigurationManager;
import logging.WrappedReportLogger;
import report.ExtentFactory;

public class SeleniumUtils {
	
	//Note: please use the ExpectedConditions used below are these are best one to be used here
	// No implicitlywait is user here as it is Interference with explicitwait
	
	
	//Since i was short on time: select by visible Text, Select by Index, isEnabled, isElementAvailable, getAttribute, getTagName, getDropDownOptions, getTextBoxValue, size, hoverAbdClick etc etc
	private static Properties prop = ConfigurationManager.init_prop();

	public static void navgateToUrl(WebDriver driver, String url, String title, int timeOut) {
		try {
			// in case of chrome debugger do nothing
			if (!url.equalsIgnoreCase("START CHROME DEBUGGER")) {
				driver.get(url);
				
				new WebDriverWait(driver, timeOut).until(ExpectedConditions.titleContains(title));
				ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "User able to navigate to URL: " + url);
			}
		}
		catch (Exception e) {
			WrappedReportLogger.fatal("Unable to navigate to URL:" + url);
			Assert.fail("Unable to navigate to URL:" + url);
		}
	}
	
	public static WebElement getElement(WebDriver driver, By locator, int timeout, String fieldName) {
		try {
			return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
		}
		catch(Exception e) {
			WrappedReportLogger.error("Unable to find '"+fieldName+"' with locator '"+locator+"'");
			Assert.fail("Unable to find '"+fieldName+"' with locator '"+locator+"'");
		}
		return null;
	}
	
	public static void highlighterMethod(WebDriver driver, WebElement element) throws InterruptedException {
	    try {
	        if (prop.getProperty("visualHighlightingEnabled").equalsIgnoreCase("yes")) {
	           // this is good way to highlight but below implementation is better ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	        	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
	            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style','border:2px solid green;');", element);
	            Thread.sleep(300);
	            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	        }
	    } catch (InterruptedException e) {
	        WrappedReportLogger.warn("Highlighting was interrupted for element '"+element+"'");
	        Thread.currentThread().interrupt(); 
	    } catch (Exception e) {
	        WrappedReportLogger.warn("Could not highlight element '" + element+"'");
	    }
	}
	
	public static void setTextEntry(WebDriver driver, By locator, String textValue, int timeOut, String fieldName ) {
		
		try {
			WebElement element = getElement(driver, locator, timeOut, fieldName);
			highlighterMethod(driver,element);
			if(textValue.equalsIgnoreCase("SEND NO ADDITIONAL DATA")) {
				element.sendKeys("");
			}
			else {
				element.clear();
				element.sendKeys(textValue);
			}
			if(fieldName.contains("Password")) {
				textValue="********";
			}
			ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Filled '"+textValue+"' value in '"+fieldName+"' textBox");
		}
			catch(Exception e) {
				//******  ExtentFactory class already has ExtentFactory.getInstance().getExtentTest().log(Status.FAIL,xxxxxxxx) hence I didnot write Status.FAIL over here
				WrappedReportLogger.error("Unable to set '"+textValue+"' value in '"+fieldName+"' textBox with locator '"+locator+"'");
				Assert.fail("Unable to set '"+textValue+"' value in '"+fieldName+"' textBox with locator '"+locator+"'");
			}
	}
	
	public static void selectByValue(WebDriver driver, By locator, String value, int timeout, String fieldName) {
		try {
			 WebElement element= getElement(driver, locator, timeout, fieldName);
			 highlighterMethod(driver,element);
			 Select select = new Select(element);
			 
			 if(value.equalsIgnoreCase("SEND NO ADDITIONAL DATA")) {
					select.selectByValue("");
				}
				else {
					select.selectByValue(value);
				}
			 ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Selected '"+value+"' value from dropdown '"+fieldName+"'");
			
		}
		
		catch(Exception e) {
			WrappedReportLogger.error("Unable to select value '"+value+"' from dropdown '"+fieldName+"' with locator '"+locator+"'");
			Assert.fail("Unable to select value '"+value+"' from dropdown '"+fieldName+"' with locator '"+locator+"'");
		}
	}
	
	
	public static void click(WebDriver driver, By locator, int timeOut, String fieldName ) {
		
		try {
			WebElement element = getElement(driver, locator, timeOut, fieldName);
			highlighterMethod(driver,element);
			element.click();
			ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Clicks on '"+fieldName+"' button/link");
		}
		catch(Exception e) {
			//******  ExtentFactory class already has ExtentFactory.getInstance().getExtentTest().log(Status.FAIL,xxxxxxxx) hence I didnot write Status.FAIL over here
			WrappedReportLogger.error("Unable to clicks on '"+fieldName+"' button/link with locator '"+locator+"'");
			Assert.fail("Unable to clicks on '"+fieldName+"' button/link with locator '"+locator+"'");
		}
		
	}
	
	public static String getElementText(WebDriver driver, By locator, int timeout, String fieldName) {
		WebElement element=null;
		String text=null;
		try {
			 element= getElement(driver, locator, timeout, fieldName);
			 highlighterMethod(driver,element);
			 text=element.getText();
			 ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Text of field '"+fieldName+"' is retrieved '"+text+"'");
		}
		catch(Exception e) {
			WrappedReportLogger.error("Unable to retrieved text of field '"+fieldName+"' with locator '"+locator+"'");
			Assert.fail("Unable to retrieved text of field '"+fieldName+"' with locator '"+locator+"'");
			
		}
		return text;
	}
	
	
	
	
	
	public static boolean isDisplayed(WebDriver driver, By locator, String value, int timeout, String fieldName) {
		Boolean isDisplayed= false;
		try {
			 WebElement element= getElement(driver, locator, timeout, fieldName);
			 highlighterMethod(driver,element);
			 isDisplayed= element.isDisplayed();
			 
			 ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "field '"+fieldName+"' is displayed: '"+isDisplayed+"'");
			
		}
		catch(Exception e) {
			WrappedReportLogger.error("Unable to get display status of field '"+fieldName+"' with locator '"+locator+"'");
			Assert.fail("Unable to get display status of field '"+fieldName+"' with locator '"+locator+"'");
		}
		return isDisplayed;
	}
	
	
	public static String getSelectedValue(WebDriver driver, By locator, String value, int timeout, String fieldName) {
		String selectedOption=null;
		try
		{
			WebElement element= getElement(driver, locator, timeout, fieldName);
			 highlighterMethod(driver,element);
			 Select select = new Select(element);
			 selectedOption=select.getFirstSelectedOption().getText();
			 
			 ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "dropdown named '"+fieldName+"'s selected value '"+selectedOption+"' has been retreived");
			 
		}
		catch(Exception e) {
			WrappedReportLogger.error("Unable to get selected value of  '"+fieldName+"' dropdown with locator '"+locator+"'");
			Assert.fail("Unable to get selected value of  '"+fieldName+"' dropdown with locator '"+locator+"'");
		}
		return selectedOption;
	}
	
	
	public static String extentReportComment(String message) {
	    String color = "RoyalBlue";
	    String modifiedString = "<span style='border: 1px solid white;background-color:" + color + ";'>" + message + "</span>"; 
	    return modifiedString;
	}

	

}
