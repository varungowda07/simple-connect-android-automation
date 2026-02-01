package drivers;

import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestContext;

public class DriverFactory {

    public static AndroidDriver createDriver(ITestContext context) throws Exception {

        String execution = context.getCurrentXmlTest().getParameter("execution");

        if ("lambda".equalsIgnoreCase(execution)) {
            return LambdaDriverManager.createDriver(context);
        } else {
            return LocalDriverManager.createDriver(context);
        }
    }
}
