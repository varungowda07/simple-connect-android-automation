package tests.password;

import tests.base.BaseTest;
import tests.login.ConfirmPasscodeScreen;
import tests.login.SetScooterPasscodeScreen;

public class ResetPasscodeScreen extends BaseTest {
    String passcode = "4321";

    public void resetPasscodeScreen() {
        SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();

        ConfirmPasscodeScreen confirmPasscodeScreen = new ConfirmPasscodeScreen();
        PasscodeResetSuccessScreen passcodeResetSuccessScreen = new PasscodeResetSuccessScreen();
        setScooterPasscodeScreen.verifySetScooterPasscodeText("Reset Passcode");
        setScooterPasscodeScreen.verifyChoose4DigitPasscodeText();
        setScooterPasscodeScreen.enter4DigitPasscode(passcode);
        if(setScooterPasscodeScreen.verifyAndClickContinueButton()) {
         if(confirmPasscodeScreen.confirmPasscode(passcode)) {
             passcodeResetSuccessScreen.passcodeResetSuccessScreen();

         }
        }
    }
}

