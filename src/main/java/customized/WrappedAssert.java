package customized;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

import logging.WrappedReportLogger;
import report.ExtentFactory;

public class WrappedAssert {

	

//	public static void assertVerifyTrue(boolean condition, String message) {
//
//		try {
//			Assert.assertTrue(condition, message);
//			WrappedReportLogger.info("[VERIFICATION - PASS] " + message );
//
//		} catch (AssertionError e) { // see warn has been used below
//			WrappedReportLogger.warn("[VERIFICATION - FAIL] " + message);
//		}
//
//	}

	
//  Things leftout:-
	// 1. all verification, above is an example
	// 2. assertNot, assertNull, assertSame, assetNotSame, assertContains, below is an example
	
	public static void assertTrue(boolean condition, String message) {

		try {
			Assert.assertTrue(condition, message);
			WrappedReportLogger.info("[VERIFICATION - PASS] " + message );
			ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "[VERIFICATION - PASS] " + message);

		} catch (AssertionError e) { 
			WrappedReportLogger.error("[VERIFICATION - FAIL] " + message );
			ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "[VERIFICATION - FAIL] " + message);
			Assert.fail("[VERIFICATION - FAIL] " + message);
		}

	}

	public static void assertFalse(boolean condition, String message) {
		try {
			Assert.assertFalse(condition, message);
			WrappedReportLogger.info("[VERIFICATION - PASS] " + message );
			ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "[VERIFICATION - PASS] " + message);
		} catch (AssertionError e) { 
			WrappedReportLogger.error("[VERIFICATION - FAIL] " + message );
			ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "[VERIFICATION - FAIL] " + message);
			Assert.fail("[VERIFICATION - FAIL] " + message);
		}
	}
	
	
	public static void assertEquals(Object actual, Object expected, String message) {
		try {
			
			if(expected == null || expected.equals("")) {
				expected="BLANK";
			}
			
			if(actual == null || actual.equals("")) {
				actual="BLANK";
			}
			
			Assert.assertEquals(actual, expected, message);
			WrappedReportLogger.info("[VERIFICATION - PASS] " + message + ". EXPECTED ["+ expected +"] "+" ACTUAL ["+actual+"]");
			ExtentFactory.getInstance().getExtentTest().log(Status.PASS, "[VERIFICATION - PASS] " + message + ". EXPECTED ["+ expected +"] "+" ACTUAL ["+actual+"]");
		} catch (AssertionError e) { 
			WrappedReportLogger.error("[VERIFICATION - FAIL] " + message + ". EXPECTED ["+ expected +"] "+" ACTUAL ["+actual+"]");
			ExtentFactory.getInstance().getExtentTest().log(Status.FAIL, "[VERIFICATION - FAIL] " + message + ". EXPECTED ["+ expected +"] "+" ACTUAL ["+actual+"]");
			Assert.fail("[VERIFICATION - FAIL] " + message + ". EXPECTED ["+ expected +"] "+" ACTUAL ["+actual+"]");
		}
	}


}
