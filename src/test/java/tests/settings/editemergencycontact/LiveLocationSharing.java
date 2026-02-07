package tests.settings.editemergencycontact;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class LiveLocationSharing extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    LearnHowItWorks learnHowItWorks = new LearnHowItWorks();
    EditContact editContact = new EditContact();
    public void liveLocationSharing() {
        String liveLocationTitle = "LIVE LOCATION SHARING";
        String nameLabel = "Name";
        String mobileLabel = "Mobile Number";
        String noteFull = "Note: This contact will be used for sending emergency updates and the scooter’s live location when triggered.";
        String notePartial = "Note: This contact will be used for sending emergency";
        String learnHowText = "Learn how it works";
        String editText = "Edit";

        universalMethods.verifyTextContains(liveLocationTitle, liveLocationTitle);
        universalMethods.verifyTextContains(nameLabel, nameLabel);
        universalMethods.verifyTextContains(mobileLabel, mobileLabel);
        universalMethods.verifyTextContains(noteFull, notePartial);
        if(verifyAndClickLearnHowItWorks(learnHowText)) {
            learnHowItWorks.learnHotItWorks();
        }
        if(verifyAndClickEdit(editText)) {
          editContact.editContact();
        }

    }
    private boolean verifyAndClickLearnHowItWorks(String text) {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")"),
                text,
                true
        );
    }
    private boolean verifyAndClickEdit(String editText) {
        universalMethods.verifyExactText(editText);
        return  universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"" + editText + "\")"),
                "Edit emergency contact",
                true
        );
    }
}
