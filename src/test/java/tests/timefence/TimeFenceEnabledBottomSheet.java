package tests.timefence;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class TimeFenceEnabledBottomSheet extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void timeFenceEnabledBottomSheet() {
        TimeFenceEnabled timeFenceEnabled = new TimeFenceEnabled();
        verifyLongText();
        if(!verifyIocns()) {
            driver.navigate().back();
        }
        else {
            timeFenceEnabled.timeFenceEnabled();

        }
    }
    public boolean verifyIocns() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Shield\")"),
                "TimeFence Enabled shiled",
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
                "TimeFence Enabled","TimeFence"
        );
        universalMethods.verifyTextContains(
                "You’ll get notifications if your vehicle is used outside allowed hours.","You’ll get notifications"
        );
    }


}
