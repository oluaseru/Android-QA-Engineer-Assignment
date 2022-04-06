package cucumber.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class

AppiumDriverBase {
    public AndroidDriver driver;
    protected WebDriverWait wait;
    public static String deviceCaps;
    public static String os;

    public void createInstance(String myDevice, String platform) throws MalformedURLException {
        switch (platform.toLowerCase()) {
            case "android":
                android(platform, myDevice);
                break;
        }
    }

    public void android(String platform, String myDevice) throws MalformedURLException {
        deviceCaps = myDevice;
        os = platform;

        //your device listed in appium_capabilities.json will be passed to desired capabilities
        new ReadJsonArray().readJson(deviceCaps, "src/test/java/cucumber/config/appium_capabilities.json");

        String APPIUM_SERVER_PORT1 = new PropertyReader().readProperty("APPIUM_SERVER_PORT1");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, ReadJsonArray.platformName);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, ReadJsonArray.platformVersion);
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, ReadJsonArray.automationName);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, ReadJsonArray.deviceName);
        caps.setCapability("avd", ReadJsonArray.deviceName);
        caps.setCapability(MobileCapabilityType.APP, (System.getProperty("user.dir") + ReadJsonArray.appFilePath));
        caps.setCapability("retryBackoffTime", ReadJsonArray.retryBackoffTime);
        caps.setCapability(MobileCapabilityType.NO_RESET, false);
        caps.setCapability(MobileCapabilityType.FULL_RESET, false);
        //set threadLocalDriver
        ThreadLocalDriver.setTLDriver(new AndroidDriver(new URL("http://127.0.0.1:" + APPIUM_SERVER_PORT1 + "/wd/hub"), caps));
        //get threadLocalDriver
        driver = ThreadLocalDriver.getTLDriver();
        wait = new WebDriverWait(ThreadLocalDriver.getTLDriver(), 10);
        // driver = new AppiumDriver(new URL("http://127.0.0.1:" + APPIUM_SERVER_PORT1 + "/wd/hub"), caps);
        System.out.println("driver has been built");
    }

    public void destroyDriver() throws Exception {
        ThreadLocalDriver.getTLDriver().quit();
    }
}
