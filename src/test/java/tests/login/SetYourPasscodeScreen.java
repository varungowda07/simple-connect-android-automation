package tests.login;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

public class SetYourPasscodeScreen extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // ====================== TEST ENTRY ======================
    public void setYourPasscodeScreen() {
        ExtentLogger.info("🔐 Set Your Passcode Screen Test Started");

        verifySetYourPasscodeText();
        verifyPasscodeDescriptionText();
        verifyEnhancedProtectionText();
        verifyEnhancedProtectionDescriptionText();
        verifyQuickUnlockText();
        verifyQuickUnlockDescriptionText();
        verifySeamlessControlText();
        verifySeamlessControlDescriptionText();
        verifyInfoIconDisplayed();
        verifyPasscodeInfoTextIsDisplayed();
        clickContinueButton();

        ExtentLogger.info("✅ Set Your Passcode Screen Test Completed");
    }

    // ====================== VALIDATIONS ======================

    private void verifySetYourPasscodeText() {
        try {
            WebElement passcodeText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Set your passcode\")")
            ));

            softAssert.assertEquals(passcodeText.getText().trim(),
                    "Set your passcode",
                    "❌ Set your passcode text mismatch");

            ExtentLogger.pass("✅ Set your passcode text verified");

        } catch (Exception e) {
            fail("❌ Set your passcode text not found or incorrect", e);
        }
    }

    private void verifyPasscodeDescriptionText() {
        try {
            WebElement descriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Secure your scooter\")")
            ));

            String actual = normalize(descriptionText.getText());
            String expected = "Secure your scooter and ride worry-free with a personal passcode.";

            softAssert.assertEquals(actual, expected,
                    "❌ Passcode description text mismatch");

            ExtentLogger.pass("✅ Passcode description text verified");

        } catch (Exception e) {
            fail("❌ Passcode description text not found or incorrect", e);
        }
    }

    private void verifyEnhancedProtectionText() {
        try {
            WebElement enhancedProtectionText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Enhanced Protection\")")
            ));

            softAssert.assertEquals(enhancedProtectionText.getText().trim(),
                    "Enhanced Protection",
                    "❌ Enhanced Protection text mismatch");

            ExtentLogger.pass("✅ Enhanced Protection text verified");

        } catch (Exception e) {
            fail("❌ Enhanced Protection text not found or incorrect", e);
        }
    }

    private void verifyEnhancedProtectionDescriptionText() {
        try {
            WebElement safetyText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().textContains(\"Your passcode prevents unauthorized access\")")
            ));

            String actual = normalize(safetyText.getText());
            String expected =
                    "Your passcode prevents unauthorized access and keeps your scooter safe, even when parked.";

            softAssert.assertEquals(actual, expected,
                    "❌ Passcode safety description mismatch");

            ExtentLogger.pass("✅ Passcode safety description verified");

        } catch (Exception e) {
            fail("❌ Passcode safety description not found or incorrect", e);
        }
    }

    private void verifyQuickUnlockText() {
        try {
            WebElement quickUnlockText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Quick Unlock\")")
            ));

            softAssert.assertEquals(quickUnlockText.getText().trim(),
                    "Quick Unlock",
                    "❌ Quick Unlock text mismatch");

            ExtentLogger.pass("✅ Quick Unlock text verified");

        } catch (Exception e) {
            fail("❌ Quick Unlock text not found or incorrect", e);
        }
    }

    private void verifyQuickUnlockDescriptionText() {
        try {
            WebElement descriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Easily unlock your scooter\")")
            ));

            String actual = normalize(descriptionText.getText());
            String expected =
                    "Easily unlock your scooter with a simple code — no need to fumble with keys.";

            softAssert.assertEquals(actual, expected,
                    "❌ Quick Unlock description mismatch");

            ExtentLogger.pass("✅ Quick Unlock description verified");

        } catch (Exception e) {
            fail("❌ Quick Unlock description not found or incorrect", e);
        }
    }

    private void verifySeamlessControlText() {
        try {
            WebElement seamlessControlText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Seamless Control\")")
            ));

            softAssert.assertEquals(seamlessControlText.getText().trim(),
                    "Seamless Control",
                    "❌ Seamless Control text mismatch");

            ExtentLogger.pass("✅ Seamless Control text verified");

        } catch (Exception e) {
            fail("❌ Seamless Control text not found or incorrect", e);
        }
    }

    private void verifySeamlessControlDescriptionText() {
        try {
            WebElement descriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Your passcode integrates smoothly\")")
            ));

            String actual = normalize(descriptionText.getText());
            String expected =
                    "Your passcode integrates smoothly with your scooter's dashboard for a smarter, faster experience.";

            softAssert.assertEquals(actual, expected,
                    "❌ Seamless Control description mismatch");

            ExtentLogger.pass("✅ Seamless Control description verified");

        } catch (Exception e) {
            fail("❌ Seamless Control description not found or incorrect", e);
        }
    }

    private void verifyPasscodeInfoTextIsDisplayed() {
        try {
            WebElement infoText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().text(\"You can update passcode anytime in your settings.\")")
            ));

            softAssert.assertTrue(infoText.isDisplayed(),
                    "❌ Info text not displayed");

            ExtentLogger.pass("✅ Info text displayed");

        } catch (Exception e) {
            fail("❌ Info text not found or not visible", e);
        }
    }

    private void verifyInfoIconDisplayed() {
        try {
            WebElement infoIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text='You can update passcode anytime in your settings.']" +
                            "/preceding-sibling::android.view.View[3]")
            ));

            softAssert.assertTrue(infoIcon.isDisplayed(),
                    "❌ Info icon not displayed");

            ExtentLogger.pass("✅ Info icon displayed");

        } catch (Exception e) {
            fail("❌ Info icon not found or not visible", e);
        }
    }

    private void clickContinueButton() {
        try {
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Continue\")")
            ));

            softAssert.assertTrue(continueBtn.isDisplayed(),
                    "❌ Continue button not displayed");

            continueBtn.click();
            ExtentLogger.pass("✅ Continue button clicked");

        } catch (Exception e) {
            fail("❌ Continue button not clickable", e);
        }
    }

    // ====================== HELPERS ======================

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

    private String normalize(String text) {
        return text.replace("\n", " ").replaceAll("\\s+", " ").trim();
    }
}
