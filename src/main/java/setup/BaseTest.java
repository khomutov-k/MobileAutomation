package setup;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    static String resourceFilePath = "./src/test/resources/";
    private AppiumDriver appiumDriver; // singleton

    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    @Parameters({"platformName", "appType", "deviceName", "udid", "osVersion","resourceFilePath", "browserName",
            "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, @Optional("") String deviceName,String udid,
                      @Optional("9.0") String osVersion,
                      @Optional("src/test/resources/") String resourceFilePath,
                      @Optional("") String browserName,
                      @Optional("") String appPackage, @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        System.out.println("Before: app type - " + appType);
        BaseTest.resourceFilePath = resourceFilePath;
        setAppiumDriver(appType,platformName,udid, osVersion, deviceName,
                browserName, appPackage,appActivity,bundleId);
    }



    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("TearDown");
        if (appiumDriver != null) {
            appiumDriver.closeApp();
            appiumDriver.quit();
        }
    }

    private void setAppiumDriver(String appType,String platformName,String udid, String osVersion, String deviceName,
                                 String browserName, String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
//        capabilities.setCapability("device", deviceName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("os_version", osVersion);
//        capabilities.setCapability("project", "EpamTA Project");
//        capabilities.setCapability("build", "My First Build");
//        capabilities.setCapability("name", appType + " tests launch");
        if (appType.equalsIgnoreCase("web")) {
            capabilities.setCapability("browserName", browserName);
//            capabilities.setCapability("chromedriverDisableBuildCheck", "true");
            capabilities.setCapability("autoAcceptAlerts", true);
        } else {
            capabilities.setCapability("bundleId",bundleId);
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);
        }

        try {
            final String fileName = "secrets.json";
            Reader reader = new FileReader(BaseTest.getResourceFilePath()+ fileName);
            JsonObject jsonData = new JsonParser().parse(reader).getAsJsonObject();
            final String token = jsonData.get("token").getAsString();
            appiumDriver = new AppiumDriver<>(new URL("https://EPM-TSTF:" + token + "@mobilecloud.epam.com/wd/hub"), capabilities);
        } catch (MalformedURLException | FileNotFoundException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static String getResourceFilePath() {
        return resourceFilePath;
    }
}
