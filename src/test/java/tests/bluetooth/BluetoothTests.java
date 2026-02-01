package tests.bluetooth;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class BluetoothTests extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    @Test(retryAnalyzer = listeners.RetryAnalyzer.class)
    public void bluetoothFlow() {

        ExtentLogger.info("🔵 Bluetooth Flow Started");

        clickBluetoothButton();
        clickGetStartedButton();
        clickCancelButton();

        ExtentLogger.info("✅ Bluetooth Flow Completed");
    }

    // ====================== STEPS ======================

    private void clickBluetoothButton() {
        try {
            WebElement bluetoothBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().description(\"Bluetooth\")")
                    )
            );

            softAssert.assertTrue(bluetoothBtn.isDisplayed(), "❌ Bluetooth button not displayed");
            bluetoothBtn.click();
            ExtentLogger.pass("✅ Bluetooth button clicked");

        } catch (Exception e) {
            fail("❌ Bluetooth button NOT found or not clickable", e);
        }
    }

    private void clickGetStartedButton() {
        try {
            WebElement getStartedBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().className(\"android.widget.Button\")")
                    )
            );

            softAssert.assertTrue(getStartedBtn.isEnabled(), "❌ Get Started button not enabled");
            getStartedBtn.click();
            ExtentLogger.pass("✅ Get Started button clicked");

            sleep(3000);

        } catch (Exception e) {
            fail("❌ Get Started button NOT found or not clickable", e);
        }
    }

    private void clickCancelButton() {
        try {
            WebElement cancelBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator("new UiSelector().text(\"Cancel\")")
                    )
            );

            softAssert.assertTrue(cancelBtn.isDisplayed(), "❌ Cancel button not displayed");
            cancelBtn.click();
            ExtentLogger.pass("✅ Cancel button clicked");

        } catch (Exception e) {
            fail("❌ Cancel button NOT found or not clickable", e);
        }
    }

    // ====================== HELPERS ======================

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
