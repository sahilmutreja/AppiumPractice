import io.appium.java_client.MobileElement;

/**
 * @author sahil.mu
 * @since Feb 25, 2018
 */
public class TestNativeCalculatorApp extends BaseClass {

    public static void main(String[] args) {
        TestNativeCalculatorApp obj = new TestNativeCalculatorApp();
        obj.setup("com.android.calculator2", "com.android.calculator2.Calculator");
        obj.validate();
        obj.tearDown();
    }

    private void validate() {
        try {
            driver.findElementById("com.android.calculator2:id/digit_2").click();
            driver.findElementById("com.android.calculator2:id/op_add").click();
            driver.findElementByXPath("//*[@text='5']").click();
            driver.findElementByXPath("//android.support.v4.view.ViewPager[@resource-id='com.android.calculator2:id/pad_pager']//android.widget.LinearLayout[@index='0']//android.widget.Button[@index='11']").click();
            MobileElement e3 = driver.findElementById("com.android.calculator2:id/result");
            System.out.println("Result = " + e3.getText());
            int result = Integer.parseInt(e3.getText());
            if (result == 7)
                System.out.println("Test case passed.");
            else
                System.out.println("Test case failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
