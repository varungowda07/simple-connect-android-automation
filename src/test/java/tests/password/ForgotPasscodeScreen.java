package tests.password;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import tests.base.BaseTest;
import tests.login.SetScooterPasscodeScreen;
import tests.settings.mygarage.MyGarage;
import tests.utils.UniversalMethods;

public class ForgotPasscodeScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void forgotPasscodeScreen() {
        PasswordFlow passwordFlow = new PasswordFlow();
        VerifyOtpScreen verifyOtpScreen = new VerifyOtpScreen();
        UniversalMethods universalMethods = new UniversalMethods();
        SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();
        MyGarage myGarage = new MyGarage();
        if(myGarage.verifySettingsIconIsDisplayed()) {
            universalMethods.scrollToText("SECURITY");
            universalMethods.scrollDownOnce();
            if(universalMethods.verifyAndClick(
                    By.xpath("//android.widget.TextView[@text=\"Password\"]/following-sibling::android.view.View"),
                    "Password Forward Arrow",
                    true
            )) {

                universalMethods.verifyTextContains("Enter passcode","Enter");
                setScooterPasscodeScreen.verifyChoose4DigitPasscodeText();
                if(setScooterPasscodeScreen.enter4DigitPasscode(setScooterPasscodeScreen.passcode)) {
                    universalMethods.verifyExactText("Invalid passcode. Attempt 1/3");
                    universalMethods.verifyExactText("Forgot passcode?");
                    if(clickForgotPasscode()) {
                        verifyOtpScreen.verifyOtpscreen();
                    }

                }
            }
        }

    }
    public boolean clickForgotPasscode() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Forgot passcode?\")"),
                "Click Forgot Passcode",
                true
        );
    }
}
