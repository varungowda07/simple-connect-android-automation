//package tests.login;
//
//import io.appium.java_client.AppiumBy;
//import listeners.ExtentLogger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Rectangle;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.PointerInput;
//import org.openqa.selenium.interactions.Sequence;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//import tests.base.BaseTest;
//import utils.ScreenshotUtil;
//
//import java.time.Duration;
//import java.util.List;
//
//import static io.appium.java_client.AppiumBy.androidUIAutomator;
//
//public class SignInScreenTests extends BaseTest {
//    SoftAssert softAssert = new SoftAssert();
//
//    @Test
//    public void testSignInScreen() {
//        ExtentLogger.info("🔐 SignIn screen test started");
//
//        verifyWelcomeScreenIsDisplayed();
//        verifyLogoIsDisplayed();
//        verifyConsentTextAndOpenLinks();
//        signInButton();
//        driver.navigate().back();
//        signInButton();
//
//        ExtentLogger.info("✅ Sign In Screen test completed");
//    }
//
//    /** ✅ SCREENSHOT FIRST PATTERN - Used everywhere */
//    private void safeScreenshotAndFail(String testName, String errorMsg) {
//        String screenshot = ScreenshotUtil.capture(driver, testName);
//        ExtentLogger.fail(errorMsg + (screenshot != null ? " [Screenshot: " + screenshot + "]" : ""));
//    }
//
//    private void verifyWelcomeScreenIsDisplayed() {
//        try {
//            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    By.xpath("//android.widget.TextView[@text=\"Sign in\"]/../../../../android.view.View[1]")
//            ));
//
//            softAssert.assertTrue(logo.isDisplayed(), "❌ Welcome image NOT displayed");
//            ExtentLogger.pass("✅ Welcome image displayed");
//
//        } catch (Exception e) {
//            safeScreenshotAndFail("welcome_image_missing", "❌ Welcome image not found: " + e.getMessage());
//            softAssert.fail("Welcome image verification failed");
//        }
//    }
//
//    private void verifyLogoIsDisplayed() {
//        try {
//            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    By.xpath("//android.widget.TextView[@text='Sign in']/../../android.view.View[1]")
//            ));
//
//            softAssert.assertTrue(logo.isDisplayed(), "❌ Logo NOT displayed");
//            ExtentLogger.pass("✅ Logo displayed");
//
//        } catch (Exception e) {
//            safeScreenshotAndFail("logo_missing", "❌ Logo not found: " + e.getMessage());
//            softAssert.fail("Logo verification failed");
//        }
//    }
//
//    private void signInButton() {
//        try {
//            var signInBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                    androidUIAutomator("new UiSelector().text(\"Sign in\")")));
//
//            softAssert.assertTrue(signInBtn.isDisplayed(), "❌ Sign In button NOT visible");
//            signInBtn.click();
//            ExtentLogger.pass("✅ Sign In button clicked");
//
//        } catch (Exception e) {
//            safeScreenshotAndFail("signin_button_missing", "❌ SignIn button failed: " + e.getMessage());
//            softAssert.fail("SignIn button verification failed");
//        }
//    }
//
//    private void verifyConsentTextAndOpenLinks() {
//        WebElement consentText = null;
//
//        // 1️⃣ Locate consent text (UiAutomator SAFE)
//        try {
//            consentText = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    androidUIAutomator("new UiSelector().textContains(\"By continuing\")")
//            ));
//        } catch (Exception e) {
//            safeScreenshotAndFail("consent_text_missing", "❌ Consent text not found: " + e.getMessage());
//            return;
//        }
//
//        // 2️⃣ Verify text content
//        try {
//            String actualText = consentText.getText().trim();
//            String expectedText = "By continuing , I agree to the \n Terms of Service and consent to the Privacy Policy";
//
//            softAssert.assertEquals(actualText, expectedText, "❌ Consent text mismatch");
//            ExtentLogger.pass("✅ Consent text verified: " + actualText);
//        } catch (Exception e) {
//            safeScreenshotAndFail("consent_text_mismatch", "❌ Consent text mismatch: " + e.getMessage());
//            return;
//        }
//
//        // 3️⃣ Test clickable links (Atomic operations)
//        Rectangle rect = consentText.getRect();
//        testTermsLink(rect);
//        testPrivacyLink(rect);
//    }
//
//    private void testTermsLink(Rectangle rect) {
//        try {
//            int termsX = rect.getX() + (rect.getWidth() / 4);
//            int centerY = rect.getY() + (rect.getHeight() * 3 / 4);
//
//            tapByCoordinates(termsX, centerY);
//            sleep(2000); // Reduced from 4000ms
//
//            softAssert.assertTrue(driver.getPageSource().contains("Terms"), "❌ Terms page NOT opened");
//            verifyTermsAndConditionsText();
//            driver.navigate().back();
//            sleep(1000);
//
//            ExtentLogger.pass("✅ Terms of Service link verified");
//
//        } catch (Exception e) {
//            safeScreenshotAndFail("terms_link_failed", "❌ Terms link failed: " + e.getMessage());
//        }
//    }
//
//    private void testPrivacyLink(Rectangle rect) {
//        try {
//            int privacyX = rect.getX() + (rect.getWidth() * 3 / 4);
//            int centerY = rect.getY() + (rect.getHeight() * 3 / 4);
//
//            tapByCoordinates(privacyX, centerY);
//            sleep(2000);
//
//            softAssert.assertTrue(driver.getPageSource().contains("Privacy"), "❌ Privacy page NOT opened");
//            verifyPrivacyPolicyText();
//            driver.navigate().back();
//            sleep(1000);
//
//            ExtentLogger.pass("✅ Privacy Policy link verified");
//
//        } catch (Exception e) {
//            safeScreenshotAndFail("privacy_link_failed", "❌ Privacy link failed: " + e.getMessage());
//        }
//    }
//
//    private void verifyTermsAndConditionsText() {
//        try {
//            WebElement termsText = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    androidUIAutomator("new UiSelector().textContains(\"Terms\")")
//            ));
//
//            String actualText = termsText.getText().trim();
//            softAssert.assertTrue(actualText.contains("Terms"), "❌ Terms text missing");
//            ExtentLogger.pass("✅ Terms page verified");
//
//        } catch (Exception e) {
//            safeScreenshotAndFail("terms_page_missing", "❌ Terms page failed: " + e.getMessage());
//        }
//    }
//
//    private void verifyPrivacyPolicyText() {
//        try {
//            WebElement privacyText = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    androidUIAutomator("new UiSelector().textContains(\"Privacy\")")
//            ));
//
//            String actualText = privacyText.getText().trim();
//            softAssert.assertTrue(actualText.contains("Privacy"), "❌ Privacy text missing");
//            ExtentLogger.pass("✅ Privacy page verified");
//
//        } catch (Exception e) {
//            safeScreenshotAndFail("privacy_page_missing", "❌ Privacy page failed: " + e.getMessage());
//        }
//    }
//
//    private void tapByCoordinates(int x, int y) {
//        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//        Sequence tap = new Sequence(finger, 1);
//        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
//        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//        driver.perform(List.of(tap));
//    }
//
//    private void sleep(long millis) {
//        try {
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//}
//
//package tests.login;
//
//import io.appium.java_client.AppiumBy;
//import listeners.ExtentLogger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Rectangle;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.PointerInput;
//import org.openqa.selenium.interactions.Sequence;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
//import org.testng.asserts.SoftAssert;
//import tests.base.BaseTest;
//import utils.ScreenshotUtil;
//
//import java.time.Duration;
//import java.util.List;
//
//import static io.appium.java_client.AppiumBy.androidUIAutomator;
//
//public class SignInScreenTests extends BaseTest {
//
//    private SoftAssert softAssert = new SoftAssert();
//    public void testSignInScreen() {
//        ExtentLogger.info("SignIn screen started..");
//        verifyWelcomeScreenIsDisplayed();
//        verifyLogoIsDisplayed();
//        verifyConsentTextAndOpenLinks();
//        signInButton();
//        driver.navigate().back();
//        signInButton();
//        ExtentLogger.info("Sign In Screen ended");
//    }
//    private void verifyWelcomeScreenIsDisplayed() {
//        try {
//            WebElement logo = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath(
//                                    "    //android.widget.TextView[@text=\"Sign in\"]/../../../../android.view.View[1]"
//                            )
//                    )
//            );
//            softAssert.assertTrue(
//                    logo.isDisplayed(),
//                    "❌ Image is NOT displayed"
//            );
//            if(logo.isDisplayed()){
//                ExtentLogger.pass("Image is displayed");
//            }
//            else {
//                sleep(2000);
//                String screen = ScreenshotUtil.capture(driver,"Image not displayed");
//                ExtentLogger.failWithScreenshot("hi",screen);
//                ExtentLogger.fail("Image not found or not visible: ");
//            }
//
//        } catch (Exception e) {
//           String screen =  ScreenshotUtil.capture(driver,"Image not displayed");
//            ExtentLogger.fail("Image not found or not visible: " + e.getMessage()+screen);
//            softAssert.fail("Image verification failed", e);
//        }
//    }
//    public void signInButton() {
//        try {
//            var signInBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                    androidUIAutomator("new UiSelector().text(\"Sign in\")")));
//
//            Assert.assertTrue(signInBtn.isDisplayed(), "Sign In button NOT visible");
//            signInBtn.click();
//        } catch (Exception e) {
//            sleep(2000);
//           String screen= ScreenshotUtil.capture(driver,"SignIn btn not displayed");
//            ExtentLogger.fail("SignInBtn Not Found " + e.getMessage()+screen);
//            softAssert.fail("SignIn btn not displayed");
//        }
//    }
//    public void verifyConsentTextAndOpenLinks() {
//
//        // 1️⃣ Locate consent TextView
//        WebElement consentText = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        AppiumBy.androidUIAutomator(
//                                "new UiSelector().textContains(\"By continuing , I agree\")"
//                        )
//                )
//        );
//        // 2️⃣ Verify exact displayed text (newline-aware)
//        String actualText = consentText.getText().trim();
//        String expectedText = "By continuing , I agree to the \n Terms of Service and consent to the Privacy Policy";
//
//        softAssert.assertEquals(
//                actualText,
//                expectedText,
//                "❌ Consent text mismatch"
//        );
//        if(actualText.equals(expectedText)) {
//            System.out.println(actualText+" --> "+expectedText);
//            ExtentLogger.pass("✅ Consent text verified");
//        }
//        else {
//            sleep(2000);
//            String screen = ScreenshotUtil.capture(driver,"Consent text verification failed");
//            ExtentLogger.fail("Consent text verification failed"+screen);
//        }
//        // 3️⃣ Get element bounds
//        Rectangle rect = consentText.getRect();
//        int startX = rect.getX();
//        int startY = rect.getY();
//        int width = rect.getWidth();
//        int height = rect.getHeight();
//        int centerY = startY + (height * 3 / 4);
//
//        // ================= CLICK TERMS OF SERVICE =================
//        try {
//            int termsX = startX + (width / 4);
//            tapByCoordinates(termsX, centerY);
//
//            ExtentLogger.pass("✅ Clicked on Terms of Service");
//            sleep(4000);
//
//            // VERIFY navigation happened (URL / WebView / Title check)
//            softAssert.assertTrue(
//                    driver.getPageSource().contains("Terms"),
//                    "❌ Terms of Service page NOT opened"
//            );
//            verifyTermsAndConditionsText();
//            driver.navigate().back();
//        } catch (Exception e) {
//            sleep(2000);
//           String screen = ScreenshotUtil.capture(driver,"Failed to open Terms of Service");
//            ExtentLogger.fail("❌ Failed to open Terms of Service: " + e.getMessage()+screen);
//            softAssert.fail("Terms of Service click failed", e);
//        }
//
//// ================= CLICK PRIVACY POLICY =================
//        try {
//            int privacyX = startX + (width * 3 / 4);
//            tapByCoordinates(privacyX, centerY);
//
//            ExtentLogger.pass("✅ Clicked on Privacy Policy");
//            sleep(4000);
//
//            // VERIFY navigation happened
//            softAssert.assertTrue(
//                    driver.getPageSource().contains("Privacy"),
//                    "❌ Privacy Policy page NOT opened"
//            );
//            verifyPrivacyPolicyText();
//            driver.navigate().back();
//
//        } catch (Exception e) {
//            sleep(2000);
//            ScreenshotUtil.capture(driver,"Failed to open Privacy Policy");
//            ExtentLogger.fail("Failed to open Privacy Policy: " + e.getMessage());
//            softAssert.fail("Privacy Policy click failed", e);
//        }
//    }
//    private void verifyTermsAndConditionsText() {
//        try {
//            WebElement termsText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.androidUIAutomator(
//                                    "new UiSelector().text(\"Terms & Conditions\")"
//                            )
//                    )
//            );
//
//            String actualText = termsText.getText().trim();
//            String expectedText = "Terms & Conditions";
//
//            softAssert.assertEquals(
//                    actualText,
//                    expectedText,
//                    "❌ Terms and Conditions text mismatch"
//            );
//            if(actualText.equals(expectedText)) {
//                ExtentLogger.pass("✅ Terms and Conditions page verified");
//            }
//            else {
//                sleep(2000);
//               String screen = ScreenshotUtil.capture(driver,"Terms and Conditions text page found");
//                ExtentLogger.fail("❌ Terms and Conditions page not found: "+screen );
//
//            }
//
//        } catch (Exception e) {
//            ScreenshotUtil.capture(driver,"Terms and Conditions text page found");
//            ExtentLogger.fail("❌ Terms and Conditions text page found: " + e.getMessage());
//            softAssert.fail("Terms and Conditions verification failed"+ e.getMessage());
//        }
//    }
//    private void verifyPrivacyPolicyText() {
//        try {
//            WebElement privacyPolicyText = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            AppiumBy.androidUIAutomator(
//                                    "new UiSelector().text(\"Privacy Policy\")"
//                            )
//                    )
//            );
//
//            // Get actual text
//            String actualText = privacyPolicyText.getText().trim();
//            String expectedText = "Privacy Policy";
//
//            // Verify text
//            softAssert.assertEquals(
//                    actualText,
//                    expectedText,
//                    "❌ Privacy Policy text mismatch"
//            );
//            if(actualText.equals(expectedText)){
//                ExtentLogger.pass("✅ Privacy Policy page verified successfully");
//
//            }
//            else {
//                sleep(2000);
//                ScreenshotUtil.capture(driver,"Privacy Policy page verification failed");
//                ExtentLogger.fail("❌ Privacy Policy page not found or mismatch: " );
//
//            }
//
//
//        } catch (Exception e) {
//            ExtentLogger.fail("❌ Privacy Policy page not found or mismatch: " + e.getMessage());
//            String screen = ScreenshotUtil.capture(driver,"Privacy Policy page verification failed");
//            softAssert.fail("Privacy Policy text verification failed"+e.getMessage()+screen);
//        }
//    }
//    private void tapByCoordinates(int x, int y) {
//
//        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//
//        Sequence tap = new Sequence(finger, 1);
//        tap.addAction(finger.createPointerMove(
//                Duration.ZERO,
//                PointerInput.Origin.viewport(),
//                x,
//                y
//        ));
//        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//
//        driver.perform(List.of(tap));
//    }
//    private void verifyLogoIsDisplayed() {
//        try {
//            WebElement logo = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(
//                            By.xpath(
//                                    "//android.widget.TextView[@text='Sign in']/../../android.view.View[1]"
//                            )
//                    )
//            );
//
//            softAssert.assertTrue(
//                    logo.isDisplayed(),
//                    "❌ Logo is NOT displayed"
//            );
//            if(logo.isDisplayed()) {
//                ExtentLogger.pass("✅ Logo is displayed");
//            }
//            else {
//                sleep(2000);
////                ScreenshotUtil.capture(driver,"Logo not found or not visible");
//                ExtentLogger.fail("Logo not found or not visible: ");
//            }
//
//        } catch (Exception e) {
//            ScreenshotUtil.capture(driver,"Logo not found or not visible");
//            ExtentLogger.fail("❌ Logo not found or not visible: " + e.getMessage());
//            softAssert.fail("Logo verification failed", e);
//        }
//    }
//    private void sleep(long millis) {
//        try {
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//}
package tests.login;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import java.time.Duration;
import java.util.List;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class SignInScreenTests extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // ====================== TEST ENTRY ======================
    public void testSignInScreen() {
        ExtentLogger.info("🔐 Sign In screen test started");

        verifyWelcomeImage();
        verifyLogo();
        verifyConsentTextAndLinks();
        clickSignInButton();
        safeBack();
        clickSignInButton();
        ExtentLogger.info("✅ Sign In screen test completed");
    }

    // ====================== VERIFICATIONS ======================

    private void verifyWelcomeImage() {
        try {
            WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text='Sign in']/../../../../android.view.View[1]")
            ));
            softAssert.assertTrue(image.isDisplayed(), "❌ Welcome image not displayed");
            ExtentLogger.pass("✅ Welcome image displayed");

        } catch (Exception e) {
            fail("❌ Welcome image not visible", e);
        }
    }

    private void verifyLogo() {
        try {
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text='Sign in']/../../android.view.View[1]")
            ));

            softAssert.assertTrue(logo.isDisplayed(), "❌ Logo not displayed");
            ExtentLogger.pass("✅ Logo displayed");

        } catch (Exception e) {
            fail("❌ Logo not visible", e);
        }
    }

    private void clickSignInButton() {
        try {
            WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    androidUIAutomator("new UiSelector().text(\"Sign in\")")
            ));

            signInBtn.click();
            ExtentLogger.pass("✅ Sign In button clicked");

        } catch (Exception e) {
            fail("❌ Sign In button not clickable", e);
        }
    }

    private void verifyConsentTextAndLinks() {

        WebElement consentText;
        try {
            consentText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"By continuing, I agree to the\")")
            ));
        } catch (Exception e) {
            fail("❌ Consent text not found", e);
            return;
        }

        // ---------- Consent Text ----------
        try {
            String actual = consentText.getText().trim();
            String expected = "By continuing , I agree to the \n Terms of Service and consent to the Privacy Policy";

            softAssert.assertEquals(actual, expected, "❌ Consent text mismatch");
            ExtentLogger.pass("✅ Consent text verified");

        } catch (Exception e) {
            fail("❌ Consent text mismatch", e);
        }

        // ---------- Tap Links ----------
        Rectangle rect = consentText.getRect();
        int centerY = rect.getY() + (rect.getHeight() * 3 / 4);

        clickTermsLink(rect.getX() + (rect.getWidth() / 4), centerY);
        clickPrivacyLink(rect.getX() + (rect.getWidth() * 3 / 4), centerY);
    }

    private void clickTermsLink(int x, int y) {
        try {
            tap(x, y);
            ExtentLogger.pass("✅ Clicked Terms of Service");

            verifyTermsPage();
            safeBack();

        } catch (Exception e) {
            fail("❌ Terms of Service link failed", e);
        }
    }

    private void clickPrivacyLink(int x, int y) {
        try {
            tap(x, y);
            ExtentLogger.pass("✅ Clicked Privacy Policy");

            verifyPrivacyPage();
            safeBack();

        } catch (Exception e) {
            fail("❌ Privacy Policy link failed", e);
        }
    }

    private void verifyTermsPage() {
        try {
            WebElement terms = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    androidUIAutomator("new UiSelector().text(\"Terms & Conditions\")")
            ));

            softAssert.assertEquals(terms.getText().trim(), "Terms & Conditions",
                    "❌ Terms page title mismatch");

            ExtentLogger.pass("✅ Terms & Conditions page verified");

        } catch (Exception e) {
            fail("❌ Terms & Conditions page verification failed", e);
        }
    }

    private void verifyPrivacyPage() {
        try {
            WebElement privacy = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    androidUIAutomator("new UiSelector().text(\"Privacy Policy\")")
            ));

            softAssert.assertEquals(privacy.getText().trim(), "Privacy Policy",
                    "❌ Privacy page title mismatch");

            ExtentLogger.pass("✅ Privacy Policy page verified");

        } catch (Exception e) {
            fail("❌ Privacy Policy page verification failed", e);
        }
    }

    // ====================== CORE UTILITIES ======================

    private void tap(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(tap));
    }

    public void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}



