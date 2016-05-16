package helper;

import org.openqa.selenium.remote.DesiredCapabilities;

/*
 * author: sheetal
 * 16th May 2016
 * https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
 */
public enum DriverRepo {

	ANDROID_NEXUS_5_API23 {
		public DesiredCapabilities getDesiredCapabilities() {

			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("appium-version", "1.5.0");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "6.0");
			capabilities.setCapability("deviceName", "Nexus 5 API 23");
			capabilities.setCapability("app",
					"/Users/sheetalsingh/Documents/workspacee/AppiumApp/src/test/resources/builds/grindr_android_dev1.apk");
			capabilities.setCapability("appPackage", "com.grindrapp.android");
			capabilities.setCapability("browserName", "");
			return capabilities;
		}

	},
	IOS_IPHONE6_OS_9_2 {
		public DesiredCapabilities getDesiredCapabilities() {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appium-version", "1.5.0");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("platformVersion", "9.2");
			capabilities.setCapability("deviceName", "iPhone 6");
			capabilities.setCapability("app",
					"/Users/sheetalsingh/Documents/workspacee/AppiumApp/src/test/resources/builds/grindr_ios_preprod1.zip");
			capabilities.setCapability("browserName", "");
			return capabilities;
		}
	};

	DesiredCapabilities capabilities;

	DriverRepo(DesiredCapabilities dc) {
		this.capabilities = dc;
	}

	DriverRepo() {
		// default constructor
	}

	public DesiredCapabilities getDesiredCapabilities() {
		return capabilities;
	}

}
