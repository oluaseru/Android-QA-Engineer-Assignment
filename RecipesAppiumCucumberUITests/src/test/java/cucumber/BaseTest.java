package cucumber;

import cucumber.pageobjects.HomeScreen;
import cucumber.utils.AppiumDriverBase;
import cucumber.utils.ThreadLocalDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    AppiumDriverBase appiumDriverBase = new AppiumDriverBase();
    protected HomeScreen homeScreen;


    @BeforeMethod(alwaysRun = true)
    @Parameters({"device", "platform"})
    public void beforeMethod(String device, String platform) throws Exception {
        appiumDriverBase.createInstance(device, platform);
        homeScreen = new HomeScreen(ThreadLocalDriver.getTLDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        appiumDriverBase.destroyDriver();
    }

}
