package cucumber.utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.TapOptions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;


public class MobileUtils extends AppiumDriverBase {

    protected AndroidDriver driver;


    public MobileUtils(AndroidDriver driver) {
        this.driver = driver;
        Duration duration = Duration.of(4, ChronoUnit.SECONDS);
        PageFactory.initElements(new AppiumFieldDecorator(driver, duration), this);
    }

    public boolean tap(MobileElement element) {
        TouchAction touch = new TouchAction(driver);
        boolean result = false;
        if (element == null)
            return false;
        try {
            if (!element.isDisplayed()) {
                Log.info(this, "tap(): isDisplayed = false");
                //System.out.println("tap(): isDisplayed = false");
                return false;
            }
        } catch (NoSuchElementException e) {
            Log.info(this, "tap(): NoSuchElementException = true");
            //System.out.println("tap(): NoSuchElementException = true");
            return false;
        }

        try {
            touch.tap(tapOptions().withElement(element(element))).perform();
            result = true;
        } catch (StaleElementReferenceException e) {
            Log.info(this, "FAILED with staleElementReferenceException" + e.getMessage());
            //System.out.println(e.getMessage());
        }
        return result;
    }

    public void tapElement(MobileElement element) {
        TouchAction action = new TouchAction(driver);
        action.tap(TapOptions.tapOptions().withElement(element(element))).perform();
    }

    public void waitUntilElementIsVisible(MobileElement element, int sec) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, sec);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            //System.out.println("Element NOT located on the screen");
            Log.info(this, "Element NOT located on the screen");
        }

    }

    public static List<String> getElementsText(List<MobileElement> elements) throws InterruptedException {
        List<String> list = new ArrayList<String>();
        int attempts = 0;
        while (attempts < 10) {
            try {
                for (MobileElement element : elements) {
                    list.add(element.getText());
                }
                break;
            } catch (StaleElementReferenceException e) {
                e.toString();
                Thread.sleep(1000);
                list.clear();
                attempts++;
            }
        }
        return list;
    }
}
