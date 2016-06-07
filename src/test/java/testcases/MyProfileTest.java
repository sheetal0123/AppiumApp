package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.CascadePage;
import pages.LoginPage;
import pages.MyProfilePage;

public class MyProfileTest {

	@AfterMethod
	public void tearDown() {
		BasePage.resetApp();
	}

	@Test
	public void verifyDisplayName() throws InterruptedException {
		System.out.println("*** My Profile Test 1");
		LoginPage.login("testuser2@grindr.com", "222222");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		CascadePage.clickMyUser();
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
		Assert.assertTrue(MyProfilePage.isMyNameDisplayed());
	}

	@Test
	public void verifyDisplayNameText() throws InterruptedException {
		System.out.println("*** My Profile Test 2");
		LoginPage.login("testuser2@grindr.com", "222222");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		CascadePage.clickMyUser();
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
		Assert.assertTrue(MyProfilePage.getDisplayName().contains("MyPie"));
	}

}
