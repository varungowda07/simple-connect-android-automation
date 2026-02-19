package tests.password;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class PasswordsScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    ResetPasscodeScreen resetPasscodeScreen = new ResetPasscodeScreen();
    public void passwordScreen() {
        verifyLong();
        verifyIconTextAndToggle();
        if(verifyResetTextAndClick()) {
          resetPasscodeScreen.resetPasscodeScreen();
        }
    }

    String[] toggles = {"Passwords","Documents","OTA Updates","Incognito","Geofence"};
    private void verifyLong() {
        universalMethods.verifyTextContains(
                "When the app lock is active, the app will use the default phone lock",
                "When the app lock is active");
    }
    private void verifyIconTextAndToggle() {
        for(int i=0;i<toggles.length;i++) {
            String iconLocator = String.format("new UiSelector().description(\"%s\")", toggles[i]);
            universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator(iconLocator),
                    toggles[i]+" Icon Displayed",
                    false
            );
            universalMethods.verifyExactText(toggles[i]);
            String toggleXpath = String.format(
                    "//android.widget.TextView[@text='%s']/following-sibling::android.view.View[%d]",
                    toggles[i],
                    i
            );
            if(i !=0) {
                universalMethods.verifyAndClick(
                        By.xpath(toggleXpath),
                        toggles[i]+" turned on",
                        true
                );
            }
        }
    }
    private boolean verifyResetTextAndClick() {
        universalMethods.verifyExactText("Reset Passcode");
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Reset Passcode\")"),
                "Reset Button Clicked",
                true
        );
    }
}
