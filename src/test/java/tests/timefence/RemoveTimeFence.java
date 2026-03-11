package tests.timefence;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class RemoveTimeFence extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void removeTimeFence() {
        RemoveBottomSheet removeBottomSheet = new RemoveBottomSheet();
        if(clickEdit()) {
            if(clickClose()) {
                if(clickEdit()) {
                    if(clickDelete()) {
                      removeBottomSheet.removeBottomSheet();
                    }
                }
            }
        }
    }
    public boolean clickEdit() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Edit\")"),
                "Edit Clicked",
                true
        );
    }
    String[] shortText = {"Time Fence","Remove","Modify"};
    public void verifyShortText() {
        for(int i=0;i<shortText.length;i++) {
            universalMethods.verifyExactText(shortText[i]);
        }
    }
    public boolean clickClose() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Close\")"),
                "Click close",
                true
        );
    }
    public boolean clickDelete() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Delete\")"),
                "Click Delete",
                true
        );
    }


}
