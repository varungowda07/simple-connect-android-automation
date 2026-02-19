package tests.login;

import io.appium.java_client.AppiumBy;
import tests.utils.UniversalMethods;

public class OnBoardingFlow {
    SignInScreenTests signInScreenTests = new SignInScreenTests();
    OtpScreenTests otpScreenTests = new OtpScreenTests();
    GetStartedTests getStartedTests = new GetStartedTests();
    SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();
    SetYourPasscodeScreen setYourPasscodeScreen = new SetYourPasscodeScreen();
    AddEmergencyContact addEmergencyContact = new AddEmergencyContact();
    ConfirmPasscodeScreen confirmPasscodeScreen = new ConfirmPasscodeScreen();
    PasscodeSuccessScreen passcodeSuccessScreen = new PasscodeSuccessScreen();
    UniversalMethods universalMethods = new UniversalMethods();

    public void onboardingFlow() {
        signInScreenTests.testSignInScreen();
        otpScreenTests.testOtpScreen();
        getStartedTests.testGetStartedScreen();
        addEmergencyContact.addEmergencyContact();
        setYourPasscodeScreen.setYourPasscodeScreen();
        setScooterPasscodeScreen.setScooterPasscodeScreen();
        confirmPasscodeScreen.confirmPasscode(setScooterPasscodeScreen.passcode);
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Allow\")"),
                "Allow",
                true
        );
//        passcodeSuccessScreen.passcodeSuccessScreen();

    }
}
