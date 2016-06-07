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

	ExtentReports extent;
	ExtentTest test;
	final String filePath = "/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/extentreporting/mainreport.html";
	final String screenShotPath = "/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/extentreporting/";

	@BeforeSuite
	public void init() throws MalformedURLException {
		System.out.println("*** Before Suite ***");
		new BasePage();

		extent = new ExtentReports(filePath, true);

	}

	@AfterSuite
	public void end() {
		System.out.println("*** After Suite ***");
		//extent.close();
	}
}
