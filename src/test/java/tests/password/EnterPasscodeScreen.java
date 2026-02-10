package tests.password;

import tests.base.BaseTest;
import tests.login.SetScooterPasscodeScreen;

public class EnterPasscodeScreen extends BaseTest {
    SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();
    public void enterPasscodeScreen() {
        setScooterPasscodeScreen.verifySetScooterPasscodeText("Enter");
        setScooterPasscodeScreen.verifyChoose4DigitPasscodeText();
        if(setScooterPasscodeScreen.enter4DigitPasscode(setScooterPasscodeScreen.passcode)) {

        }

    }
}
