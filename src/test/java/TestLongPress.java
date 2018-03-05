import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import java.time.Duration;

/**
 * @author sahil.mu
 * @since March 4, 2018
 */
public class TestLongPress extends BaseClass {

    public static void main(String[] args) {
        TestLongPress test = new TestLongPress();
        test.setup("com.google.android.apps.messaging", "com.google.android.apps.messaging.ui.ConversationListActivity");
        test.testLongPressAction();
        test.tearDown();
    }

    private void testLongPressAction() {
        MobileElement e = driver.findElementById("com.google.android.apps.messaging:id/conversation_snippet");
        TouchAction a = new TouchAction(driver);
        a.longPress(e).waitAction(Duration.ofSeconds(1)).perform().release();
        if (driver.findElementById("com.google.android.apps.messaging:id/action_add_contact").isDisplayed())
            System.out.println("Test Case Passed");
        else
            System.out.println("Test Case Failed");
    }
}
