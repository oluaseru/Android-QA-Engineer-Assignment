package cucumber.runners;

import cucumber.BaseTest;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.utils.AppiumServer;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.*;


@CucumberOptions(
        tags = {"@regression"},
        features = "src/test/java/cucumber/features",
        glue = {"cucumber/steps"},
        snippets = SnippetType.CAMELCASE,
        monochrome = true,
        strict = true,
        plugin = {"pretty", "json:target/report.json", "de.monochromata.cucumber.report.PrettyReports:target/pretty-cucumber"}
)

public class RunCucumberTest extends BaseTest {

    AppiumServer appiumServer = new AppiumServer();
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeSuite(alwaysRun = true)
    public void setup() throws Throwable, Exception {
        //Provide Log4j configuration settings
        BasicConfigurator.configure();
        //appiumServer.startAppiumServerCmd(new PropertyReader().readProperty("APPIUM_SERVER_PORT1"));
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "Cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
        //appiumServer.stopAppiumServerCmd(new PropertyReader().readProperty("APPIUM_SERVER_PORT1"));
    }
}
