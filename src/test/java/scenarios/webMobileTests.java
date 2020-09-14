package scenarios;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.chrome.GooglePageObject;
import setup.BaseTest;

public class webMobileTests extends BaseTest {
    GooglePageObject googlePageObject;

    @BeforeClass(groups = {"web"})
    public void beforeClass() {
        googlePageObject = ((GooglePageObject) getHomePage());
    }
    @Test(groups = {"web"}, description = "Open Google and search epam ")
    public void simpleWebTest() throws InterruptedException {
        googlePageObject = ((GooglePageObject) getHomePage());
        googlePageObject.open();
        // Make sure that page has been loaded completely
        googlePageObject.waitLoading();
        googlePageObject.search("Epam");
        boolean resultIsDisplayed = googlePageObject
                .getResultPage()
                .resultIsDisplayed();
        Assert.assertTrue(resultIsDisplayed);
    }

}
