package tests.settings.tyrepressure;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.settings.mygarage.MyGarage;
import tests.utils.UniversalMethods;

public class TPMS extends BaseTest {

    MyGarage myGarage = new MyGarage();
    SoftAssert softAssert = new SoftAssert();
    UniversalMethods universalMethods = new UniversalMethods();

    // ================= FLOW =================
    public void tpms() {
        myGarage.verifySettingsIconIsDisplayed();
        sleep(3000);
        universalMethods.verifyExactText("TYRE PRESSURE");
        universalMethods.verifyExactText("This is your ideal tyre pressure to maintained");
        verifyTyrePressureValues(
                By.xpath("//android.widget.ImageView[@content-desc=\"Rider\"]/following-sibling::android.widget.TextView")
        );
        verifyTyrePressureValues(
                By.xpath("//android.widget.ImageView[@content-desc=\"Rider with pillion\"]/following-sibling::android.widget.TextView")
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Rider\")"),
                "Tpms Rider Icon",
                false
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Rider with pillion\")"),
                "Tpms Rider with pillion Icon",
                false
        );
        universalMethods.scrollToText("Air Stick");
        universalMethods.verifyExactText("Air Stick");
        universalMethods.verifyTextContains(
                "Stop guessing your tyre PSI, Get AirStick & see real-time PSI.",
                "Stop guessing your tyre PSI,"
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Info\")"),
                "Info Description",
                false
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Air Stick\")"),
                "Air Stick Description",
                false
        );
    }

    private void verifyTyrePressureValues(By locator) {
        try {
            WebElement tyrePressure = wait.until(
                    ExpectedConditions.elementToBeClickable(locator)
            );

            String text = tyrePressure.getText().trim(); // e.g. "F 30, R 32"

            // ✅ Format validation
            softAssert.assertTrue(text.matches("F \\d+, R \\d+"),
                    "❌ Invalid tyre pressure format: " + text);

            // ✅ Extract values
            String[] values = text.replace("F ", "").replace("R ", "").split(",");
            int front = Integer.parseInt(values[0].trim());
            int rear = Integer.parseInt(values[1].trim());

            // ✅ Range validation
            softAssert.assertTrue(front > 0 && front <= 32,
                    "❌ Front tyre pressure out of range: " + front);
            softAssert.assertTrue(rear > 0 && rear <= 32,
                    "❌ Rear tyre pressure out of range: " + rear);

            ExtentLogger.pass("✅ tyre pressure validated — F: " + front + ", R: " + rear);

        } catch (Exception e) {
            fail("❌ tyre pressure validation failed", e);
        }
    }
    public void sleep(long sec) {
        try {
            Thread.sleep(sec);
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
