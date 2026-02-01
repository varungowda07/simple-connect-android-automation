package tests.profile.viewprofile;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class ProfileTests extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // ====================== FLOW ======================
    public void profile() {
        ExtentLogger.info("📋 Profile screen validations started");

        navigateToProfileScreen();
        validateProfileImage();
        validateProfileText();
        validateGeneralSettingsText();
        validateSupportAndCommunityText();
        scrollAndValidateLogoNearVersion();
        validateAppVersionText();
        validateCompanyNameText();

        driver.navigate().back();
        sleep(2000);

        ExtentLogger.info("✅ Profile screen validations completed");

    }

    // ====================== STEPS ======================

    public void navigateToProfileScreen() {
        try {
            WebElement profileBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().description(\"Placeholder\").instance(1)")
                    )
            );

            softAssert.assertTrue(profileBtn.isDisplayed(), "❌ Profile button is not displayed");

            profileBtn.click();
            ExtentLogger.pass("✅ Profile screen navigated");

        } catch (Exception e) {
            fail("❌ Profile screen navigation failed", e);
        }
    }

    private void validateProfileImage() {
        try {
            WebElement image = driver.findElement(AppiumBy.className("android.widget.ImageView"));

            softAssert.assertTrue(image.isDisplayed(), "❌ Profile image is not displayed");
            ExtentLogger.pass("✅ Profile image validated");

        } catch (Exception e) {
            fail("❌ Profile image not found", e);
        }
    }

    private void validateProfileText() {
        try {
            WebElement textElement = driver.findElement(
                    By.xpath("//android.widget.TextView[@text='View Profile']/../preceding-sibling::android.widget.TextView")
            );

            softAssert.assertTrue(textElement.isDisplayed(), "❌ Text near 'View Profile' is not displayed");
            ExtentLogger.pass("✅ Profile text found: " + textElement.getText());

        } catch (Exception e) {
            fail("❌ Text near 'View Profile' not found", e);
        }
    }

    private void validateGeneralSettingsText() {
        try {
            WebElement generalSettings = driver.findElement(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"GENERAL SETTINGS\")")
            );

            softAssert.assertTrue(generalSettings.isDisplayed(), "❌ GENERAL SETTINGS text not displayed");
            ExtentLogger.pass("✅ GENERAL SETTINGS text displayed");

        } catch (Exception e) {
            fail("❌ GENERAL SETTINGS text not found", e);
        }
    }

    private void validateSupportAndCommunityText() {
        try {
            WebElement supportCommunity = wait.until(driver -> driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true))" +
                                    ".scrollTextIntoView(\"SUPPORT & COMMUNITY\")"
                    )
            ));

            softAssert.assertTrue(supportCommunity.isDisplayed(),
                    "❌ SUPPORT & COMMUNITY text not displayed");

            ExtentLogger.pass("✅ SUPPORT & COMMUNITY text displayed");

        } catch (Exception e) {
            fail("❌ SUPPORT & COMMUNITY text not found", e);
        }
    }


    private void scrollAndValidateLogoNearVersion() {
        try {
            WebElement logoElement = driver.findElement(
                    By.xpath("//android.widget.TextView[contains(@text,'Version')]/preceding-sibling::android.view.View[last()]")
            );

            softAssert.assertTrue(logoElement.isDisplayed(), "❌ Logo near Version text not displayed");
            ExtentLogger.pass("✅ Logo near Version text displayed");

        } catch (Exception e) {
            fail("❌ Logo near Version text not found", e);
        }
    }

    private void validateAppVersionText() {
        try {
            WebElement versionText = driver.findElement(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Version 2.0.0\")")
            );

            softAssert.assertTrue(versionText.isDisplayed(), "❌ App version 'Version 2.0.0' not displayed");
            ExtentLogger.pass("✅ App version displayed: " + versionText.getText());

        } catch (Exception e) {
            fail("❌ App version 'Version 2.0.0' not found", e);
        }
    }

    private void validateCompanyNameText() {
        try {
            WebElement companyName = driver.findElement(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Simpleenergy Private Limited\")")
            );

            softAssert.assertTrue(companyName.getText().contains("Simpleenergy"), "❌ Company name text not displayed");
            ExtentLogger.pass("✅ Company name displayed: " + companyName.getText());

        } catch (Exception e) {
            fail("❌ Company name not found", e);
        }
    }

    // ====================== HELPERS ======================
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
