package listeners;

import com.aventstack.extentreports.ExtentReports;
import org.testng.*;
import tests.utils.ExtentManager;

public class ExtentListener implements ITestListener {

    private static final ExtentReports extent = ExtentManager.getExtent();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.setTest(
                extent.createTest(result.getMethod().getMethodName())
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("✅ Test PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
