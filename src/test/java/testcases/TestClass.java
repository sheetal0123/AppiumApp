package testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestClass {
	ExtentReports extentReporters;
	ExtentTest extentTest;
	final String filePath = "/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/extentreporting/report.html";
	final String screenPath = "/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/extentreporting/screenshots";

	//@BeforeSuite
	public void beforeSuite() {
		extentReporters = new ExtentReports(filePath, true);
		
	}

	//ß@AfterSuite
	protected void afterSuite() {
		extentReporters.close();
	}

	
	//@AfterMethod
	protected void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			extentTest.log(LogStatus.PASS, "Test passed");
		}

		extentReporters.endTest(extentTest);
		extentReporters.flush();
	}

	@Test
	public void one() {
		extentTest = extentReporters.startTest("one", "one - reg");
		extentTest.log(LogStatus.PASS, "Step 1");

		System.out.println("@1");
		Assert.assertTrue(200 > 100);
	}

	@Test(dependsOnMethods = "one")
	public void two() {
		extentTest = extentReporters.startTest("two", "two - reg");
		extentTest.log(LogStatus.PASS, "Step A");

		System.out.println("@2");
		Assert.assertTrue(20 > 10);
	}

}
