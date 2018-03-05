import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author sahil.mu
 * @since March 1, 2018
 */
public class TestOTPMessage extends BaseClass {

    public static void main(String[] args) {
        TestOTPMessage test = new TestOTPMessage();
        test.setup("com.example.android.apis", "com.example.android.apis.ApiDemos");
        test.verifyOTPValue();
        test.tearDown();
    }

    private void verifyOTPValue() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElementByXPath("//android.widget.TextView[@text='OS']").click();
        for (int i = 0; i <= 5; i++) {
            try {
                driver.findElementByXPath("//android.widget.TextView[@text='SMS Messaging']").click();
                break;
            } catch (Exception ex) {
                swipeDown();
            }
        }
        driver.findElementById("com.example.android.apis:id/sms_recipient").sendKeys("12345");
        driver.findElementById("com.example.android.apis:id/sms_content").sendKeys("DEMO OTP: 1234");
        driver.findElementById("com.example.android.apis:id/sms_send_message").click();
        String appPackage = "com.google.android.apps.messaging";
        String appActivity = "com.google.android.apps.messaging.ui.ConversationListActivity";
        switchApp(appPackage, appActivity);
        String otpValue = driver.findElementByXPath("//android.widget.TextView[@index='1' and @resource-id='com.google.android.apps.messaging:id/conversation_snippet']").getText();
        System.out.println("OTP Value = " + otpValue);
    }

    public void switchApp(String appPackage, String appActivity) {
        Activity activity = new Activity(appPackage, appActivity);
        ((AndroidDriver) driver).startActivity(activity);
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
