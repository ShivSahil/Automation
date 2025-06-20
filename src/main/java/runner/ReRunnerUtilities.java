package runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import ConstantClasses.IAutoConstant;
import configuration.ConfigurationManager;
import logging.WrappedReportLogger;

public class ReRunnerUtilities {
	
	private static String workingDirectory= System.getProperty("user.dir");
	private static Properties prop = ConfigurationManager.init_prop();
	
	// Creating a TestNG on Run Time and executing it
	// Note: make createdAndRunTestNG() synchronized if I ever call this in parallel threads.
	
	
	public static void createdAndRunTestNG(String runnerType) {
		
		int threadCount = 0;
		
		// browser1 and cucumber.filter.tags are coming from mvn verify -Dcucumber.filter.tags="@taghere" -Dbrowser1="chrome" 
		String browser= System.getProperty("browser1");
		String cucumberTag= System.getProperty("cucumber.filter.tags");
		
		List<XmlSuite> suiteList= new ArrayList<>();
		XmlSuite suite= new XmlSuite();
		
		suite.setName("TestNG Suite");
		suite.setParallel(XmlSuite.ParallelMode.TESTS);
		
	
		// in case threadCount is not retrieved from mvn then values for threadCount picked from config.properties
		if(System.getProperty("threadCount") == null) {
			threadCount= Integer.parseInt(prop.getProperty("threadCount"));
		}
		else {
			threadCount= Integer.parseInt(System.getProperty("threadCount"));
		}
		
		
		suite.setDataProviderThreadCount(threadCount);
		
		XmlTest xmlTest1= new XmlTest(suite);
		if(browser != null && cucumberTag != null) {
			xmlTest1.setName("execution done using mvn command");
		}
		// in case browser and cucumberTag are from retrieved from mvn command then values for browser and tag are picked from config.properties
		else {
			 browser= prop.getProperty("browser");
			 cucumberTag= prop.getProperty("cucumberTag");
			xmlTest1.setName("execution NOT done using mvn command");
		}
		
		List<XmlClass> listClass= new ArrayList<>();
		XmlClass class1= new XmlClass();
		
		if(runnerType.equalsIgnoreCase(IAutoConstant.TESTRUNNER)) {
			class1.setName(IAutoConstant.TESTRUNNER);
			WrappedReportLogger.debug("TestRunner class trigerred");
			
		}
		
		else if(runnerType.equalsIgnoreCase(IAutoConstant.RERUNTESTRUNNER)) {
			class1.setName(IAutoConstant.RERUNTESTRUNNER);
			WrappedReportLogger.debug("RerunTestRunner class trigerred");
			
		}
		listClass.add(class1);
		xmlTest1.setClasses(listClass);
		suiteList.add(suite);
		
		TestNG testng = new TestNG();
		testng.setXmlSuites(suiteList);
		// run the testNG
		testng.run();
	}
	
	// if failedscenairos.txt under target folder is not empty then it means there are still failed scenarios
	public static void reRunFailedScenarios() {
		try {
			
			int reRunCount=0;
			
			if(System.getProperty("reRunCount")==null) 
			{
				reRunCount = Integer.parseInt(prop.getProperty("reRunCount"));
			}
			
			else {
				 reRunCount = Integer.parseInt(System.getProperty("reRunCount"));
			}
			
			File file= new File(workingDirectory+ IAutoConstant.FAILEDSCENARIOSPATH);
			
			if(file.exists() && !file.isDirectory()) {
				
				for(int i= reRunCount; i>0 ; i--) {
					
					if(file.length()==0) {
						// if file is empty then make updatedReRunCount as 0
						WrappedReportLogger.debug("Failedscenarios.txt is Empty means all scenarios passed");
						break;
					}
					
					else {
						Thread.sleep(10000); // sometime report creation takes times in-between of reruns
						WrappedReportLogger.info("###### TRIGERRING ROUND: "+ (reRunCount - i + 1 )+" OF FAILED SCENARIOS#####");
						createdAndRunTestNG(IAutoConstant.RERUNTESTRUNNER);
					}	
				}
			}
		}
		catch (InterruptedException e) {
			WrappedReportLogger.fatal("Thread interupted");
		}
		catch (Exception e){
			WrappedReportLogger.fatal("unable to locate Failedscenarios.txt file");
		}
		
	}

}
