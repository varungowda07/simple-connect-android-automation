package tests.login;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.TokenStore;

import java.time.Duration;
import java.util.Collections;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class OtpScreenTests extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();
    private final TokenStore tokenStore = new TokenStore();

    // ====================== TEST ENTRY ======================
    public void testOtpScreen() {
        ExtentLogger.info("📲 OTP Screen Test Started");

        validateMobileScreenTexts();
        verifySendOtpInitiallyDisabled();
        enterMobileNumber(tokenStore.mobileNumber);
        clickSendOtp();
        tapEditInOtpInstruction();
        clickSendOtp();
        verifyVerifyYourMobileNumberText();
        verifyOtpInstructionText(tokenStore.mobileNumber);
        verifyRetryTimerVisible();
        verifyAndClickResendOtp();
        enterOtp();
        ExtentLogger.info("✅ OTP Screen Test Completed");
    }

    // ====================== CORE FLOW ======================

    private void validateMobileScreenTexts() {
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    androidUIAutomator("new UiSelector().className(\"android.widget.TextView\").textContains(\"Enter your\")")
            ));

            softAssert.assertEquals(header.getText().trim(),
                    "Enter your\nmobile number",
                    "❌ Header text mismatch");

            ExtentLogger.pass("✅ 'Enter your mobile number' header verified");

        } catch (Exception e) {
            fail("❌ 'Enter your mobile number' header not found", e);
        }

        try {
            WebElement subText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    androidUIAutomator("new UiSelector().textContains(\"4-digit OTP\")")
            ));

            softAssert.assertEquals(subText.getText().trim(),
                    "A 4-digit OTP will be sent to this number\nvia SMS for verification.\n",
                    "❌ Subtext mismatch");

            ExtentLogger.pass("✅ OTP description text verified");

        } catch (Exception e) {
            fail("❌ OTP description text not found", e);
        }
    }

    private void verifySendOtpInitiallyDisabled() {
        try {
            WebElement sendOtpBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    androidUIAutomator("new UiSelector().text(\"Send OTP\")")
            ));

            softAssert.assertFalse(sendOtpBtn.isEnabled(),
                    "❌ Send OTP button should be disabled initially");

            ExtentLogger.pass("✅ Send OTP button disabled initially");

        } catch (Exception e) {
            fail("❌ Send OTP button state verification failed", e);
        }
    }

    private void enterMobileNumber(String mobileNumber) {
        try {
            WebElement mobileField = wait.until(ExpectedConditions.elementToBeClickable(
                    androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
            ));

            mobileField.clear();
            mobileField.sendKeys(mobileNumber);

            ExtentLogger.pass("✅ Mobile number entered: " + mobileNumber);

        } catch (Exception e) {
            fail("❌ Mobile number field not usable", e);
        }
    }

    private void clickSendOtp() {
        try {
            WebElement sendOtpBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    androidUIAutomator("new UiSelector().text(\"Send OTP\")")
            ));

            sendOtpBtn.click();
            ExtentLogger.pass("✅ Send OTP button clicked");

        } catch (Exception e) {
            fail("❌ Send OTP button not clickable", e);
        }
    }

    private void verifyVerifyYourMobileNumberText() {
        try {
            WebElement verifyText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Verify your\")")
            ));

            softAssert.assertEquals(verifyText.getText().trim(),
                    "Verify your\nmobile number",
                    "❌ Verify mobile number header mismatch");

            ExtentLogger.pass("✅ 'Verify your mobile number' text verified");

        } catch (Exception e) {
            fail("❌ Verify mobile number header not found", e);
        }
    }

    private void verifyOtpInstructionText(String mobileNumber) {
        try {
            WebElement otpText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Enter the OTP sent via SMS\")")
            ));

            String expected =
                    "Enter the OTP sent via SMS to\n" +
                            mobileNumber + " edit";

            softAssert.assertEquals(otpText.getText().trim(),
                    expected,
                    "❌ OTP instruction text mismatch");

            ExtentLogger.pass("✅ OTP instruction text verified");

        } catch (Exception e) {
            fail("❌ OTP instruction text not found", e);
        }
    }

    private void verifyRetryTimerVisible() {
        try {
            WebElement retryText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    androidUIAutomator("new UiSelector().textContains(\"Retry\")")
            ));

            softAssert.assertTrue(retryText.isDisplayed(),
                    "❌ Retry timer not displayed");

            ExtentLogger.pass("✅ Retry timer displayed");

        } catch (Exception e) {
            fail("❌ Retry timer text not visible", e);
        }
    }

    private void verifyAndClickResendOtp() {
        try {
            WebElement resendOtp = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Resend OTP\")")
            ));

            softAssert.assertTrue(resendOtp.isDisplayed(),
                    "❌ Resend OTP button not displayed");

            resendOtp.click();
            ExtentLogger.pass("✅ Resend OTP clicked");

        } catch (Exception e) {
            fail("❌ Resend OTP not clickable", e);
        }
    }

    private void enterOtp() {
        try {
            WebElement otpField = wait.until(ExpectedConditions.elementToBeClickable(
                    androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
            ));

            otpField.sendKeys("3289");
            ExtentLogger.pass("✅ OTP entered successfully");

        } catch (Exception e) {
            fail("❌ OTP field not usable", e);
        }
    }

    private void tapEditInOtpInstruction() {
        try {
            WebElement otpText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Enter the OTP sent via SMS\")")
            ));

            Rectangle rect = otpText.getRect();
            int tapX = rect.getX() + (rect.getWidth() * 80 / 100);
            int tapY = rect.getY() + (rect.getHeight() * 70 / 100);

            tapByCoordinates(tapX, tapY);
            ExtentLogger.pass("✅ Tapped Edit button in OTP instruction");

        } catch (Exception e) {
            fail("❌ Failed to tap Edit button", e);
        }
    }

    // ====================== UTILITIES ======================

    private void tapByCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                x, y
        ));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
