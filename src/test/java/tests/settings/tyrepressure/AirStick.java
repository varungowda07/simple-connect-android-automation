package tests.settings.tyrepressure;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class AirStick extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    UniversalMethods universalMethods = new UniversalMethods();
    public void airStick() {
        if (universalMethods.verifyAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Info\"]/preceding-sibling::android.view.View[3]"),
                "Air Stick Greater than icon ",
                true)) {
            universalMethods.verifyExactText("AIRSTICK");
            if (universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Contact Support\")"),
                    "Contact Support ICon",
                    true)) {
                universalMethods.verifyTextContains("TICKET STATUS","TICKET");
                universalMethods.verifyAndClickBackArrow("TICKET");
            }
            universalMethods.verifyTextContains(
                    "Maintain optimal tyre pressure for maximum range & Performance",
                    "Maintain optimal tyre pressure"
            );
            verifyTextAndIcon("OPTIMUM");
            verifyTextAndIcon("LOW/HIGH");
            verifyTextAndIcon("CRITICAL");
            verifyRiderAndPillowIcon("Laden.");
            verifyRiderAndPillowIcon("Unladen.");
            verifyLadenTyrePressureValue(30, 32, "Laden.");
            verifyLadenTyrePressureValue(30, 30, "Unladen.");
            universalMethods.verifyTextContains(
                    "Lower/Higher the tyre pressure can reduce the range and performance",
                    "Lower/Higher the tyre pressure can reduce"
            );
            universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Info\")"),
                            "Info Iocn",
                    false
            );
            universalMethods.verifyAndClickBackArrow("AIRSTICK");
            driver.navigate().back();
        }
    }
    private void verifyTextAndIcon(String text) {
        universalMethods.verifyExactText(text);
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().descriptionContains('" + text + "')"),
                text+" Icon ",
                false
        );
    }
    private void verifyRiderAndPillowIcon(String name) {
        universalMethods.verifyExactText(name);
        universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text='" +name+ "']/preceding-sibling::android.view.View[2]"),
                name+" Icon",
                false
        );
    }

    private void verifyLadenTyrePressureValue(int maxF, int maxR, String name) {
        try {
            WebElement pressureText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='" + name + "']/following-sibling::android.widget.TextView[1]")
                    )
            );

            String text = pressureText.getText().trim(); // F 30, R 32

            softAssert.assertTrue(text.matches("F \\d+, R \\d+"),
                    "❌ Invalid pressure format: " + text);

            String[] parts = text.replace("F ", "").replace("R ", "").split(",");
            int front = Integer.parseInt(parts[0].trim());
            int rear = Integer.parseInt(parts[1].trim());

            softAssert.assertTrue(front > 0 && front <= maxF,
                    "❌ Front pressure out of range: " + front);
            softAssert.assertTrue(rear > 0 && rear <= maxR,
                    "❌ Rear pressure out of range: " + rear);

            ExtentLogger.pass("✅ " + name + " tyre pressure validated: " + text);

        } catch (Exception e) {
            fail("❌ " + name + " tyre pressure verification failed", e);
        }
    }
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
