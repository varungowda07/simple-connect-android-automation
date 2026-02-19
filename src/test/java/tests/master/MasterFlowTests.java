package tests.master;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.bluetooth.BluetoothFlow;
import tests.home.Home;
import tests.home.HomeScreenFlow;
import tests.login.*;
import tests.password.PasswordFlow;
import tests.profile.myscooter.*;
import tests.profile.viewprofile.ProfileFlow;
import tests.profile.viewprofile.*;
import tests.settings.editemergencycontact.EditEmergenctContactFlow;
import tests.settings.mygarage.MyGarage;
import tests.settings.mygarage.MyGarageFlow;
import tests.settings.tyrepressure.TPMS;
import tests.settings.tyrepressure.TPMSFlow;
import tests.subscription.Subscription;

@Listeners(listeners.ExtentListener.class)
public class MasterFlowTests extends BaseTest {
    SoftAssert softAssert = new SoftAssert();

    OnBoardingFlow onBoardingFlow = new OnBoardingFlow();
    BluetoothFlow bluetoothFlow = new BluetoothFlow();
    ProfileFlow profileFlow = new ProfileFlow();
    MyScooterFlow myScooterFlow = new MyScooterFlow();
    HomeScreenFlow homeScreenFlow = new HomeScreenFlow();
    MyGarageFlow myGarageFlow = new MyGarageFlow();
    TPMSFlow tpmsFlow = new TPMSFlow();
    EditEmergenctContactFlow editEmergenctContactFlow = new EditEmergenctContactFlow();
    Subscription subscription = new Subscription();
    PasswordFlow passwordFlow = new PasswordFlow();
    LogoutTests logout = new LogoutTests();
    @Test(priority = 1)
    public void flow1_Login() {
        try {
        onBoardingFlow.onboardingFlow();
        } finally {
            softAssert.assertAll();
        }
    }
//    @Test(priority = 2)
//    public void flow2_BluetoothFlow() {
//        try {
//            bluetoothFlow.bluetoothFlow();
//            sleep(3000);
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 3)
//    public void flow3_Profile() {
//        try{
//           profileFlow.profileFlow();
//        }
//        finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 4)
//    public void flow4_MyScooter() {
//        try{
//           myScooterFlow.myScooterFlow();
//        }
//        finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 5)
//    public void flow5_HomeScreen() {
//        try {
//           homeScreenFlow.homeScreenFlow();
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 6)
//    public void flow6_YourGarage() {
//        try {
//            myGarageFlow.myGarageFlow();
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//    @Test(priority = 7)
//    public void flow6_TPMS() {
//        try {
//           tpmsFlow.tpmsFlow();
//        } finally {
//            softAssert.assertAll();
//        }
//    }
//@Test(priority = 8)
//public void flow8_EditEmergencyConatct() {
//    try {
//        editEmergenctContactFlow.editEmergencyContact();
//    } finally {
//        softAssert.assertAll();
//    }
//}
//    @Test(priority = 9)
//    public void flow8_Subscription() {
//        try {
//            subscription.subscription();
//        } finally {
//            ensureLogout();
//            sleep(5000);
//            softAssert.assertAll();
//        }
//    }
    @Test(priority = 10)
    public void flow8_PasswordReset() {
        try {
          passwordFlow.passwordFlow();
        } finally {
//            ensureLogout();
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

