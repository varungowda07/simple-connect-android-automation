package tests.bluetooth;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class ScanningScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void scanningScreen() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Cancel\")"),
                "Cancel",
                true
        );
        ExtentLogger.info("✅ Bluetooth Flow Completed");
    }


}
