package tests.timefence;

import io.appium.java_client.AppiumBy;
import listeners.ExtentLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class TimeFenceEnabled extends BaseTest {
    SoftAssert softAssert  = new SoftAssert();
    UniversalMethods universalMethods = new UniversalMethods();
    TimeFenceThree timeFenceThree = new TimeFenceThree();
    SelectTime selectTime = new SelectTime();
    public void timeFenceEnabled() {
        verifyShortText();
        extractTitle();
        verifyIocns();

        extractTime(By.xpath("//android.view.View[@content-desc=\"Timer\"]/../following-sibling::android.widget.TextView[2]"));
        driver.navigate().back();
        universalMethods.scrollDownOnce();
        extractTime(By.xpath("//android.widget.TextView[@text=\"Time-fence\"]/following-sibling::android.widget.TextView[1]"));
        universalMethods.verifyExactText("Enabled");



    }
    String[] shortText = {"ENABLED","Disable","Recent Events","Repeats on"};
    String[] icons = {"Edit","Back","Timer","Recents","Scooter"};
    public void verifyShortText() {
        for(int i=0;i<shortText.length;i++) {
            universalMethods.verifyExactText(shortText[i]);
        }
    }
    public void verifyIocns() {
        for(int i=0;i<icons.length;i++) {
            String locator = String.format("new UiSelector().description(\"%s\")", icons[i]);
            universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator(locator),
                    icons[i]+" displayed",
                    false
            );
        }
    }
    public void extractTitle() {
        String timeFenceTitle = "";
        try {
            WebElement title = driver.findElement(AppiumBy.androidUIAutomator("//android.view.View[@content-desc=\"Timer\"]/../following-sibling::android.widget.TextView[1]"));
            timeFenceTitle = title.getText();
            softAssert.assertEquals(timeFenceThree.title, timeFenceTitle);
            System.out.println("Expected title " + timeFenceThree.title + " Actual " + timeFenceTitle + " Both are same");
            ExtentLogger.pass("Expected title " + timeFenceThree.title + " Actual " + timeFenceTitle + " Both are same");
        } catch (Exception e) {
            fail("Expected title " + timeFenceThree.title + " Actual " + timeFenceTitle + " Both are not same", e);

        }
    }
        public void extractTime(By locator) {
            String fromTime="";
            String toTime="";
            String fullTime="";
            try {
                WebElement title = wait.until(
                        ExpectedConditions.elementToBeClickable(locator)
                );;
                fullTime = title.getText();
                String[] arr = fullTime.split(" ");
                softAssert.assertEquals(arr[0], selectTime.fromTime);
                ExtentLogger.pass("Expected time "+selectTime.fromTime+" Actual "+arr[0]+" Both are same");
                softAssert.assertEquals(arr[2],selectTime.toTime);
                ExtentLogger.pass("Expected time "+selectTime.toTime+" Actual "+arr[2]+" Both are same");
                System.out.println("Expected time "+selectTime.fromTime+"to"+selectTime.toTime+" Actual "+fullTime+" Both are not same");
            } catch (Exception e) {
                System.out.println("Expected time "+selectTime.fromTime+"to"+selectTime.toTime+" Actual "+fullTime+" Both are not same");
                fail("Expected time "+selectTime.fromTime+"to"+selectTime.toTime+" Actual "+fullTime+" Both are not same",e);
            }
    }
    private void fail(String message, Exception e) {
        ExtentLogger.failWithScreenshot(message);
        softAssert.fail(message, e);
    }

}
