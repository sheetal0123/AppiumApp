package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import helper.DriverRepo;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/*
 * Base page is parent class of all page classes
 * contains all static general methods
 * reading property file + driver repo 
 * 
 * @author: sheetal
 * May 2016
 */
public class BasePage {

	static AppiumDriver<MobileElement> driver;
	public Properties properties;
	String cmd_osdevice, osdevice, device;
	static String os;

	public BasePage() throws MalformedURLException {
		getProperties();
		initDriver(os, device);
	}

	//TODO: replace with relative path
	public void getProperties() {
		// reading from properties file
		properties = new Properties();
		try {
			properties.load(new FileInputStream(
					"/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/gr.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// reading from command line
		cmd_osdevice = System.getProperty("osdevice");

		// priority will be given to cmd
		if (cmd_osdevice != null && !cmd_osdevice.trim().isEmpty()) {
			os = cmd_osdevice.split("#")[0];
			device = cmd_osdevice.split("#")[1];
			System.out.println("CMD OS & Device: " + os + " & " + device);
		} else {
			osdevice = properties.getProperty("osdevice");
			os = osdevice.split("#")[0];
			device = osdevice.split("#")[1];
			System.out.println("Prop OS & Device: " + os + " & " + device);
		}
	}

	public void initDriver(String os, String device) throws MalformedURLException {
		if (os.equals("android") && device.equals("Nexus_5_API_23")) {
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
					DriverRepo.ANDROID_NEXUS_5_API23.getDesiredCapabilities());
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else if (os.equals("ios") && device.equals("iPhone_6_9.2")) {
			driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
					DriverRepo.IOS_IPHONE6_OS_9_2.getDesiredCapabilities());
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else {
			System.out.println("Driver: no driver selected");
			System.out.println("Running test with driver: " + os + " & device: " + device);
		}

	}

	/**
	 * Based on android and ios, this method will return By locator of any
	 * element
	 */
	public static By getByElement(By a_element, By i_element) {
		if (os.equals("android")) {
			return a_element;
		} else if (os.equals("ios")) {
			return i_element;
		}
		return null;
	}

	public static void quit() {
		driver.quit();
	}

	public static void resetApp() {
		driver.resetApp();
	}

	/*
	 * This method will take screen shot and place in specified folder
	 */
	public static void getScreenshot(String testclass, String testname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss'.png'").format(new Date());
		String dir;

		// todo: path need to be relative
		if (os.equals("android")) {
			dir = "/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/screenshots/android/";
		} else {
			dir = "/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/screenshots/ios/";
		}

		String path = dir + testclass + "_" + testname + "_" + timestamp;
		System.out.println("path:" + path);

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(path));
	}

	public static void sendKeys(By locator, String keyword) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(keyword);
	}

	public static void click(By locator) {
		driver.findElement(locator).click();
	}

	public static boolean isPageLoaded(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	public static boolean isElementPresent(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	public static List<MobileElement> getElementList(By locator) {
		return driver.findElements(locator);
	}

	public static String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	public static String getAttributeValue(By locator, String attribute) {
		return driver.findElement(locator).getAttribute(attribute);
	}

}
