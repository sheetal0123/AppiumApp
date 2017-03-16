package testcases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import pages.*;

public class EditProfileTest extends SetupTest {

	@BeforeClass
	public void before(){
		extent = new ExtentReports(filePath, true);
	}
	
	
	@AfterClass
	public void after(){
		extent.close();
	}
	
	
	/**
	 * resetApp will close the app and again open the app and 
	 * user will be on login page from where new test case can start
	 */
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
	public void verifyLoginPage() throws InterruptedException{
		System.out.println("*** Login Test 1");
		LoginPage.login("one@grindr.com", "111111");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		System.out.println("Total user:"+ CascadePage.getUserCount());
		
	}

	
	
	@Test
	public void setFacebookUsername() throws InterruptedException, IOException{
		
		System.out.println("### Extent edit profile 1");
		LoginPage.login("testuser2@grindr.com", "222222");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		CascadePage.clickMyUser();
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
		MyProfilePage.clickEditProfileButton();
		EditProfilePage.setFacebookUserName("facebook123");
		
	}
	
	
	
}
