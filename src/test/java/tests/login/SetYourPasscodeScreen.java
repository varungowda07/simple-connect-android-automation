package tests.login;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class SetYourPasscodeScreen extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();
    UniversalMethods universalMethods = new UniversalMethods();

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
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Enhanced Protection\")\n"),
                "Enhanced Protection Iocn",
                false
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Seamless Control\")\n"),
                "Seamless Control Icon",
                false
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Secure Access\")\n"),
                "Secure Access Icon",
                false
        );
        clickContinueButton();

        ExtentLogger.info("✅ Set Your Passcode Screen Test Completed");
    }

    // ====================== VALIDATIONS ======================

    private void verifySetYourPasscodeText() {
        try {
            WebElement passcodeText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Set your\")")
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
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Enhanced Protection\")")
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
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Secure Access\")")
            ));

            softAssert.assertEquals(quickUnlockText.getText().trim(),
                    "Secure Access",
                    "❌ Secure Access text mismatch");

            ExtentLogger.pass("✅ Secure Access text verified");

        } catch (Exception e) {
            fail("❌ Secure Access text not found or incorrect", e);
        }
    }

    private void verifyQuickUnlockDescriptionText() {
        try {
            WebElement descriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Uses a PIN to protect Docs\")")
            ));

            String actual = normalize(descriptionText.getText());
            String expected =
                    "Uses a PIN to protect Docs, OTA, GeoFence, and Incognito—securing critical features without slowing you down.";

            softAssert.assertEquals(actual, expected,
                    "❌ Secure Access description mismatch");

            ExtentLogger.pass("✅  Secure Access description verified");

        } catch (Exception e) {
            fail("❌  Secure Access description not found or incorrect", e);
        }
    }

    private void verifySeamlessControlText() {
        String expected = "Seamless Control";
        try {
            WebElement seamlessControlText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Seamless Control\")")
            ));
            String actual = seamlessControlText.getText().trim();

            softAssert.assertEquals(actual,
                    expected,
                    "❌ Seamless Control text mismatch");

            ExtentLogger.pass("✅ Seamless Control text verified :"+actual);

        } catch (Exception e) {
            fail("❌ Seamless Control text not found or incorrect : "+expected, e);
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
                            "new UiSelector().textContains(\"You can update passcode anytime\")")
            ));
            String actual = infoText.getText();

            String expected =
                    "You can update passcode anytime from your scooter garage.";

            softAssert.assertEquals(actual, expected,
                    "❌ Info text mismatch");

            ExtentLogger.pass("✅ Info text displayed");

        } catch (Exception e) {
            fail("❌ Info text not found or not visible", e);
        }
    }

    private void verifyInfoIconDisplayed() {
        try {
            WebElement infoIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Info\")\n")
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
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Continue\")")
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
