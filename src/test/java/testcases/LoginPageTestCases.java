package testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.*;

public class LoginPageTestCases {
	
	//BasePage basePage;

	@BeforeMethod
	public void init() throws MalformedURLException{
		System.out.println("-------- Before Method Login----------");
		//basePage = new BasePage();
		new BasePage();
	}
	
	@AfterMethod
	public void tearDown(){
		System.out.println("-------- After Method Login----------");
		BasePage.quit();
	}
		
	@Test
	public void verifyLoginPage() throws InterruptedException{
		System.out.println("-------- Login Test 1----------");
		LoginPage.login("one@grindr.com", "111111");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		System.out.println("Login successfully");
		System.out.println("Total user:"+ CascadePage.getUserCount());
		
	}
	
	@Test
	public void verifyEmailFiled() throws InterruptedException{
		System.out.println("-------- Login Test 2----------");
		Assert.assertTrue(LoginPage.isEmailFieldDisplayed());
	}
	
	@Test
	public void verifyPasswordField() throws InterruptedException{
		System.out.println("-------- Login Test 3 -----------");
		Assert.assertTrue(LoginPage.isPasswordFieldDisplayed());
	}
	
	
}
