package tests.password;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.login.SetScooterPasscodeScreen;
import tests.utils.TokenStore;
import tests.utils.UniversalMethods;

public class VerifyOtpScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    TokenStore tokenStore = new TokenStore();
    SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();
    public void verifyOtpscreen() {
        SetNewPasscodeScreen setNewPasscodeScreen = new SetNewPasscodeScreen();
        verifyClock();
        verifyText();
        if(setScooterPasscodeScreen.enter4DigitPasscode("1234")) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setNewPasscodeScreen.setNewPasscodeScreen();
        }


    }
    public void verifyClock() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Timer\")"),
                "Clock Icon",
                false
        );
    }

    public void verifyText() {
        universalMethods.verifyTextContains("Retry in","Retry in");
        universalMethods.verifyTextContains("Verify the shared OTP","Verify the");
        universalMethods.verifyTextContains(
                "we have shared a 4-digit otp to your registered mobile number +91"+tokenStore.mobileNumber,
                "we have shared a");
    }

}
