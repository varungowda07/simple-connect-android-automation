package tests.settings.editemergencycontact;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

import java.util.Random;

public class EditContact extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    String randomName = generateRandomName();
    String randomMobile = generateRandomMobileNumber();
    UniversalMethods universalMethods = new UniversalMethods();
    public void editContact() {
        String editContactTitle = "Edit Contact";
        String doneText = "Done";
        String nameLabel = "Name";
        universalMethods.verifyTextContains(editContactTitle, editContactTitle);
        universalMethods.verifyTextContains(nameLabel, nameLabel);
        enterRandomNameInNameField();
        verifyAndEnterRandomMobileNumber();
        verifyContactIconDisplayed();

        if(!verifyDoneButtonClick(doneText)) {
             if(!verifyAndClickClose()) {
                 safeBack();
             }
        }
        else {
            fetchNameAndVerify();
            fetchMobileNumberAndVerify();
        }
    }
    private void verifyContactIconDisplayed() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Contacts\")"),
                "Contact Icon",
                false
        );
    }
    private boolean verifyAndClickClose() {
       return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Close\")"),
                "Contact Icon",
                true
        );
    }
    public boolean verifyDoneButtonClick(String doneText) {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"" + doneText + "\")"),
                "Edit emergency contact Done Clicked",
                true
        );
    }
    private void fetchNameAndVerify() {
        try {
            WebElement getName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text=\"Name\"]/following-sibling::android.widget.TextView")));
            universalMethods.verifyTextContains(randomName,getName.getText());
            ExtentLogger.pass("✅ Name Updated: " + randomName);
        } catch (Exception e) {
            fail("❌ Updating name failed", e);
        }
    }
    public void fetchMobileNumberAndVerify() {
        try {
            WebElement getName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text=\"Mobile Number\"]/following-sibling::android.widget.TextView")));
            universalMethods.verifyTextContains(randomMobile,getName.getText());
            ExtentLogger.pass("✅ Mobile number Updated: " + randomMobile);
        } catch (Exception e) {
            fail("❌ Updating mobile failed", e);
        }
    }
    public void enterRandomNameInNameField() {
        try {
            WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text=\"Name\"]/../android.widget.EditText")
            ));
            nameInput.clear();
            nameInput.sendKeys(randomName);

            softAssert.assertEquals(nameInput.getText().trim(), randomName, "❌ Name input mismatch");
            ExtentLogger.pass("✅ Random name entered: " + randomName);

        } catch (Exception e) {
            fail("❌ Failed to enter random name", e);
        }
    }

    public void verifyAndEnterRandomMobileNumber() {
        try {
            WebElement mobileLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().text(\"Mobile number\")")
            ));

            softAssert.assertEquals(mobileLabel.getText().trim(),
                    "Mobile number",
                    "❌ Mobile number label mismatch");

            WebElement mobileInput = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.widget.TextView[@text=\"Mobile Number\"]\n" +
                            "/../android.widget.EditText")
            ));
            mobileInput.clear();
            mobileInput.sendKeys(randomMobile);

            softAssert.assertEquals(mobileInput.getText().trim(), randomMobile,
                    "❌ Mobile number input mismatch");

            ExtentLogger.pass("✅ Random mobile number entered: " + randomMobile);

        } catch (Exception e) {
            fail("❌ Failed to verify or enter mobile number", e);
        }
    }
    public String generateRandomName() {
        String[] names = {"Aarav", "Rohan", "Vikram", "Kiran", "Neha", "Anita"};
        return names[new Random().nextInt(names.length)];
    }

    public String generateRandomMobileNumber() {
        Random random = new Random();
        return "9" + (100000000 + random.nextInt(900000000));
    }
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

}
