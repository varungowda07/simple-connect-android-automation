package tests.profile.viewprofile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import listeners.ExtentLogger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

import java.util.Map;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class EditProfilePhoto extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();
    UniversalMethods universalMethods = new UniversalMethods();

    // ====================== FLOW ======================

    public void editProfilePhoto() {

        ExtentLogger.info("📸 Edit Profile Photo Flow Started");

        clickEditPhoto();
        verifyEditProfilePictureText();
        clickCloseIcon();
        clickEditPhoto();
        clickTakePhoto();
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"While using the app\")"),
                "While Using App",
                true
        );
        takePhoto();


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
    public void takePhotoByTap() {
        // Tap CENTER of screen (camera button usually there)
        Dimension size = driver.manage().window().getSize();
        int centerX = size.width / 2;
        int centerY = size.height * 3 / 4;  // Bottom-center (shutter position)

        new TouchAction(driver)
                .tap(PointOption.point(centerX, centerY))
                .perform();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // Confirm photo
        try {
            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            driver.findElement(AppiumBy.accessibilityId("Done")).click();
            driver.findElement(AppiumBy.accessibilityId("Tick")).click();
            driver.findElement(AppiumBy.accessibilityId("Confirm")).click();
        } catch (Exception ignored) {
            safeBack();
            safeBack();
        }
    }


    // ====================== HELPERS ======================
    public void takePhoto() {
        try {
            universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.myos.camera:id/photo_shutter_button_photo\")"),
                            "Shutter click",
                            true);

            driver.findElement(AppiumBy.accessibilityId("OK")).click();
            driver.findElement(AppiumBy.accessibilityId("Done")).click();
            driver.findElement(AppiumBy.accessibilityId("Tick")).click();
            driver.findElement(AppiumBy.accessibilityId("Confirm")).click();
            driver.findElement(AppiumBy.accessibilityId("Take photo")).click();


        } catch (Exception ignored) {
            safeBack();
            safeBack();
        }
    }


    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
