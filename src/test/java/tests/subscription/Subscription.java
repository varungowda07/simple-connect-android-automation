package tests.subscription;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import tests.base.BaseTest;
import tests.settings.mygarage.MyGarage;
import tests.utils.UniversalMethods;

public class Subscription extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    RSA rsa = new RSA();
    MyGarage myGarage = new MyGarage();
    public void subscription() {
        if(myGarage.verifySettingsIconIsDisplayed()) {
            verifySubscriptionText("SUBSCRIPTIONS");
            verifySimpleRSADescription();
            verifySimpleRSAText("Simple RSA");
            if(verifySimpleRSAAndClick()) {
                rsa.rsa();

            }
            safeBack();

        }


    }
    private void verifySubscriptionText(String text) {
        universalMethods.scrollDownOnce();
        universalMethods.scrollToText("SUBSCRIPTIONS");
        universalMethods.verifyExactText(text);
    }
    private void verifySimpleRSAText(String text) {
        universalMethods.verifyExactText(text);
    }
    private void verifySimpleRSADescription() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Simple RSA\")"),
                "Simple RSA Description",
                false
        );
    }
    private boolean verifySimpleRSAAndClick() {
        return universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"Simple RSA\"]//following-sibling::android.view.View"),
                "Simple RSA Click",
                true
        );
    }

}
