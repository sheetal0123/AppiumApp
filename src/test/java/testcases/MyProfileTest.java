package testcases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.BasePage;
import pages.CascadePage;
import pages.LoginPage;
import pages.MyProfilePage;

public class MyProfileTest extends SetupTest{

	@BeforeClass
	public void before(){
		extent = new ExtentReports(filePath, true);
	}
	
	
	@AfterClass
	public void after(){
		extent.close();
	}
	
//	@AfterMethod
//	public void tearDown() {
//		BasePage.resetApp();
//	}
	
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

	//@Test
	public void verifyDisplayName() throws InterruptedException {
		System.out.println("*** My Profile Test 1");
		LoginPage.login("testuser2@grindr.com", "222222");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		CascadePage.clickMyUser();
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
		Assert.assertTrue(MyProfilePage.isMyNameDisplayed());
	}

	//@Test
	public void verifyDisplayNameText() throws InterruptedException {
		System.out.println("*** My Profile Test 2");
		LoginPage.login("testuser2@grindr.com", "222222");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		CascadePage.clickMyUser();
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
		Assert.assertTrue(MyProfilePage.getDisplayName().contains("MyPie"));
	}

	
	@Test
	public void verifyDisplayNameTextExtent() throws InterruptedException {
		System.out.println("### Extent myprofile 1");
		test = extent.startTest("verifyDisplayNameTextExtent").assignCategory("Regression");
		
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
	
	
	@Test
	public void verifyDisplayNameExtent() throws InterruptedException {
		System.out.println("### Extent myprofile 2");
		test = extent.startTest("verifyDisplayNameExtent").assignCategory("Sanity");
		
		test.log(LogStatus.PASS, "Step 1: Login into application");
		LoginPage.login("testuser2@grindr.com", "222222");
		
		test.log(LogStatus.PASS, "Step 2: Verify Cascade Page");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		
		test.log(LogStatus.PASS, "Step 3: Click My User");
		CascadePage.clickMyUser();
		
		test.log(LogStatus.PASS, "Step 4: Verify Profile Page");
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
		
		test.log(LogStatus.PASS, "Step 5: Verify Name Visibility");
		Assert.assertTrue(MyProfilePage.isMyNameDisplayed());
	}
	
	
}
