package testcases;

import java.net.MalformedURLException;
import org.testng.annotations.BeforeSuite;
import pages.BasePage;

public class SetupTest {

	@BeforeSuite
	public void init() throws MalformedURLException{
		System.out.println("*** Before Suite ***");
		new BasePage();
	}
	
	//This method is not running at all, hence declared !aftermethod in each test class
	//@AfterMethod
	public void tearDown(){
		System.out.println("*** After Method ***");
		BasePage.resetApp();
	}
	
		
}
