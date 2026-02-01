package tests.profile.viewprofile;

import listeners.ExtentLogger;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class LogoutTests extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // @Test
    public void performLogoutFromAnyScreen() {

        ExtentLogger.info("🚪 Logout Flow Started");

        executeLogoutFlow();
        ExtentLogger.info("✅ Logout Flow Completed");
    }

    private void executeLogoutFlow() {
        try {
            // Open Profile
            var profileBtn = wait.until(elementToBeClickable(
                    androidUIAutomator("new UiSelector().description(\"Placeholder\").instance(1)")
            ));
            softAssert.assertTrue(profileBtn.isDisplayed(),
                    "❌ Profile button not displayed");

            profileBtn.click();
            ExtentLogger.pass("Profile button clicked");

            // Scroll to Logout
            var logoutBtn = wait.until(elementToBeClickable(androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"Logout\")"
            )));
            softAssert.assertTrue(logoutBtn.isDisplayed(),
                    "❌ Logout button not displayed");

            logoutBtn.click();
            ExtentLogger.pass("Logout button clicked");

            // Confirm Logout
            var yesBtn = wait.until(elementToBeClickable(
                    androidUIAutomator("new UiSelector().text(\"Yes\")")
            ));
            softAssert.assertTrue(yesBtn.isDisplayed(),
                    "❌ Yes confirmation not displayed");

            yesBtn.click();
            ExtentLogger.pass("Yes button clicked");

        } catch (Exception e) {
            fail("❌ Logout execution failed", e);
        }
    }

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
