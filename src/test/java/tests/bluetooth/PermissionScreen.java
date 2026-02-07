package tests.bluetooth;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class PermissionScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    private final String Pairing_Permissions = "Pairing Permissions";
    private final String Secure_your_scooter = "Secure your scooter and ride worry-free with a personal passcode.";
    String[] permissionText = {"Phone call logs","Contact access required","Manage Calls","Nearby device access","Location access"};
    public void permissionScreen() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"While using the app\")\n"),
                "While Using App",
                true
        );
        for(int i=1;i<=4;i++) {
            universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Allow\")"),
                    "Allow",
                    true
            );
        }
        universalMethods.verifyTextContains(Pairing_Permissions,"Pairing");
        universalMethods.verifyTextContains(Secure_your_scooter,"Secure your scooter");
        for(int i = 0; i <= 4; i++) {
            universalMethods.verifyExactText(permissionText[i]);
            // 1. Click Permission Icon
            String iconLocator = String.format("new UiSelector().description(\"%s\")", permissionText[i]);
            universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator(iconLocator),
                    permissionText[i] + " Icon",
                    false
            );
            // 2. Click Check (ith instance)
            String checkLocator = String.format("new UiSelector().description(\"Check\").instance(%d)", i);
            universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator(checkLocator),
                    permissionText[i] + " granted",
                    false
            );
        }
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Next\")"),
                "Next Button",
                true
        );
        universalMethods.verifyExactText("Notification access");
        universalMethods.verifyExactText("To keep you posted");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Notification access\")"),
                "Notification access icon",
                false
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Get Started\")"),
                "Get Started button",
                true
        );
        universalMethods.scrollToText("DEV Simple Connect");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"DEV Simple Connect\")"),
                "Tap on simple Connect App",
                true
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.settings:id/switchWidget\")"),
                "Tap on Simple toggle Notification On",
                true
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Allow\")"),
                "Allow",
                true
        );
        safeBack();
        safeBack();
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Get Started\")"),
                "Get Started button",
                true
        );
    }

}
