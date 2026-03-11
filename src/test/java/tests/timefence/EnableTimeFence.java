package tests.timefence;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class EnableTimeFence extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void enableTimeFence() {
        TimeFenceEnabledBottomSheet timeFenceEnabledBottomSheet = new TimeFenceEnabledBottomSheet();
        if(clickEnable()) {
            if(verifyIcons()) {
                if(clickEnable()) {
                    if(clickEnable()) {
                        timeFenceEnabledBottomSheet.timeFenceEnabledBottomSheet();
                    }
                }

            }
        }
    }
    public void verifyShortText() {
        universalMethods.verifyExactText("Enable");
        universalMethods.verifyTextContains("Are you sure you want to enable Time-fence?","Are you sure you");
    }
    public boolean verifyIcons() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Info\")"),
                "Info displayed",
                false
        );
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Close\")"),
                "Close Clicked",
                true
        );
    }
    public boolean clickEnable() {
        return universalMethods.verifyAndClick(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enable\")"),
        "Enable Clicked ",
        true
        );
    }

}
