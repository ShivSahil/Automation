package utils;

import java.util.List;
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

	
	// implicitlywait is NOT used here as it is Interference with explicitwait

	// Since i was short on time. I have not wrote: select by visible Text, Select by Index, getCheckBoxStatus, waitForPageLoadElementToDisappear,  setRadioboxStatus, getRadoboxStatus,  isEnabled, isElementAvailable, getAttribute, getTagName, getDropDownOptions, getTextBoxValue, hoverAndClick etc etc
	
	private static Properties prop = ConfigurationManager.init_prop();

	public static void navgateToUrl(WebDriver driver, String url, String title, int timeOut) {
		try {
			// in case of chrome debugger do nothing
			if (!url.equalsIgnoreCase("START CHROME DEBUGGER")) {
				driver.get(url);

				new WebDriverWait(driver, timeOut).until(ExpectedConditions.titleContains(title));
				ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "User able to navigate to URL: " + url);
			}
		} catch (Exception e) {
			WrappedReportLogger.fatal("Unable to navigate to URL:" + url);
			Assert.fail("Unable to navigate to URL:" + url);
		}
	}

	public static WebElement getElement(WebDriver driver, By locator, int timeout, String fieldName, boolean bolElementToBeClickable) {
		try {
			
			if (bolElementToBeClickable) {
				return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
			} else {
				return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
		} catch (Exception e) {
			WrappedReportLogger.error("Unable to find '" + fieldName + "' with locator '" + locator + "'");
			Assert.fail("Unable to find '" + fieldName + "' with locator '" + locator + "'");
		}
		return null;
	}

	public static void highlighterMethod(WebDriver driver, WebElement element) throws InterruptedException {
		try {
			if (prop.getProperty("visualHighlightingEnabled").equalsIgnoreCase("yes")) {
				// this is good way to highlight but below implementation is better than ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style','border:2px solid green;');", element);
				Thread.sleep(300);
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "");
			}
		} catch (Exception e) {
			WrappedReportLogger.warn("Could not highlight element '" + element + "'");
		}
	}

	public static void setTextEntry(WebDriver driver, By locator, String textValue, int timeOut, String fieldName) {

		try {
			WebElement element = getElement(driver, locator, timeOut, fieldName, true);
			highlighterMethod(driver, element);
			
			// My innovation; these @field lets you get data on demand directly on feature file************************************************
			if(textValue.equalsIgnoreCase("@clearOut")) {
				element.clear();
			}
			else if(textValue.equalsIgnoreCase("@currentUser")) {
				element.clear();
				textValue=prop.getProperty("user");
				element.sendKeys(textValue);
			}
			else if(textValue.toLowerCase().contains("@randomnumber")) {
				element.clear();
				int min=TestUtils.extractRangeFromRandomNumber(textValue)[0];
				int max=TestUtils.extractRangeFromRandomNumber(textValue)[1];
				textValue=Integer.toString(TestUtils.getRandomNumberInRange(min, max));
				element.sendKeys(textValue);
			}
			
			else if (textValue.matches(".*@(?i)(today|yesterday|tomorrow)DateIn[A-Za-z0-9]+.*")) {
				element.clear();
				String[] result = TestUtils.extractDateAndFormat(textValue);
				textValue=TestUtils.getFormattedDate(result[0],result[1]);
				element.sendKeys(textValue);
			}
			
			else if(textValue.toLowerCase().contains("@randomfirstname")) {
				element.clear();
				textValue=TestUtils.generateRandomFirstName();
				element.sendKeys(textValue);
			}
			
			else if(textValue.toLowerCase().contains("@randomlastname")) {
				element.clear();
				textValue=TestUtils.generateRandomLastName();
				element.sendKeys(textValue);
			}
			
			else if (textValue.matches("@randomDateFrom.+Till.+In.+")) {
				String [] str=TestUtils.extractStartAndEndDatesAndFormat(textValue);
				textValue=TestUtils.getFormattedRandomDate(str[0], str[1], str[2]);
				element.sendKeys(textValue);
			}
			
			else {
				element.clear();
				element.sendKeys(textValue);
			}
			if (fieldName.contains("Password")) {
				textValue = "********";
			}
			WrappedReportLogger.debug("Filled '" + textValue + "' value in '" + fieldName + "' textBox");
			ExtentFactory.getInstance().getExtentTest().log(Status.PASS,
					"Filled '" + textValue + "' value in '" + fieldName + "' textBox");
		} catch (Exception e) {
			// ****** ExtentFactory class already has ExtentFactory.getInstance().getExtentTest().log(Status.FAIL,xxxxxxxx) hence I didnot write Status.FAIL over here
			WrappedReportLogger.error("Unable to set '" + textValue + "' value in '" + fieldName
					+ "' textBox with locator '" + locator + "'");
			Assert.fail("Unable to set '" + textValue + "' value in '" + fieldName + "' textBox with locator '"
					+ locator + "'");
		}
	}

	public static void selectByValue(WebDriver driver, By locator, String value, int timeout, String fieldName) {
		try {
			WebElement element = getElement(driver, locator, timeout, fieldName, true);
			highlighterMethod(driver, element);
			Select select = new Select(element);

			select.selectByValue(value);
			
			WrappedReportLogger.debug("Selected '" + value + "' value from dropdown '" + fieldName + "'");
			ExtentFactory.getInstance().getExtentTest().log(Status.PASS,
					"Selected '" + value + "' value from dropdown '" + fieldName + "'");
		}

		catch (Exception e) {
			WrappedReportLogger.error("Unable to select value '" + value + "' from dropdown '" + fieldName
					+ "' with locator '" + locator + "'");
			Assert.fail("Unable to select value '" + value + "' from dropdown '" + fieldName + "' with locator '"
					+ locator + "'");
		}
	}

	public static void click(WebDriver driver, By locator, int timeOut, String fieldName) {

		try {
			WebElement element = getElement(driver, locator, timeOut, fieldName, true);
			highlighterMethod(driver, element);
			element.click();
			WrappedReportLogger.debug("Clicks on '" + fieldName + "' button/link");
			ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Clicks on '" + fieldName + "' button/link");
		} catch (Exception e) {
			// ****** ExtentFactory class already has ExtentFactory.getInstance().getExtentTest().log(Status.FAIL,xxxxxxxx) hence I  didnot write Status.FAIL over here
			WrappedReportLogger
					.error("Unable to clicks on '" + fieldName + "' button/link with locator '" + locator + "'");
			Assert.fail("Unable to clicks on '" + fieldName + "' button/link with locator '" + locator + "'");
		}

	}
	
	 // My innovation  that intelligently selects or deselects a checkbox only if its current state differs from the desired one — ensuring no redundant actions are performed.
	//************************************************
	   
	public static void checkBoxElementAction(WebDriver driver, By locator, String fieldName, int timeOut, String inputValue) {
		// locator is checkbox label & not checkbox square input button
		try {
			WebElement element = getElement(driver, locator, timeOut, fieldName, true);
		    highlighterMethod(driver, element);
		    
		 // write custom method according to AUT
		    if(inputValue.equalsIgnoreCase("uncheck") && getAttribute(driver, locator, "class", timeOut, fieldName).toLowerCase().contains("selected")) {
		    	String updatedLocator=locator.toString().split("xpath:")[1].trim();
		    	By finalLocator= By.xpath(updatedLocator+"//parent::div");   //navigating to checkbox square input button
		    	click(driver, finalLocator, timeOut, fieldName);
		    	WrappedReportLogger.debug("Checkbox named '"+fieldName+"' is '"+inputValue+"ed'");
				ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Checkbox named '"+fieldName+"' is '"+inputValue+"ed'");
		    }
		    else if(inputValue.equalsIgnoreCase("check") && getAttribute(driver, locator, "class", timeOut, fieldName).toLowerCase().contains("unselected")) 
		    	{	
		    	String updatedLocator=locator.toString().split("xpath:")[1].trim();
		    	By finalLocator= By.xpath(updatedLocator+"//parent::div");  //navigating to checkbox square input button
		    	click(driver, finalLocator, timeOut, fieldName);
		    	WrappedReportLogger.debug("Checkbox named '"+fieldName+"' is '"+inputValue+"ed'");
				ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "Checkbox named '"+fieldName+"' is '"+inputValue+"ed'");
		 } 
		}
		catch (Exception e) {
			WrappedReportLogger.error("Unable to perform action on '" + fieldName + "' checkbox with locator '" + locator + "'");
	        Assert.fail("Unable to perform action on '" + fieldName + "' checkbox with locator '" + locator + "'");
		}
	}

	public static String getElementText(WebDriver driver, By locator, int timeout, String fieldName) {
		WebElement element = null;
		String text = null;
		try {
			element = getElement(driver, locator, timeout, fieldName, false);
			highlighterMethod(driver, element);
			text = element.getText();
			WrappedReportLogger.debug("Text of field '" + fieldName + "' is retrieved '" + text + "'");
		} catch (Exception e) {
			WrappedReportLogger
					.error("Unable to retrieved text of field '" + fieldName + "' with locator '" + locator + "'");
			Assert.fail("Unable to retrieved text of field '" + fieldName + "' with locator '" + locator + "'");

		}
		return text.trim();
	}

	public static boolean isDisplayed(WebDriver driver, By locator, String value, int timeout, String fieldName) {
		Boolean isDisplayed = false;
		try {
			WebElement element = getElement(driver, locator, timeout, fieldName, false);
			highlighterMethod(driver, element);
			isDisplayed = element.isDisplayed();
			WrappedReportLogger.debug("field '" + fieldName + "' is displayed: '" + isDisplayed + "'");
			ExtentFactory.getInstance().getExtentTest().log(Status.PASS,
					"field '" + fieldName + "' is displayed: '" + isDisplayed + "'");

		} catch (Exception e) {
			WrappedReportLogger
					.error("Unable to get display status of field '" + fieldName + "' with locator '" + locator + "'");
			Assert.fail("Unable to get display status of field '" + fieldName + "' with locator '" + locator + "'");
		}
		return isDisplayed;
	}

	public static String getSelectedValue(WebDriver driver, By locator, String value, int timeout, String fieldName) {
		String selectedOption = null;
		try {
			WebElement element = getElement(driver, locator, timeout, fieldName, true);
			highlighterMethod(driver, element);
			Select select = new Select(element);
			selectedOption = select.getFirstSelectedOption().getText();
			WrappedReportLogger.debug("dropdown named '" + fieldName + "'s selected value '" + selectedOption + "' has been retreived");
		} catch (Exception e) {
			WrappedReportLogger.error(
					"Unable to get selected value of  '" + fieldName + "' dropdown with locator '" + locator + "'");
			Assert.fail("Unable to get selected value of  '" + fieldName + "' dropdown with locator '" + locator + "'");
		}
		return selectedOption;
	}
	
	public static String getAttribute(WebDriver driver, By locator, String attributeName, int timeOut, String fieldName) {
		String attributeValue=null;
			try {
				attributeValue= getElement(driver, locator, timeOut, fieldName, false).getAttribute(attributeName);
			}
	    catch (Exception e) {
			WrappedReportLogger.error(
					"Unable to get attribute '" + attributeName + "' of field '"+fieldName+"' with locator '" + locator + "'");
			Assert.fail("Unable to get attribute '" + attributeName + "' of field '"+fieldName+"' with locator '" + locator + "'");
		}
			return attributeValue;
	}
	
	public static String getTagName(WebDriver driver, By locator, int timeOut, String fieldName) {
		String tagName=null;
			try {
				tagName= getElement(driver, locator, timeOut, fieldName, false).getTagName();
			}
	    catch (Exception e) {
			WrappedReportLogger.error(
					"Unable to get tagName of field '"+fieldName+"' with locator '" + locator + "'");
			Assert.fail("Unable to get tagName of field '"+fieldName+"' with locator '" + locator + "'");
		}
			return tagName;	
	}
	
	
	public static int size(WebDriver driver, By locator, String fieldName) {
		// since size never throws the exception so no try-cactch used
			List<WebElement> locatorSize= driver.findElements(locator);
			WrappedReportLogger.debug("Total number of instance found for '"+fieldName+"' is '"+locatorSize.size()+"'");
			return locatorSize.size();
	}

	// My innovation or rather an idea which lets you add comments to feature file and extent report***********************************************
	public static String extentReportComment(String message, String color) {
		String modifiedString = "<span style='border: 1px solid white;background-color:" + color + ";'>" + message
				+ "</span>";		
		ExtentFactory.getInstance().getExtentTest().log(Status.PASS,"COMMENT:"+modifiedString.toUpperCase());
		return modifiedString;
	}

}
