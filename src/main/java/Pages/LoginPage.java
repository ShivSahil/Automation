package Pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumUtils;
import utils.TestUtils;

public class LoginPage {

	WebDriver driver;

	private By getLocatorForField(String fieldName) {
		switch (fieldName.toLowerCase().replaceAll("\\s", "")) {
		case "username": return By.xpath("//input[@name='username']");
		case "password": return By.xpath("//input[@name='password']");
		default:
			throw new RuntimeException("No locator defined for field: " + fieldName);
		}
	}

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void setLoginPage(Map<String, String> login) {
		for (String fieldName : login.keySet()) {
			String value = login.get(fieldName);
			value = TestUtils.resolveDynamicVariableIfNeeded(value);   //this value will become key for resolveDynamicVariableIfNeeded()
			By locator = getLocatorForField(fieldName);
			SeleniumUtils.setTextEntry(driver, locator, value, 10, fieldName);
		}
	}

}
