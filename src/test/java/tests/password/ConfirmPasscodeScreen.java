package tests.password;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.base.BaseTest;
import tests.login.SetScooterPasscodeScreen;
import tests.utils.UniversalMethods;

public class ConfirmPasscodeScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void confirmPasscodeScreen() {
        SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();
       PasscodeSetSuccessScreen passcodeSetSuccessScreen = new PasscodeSetSuccessScreen();
        if(setScooterPasscodeScreen.enter4DigitPasscode(setScooterPasscodeScreen.passcode)) {
            verifyText();
            if(clickConfirm()) {
                passcodeSetSuccessScreen.passcodeSuccessScreen2();
                driver.navigate().back();
                driver.navigate().back();

            }
        }

    }

    public void verifyText() {
        universalMethods.verifyExactText("Confirm Passcode");
        universalMethods.verifyExactText("Re-enter your 4-digit passcode.");
        universalMethods.verifyExactText("Confirm");
    }
    public boolean clickConfirm() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Confirm\")"),
                "Click Confirm",
                true
        );
    }
}
