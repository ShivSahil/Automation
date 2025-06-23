package Pages;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumUtils;

public class AccountDetailsPage {
	
	WebDriver driver;
	
	
	public AccountDetailsPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By getLocatorForField(String fieldName) {
	    switch (fieldName.trim().toLowerCase().replaceAll("\\s", "")) {
	        case "accounttype":
	            return By.xpath("//td[normalize-space(text())='Account Type:']//following::td");
	        case "balance":
	            return By.xpath("//td[normalize-space(text())='Balance:']//following::td");
	        case "available":
	            return By.xpath("//td[normalize-space(text())='Available:']//following::td");
	        default:
	            throw new IllegalArgumentException("No locator defined for field: " + fieldName);
	    }
	}

	public Map<String, String> getAccountDetailsPage(Set<String> fieldNames) {
	    Map<String, String> accountDetails = new HashMap<>();

	    for (String fieldName : fieldNames) {
	        By locator = getLocatorForField(fieldName);
	        String value = SeleniumUtils.getElementText(driver, locator, 10, fieldName);
	        accountDetails.put(fieldName, value);
	    }

	    return accountDetails;
	}


}
