package cucumber.pageobjects;

import cucumber.utils.MobileUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import java.util.List;

public class RecipeDetailsScreen extends MobileUtils {

    public RecipeDetailsScreen(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBys({
            @AndroidBy(id = "com.asos.recipes.debug:id/ingredientsWrapper"),
            @AndroidBy(id = "com.asos.recipes.debug:id/nameView")
    })
    private List<MobileElement> ingredientsList;

    public List<String> getIngredientsList() throws InterruptedException {
        return getElementsText(ingredientsList);
    }
}
