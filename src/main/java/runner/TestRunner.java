package runner;

import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import configuration.ConfigurationManager;
import driver.BrowserFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "classpath:/features/" }, glue = {
		"stepDefinitions", "hooks" }, monochrome = true, 
				plugin = { "pretty", "rerun:target/failedscenarios.txt" })

public class TestRunner extends AbstractTestNGCucumberTests {
	
	public static int reRunCount;
	static Properties prop = ConfigurationManager.init_prop();

	// I am not using data provider annotation still I am using below method
	@DataProvider(parallel = true)
	@Override
	public Object[][] scenarios() {
	    return super.scenarios();
	}
	
	@BeforeSuite(alwaysRun = true)
    public void setUpClass() {
		// instead of passing the cucumber.filter.tags on @cucumbeOptions(tags =  "@individualTag") I am setting it here.
		//because I need to set it via config.properties
        prop = ConfigurationManager.init_prop();
        String tagFromProp = prop.getProperty("cucumberTag");
        System.setProperty("cucumber.filter.tags", tagFromProp); 
    }
	
	@AfterMethod
	public void tearDown() {
		if(BrowserFactory.getDriver() != null) {
			BrowserFactory.getDriver().quit();
		}
	}

}
