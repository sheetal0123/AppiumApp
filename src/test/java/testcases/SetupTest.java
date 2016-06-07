package testcases;

import java.net.MalformedURLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pages.BasePage;

/*
 * Run this test class in every testng.xml files to initialize driver once
 */
public class SetupTest {

	// ExtentReports extentReporters;
	// ExtentTest extentTest;
	// final String filePath =
	// "/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/extentreporting/report.html";

	@BeforeSuite
	public void init() throws MalformedURLException {
		System.out.println("*** Before Suite ***");
		new BasePage();

		// extentReporters = new ExtentReports(filePath, true);

	}

	@AfterSuite
	public void end() {
		System.out.println("*** After Suite ***");
		// extentReporters.flush();
		// extentReporters.close();
	}
}
