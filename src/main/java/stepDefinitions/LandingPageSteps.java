package stepDefinitions;

import java.util.List;
import java.util.Map;

import Pages.LandingPage;
import driver.BrowserFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import logging.WrappedReportLogger;

public class LandingPageSteps {
//	private LandingPage landingPage = new LandingPage(BrowserFactory.getDriver());
//
//	@When("user entering a search query on {string} page")
//	public void user_entering_a_search_query_on_page(String pageName, DataTable dataTable) {
//		WrappedReportLogger.trace("User entering a search query on " + pageName + " page.....");
//
//		List<Map<String, String>> userData = dataTable.asMaps(String.class, String.class);
//		Map<String, String> user = userData.get(0); // since we would be having only one row of data I always use get(0)
//
//		String searchBar = user.get("searchBar");
//		String otherField = user.get("otherField");
//		landingPage.settingDetailsInSearchBar(searchBar, otherField);
//
//		WrappedReportLogger.trace("User entered a search query on " + pageName + " page!!!!!");
//	}

}
