package report;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentFactoryBackUp {

	/*
	 * private ExtentFactoryBackUp() { // class with private constructor can not be
	 * initialized }
	 * 
	 * private static ThreadLocal<ExtentTest> extent = new ThreadLocal<>(); private
	 * static ExtentReports report;
	 * 
	 * private static ExtentFactoryBackUp instance = new ExtentFactoryBackUp();
	 * 
	 * public static ExtentFactoryBackUp getInstance() { return instance; } // all
	 * the code in this class above this comment line is written as such that ONLY
	 * ONE instance of extentfactory class exists
	 * 
	 * 
	 * 
	 * public static void init_reporter() {
	 * 
	 * Date date = new Date(); // instead of Date class, Calendar class is better
	 * SimpleDateFormat simpledate = new SimpleDateFormat("dd_MMMM_hh_a_mm_ss");
	 * String name = simpledate.format(date);
	 * 
	 * //String path = System.getProperty("user.dir") +
	 * "\\src\\test\\resources\\emailables\\" + name + "_extentReport.html"; String
	 * path = Paths.get(System.getProperty("user.dir"), "report",
	 * name+"_Report.html").toString();
	 * 
	 * ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	 * 
	 * reporter.config().setReportName("Name of Report");
	 * reporter.config().setDocumentTitle("Title of page");
	 * 
	 * report = new ExtentReports(); report.attachReporter(reporter); // name of the
	 * tester and environment is harcoded to my name. But I can easily replace with
	 * them with data from config.properties
	 * report.setSystemInfo("Automation Tester", "Shiv Sahil Guleri");
	 * report.setSystemInfo("Environment", "QA");
	 * 
	 * } public void setExtentTest(ExtentTest test) { extent.set(test); }
	 * 
	 * public ExtentTest getExtentTest() { return extent.get(); }
	 * 
	 * public static ExtentReports getReport() {
	 * 
	 * if (report == null) { init_reporter(); // ensure it’s initialized }
	 * 
	 * return report; }
	 */
}
