package report;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentFactory {

    private ExtentFactory() {}

    private static ThreadLocal<ExtentTest> extent = new ThreadLocal<>();
    private static ExtentReports report;
    private static Map<String, ExtentTest> testMap = new HashMap<>();
    private static Set<String> failedScenarios = new HashSet<>();

    private static ExtentFactory instance = new ExtentFactory();

    public static ExtentFactory getInstance() {
        return instance;
    }

    public static void init_reporter() {
        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MMMM_hh_a_mm_ss");
        String name = simpleDate.format(date);

        String path = Paths.get(System.getProperty("user.dir"), "report", name + "_Report.html").toString();
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        reporter.config().setReportName("Name of Report");
        reporter.config().setDocumentTitle("Title of page");

        report = new ExtentReports();
        report.attachReporter(reporter);
        report.setSystemInfo("Automation Tester", "Shiv Sahil Guleri");
        report.setSystemInfo("Environment", "QA");
    }

    public static ExtentReports getReport() {
        if (report == null) {
            init_reporter();
        }
        return report;
    }

    public static ExtentTest createOrGetTest(String scenarioName) {
        if (!testMap.containsKey(scenarioName)) {
            ExtentTest test = report.createTest(scenarioName);
            testMap.put(scenarioName, test);
        }
        return testMap.get(scenarioName);
    }

    public void setExtentTest(ExtentTest test) {
        extent.set(test);
    }

    public ExtentTest getExtentTest() {
        return extent.get();
    }

    public static void markFailed(String scenarioName) {
        failedScenarios.add(scenarioName);
    }

    public static void markPassed(String scenarioName) {
        failedScenarios.remove(scenarioName);
        ExtentTest test = testMap.get(scenarioName);
        if (test != null) {
            test.getModel().setStatus(Status.PASS); // Overwrite to pass
            test.info("Test passed on retry and previous failures were ignored.");
        }
    }
}
