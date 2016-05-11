package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BasePage {

	static AppiumDriver<MobileElement> driver;
	public Properties properties;
	static String driverToRun;
	String deviceToRun;

	public BasePage() throws MalformedURLException {
		getProperties(); // load all properties
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
		if (driverToRun.equals("android")) {
			System.out.println("Driver: android");

			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("appium-version", "1.5.0");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "6.0");
			// capabilities.setCapability("deviceName", "Nexus 5 API 23");
			capabilities.setCapability("deviceName", deviceToRun);
			// capabilities.setCapability("app",
			// "/Users/sheetalsingh/Documents/workspacee/AppiumApp/src/test/resources/builds/grindr_android_dev1.apk");
			capabilities.setCapability("app",
					"/Users/sheetalsingh/Documents/workspacee/AppiumApp/src/test/resources/builds/grindr_android_preprod1.apk");
			capabilities.setCapability("appPackage", "com.grindrapp.android");
			capabilities.setCapability("browserName", "");

			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} else if (driverToRun.equals("ios")) {
			System.out.println("Driver: ios");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appium-version", "1.5.0");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("platformVersion", "9.2");
			capabilities.setCapability("deviceName", deviceToRun);
			capabilities.setCapability("app", "/Users/sheetalsingh/Documents/workspacee/AppiumApp/src/test/resources/builds/grindr_ios_preprod1.zip");
			capabilities.setCapability("browserName", "");

			driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		} else {
			System.out.println("Driver: no driver selected");

		}

	}

	public static void quit() {
		driver.quit();
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
