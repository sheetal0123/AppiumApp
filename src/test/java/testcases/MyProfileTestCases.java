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

public class MyProfileTestCases {

	BasePage basePage;

	@BeforeMethod
	public void init() throws MalformedURLException{
		System.out.println("-------- Before Method MyProfile----------");
		basePage = new BasePage();
	}
	
	@AfterMethod
	public void tearDown(){
		System.out.println("-------- After Method MyProfile----------");
		BasePage.quit();
	}
		
	@Test
	public void verifyDisplayName() throws InterruptedException{
		System.out.println("-------- My Profile Test 1----------");
		Assert.assertTrue(MyProfilePage.isMyNameDisplayed());
	}
	
	@Test
	public void verifyDisplayNameText() throws InterruptedException{
		System.out.println("-------- My Profile Test 2----------");
		LoginPage.login("testuser2@grindr.com", "222222");
		System.out.println("Login successfully");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		CascadePage.clickMyUser();
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
		System.out.println(MyProfilePage.getDisplayName());
		Assert.assertTrue(MyProfilePage.getDisplayName().contains("MyPie"));
	}

	
}
