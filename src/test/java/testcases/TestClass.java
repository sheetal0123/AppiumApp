package testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.BasePage;
import pages.CascadePage;
import pages.LoginPage;
import pages.MyProfilePage;

public class TestClass {
	ExtentReports extent;
	ExtentTest test;
	final String filePath = "/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/extentreporting/testreport.html";
	final String screenShotPath = "/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/extentreporting/";
	
	@BeforeSuite
	public void beforeSuite() throws MalformedURLException {
		extent = new ExtentReports(filePath, true);
		System.out.println("### Before Suite ###");
		
		
		new BasePage();
	}

	@AfterSuite
	protected void afterSuite() {
		extent.close();
	}

	
	@AfterMethod
	protected void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			test.log(LogStatus.PASS, "Test passed");
		}

		extent.endTest(test);
		extent.flush();
		
		
		BasePage.resetApp();
	}

	
	
	@Test
	public void verifyLogin() throws InterruptedException {
		System.out.println("### Extent test 1");
		
		test = extent.startTest("verifyLogin");
		
		test.log(LogStatus.PASS, "Step 1");

	    
		LoginPage.login("one@grindr.com", "111111");
		
		String img = test.addScreenCapture(screenShotPath);
	    test.log(LogStatus.INFO, "Image", "Image example: " + img);
		
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		System.out.println("### Total user:"+ CascadePage.getUserCount());

	}

	@Test
	public void verifyEmailFiledPresence() {
		System.out.println("### Extent test 2");
		
		test = extent.startTest("verifyEmailFiledPresence").assignCategory("Sanity");
		test.log(LogStatus.FAIL, "Step A");

		
		Assert.assertTrue(LoginPage.isEmailFieldDisplayed());
	}

	
	@Test
	public void verifyDisplayNameText() throws InterruptedException {
		System.out.println("### Extent test 3");
		test = extent.startTest("verifyDisplayNameText").assignCategory("Regression");
		
		test.log(LogStatus.PASS, "Step 1: Login into application");
		LoginPage.login("testuser2@grindr.com", "222222");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		
		String img1 = test.addScreenCapture(screenShotPath);
	    test.log(LogStatus.INFO, "Image", "Cascade Page: " + img1);
	    
		
		test.log(LogStatus.PASS, "Step 2: Click My User");
		CascadePage.clickMyUser();
		
		test.log(LogStatus.PASS, "Step 3: Profile page has been opened");
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
		
		String img2 = test.addScreenCapture(screenShotPath);
	    test.log(LogStatus.INFO, "Image", "Profile Page: " + img2);
	    
	    test.log(LogStatus.PASS, "Step 4: Profile page contains correct profile name");
		Assert.assertTrue(MyProfilePage.getDisplayName().contains("MyPie"));
	}
	
	
}
