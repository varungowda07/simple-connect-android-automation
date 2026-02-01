package tests.profile.viewprofile;

import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class EditProfileTests extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // ======================= FLOW =======================

    public void editProfile() {

        ExtentLogger.info("👤 Edit Profile Screen Validation Started");

        clickAndVerifyEditButton();
        verifyEditPhotoImage();
        clickAndVerifySupportIcon();
        driver.navigate().back();
        verifyFullNameField();
        verifyPincodeField();
        verifyMobileNumberField();
        verifyChangeNoteText();
        verifyAndValidateEmailId();
        verifyEmailIdIconDisplayed();

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

    private void verifyEditPhotoImage() {
        try {
            WebElement editPhotoImage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='Edit Photo']/preceding-sibling::android.view.View[3]")
                    )
            );

            softAssert.assertTrue(editPhotoImage.isDisplayed(),
                    "❌ Edit Photo image not displayed");

            ExtentLogger.pass("✅ Edit Photo image displayed");

        } catch (Exception e) {
            fail("❌ Edit Photo image not displayed", e);
        }
    }

    private void clickAndVerifySupportIcon() {
        try {
            WebElement supportIcon = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.TextView[@text='Edit Photo']/preceding-sibling::android.view.View[2]")
                    )
            );

            softAssert.assertTrue(supportIcon.isDisplayed(),
                    "❌ Support icon not displayed");

            supportIcon.click();
            ExtentLogger.pass("✅ Support icon clicked");

        } catch (Exception e) {
            fail("❌ Failed to click Support icon", e);
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
                            androidUIAutomator("new UiSelector().text(\"Mobile number\")")
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

    private void verifyAndValidateEmailId() {
        try {
            WebElement emailLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Email ID\")")
                    )
            );

            softAssert.assertTrue(emailLabel.isDisplayed(),
                    "❌ Email ID label not displayed");

            WebElement emailValue = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='Email ID']/following-sibling::android.widget.TextView")
                    )
            );

//            String email = emailValue.getText().trim();
//            softAssert.assertTrue(
//                    email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"),
//                    "❌ Invalid Email ID: " + email
//            );
//
//            ExtentLogger.pass("✅ Email validated: " + email);

        } catch (Exception e) {
            fail("❌ Email ID verification failed", e);
        }
    }

    private void verifyEmailIdIconDisplayed() {
        try {
            WebElement emailIcon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='Email ID']/following-sibling::android.view.View")
                    )
            );

            softAssert.assertTrue(emailIcon.isDisplayed(),
                    "❌ Email ID icon not displayed");

            ExtentLogger.pass("✅ Email ID icon displayed");

        } catch (Exception e) {
            fail("❌ Email ID icon verification failed", e);
        }
    }

    // ======================= COMMON FAILURE HANDLER =======================

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
