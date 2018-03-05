import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author sahil.mu
 * @since Feb 27, 2018
 */
public class TestSwipeAction extends BaseClass {

    public static void main(String[] args) {
        TestSwipeAction obj = new TestSwipeAction();
        obj.setup("com.example.android.apis", "com.example.android.apis.ApiDemos");
        obj.testSwipe();
        obj.tearDown();
    }

    private void testSwipe() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElementByXPath("//android.widget.TextView[@index='11' and @text='Views']").click();
        for (int i = 0; i < 5; i++) {
            try {
                driver.findElementByXPath("//android.widget.TextView[@index='9' and @text='Tabs']").click();
                break;
            } catch (Exception e) {
                swipeDown();
            }
        }
        driver.findElementByXPath("//android.widget.TextView[@text='5. Scrollable']").click();
        Boolean testResult = false;
        for (int i = 0; i <= 5; i++) {
            try {
                testResult = driver.findElementByXPath("//android.widget.HorizontalScrollView//android.widget.TextView[@text='TAB 19']").isDisplayed();
                break;
            } catch (Exception e) {
                swipeRight();
            }
        }
        if (testResult)
            System.out.println("Pass");
        else
            System.out.println("Fail");
    }

    public void swipeRight() {
        Dimension dim = driver.manage().window().getSize();
        int height = (int) (dim.height * 0.2);
        int width = dim.width;
        int startX = (int) (width * 0.8);
        int endX = (int) (width * 0.2);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(startX, height).waitAction(Duration.ofMillis(200)).moveTo(endX, height).release().perform();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
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
