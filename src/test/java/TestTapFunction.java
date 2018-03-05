import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author sahil.mu
 * @since March 4, 2018
 */
public class TestTapFunction {
    AppiumDriver<MobileElement> driver;
    AppiumDriverLocalService service;

    @BeforeMethod
    @Parameters({"deviceName"})
    private void setup(@Optional("Pixel") String deviceName) {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        System.out.println("Appium Server Started.");
        System.out.println("Test on " + deviceName);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        if (deviceName.equalsIgnoreCase("Nexus")) {
            caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5X");
        } else if(deviceName.equalsIgnoreCase("Pixel")){
            caps.setCapability(MobileCapabilityType.UDID, "emulator-5556");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2");
        }
        caps.setCapability("appPackage", "com.example.android.apis");
        caps.setCapability("appActivity", "com.example.android.apis.ApiDemos");
        caps.setCapability(MobileCapabilityType.NO_RESET, true);
        caps.setCapability(MobileCapabilityType.FULL_RESET, false);
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    private void tearDown() {
        driver.quit();
        service.stop();
        System.out.println("Appium Server Stopped");
    }

    @Test
    public void testTapFunction() {
        TouchAction action = new TouchAction(driver);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//android.widget.TextView[@text='Views']")));
        MobileElement e = driver.findElementByXPath("//android.widget.TextView[@text='Views']");
        action.tap(e).perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//android.widget.TextView[@text='Auto Complete']")));
        e = driver.findElementByXPath("//android.widget.TextView[@text='Auto Complete']");
        action.tap(e).perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//android.widget.TextView[@text='1. Screen Top']")));
        if (driver.findElementByXPath("//android.widget.TextView[@text='1. Screen Top']").isDisplayed())
            System.out.println("Test Case Passed");
        else
            System.out.println("Test Case Failed");
    }
}

