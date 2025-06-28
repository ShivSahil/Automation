package stepDefinitions;

import java.util.Map;

import Pages.AccountDetailsPage;
import customized.WrappedAssert;
import driver.BrowserFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import utils.TestUtils;

public class AccountDetailsSteps {
	AccountDetailsPage accountDetailsPage = new AccountDetailsPage(BrowserFactory.getDriver());

	@Then("user validating detais on account details page")
	public void user_validating_detais_on_account_details_page(DataTable dataTable) {
		Map<String, String> expectedData = dataTable.asMaps().get(0); //expectedData {Account Type=CHECKING, Balance=var_TotalOnAccountOverview, Available=var_TotalOnAccountOverview}

		Map<String, String> actualData = accountDetailsPage.getAccountDetailsPage(expectedData.keySet()); //expectedData.keySet() [Account Type, Balance, Available]

		for (String fieldName : expectedData.keySet()) {
		    String expectedValue = expectedData.get(fieldName);
		    expectedValue = TestUtils.resolveDynamicVariableIfNeeded(expectedValue); // this value will become key for resolveDynamicVariableIfNeeded()
		    String actualValue = actualData.get(fieldName);
		    WrappedAssert.assertEquals(actualValue, expectedValue, "Validating: " + fieldName);
		}

	}
}
