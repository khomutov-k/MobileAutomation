package pageObjects.chrome;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePageObject extends WebPageObject{

    @FindBy(css = "input[name=q]")
    WebElement inputField;

    public GooglePageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void open() {
        appiumDriver.get("http://google.com");
    }

    public void waitLoading() {
        new WebDriverWait(appiumDriver, 15).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    public void search(String string) {
        inputField.sendKeys(string);
        inputField.sendKeys(Keys.ENTER);
    }

    public GoogleResultsPageObject getResultPage() {
        waitLoading();
        return new GoogleResultsPageObject(appiumDriver);
    }

}
