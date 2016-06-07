package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.CascadePage;
import pages.LoginPage;
import pages.MyProfilePage;

public class CascadePageTest extends SetupTest {
	@AfterMethod
	public void tearDown(){
		BasePage.resetApp();
	}
		
	@Test
	public void verifyMyProfileClickFromCascade() throws InterruptedException{
		System.out.println("*** Cascade Page Test 1");
		LoginPage.login("testuser2@grindr.com", "222222");
		Assert.assertTrue(CascadePage.isCascadePageLoaded());
		CascadePage.clickMyUser();
		Assert.assertTrue(MyProfilePage.isMyProfilePageLoaded());
	}
	
}
