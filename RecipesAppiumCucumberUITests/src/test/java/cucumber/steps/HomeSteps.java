package cucumber.steps;

import cucumber.BaseTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.pageobjects.HomeScreen;
import cucumber.utils.ThreadLocalDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class HomeSteps extends BaseTest {

    HomeScreen homeScreen = new HomeScreen(ThreadLocalDriver.getTLDriver());

    @Given("I'm on the landing page")
    public void i_m_on_the_landing_page() {
        homeScreen.isSearchTextboxDisplayed();
    }

    @When("I search for {string}")
    public void i_search_for(String searchTerm) {
        homeScreen.SetSearchText(searchTerm);
    }

    @Then("I should se displayed results containing {string}")
    public void i_should_se_displayed_results_containing(String searchTerm) {
        assertThat(homeScreen.getSearchResultTitle(), containsString(searchTerm));
    }
}
