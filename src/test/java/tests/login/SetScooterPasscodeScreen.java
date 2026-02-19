package tests.login;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

public class SetScooterPasscodeScreen extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();
    public String passcode = "1234";

    // ====================== TEST ENTRY ======================
    public void setScooterPasscodeScreen() {

        verifySetScooterPasscodeText("Set Scooter");
        verifyChoose4DigitPasscodeText();
        enter4DigitPasscode(passcode);
        verifyAndClickContinueButton();
    }

    // ====================== VALIDATIONS ======================

    public void verifySetScooterPasscodeText(String name) {
        try {
            WebElement passcodeText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains('" +name+ "')")
            ));

            String actualText = passcodeText.getText();
            softAssert.assertTrue(actualText.contains("Set scooter"),
                    "❌ Text does not contain 'Set scooter': " + actualText);

            ExtentLogger.pass("✅ 'Set scooter' text is displayed: " + actualText);

        } catch (Exception e) {
            fail("❌ 'Set scooter' text not found or incorrect", e);
        }
    }


    public void verifyChoose4DigitPasscodeText() {
        try {
            WebElement passcodeText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Choose a 4-digit passcode.\")")
            ));

            softAssert.assertTrue(passcodeText.isDisplayed(),
                    "❌ 'Choose a 4-digit passcode.' text is NOT displayed");

            ExtentLogger.pass("✅ 'Choose a 4-digit passcode.' text is displayed");

        } catch (Exception e) {
            fail("❌ 'Choose a 4-digit passcode.' text not found or incorrect", e);
        }
    }

    public boolean enter4DigitPasscode(String passcode) {
        try {
            WebElement passcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")

            ));

            passcodeField.clear();
            passcodeField.sendKeys(passcode);

            ExtentLogger.pass("✅ Entered 4-digit passcode: " + passcode);
            return true;

        } catch (Exception e) {
            fail("❌ Failed to enter passcode", e);
            return false;
        }
    }

    public boolean verifyAndClickContinueButton() {
        try {
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Continue\")")
            ));

            softAssert.assertTrue(continueBtn.isDisplayed(),
                    "❌ 'Continue' button is NOT displayed");

            continueBtn.click();
            ExtentLogger.pass("✅ 'Continue' button is displayed and clicked");
            return true;

        } catch (Exception e) {
            fail("❌ 'Continue' button not clickable", e);
            return  false;
        }
    }

    // ====================== HELPERS ======================

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
