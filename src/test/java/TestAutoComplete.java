import io.appium.java_client.MobileElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Keyboard;

/**
 * @author sahil.mu
 * @since March 4, 2018
 */
public class TestAutoComplete extends BaseClass {

    public static void main(String[] args) throws InterruptedException {
        TestAutoComplete test = new TestAutoComplete();
        test.setup("com.example.android.apis", "com.example.android.apis.ApiDemos");
        test.automateAutoCompleteDropDown();
        test.tearDown();
    }

    private void automateAutoCompleteDropDown() throws InterruptedException {
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Auto Complete']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='1. Screen Top']").click();
        MobileElement e = driver.findElementById("com.example.android.apis:id/edit");
        e.click();

//        driver.findElementById("com.example.android.apis:id/edit").sendKeys("indi");
        Thread.sleep(1000);
        Keyboard keyboard = driver.getKeyboard();
//        keyboard.pressKey(Keys.BACK_SPACE);
        keyboard.pressKey(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        keyboard.pressKey(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        keyboard.pressKey(Keys.ENTER);
        Thread.sleep(1000);
        System.out.println("AutoComplete DropDown value" + driver.findElementById("com.example.android.apis:id/edit").getText());


    }

}
