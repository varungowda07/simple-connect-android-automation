package tests.password;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.login.SetScooterPasscodeScreen;
import tests.utils.UniversalMethods;

public class SetNewPasscodeScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void setNewPasscodeScreen() {
        SetScooterPasscodeScreen setScooterPasscodeScreen  = new SetScooterPasscodeScreen();
        ConfirmPasscodeScreen confirmPasscodeScreen = new ConfirmPasscodeScreen();
        if(setScooterPasscodeScreen.enter4DigitPasscode(setScooterPasscodeScreen.passcode)){
            verifyText();
            if(clickContinue()) {
                confirmPasscodeScreen.confirmPasscodeScreen();

            }
        }

    }
    public void verifyText() {
        universalMethods.verifyExactText("Continue");
        universalMethods.verifyTextContains("Set new passcode","Set new");
        universalMethods.verifyExactText("choose a 4-digit passcode.");
    }
    public boolean clickContinue() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Continue\")"),
                "Click Continue",
                true
        );
    }
}
