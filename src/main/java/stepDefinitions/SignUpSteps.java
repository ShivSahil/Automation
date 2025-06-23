package stepDefinitions;

import java.util.Map;

import Pages.SignUpPage;
import driver.BrowserFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import logging.WrappedReportLogger;

public class SignUpSteps {
	private SignUpPage signUpPage = new SignUpPage(BrowserFactory.getDriver());

	@When("user entering details on signing up is easy page")
	public void user_entering_details_on_signing_up_is_easy_page(DataTable dataTable) {
		WrappedReportLogger.trace("user entering details on signing up is easy page....");
	    Map<String, String> signUpData = dataTable.asMaps().get(0);  	// signUpData   {First Name=Shiv Sahil, Last Name=Guleri, Address=E-24 panjab University, City=Chandigarh, State=Chandigarh, Zip Code=160014, Phone=8209060559, SSN=1234, Username=@randomFirstName, Password=1234, Confirm=1234}
	    signUpPage.setSignUpPage(signUpData);
	    WrappedReportLogger.trace("user entered details on signing up is easy page!!!!!");
	}

}


