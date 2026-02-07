package tests.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.appmanagement.ApplicationState;
import listeners.ExtentLogger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import tests.utils.ExtentManager;

import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import api.AuthApi;

public class BaseTest {

    public static AndroidDriver driver;
    public static WebDriverWait wait;

    private static boolean isSetupDone = false;

    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    protected static final String APP_PACKAGE = "com.sepl.android.dev";
    protected static final String APP_PATH =
            System.getProperty("user.dir") + "/apps/presentation-dev-debug (6).apk";

    // ===================== REPORT SETUP =====================
    @BeforeClass
    public void setupReport() {
        extent = ExtentManager.getExtent();
    }

    @BeforeMethod
    public void setupTest(Method method) {

        ExtentTest test = extent.createTest(method.getName());
        extentTest.set(test);
        forceKillApp();
//        ExtentLogger.setExtentTest(extentTest.get());
    }

    @AfterMethod
    public void flushReport() {
        extent.flush();
    }

    // ===================== APPIUM SETUP =====================
    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        if (isSetupDone) return;
        try {
            AuthApi.authApi();
        } catch (RuntimeException e) {
            System.out.println(e);
        } catch (Exception e1) {
            System.out.println(e1);
        }


        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("ZD222RCP7G")
                .setNoReset(true)
                .setAutoGrantPermissions(true);

        options.setCapability("appium:autoAcceptAlerts", true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723/"),
                options
        );

        // Install app if missing
        if (!driver.isAppInstalled(APP_PACKAGE)) {
            driver.installApp(APP_PATH);
        }

//        driver.terminateApp(APP_PACKAGE);
        forceKillApp();
        Thread.sleep(2000);


        driver.activateApp(APP_PACKAGE);


//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        isSetupDone = true;
        System.out.println("✅ Appium session STARTED");
    }
    private void forceKillApp() {
        try {
            // ADB force stop - 100% works
            Map<String, Object> args = new HashMap<>();
            args.put("command", "am force-stop " + APP_PACKAGE);
            driver.executeScript("mobile: shell", args);
            Thread.sleep(2000);
            System.out.println("✅ Force killed via ADB");
        } catch (Exception e) {
            System.out.println("⚠️ Force kill failed");
        }
    }



    // ===================== TERMINATE + ACTIVATE AFTER EACH FLOW =====================
    @AfterMethod(alwaysRun = true)
    public void restartAppAfterEachFlow() {
        try {
            System.out.println("🔁 Restarting app after test flow...");

            Map<String, Object> terminateArgs = new HashMap<>();
            terminateArgs.put("appId", APP_PACKAGE);
            terminateArgs.put("timeout", 1500); // Samsung-safe timeout

            driver.executeScript("mobile: terminateApp", terminateArgs);
            Thread.sleep(500);

            driver.activateApp(APP_PACKAGE);
            Thread.sleep(1000);

            System.out.println("✅ App terminated and activated successfully");

        } catch (Exception e) {
            System.out.println("⚠️ Terminate failed, using fallback...");

            try {
                driver.runAppInBackground(Duration.ofSeconds(10));
                driver.activateApp(APP_PACKAGE);
                System.out.println("✅ App recovered via fallback");
            } catch (Exception ex) {
                System.out.println("❌ App restart failed: " + ex.getMessage());
            }
        }
    }
    public static void safeBack() {
        try {
            // Try normal back first
            driver.navigate().back();
            Thread.sleep(1000);
        } catch (Exception e) {
            // Fallback: Device keyevent
            Map<String, Object> params = Map.of("command", "input keyevent 4");
            driver.executeScript("mobile: shell", params);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    private void clearAppData(String packageName) throws Exception {
        System.out.println("Clearing app data via ADB...");
        Process process = Runtime.getRuntime().exec("adb shell pm clear " + packageName);
        process.waitFor();
        System.out.println("App data cleared");
    }


    // ===================== CLEANUP =====================
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        try {
//            // 🔥 CLEAR APP DATA FIRST
//            Map<String, Object> clearArgs = new HashMap<>();
//            clearArgs.put("appPackage", APP_PACKAGE);
//            driver.executeScript("mobile: clearAppData", clearArgs);
//            Thread.sleep(2000);
//
//            // Then quit
            clearAppData(APP_PACKAGE);
            driver.quit();
            System.out.println("✅ Appium session CLOSED + DATA CLEARED");

        } catch (Exception e) {
            System.out.println("⚠️ Cleanup failed: " + e.getMessage());
        }
        finally {
            if(driver!= null) {
                driver.quit();
                System.out.println("✅ Appium session CLOSED + DATA CLEARED");
            }

        }
    }

//    // ===================== UTILITIES =====================
//    private void enableNotifications(String packageName) {
//        try {
//            HashMap<String, Object> args = new HashMap<>();
//            args.put("command", "appops");
//            args.put("args", new String[]{
//                    "set",
//                    packageName,
//                    "POST_NOTIFICATION",
//                    "allow"
//            });
//
//            driver.executeScript("mobile: shell", args);
//            System.out.println("✅ Notifications enabled");
//        } catch (Exception e) {
//            System.out.println("⚠️ Notification enable failed: " + e.getMessage());
//        }
//    }
//
//    protected void handleNotificationPermissionPopup() {
//        try {
//            driver.findElement(
//                    AppiumBy.androidUIAutomator(
//                            "new UiSelector().textMatches(\"(?i)allow|while using the app\")"
//                    )
//            ).click();
//            System.out.println("✅ Notification permission accepted");
//        } catch (Exception ignored) {
//            System.out.println("ℹ️ No notification popup shown");
//        }
//    }
}
//
////package tests.base;
////
////import com.aventstack.extentreports.ExtentReports;
////import com.aventstack.extentreports.ExtentTest;
////import io.appium.java_client.AppiumBy;
////import io.appium.java_client.android.AndroidDriver;
////import io.appium.java_client.android.options.UiAutomator2Options;
////import io.appium.java_client.appmanagement.ApplicationState;
////import listeners.ExtentLogger;
////import org.openqa.selenium.support.ui.WebDriverWait;
////import org.testng.annotations.*;
////import tests.utils.ExtentManager;
////import java.util.HashMap;
////
////import java.lang.reflect.Method;
////import java.net.URL;
////import java.time.Duration;
////
////public class BaseTest {
////
////    public static AndroidDriver driver;
////    public static WebDriverWait wait;
////    private static boolean isSetupDone = false;
////
////    protected static ExtentReports extent;
////    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
////
////    public String APP_PACKAGE = "com.sepl.android.dev";
////    private static final String APP_PATH = System.getProperty("user.dir") + "/apps/app-version-60.apk";
////
////
////    private void forceEnableNotificationsViaAppium(String packageName) {
////        try {
////            HashMap<String, Object> args = new HashMap<>();
////            args.put("command", "appops");
////            args.put("args", new String[]{
////                    "set",
////                    packageName,
////                    "POST_NOTIFICATION",
////                    "allow"
////            });
////
////            driver.executeScript("mobile: shell", args);
////            System.out.println("✅ Notifications force-enabled via Appium for: " + packageName);
////        } catch (Exception e) {
////            System.out.println("❌ Failed to enable notifications: " + e.getMessage());
////        }
////    }
////    public void handleNotificationPermissionPopup() {
////        try {
////            driver.findElement(
////                    AppiumBy.androidUIAutomator(
////                            "new UiSelector().textMatches(\"(?i)allow|while using the app\")"
////                    )
////            ).click();
////            System.out.println("✅ Notification permission accepted");
////        } catch (Exception ignored) {
////            System.out.println("ℹ️ No notification popup shown");
////        }
////    }
////
////
////    @BeforeClass
////    public void setupReport() {
////        extent = ExtentManager.getExtent();
////    }
////
////    @BeforeMethod
////    public void setupTest(Method method) {
////        ExtentTest test = ExtentManager.getExtent().createTest(method.getName());
////        extentTest.set(test);
////        ExtentLogger.setExtentTest(extentTest.get());
////    }
////
////    @AfterMethod
////    public void endTest() {
////        extent.flush();
////    }
////
////    @BeforeSuite
////    public void setUp() throws Exception {
////        if (isSetupDone) return;
////
////        UiAutomator2Options options = new UiAutomator2Options()
////                .setPlatformName("Android")
////                .setAutomationName("UiAutomator2")
////                .setDeviceName("RZCT91RWG0Y")
////                .setNoReset(true)
////                .setAutoGrantPermissions(true);
////                options.setCapability("appium:autoAcceptAlerts", true);
////
////
////        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
////
////        // Install app if missing
////        if (!driver.isAppInstalled(APP_PACKAGE)) {
////            driver.installApp(APP_PATH);
////        }
////        // 1️⃣ Terminate app if running
////        ApplicationState appState = driver.queryAppState(APP_PACKAGE);
////        System.out.println("App state before start: " + appState);
////
////        if (appState != ApplicationState.NOT_INSTALLED && appState != ApplicationState.NOT_RUNNING) {
////            System.out.println("App is running. Terminating to start fresh...");
////            driver.terminateApp(APP_PACKAGE);
////            Thread.sleep(2000);
////        }
////
////
////        // 2️⃣ Clear app data manually using ADB (forces login)
//////        clearAppData(APP_PACKAGE);
////
////        // 3️⃣ Activate app
////        driver.activateApp(APP_PACKAGE);
////        Thread.sleep(3000);
////        forceEnableNotificationsViaAppium(APP_PACKAGE);
////        handleNotificationPermissionPopup();
////
////        // 4️⃣ Waits
////        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
////        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
////
////        isSetupDone = true;
////        System.out.println("✅ Appium session STARTED - App ready at LOGIN screen");
////    }
////
////    @AfterSuite(alwaysRun = true)
////    public void tearDown() {
////        if (driver != null) {
////            driver.quit();
////            System.out.println("✅ Appium session CLOSED");
////        }
////    }
////
////    // ====================== UTILITY ======================
////    @AfterMethod(alwaysRun = true)
////    public void restartAppAfterEachTest() {
////        try {
////            System.out.println("🔁 Restarting app after test...");
////
////            driver.terminateApp(APP_PACKAGE);
////            Thread.sleep(4000);
////
////            driver.activateApp(APP_PACKAGE);
////            Thread.sleep(5000);
////
////            System.out.println("✅ App restarted successfully");
////        } catch (Exception e) {
////            System.out.println("❌ Failed to restart app: " + e.getMessage());
////        }
////    }
////
////    protected void resetToLoginScreen() {
////        try {
////            driver.terminateApp(APP_PACKAGE);
////            Thread.sleep(4000);
//////            clearAppData(APP_PACKAGE); // clear data to ensure login
////            driver.activateApp(APP_PACKAGE);
////            System.out.println("🔄 RESET: App killed and restarted - Login screen ready");
////        } catch (Exception e) {
////            System.out.println("Reset failed: " + e.getMessage());
////        }
////    }
////
////    /**
////     * Clear app data using ADB (forces fresh login)
////     */
////    private void clearAppData(String packageName) throws Exception {
////        System.out.println("Clearing app data via ADB...");
////        Process process = Runtime.getRuntime().exec("adb shell pm clear " + packageName);
////        process.waitFor();
////        System.out.println("App data cleared");
////    }
////}
