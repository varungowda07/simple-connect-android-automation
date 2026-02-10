package tests.settings.mygarage;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class ScooterManual extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void scooterManual() {
        if(clickScooterManual()){
                verifyScooterManualText();
                verifyDownloadIconDisplayed();
                verifyScooterManualDescriptionText();
                verifyAndClickViewManualButton();
                if(verifyAndClickDownloadButton()) {
                    sleep();
                    if(verifyAndClickDownloadedPdf()){
                        scrollToBottom();
                        driver.navigate().back();
                        return;
                    }
                    else {
                        scrollToBottom();
                        driver.navigate().back();
                        return;
                    }

                }
                else{
                    if(clickCancelButton()){
                        sleep();
                        verifyAndClickDownloadedPdf();
                        scrollToBottom();
                        driver.navigate().back();
                        return;
                    }
                    else{
                        driver.navigate().back();
                        sleep();
                        verifyAndClickDownloadedPdf();
                        scrollToBottom();
                        driver.navigate().back();
                        return;

                    }


            }

        }
    }
    SoftAssert softAssert = new SoftAssert();
    private boolean clickScooterManual() {
        try {
            WebElement scooterManual = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='MY GARAGE']/following-sibling::android.view.View[1]")
                    )
            );

            softAssert.assertTrue(scooterManual.isDisplayed(), "❌ Scooter Manual is NOT displayed");
            scooterManual.click();
            ExtentLogger.pass("✅ Scooter Manual is displayed");
            return true;

        } catch (Exception e) {
            fail("❌ Scooter Manual verification failed", e);
            return false;
        }
    }
    private void clickCloseIcon() {
        try {
            WebElement closeIcon = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().description(\"Close\")"
                            )
                    )
            );

            closeIcon.click();
            ExtentLogger.pass("✅ Close icon clicked successfully");

        } catch (Exception e) {
            fail("❌ Close icon click failed", e);
        }
    }
    private void verifyScooterManualText() {
        try {
            WebElement scooterManual = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"Scooter Manual\")"
                            )
                    )
            );

            String actualText = scooterManual.getText();
            softAssert.assertTrue(actualText.contains("Scooter Manual"),
                    "❌ Scooter Manual text mismatch: " + actualText);

            ExtentLogger.pass("✅ Scooter Manual text verified: " + actualText);

        } catch (Exception e) {
            fail("❌ Scooter Manual text verification failed", e);
        }
    }
    private void verifyScooterManualDescriptionText() {
        try {
            WebElement descriptionText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"Everything you need\")"
                            )
                    )
            );

            String actualText = descriptionText.getText();
            softAssert.assertTrue(
                    actualText.contains("Everything you need"),
                    "❌ Scooter manual description text mismatch: " + actualText
            );

            ExtentLogger.pass("✅ Scooter manual description text verified: " + actualText);

        } catch (Exception e) {
            fail("❌ Scooter manual description text verification failed", e);
        }
    }
    private void verifyDownloadIconDisplayed() {
        try {
            WebElement downloadIcon = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//android.widget.TextView[@text='Scooter Manual']/preceding-sibling::android.view.View")
                    )
            );

            softAssert.assertTrue(downloadIcon.isDisplayed(), "❌ Download icon is NOT displayed");
            ExtentLogger.pass("✅ Download icon is displayed");

        } catch (Exception e) {
            fail("❌ Download icon verification failed", e);
        }
    }
    private void verifyAndClickViewManualButton() {
        try {
            WebElement viewManualBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"View Manual\")"
                            )
                    )
            );

            String actualText = viewManualBtn.getText();
            softAssert.assertTrue(
                    actualText.contains("View Manual"),
                    "❌ View Manual button text mismatch: " + actualText
            );

            viewManualBtn.click();
            ExtentLogger.pass("✅ View Manual button verified and clicked");

        } catch (Exception e) {
            fail("❌ View Manual button verification/click failed", e);
        }
    }
    private boolean verifyAndClickDownloadButton() {
        try {
            WebElement downloadBtn;

            try {
                // ✅ Primary (Chrome)
                downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().resourceId(\"com.android.chrome:id/positive_button\")"
                        )
                ));
            } catch (Exception e) {
                // ✅ Fallback: text based
                downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().textContains(\"Download\")"
                        )
                ));
            }

            softAssert.assertTrue(downloadBtn.isDisplayed(), "❌ Download button is NOT displayed");
            downloadBtn.click();
            ExtentLogger.pass("✅ Download button clicked successfully");
            return true;

        } catch (Exception e) {
            fail("❌ Download button verification/click failed", e);
            return false;
        }
    }
    private boolean verifyAndClickDownloadedPdf() {
        try {
            WebElement pdfFile = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\".pdf\")"
                            )
                    )
            );

            softAssert.assertTrue(pdfFile.isDisplayed(), "❌ PDF file is NOT displayed");
            pdfFile.click();
            ExtentLogger.pass("✅ PDF file displayed and clicked: " + pdfFile.getText());
            return true;
        } catch (Exception e) {
            fail("❌ PDF file verification/click failed", e);
            return false;
        }
    }
    private void scrollToBottom() {
       for(int i=0;i<20;i++) {
           universalMethods.scrollDownOnce();
       }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    private boolean clickCancelButton() {
        try {
            WebElement cancelBtn;

            try {
                cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.android.chrome:id/negative_button\")")
                ));
            } catch (Exception e1) {
                try {
                    cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)cancel|no|dismiss\")")
                    ));
                } catch (Exception e2) {
                    cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)")
                    ));
                }
            }

            cancelBtn.click();
            ExtentLogger.pass("✅ Cancel button clicked successfully");
            return true;

        } catch (Exception e) {
            fail("❌ Cancel button click failed", e);
            return false;
        }
    }







private void sleep() {
    try {
        Thread.sleep(6000);
    } catch (InterruptedException ex) {
        System.out.println("Something went Wrong");;
    }
}




    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

}
