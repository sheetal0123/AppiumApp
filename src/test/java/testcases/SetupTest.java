package testcases;

import java.net.MalformedURLException;
import org.testng.annotations.BeforeSuite;
import pages.BasePage;

/*
 * Run this test class in every testng.xml files to initialize driver once
 */
public class SetupTest {

	@BeforeSuite
	public void init() throws MalformedURLException{
		System.out.println("*** Before Suite ***");
		new BasePage();
	}
	
	//This method is not running at all, hence declared @AfterMethod in each test class separately
	//@AfterMethod
	public void tearDown(){
		System.out.println("*** After Method ***");
		BasePage.resetApp();
	}
	
		
}
