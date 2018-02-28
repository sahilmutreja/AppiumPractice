import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author sahil.mu
 * @since Feb 27, 2018
 */
public class TestHybridApp {
    AppiumDriverLocalService service;
    AppiumDriver<MobileElement> driver;

    public static void main(String[] args) {
        TestHybridApp obj = new TestHybridApp();
        obj.setup();
        obj.testHybridApp();
        obj.tearDown();
    }

    private void setup() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        System.out.println("Server started.");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.example.android.apis");
        caps.setCapability("appActivity", "com.example.android.apis.ApiDemos");
        caps.setCapability("noReset", "true");
        caps.setCapability("fullReset","false");
        try {
            driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void testHybridApp() {
//        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElementByXPath("//android.widget.TextView[@index='11' and @text='Views']").click();
        for (int i = 0; i <= 5; i++) {
            driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
            try {
                driver.findElementByXPath("//android.widget.TextView[@text='WebView']").click();
                break;
            } catch (Exception e){
                swipeDown();
            }
        }
        switchContext("WEBVIEW");
        System.out.println("Text in WebView: " + driver.findElementByAccessibilityId("Hello World! - 1").getText());
    }

    private void switchContext(String desiredContext) {
        System.out.println("Current Context: " + driver.getContext());
        Set<String> availableContextSet = driver.getContextHandles();
        for (String context:availableContextSet) {
            System.out.println("Available Context: " + context);
            if(context.toUpperCase().contains(desiredContext.toUpperCase()))
                driver.context(context);
        }
        System.out.println("After Swtiching Current Context: " + driver.getContext());
    }

    private void tearDown() {
        driver.quit();
        service.stop();
        System.out.println("Server stopped.");
    }

    private void swipeDown(){
        Dimension dim = driver.manage().window().getSize();
        int height = dim.height;
        int width = dim.width;
        int startY = (int) (height*0.8);
        int endY = (int) (height*0.2);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(width/2, startY).waitAction(Duration.ofMillis(200)).moveTo(width/2, endY).release().perform();
    }
}
