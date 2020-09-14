package pageObjects.chrome;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject {
    AppiumDriver appiumDriver;

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
        this.appiumDriver = appiumDriver;
    }
}
