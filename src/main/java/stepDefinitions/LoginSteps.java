package stepDefinitions;

import java.util.Map;

import Pages.LoginPage;
import driver.BrowserFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import logging.WrappedReportLogger;

public class LoginSteps {
	
	LoginPage loginPage=new LoginPage(BrowserFactory.getDriver());
	
	@When("user entering credentails on login page")
	public void user_entering_details_on_signing_up_is_easy_page(DataTable dataTable) {
		WrappedReportLogger.trace("user entering credentails on login page....");
	    Map<String, String> credData = dataTable.asMaps().get(0);  	
	    loginPage.setLoginPage(credData);
	    WrappedReportLogger.trace("user entered credentails on login page!!!!!");
	}

}
