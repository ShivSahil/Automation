package Pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumUtils;
import utils.TestUtils;

public class SignUpPage {
	
	WebDriver driver;
	
	
	public SignUpPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By getLocatorForField(String fieldName) {
	    switch (fieldName.toLowerCase().replaceAll("\\s", "")) {
	        case "firstname": return By.xpath("//input[@id='customer.firstName']");
	        case "lastname": return By.xpath("//input[@id='customer.lastName']");
	        case "address": return By.xpath("//input[@id='customer.address.street']");
	        case "city": return By.xpath("//input[@id='customer.address.city']");
	        case "state": return By.xpath("//input[@id='customer.address.state']");
	        case "zipcode": return By.xpath("//input[@id='customer.address.zipCode']");
	        case "phone": return By.xpath("//input[@id='customer.phoneNumber']");
	        case "ssn": return By.xpath("//input[@id='customer.ssn']");
	        case "username": return By.xpath("//input[@id='customer.username']");
	        case "password": return By.xpath("//input[@id='customer.password']");
	        case "confirm": return By.xpath("//input[@id='repeatedPassword']");
	        default:
	            throw new RuntimeException("No locator defined for field: " + fieldName);
	    }
	}
	
	public void setSignUpPage(Map<String, String> signUp) {
	    for (String fieldName : signUp.keySet()) {    // signUp.keySet()   [First Name, Last Name, Address, City, State, Zip Code, Phone, SSN, Username, Password, Confirm]
	        String value = TestUtils.resolveDynamicVariableIfNeeded(signUp.get(fieldName));
	        By locator = getLocatorForField(fieldName);
	        SeleniumUtils.setTextEntry(driver, locator, value, 10, fieldName);
	    }
	}


}
