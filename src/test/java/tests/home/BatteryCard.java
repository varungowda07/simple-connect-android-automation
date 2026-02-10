package tests.home;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class BatteryCard extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    UniversalMethods universalMethods = new UniversalMethods();
    public void batteryCard() {
        verifyBatteryTextIsDisplayed("BATTERY");
        verifyLastChargedTextIsDisplayed();
        verifyBatteryBtnDisplayed();
        verifyBatteryPercentageDisplayed();
        verifyChargingHistoryButtonDisplayed();
        verifyCompanyNameDisplayed();
        verifySimpleByDesignViewDisplayed();
        verifyAtSymbolDisplayedBesideCompanyName();

    }
    private void verifyBatteryTextIsDisplayed(String text) {
        universalMethods.scrollDownOnce();
        universalMethods.verifyTextContains(text,"BATTERY");
    }

    private void verifyLastChargedTextIsDisplayed() {
        try {
            WebElement lastChargedText = driver.findElement(
                    AppiumBy.androidUIAutomator("new UiSelector().textContains(\"last charged to\")")
            );

            softAssert.assertTrue(lastChargedText.isDisplayed(), "❌ 'last charged to' text is NOT displayed");
            ExtentLogger.pass("✅ 'last charged to' text is displayed: " + lastChargedText.getText());

        } catch (Exception e) {
            fail("❌ 'last charged to' text verification failed", e);
        }
    }

    private void verifyBatteryBtnDisplayed() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text=\"BATTERY \"]/../android.view.View/android.view.View\n")
                    )
            );

            softAssert.assertTrue(element.isDisplayed(), "❌ batteryBtn is NOT displayed");
            ExtentLogger.pass("✅ batteryBtn is displayed");

        } catch (Exception e) {
            fail("❌ batteryBtn verification failed", e);
        }
    }

    private void verifyBatteryPercentageDisplayed() {
        try {
            WebElement batteryPercentage = driver.findElement(
                    By.xpath("(//android.widget.TextView[contains(@text,'%')])[2]\n")
            );

            softAssert.assertTrue(batteryPercentage.isDisplayed(), "❌ Battery percentage is NOT displayed");
            ExtentLogger.pass("✅ Battery percentage is displayed: " + batteryPercentage.getText());

        } catch (Exception e) {
            fail("❌ Battery percentage verification failed", e);
        }
    }

    private void verifyChargingHistoryButtonDisplayed() {
        try {
            WebElement chargingHistoryBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\")")
                    )
            );

            softAssert.assertTrue(chargingHistoryBtn.isDisplayed(), "❌ Charging History button is NOT displayed");
            softAssert.assertEquals(chargingHistoryBtn.getText(), "Charging History", "❌ Button text mismatch");

            ExtentLogger.pass("✅ Charging History button is displayed with text: " + chargingHistoryBtn.getText());

        } catch (Exception e) {
            fail("❌ Charging History button verification failed", e);
        }
    }
    private void verifySimpleByDesignViewDisplayed() {
       universalMethods.verifyAndClick(
               AppiumBy.androidUIAutomator("new UiSelector().description(\"Simple Design\")"),
               "verifySimpleByDesignViewDisplayed",
               false
       );
    }

    private void verifyCompanyNameDisplayed() {
        universalMethods.scrollDownOnce();
        universalMethods.verifyExactText("Simpleenergy Private Limited");
    }

    private void verifyAtSymbolDisplayedBesideCompanyName() {
       universalMethods.verifyAndClick(AppiumBy.androidUIAutomator(
               "new UiSelector().description(\"Copyrights\")"),
               "verifyCopyRightSymbol",
               false
       );
    }

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
