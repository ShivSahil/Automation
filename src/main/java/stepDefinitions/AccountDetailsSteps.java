package stepDefinitions;

import java.util.Map;

import Pages.AccountDetailsPage;
import customized.WrappedAssert;
import driver.BrowserFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import utils.TestUtils;

public class AccountDetailsSteps {

	@Then("user validating detais on account details page")
	public void user_validating_detais_on_account_details_page(DataTable dataTable) {
		Map<String, String> expectedData = dataTable.asMaps().get(0); // single row from feature file

		AccountDetailsPage accountDetailsPage = new AccountDetailsPage(BrowserFactory.getDriver());
		Map<String, String> actualData = accountDetailsPage.getAccountDetailsPage(); 

		for (Map.Entry<String, String> entry : expectedData.entrySet()) {
			String key = entry.getKey();
			String expectedValue = entry.getValue();
			String actualValue = actualData.get(key);

			expectedValue = TestUtils.resolveDynamicVariableIfNeeded(expectedValue);

			WrappedAssert.assertEquals(actualValue, expectedValue, "validating "+key);
		}

	}
}
