import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author sahil.mu
 * @since Feb 25, 2018
 */
public class TestNativeCalculatorApp {
    AppiumDriverLocalService service;
    AppiumDriver<MobileElement> driver;

    public static void main(String[] args) {
        TestNativeCalculatorApp obj = new TestNativeCalculatorApp();
        obj.setup();
        obj.validate();
        obj.tearDown();
    }

    private void setup() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        System.out.println("Server started.");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.android.calculator2");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        caps.setCapability("noReset", "true");
        caps.setCapability("fullReset","false");
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void validate() {
       try{
           driver.findElementById("com.android.calculator2:id/digit_2").click();
           driver.findElementById("com.android.calculator2:id/op_add").click();
           driver.findElementByXPath("//*[@text='5']").click();
           driver.findElementByXPath("//android.support.v4.view.ViewPager[@resource-id='com.android.calculator2:id/pad_pager']//android.widget.LinearLayout[@index='0']//android.widget.Button[@index='11']").click();
           MobileElement e3 = driver.findElementByXPath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");
           System.out.println("Result = " + e3.getText());
           int result = Integer.parseInt(e3.getText());
           if( result == 7)
               System.out.println("Test case passed.");
           else
               System.out.println("Test case failed.");
       }catch (Exception e) {
           e.printStackTrace();
       }
    }

    private void tearDown() {
        driver.quit();
        service.stop();
        System.out.println("Server stopped.");
    }
}
