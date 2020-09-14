package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.chrome.GooglePageObject;
import pageObjects.nativePages.HomePageObject;

public class PageObject {

    PageObject somePageObject; // it should be set of web page or EPAM Test App WebElements
    AppiumDriver appiumDriver;

    public PageObject(String appType, AppiumDriver appiumDriver) throws Exception {

        System.out.println("Current app type: "+appType);
        switch(appType){
            case "web":
                somePageObject = new GooglePageObject(appiumDriver);
                break;
            case "native":
                somePageObject = new HomePageObject(appiumDriver);
                break;
            default: throw new Exception("Can't create a page object for "+appType);
        }

    }

    public PageObject getPageObject() {
        return somePageObject;
    }

    public PageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
        this.appiumDriver = appiumDriver;
    }

    public PageObject() {
    }
}
