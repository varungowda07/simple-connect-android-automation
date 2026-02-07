package tests.utils;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;


public class UniversalMethods extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    public void verifyExactText(String expectedText) {
        try {
            WebElement element = wait.until(driver ->
                    driver.findElement(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().text(\"" + expectedText + "\")"
                            )
                    )
            );

            String actualText = element.getText().trim();

            softAssert.assertEquals(actualText, expectedText,
                    "❌ Text mismatch. Expected: " + expectedText + " | Actual: " + actualText);

            ExtentLogger.pass("✅ Text verified successfully: " + actualText);
            System.out.println(actualText +" text found");

        } catch (Exception e) {
            System.out.println(expectedText +" text found");
            fail("❌ Text verification failed for: " + expectedText, e);
        }
    }
    public void verifyTextContains(String expected, String locatorText) {
        try {
            WebElement element = wait.until(driver ->
                    driver.findElement(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"" + locatorText + "\")"
                            )
                    )
            );

            String actual = element.getText().trim();

            softAssert.assertTrue(actual.contains(expected),
                    "❌ Expected text to contain: \"" + expected + "\" but found: \"" + actual + "\"");

            ExtentLogger.pass("✅ Text verified. Actual: " + actual);
            System.out.println(actual +" text found");

        } catch (Exception e) {
            System.out.println(expected +" text found");
            fail("❌ Text contains verification failed for: " + expected, e);
        }
    }
    public void verifyTextContainsByIndex(String expected, String containsText, int index) {
        try {
            WebElement element = wait.until(driver ->
                    driver.findElements(
                            By.xpath("//android.widget.TextView[contains(@text,'" + containsText + "')]")
                    ).get(index)
            );

            String actual = element.getText().trim();

            softAssert.assertTrue(actual.contains(expected),
                    "❌ Expected: " + expected + " but found: " + actual);

            ExtentLogger.pass("✅ Text verified at index " + index + ": " + actual);

        } catch (Exception e) {
            fail("❌ Text verification failed " + expected, e);
        }
    }
    public void verifyAndClickBackArrow(String name) {
        try {
            WebElement backArrow = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[contains(@text,'" + name + "')]/preceding-sibling::android.view.View")
                    )
            );

            softAssert.assertTrue(backArrow.isDisplayed(), "❌ Back arrow NOT displayed");
            backArrow.click();
            ExtentLogger.pass("✅ Back arrow clicked");

        } catch (Exception e) {
            fail("❌ Back arrow verification/click failed", e);
        }
    }
    public void verifyAndClickButtonByTextContains(String expectedText) {
        try {
            WebElement button = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.Button[contains(@text,'" + expectedText + "')] | " +
                                    "//android.widget.TextView[contains(@text,'" + expectedText + "')]")
                    )
            );

            String actual = button.getText().trim();
            softAssert.assertTrue(actual.contains(expectedText),
                    "❌ Expected button text containing: " + expectedText + " but found: " + actual);

            button.click();
            ExtentLogger.pass("✅ Button verified and clicked: " + actual);

        } catch (Exception e) {
            fail("❌ Button verification/click failed for: " + expectedText, e);
        }
    }
    public boolean verifyAndClick(By locator, String elementName,boolean click) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(locator)
            );

            softAssert.assertTrue(element.isDisplayed(),
                    "❌ " + elementName + " is NOT displayed");
            if(!click) {
                ExtentLogger.pass("✅ " + elementName + " displayed");
            }
            if(click) {
                element.click();
                ExtentLogger.pass("✅ " + elementName + " verified and clicked");

            }
            System.out.println(elementName +" found");
            return true;

        } catch (Exception e) {
            fail("❌ " + elementName + " verification/click failed", e);
            System.out.println(elementName +" not found");
            return false;
        }
    }
    public void scrollToText(String text) {
        try {
            driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true))" +
                                    ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"
                    )
            );
            ExtentLogger.pass("✅ Scrolled to text: " + text);

        } catch (Exception e) {
            fail("❌ Scroll to text failed: " + text, e);
        }
    }
    public void scrollUpOnce() {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()"
                )
        );
    }public void scrollDownOnce() {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
                )
        );
    }




    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }


}


    // ================= COMMON TEXT CHECK =================


