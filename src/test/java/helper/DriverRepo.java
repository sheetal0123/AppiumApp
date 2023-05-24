package helper;

import java.io.File;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

/*
 * author: sheetal
 * 16th May 2016
 * https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
 */
public enum DriverRepo {

	ANDROID_NEXUS_5_API24 {
		public DesiredCapabilities getDesiredCapabilities() {
			System.out.println("*************123**************");
			
			//DesiredCapabilities capabilities = DesiredCapabilities.android();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability("appPackage", "com.grindrapp.android");
			
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
			capabilities.setCapability("appium-version", "1.22.3"); //Start Appium server and it will display
			capabilities.setCapability("platformVersion", "7.0"); // Emulator settings will show platform
			capabilities.setCapability("deviceName", "Nexus 5 API 24"); //Check in Android Studio
			
			
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("builds/grindr_android_dev1.apk").getFile());
			capabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());

//			capabilities.setCapability("app",
//					"C:\\Users\\cmash\\Documents\\Papi\\Repo\\AppiumTest\\src\\test\\resources\\builds\\ApiDemos-debug.apk");
			
			return capabilities;
		}

	},
	ANDROID_NEXUS_5_API24_OLD {
		public DesiredCapabilities getDesiredCapabilities() {

			//DesiredCapabilities capabilities = DesiredCapabilities.android();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
			//capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
			//capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
			
			
			
			capabilities.setCapability("appium-version", "1.5.0");
			capabilities.setCapability("platformVersion", "6.0");
			capabilities.setCapability("deviceName", "Nexus 5 API 24");
			capabilities.setCapability("app",
					"/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/builds/grindr_android_dev1.apk");
			capabilities.setCapability("appPackage", "com.grindrapp.android");
			return capabilities;
		}

	},
	
	ANDROID_NEXUS_5_API24_CHROME {
		public DesiredCapabilities getDesiredCapabilities() {
			//DesiredCapabilities capabilities = DesiredCapabilities.android();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
			//capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
			return capabilities;
		}

	}
	
	,IOS_IPHONE6_OS_9_2 {
		public DesiredCapabilities getDesiredCapabilities() {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("appium-version", "1.5.0");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("platformVersion", "9.2");
			capabilities.setCapability("deviceName", "iPhone 6");
			capabilities.setCapability("app",
					"/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/builds/grindrx-dev1-branchDevelop.zip");
			capabilities.setCapability("browserName", "");
			return capabilities;
		}
	}
	
	/*
	,
	ANDROID_NEXUS_5_API23 {
		public DesiredCapabilities getDesiredCapabilities() {

			//DesiredCapabilities capabilities = DesiredCapabilities.android();
			DesiredCapabilities capabilities = DesiredCapabilities.android();

			capabilities.setCapability("appium-version", "1.5.0");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "6.0");
			capabilities.setCapability("deviceName", "Nexus 5 API 23");
			capabilities.setCapability("app",
					"/Users/sheetalsingh/Documents/workspace/AppiumApp/src/test/resources/builds/grindr_android_dev1.apk");
			capabilities.setCapability("appPackage", "com.grindrapp.android");
			capabilities.setCapability("browserName", "");
			return capabilities;
		}

	}
	*/
	
	;

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
