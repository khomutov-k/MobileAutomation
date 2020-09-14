package pageObjects.chrome;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleResultsPageObject extends WebPageObject{

    @FindBy(css = "#search div.g")
    List<WebElement> resultStrings;

    public GoogleResultsPageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public boolean resultIsDisplayed() {
        return resultStrings != null;
    }

}
