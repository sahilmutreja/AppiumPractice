import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * @author sahil.mu
 * @since March 4, 2018
 */
public class TestScreenShot extends BaseClass {

    public static void main(String[] args) throws IOException {
        TestScreenShot test = new TestScreenShot();
        test.setup("com.google.android.apps.messaging", "com.google.android.apps.messaging.ui.ConversationListActivity");
        test.testLongPressAction();
        test.tearDown();
    }

    private void testLongPressAction() throws IOException {
        try {
            MobileElement e = driver.findElementById("com.google.android.apps.messaging:id/conversation_snippet");
            TouchAction a = new TouchAction(driver);
            a.longPress(e).waitAction(Duration.ofSeconds(1)).perform().release();
            if (driver.findElementById("com.google.android.apps.messaging:id/action_add_contact123").isDisplayed())
                System.out.println("Test Case Passed");
            else {
                System.out.println("Test Case Failed");
            }
        } catch (Exception e) {
            getScreenshot(driver);
        }
    }

    public void getScreenshot(AppiumDriver<MobileElement> driver) throws IOException {
        File failureScreenshot = driver.getScreenshotAs((OutputType.FILE));
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String fileName = sdf.format(new Date());
        FileUtils.copyFile(failureScreenshot, new File(System.getProperty("user.dir") + "//src//test//resources//screenshots//" + fileName + ".png"));
        System.out.println("Screenshot captured successfully");
    }
}
