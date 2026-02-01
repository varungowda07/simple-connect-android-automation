package tests.master;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.bluetooth.BluetoothTests;
import tests.home.Home;
import tests.login.*;
import tests.profile.myscooter.EditSecooterName;
import tests.profile.myscooter.MyScooter;
import tests.profile.myscooter.MyScooterScreen;
import tests.profile.myscooter.Scooters;
import tests.profile.viewprofile.EditEmail;
import tests.profile.viewprofile.*;

@Listeners(listeners.ExtentListener.class)
public class MasterFlowTests extends BaseTest {
    SoftAssert softAssert = new SoftAssert();


    SignInScreenTests signIn = new SignInScreenTests();
    OtpScreenTests otp = new OtpScreenTests();
    GetStartedTests getStarted = new GetStartedTests();
    BluetoothTests bluetooth = new BluetoothTests();
    LogoutTests logout = new LogoutTests();
    ProfileTests profile = new ProfileTests();
    ViewProfile viewProfile = new ViewProfile();
    EditProfileTests editProfile = new EditProfileTests();
    EditProfilePhoto editProfilePhoto = new EditProfilePhoto();
    EditFullName editFullName = new EditFullName();
    EditPincode editPincode = new EditPincode();
    EditEmail editEmail = new EditEmail();
    MyScooter myScooter = new MyScooter();
    MyScooterScreen myScooterScreen = new MyScooterScreen();
    EditSecooterName editSecooterName = new EditSecooterName();
    AddEmergencyContact addEmergencyContact = new AddEmergencyContact();
    SetYourPasscodeScreen setYourPasscodeScreen = new SetYourPasscodeScreen();
    SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();
    ConfirmPasscodeScreen confirmPasscodeScreen = new ConfirmPasscodeScreen();
    PasscodeSuccessScreen passcodeSuccessScreen = new PasscodeSuccessScreen();
    Home home = new Home();
    Scooters scooters = new Scooters();


    @Test(priority = 1)
    public void flow1_Login() {
        try {
            signIn.testSignInScreen();
            otp.testOtpScreen();
            getStarted.testGetStartedScreen();
            addEmergencyContact.addEmergencyContact();
            setYourPasscodeScreen.setYourPasscodeScreen();
            setScooterPasscodeScreen.setScooterPasscodeScreen();
            confirmPasscodeScreen.confirmPasscode();
            passcodeSuccessScreen.passcodeSuccessScreen();
            sleep(3000);
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 3)
    public void flow3_Profile() {
        try{
            profile.profile();
            viewProfile.viewProfile();
            editProfile.editProfile();
            editProfilePhoto.editProfilePhoto();
            editFullName.editFullName();
            editPincode.editPincode();
            editEmail.editEmail();
            sleep(3000);
        }
        finally {
            softAssert.assertAll();
        }
    }

    @Test(priority = 2)
    public void flow2_BluetoothFlow() {
        try {
            getStarted.clickGetStartedButton();
            bluetooth.bluetoothFlow();
            sleep(3000);
        } finally {
            softAssert.assertAll();
        }
    }
    @Test(priority = 4)
    public void flow4_MyScooter() {
        try{
           myScooter.myScooter();
           scooters.scooters();
           myScooterScreen.myScooterScreen();
           editSecooterName.editSecooterName();
        }
        finally {
//            ensureLogout();
            softAssert.assertAll();
        }
    }
    @Test(priority = 5)
    public void flow5_HomeScreen() {
        try {
            home.home();
//            sleep(3000);
        } finally {
            ensureLogout();
            sleep(5000);
            softAssert.assertAll();
        }
    }

    private void ensureLogout() {
        try {
            logout.performLogoutFromAnyScreen();
            System.out.println("✅ Logout SUCCESS");
        } catch (Exception e) {
            System.out.println("❌ Logout FAILED → Resetting app");
//            resetToLoginScreen();
        }
    }
    private void sleep(long millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}

