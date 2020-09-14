package pageObjects.chrome;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.PageObject;

import java.util.List;

public class GoogleResultsPageObject extends PageObject {

    @FindBy(css = "#search div.g")
    List<WebElement> resultStrings;

    public boolean resultIsDisplayed() {
        return resultStrings != null;
    }

    public GoogleResultsPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
