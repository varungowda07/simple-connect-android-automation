package tests.profile.viewprofile;

import listeners.ExtentLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class EditProfilePhoto extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // ====================== FLOW ======================

    public void editProfilePhoto() {

        ExtentLogger.info("📸 Edit Profile Photo Flow Started");

        clickEditPhoto();
        verifyEditProfilePictureText();
        clickCloseIcon();
        clickEditPhoto();
        clickTakePhoto();
        if(clickShutterButton()) {
            clickOkButton();
        }
        else {
            driver.navigate().back();
        }


        ExtentLogger.info("✅ Edit Profile Photo Flow Completed");
    }

    // ====================== STEPS ======================

    private void clickEditPhoto() {
        try {
            WebElement editPhoto = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Edit Photo\")")
                    )
            );

            softAssert.assertTrue(editPhoto.isDisplayed(),
                    "❌ Edit Photo is not displayed");

            editPhoto.click();
            ExtentLogger.pass("✅ Edit Photo clicked");

        } catch (Exception e) {
            fail("❌ Failed to click Edit Photo", e);
        }
    }

    private void verifyEditProfilePictureText() {
        try {
            WebElement editProfileText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Edit Profile Picture\")")
                    )
            );

            softAssert.assertEquals(
                    editProfileText.getText().trim(),
                    "Edit Profile Picture",
                    "❌ Edit Profile Picture text mismatch"
            );

            ExtentLogger.pass("✅ Edit Profile Picture text verified");

        } catch (Exception e) {
            fail("❌ Edit Profile Picture text not found", e);
        }
    }

    private void clickCloseIcon() {
        try {
            WebElement closeIcon = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().description(\"Close\")")
                    )
            );

            softAssert.assertTrue(closeIcon.isDisplayed(),
                    "❌ Close icon not displayed");

            closeIcon.click();
            ExtentLogger.pass("✅ Close icon clicked");

        } catch (Exception e) {
            fail("❌ Failed to click Close icon", e);
        }
    }

    private void clickTakePhoto() {
        try {
            WebElement takePhoto = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Take a Photo\")")
                    )
            );

            softAssert.assertEquals(
                    takePhoto.getText().trim(),
                    "Take a Photo",
                    "❌ Take a Photo text mismatch"
            );

            takePhoto.click();
            ExtentLogger.pass("✅ Take a Photo clicked");

        } catch (Exception e) {
            fail("❌ Failed to click Take a Photo", e);
        }
    }

    private boolean clickShutterButton() {
        try {
            WebElement shutterButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator(
                                    "new UiSelector().resourceId(\"com.sec.android.app.camera:id/normal_center_button\")"
                            )
                    )
            );

            softAssert.assertTrue(shutterButton.isDisplayed(),
                    "❌ Shutter button not displayed");

            shutterButton.click();
            ExtentLogger.pass("✅ Shutter button clicked");
            return true;

        } catch (Exception e) {
            fail("❌ Failed to click Shutter button", e);
            return false;
        }
    }

    private void clickOkButton() {
        try {
            WebElement okButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"OK\")")
                    )
            );

            softAssert.assertTrue(okButton.isDisplayed(),
                    "❌ OK button not displayed");

            okButton.click();
            ExtentLogger.pass("✅ OK button clicked");

        } catch (Exception e) {
            fail("❌ Failed to click OK button", e);
        }
    }

    // ====================== HELPERS ======================

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
