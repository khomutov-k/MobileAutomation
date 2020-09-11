package pageObjects.chrome;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleResultsPageObject {

    @FindBy(css = "#search div.g")
    List<WebElement> resultStrings;

    public boolean resultIsDisplayed() {
        return resultStrings != null;
    }

    public GoogleResultsPageObject(AppiumDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
