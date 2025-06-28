package Pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.AutoPageCreationUtility;
import utils.SeleniumUtils;

public class CommonPage {
	
	private WebDriver driver;
	private static ThreadLocal<Map<String, String>> threadLocalMap = new ThreadLocal<>();
	
	public CommonPage(WebDriver driver) {
		this.driver=driver;
	}
	
	// My innovation; setting dynamic variables in feature file directly************************************************
	public static Map<String, String> getThreadSafeMap() {
	    if (threadLocalMap.get() == null) {
	        threadLocalMap.set(new java.util.HashMap<>());
	    }
	    return threadLocalMap.get();
	}
	
	// originally in our framework I have  written this step in such a way that it was able to retrieve the value of ANY field  (input textbox field, selected dropdown value , value in front of a label , checkbox)  and these captured values are stored in Map keys for later usage in script
	public void getRequiredFieldValue(String fieldName, String PageName, String variableName) {
		By elementFieldLocator= By.xpath("//*[normalize-space(text())='"+fieldName+"']//following::td");
		String capturedValue=SeleniumUtils.getElementText(driver, elementFieldLocator, 30, fieldName);
		getThreadSafeMap().put(variableName, capturedValue);
			// need to write code to handle addition, subtraction using BigDecimal
	}
	
	
	
	// My innovation; I had created a custom utility that creates the page class automatically for any page of AUT************************************************
	public void pageGeneration() {
		AutoPageCreationUtility.pageGenerationUtility(driver);
	}
	
	
	
	
	public void clickOnButton(String buttonName) {
		SeleniumUtils.click(driver, By.xpath("//input[@value='"+buttonName+"']"), 30, buttonName);
	}
	
	public void clickOnlink(String LinkName) {
		SeleniumUtils.click(driver, By.linkText(LinkName), 30, LinkName);
	}
	
	

}
