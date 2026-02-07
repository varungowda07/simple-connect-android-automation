package tests.profile.viewprofile;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

import java.util.Random;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class EditEmail extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();
    private final EditProfileTests editProfileTests = new EditProfileTests();
    UniversalMethods universalMethods = new UniversalMethods();

    // ====================== FLOW ======================

    public void editEmail() {

        ExtentLogger.info("📧 Edit Email Flow Started");

        enterRandomEmailId();
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Email ID\")"),
                "Email Id description",
                true
        );
        closeEmailInfoView();
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Email ID\")"),
                "Email Id description",
                true
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Info\")"),
                "Info Icon",
                false
        );

        verifyVerificationLinkText();
        clickSendLink();
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Check\")"),
                "Info Icon",
                false
        );
        verifyConfirmationLinkText();
        clickDone();
        enterRandomEmailId();
        navigateBack();
        clickUpdateButton();
        verifyConfirmationMessage();
        clickCancelButton();
        editProfileTests.clickAndVerifyEditButton();
        enterRandomEmailId();
        navigateBack();
        clickUpdateButton();
        clickUpdateButton();
        navigateBack();
        navigateBack();
        navigateBack();

        ExtentLogger.info("✅ Edit Email Flow Completed");
    }

    // ====================== STEPS ======================

    private void enterRandomEmailId() {
        try {
            WebElement emailInput = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.TextView[@text='Email ID']/../android.widget.EditText")
                    )
            );

            emailInput.click();
            emailInput.clear();

            String randomEmail = generateRandomEmail();
            emailInput.sendKeys(randomEmail);

            softAssert.assertEquals(emailInput.getText().trim(), randomEmail,
                    "❌ Entered Email ID does not match");

            ExtentLogger.pass("✅ Entered random Email ID: " + randomEmail);

        } catch (Exception e) {
            fail("❌ Email ID entry failed", e);
        }
    }

    private void openEmailInfoView() {
        try {
            WebElement emailIdView = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.TextView[@text='Email ID']/../android.view.View")
                    )
            );

            emailIdView.click();
            ExtentLogger.pass("✅ Email ID info view opened");

        } catch (Exception e) {
            fail("❌ Email ID info view not clickable", e);
        }
    }

    private void closeEmailInfoView() {
        try {
            WebElement closeIcon = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().description(\"Close\")")
                    )
            );

            closeIcon.click();
            ExtentLogger.pass("✅ Email info view closed");

        } catch (Exception e) {
            fail("❌ Close icon not clickable", e);
        }
    }

    private void verifyInfoViewDisplayed() {
        try {
            WebElement infoView = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.view.View[@content-desc='Close']/../android.view.View")
                    )
            );

            softAssert.assertTrue(infoView.isDisplayed(), "❌ Info view not displayed");
            ExtentLogger.pass("✅ Info view displayed");

        } catch (Exception e) {
            fail("❌ Info view not displayed", e);
        }
    }

    private void verifyVerificationLinkText() {
        try {
            WebElement verificationText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().textContains(\"Send a verification link\")")
                    )
            );

            String actual = normalize(verificationText.getText());
            String expected = "Send a verification link to email to verify it.";

            softAssert.assertEquals(actual, expected, "❌ Verification text mismatch");
            ExtentLogger.pass("✅ Verification text verified");

        } catch (Exception e) {
            fail("❌ Verification text not displayed", e);
        }
    }

    private void clickSendLink() {
        try {
            WebElement sendLinkBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Send Link\")")
                    )
            );

            softAssert.assertTrue(sendLinkBtn.isDisplayed(), "❌ Send Link button not displayed");
            sendLinkBtn.click();
            ExtentLogger.pass("✅ Send Link clicked");

        } catch (Exception e) {
            fail("❌ Send Link button not clickable", e);
        }
    }

    private void verifyConfirmationLinkText() {
        try {
            WebElement confirmationText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().textContains(\"Verification link\")")
                    )
            );

            String actual = normalize(confirmationText.getText());
            String expected = "Verification link has been sent to your Email Id";

            softAssert.assertEquals(actual, expected, "❌ Confirmation text mismatch");
            ExtentLogger.pass("✅ Confirmation link text verified");

        } catch (Exception e) {
            fail("❌ Confirmation link text not displayed", e);
        }
    }

    private void clickDone() {
        try {
            WebElement doneBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Done\")")
                    )
            );

            softAssert.assertTrue(doneBtn.isDisplayed(), "❌ Done button not displayed");
            doneBtn.click();
            ExtentLogger.pass("✅ Done clicked");

        } catch (Exception e) {
            fail("❌ Done button not clickable", e);
        }
    }

    private void clickUpdateButton() {
        try {
            WebElement updateBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Update\")")
                    )
            );

            softAssert.assertTrue(updateBtn.isDisplayed(), "❌ Update button not displayed");
            updateBtn.click();
            ExtentLogger.pass("✅ Update button clicked");

        } catch (Exception e) {
            fail("❌ Update button not clickable", e);
        }
    }

    private void verifyConfirmationMessage() {
        try {
            WebElement confirmationMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Are you sure you want to make changes?\")")
                    )
            );

            softAssert.assertEquals(
                    confirmationMsg.getText().trim(),
                    "Are you sure you want to make changes?",
                    "❌ Confirmation message mismatch"
            );

            ExtentLogger.pass("✅ Confirmation message verified");

        } catch (Exception e) {
            fail("❌ Confirmation message not displayed", e);
        }
    }

    private void clickCancelButton() {
        try {
            WebElement cancelBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Cancel\")")
                    )
            );

            softAssert.assertTrue(cancelBtn.isDisplayed(), "❌ Cancel button not displayed");
            cancelBtn.click();
            ExtentLogger.pass("✅ Cancel button clicked");

        } catch (Exception e) {
            fail("❌ Cancel button not clickable", e);
        }
    }

    // ====================== HELPERS ======================

    private String generateRandomEmail() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            username.append(chars.charAt(random.nextInt(chars.length())));
        }
        return username + "@testmail.com";
    }

    private String normalize(String text) {
        return text.replace("\n", " ").replaceAll("\\s+", " ").trim();
    }

    private void navigateBack() {
        driver.navigate().back();
    }

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
