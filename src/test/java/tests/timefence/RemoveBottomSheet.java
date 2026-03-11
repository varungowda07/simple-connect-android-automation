package tests.timefence;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class RemoveBottomSheet extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void removeBottomSheet() {
        if(verifyAndClickIocns()) {
            verifyLongText();
            if(clickRemove()) {

            }

        }


    }
    public void verifyLongText() {
        universalMethods.verifyTextContains("Remove Time fence","Remove");
        universalMethods.verifyTextContains("This time fence will be deleted. You can create a new one anytime/later.","This time fence will be deleted.");
        universalMethods.verifyExactText("Remove ");
    }
    public boolean verifyAndClickIocns() {
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Delete\")"),
                "Delete Icon",
                false
        );
        return  universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Close\")"),
                "Clicked close",
                false
        );
    }
    public boolean clickRemove() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Remove \")"),
                "Remove Clicked",
                true
        );
    }
}
