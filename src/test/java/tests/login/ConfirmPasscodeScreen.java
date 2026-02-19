package tests.login;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class ConfirmPasscodeScreen extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();
    UniversalMethods universalMethods = new UniversalMethods();

    // ====================== TEST ENTRY ======================
    public boolean confirmPasscode(String passcode) {
        ExtentLogger.info("🔐 Confirm Passcode Screen Test Started");

        verifyConfirmPasscodeText();
        verifyReEnter4DigitPasscodeText();
        enter4DigitPasscode(passcode);
        return verifyAndClickConfirmButton();
    }

    // ====================== VALIDATIONS ======================

    private void verifyConfirmPasscodeText() {
        try {
            WebElement confirmPasscodeText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Confirm Passcode\")")
            ));

            String actualText = confirmPasscodeText.getText();
            softAssert.assertTrue(actualText.contains("Confirm"),
                    "❌ Text does not contain 'Confirm': " + actualText);

            ExtentLogger.pass("✅ 'Confirm' text is displayed: " + actualText);

        } catch (Exception e) {
            fail("❌ 'Confirm' text not found or incorrect", e);
        }
    }


    private void verifyReEnter4DigitPasscodeText() {
        try {
            WebElement passcodeText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Re-enter your 4-digit passcode.\")")
            ));

            softAssert.assertTrue(passcodeText.isDisplayed(),
                    "❌ 'Re-enter your 4-digit passcode.' text is NOT displayed");

            ExtentLogger.pass("✅ 'Re-enter your 4-digit passcode.' text is displayed");

        } catch (Exception e) {
            fail("❌ 'Re-enter your 4-digit passcode.' text not found or incorrect", e);
        }
    }

    private void enter4DigitPasscode(String passcode) {
        try {
            WebElement passcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
            ));

            passcodeField.clear();
            passcodeField.sendKeys(passcode);

            ExtentLogger.pass("✅ Entered 4-digit passcode: " + passcode);

        } catch (Exception e) {
            fail("❌ Failed to re-enter passcode", e);
        }
    }

    private boolean verifyAndClickConfirmButton() {
        try {
            WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Confirm\")")
            ));

            softAssert.assertTrue(confirmBtn.isDisplayed(),
                    "❌ 'Confirm' button is NOT displayed");

            confirmBtn.click();
            ExtentLogger.pass("✅ 'Confirm' button is displayed and clicked");
            return true;

        } catch (Exception e) {
            fail("❌ 'Confirm' button not clickable", e);
            return false;
        }
    }

    // ====================== HELPERS ======================

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
