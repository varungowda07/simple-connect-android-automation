package tests.login;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class GetStartedTests extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // ====================== TEST ENTRY ======================
    public void testGetStartedScreen() {
        ExtentLogger.info("🚀 Get Started Screen Test Started");

        verifySimpleLogoIsDisplayed();
        verifyHeroImageDisplayed();
        verifyWelcomeText();
        verifyScooterText();
        verifyShareLocationText();
        clickGetStartedButton();
        ExtentLogger.info("✅ Get Started Screen Test Completed");
    }

    // ====================== VALIDATIONS ======================

    private void verifySimpleLogoIsDisplayed() {
        try {
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text=\"Get Started\"]/../../android.view.View[1]")
            ));

            softAssert.assertTrue(logo.isDisplayed(), "❌ Simple logo not displayed");
            ExtentLogger.pass("✅ Simple logo displayed");

        } catch (Exception e) {
            fail("❌ Simple logo not found or not visible", e);
        }
    }

    private void verifyHeroImageDisplayed() {
        try {
            WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text=\"Get Started\"]/../../android.view.View[2]")
            ));

            softAssert.assertTrue(image.isDisplayed(), "❌ Hero image not displayed");
            ExtentLogger.pass("✅ Hero image displayed");

        } catch (Exception e) {
            fail("❌ Hero image not found or not visible", e);
        }
    }

    private void verifyWelcomeText() {
        try {
            WebElement welcomeText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text=\"Get Started\"]/../../android.widget.TextView[1]")
            ));

            softAssert.assertEquals(welcomeText.getText().trim(),
                    "Welcome\nSimple!",
                    "❌ Welcome text mismatch");

            ExtentLogger.pass("✅ Welcome text verified");

        } catch (Exception e) {
            fail("❌ Welcome text not found or incorrect", e);
        }
    }

    private void verifyScooterText() {
        try {
            WebElement scooterText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    androidUIAutomator("new UiSelector().text(\"Take complete control of your Super Scooter.\")")
            ));

            softAssert.assertEquals(scooterText.getText().trim(),
                    "Take complete control of your Super Scooter.",
                    "❌ Scooter text mismatch");

            ExtentLogger.pass("✅ Scooter text verified");

        } catch (Exception e) {
            fail("❌ Scooter text not found or incorrect", e);
        }
    }

    private void verifyShareLocationText() {
        try {
            WebElement shareText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    androidUIAutomator("new UiSelector().text(\"Share location, personalise your ride and do more right from the app.\")")
            ));

            softAssert.assertEquals(shareText.getText().trim(),
                    "Share location, personalise your ride and do more right from the app.",
                    "❌ Share location text mismatch");

            ExtentLogger.pass("✅ Share location text verified");

        } catch (Exception e) {
            fail("❌ Share location text not found or incorrect", e);
        }
    }

    public void clickGetStartedButton() {
        try {
            WebElement getStartedBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Get Started\")")
            ));

            softAssert.assertTrue(getStartedBtn.isDisplayed(), "❌ Get Started button not displayed");
            getStartedBtn.click();

            ExtentLogger.pass("✅ Get Started button clicked");

        } catch (Exception e) {
            fail("❌ Get Started button not clickable", e);
        }
    }

    // ====================== UTIL ======================

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
