package tests.profile.myscooter;

import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static tests.base.BaseTest.driver;
import static tests.base.BaseTest.wait;

public class EditSecooterName {

    private final SoftAssert softAssert = new SoftAssert();
    private final String scooterName = "Scooter" + System.currentTimeMillis();

    // ====================== FLOW ======================
    public void editSecooterName() {
        ExtentLogger.info("🛵 Edit Scooter Name Flow Started");

        verifyEditNameClicked();
        clickCloseButton();
        verifyEditNameClicked();

        verifyCustomiseScooterNameText();
        verifyScooterNameHelperText();
        verifyAndClickScooterNameLabel();
        enterScooterName();
        verifyScooterNameRuleText();
        verifyAndClickSaveButton();

        sleep(3000);
        verifyScooterNameUpdated();

        // Navigate back to main screen
        driver.navigate().back();
        driver.navigate().back();

        ExtentLogger.info("✅ Edit Scooter Name Flow Completed");

    }

    // ====================== STEPS ======================
    private void verifyEditNameClicked() {
        try {
            WebElement editNameText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Edit Name\")")
                    )
            );

            editNameText.click();
            ExtentLogger.pass("✅ Edit Name clicked");

        } catch (Exception e) {
            fail("❌ Edit Name not clicked", e);
        }
    }

    private void clickCloseButton() {
        try {
            WebElement closeButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().description(\"Close\")")
                    )
            );

            softAssert.assertTrue(closeButton.isDisplayed(), "❌ Close button not displayed");
            closeButton.click();

            ExtentLogger.pass("✅ Close button clicked");

        } catch (Exception e) {
            fail("❌ Close button click failed", e);
        }
    }

    private void verifyCustomiseScooterNameText() {
        try {
            WebElement customiseText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Customise scooter name\")")
                    )
            );

            softAssert.assertEquals(customiseText.getText().trim(),
                    "Customise scooter name",
                    "❌ Customise scooter name text mismatch");

            ExtentLogger.pass("✅ Customise scooter name text verified");

        } catch (Exception e) {
            fail("❌ Customise scooter name text verification failed", e);
        }
    }

    private void verifyScooterNameHelperText() {
        try {
            WebElement helperText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().textContains(\"Your scooter will display this name\")")
                    )
            );

            String actualText = helperText.getText().replace("\n", " ").trim();
            String expectedText = "Your scooter will display this name. Feel free to rename it anytime.";

            softAssert.assertEquals(actualText, expectedText, "❌ Scooter name helper text mismatch");
            ExtentLogger.pass("✅ Scooter name helper text verified");

        } catch (Exception e) {
            fail("❌ Scooter name helper text verification failed", e);
        }
    }

    private void verifyAndClickScooterNameLabel() {
        try {
            WebElement label = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Scooter Name\")")
                    )
            );

            softAssert.assertTrue(label.isDisplayed(), "❌ Scooter Name label not present");
            label.click();
            ExtentLogger.pass("✅ Scooter Name label clicked");

        } catch (Exception e) {
            fail("❌ Scooter Name label verification/click failed", e);
        }
    }

    private void enterScooterName() {
        try {
            WebElement input = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
                    )
            );

            input.click();
            input.clear();
            input.sendKeys(scooterName);

            softAssert.assertTrue(input.isDisplayed(), "❌ Scooter name not entered");
            ExtentLogger.pass("✅ Scooter name entered: " + scooterName);

        } catch (Exception e) {
            fail("❌ Scooter name entry failed", e);
        }
    }

    private void verifyScooterNameRuleText() {
        try {
            WebElement ruleText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Up to 20 characters. Letters and numbers only.\")")
                    )
            );

            softAssert.assertEquals(ruleText.getText().trim(),
                    "Up to 20 characters. Letters and numbers only.",
                    "❌ Scooter name rule text mismatch");

            ExtentLogger.pass("✅ Scooter name rule text verified");

        } catch (Exception e) {
            fail("❌ Scooter name rule text verification failed", e);
        }
    }

    private void verifyAndClickSaveButton() {
        try {
            WebElement saveButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Save\")")
                    )
            );

            softAssert.assertTrue(saveButton.isDisplayed(), "❌ Save button not displayed");
            softAssert.assertEquals(saveButton.getText().trim(), "Save", "❌ Save button text mismatch");

            saveButton.click();
            ExtentLogger.pass("✅ Save button clicked");

        } catch (Exception e) {
            fail("❌ Save button verification/click failed", e);
        }
    }

    private void verifyScooterNameUpdated() {
        try {
            WebElement updatedName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY SCOOTER']/../android.widget.TextView[2]")
                    )
            );

            String actualName = updatedName.getText().trim();
            if (actualName.equals(scooterName)) {
                ExtentLogger.pass("✅ Scooter name updated successfully: " + actualName);
            } else {
                ExtentLogger.fail("❌ Scooter name update failed. Expected: " + scooterName + ", Found: " + actualName);
                softAssert.fail("Scooter name update failed");
            }

        } catch (Exception e) {
            fail("❌ Scooter name update verification failed", e);
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
