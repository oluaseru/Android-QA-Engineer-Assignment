package cucumber.steps;

import cucumber.BaseTest;
import cucumber.api.java.en.Then;
import cucumber.pageobjects.HomeScreen;
import cucumber.pageobjects.RecipeDetailsScreen;
import cucumber.utils.ThreadLocalDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RecipeDetailsSteps extends BaseTest {

    RecipeDetailsScreen recipeDetailsScreen = new RecipeDetailsScreen(ThreadLocalDriver.getTLDriver());
    HomeScreen homeScreen = new HomeScreen(ThreadLocalDriver.getTLDriver());

    @Then("I should see displayed the following ingredients {string}")
    public void i_should_see_displayed_the_following_ingredients(String ingredients) throws InterruptedException {
        homeScreen.TapSearchResultTitle();
        List<String> expectedIngredients = new ArrayList<String>(Arrays.asList(ingredients.split(",")));
        recipeDetailsScreen.getIngredientsList();
        assertThat(expectedIngredients, is(equalTo(recipeDetailsScreen.getIngredientsList())));
    }
}
