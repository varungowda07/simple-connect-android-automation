package tests.settings.mygarage;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

public class MyGarage extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    public void myGarage() {
        ExtentLogger.info("MyGarage screen test started");
        if(verifySettingsIconIsDisplayed()) {
            if(clickBackArrowFromMyGarage()) {
                verifySettingsIconIsDisplayed();
            }
            verifyMyGarageTextDisplayed();
            if(verifyScooterManualDisplayed()) {
                driver.navigate().back();
            }
            verifyScooterNameDisplayed();
            verifyScooterModelAndColorDisplayed();
            verifyIndImageDisplayedBesideRegistration();
            verifyRegistrationNumberDisplayed();
            verifyVehicleImageDisplayed();
            if(verifyVehicleCountDisplayed()) {
                verifySwipeToSwitchVehicleTextDisplayed();
            }

        }



    }

    public boolean verifySettingsIconIsDisplayed() {
        try {
            WebElement settingsIcon = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"Settings\")")
                    )
            );

            softAssert.assertTrue(settingsIcon.isDisplayed(), "❌ Settings icon is NOT displayed");
            settingsIcon.click();
            ExtentLogger.pass("✅ Settings icon is displayed and clicked");
            return true;

        } catch (Exception e) {
            fail("❌ Settings icon verification/click failed", e);
            return false;
        }
    }
    private boolean clickBackArrowFromMyGarage() {
        try {
            WebElement backArrow = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.TextView[@text='MY GARAGE']/preceding-sibling::android.view.View")
                    )
            );

            softAssert.assertTrue(backArrow.isDisplayed(), "❌ Back arrow is NOT displayed");
            backArrow.click();
            ExtentLogger.pass("✅ Back arrow clicked successfully");
            return true;

        } catch (Exception e) {
            fail("arrow click failedBack: " ,e);
            return false;
        }
    }
    private void verifyMyGarageTextDisplayed() {
        try {
            WebElement myGarageText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().text(\"MY GARAGE\")")
                    )
            );

            softAssert.assertTrue(myGarageText.isDisplayed(), "❌ 'MY GARAGE' text is NOT displayed");
            ExtentLogger.pass("✅ 'MY GARAGE' text is displayed");

        } catch (Exception e) {
            fail("❌ 'MY GARAGE' text verification failed", e);
        }
    }
    private boolean verifyScooterManualDisplayed() {
        try {
            WebElement scooterManual = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY GARAGE']/following-sibling::android.view.View[1]")
                    )
            );

            softAssert.assertTrue(scooterManual.isDisplayed(), "❌ Scooter Manual is NOT displayed");
            scooterManual.click();
            ExtentLogger.pass("✅ Scooter Manual is displayed");
            return true;

        } catch (Exception e) {
            fail("❌ Scooter Manual verification failed", e);
            return false;
        }
    }
    private void verifyScooterNameDisplayed() {
        try {
            WebElement scooterName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY GARAGE']/following-sibling::android.widget.TextView[1]")
                    )
            );

            softAssert.assertTrue(scooterName.isDisplayed(), "❌ Scooter name is NOT displayed");
            ExtentLogger.pass("✅ Scooter name is displayed: " + scooterName.getText());

        } catch (Exception e) {
            fail("❌ Scooter name verification failed", e);
        }
    }
    private void verifyScooterModelAndColorDisplayed() {
        try {
            WebElement modelColorText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY GARAGE']/following-sibling::android.widget.TextView[2]")
                    )
            );

            String text = modelColorText.getText().trim();
            String[] parts = text.split(","); // model + color

            softAssert.assertTrue(parts.length == 2, "❌ Model and Color not properly formatted: " + text);
            softAssert.assertFalse(parts[0].trim().isEmpty(), "❌ Model is empty");
            softAssert.assertFalse(parts[1].trim().isEmpty(), "❌ Color is empty");

            ExtentLogger.pass("✅ Model displayed: " + parts[0] + ", Color displayed: " + parts[1]);

        } catch (Exception e) {
            fail("❌ Model & Color verification failed", e);
        }
    }
    private void verifyIndImageDisplayedBesideRegistration() {
        try {
            WebElement indImage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.ScrollView/android.view.View[1]/android.view.View[3]/android.view.View")
                    )
            );

            softAssert.assertTrue(indImage.isDisplayed(), "❌ IND image is NOT displayed beside registration number");
            ExtentLogger.pass("✅ IND image is displayed beside registration number");

        } catch (Exception e) {
            fail("❌ IND image verification failed", e);
        }
    }
    private void verifyRegistrationNumberDisplayed() {
        try {
            WebElement registrationText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY GARAGE']/following-sibling::android.view.View[2]/android.widget.TextView")
                    )
            );

            softAssert.assertTrue(registrationText.isDisplayed(), "❌ Registration number is NOT displayed");
            ExtentLogger.pass("✅ Registration number is displayed: " + registrationText.getText());

        } catch (Exception e) {
            fail("❌ Registration number verification failed", e);
        }
    }
    private void verifyVehicleImageDisplayed() {
        try {
            WebElement vehicleImage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY GARAGE']/following-sibling::android.view.View[3]")
                    )
            );

            softAssert.assertTrue(vehicleImage.isDisplayed(), "❌ Vehicle image is NOT displayed");
            ExtentLogger.pass("✅ Vehicle image is displayed");

        } catch (Exception e) {
            fail("❌ Vehicle image verification failed", e);
        }
    }
    private boolean verifyVehicleCountDisplayed() {
        try {
            WebElement vehicleCount = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY GARAGE']/following-sibling::android.widget.TextView[3]")
                    )
            );

            String text = vehicleCount.getText().trim(); // e.g., "1/3"

            // Verify format X/Y
            softAssert.assertTrue(text.matches("\\d+/\\d+"),
                    "❌ Vehicle count format invalid: " + text);

            String[] parts = text.split("/");
            int selected = Integer.parseInt(parts[0]);
            int total = Integer.parseInt(parts[1]);

            // Logical validation
            softAssert.assertTrue(selected >= 1, "❌ Selected vehicle index invalid: " + selected);
            softAssert.assertTrue(total >= 1, "❌ Total vehicle count invalid: " + total);
            softAssert.assertTrue(selected <= total,
                    "❌ Selected vehicle index greater than total: " + text);

            ExtentLogger.pass("✅ Vehicle count displayed correctly: " + text);
            return true;

        } catch (Exception e) {
            fail("❌ Vehicle count verification failed", e);
            return false;
        }
    }
    private void verifySwipeToSwitchVehicleTextDisplayed() {
        try {
            WebElement swipeText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"Swipe to switch\")"
                            )
                    )
            );

            softAssert.assertTrue(swipeText.isDisplayed(),
                    "❌ 'Swipe to switch vehicle' text is NOT displayed");

            ExtentLogger.pass("✅ 'Swipe to switch vehicle' text is displayed");

        } catch (Exception e) {
            fail("❌ 'Swipe to switch vehicle' text verification failed", e);
        }
    }











    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
