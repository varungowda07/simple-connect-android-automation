package tests.password;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class PasscodeSetSuccessScreen extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    SoftAssert softAssert = new SoftAssert();
    public void passcodeSuccessScreen2() {
        verifyText();
        verifyNewPasscodeValue();
        if(clickDoneBtn()) {
            System.out.println("Passcode set success");
        }

    }
    private void verifyText() {
        universalMethods.verifyTextContains("passcode set successfully","passcode set");
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
            String expectedPasscode = "1234";

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
