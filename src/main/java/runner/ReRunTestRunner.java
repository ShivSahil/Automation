package runner;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import driver.BrowserFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// you can see that instead of feature files location I have given failedscenarios.txt and ofcource no tags given
@CucumberOptions(features = { "@target/failedscenarios.txt" }, glue = {
		"stepDefinitions", "hooks" }, monochrome = true, 
				plugin = { "pretty", "rerun:target/failedscenarios.txt" })

public class ReRunTestRunner extends AbstractTestNGCucumberTests{

	// I am not using data provider annotation still I am using below method
		@DataProvider(parallel = true)
		@Override
		public Object[][] scenarios() {
		    return super.scenarios();
		}
		
		
		
		
		@AfterMethod
		public void tearDown() {
			if(BrowserFactory.getDriver() != null) {
				BrowserFactory.getDriver().quit();
			}
		}
	
}
