package tests.password;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import tests.base.BaseTest;
import tests.settings.mygarage.MyGarage;
import tests.utils.UniversalMethods;

public class PasswordFlow extends BaseTest {
    UniversalMethods universalMethods  = new UniversalMethods();
    MyGarage myGarage = new MyGarage();
    EnterPasscodeScreen enterPasscodeScreen = new EnterPasscodeScreen();
    public void passwordFlow() {
        if(myGarage.verifySettingsIconIsDisplayed()) {
            if(verifyTextAndClick()) {
                enterPasscodeScreen.enterPasscodeScreen();
            }
        }


    }
    private boolean verifyTextAndClick() {
        universalMethods.scrollToText("SECURITY");
        universalMethods.scrollDownOnce();
        universalMethods.verifyExactText("SECURITY");
        universalMethods.verifyExactText("Password");
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Password\")"),
                "Password description Icon",
                false
        );
        boolean result = universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"Password\"]/following-sibling::android.view.View"),
                "Password Forward Arrow",
                true
        );
        System.out.println("result "+ result);
        return result;

    }
}
