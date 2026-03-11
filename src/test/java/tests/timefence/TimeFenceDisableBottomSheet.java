package tests.timefence;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class TimeFenceDisableBottomSheet extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void timeFenceDisableBottomSheet() {
        verifyLongText();
        if(!verifyIocns()) {
            driver.navigate().back();
        }
        else {
            driver.navigate().back();

        }
    }
    public boolean verifyIocns() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Shield\")"),
                "TimeFence disable shiled",
                false
        );
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Close\")"),
                "Click Close",
                true
        );
    }

    public void verifyLongText() {
        universalMethods.verifyTextContains(
                "TimeFence Disabled","Disabled"
        );
        universalMethods.verifyTextContains(
                "You won’t get notifications if your vehicle is used outside allowed hours.","You won’t get notifications if your"
        );
    }
}
