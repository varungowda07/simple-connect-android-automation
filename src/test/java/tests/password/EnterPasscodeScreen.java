package tests.password;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.login.SetScooterPasscodeScreen;
import tests.utils.UniversalMethods;

public class EnterPasscodeScreen extends BaseTest {
    SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();
    UniversalMethods universalMethods = new UniversalMethods();
    PasswordsScreen passwordsScreen = new PasswordsScreen();
    public void enterPasscodeScreen() {
        universalMethods.verifyTextContains("Enter passcode","Enter");
        setScooterPasscodeScreen.verifyChoose4DigitPasscodeText();
        if(setScooterPasscodeScreen.enter4DigitPasscode(setScooterPasscodeScreen.passcode)) {
             passwordsScreen.passwordScreen();
        }

    }


}
