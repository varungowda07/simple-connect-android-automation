package tests.login;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.settings.editemergencycontact.EditEmergenctContactFlow;

import java.util.Random;

public class AddEmergencyContact extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();
    GetStartedTests getStartedTests = new GetStartedTests();
    EditEmergenctContactFlow editEmergenctContact  = new EditEmergenctContactFlow();

    // ====================== TEST ENTRY ======================
    public void addEmergencyContact() {
        ExtentLogger.info("🚨 Add Emergency Contact Test Started");

        verifyAddEmergencyContactText();
        verifyAndClickBackArrow();
        getStartedTests.clickGetStartedButton();
        verifyEmergencyContactInfoText();
        verifyNameLabel();
        enterRandomNameInNameField();
        verifyAndEnterRandomMobileNumber();
        verifyMobileNumberIconIsDisplayed();
        verifyAndClickContinueButton();
        ExtentLogger.info("✅ Add Emergency Contact Test Completed");
    }

    // ====================== STEPS ======================

    private void verifyAddEmergencyContactText() {
        try {
            WebElement emergencyContactText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Add emergency contact\")")
            ));

            softAssert.assertEquals(emergencyContactText.getText().trim(),
                    "Add emergency contact",
                    "❌ Add emergency contact text mismatch");

            ExtentLogger.pass("✅ Add emergency contact text verified");

        } catch (Exception e) {
            fail("❌ Add emergency contact text not found or mismatch", e);
        }
    }

    private void verifyAndClickBackArrow() {
        try {
            WebElement backArrow = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//androidx.compose.ui.platform.ComposeView" +
                            "/android.view.View/android.view.View/android.view.View" +
                            "/android.view.View/android.view.View[1]/android.view.View/..")
            ));

            softAssert.assertTrue(backArrow.isDisplayed(), "❌ Back arrow not displayed");
            backArrow.click();

            ExtentLogger.pass("✅ Back arrow displayed and clicked");

        } catch (Exception e) {
            fail("❌ Back arrow not found or not clickable", e);
        }
    }

    private void verifyEmergencyContactInfoText() {
        try {
            WebElement infoText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().textContains(\"This contact will be used for sending emergency updates\")")
            ));

            String actual = infoText.getText().replace("\n", " ").replaceAll("\\s+", " ").trim();
            String expected = "This contact will be used for sending emergency updates and the scooter’s live location when triggered.";

            softAssert.assertEquals(actual, expected, "❌ Emergency contact info text mismatch");

            ExtentLogger.pass("✅ Emergency contact info text verified");

        } catch (Exception e) {
            fail("❌ Emergency contact info text not found or incorrect", e);
        }
    }

    private void verifyNameLabel() {
        try {
            WebElement nameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Name\")")
            ));

            softAssert.assertEquals(nameLabel.getText().trim(), "Name", "❌ Name label mismatch");
            ExtentLogger.pass("✅ Name label verified");

        } catch (Exception e) {
            fail("❌ Name label not found or incorrect", e);
        }
    }

    public void enterRandomNameInNameField() {
        try {
            WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text=\"Name\"]/..")
            ));

            String randomName = generateRandomName();
            nameInput.clear();
            nameInput.sendKeys(randomName);

            softAssert.assertEquals(nameInput.getText().trim(), randomName, "❌ Name input mismatch");
            ExtentLogger.pass("✅ Random name entered: " + randomName);

        } catch (Exception e) {
            fail("❌ Failed to enter random name", e);
        }
    }

    public void verifyAndEnterRandomMobileNumber() {
        try {
            WebElement mobileLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Mobile Number\")")
            ));

            softAssert.assertEquals(mobileLabel.getText().trim(),
                    "Mobile Number",
                    "❌ Mobile number label mismatch");

            WebElement mobileInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text=\"Mobile Number\"]/..")
            ));

            String randomMobile = generateRandomMobileNumber();
            System.out.println(randomMobile);
            mobileInput.clear();
            mobileInput.sendKeys(randomMobile);

            softAssert.assertEquals(mobileInput.getText().trim(), randomMobile,
                    "❌ Mobile number input mismatch");

            ExtentLogger.pass("✅ Random mobile number entered: " + randomMobile);

        } catch (Exception e) {
            fail("❌ Failed to verify or enter mobile number", e);
        }
    }

    private void verifyMobileNumberIconIsDisplayed() {
        try {
            WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Contacts\")\n")
            ));

            softAssert.assertTrue(icon.isDisplayed(), "❌ Mobile number icon not displayed");
            ExtentLogger.pass("✅ Mobile number icon displayed");

        } catch (Exception e) {
            fail("❌ Mobile number icon not found or not visible", e);
        }
    }

    private void verifyAndClickContinueButton() {
        try {
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Continue\")")
            ));

            softAssert.assertEquals(continueButton.getText().trim(), "Continue",
                    "❌ Continue button text mismatch");

            continueButton.click();
            ExtentLogger.pass("✅ Continue button clicked");

        } catch (Exception e) {
            fail("❌ Continue button not found or not clickable", e);
        }
    }

    // ====================== HELPERS ======================

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

    public String generateRandomName() {
        String[] names = {"Aarav", "Rohan", "Vikram", "Kiran", "Neha", "Anita"};
        return names[new Random().nextInt(names.length)];
    }

    public String generateRandomMobileNumber() {
        Random random = new Random();
        return "9" + (100000000 + random.nextInt(900000000));
    }

}
