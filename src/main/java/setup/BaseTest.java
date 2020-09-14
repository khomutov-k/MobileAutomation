package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    PageObject homePage;
    String userFilePath ;

    private static AppiumDriver<WebElement> appiumDriver; // singleton

    public static AppiumDriver getDriver() {
        return appiumDriver; }

    @Parameters({"platformName","appType","deviceName","userFilePath","browserName","app"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName,
                      @Optional("src/test/resources/Users.json") String userFilePath,
                      @Optional("") String browserName, @Optional("") String app) throws Exception {
        System.out.println("Before: app type - "+appType);
        setAppiumDriver(platformName, deviceName, browserName, app);
        setHomePage(new PageObject(appType, appiumDriver).getPageObject());
        this.userFilePath = userFilePath;
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("deviceName",deviceName);

        if(app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck","true");
        capabilities.setCapability("autoAcceptAlerts", true);
        try {
            appiumDriver = new AppiumDriver<>(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public String getUserFilePath() {
        return userFilePath;
    }

    public void setHomePage(PageObject homePage) {
        this.homePage = homePage;
    }

    public PageObject getHomePage() {
        return homePage;
    }
}
