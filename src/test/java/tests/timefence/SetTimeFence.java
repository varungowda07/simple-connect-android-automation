package tests.timefence;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class SetTimeFence extends BaseTest {
    UniversalMethods universalMethods  = new UniversalMethods();
    public void setTimeFence() {
        TimeFenceOne timeFence = new TimeFenceOne();
        verifyIcons();
        verifyLongText();
        verifyShortText();
        if(clickSetTimeFence()) {
          timeFence.timeFence();
        }

    }
    String icons[] = {"Timefence filled","Define Hours","Set Frequency","Save Schedule"};
    String shortText[] = {"Time Fence","Define Hours","Set Frequency","Save Schedule","Set Time-fence"};
    String longText[] = {
            "Set time limits for your vehicle. Get alerts if it’s used outside allowed hours.",
            "Select the time range when your vehicle can be used.",
            "Choose the days to apply these hours, or set for today only.",
            "Name your time fence and turn it on."
    };
    public void verifyShortText() {
        for(int i=0;i<shortText.length;i++) {
            universalMethods.verifyExactText(shortText[i]);
        }
    }
    public void verifyLongText() {
        for(int i=0;i< longText.length;i++) {
            universalMethods.verifyTextContains(longText[i],longText[i].substring(0,10));
        }

    }
    public void verifyIcons() {
        for(int i=0;i< icons.length;i++) {
            String iconLocator = String.format("new UiSelector().description(\"%s\")", icons[i]);
            universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator(iconLocator),
                    icons[i],
                    false
            );
        }
    }
    public boolean clickSetTimeFence() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Set Time-fence\")"),
                "Click Set Time-frnce",
                true
        );
    }




}
