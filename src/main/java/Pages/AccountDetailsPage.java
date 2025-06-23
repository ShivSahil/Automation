package Pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.SeleniumUtils;

public class AccountDetailsPage {
	
	WebDriver driver;
	private By elementAccountType=By.xpath("//td[normalize-space(text())='Account Type:']//following::td");
	private By elementBalance=By.xpath("//td[normalize-space(text())='Balance:']//following::td");
	private By elemenAvailable=By.xpath("//td[normalize-space(text())='Available:']//following::td");
	
	public AccountDetailsPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public Map<String, String> getAccountDetailsPage() {
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Map<String, String> details = new HashMap<>();
		details.put("Account Type",SeleniumUtils.getElementText(driver, elementAccountType, 10, "Account Type"));
		details.put("Balance",SeleniumUtils.getElementText(driver, elementBalance, 10, "Balance"));
		details.put("Available",SeleniumUtils.getElementText(driver, elemenAvailable, 10, "Available"));
		return details;
	}
	
	

}
