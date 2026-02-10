package tests.password;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import tests.base.BaseTest;
import tests.settings.mygarage.MyGarage;
import tests.utils.UniversalMethods;

public class PasswordFlow extends BaseTest {
    UniversalMethods universalMethods  = new UniversalMethods();
    MyGarage myGarage = new MyGarage();
    public void passwordFlow() {
        if(myGarage.verifySettingsIconIsDisplayed()) {
            if(verifyTextAndClick()) {
                
            }
        }


    }
    private boolean verifyTextAndClick() {
        universalMethods.scrollDownOnce();
        universalMethods.scrollToText("SECURITY");
        universalMethods.verifyExactText("SECURITY");
        universalMethods.verifyExactText("Password");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Password\")"),
                "Password description Icon",
                true
        );
        return universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"Password\"]/following-sibling::android.view.View"),
                "Password Forward Arrow",
                true
        );

    }
}
