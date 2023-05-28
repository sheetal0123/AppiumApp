package demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/*
 * This class is for Appium v1 and v2 + Java-client 7
 * 
 * 
 * mvn clean test -DsuiteXmlFile=appium_code.xml        Eclipse
 * mvn clean test -DsuiteXmlFile="appium_code.xml"      IntelliJ
 * Report : target/surefire-reports/index.html

 * 
 * 
 */
public class A2_StartAppiumServerFromCodeTestNG {

	static AndroidDriver<MobileElement> driver;
	static AppiumDriverLocalService service;

	/*
	 * nodeJSMainPath: Path may change as per installation setup
	 * C:\Users\<Username>\AppData\Local\Program\resources\app\node_modules\appium\
	 * build\lib\main.js
	 * 
	 */
	@BeforeClass
	public void init() throws MalformedURLException {
		String nodeExePath = "C:\\Program Files\\nodejs\\node.exe";
		
		//Appium 1 installation path
		String nodeJSMainPath2 = "C:\\Users\\cmash\\Downloads\\Appium-Desktop\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
		//Appium 2 installation path
		String nodeJSMainPath = "C:\\Users\\cmash\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
		String logFilePath = "C:\\Users\\cmash\\Documents\\Papi\\Repo\\AppiumTest\\src\\test\\resources\\logs\\log.txt";

		//Office
		String nodeJSMainPathOff = "C:\\Users\\2245419\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
		String logFilePathOff = "C:\\Users\\2245419\\Documents\\CodeRepoAppiumApp\\AppiumApp\\src\\test\\resources\\logs\\log.txt";

				
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.withAppiumJS(new File(nodeJSMainPath))
				.usingDriverExecutable(new File(nodeExePath))
				.withIPAddress("127.0.0.1")
				.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub") // IMP
				.usingPort(4723)
				.withLogFile(new File(logFilePath))
		);

		String appiumUrl = service.getUrl().toString();
		service.start();
		System.out.println("Starting Appium Server ....");
		System.out.println(appiumUrl);

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), getDesiredCapabilities());
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void end() {
		System.out.println("---------- Stopping Appium Server ----------");
		service.stop();
		System.out.println("---------- Run Finished ----------");

	}

	public DesiredCapabilities getDesiredCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");

		capabilities.setCapability("appPackage", "io.appium.android.apis");
		capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		//capabilities.setCapability("appium-version", "1.22.3"); // Start Appium server and it will display

		//Office
		capabilities.setCapability("appium:appium-version", "1.22.3"); //Office
		capabilities.setCapability("automationName", "UiAutomator2"); //Only in case Appium 2.0 is installed
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("builds/A_ApiDemos-debug.apk").getFile());
		capabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());

		return capabilities;
	}

	@Test
	public void basicFlowFromCode() {

		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='App']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Activity']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Animation']")).click();
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().back();

	}

}
