package tests.timefence;

import io.appium.java_client.AppiumBy;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class TimeFenceOne extends BaseTest {
    UniversalMethods universalMethods= new UniversalMethods();
    public void timeFence() {
        SetTimeFence setTimeFence = new SetTimeFence();
        TimeFenceFlow timeFenceFlow = new TimeFenceFlow();
        if(clickInfo()) {
            if (setTimeFence.clickSetTimeFence()) {
                if(clickBack()) {
                    universalMethods.scrollDownOnce();
                    if(timeFenceFlow.clickForwardArrow()){
                        if(setTimeFence.clickSetTimeFence()) {
                            modifyFlow();

                        }

                    }

                }

            }

        }
    }
    public void modifyFlow() {
        SelectTime selectTime = new SelectTime();
        TimeFenceTwo timeFenceTwo = new TimeFenceTwo();
        if(clickAndScrollFrom()) {
            universalMethods.scrollUpOnce();;
            selectTime.selectTime("From");
        }
        if( clickAndScrollTo()) {
            universalMethods.scrollDownOnce();;
            selectTime.selectTime("To");
        }

        if(clickNext()) {
            timeFenceTwo.timeFenceTwo();
        }
    }
    String[] icons = {"Info","Back"};
    String[] shortText = {"Time Fence","Step 1/3","Set Allowed Hours","Pick a time range (1–12 hours)","From","To","Next"};
    public boolean clickInfo() {
        int info = 1;
        if (info <= 1) {
            info++;
//                String textLocator = String.format("");
            return universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Info\")"),
                    "Info displayed",
                    true
            );
        }
        return false;
    }
    public boolean clickBack() {
        int back = 1;
        if (back <= 1) {
            back++;
//                String textLocator = String.format("");
            return universalMethods.verifyAndClick(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Back\")"),
                    "Back displayed",
                    true
            );
        }
        return false;
    }
    public boolean clickNext() {
        return  universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Next\")"),
                "Clicked Next",
                true
        );
    }
    public void shortText() {
        for(int i=0;i<shortText.length;i++) {
            universalMethods.verifyExactText(shortText[i]);
        }
    }
    public void longText() {}
    public boolean clickAndScrollFrom() {
      return universalMethods.verifyAndClick(
              AppiumBy.androidUIAutomator("new UiSelector().text(\"From\")"),
              "From time clicked",
              true
      );



    }
    public boolean clickAndScrollTo() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"To\")"),
                "To time clicked",
                true
        );
    }



}
