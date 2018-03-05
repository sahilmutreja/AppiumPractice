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
public class TestHybridApp extends BaseClass {

    public static void main(String[] args) {
        TestHybridApp obj = new TestHybridApp();
        obj.setup("com.example.android.apis", "com.example.android.apis.ApiDemos");
        obj.testHybridApp();
        obj.tearDown();
    }

    private void testHybridApp() {
        driver.findElementByXPath("//android.widget.TextView[@index='11' and @text='Views']").click();
        for (int i = 0; i <= 5; i++) {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            try {
                driver.findElementByXPath("//android.widget.TextView[@text='WebView']").click();
                break;
            } catch (Exception e) {
                swipeDown();
            }
        }
        switchContext("WEBVIEW");
        System.out.println("Text in WebView: " + driver.findElementByXPath("//android.view.View[@text='Hello World! - 1']").getText());
    }

    public void switchContext(String desiredContext) {
        System.out.println("Current Context: " + driver.getContext());
        Set<String> availableContextSet = driver.getContextHandles();
        for (String context : availableContextSet) {
            System.out.println("Available Context: " + context);
            if (context.toUpperCase().contains(desiredContext.toUpperCase()))
                driver.context(context);
        }
        System.out.println("After Swtiching Current Context: " + driver.getContext());
    }

    public void swipeDown() {
        Dimension dim = driver.manage().window().getSize();
        int height = dim.height;
        int width = dim.width;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(width / 2, startY).waitAction(Duration.ofMillis(200)).moveTo(width / 2, endY).release().perform();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    }
}
