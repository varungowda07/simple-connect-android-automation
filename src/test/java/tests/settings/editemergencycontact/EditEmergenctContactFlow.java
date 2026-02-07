package tests.settings.editemergencycontact;

import io.appium.java_client.AppiumBy;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.settings.mygarage.MyGarage;
import tests.utils.UniversalMethods;

public class EditEmergenctContactFlow extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    SoftAssert softAssert = new SoftAssert();
    MyGarage myGarage = new MyGarage();
    LiveLocationSharing liveLocationSharing = new LiveLocationSharing();
    public void editEmergencyContact() {
        if(myGarage.verifySettingsIconIsDisplayed()) {
            if(verifyTextIconAndClick("Live Location")) {
                liveLocationSharing.liveLocationSharing();
                universalMethods.verifyAndClickBackArrow("LIVE LOCATION SHARING");
                universalMethods.scrollUpOnce();
                universalMethods.verifyAndClickBackArrow("MY GARAGE");
            }

        }
    }
    private boolean verifyTextIconAndClick(String text) {
        universalMethods.scrollToText(text);
        universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"Live Location / Emergency \")"),
                text,
                false
        );
       universalMethods.verifyExactText(text);
       if(universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().textContains('" +text+ "')"),
                text,
                true
       )){
           return true;
       }
       return false;
    }



}
