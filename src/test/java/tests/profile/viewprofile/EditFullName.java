package tests.profile.viewprofile;

import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;

import java.util.Random;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class EditFullName extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    // ====================== FLOW ======================

    public void editFullName() {

        ExtentLogger.info("👤 Edit Full Name Flow Started");

        openFullNameField();
        closeEditNameScreen();
        openFullNameField();
        verifyEditNameTitle();
        verifyFirstNameLabel();
        enterRandomFirstName();
        verifySecondNameLabel();
        enterRandomSecondName();
        clickDoneButton();

        ExtentLogger.info("✅ Edit Full Name Flow Completed");
    }

    // ====================== STEPS ======================

    private void openFullNameField() {
        try {
            WebElement fullNameField = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Full Name\")")
                    )
            );

            fullNameField.click();
            ExtentLogger.pass("✅ Full Name field clicked");

        } catch (Exception e) {
            fail("❌ Full Name field not clickable", e);
        }
    }

    private void closeEditNameScreen() {
        try {
            WebElement closeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().description(\"Close\")")
                    )
            );

            closeBtn.click();
            ExtentLogger.pass("✅ Edit Name screen closed");

        } catch (Exception e) {
            fail("❌ Close button not clickable", e);
        }
    }

    private void verifyEditNameTitle() {
        try {
            WebElement editNameText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Edit Name\")")
                    )
            );

            softAssert.assertEquals(editNameText.getText().trim(),
                    "Edit Name",
                    "❌ Edit Name title mismatch");

            ExtentLogger.pass("✅ Edit Name title verified");

        } catch (Exception e) {
            fail("❌ Edit Name title not displayed", e);
        }
    }

    private void verifyFirstNameLabel() {
        try {
            WebElement firstNameLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"First Name *\")")
                    )
            );

            softAssert.assertEquals(firstNameLabel.getText().trim(),
                    "First Name *",
                    "❌ First Name label mismatch");

            ExtentLogger.pass("✅ First Name label verified");

        } catch (Exception e) {
            fail("❌ First Name label not displayed", e);
        }
    }

    private void enterRandomFirstName() {
        try {
            WebElement firstNameInput = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.TextView[@text='First Name *']/../android.widget.EditText")
                    )
            );

            firstNameInput.click();
            firstNameInput.clear();

            String randomFirstName = generateRandomName();
            firstNameInput.sendKeys(randomFirstName);

            ExtentLogger.pass("✅ Entered First Name: " + randomFirstName);

        } catch (Exception e) {
            fail("❌ First Name entry failed", e);
        }
    }

    private void verifySecondNameLabel() {
        try {
            WebElement secondNameLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().text(\"Second Name\")")
                    )
            );

            softAssert.assertEquals(secondNameLabel.getText().trim(),
                    "Second Name",
                    "❌ Second Name label mismatch");

            ExtentLogger.pass("✅ Second Name label verified");

        } catch (Exception e) {
            fail("❌ Second Name label not displayed", e);
        }
    }

    private void enterRandomSecondName() {
        try {
            WebElement secondNameInput = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.widget.TextView[@text='Second Name']/../android.widget.EditText")
                    )
            );

            secondNameInput.click();
            secondNameInput.clear();

            String randomSecondName = generateRandomName();
            secondNameInput.sendKeys(randomSecondName);

            ExtentLogger.pass("✅ Entered Second Name: " + randomSecondName);

        } catch (Exception e) {
            fail("❌ Second Name entry failed", e);
        }
    }

    private void clickDoneButton() {
        try {
            WebElement doneBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            androidUIAutomator("new UiSelector().text(\"Done\")")
                    )
            );

            softAssert.assertTrue(doneBtn.isDisplayed(), "❌ Done button not displayed");
            doneBtn.click();
            ExtentLogger.pass("✅ Done button clicked");

        } catch (Exception e) {
            fail("❌ Done button not clickable", e);
        }
    }

    // ====================== HELPERS ======================

    private String generateRandomName() {
        String[] names = {
                "Aarav", "Vihaan", "Arjun", "Rohan",
                "Karan", "Aditya", "Rahul", "Siddharth"
        };
        return names[new Random().nextInt(names.length)];
    }

    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }
}
