package tests.profile.myscooter;

import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.profile.viewprofile.ProfileTests;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static tests.base.BaseTest.driver;
import static tests.base.BaseTest.wait;

public class MyScooter {

    private final ProfileTests profileTests = new ProfileTests();
    private final SoftAssert softAssert = new SoftAssert();

    // ====================== FLOW ======================
    public void myScooter() {
        ExtentLogger.info("🛵 My Scooter Flow Started");

        profileTests.navigateToProfileScreen();
        verifyMyScootersIconDisplayed();
        verifyMyScootersIconArrow();
//        verifyAndClickMyScooters();

        ExtentLogger.info("✅ My Scooter Flow Completed");
    }

    // ====================== STEPS ======================
    private void verifyMyScootersIconDisplayed() {
        try {
            WebElement icon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='My Scooters']/../android.view.View[1]")
                    )
            );

            softAssert.assertTrue(icon.isDisplayed(), "❌ My Scooters icon not displayed");
            ExtentLogger.pass("✅ My Scooters icon displayed");

        } catch (Exception e) {
            fail("❌ My Scooters icon verification failed", e);
        }
    }

    private void verifyMyScootersIconArrow() {
        try {
            WebElement arrow = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='My Scooters']/../android.view.View[2]")
                    )
            );

            softAssert.assertTrue(arrow.isDisplayed(), "❌ My Scooters arrow not displayed");
            ExtentLogger.pass("✅ My Scooters arrow displayed");

        } catch (Exception e) {
            fail("❌ My Scooters arrow verification failed", e);
        }
    }
    // ====================== HELPERS ======================
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

}
