import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class FirstTest {

    static AndroidDriver driver;
    static final String APP_PACKAGE = "com.sepl.android.dev";
    static final String APP_PATH = System.getProperty("user.dir") + "/apps/app-version-60.apk";
    SoftAssert softAssert = new SoftAssert();


    @BeforeTest
    @Test(priority = 1)
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("22231FDF6007KF")
                .setNoReset(true)
                .setAutoGrantPermissions(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);

        if (!driver.isAppInstalled(APP_PACKAGE)) {
            driver.installApp(APP_PATH);
        }

        if (driver.queryAppState(APP_PACKAGE) == ApplicationState.RUNNING_IN_FOREGROUND ||
                driver.queryAppState(APP_PACKAGE) == ApplicationState.RUNNING_IN_BACKGROUND) {
            driver.terminateApp(APP_PACKAGE);
            Thread.sleep(2000);
        }

        driver.activateApp(APP_PACKAGE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(priority = 2)
    public void testFullLoginOtp() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        SoftAssert softAssert = new SoftAssert();

        /* ---------- TEXT VALIDATIONS (SOFT ASSERT) ---------- */

        try {
            WebElement consentText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator(
                                    "new UiSelector().className(\"android.widget.TextView\")" +
                                            ".textContains(\"By continuing\")"
                            )
                    )
            );

            String actualText = consentText.getText().trim();

            String expectedText =
                    "By continuing , I agree to the \n Terms of Service and consent to the Privacy Policy";

            softAssert.assertEquals(
                    actualText,
                    expectedText,
                    "❌ Consent text mismatch"
            );

        } catch (Exception e) {
            softAssert.fail("❌ Consent text NOT found");
        }
        WebElement termsLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator(
                                "new UiSelector().textContains(\"Terms of Service\")"
                        )
                )
        );
        termsLink.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.activateApp(APP_PACKAGE);
        WebElement privacyLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator(
                                "new UiSelector().textContains(\"Privacy Policy\")"
                        )
                )
        );
        privacyLink.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



        /* ---------- BUTTON: SIGN IN (HARD ASSERT) ---------- */

        WebElement signInBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator("new UiSelector().text(\"Sign in\")")
                )
        );
        Assert.assertTrue(signInBtn.isDisplayed(), "❌ Sign In button NOT visible");
        signInBtn.click();

        /* ---------- TEXT VALIDATION ---------- */

        try {
            WebElement headerText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator(
                                    "new UiSelector().className(\"android.widget.TextView\")" +
                                            ".textContains(\"Enter your\")"
                            )
                    )
            );

            String actualHeader = headerText.getText().trim();
            String expectedHeader = "Enter your\nmobile number";

            softAssert.assertEquals(
                    actualHeader,
                    expectedHeader,
                    "❌ Header text mismatch"
            );

        } catch (Exception e) {
            softAssert.fail("Enter your mobile number text not found");
        }
        try {
            WebElement subText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator(
                                    "new UiSelector().className(\"android.widget.TextView\")" +
                                            ".textContains(\"4-digit OTP\")"
                            )
                    )
            );

            String actualSubText = subText.getText().trim();
            String ExpectedSubText = "A 4-digit OTP will be sent to this number\nvia SMS for verification.\n";

            softAssert.assertEquals(
                    actualSubText,
                    ExpectedSubText,
                    "❌ OTP description text mismatch."
            );

        } catch (Exception e) {
            softAssert.fail("❌ OTP description text NOT found");
        }


        /* ---------- INPUT: MOBILE NUMBER (HARD ASSERT) ---------- */

        WebElement mobileField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
                )
        );
        Assert.assertTrue(mobileField.isDisplayed(), "❌ Mobile input not visible");
        mobileField.clear();
        mobileField.sendKeys("9999999999");

        /* ---------- BUTTON: SEND OTP ---------- */

        WebElement sendOtpBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator("new UiSelector().text(\"Send OTP\")")
                )
        );
        Assert.assertTrue(sendOtpBtn.isEnabled(), "❌ Send OTP button not enabled");
        sendOtpBtn.click();

        /* ---------- TEXT VALIDATION ---------- */

        try {
            WebElement retryText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            androidUIAutomator("new UiSelector().textContains(\"Retry\")")
                    )
            );
            softAssert.assertTrue(retryText.isDisplayed(),
                    "❌ Retry timer text not displayed");
        } catch (Exception e) {
            softAssert.fail("❌ Retry timer text NOT found");
        }

        /* ---------- INPUT: OTP ---------- */

        WebElement otpField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
                )
        );
        Assert.assertTrue(otpField.isDisplayed(), "❌ OTP field not visible");
        otpField.sendKeys("3289");

        /* ---------- BUTTON: GET STARTED ---------- */

        WebElement getStartedBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator("new UiSelector().className(\"android.widget.Button\")")
                )
        );
        Assert.assertTrue(getStartedBtn.isEnabled(), "❌ Get Started button not enabled");
        getStartedBtn.click();

        /* ---------- REPORT ALL TEXT FAILURES ---------- */
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void profile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement profileBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator("new UiSelector().description(\"Placeholder\").instance(1)")
                )
        );
        Assert.assertTrue(profileBtn.isDisplayed(), "❌Profile btn is  not dislayed");
        profileBtn.click();
        WebElement image = driver.findElement(
                AppiumBy.className("android.widget.ImageView")
        );
        softAssert.assertEquals(image.isDisplayed(),"profile image is not displayed");

//        Assert.assertTrue(image.isDisplayed(), "❌ Image is not displayed");
        try {
            WebElement textElement = driver.findElement(
                    By.xpath("//android.widget.TextView[@text='View Profile']/../preceding-sibling::android.widget.TextView")
            );

            softAssert.assertTrue(
                    textElement.isDisplayed(),
                    "❌ Text near 'View Profile' is not displayed"
            );

            System.out.println("Text found: " + textElement.getText());

        } catch (Exception e) {
            softAssert.fail("❌ Text near 'View Profile' NOT found");
        }
        WebElement viewProfile = driver.findElement(
                androidUIAutomator(
                        "new UiSelector().text(\"View Profile\")"
                )
        );
        softAssert.assertEquals(viewProfile.isDisplayed(),"View Profile button not displayed");
        viewProfile.click();
        try {
            WebElement image1 = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().className(\"android.widget.ImageView\").instance(0)"
                            )
                    )
            );
            softAssert.assertTrue(image1.isDisplayed(), "❌ Image not visible");
        } catch (Exception e) {
            softAssert.fail("❌ Image not found within timeout");
        }



        Assert.assertTrue(profileBtn.isDisplayed(),"Profile button is not displayed");
        WebElement logoutBtn = driver.findElement(
                androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollTextIntoView(\"Logout\")"
                )
        );

        Assert.assertTrue(logoutBtn.isDisplayed(), "❌ Logout button not displayed");
        logoutBtn.click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement yesBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().text(\"Yes\")")
                )
        );
        yesBtn.click();

        Assert.assertTrue(logoutBtn.isDisplayed(), "❌ Yes button not displayed");
        yesBtn.click();

        softAssert.assertAll();


    }

    @Test(priority = 3)
    public void bluetoothFlow() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement bluetoothBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator("new UiSelector().description(\"Bluetooth\")")
                )
        );
        Assert.assertTrue(bluetoothBtn.isDisplayed(), "❌ Bluetooth button not displayed");
        bluetoothBtn.click();

        WebElement getStartedBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        androidUIAutomator("new UiSelector().className(\"android.widget.Button\")")
                )
        );
        Assert.assertTrue(getStartedBtn.isEnabled(), "❌ Bluetooth Get Started not enabled");
        getStartedBtn.click();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement cancelBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().text(\"Cancel\")"
                        )
                )
        );
        cancelBtn.click();

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.appmanagement.ApplicationState;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import io.appium.java_client.android.options.UiAutomator2Options;
//
//import java.net.URL;
//import java.time.Duration;
//
//import static io.appium.java_client.AppiumBy.androidUIAutomator;
//
//public class FirstTest {
//
//    static AndroidDriver driver;
//    static final String APP_PACKAGE = "com.sepl.android.dev";
//    static final String APP_ACTIVITY = "com.sepl.android.MainActivity";
//    static final String APP_PATH = System.getProperty("user.dir") + "/apps/presentation-dev-debug.apk";
//    @BeforeTest
//    @Test(priority = 0)
//    public static void setUp() throws Exception {
//
//        UiAutomator2Options options = new UiAutomator2Options()
//                .setPlatformName("Android")
//                .setAutomationName("UiAutomator2")
//                .setDeviceName("22231FDF6007KF")
//                .setNoReset(true)
//                .setAutoGrantPermissions(true);
//
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
//
//        // ⬇️ CHECK IF APP IS INSTALLED
//        if (!driver.isAppInstalled(APP_PACKAGE)) {
//            System.out.println("📦 App not installed. Installing...");
//            driver.installApp(APP_PATH);
//        } else {
//            System.out.println("✅ App already installed. Skipping install.");
//        }
//        if (driver.queryAppState(APP_PACKAGE) == ApplicationState.RUNNING_IN_FOREGROUND ||
//                driver.queryAppState(APP_PACKAGE) == ApplicationState.RUNNING_IN_BACKGROUND) {
//
//            driver.terminateApp(APP_PACKAGE);
//            Thread.sleep(2000);
//        }
//        driver.activateApp(APP_PACKAGE);
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//    }
//
//    @Test(priority = 1)
//    public void testFullLoginOtp() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        boolean isTextPresent = !driver.findElements(
//                AppiumBy.androidUIAutomator(
//                        "new UiSelector().textContains(\"Privacy Policy\")"
//                )
//        ).isEmpty();
//
//        Assert.assertTrue(isTextPresent, "❌ Privacy Policy text not displayed");
//
//
//        // 1. Sign In Button
//       WebElement signInBtn =  wait.until(ExpectedConditions.elementToBeClickable(
//                AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign in\")\n")
//        ));
//        Assert.assertTrue(signInBtn.isDisplayed(), "Sign In button is not visible");
//        signInBtn.click();
//        WebElement mobileText = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        AppiumBy.androidUIAutomator(
//                                "new UiSelector().textContains(\"Enter your mobile\")"
//                        )
//                )
//        );
//
//        Assert.assertTrue(mobileText.isDisplayed());
////        WebElement mobileSubText = driver.findElement(
////                AppiumBy.androidUIAutomator(
////                        "new UiSelector().text(\"A 4-digit OTP will be sent to this number via SMS for verification.\")"
////                )
////        );
////        Assert.assertTrue(mobileSubText.isDisplayed(), "❌ 'Enter your mobile number' text not displayed");
//        // 2. Enter Mobile Number
//        WebElement mobileField = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
//                )
//        );
//        Assert.assertTrue(mobileField.isDisplayed(), "Mobile number field is not displayed");
//        mobileField.clear();
//        String mobile = "9999999999";
//        mobileField.sendKeys("9999999999");
//
//        // 3. Send OTP Button
//        WebElement sendOtpBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                AppiumBy.androidUIAutomator("new UiSelector().text(\"Send OTP\")")
//        ));
//        Assert.assertTrue(sendOtpBtn.isEnabled(), "Send OTP button is not enabled");
//        sendOtpBtn.click();
//        System.out.println("send...........");
////        WebElement verifyText = driver.findElement(
////                AppiumBy.androidUIAutomator(
////                        "new UiSelector().text(\"Verify your mobile number\")")
////        );
////        Assert.assertTrue(verifyText.isDisplayed(), "❌ 'Verify your mobile number' text not displayed");
////        WebElement verifySubText = driver.findElement(
////                AppiumBy.androidUIAutomator(
////                        "new UiSelector().text(\"Enter the OTP sent via SMS to " +mobile+ " edit\")")
////        );
////        Assert.assertTrue(verifySubText.isDisplayed(), "❌ 'Enter the OTP sent via SMS to \" +mobile+ \" edit' text not displayed");
//        WebElement retryText = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        AppiumBy.androidUIAutomator(
//                                 "new UiSelector().textContains(\"Retry in 1:53\")"
//                        )
//                )
//        );
//        Assert.assertTrue(retryText.isDisplayed());        // 4. Enter 4-digit OTP: 3289
//        String otp = "3289";
//        WebElement enterOtp = wait.until(ExpectedConditions.elementToBeClickable(
//                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
//        ));
//        Assert.assertTrue(enterOtp.isDisplayed(), "OTP field is not visible");
//        enterOtp.sendKeys(otp);
//        WebElement getStarted = wait.until(ExpectedConditions.elementToBeClickable(
//                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\")")
//        ));
//        Assert.assertTrue(getStarted.isEnabled(), "Get Started button is not enabled");
//        getStarted.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//
//    }
//    @Test(priority = 2)
//    public void bluetoothFlow() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement tapBluetooth = wait.until(ExpectedConditions.elementToBeClickable(
//                androidUIAutomator("new UiSelector().description(\"Bluetooth\")")
//        ));
//        Assert.assertTrue(tapBluetooth.isDisplayed(), "Bluetooth button not displayed");
//        tapBluetooth.click();
//        WebElement tapGetStarted = wait.until(ExpectedConditions.elementToBeClickable(
//                androidUIAutomator("new UiSelector().className(\"android.widget.Button\")")
//        ));
//        Assert.assertTrue(tapGetStarted.isEnabled(), "Get Started in Bluetooth flow not enabled");
//        tapGetStarted.click();
////        WebElement tapNext = wait.until(ExpectedConditions.elementToBeClickable(
////                androidUIAutomator("new UiSelector().className(\"android.widget.Button\")")
////        ));
////        tapNext.click();
////        // Navigate to Settings for "Simple Connect" permission
////        driver.activateApp("com.android.settings");  // ✅ Open Settings
////
////        // Search "Simple Connect"
////        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
////                AppiumBy.id("com.android.settings:id/search")
////        ));
////        searchBox.sendKeys("Simple Connect");
////
////        // Tap app result
////        WebElement appResult = wait.until(ExpectedConditions.elementToBeClickable(
////                androidUIAutomator("new UiSelector().textContains(\"Simple Connect\")")
////        ));
////        appResult.click();
////
////        // Permissions → Notifications → Allow
////        driver.findElement(androidUIAutomator(
////                "new UiScrollable(new UiSelector()).scrollTextIntoView(\"Permissions\")"
////        )).click();
////
////        WebElement notifications = driver.findElement(androidUIAutomator(
////                "new UiSelector().text(\"Notifications\")"
////        ));
////        notifications.click();
////
////        WebElement allowBtn = wait.until(ExpectedConditions.elementToBeClickable(
////                androidUIAutomator("new UiSelector().text(\"Allow\")")
////        ));
////        allowBtn.click();
////
////        // Back to your app (3x back)
////        driver.activateApp("com.sepl.android.dev");  // Return to app
////        WebElement getStarted = wait.until(ExpectedConditions.elementToBeClickable(
////                androidUIAutomator("new UiSelector().className(\"android.widget.Button\")")
////        ));
////        getStarted.click();
//    }
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}