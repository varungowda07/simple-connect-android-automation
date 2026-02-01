package tests.utils;

import tests.base.BaseTest;

public class TestUtils extends BaseTest {

    public static void executeSignIn() {
        new tests.login.SignInScreenTests().testSignInScreen();
    }

    public static void executeOtp() {
        new tests.login.OtpScreenTests().testOtpScreen();
    }

    public static void executeGetStarted() {
        new tests.login.GetStartedTests().testGetStartedScreen();
    }

    public static void executeBluetooth() {
        new tests.bluetooth.BluetoothTests().bluetoothFlow();
    }
}
