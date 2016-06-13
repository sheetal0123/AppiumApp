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

public class LoginPageTest extends SetupTest {

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
	
	//@Test
	public void verifyEmailFiled() throws InterruptedException, IOException{
		System.out.println("*** Login Test 2");
		Assert.assertTrue(LoginPage.isEmailFieldDisplayed());
		/**
		from catch we can call getscreenshot method
		try{
			LoginPage.isEmailFieldDisplayed();
		}catch(NoSuchElementException e){
			//pass test class name and test name for better reporting
			LoginPage.getScreenshot("LoginPageTest","verifyEmailFiled");			
		}
		*/
	}
	
	//@Test
	public void verifyPasswordField() throws InterruptedException{
		System.out.println("*** Login Test 3");
		Assert.assertTrue(LoginPage.isPasswordFieldDisplayed());
	}
	

	@Test
	public void verifyLoginPageExtent() throws InterruptedException{
		System.out.println("### Extent login 1");
		test = extent.startTest("verifyLoginPageExtent").assignCategory("sanity");
		
		test.log(LogStatus.PASS, "Step 1: Login into application");
		LoginPage.login("one@grindr.com", "111111");
		
		test.log(LogStatus.PASS, "Step 2: verify cascade page");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		
		System.out.println("Total user:"+ CascadePage.getUserCount());
	}
	
	
	@Test
	public void verifyEmailFiledExtent() throws InterruptedException, IOException{
		System.out.println("### Extent login 2");
		test = extent.startTest("verifyEmailFiledExtent").assignCategory("regression");
		
		test.log(LogStatus.PASS, "Step 1: verify email field presence");
		Assert.assertTrue(LoginPage.isEmailFieldDisplayed());
		
	}
	
	
	@Test
	public void verifyPasswordFieldExtent() throws InterruptedException{
		System.out.println("### Extent login 3");
		test = extent.startTest("verifyPasswordFieldExtent").assignCategory("regression");
		
		test.log(LogStatus.PASS, "Step 1: verify password field presence");
		Assert.assertTrue(LoginPage.isPasswordFieldDisplayed());
	}
	
	
}
