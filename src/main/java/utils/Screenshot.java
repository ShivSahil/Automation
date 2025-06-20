package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;

import com.aventstack.extentreports.Status;

import report.ExtentFactory;

public class Screenshot {
	
	public static void takeScreenShot(WebDriver driver, Scenario scenario) {
		ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "SCENARIO FAILED: " + scenario.getName());

		String srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		ExtentFactory.getInstance().getExtentTest().addScreenCaptureFromBase64String(srcFile,
				scenario.getName() + " failed");
	
	//  this below code also run, but in above code base64 is used which is better as it will not create a physical file and is more usefull in CI/CD		
	//		if (scenario.isFailed()) {
	//		    ExtentFactory.getInstance().getExtentTest()
	//		        .log(Status.FAIL, "SCENARIO FAILED: " + scenario.getName());
	//
	//		    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	//		    String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(Calendar.getInstance().getTime());
	//
	//		    String screeshotPath= System.getProperty("user.dir")+"/report/Screenshots/"+scenario.getName()+"_"+timestamp+".jpeg";
	//		    
	//		    File destFile = new File(screeshotPath);
	//		    FileUtils.copyFile(srcFile, destFile);
	//		   SCENARIO FAILED: sample scenario 
	//		    ExtentFactory.getInstance().getExtentTest().addScreenCaptureFromPath(screeshotPath);
	//		}
	
	
	}

}
