package pageObjects.chrome;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageObject;

public class GooglePageObject extends PageObject {

    @FindBy(css = "input[name=q]")
    WebElement inputField;

    AppiumDriver appiumDriver;

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

    public GoogleResultsPageObject getResultPage() throws InterruptedException {
        waitLoading();
        acceptNativePopUp();
        waitLoading();
        return new GoogleResultsPageObject(appiumDriver);
    }

    private void acceptNativePopUp() {
        appiumDriver.context("NATIVE_APP"); // set context to NATIVE
        appiumDriver.findElement(By.id("android:id/button1")).click();
        appiumDriver.context("CHROMIUM");
    }

    public GooglePageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
}
