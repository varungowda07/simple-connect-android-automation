package tests.password;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class PasscodeResetSuccessScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    ResetPasscodeScreen resetPasscodeScreen = new ResetPasscodeScreen();
    SoftAssert softAssert = new SoftAssert();
    public void passcodeResetSuccessScreen() {
        verifyText();
        verifyNewPasscodeValue();
        if(clickDoneBtn()) {
            System.out.println("Passcode reset success");
        }

    }
    private void verifyText() {
        universalMethods.verifyTextContains("passcode reset successfully","passcode reset");
        universalMethods.verifyExactText("Your new passcode is now active.");
        universalMethods.verifyExactText("NEW PASSCODE");
        universalMethods.verifyExactText("Done");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Check\")"),
                "Check Button",
                false
        );
    }
    private boolean clickDoneBtn() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Done\")"),
                "Done button Clicked",
                true
        );
    }
    private void verifyNewPasscodeValue() {
        try {
            WebElement passcodeValue = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.TextView[@text=\"NEW PASSCODE\"]/following-sibling::android.widget.TextView")
                    )
            );

            String actualPasscode = passcodeValue.getText().trim();
            String expectedPasscode = resetPasscodeScreen.passcode;

            softAssert.assertEquals(actualPasscode, expectedPasscode,
                    "❌ Passcode mismatch");

            ExtentLogger.pass("✅ Passcode matched: " + actualPasscode);

        } catch (Exception e) {
            fail("❌ New passcode value verification failed", e);
        }
    }
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
