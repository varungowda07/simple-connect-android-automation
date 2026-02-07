package tests.bluetooth;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class InstructionScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    private final String All_Notifications_at_Dash = "All Notifications at Dash";
    private final String descriptionText2 = "Get all phone notifications right at your screen.";
    private final String Seamless_auto_connect = "Seamless auto connect";
    private final String descriptionText1 = "The scooter automatically connects with the phone when it's in proximity.";
    private final String Call_Music_Controls = "Call & Music Controls";
    private final String descriptionText3 = "Answer calls and control your playlist without missing a beat";
    public void instructionScreen() {
        ExtentLogger.info("🔵 Bluetooth Flow Started");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Bluetooth\")"),
                "Bluetooth",
                true
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Quick Icon\").instance(0)\n"),
                "Seamless Icon",
                true
        );
        universalMethods.verifyTextContains(Seamless_auto_connect,"Seamless");
        universalMethods.verifyTextContains(descriptionText1,"The scooter automatically connects");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Quick Icon\").instance(1)\n"),
                "All Notifications Icon",
                true
        );
        universalMethods.verifyTextContains(All_Notifications_at_Dash,"All Notifications");
        universalMethods.verifyTextContains(descriptionText2,"Get all phone notifications right");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Quick Icon\").instance(2)\n"),
                "Call Music Icon",
                true
        );
        universalMethods.verifyTextContains(Call_Music_Controls,"Call & Music");
        universalMethods.verifyTextContains(descriptionText3,"Answer calls and control");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Get Started\")\n"),
                "Get Started",
                true
        );

    }

}
