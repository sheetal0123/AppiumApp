package helper;

import org.openqa.selenium.remote.DesiredCapabilities;

public enum CapabilitiesRepo {
	ANDROID_Nexus_5_API_23{
		public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.android();
    		capabilities.setCapability("appium-version", "1.5.0");
    		capabilities.setCapability("platformName", "Android");
    		capabilities.setCapability("platformVersion", "6.0");
    		capabilities.setCapability("deviceName", "Nexus 5 API 23");
    		capabilities.setCapability("app", "/Users/sheetalsingh/Documents/workspacee/AppiumApp/src/test/resources/builds/grindr_android_dev1.apk");
    		capabilities.setCapability("appPackage", "com.grindrapp.android");
    		capabilities.setCapability("browserName", "");
            return capabilities;
        }
		
	}
}
