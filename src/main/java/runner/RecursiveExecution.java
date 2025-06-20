package runner;

import java.util.Properties;

import ConstantClasses.IAutoConstant;
import configuration.ConfigurationManager;

public class RecursiveExecution {
	
	private static Properties prop = ConfigurationManager.init_prop();

	// My invention which lets your failed scenarios to run automatically************************************************

	public static void main(String[] arg) {

		// reRunFailedScenario is coming from mvn command
		String reRunFailedScenarioProperty = System.getProperty("reRunFailedScenario");
		
		// in case reRunFailedScenario is not retrieved from mvn then values for reRunFailedScenario picked from config.properties
		if(reRunFailedScenarioProperty == null) {
			reRunFailedScenarioProperty= prop.getProperty("reRunFailedScenario");
	    }

		Boolean reRunFailedScenario = Boolean.parseBoolean(reRunFailedScenarioProperty);
		if (!reRunFailedScenario) {
			ReRunnerUtilities.createdAndRunTestNG(IAutoConstant.TESTRUNNER);
		}
		else {
			ReRunnerUtilities.createdAndRunTestNG(IAutoConstant.RERUNTESTRUNNER);
		}
		
		ReRunnerUtilities.reRunFailedScenarios();
		
	}

}
