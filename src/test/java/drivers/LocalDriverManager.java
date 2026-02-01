package drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.ITestContext;

import java.net.URL;

public class LocalDriverManager {

    public static AndroidDriver createDriver(ITestContext context) throws Exception {

        String device = context.getCurrentXmlTest().getParameter("device");

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(device)
                .setAutoGrantPermissions(true)
                .setNoReset(false);

        return new AndroidDriver(
                new URL("http://127.0.0.1:4723/"),
                options
        );
    }
}
