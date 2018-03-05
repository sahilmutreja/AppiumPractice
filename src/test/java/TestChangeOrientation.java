import org.openqa.selenium.ScreenOrientation;

/**
 * @author sahil.mu
 * @since March 3, 2018
 */
public class TestChangeOrientation extends BaseClass {

    public static void main(String[] args) {
        TestChangeOrientation test = new TestChangeOrientation();
        test.setup("com.google.android.apps.messaging", "com.google.android.apps.messaging.ui.ConversationListActivity");
        test.testChangeOrientage();
        test.tearDown();
    }

    public void testChangeOrientage() {
        System.out.println("Current Orientation: " + driver.getOrientation());
        driver.rotate(ScreenOrientation.LANDSCAPE);
        System.out.println("After rotation, current orientation: " + driver.getOrientation());
    }
}
