package tests.login;

public class OnBoardingFlow {
    SignInScreenTests signInScreenTests = new SignInScreenTests();
    OtpScreenTests otpScreenTests = new OtpScreenTests();
    GetStartedTests getStartedTests = new GetStartedTests();
    SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();
    SetYourPasscodeScreen setYourPasscodeScreen = new SetYourPasscodeScreen();
    AddEmergencyContact addEmergencyContact = new AddEmergencyContact();
    ConfirmPasscodeScreen confirmPasscodeScreen = new ConfirmPasscodeScreen();
    PasscodeSuccessScreen passcodeSuccessScreen = new PasscodeSuccessScreen();

    public void onboardingFlow() {
        signInScreenTests.testSignInScreen();
        otpScreenTests.testOtpScreen();
        getStartedTests.testGetStartedScreen();
        addEmergencyContact.addEmergencyContact();
        setYourPasscodeScreen.setYourPasscodeScreen();
        setScooterPasscodeScreen.setScooterPasscodeScreen();
        confirmPasscodeScreen.confirmPasscode();
//        passcodeSuccessScreen.passcodeSuccessScreen();

    }
}
