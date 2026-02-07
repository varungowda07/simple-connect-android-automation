package tests.settings.editemergencycontact;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;


public class LearnHowItWorks extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void learnHotItWorks() {
        String manualLiveText = "Manual Live location sharing";
        String holdReverseText = "Hold Reverse (R) for 3 sec to share";
        String alsoShareText = "You can also share from scooter settings";
        String shareDescText = "Share your scooter’s live location with your emergency contact for 1 hour.";
        String incognitoNoteText = "Note: Not available in Incognito mode";

        universalMethods.verifyTextContains(manualLiveText, "Manual Live");
        universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"" + holdReverseText + "\"]/.."),
                "Scooter Icon",
                false
        );
        universalMethods.verifyTextContains(holdReverseText, holdReverseText);
        universalMethods.verifyTextContains(alsoShareText, alsoShareText);
        universalMethods.verifyTextContains(shareDescText, "Share your scooter’s");
        universalMethods.verifyTextContains(incognitoNoteText, incognitoNoteText);
        if(!clickCloseBtn()) {
            safeBack();
        }

    }
    private boolean clickCloseBtn() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Close\")"),
                "Close Btn",
                true
        );
    }
}
