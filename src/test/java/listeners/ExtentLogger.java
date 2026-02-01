package listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import tests.base.BaseTest;
import tests.utils.ExtentManager;
import utils.ScreenshotUtil;

public class ExtentLogger {

    public static void info(String message) {
        ExtentManager.getTest().info(message);
    }

    public static void pass(String message) {
        ExtentManager.getTest().pass(message);
    }

    public static void fail(String message) {
        ExtentManager.getTest().fail(message);
    }

    // 🔥 Screenshot captured immediately at failure line
    public static void failWithScreenshot(String message) {
        String base64 = ScreenshotUtil.captureBase64(BaseTest.driver);

        if (base64 != null) {
            ExtentManager.getTest().fail(
                    message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build()
            );
        } else {
            ExtentManager.getTest().fail(message + " (Screenshot unavailable)");
        }
    }
}
