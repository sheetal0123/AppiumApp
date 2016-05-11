package testcases;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.CascadePage;
import pages.LoginPage;
import pages.MyProfilePage;

public class CascadePageTestCases {

	BasePage basePage;

	@BeforeMethod
	public void init() throws MalformedURLException{
		System.out.println("-------- Before Method Cascade----------");
		basePage = new BasePage();
	}
	
	@AfterMethod
	public void tearDown(){
		System.out.println("-------- After Method Cascade ----------");
		BasePage.quit();
	}
		
	@Test
	public void verifyMyProfileClickFromCascade() throws InterruptedException{
		System.out.println("-------- Home Page Test 1----------");
		LoginPage.login("testuser2@grindr.com", "222222");
		System.out.println("Login successfully");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		CascadePage.clickMyUser();
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
	}
	
}
