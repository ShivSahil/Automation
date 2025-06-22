package stepDefinitions;

import io.cucumber.java.en.Given;
import utils.SeleniumUtils;

public class CommonSteps {
	
	
	// i have skipped common steps like entering credentials,clicking on actions button, delete button etc etc
	
	
	// all the step defs will have lower letter except for comment step def
	@Given("COMMENT:{string}")
	public void log_comment_to_extent_report(String comment) {
        SeleniumUtils.extentReportComment(comment, "RoyalBlue");
    }

}
