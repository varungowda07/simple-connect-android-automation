package tests.timefence;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class SelectTime extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public static String fromTime="";
    public static String toTime = "";
    public void selectTime(String name) {
      verifyShortText();
      getSelectedTime(name);
      if(!clickSetNow()) {
          if(!clickClose()) {
              driver.navigate().back();
          }
      }
    }
    public void verifyShortText() {
        universalMethods.verifyExactText("Select Time");
        universalMethods.verifyExactText("Set Now");
    }
    public void getSelectedTime(String name) {
        WebElement getFromText  = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"(i.e.,\")"));
        String selectedTime = getFromText.getText();
        String time = selectedTime.split(" ")[1];
        String hours[] = time.split(":");
        String amOrPm = selectedTime.split(" ")[2];
        if(name.equals("From")) {
            if(amOrPm.startsWith("PM") && !hours[0].equals("12")) {
                int a = Integer.parseInt(hours[0]) + 12;
                fromTime =a+":"+hours[1];
            }
            else {
                fromTime = time;
            }
        }
        else  {
            if(amOrPm.startsWith("PM") && !hours[0].equals("12")) {
                int a = Integer.parseInt(hours[0]) + 12;
                toTime =a+":"+hours[1];
            }
            else {
                toTime = time;
            }

        }


        System.out.println(fromTime);
        System.out.println(toTime);

    }
    public boolean clickSetNow() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Set Now\")"),
                "Click Set Now",
                true
        );
    }
    public boolean clickClose() {
        return  universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Close\")"),
                "Click Close",
                true
        );
    }
}
