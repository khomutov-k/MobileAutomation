package pageObjects.nativePages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BudgetPageObject {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/" +
            "android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/android.widget.TextView")
    private WebElement title;

    public BudgetPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public String getTitle() {
        return title.getText();
    }
}
