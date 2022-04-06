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
    public static String os;

    public void createInstance() throws MalformedURLException {
        android();
    }

    public void android() throws MalformedURLException {

        String APPIUM_SERVER_PORT1 = new PropertyReader().readProperty("APPIUM_SERVER_PORT1");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, new PropertyReader().readProperty("platformName"));
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, new PropertyReader().readProperty("platformVersion"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, new PropertyReader().readProperty("automationName"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, new PropertyReader().readProperty("deviceName"));
        caps.setCapability("avd", new PropertyReader().readProperty("deviceName"));
        caps.setCapability(MobileCapabilityType.APP, (System.getProperty("user.dir") + new PropertyReader().readProperty("appFilePath")));
        caps.setCapability("retryBackoffTime", new PropertyReader().readProperty("retryBackoffTime"));
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
