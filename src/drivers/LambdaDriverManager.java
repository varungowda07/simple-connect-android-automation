package drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.ITestContext;
import utils.EnvUtils;

import java.net.URL;
import java.util.HashMap;

public class LambdaDriverManager {

    public static AndroidDriver createDriver(ITestContext context) throws Exception {

        String device = context.getCurrentXmlTest().getParameter("device");
        String version = context.getCurrentXmlTest().getParameter("platformVersion");
        String app = context.getCurrentXmlTest().getParameter("app");

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(device)
                .setPlatformVersion(version)
                .setApp(app);

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", EnvUtils.get("LT_USERNAME"));
        ltOptions.put("accessKey", EnvUtils.get("LT_ACCESS_KEY"));
        ltOptions.put("build", "Local + Lambda Parallel");
        ltOptions.put("name", context.getName());
        ltOptions.put("isRealMobile", true);

        options.setCapability("lt:options", ltOptions);

        return new AndroidDriver(
                new URL("https://mobile-hub.lambdatest.com/wd/hub"),
                options
        );
    }
}
