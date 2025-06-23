package stepDefinitions;

import Pages.LandingPage;
import customized.WrappedAssert;
import driver.BrowserFactory;
import io.cucumber.java.en.Then;
import logging.WrappedReportLogger;

public class LandingSteps {

	
	LandingPage landingPage=new LandingPage(BrowserFactory.getDriver());
	
	@Then("user validating userid {string} is present on login page")
	public void user_validating_userid_on_login_page(String userid) {
		WrappedReportLogger.trace("user validating userid {string} is present on login page....");
		String actualTitle=landingPage.getLoginTitle();
		WrappedAssert.assertEquals(actualTitle, "Welcome "+userid, "validating title");
	    WrappedReportLogger.trace("user validated userid {string} is present on login page!!!!!");
	}
}
