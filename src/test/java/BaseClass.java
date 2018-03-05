import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author sahil.mu
 * @since Feb 25, 2018
 */
public class BaseClass {
    AppiumDriverLocalService service;
    AppiumDriver<MobileElement> driver;

    public void setup(String appPackage, String appActivity) {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        System.out.println("Server started.");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5X");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5556");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.0.0");
        caps.setCapability("appPackage", appPackage);
        caps.setCapability("appActivity", appActivity);
        caps.setCapability("noReset", "true");
        caps.setCapability("fullReset", "false");
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void tearDown() {
        driver.quit();
        service.stop();
        System.out.println("Server stopped.");
    }
}