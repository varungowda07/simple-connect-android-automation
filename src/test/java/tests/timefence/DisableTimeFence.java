package tests.timefence;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class DisableTimeFence extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    TimeFenceDisableBottomSheet timeFenceDisableBottomSheet =  new TimeFenceDisableBottomSheet();
    public void disableTimeFence() {
        if(clickEnable()) {
            if(verifyIcons()) {
                if(clickEnable()) {
                    if(clickEnable()) {
                      timeFenceDisableBottomSheet.timeFenceDisableBottomSheet();
                    }
                }

            }
        }
    }
    public void verifyShortText() {
        universalMethods.verifyExactText("Disable");
        universalMethods.verifyTextContains("Are you sure you want to disable Time-fence?","Are you sure you");
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
        return universalMethods.verifyAndClick(AppiumBy.androidUIAutomator("new UiSelector().text(\"Disable\")"),
                "Disable Clicked ",
                true
        );
    }
}
