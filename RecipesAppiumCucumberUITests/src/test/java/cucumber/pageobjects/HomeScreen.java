package cucumber.pageobjects;

import cucumber.utils.Log;
import cucumber.utils.MobileUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class HomeScreen extends MobileUtils {

    public HomeScreen(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.asos.recipes.debug:id/searchInput")
    private MobileElement searchTextbox;

    @AndroidFindBy(id = "com.asos.recipes.debug:id/titleView")
    private List<MobileElement> searchResultTitle;

    public boolean isSearchTextboxDisplayed() {
        Log.info(this, "Checking Landing page has loaded");
        waitUntilElementIsVisible(searchTextbox, 7);
        return searchTextbox.isDisplayed();
    }

    public void SetSearchText(String searchTerm) {
        Log.info(this, "Setting search term: " + searchTerm);
        searchTextbox.sendKeys(searchTerm);
    }

    public String getSearchResultTitle() {
        Log.info(this, "Getting search Result title");
        return searchResultTitle.get(0).getText();
    }

    public void TapSearchResultTitle() {
        Log.info(this, "Tapping search Result title");
        tap(searchResultTitle.get(0));
    }

}
