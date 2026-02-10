package tests.subscription;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class RSA extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void rsa() {
        verifyShortText();
        verifyIcons();
        verifyLongText();
        if(clickVisitDealer()) {
            universalMethods.verifyExactText("Simple Energy");
            safeBack();
        }
        universalMethods.verifyAndClickBackArrow("RSA");


    }
    private void verifyShortText() {
        universalMethods.verifyExactText("Simple RoadSide Assistance");
        universalMethods.verifyExactText("RSA");
        universalMethods.verifyExactText("Visit the nearest dealer");

    }
    private void verifyIcons() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"RSA State\")"),
                "RSA State Icon",
                false
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"RSA\")"),
                "RSA Icon",
                false
        );
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Location\")"),
                "Location icon",
                false
        );
    }
    private void verifyLongText() {
        universalMethods.verifyTextContains(
                "24x7 emergency assistance in case of accident, breakdown, puncture",
                "24x7 emergency assistance in case of"
        );
        universalMethods.verifyTextContains(
                "when your Simple runs out of charge. Ride worry-free",
                "when your Simple runs out of charge."
        );
    }
    public boolean clickVisitDealer() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Visit the nearest dealer\")"),
                "Visit the nearest dealer btn clicked",
                true
        );
    }

}
