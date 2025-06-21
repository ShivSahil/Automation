package report;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentFactory {

	private static ThreadLocal<ExtentTest> extent = new ThreadLocal<>();
	private static ExtentReports report;
	private static ExtentFactory instance = new ExtentFactory();
	private static Map<String, ExtentTest> testMap = new HashMap<>();
	 private static Set<String> failedScenarios = new HashSet<>();
	
	// class with private constructor can not be initialized
	private ExtentFactory() { 
	}

	// ONLY ONE instance of extentfactory class exists
	public static ExtentFactory getInstance() {
		return instance;
	} 
	

	public static void init_reporter() {

		Calendar cal= Calendar.getInstance();
		SimpleDateFormat simpledate = new SimpleDateFormat("dd_MMMM_hh_a_mm_ss");
		String name = simpledate.format(cal.getTime());
		
		// Path makes sure that codes works with both ubuntu and windows
		String path = Paths.get(System.getProperty("user.dir"), "report", name + "_Report.html").toString();

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("Name of Report created by Shiv Sahil Guleri");
		reporter.config().setDocumentTitle("Title of page created by Shiv Sahil Guleri");

		report = new ExtentReports();
		report.attachReporter(reporter); 
		// name of the tester and environment is harcoded to my name. But I can easily replace with them with data from config.properties
		report.setSystemInfo("Automation Tester", "Shiv Sahil Guleri");
		report.setSystemInfo("Contact Details", "8209060559");

	}

	public void setExtentTest(ExtentTest test) {
		extent.set(test);
	}

	public ExtentTest getExtentTest() {
		return extent.get();
	}

	public static ExtentReports getReport() {

		if (report == null) {
			init_reporter(); // ensure it’s initialized

		}

		return report;
	}
	
	// My invention which merges multiple reRun report and scenarios in a single report. this feature is still under work!************************************************
	
	public static ExtentTest createOrGetTest(String scenarioName) {
        if (!testMap.containsKey(scenarioName)) {
        	report=getReport();
            ExtentTest test = report.createTest(scenarioName);
            testMap.put(scenarioName, test);
            
           // testMap:: {sceario 1=com.aventstack.extentreports.ExtentTest@5a0b51c2, sceario 3=com.aventstack.extentreports.ExtentTest@78bc4e05, sceario 2=com.aventstack.extentreports.ExtentTest@26411d18}
           // these ids are unique for each scenario, hence I used this to deduplicated scenarios. one scenario one time in a report
        }
        return testMap.get(scenarioName);
    }
	
    public static void markFailed(String scenarioName) {
        failedScenarios.add(scenarioName);
    }

    public static void markPassed(String scenarioName) {
    	
    	    if (failedScenarios.contains(scenarioName)) {
    	        ExtentTest test = testMap.get(scenarioName);
    	        test.getModel().setStatus(Status.PASS);
    	        test.info("This scenario previously failed but passed on retry.");
    	        failedScenarios.remove(scenarioName);
    	    }  	
    }

}
