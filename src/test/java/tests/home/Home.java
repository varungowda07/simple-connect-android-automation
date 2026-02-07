package tests.home;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;


public class Home extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // ====================== FLOW ======================
    public void home() {
        verifyBluetoothImageIsDisplayed();
        verifyVehicleStatus();
        verifyVehicleNameIsDisplayed();
        verifyBluetoothIconIsDisplayed();
        verifyBatteryIconIsDisplayed();
        verifyBatteryPercentageAndSymbol();
        verifyAndClickBatteryLessThanIcon();
        verifyKmTextIsDisplayed();
        verifyOdoTextIsDisplayed();
        verifyModeTextIsDisplayed();
        verifyModeIconIsDisplayed();
        verifyOdoImageIsDisplayed();
        verifySettingsIconIsDisplayed();
        verifyPingMyScooterIconIsDisplayed();
        verifyParkedIconIsDisplayed();
        verifyParkedTextIsDisplayed();
        verifyFindScooterTextIsDisplayed();
    }

    // ====================== STEPS ======================
    private void verifyBluetoothImageIsDisplayed() {
        try {
            WebElement bluetoothImage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().description(\"Profile Picture\")"
                            )
                    )
            );

            softAssert.assertTrue(bluetoothImage.isDisplayed(), "❌ Profile Picture is NOT displayed");
            ExtentLogger.pass("✅ Profile Picture is displayed");

        } catch (TimeoutException e) {
            fail("❌ Profile Picture not visible within wait time", e);
        } catch (Exception e) {
            fail("❌ Error verifying Profile Picture", e);
        }
    }
    private void verifyVehicleStatus() {
        try {
            WebElement statusText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textMatches(\"(?i)offline|online|synced.*\")"
                            )
                    )
            );

            softAssert.assertTrue(statusText.isDisplayed(),
                    "❌ Connection status not displayed");

            String actualText = statusText.getText();
            ExtentLogger.pass("✅ Connection status displayed: " + actualText);

        } catch (Exception e) {
            fail("Connection status verification failed",e);
           }
        }

        private void verifyVehicleNameIsDisplayed() {
        try {
            WebElement vehicleName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath(
                                    "//android.widget.ImageView[@content-desc='Bluetooth']" +
                                            "/../../android.widget.TextView[1]"
                            )
                    )
            );

            softAssert.assertTrue(vehicleName.isDisplayed(), "❌ Vehicle name is NOT displayed");
            ExtentLogger.pass("✅ Vehicle name is displayed: " + vehicleName.getText());

        } catch (TimeoutException e) {
            fail("❌ Vehicle name not visible within wait time", e);
        } catch (Exception e) {
            fail("❌ Error verifying vehicle name", e);
        }
    }

    private void verifyBluetoothIconIsDisplayed() {
        try {
            WebElement bluetoothIcon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"Bluetooth\")")
                    )
            );

            softAssert.assertTrue(bluetoothIcon.isDisplayed(), "❌ Bluetooth icon is NOT displayed");
            ExtentLogger.pass("✅ Bluetooth icon is displayed");

        } catch (TimeoutException e) {
            fail("❌ Bluetooth icon not visible within wait time", e);
        } catch (Exception e) {
            fail("❌ Error verifying Bluetooth icon", e);
        }
    }

    private void verifyBatteryIconIsDisplayed() {
        try {
            WebElement batteryIcon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"Battery\")")
                    )
            );

            softAssert.assertTrue(batteryIcon.isDisplayed(), "❌ Battery icon is NOT displayed");
            ExtentLogger.pass("✅ Battery icon is displayed");

        } catch (TimeoutException e) {
            fail("❌ Battery icon not visible within wait time", e);
        } catch (Exception e) {
            fail("❌ Error verifying Battery icon", e);
        }
    }

    private void verifyBatteryPercentageAndSymbol() {
        try {
            WebElement batteryText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath(
                                    "//android.view.View[@content-desc='Battery']" +
                                            "/following-sibling::android.widget.TextView"
                            )
                    )
            );

            String batteryValue = batteryText.getText().trim();

            softAssert.assertTrue(batteryText.isDisplayed(), "❌ Battery percentage is NOT displayed");
            softAssert.assertTrue(batteryValue.contains("%"), "❌ Battery percentage does NOT contain % symbol");

            String percentageOnly = batteryValue.replace("%", "").trim();
            softAssert.assertTrue(percentageOnly.matches("\\d{1,3}"), "❌ Battery percentage is not numeric: " + batteryValue);

            int percent = Integer.parseInt(percentageOnly);
            softAssert.assertTrue(percent >= 0 && percent <= 100, "❌ Battery percentage out of range: " + percent);

            ExtentLogger.pass("✅ Battery percentage verified: " + batteryValue);

        } catch (Exception e) {
            fail("❌ Battery percentage verification failed", e);
        }
    }

    private void verifyAndClickBatteryLessThanIcon() {
        try {
            WebElement lessThanIcon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath(
                                    "//android.view.View[@content-desc='Battery']/..//android.view.View[2]"
                            )
                    )
            );

            softAssert.assertTrue(lessThanIcon.isDisplayed(), "❌ Less-than icon is NOT displayed");

            ExtentLogger.pass("✅ Less-than icon displayed");

        } catch (TimeoutException e) {
            fail("❌ Less-than icon not visible within wait time", e);
        } catch (Exception e) {
            fail("❌ Error verifying/clicking less-than icon", e);
        }
    }
    private void verifyKmTextIsDisplayed() {
        try {
            WebElement distanceText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"km\")"

                            )
                    )
            );

            softAssert.assertTrue(distanceText.isDisplayed(),
                    "❌ Distance text not displayed");

            ExtentLogger.pass("✅ Distance displayed: " + distanceText.getText());

        } catch (Exception e) {
            fail("❌ Distance verification failed: ",e);
        }
    }
    private void verifyOdoTextIsDisplayed() {
        try {
            WebElement odoText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"ODO\")"
                            )
                    )
            );

            softAssert.assertTrue(odoText.isDisplayed(),
                    "❌ ODO text not displayed");

            ExtentLogger.pass("✅ ODO displayed: " + odoText.getText());

        } catch (Exception e) {
            fail("❌ ODO verification failed: " ,e);
        }
    }
    private void verifyModeTextIsDisplayed() {
        try {
            WebElement modeText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[contains(@text,'ODO -') and contains(@text,'km')]/following-sibling::android.view.View[1]/android.widget.TextView")
                    )
            );

            softAssert.assertTrue(modeText.isDisplayed(), "❌ Mode text is NOT displayed");
            ExtentLogger.pass("✅ Mode text displayed: " + modeText.getText());

        } catch (Exception e) {
            fail("❌ Mode text verification failed", e);
        }
    }

    private void verifyModeIconIsDisplayed() {
        try {
            WebElement modeIcon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[contains(@text,'ODO -') and contains(@text,'km')]/following-sibling::android.view.View[1]/android.view.View")
                    )
            );

            softAssert.assertTrue(modeIcon.isDisplayed(), "❌ Mode icon is NOT displayed");
            ExtentLogger.pass("✅ Mode icon is displayed");

        } catch (Exception e) {
            fail("❌ Mode icon verification failed", e);
        }
    }

    private void verifyOdoImageIsDisplayed() {
        try {
            WebElement odoImage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"Scooter\")")
                    )
            );

            softAssert.assertTrue(odoImage.isDisplayed(), "❌ Vehicle image is NOT displayed");
            ExtentLogger.pass("✅ Vehicle image is displayed");

        } catch (Exception e) {
            fail("❌ Vehicle image verification failed", e);
        }
    }

    private void verifySettingsIconIsDisplayed() {
        try {
            WebElement settingsIcon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"Settings\")")
                    )
            );

            softAssert.assertTrue(settingsIcon.isDisplayed(), "❌ Settings icon is NOT displayed");
            ExtentLogger.pass("✅ Settings icon is displayed");

        } catch (Exception e) {
            fail("❌ Settings icon verification failed", e);
        }
    }

    private void verifyPingMyScooterIconIsDisplayed() {
        try {
            WebElement pingIcon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"Ping My Scooter\")")
                    )
            );

            softAssert.assertTrue(pingIcon.isDisplayed(), "❌ 'Ping My Scooter' icon is NOT displayed");
            ExtentLogger.pass("✅ 'Ping My Scooter' icon is displayed");


        } catch (Exception e) {
            fail("❌ 'Ping My Scooter' icon verification failed", e);
        }
    }

    private void verifyParkedIconIsDisplayed() {
        try {
            WebElement parkedIcon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                           AppiumBy.androidUIAutomator("new UiSelector().description(\"Scooter State\")")
                    )
            );

            softAssert.assertTrue(parkedIcon.isDisplayed(), "❌ 'Parked' icon is NOT displayed");
            ExtentLogger.pass("✅ 'Parked' icon is displayed");

        } catch (Exception e) {
            fail("❌ 'Parked' icon verification failed", e);
        }
    }

    private void verifyParkedTextIsDisplayed() {
        try {
            WebElement parkedText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().text(\"Parked\")")
                    )
            );

            softAssert.assertTrue(parkedText.isDisplayed(), "❌ 'Parked' text is NOT displayed");
            softAssert.assertEquals(parkedText.getText(), "Parked", "❌ Text mismatch for parked status");

            ExtentLogger.pass("✅ 'Parked' text is displayed: " + parkedText.getText());

        } catch (Exception e) {
            fail("❌ 'Parked' text verification failed", e);
        }
    }

    private void verifyFindScooterTextIsDisplayed() {
        try {
            WebElement findScooterText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().text(\"Find scooter\")")
                    )
            );

            softAssert.assertTrue(findScooterText.isDisplayed(), "❌ 'Find scooter' text is NOT displayed");
            softAssert.assertEquals(findScooterText.getText(), "Find scooter", "❌ Text mismatch for 'Find scooter'");

            ExtentLogger.pass("✅ 'Find scooter' text is displayed: " + findScooterText.getText());

        } catch (Exception e) {
            fail("❌ 'Find scooter' text verification failed", e);
        }
    }


    // ====================== HELPERS ======================
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}

//package tests.home;
//
//import io.appium.java_client.AppiumBy;
//import listeners.ExtentLogger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.asserts.SoftAssert;
//import utils.ScreenshotUtil;
//
//import static tests.base.BaseTest.driver;
//import static tests.base.BaseTest.wait;
//
//public class Home {
//    SoftAssert softAssert = new SoftAssert();
//    public void home() {
//        verifyBluetoothImageIsDisplayed();
//        verifyVehicleStatus();
//        verifyVehicleNameIsDisplayed();
//        verifyBluetoothIconIsDisplayed();
//        verifyBatteryIconIsDisplayed();
//        verifyBatteryPercentageAndSymbol();
//        verifyAndClickBatteryLessThanIcon();
//        verifyKmTextIsDisplayed();
//        verifyOdoTextIsDisplayed();
//        verifyModeTextIsDisplayed();
//        verifyModeIconIsDisplayed();
//    }
//    public void verifyBluetoothImageIsDisplayed() {
//        try {
//            WebElement bluetoothImage = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.xpath(
//                                    "//android.widget.ImageView[@content-desc='Bluetooth']" +
//                                            "/../../android.view.View[1]/android.widget.ImageView"
//                            )
//                    )
//            );
//
//            softAssert.assertTrue(
//                    bluetoothImage.isDisplayed(),
//                    "❌ Bluetooth image is NOT displayed"
//            );
//
//            ExtentLogger.pass("✅ Bluetooth image is displayed");
//
//        } catch (TimeoutException e) {
//            ExtentLogger.fail("❌ Bluetooth image not visible within wait time");
//            Assert.fail("Bluetooth image verification failed", e);
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ Error verifying Bluetooth image: " + e.getMessage());
//            Assert.fail("Bluetooth image verification failed", e);
//        }
//    }
//    public void verifyVehicleStatus() {
//        try {
//            WebElement statusElement = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.xpath(
//                                    "//android.widget.ImageView[@content-desc='Bluetooth']" +
//                                            "/../../android.view.View[2]"
//                            )
//                    )
//            );
//
//            String statusText = statusElement.getText().trim();
//            ExtentLogger.info("Vehicle status text: " + statusText);
//
//            // Allowed conditions
//            boolean isValidStatus =
//                    statusText.equalsIgnoreCase("Online") ||
//                            statusText.equalsIgnoreCase("Offline") ||
//                            statusText.toLowerCase().startsWith("synced");
//
//            softAssert.assertTrue(
//                    isValidStatus,
//                    "❌ Unexpected Bluetooth status text: " + statusText
//            );
//
//            ExtentLogger.pass("Vehicle status verified: " + statusText);
//
//        } catch (TimeoutException e) {
////            ScreenshotUtil.capture(driver,"Vehicle status text not visible within wait time");
//            ExtentLogger.fail("❌ Vehicle status text not visible within wait time");
//            softAssert.fail("Vehicle status verification failed", e);
//
//        } catch (Exception e) {
////            ScreenshotUtil.capture(driver,"Error verifying Vehicle status");
//            ExtentLogger.fail("❌ Error verifying Vehicle status: " + e.getMessage());
//            softAssert.fail("Vehicle status verification failed", e);
//        }
//    }
//    public void verifyVehicleNameIsDisplayed() {
//        try {
//            WebElement vehicleName = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.xpath(
//                                    "//android.widget.ImageView[@content-desc='Bluetooth']" +
//                                            "/../../android.widget.TextView[1]"
//                            )
//                    )
//            );
//
//            softAssert.assertTrue(
//                    vehicleName.isDisplayed(),
//                    "❌ Vehicle name is NOT displayed"
//            );
//
//            ExtentLogger.pass(
//                    "✅ Vehicle name is displayed: " + vehicleName.getText()
//            );
//
//        } catch (TimeoutException e) {
////            ScreenshotUtil.capture(driver,"Vehicle name not visible within wait time");
//            ExtentLogger.fail("❌ Vehicle name not visible within wait time");
//            softAssert.fail("Vehicle name verification failed", e);
//
//        } catch (Exception e) {
////            ScreenshotUtil.capture(driver,"Error verifying vehicle name");
//            ExtentLogger.fail("❌ Error verifying vehicle name: " + e.getMessage());
//            softAssert.fail("Vehicle name verification failed", e);
//        }
//    }
//    public void verifyBluetoothIconIsDisplayed() {
//        try {
//            WebElement bluetoothIcon = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.androidUIAutomator(
//                                    "new UiSelector().description(\"Bluetooth\")"
//                            )
//                    )
//            );
//
//            softAssert.assertTrue(
//                    bluetoothIcon.isDisplayed(),
//                    "❌ Bluetooth icon is NOT displayed"
//            );
//
//            ExtentLogger.pass("✅ Bluetooth icon is displayed");
//
//        } catch (TimeoutException e) {
////            ScreenshotUtil.capture(driver,"Bluetooth icon not visible within wait time");
//            ExtentLogger.fail("❌ Bluetooth icon not visible within wait time");
//            softAssert.fail("Bluetooth icon verification failed", e);
//
//        } catch (Exception e) {
////            ScreenshotUtil.capture(driver,"Error verifying Bluetooth icon");
//            ExtentLogger.fail("❌ Error verifying Bluetooth icon: " + e.getMessage());
//            softAssert.fail("Bluetooth icon verification failed", e);
//        }
//    }
//    public void verifyBatteryIconIsDisplayed() {
//        try {
//            WebElement batteryIcon = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.androidUIAutomator(
//                                    "new UiSelector().description(\"Battery\")"
//                            )
//                    )
//            );
//
//            softAssert.assertTrue(
//                    batteryIcon.isDisplayed(),
//                    "❌ Battery icon is NOT displayed"
//            );
//
//            ExtentLogger.pass("✅ Battery icon is displayed");
//
//        } catch (TimeoutException e) {
////            ScreenshotUtil.capture(driver,"Battery icon not visible within wait time");
//            ExtentLogger.fail("❌ Battery icon not visible within wait time");
//            softAssert.fail("Battery icon verification failed", e);
//
//        } catch (Exception e) {
////            ScreenshotUtil.capture(driver,"Error verifying Battery icon");
//            ExtentLogger.fail("❌ Error verifying Battery icon: " + e.getMessage());
//            softAssert.fail("Battery icon verification failed", e);
//        }
//    }
//    public void verifyBatteryPercentageAndSymbol() {
//        try {
//            WebElement batteryText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.xpath(
//                                    "//android.view.View[@content-desc='Battery']" +
//                                            "/following-sibling::android.widget.TextView"
//                            )
//                    )
//            );
//
//            String batteryValue = batteryText.getText().trim();
//
//            // 1️⃣ Verify text is displayed
//            softAssert.assertTrue(batteryText.isDisplayed(), "❌ Battery percentage is NOT displayed");
//
//            // 2️⃣ Verify % symbol exists
//            softAssert.assertTrue(
//                    batteryValue.contains("%"),
//                    "❌ Battery percentage does NOT contain % symbol"
//            );
//
//            // 3️⃣ Verify numeric value exists (before %)
//            String percentageOnly = batteryValue.replace("%", "").trim();
//            softAssert.assertTrue(
//                    percentageOnly.matches("\\d{1,3}"),
//                    "❌ Battery percentage is not numeric: " + batteryValue
//            );
//
//            // 4️⃣ Optional: verify range 0–100
//            int percent = Integer.parseInt(percentageOnly);
//            softAssert.assertTrue(
//                    percent >= 0 && percent <= 100,
//                    "❌ Battery percentage out of range: " + percent
//            );
//
//            ExtentLogger.pass("✅ Battery percentage verified: " + batteryValue);
//
//        } catch (Exception e) {
////            ScreenshotUtil.capture(driver,"Battery percentage verification failed");
//            ExtentLogger.fail("❌ Battery percentage verification failed: " + e.getMessage());
//            softAssert.fail("Battery percentage verification failed", e);
//        }
//    }
//    public void verifyAndClickBatteryLessThanIcon() {
//        try {
//            WebElement lessThanIcon = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.xpath(
//                                    "//android.view.View[@content-desc='Battery']/..//android.view.View[2]"
//                            )
//                    )
//            );
//
//            // Verify displayed
//            softAssert.assertTrue(
//                    lessThanIcon.isDisplayed(),
//                    "❌ Less-than icon is NOT displayed"
//            );
//
//            // Click
//            lessThanIcon.click();
//
//            ExtentLogger.pass("✅ Less-than icon displayed and clicked successfully");
//
//        } catch (TimeoutException e) {
////            ScreenshotUtil.capture(driver,"Less-than icon not visible within wait time");
//            ExtentLogger.fail("❌ Less-than icon not visible within wait time");
//            softAssert.fail("Less-than icon verification failed", e);
//
//        } catch (Exception e) {
////            ScreenshotUtil.capture(driver,"Error verifying/clicking less-than icon");
//            ExtentLogger.fail("❌ Error verifying/clicking less-than icon: " + e.getMessage());
//            softAssert.fail("Less-than icon verification failed", e);
//        }
//    }
//    public void verifyKmTextIsDisplayed() {
//        try {
//            WebElement kmText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.androidUIAutomator(
//                                    "new UiSelector().textMatches(\"\\\\d+\\\\s?km\")"
//                            )
//                    )
//            );
//
//            softAssert.assertTrue(
//                    kmText.isDisplayed(),
//                    "❌ KM text is NOT displayed"
//            );
//
//            ExtentLogger.pass("✅ KM text displayed: " + kmText.getText());
//
//        } catch (Exception e) {
////            ScreenshotUtil.capture(driver,"KM text verification failed");
//            ExtentLogger.fail("❌ KM text verification failed: " + e.getMessage());
//            softAssert.fail("KM text verification failed", e);
//        }
//    }
//    public void verifyOdoTextIsDisplayed() {
//        try {
//            WebElement odoText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.androidUIAutomator(
//                                    "new UiSelector().textMatches(\"ODO\\\\s-\\\\s\\\\d+(\\\\.\\\\d+)?\\\\s?km\")"
//                            )
//                    )
//            );
//
//            softAssert.assertTrue(
//                    odoText.isDisplayed(),
//                    "❌ ODO text is NOT displayed"
//            );
//
//            ExtentLogger.pass("✅ ODO text displayed: " + odoText.getText());
//
//        } catch (Exception e) {
////            ScreenshotUtil.capture(driver,"ODO text verification failed");
//            ExtentLogger.fail("❌ ODO text verification failed: " + e.getMessage());
//            softAssert.fail("ODO text verification failed", e);
//        }
//    }
//    public void verifyModeTextIsDisplayed() {
//        try {
//            WebElement modeText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//android.widget.TextView[contains(@text,'ODO -') and contains(@text,'km')]/following-sibling::android.view.View[1]/android.widget.TextView")
//                    )
//            );
//
//            softAssert.assertTrue(modeText.isDisplayed(), "❌ Mode text is NOT displayed");
//            ExtentLogger.pass("✅ Mode text displayed: " + modeText.getText());
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ Mode text verification failed: " + e.getMessage());
//            softAssert.fail("Mode text verification failed", e);
//        }
//    }
//    public void verifyModeIconIsDisplayed() {
//        try {
//            WebElement modeIcon = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//android.widget.TextView[contains(@text,'ODO -') and contains(@text,'km')]/following-sibling::android.view.View[1]/android.view.View")
//                    )
//            );
//
//            softAssert.assertTrue(modeIcon.isDisplayed(), "❌ Mode icon is NOT displayed");
//            ExtentLogger.pass("✅ Mode icon is displayed");
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ Mode icon verification failed: " + e.getMessage());
//            softAssert.fail("Mode icon verification failed", e);
//        }
//    }
//    public void verifyOdoImageIsDisplayed() {
//        try {
//            WebElement odoImage = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("(//android.widget.TextView[contains(@text,'ODO -') and contains(@text,'km')]/..//android.widget.ImageView)[3]")
//                    )
//            );
//
//            softAssert.assertTrue(odoImage.isDisplayed(), "❌ vehicle image is NOT displayed");
//            ExtentLogger.pass("✅ ODO related image is displayed");
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ vehicle image verification failed: " + e.getMessage());
//            softAssert.fail("vehicle image verification failed", e);
//        }
//    }
//    public void verifySettingsIconIsDisplayed() {
//        try {
//            WebElement settingsIcon = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]/android.view.View")
//                    )
//            );
//
//            softAssert.assertTrue(settingsIcon.isDisplayed(), "❌ Settings icon is NOT displayed");
//            ExtentLogger.pass("✅ Settings icon is displayed");
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ Settings icon verification failed: " + e.getMessage());
//            softAssert.fail("Settings icon verification failed", e);
//        }
//    }
//    public void verifyPingMyScooterIconIsDisplayed() {
//        try {
//            WebElement pingIcon = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[3]/android.view.View")
//                    )
//            );
//
//            softAssert.assertTrue(pingIcon.isDisplayed(), "❌ 'Ping My Scooter' icon is NOT displayed");
//            ExtentLogger.pass("✅ 'Ping My Scooter' icon is displayed");
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ 'Ping My Scooter' icon verification failed: " + e.getMessage());
//            softAssert.fail("'Ping My Scooter' icon verification failed", e);
//        }
//    }
//    public void verifyParkedIconIsDisplayed() {
//        try {
//            WebElement parkedIcon = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[4]/android.view.View[1]")
//                    )
//            );
//
//            softAssert.assertTrue(parkedIcon.isDisplayed(), "❌ 'Parked' icon is NOT displayed");
//            ExtentLogger.pass("✅ 'Parked' icon is displayed");
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ 'Parked' icon verification failed: " + e.getMessage());
//            softAssert.fail("'Parked' icon verification failed", e);
//        }
//    }
//    public void verifyParkedTextIsDisplayed() {
//        try {
//            WebElement parkedText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.androidUIAutomator("new UiSelector().text(\"Parked\")")
//                    )
//            );
//
//            // Verify it is displayed
//            Assert.assertTrue(parkedText.isDisplayed(), "❌ 'Parked' text is NOT displayed");
//
//            // Optionally, verify the text value
//            String actualText = parkedText.getText();
//            softAssert.assertEquals(actualText, "Parked", "❌ Text mismatch for parked status");
//
//            ExtentLogger.pass("✅ 'Parked' text is displayed: " + actualText);
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ 'Parked' text verification failed: " + e.getMessage());
//            softAssert.fail("'Parked' text verification failed", e);
//        }
//    }
//    public void verifyFindScooterTextIsDisplayed() {
//        try {
//            // Locate the element using UiSelector
//            WebElement findScooterText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.androidUIAutomator("new UiSelector().text(\"Find scooter\")")
//                    )
//            );
//
//            // Verify it is displayed
//            Assert.assertTrue(findScooterText.isDisplayed(), "❌ 'Find scooter' text is NOT displayed");
//
//            // Optionally, verify the actual text
//            String actualText = findScooterText.getText();
//            Assert.assertEquals(actualText, "Find scooter", "❌ Text mismatch for 'Find scooter'");
//
//            ExtentLogger.pass("✅ 'Find scooter' text is displayed: " + actualText);
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ 'Find scooter' text verification failed: " + e.getMessage());
//            Assert.fail("'Find scooter' text verification failed", e);
//        }
//    }
//    public void verifyBatteryTextIsDisplayed() {
//        try {
//            // Scroll to the element with text "BATTERY"
//            WebElement batteryText = driver.findElement(
//                    AppiumBy.androidUIAutomator(
//                            "new UiScrollable(new UiSelector().scrollable(true))" +
//                                    ".scrollIntoView(new UiSelector().text(\"BATTERY\"))"
//                    )
//            );
//
//            // Verify it is displayed
//            Assert.assertTrue(batteryText.isDisplayed(), "❌ 'BATTERY' text is NOT displayed");
//
//            // Optionally, verify the actual text
//            String actualText = batteryText.getText();
//            Assert.assertEquals(actualText, "BATTERY", "❌ Text mismatch for 'BATTERY'");
//
//            ExtentLogger.pass("✅ 'BATTERY' text is displayed: " + actualText);
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ 'BATTERY' text verification failed: " + e.getMessage());
//            Assert.fail("'BATTERY' text verification failed", e);
//        }
//    }
//    public void verifyLastChargedTextIsDisplayed() {
//        try {
//            // Locate the element containing "last charged to"
//            WebElement lastChargedText = driver.findElement(
//                    AppiumBy.androidUIAutomator(
//                            "new UiSelector().textContains(\"last charged to\")"
//                    )
//            );
//
//            // Verify it is displayed
//            Assert.assertTrue(lastChargedText.isDisplayed(), "❌ 'last charged to' text is NOT displayed");
//
//            // Optionally, get the full text including the dynamic number
//            String actualText = lastChargedText.getText();
//            ExtentLogger.pass("✅ 'last charged to' text is displayed: " + actualText);
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ 'last charged to' text verification failed: " + e.getMessage());
//            Assert.fail("'last charged to' text verification failed", e);
//        }
//    }
//    public void verifyBatteryBtnDisplayed() {
//        try {
//            WebElement element = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View[1]")
//                    )
//            );
//
//            Assert.assertTrue(element.isDisplayed(), "❌ batteryBtn is NOT displayed");
//            ExtentLogger.pass("✅ batteryBtn is displayed");
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ batteryBtn verification failed: " + e.getMessage());
//            Assert.fail("batteryBtn verification failed", e);
//        }
//    }
//    public void verifyBatteryPercentageDisplayed() {
//        try {
//            // Locate the element containing "%" inside the battery area
//            WebElement batteryPercentage = driver.findElement(
//                    AppiumBy.androidUIAutomator(
//                            "new UiSelector().textContains(\"%\")"  // matches 0%, 23%, 100%, etc.
//                    )
//            );
//
//            // Verify it is displayed
//            Assert.assertTrue(batteryPercentage.isDisplayed(), "❌ Battery percentage is NOT displayed");
//
//            // Optionally, get the value
//            String percentageText = batteryPercentage.getText();
//            ExtentLogger.pass("✅ Battery percentage is displayed: " + percentageText);
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ Battery percentage verification failed: " + e.getMessage());
//            Assert.fail("Battery percentage verification failed", e);
//        }
//    }
//    public void verifyChargingHistoryButtonDisplayed() {
//        try {
//            // Locate the button by class
//            WebElement chargingHistoryBtn = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.androidUIAutomator(
//                                    "new UiSelector().className(\"android.widget.Button\")"
//                            )
//                    )
//            );
//
//            // Verify the button is displayed
//            Assert.assertTrue(chargingHistoryBtn.isDisplayed(), "❌ Charging History button is NOT displayed");
//
//            // Verify the button text
//            String actualText = chargingHistoryBtn.getText();
//            Assert.assertEquals(actualText, "Charging History", "❌ Button text mismatch");
//
//            ExtentLogger.pass("✅ Charging History button is displayed with text: " + actualText);
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ Charging History button verification failed: " + e.getMessage());
//            Assert.fail("Charging History button verification failed", e);
//        }
//    }
//    public void verifySimpleByDesignViewDisplayed() {
//        try {
//            WebElement simpleByDesignView = driver.findElement(
//                    By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]")
//            );
//
//            Assert.assertTrue(simpleByDesignView.isDisplayed(), "❌ SimpleByDesign view is NOT displayed");
//            ExtentLogger.pass("✅ SimpleByDesign view is displayed");
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ SimpleByDesign view not found: " + e.getMessage());
//            Assert.fail("SimpleByDesign view verification failed", e);
//        }
//    }
//    public void verifyCompanyNameDisplayed() {
//        try {
//            WebElement companyText = driver.findElement(
//                    AppiumBy.androidUIAutomator(
//                            "new UiSelector().text(\"Simpleenergy Private Limited\")"
//                    )
//            );
//
//            Assert.assertTrue(companyText.isDisplayed(), "❌ Company name text is NOT displayed");
//            Assert.assertEquals(companyText.getText(), "Simpleenergy Private Limited");
//
//            ExtentLogger.pass("✅ Company name text is displayed");
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ Company name text verification failed: " + e.getMessage());
//            Assert.fail("Company name text not found", e);
//        }
//    }
//    public void verifyAtSymbolDisplayedBesideCompanyName() {
//        try {
//            WebElement atSymbol = driver.findElement(
//                    By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]")
//            );
//
//            Assert.assertTrue(atSymbol.isDisplayed(), "❌ @ symbol icon is NOT displayed");
//            ExtentLogger.pass("✅ @ symbol icon is displayed beside company name");
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ @ symbol icon not found: " + e.getMessage());
//            Assert.fail("@ symbol icon verification failed", e);
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
