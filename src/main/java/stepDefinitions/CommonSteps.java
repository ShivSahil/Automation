package stepDefinitions;

import Pages.CommonPage;
import driver.BrowserFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import logging.WrappedReportLogger;
import utils.SeleniumUtils;

public class CommonSteps {
	
	CommonPage commonPage= new CommonPage(BrowserFactory.getDriver());
	
	// i have skipped common steps like entering credentials,clicking on actions button, delete button etc etc
	// note: all the step defs will have lower letter except for comment step def
	
	
	@Given("COMMENT:{string}")
	public void log_comment_to_extent_report(String comment) {
        SeleniumUtils.extentReportComment(comment, "Yellow");
    }
	
	@When("generate page class")
	public void generate_page_class() {
		commonPage.pageGeneration();
	}
	
	// innovation: which sets dynamic variables in feature file
	@When("user retrieve {string} field on {string} page and save in dynamic variable {string}")
	public void user_retrieve_field_on_page_and_save_in_dynamic_variable(String fieldName, String PageName, String variableName) {
		WrappedReportLogger.trace("user retrieving "+fieldName+" field on "+PageName+" page and save in dynamic variable "+variableName+".....");
	    commonPage.getRequiredFieldValue( fieldName,  PageName,  variableName);
		WrappedReportLogger.trace("user retrieved "+fieldName+" field on "+PageName+" page and save in dynamic variable "+variableName+"!!!!!");
	}
	
	@When("user clicks on {string} button on {string} page")
	public void user_clicks_on_button_on_page(String buttonName, String pageName) {
		WrappedReportLogger.trace("user clicking on "+buttonName+" button on "+pageName+" page.....");
		commonPage.clickOnButton(buttonName);
		WrappedReportLogger.trace("user clicked on "+buttonName+" button on "+pageName+" page!!!!!");
	}
	
	@When("user clicks on {string} link on {string} page")
	public void user_clicks_on_link_on_page(String linkName, String pageName){
		WrappedReportLogger.trace("user clicking on "+linkName+" link on "+pageName+" page.....");
		commonPage.clickOnlink(linkName);
		WrappedReportLogger.trace("user clicked on "+linkName+" link on "+pageName+" page!!!!!");
	}
	

}
