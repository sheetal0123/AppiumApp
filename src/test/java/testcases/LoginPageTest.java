package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.*;

public class LoginPageTest {

	@AfterMethod
	public void tearDown(){
		BasePage.resetApp();
	}
		
	@Test
	public void verifyLoginPage() throws InterruptedException{
		System.out.println("*** Login Test 1");
		LoginPage.login("one@grindr.com", "111111");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		System.out.println("Total user:"+ CascadePage.getUserCount());
		
	}
	
	@Test
	public void verifyEmailFiled() throws InterruptedException{
		System.out.println("*** Login Test 2");
		Assert.assertTrue(LoginPage.isEmailFieldDisplayed());
	}
	
	@Test
	public void verifyPasswordField() throws InterruptedException{
		System.out.println("*** Login Test 3");
		Assert.assertTrue(LoginPage.isPasswordFieldDisplayed());
	}
	
	
}
