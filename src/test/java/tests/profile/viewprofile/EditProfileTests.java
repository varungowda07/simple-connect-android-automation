package tests.profile.viewprofile;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class EditProfileTests extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();
    UniversalMethods universalMethods = new UniversalMethods();

    // ======================= FLOW =======================

    public void editProfile() {

        ExtentLogger.info("👤 Edit Profile Screen Validation Started");

        clickAndVerifyEditButton();
        universalMethods.verifyExactText("PROFILE");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Profile Picture\")"),
                "Profile Picture",
                false
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Support\")"),
                "Profile Picture",
                true
        );
        universalMethods.verifyExactText("TICKET STATUS");
        universalMethods.verifyAndClickBackArrow("TICKET");
        verifyFullNameField();
        verifyPincodeField();
        verifyMobileNumberField();
        verifyChangeNoteText();
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Email ID\")"),
                "Email Id Description",
                false
        );
        ExtentLogger.info("✅ Edit Profile Screen Validation Completed");
    }

    // ======================= STEPS =======================

    public void clickAndVerifyEditButton() {
        try {
            WebElement editBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Edit\")")
                    )
            );

            softAssert.assertTrue(editBtn.isDisplayed(),
                    "❌ Edit button not displayed");

            editBtn.click();
            ExtentLogger.pass("✅ Edit button clicked");

        } catch (Exception e) {
            fail("❌ Failed to click Edit button", e);
        }
    }

    private void verifyFullNameField() {
        try {
            WebElement fullNameField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Full Name\")")
                    )
            );

            softAssert.assertTrue(fullNameField.isDisplayed(),
                    "❌ Full Name field not displayed");

            ExtentLogger.pass("✅ Full Name field displayed");

        } catch (Exception e) {
            fail("❌ Full Name field verification failed", e);
        }
    }

    private void verifyPincodeField() {
        try {
            WebElement pincodeField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Pincode\")")
                    )
            );

            softAssert.assertTrue(pincodeField.isDisplayed(),
                    "❌ Pincode field not displayed");

            String pincode = pincodeField.getText().trim();
            softAssert.assertTrue(pincode.matches("\\d{6}"),
                    "❌ Invalid Pincode format: " + pincode);

            ExtentLogger.pass("✅ Pincode validated: " + pincode);

        } catch (Exception e) {
            fail("❌ Pincode verification failed", e);
        }
    }

    private void verifyMobileNumberField() {
        try {
            WebElement mobileField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Mobile Number\")")
                    )
            );

            softAssert.assertTrue(mobileField.isDisplayed(),
                    "❌ Mobile Number field not displayed");

            String mobileText = mobileField.getText().trim();
            softAssert.assertTrue(mobileText.matches("\\+91\\d{10}"),
                    "❌ Invalid Mobile Number: " + mobileText);

            ExtentLogger.pass("✅ Mobile number validated: " + mobileText);

        } catch (Exception e) {
            fail("❌ Mobile Number verification failed", e);
        }
    }

    private void verifyChangeNoteText() {
        try {
            WebElement noteText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().textContains(\"Note:\")")
                    )
            );

            String actual = noteText.getText().trim();
            String expected =
                    "Note: Only once you can change this, To change please connect with the customer support";

            softAssert.assertEquals(actual, expected,
                    "❌ Change note text mismatch");

            ExtentLogger.pass("✅ Change note text verified");

        } catch (Exception e) {
            fail("❌ Change note text verification failed", e);
        }
    }

    // ======================= COMMON FAILURE HANDLER =======================

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
