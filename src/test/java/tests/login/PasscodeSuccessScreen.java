package tests.login;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.utils.UniversalMethods;

import static tests.base.BaseTest.wait;

public class PasscodeSuccessScreen {

    private final SoftAssert softAssert = new SoftAssert();
    private final SetScooterPasscodeScreen setScooterPasscodeScreen = new SetScooterPasscodeScreen();
    UniversalMethods universalMethods = new UniversalMethods();

    // ====================== FLOW ======================
    public void passcodeSuccessScreen() {
        ExtentLogger.info("🔐 Passcode Success Screen Flow Started");

        verifyPasscodeSetSuccessfullyText();
        verifyNewPasscodeActiveText();
        verifyNewPasscodeText();
        verifyNewPasscodeValue();
        clickDoneButton();

        ExtentLogger.info("✅ Passcode Success Screen Flow Completed");
    }

    // ====================== STEPS ======================
    private void verifyPasscodeSetSuccessfullyText() {
        try {
            WebElement successText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Passcode set\")")
                    )
            );

            String actualText = successText.getText();
            softAssert.assertTrue(actualText.contains("Passcode set"),
                    "❌ Text does not contain 'Passcode set': " + actualText);

            ExtentLogger.pass("✅ Passcode success text displayed: " + actualText);

        } catch (Exception e) {
            fail("❌ Passcode success text verification failed", e);
        }
    }

    private void verifyNewPasscodeActiveText() {
        try {
            WebElement activeText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"now active\")")
                    )
            );

            String actualText = activeText.getText();
            softAssert.assertTrue(actualText.contains("now active"),
                    "❌ Text does not contain 'now active': " + actualText);

            ExtentLogger.pass("✅ New passcode active text displayed: " + actualText);

        } catch (Exception e) {
            fail("❌ New passcode active text verification failed", e);
        }
    }

    private void verifyNewPasscodeText() {
        try {
            WebElement newPasscodeText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"NEW PASSCODE\")")
                    )
            );

            softAssert.assertTrue(newPasscodeText.isDisplayed(),
                    "❌ 'NEW PASSCODE' label not displayed");

            ExtentLogger.pass("✅ 'NEW PASSCODE' label displayed");

        } catch (Exception e) {
            fail("❌ NEW PASSCODE label verification failed", e);
        }
    }

    private void verifyNewPasscodeValue() {
        try {
            WebElement passcodeValue = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.TextView[contains(@text,'NEW PASSCODE')]/../android.widget.TextView[2]")
                    )
            );

            String actualPasscode = passcodeValue.getText().trim();
            String expectedPasscode = setScooterPasscodeScreen.passcode;

            softAssert.assertEquals(actualPasscode, expectedPasscode,
                    "❌ Passcode mismatch");

            ExtentLogger.pass("✅ Passcode matched: " + actualPasscode);

        } catch (Exception e) {
            fail("❌ New passcode value verification failed", e);
        }
    }

    private void clickDoneButton() {
        try {
            WebElement doneButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Done\")")
                    )
            );

            softAssert.assertTrue(doneButton.isDisplayed(), "❌ 'Done' button not displayed");
            doneButton.click();

            ExtentLogger.pass("✅ 'Done' button clicked");

        } catch (Exception e) {
            fail("❌ Done button click failed", e);
        }
    }

    // ====================== HELPERS ======================
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
