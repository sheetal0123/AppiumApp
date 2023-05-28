package demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

/*
 * platformName : Android
 * platformVersion : 8.1, Emulator settings will show platform version (Edit Emulator Device)
 * 
 * appPackage: adb shell dumpsys window windows | grep mFocusedApp
 * appActivity: same as above
 * 
 * deviceName: emulator-5554, adb devices
 * appium-version : 1.22.3, Start Appium server and it will display
 * app: Can be download directly in emulators or better pass from project
 * 
 * 
 * 
 * mvn clean test -DsuiteXmlFile=appium_basics.xml           Eclipse
 * mvn clean test -DsuiteXmlFile="appium_basics.xml"         IntelliJ
 * Report : target/surefire-reports/index.html
 * 
 * 
 * Office laptop: Manually start Appium UI Server 1.22 / Working fine
 */

public class A1_AppiumBasics7TestNG {

	static AndroidDriver<MobileElement> driver; //Working fine in 7.6.0
	//static AndroidDriver driver; // Working in 8.3.3
	
	@BeforeClass
	public void init() throws MalformedURLException {
		System.out.println("---------- Run Started with Appium 1.22 & Java Client 7----------");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
				getDesiredCapabilities());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void end() {
		System.out.println("---------- Run Finished ----------");
	}
	
	public DesiredCapabilities getDesiredCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
		
		capabilities.setCapability("appPackage", "io.appium.android.apis");
		capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554"); 
		//capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4 API 27"); //Both will work

		capabilities.setCapability("appium-version", "1.22.3"); //Start Appium server and it will display
		capabilities.setCapability("appium:noReset", true); //true: will not reset app
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("builds/A_ApiDemos-debug.apk").getFile());
		capabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());

		return capabilities;
	}
	
	//App > Activity > Animation > Back to home page
	@Test
	public void basicFlow() {
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='App']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Activity']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Animation']")).click();
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();
	}

	

}
