package tests.password;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class PasswordsScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    String[] toggles = {"Passwords","Documents","Documents","Documents","Geofence"};
    private void verifyLong() {
        universalMethods.verifyTextContains(
                "When the app lock is active, the app will use the default phone lock",
                "When the app lock is active");
    }
    private void verifyIconTextAndToggle() {
        for(int i=0;i<toggles.length;i++) {
            universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator("new UiSelector().description('" +toggles[i]+ "')"),
                    toggles[i]+" Icon Displayed",
                    false
            );
            universalMethods.verifyExactText(toggles[i]);
            if(i !=0) {
                universalMethods.verifyAndClick(
                        By.xpath("//android.widget.TextView[@text='" +toggles[i]+ "']/following-sibling::android.view.View[i]"),
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
