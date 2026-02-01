package listeners;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.base.BaseTest;
import tests.utils.ExtentManager;

import java.io.File;

public class TestListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getName();

        ExtentTest test = ExtentManager.getExtent()
                .createTest(result.getMethod().getMethodName())
                .assignCategory(className); // 👈 GROUP BY FLOW

        ExtentManager.setTest(test);
//        ExtentLogger.setTest(test);
    }


    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        AndroidDriver driver = ((BaseTest) testClass).driver;

        try {
            File src = driver.getScreenshotAs(OutputType.FILE);
            String path = "screenshots/" + result.getName() + ".png";
            FileUtils.copyFile(src, new File(path));
            System.out.println("📸 Screenshot captured: " + path);
        } catch (Exception e) {
            System.out.println("❌ Screenshot failed: " + e.getMessage());
        }
    }
}
