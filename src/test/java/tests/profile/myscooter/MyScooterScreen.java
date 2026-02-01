package tests.profile.myscooter;

import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static tests.base.BaseTest.driver;
import static tests.base.BaseTest.wait;

public class MyScooterScreen {

    private final SoftAssert softAssert = new SoftAssert();

    // ====================== FLOW ======================
    public void myScooterScreen() {
        ExtentLogger.info("🛵 My Scooter Screen Flow Started");

        verifyMyScooterText();
        verifyVehicleNameIsPresent();
        verifyVehicleModelIsPresent();
        verifyEditNameTextIsPresent();
        verifyVehicleImageIsDisplayed();
        verifyVinTextIsPresent();
        verifyVinNumberIsDisplayed();
        verifyRegistrationTextIsPresent();
        verifyRegistrationNumberIsDisplayed();
        verifySoftwareTextIsPresent();
        verifySoftwareVersionNumberIsDisplayed();
        verifyWarrantyTextIsPresent();
        verifyWarrantyNumberIsDisplayed();

        ExtentLogger.info("✅ My Scooter Screen Flow Completed");
    }

    // ====================== STEPS ======================
    private void verifyMyScooterText() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"MY SCOOTER\")")
                    )
            );

            softAssert.assertEquals(element.getText().trim(), "MY SCOOTER", "❌ MY SCOOTER text mismatch");
            ExtentLogger.pass("✅ Verified MY SCOOTER text: " + element.getText().trim());

        } catch (Exception e) {
            fail("❌ MY SCOOTER text verification failed", e);
        }
    }

    private void verifyVehicleNameIsPresent() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY SCOOTER']/../android.widget.TextView[2]")
                    )
            );

            softAssert.assertFalse(element.getText().trim().isEmpty(), "❌ Vehicle name is NOT present");
            ExtentLogger.pass("✅ Vehicle name is present: " + element.getText().trim());

        } catch (Exception e) {
            fail("❌ Vehicle name verification failed", e);
        }
    }

    private void verifyVehicleModelIsPresent() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY SCOOTER']/../android.widget.TextView[3]")
                    )
            );

            softAssert.assertFalse(element.getText().trim().isEmpty(), "❌ Vehicle model is NOT present");
            ExtentLogger.pass("✅ Vehicle model is present: " + element.getText().trim());

        } catch (Exception e) {
            fail("❌ Vehicle model verification failed", e);
        }
    }

    private void verifyEditNameTextIsPresent() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Edit Name\")")
                    )
            );

            softAssert.assertTrue(element.isDisplayed(), "❌ Edit Name text is NOT present");
            ExtentLogger.pass("✅ Edit Name text is present");

        } catch (Exception e) {
            fail("❌ Edit Name text verification failed", e);
        }
    }

    private void verifyVehicleImageIsDisplayed() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.ScrollView/android.view.View[3]/../android.view.View[3]")
                    )
            );

            softAssert.assertTrue(element.isDisplayed(), "❌ Vehicle image is NOT displayed");
            ExtentLogger.pass("✅ Vehicle image is displayed");

        } catch (Exception e) {
            fail("❌ Vehicle image verification failed", e);
        }
    }

    private void verifyVinTextIsPresent() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"VIN\")")
                    )
            );

            softAssert.assertTrue(element.isDisplayed(), "❌ VIN text is NOT present");
            ExtentLogger.pass("✅ VIN text is present");

        } catch (Exception e) {
            fail("❌ VIN text verification failed", e);
        }
    }

    private void verifyVinNumberIsDisplayed() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='VIN']/../android.widget.TextView[2]")
                    )
            );

            softAssert.assertFalse(element.getText().trim().isEmpty(), "❌ VIN number is NOT displayed");
            ExtentLogger.pass("✅ VIN number is displayed: " + element.getText().trim());

        } catch (Exception e) {
            fail("❌ VIN number verification failed", e);
        }
    }

    private void verifyRegistrationTextIsPresent() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Registration Number\")")
                    )
            );

            softAssert.assertTrue(element.isDisplayed(), "❌ Registration Number text is NOT present");
            ExtentLogger.pass("✅ Registration Number text is present");

        } catch (Exception e) {
            fail("❌ Registration Number text verification failed", e);
        }
    }

    private void verifyRegistrationNumberIsDisplayed() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='Registration Number']/../android.widget.TextView[2]")
                    )
            );

            softAssert.assertFalse(element.getText().trim().isEmpty(), "❌ Registration Number is NOT displayed");
            ExtentLogger.pass("✅ Registration Number is displayed: " + element.getText().trim());

        } catch (Exception e) {
            fail("❌ Registration Number verification failed", e);
        }
    }

    private void verifySoftwareTextIsPresent() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Software Version\")")
                    )
            );

            softAssert.assertTrue(element.isDisplayed(), "❌ Software Version text is NOT present");
            ExtentLogger.pass("✅ Software Version text is present");

        } catch (Exception e) {
            fail("❌ Software Version text verification failed", e);
        }
    }

    private void verifySoftwareVersionNumberIsDisplayed() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='Software Version']/../android.widget.TextView[2]")
                    )
            );

            softAssert.assertFalse(element.getText().trim().isEmpty(), "❌ Software Version number is NOT displayed");
            ExtentLogger.pass("✅ Software Version number is displayed: " + element.getText().trim());

        } catch (Exception e) {
            fail("❌ Software Version number verification failed", e);
        }
    }

    private void verifyWarrantyTextIsPresent() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Warranty\")")
                    )
            );

            softAssert.assertTrue(element.isDisplayed(), "❌ Warranty text is NOT present");
            ExtentLogger.pass("✅ Warranty text is present");

        } catch (Exception e) {
            fail("❌ Warranty text verification failed", e);
        }
    }

    private void verifyWarrantyNumberIsDisplayed() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='Warranty']/../android.widget.TextView[2]")
                    )
            );

            softAssert.assertFalse(element.getText().trim().isEmpty(), "❌ Warranty number is NOT displayed");
            ExtentLogger.pass("✅ Warranty number is displayed: " + element.getText().trim());

        } catch (Exception e) {
            fail("❌ Warranty number verification failed", e);
        }
    }

    // ====================== HELPERS ======================
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
