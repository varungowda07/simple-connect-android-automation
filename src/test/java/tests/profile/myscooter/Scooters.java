package tests.profile.myscooter;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class Scooters extends BaseTest {
    public void scooters() {
        verifyAndClickMyScooters();
        if(verifyMyScootersTextContains()) {
            tapCloseIcon();
            verifyAndClickMyScooters();
            verifyVehicleNameDisplayed();
            verifyVehicleModelAndColorDisplayed();
            verifyDetailsImageDisplayed();
            verifyAndClickDetailsText();
            driver.navigate().back();
            verifyAndClickMyScooters();
            verifyAndClickEditNameText();
            driver.navigate().back();
        }

    }
    SoftAssert softAssert = new SoftAssert();
    public void verifyAndClickMyScooters() {
        try {
            WebElement myScooters = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"My Scooters\")")
                    )
            );

            softAssert.assertEquals(myScooters.getText().trim(), "My Scooters",
                    "❌ My Scooters text mismatch");

            myScooters.click();
            ExtentLogger.pass("✅ My Scooters clicked successfully");

        } catch (Exception e) {
            fail("❌ My Scooters verification/click failed", e);
        }
    }
    private boolean verifyMyScootersTextContains() {
        try {
            WebElement myScootersText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"My Scooters\")")
                    )
            );

            softAssert.assertTrue(myScootersText.getText().contains("My Scooters"),
                    "❌ Text does not contain 'My Scooters': " + myScootersText.getText());

            ExtentLogger.pass("✅ Text contains: " + myScootersText.getText());
            return true;

        } catch (Exception e) {
            fail("❌ My Scooters text verification failed", e);
            return false;
        }
    }
    private void tapCloseIcon() {
        try {
            WebElement closeIcon = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"Close\")")
                    )
            );

            closeIcon.click();
            ExtentLogger.pass("✅ Close icon tapped successfully");

        } catch (Exception e) {
            fail("❌ Close icon tap failed", e);
        }
    }
    private void verifyVehicleNameDisplayed() {
        try {
            WebElement vehicleName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='My Scooters']/following-sibling::android.view.View/android.view.View/android.widget.TextView[1]")
                    )
            );

            softAssert.assertTrue(vehicleName.isDisplayed(), "❌ Vehicle name is NOT displayed");
            ExtentLogger.pass("✅ Vehicle name is displayed: " + vehicleName.getText());

        } catch (Exception e) {
            fail("❌ Vehicle name verification failed", e);
        }
    }
    private void verifyVehicleModelAndColorDisplayed() {
        try {
            WebElement modelColorText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='My Scooters']/following-sibling::android.view.View/android.view.View/android.widget.TextView[2]")
                    )
            );

            String text = modelColorText.getText().trim();
            softAssert.assertTrue(modelColorText.isDisplayed(), "❌ Vehicle model & color text not displayed");
            softAssert.assertFalse(text.isEmpty(), "❌ Model & Color text is empty");

            // Split by comma → Model, Color
            String[] parts = text.split(",");
            softAssert.assertTrue(parts.length >= 2, "❌ Model and Color not properly formatted: " + text);
            softAssert.assertFalse(parts[0].trim().isEmpty(), "❌ Vehicle model is empty");
            softAssert.assertFalse(parts[1].trim().isEmpty(), "❌ Vehicle color is empty");

            ExtentLogger.pass("✅ Vehicle model and color displayed: " + text);

        } catch (Exception e) {
            fail("❌ Vehicle model & color verification failed", e);
        }
    }
    private void verifyDetailsImageDisplayed() {
        try {
            WebElement detailsImage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='Details']/../../android.widget.ImageView")
                    )
            );

            softAssert.assertTrue(detailsImage.isDisplayed(), "❌ Details image is NOT displayed");
            ExtentLogger.pass("✅ Details image is displayed");

        } catch (Exception e) {
            fail("❌ Details image verification failed", e);
        }
    }
    private void verifyAndClickDetailsText() {
        try {
            WebElement detailsText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"Details\")"
                            )
                    )
            );

            softAssert.assertTrue(detailsText.isDisplayed(), "❌ Details text is NOT displayed");
            ExtentLogger.pass("✅ Details text is displayed");

            detailsText.click();
            ExtentLogger.pass("✅ Details text clicked");

        } catch (Exception e) {
            fail("❌ Details text verification/click failed", e);
        }
    }
    private void verifyAndClickEditNameText() {
        try {
            WebElement editNameText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"Edit Name\")"
                            )
                    )
            );

            softAssert.assertTrue(editNameText.isDisplayed(), "❌ Edit Name text is NOT displayed");
            ExtentLogger.pass("✅ Edit Name text is displayed");

            editNameText.click();
            ExtentLogger.pass("✅ Edit Name text clicked");

        } catch (Exception e) {
            fail("❌ Edit Name text verification/click failed", e);
        }
    }






    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

}
