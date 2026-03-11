package tests.timefence;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

import java.util.Random;

public class TimeFenceTwo extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void timeFenceTwo() {
        TimeFenceThree timeFenceThree = new TimeFenceThree();
        verifyLongText();
        verifyShortText();
        if(clickDropDown()) {
            if(clickRepeatsOn()) {
                if(clickClose()) {
                    if(clickNext()) {
                     timeFenceThree.timeFenceThree();
                    }
                }
            }
        }

    }
    String[] shortText = {"Time Fence","Time Fence","Time Fence","Repeats on"};
    public void verifyShortText() {
        for(int i=0;i<shortText.length;i++) {
            universalMethods.verifyExactText(shortText[i]);
        }

    }
    public void verifyLongText() {
        universalMethods.verifyTextContains("Choose which days to monitor or apply for today only","Choose which days to monitor");
    }
    public void selectCustomDays() {

    }
    public boolean clickDropDown() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Drop Down\")"),
                "Clicked Drop Down",
                true
        );
    }
    Random random = new Random();
    String[] repeatesOn = {"Weekdays Only","Today Only","Weekends Only"};
    int j = random.nextInt(3);
    public boolean clickRepeatsOn() {
        String textLocator = String.format("new UiSelector().text(\"%s\")", repeatesOn[j]);
        return  universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator(textLocator),
                "Clicked RepeatsOn",
                true
        );
    }
    public boolean clickClose() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Close\")"),
                "Clicked close",
                true
        );
    }
    public boolean clickNext() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Next\")"),
                "Clicked Next",
                true
        );
    }





}
