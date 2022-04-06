package cucumber;

import cucumber.pageobjects.HomeScreen;
import cucumber.utils.AppiumDriverBase;
import cucumber.utils.ThreadLocalDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    AppiumDriverBase appiumDriverBase = new AppiumDriverBase();
    protected HomeScreen homeScreen;


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws Exception {
        appiumDriverBase.createInstance();
        homeScreen = new HomeScreen(ThreadLocalDriver.getTLDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        appiumDriverBase.destroyDriver();
    }

}
