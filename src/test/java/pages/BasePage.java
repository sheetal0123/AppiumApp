package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

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
	static String driverToRun;
	String deviceToRun;

	public BasePage() throws MalformedURLException {
		getProperties(); 
		initDriver(driverToRun, deviceToRun);
	}

	public void getProperties() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(
					"/Users/sheetalsingh/Documents/workspacee/AppiumApp/src/test/resources/grindr.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		driverToRun = properties.getProperty("driver");
		deviceToRun = properties.getProperty("device");
	}

	public void initDriver(String driverToRun, String deviceToRun) throws MalformedURLException {

		if (driverToRun.equals("android") && deviceToRun.equals("Nexus_5_API_23")) {
			System.out.println("Running test with driver: " + driverToRun + " & device: " + deviceToRun);
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
					DriverRepo.ANDROID_NEXUS_5_API23.getDesiredCapabilities());
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else if (driverToRun.equals("ios") && deviceToRun.equals("iPhone 6")) {
			System.out.println("Running test with driver: " + driverToRun + " & device: " + deviceToRun);
			driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
					DriverRepo.IOS_IPHONE6_OS_9_2.getDesiredCapabilities());
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} else {
			System.out.println("Driver: no driver selected");
			System.out.println("Running test with driver: " + driverToRun + " & device: " + deviceToRun);
		}

	}

	public static void quit() {
		driver.quit();
	}

	public static void resetApp() {
		driver.resetApp();
	}

	/**
	 * Based on android and ios, this method will return By locator of any
	 * element
	 */
	public static By getByElement(By a_element, By i_element) {
		if (driverToRun.equals("android")) {
			return a_element;
		} else if (driverToRun.equals("ios")) {
			return i_element;
		}
		return null;
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
