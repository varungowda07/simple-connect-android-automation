package tests.profile.viewprofile;

import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import java.util.Random;

public class EditPincode extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // ====================== FLOW ======================

    public void editPincode() {

        ExtentLogger.info("📍 Edit Pincode Flow Started");

        enterAndVerifyRandomPincode();
        driver.navigate().back();

        ExtentLogger.info("✅ Edit Pincode Flow Completed");
    }

    // ====================== STEPS ======================

    private void enterAndVerifyRandomPincode() {
        try {
            WebElement pincodeInput = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.TextView[@text='Pincode']/../android.widget.EditText")
                    )
            );

            pincodeInput.click();
            pincodeInput.clear();

            String randomPincode = generateRandomPincode();
            pincodeInput.sendKeys(randomPincode);

            ExtentLogger.pass("✅ Entered Pincode: " + randomPincode);

            softAssert.assertEquals(
                    pincodeInput.getText().trim(),
                    randomPincode,
                    "❌ Entered pincode mismatch"
            );

        } catch (Exception e) {
            fail("❌ Pincode entry or verification failed", e);
        }
    }

    // ====================== HELPERS ======================

    private String generateRandomPincode() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
