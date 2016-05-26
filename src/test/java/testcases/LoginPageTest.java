package testcases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.*;

public class LoginPageTest extends SetupTest {

	/**
	 * resetApp will close the app and again open the app and 
	 * user will be on login page from where new test case can start
	 */
//	@AfterMethod
//	public void tearDown(){
//		BasePage.resetApp();
//	}
	
	@AfterMethod
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
	 public void passTest() {
		 extentTest = extentReporters.startTest("passTest");
		 extentTest.log(LogStatus.PASS, "Pass");
		 Assert.assertEquals(extentTest.getRunStatus(), LogStatus.PASS);
	 }
	    
	 
	 @Test
	 public void intentionalFailure() throws Exception {
	    extentTest = extentReporters.startTest("intentionalFailure");
	    throw new Exception("intentional failure");        
	 }
	
}
