package tests.home;

import org.openqa.selenium.By;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class RideingImpact extends BaseTest {
    UniversalMethods universalMethods = new UniversalMethods();
    public void rideingImpactScreen() {
        verifyRideImpact("RIDING IMPACT");
        verifyTopSpeed("Top Speed");
        verifyAvgSpeed("Avg Speed");
        verifyCo2Saved("CO2 Saved");
        verifyYouSaved("You Saved");
        verifyCo2SavedIcon();
        verifyCo2SavedText();
        verifyYouSavedIcon();
        verifyYouSavedText();
        verifyYouSavedForwardIcon();
    }
    private void verifyRideImpact(String text) {
        universalMethods.scrollDownOnce();
        universalMethods.verifyExactText(text);
    }
    private void verifyTopSpeed(String text) {
        universalMethods.verifyExactText(text);
    }
    private void verifyAvgSpeed(String text) {
        universalMethods.verifyExactText(text);
    }
    private void verifyCo2Saved(String text) {
        universalMethods.verifyExactText(text);
    }
    private void verifyYouSaved(String text) {
        universalMethods.verifyExactText(text);
    }
    private void verifyCo2SavedIcon() {
        universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"CO2 Saved\"]/following-sibling::android.view.View"),
                "verifyCo2SavedIcon",
                false
        );
    }
    private void verifyCo2SavedText() {
        universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"CO2 Saved\"]/following-sibling::android.widget.TextView"),
                "verifyCo2SavedText",
                false
        );
    }
    private void verifyYouSavedIcon() {
        universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"You Saved\"]/following-sibling::android.view.View[1]"),
                "verifyYouSavedIcon",
                false
        );
    }
    private void verifyYouSavedText() {
        universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"You Saved\"]/following-sibling::android.widget.TextView"),
                "verifyYouSavedText",
                false
        );
    }
    private void verifyYouSavedForwardIcon() {
        universalMethods.verifyAndClick(
                By.xpath("//android.widget.TextView[@text=\"You Saved\"]/following-sibling::android.view.View[2]"),
                "verifyYouSavedForwardIcon",
                false
        );
    }
}
