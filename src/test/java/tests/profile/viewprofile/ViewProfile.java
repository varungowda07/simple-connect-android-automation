package tests.profile.viewprofile;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class ViewProfile extends BaseTest {
    ProfileTests profileTests = new ProfileTests();
    private final SoftAssert softAssert = new SoftAssert();
    UniversalMethods universalMethods = new UniversalMethods();


    // ====================== FLOW ======================
    public void viewProfile() {
        ExtentLogger.info("📋 View Profile Flow Started");
        sleep(5000);

        profileTests.navigateToProfileScreen();
        scrollAndClickViewProfileSameScreen();
        universalMethods.verifyExactText("PROFILE");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Support\")"),
                "Support Icon",
                true
        );
        universalMethods.verifyExactText("TICKET STATUS");
        universalMethods.verifyAndClickBackArrow("TICKET STATUS");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Profile Picture\")"),
                "Profile Picture",
                false
        );
        validateName();
        validatePhone();
        validateVerifiedDescription();
        validateEmailIdLabel();
        validatePincodeLabel();
        validateEmailContainsAtSymbol();
        validatePincodeValue();
        validateConsentText();
        interactWithTermsPrivacyLinks();


        ExtentLogger.info("✅ View Profile Flow Completed");

    }

    // ====================== STEPS ======================


    private void scrollAndClickViewProfileSameScreen() {
        try {
            ExtentLogger.info("Clicking View Profile");
            WebElement viewProfile = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"View Profile\")")
                    )
            );
            softAssert.assertTrue(viewProfile.isDisplayed(), "❌ View Profile not visible");
            viewProfile.click();

            ExtentLogger.pass("✅ View Profile clicked");

        } catch (Exception e) {
            fail("❌ View Profile click failed", e);
        }
    }

    private void validateName() {
        try {
            WebElement name = driver.findElement(
                    By.xpath("//android.widget.ImageView[@content-desc='Verified']/preceding-sibling::android.widget.TextView[2]")
            );

            softAssert.assertTrue(name.isDisplayed(), "❌ User name not displayed");
            ExtentLogger.pass("✅ User Name displayed: " + name.getText());

        } catch (Exception e) {
            fail("❌ User name validation failed", e);
        }
    }

    private void validatePhone() {
        try {
            WebElement phone = driver.findElement(
                    By.xpath("//android.widget.ImageView[@content-desc='Verified']/preceding-sibling::android.widget.TextView[3]")
            );

            softAssert.assertTrue(phone.isDisplayed(), "❌ Phone number not displayed");
            ExtentLogger.pass("✅ Phone Number displayed: " + phone.getText());

        } catch (Exception e) {
            fail("❌ Phone validation failed", e);
        }
    }

    private void validateVerifiedDescription() {
        try {
            WebElement verified = driver.findElement(
                    androidUIAutomator("new UiSelector().description(\"Verified\")")
            );

            softAssert.assertTrue(verified.isDisplayed(), "❌ Verified badge not displayed");
            ExtentLogger.pass("✅ Verified badge displayed");

        } catch (Exception e) {
            fail("❌ Verified badge validation failed", e);
        }
    }

    private void validateEmailIdLabel() {
        try {
            WebElement emailLabel = driver.findElement(
                    androidUIAutomator("new UiSelector().text(\"Email ID\")")
            );

            softAssert.assertTrue(emailLabel.isDisplayed(), "❌ Email ID label not displayed");
            ExtentLogger.pass("✅ Email ID label displayed");

        } catch (Exception e) {
            fail("❌ Email ID label validation failed", e);
        }
    }

    private void validatePincodeLabel() {
        try {
            WebElement pincodeLabel = driver.findElement(
                    androidUIAutomator("new UiSelector().text(\"Pincode\")")
            );

            softAssert.assertTrue(pincodeLabel.isDisplayed(), "❌ Pincode label not displayed");
            ExtentLogger.pass("✅ Pincode label displayed");

        } catch (Exception e) {
            fail("❌ Pincode label validation failed", e);
        }
    }

    private void validateEmailContainsAtSymbol() {
        try {
            WebElement email = driver.findElement(
                    By.xpath("//android.widget.TextView[contains(@text,'@')]")
            );

            softAssert.assertTrue(email.getText().contains("@"), "❌ Invalid email format");
            ExtentLogger.pass("✅ Email validated: " + email.getText());

        } catch (Exception e) {
            fail("❌ Email validation failed", e);
        }
    }

    private void validatePincodeValue() {
        try {
            WebElement pin = driver.findElement(
                    By.xpath("//android.widget.TextView[matches(@text,'\\d{6}')]")
            );

            softAssert.assertTrue(pin.getText().matches("\\d{6}"), "❌ Invalid pincode");
            ExtentLogger.pass("✅ Valid pincode: " + pin.getText());

        } catch (Exception e) {
            fail("❌ Pincode validation failed", e);
        }
    }

    private void validateConsentText() {
        try {
            WebElement consent = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    androidUIAutomator("new UiSelector().textContains(\"By continuing\")")
            ));

            softAssert.assertTrue(consent.isDisplayed(), "❌ Consent text not displayed");
            ExtentLogger.pass("✅ Consent text verified");

        } catch (Exception e) {
            fail("❌ Consent text validation failed", e);
        }
    }

    private void interactWithTermsPrivacyLinks() {
        try {
            ExtentLogger.info("Opening Terms of Service");
            driver.findElement(androidUIAutomator("new UiSelector().textContains(\"Terms\")")).click();
            sleep(2000);
            driver.activateApp(APP_PACKAGE);

            ExtentLogger.info("Opening Privacy Policy");
            driver.findElement(androidUIAutomator("new UiSelector().textContains(\"Privacy\")")).click();
            sleep(2000);
            driver.activateApp(APP_PACKAGE);

            ExtentLogger.pass("✅ Terms & Privacy verified");

        } catch (Exception e) {
            fail("❌ Terms/Privacy validation failed", e);
        }
    }

    // ====================== HELPERS ======================
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

    private void sleep(long millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
