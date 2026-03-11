package tests.timefence;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.base.BaseTest;
import tests.settings.mygarage.MyGarage;
import tests.utils.UniversalMethods;

public class TimeFenceFlow extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    SetTimeFence setTimeFence = new SetTimeFence();
    MyGarage myGarage = new MyGarage();
    EnableTimeFence enableTimeFence = new EnableTimeFence();
    DisableTimeFence disableTimeFence = new DisableTimeFence();
    ModifyTimeFence modifyTimeFence = new ModifyTimeFence();
    RemoveTimeFence removeTimeFence = new RemoveTimeFence();
    public void timeFenceFlow() {
        boolean isEnabled = true,isDisabled = true,isModified = true;
        if (myGarage.verifySettingsIconIsDisplayed()) {
            for (int i = 0;i<5;i++) {
            String text1 = verifyTextAndClick();
            if (clickForwardArrow()) {
                    if(text1.equals("Enabled") && isEnabled) {
                        isEnabled = false;
                        disableTimeFence.disableTimeFence();
                    }
                    else if(text1.equals("Disabled")) {
                        isDisabled = false;
                        enableTimeFence.enableTimeFence();
                    } else if(!isDisabled && !isEnabled && isModified) {
                          isModified = false;
                         modifyTimeFence.modifyTimeFence();
                    } else if (text1.equals("Set active hours")) {
                        setTimeFence.setTimeFence();
                    } else {
                        removeTimeFence.removeTimeFence();
                    }
                }
            }
            driver.navigate().back();
        }
    }
    public String verifyTextAndClick() {
        universalMethods.scrollToText("SECURITY");
        universalMethods.scrollDownOnce();
        universalMethods.verifyExactText("SECURITY");
        universalMethods.verifyExactText("Time-fence");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Time-fence\")"),
                "Time-fence description Icon",
                false
        );
        WebElement text = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text=\"Time-fence\"]/following-sibling::android.widget.TextView[last()]")));
        return text.getText();
    }
    public boolean clickForwardArrow() {
        boolean result = universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"Time-fence\"]/following-sibling::android.view.View"),
                "Time-fence Forward Arrow",
                true
        );
        System.out.println("result "+ result);
        return result;
    }
}
