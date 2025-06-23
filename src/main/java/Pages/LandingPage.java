package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumUtils;

public class LandingPage {

	
	WebDriver driver;

	private By getLocatorForField(String fieldName) {
		switch (fieldName.toLowerCase().replaceAll("\\s", "")) {
		case "logintitle": return By.xpath("//h1[@class='title']");
		default:
			throw new RuntimeException("No locator defined for field: " + fieldName);
		}
	}

	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getLoginTitle() {
		return SeleniumUtils.getElementText(driver, getLocatorForField("loginTitle"), 30, "loginTitle") ;
	}
	

}
