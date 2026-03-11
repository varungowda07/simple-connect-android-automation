package tests.timefence;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

import java.util.Random;

public class TimeFenceThree extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    static String[] titles = {"title1","title2","title3","title4","title5"};
   static Random random = new Random();
    public static String title =titles[random.nextInt(titles.length)];
    public void timeFenceThree() {
        TimeFenceEnabledBottomSheet timeFenceEnabledBottomSheet = new TimeFenceEnabledBottomSheet();
        verifyShortText();
        if(clickAndEnterTimeFenceName()) {
          if(clickSave()) {
             timeFenceEnabledBottomSheet.timeFenceEnabledBottomSheet();
          }
        }

    }
    String[] shortText = {"Time Fence","Step 3/3","Enter Timefence title*","Save"};
    int k=0;
    public void verifyShortText() {
        for(int i=0;i<shortText.length;i++) {
            k=i;
            universalMethods.verifyExactText(shortText[i]);
        }
    }
    public boolean clickAndEnterTimeFenceName() {
        Random random = new Random();
        String text = random.toString();
        System.out.println(text);
        try {
            WebElement enterTimeFence = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")"));
            enterTimeFence.click();
            enterTimeFence.clear();
            enterTimeFence.sendKeys(title);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean clickSave() {
        return universalMethods.verifyAndClick(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"Save\")"),
                "Clicked save",
                true
        );
    }



}
