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
        googlePageObject = new GooglePageObject(getDriver());
    }

    @Test(groups = {"web"},
            dataProvider = "Search string provider",
            dataProviderClass = DataProviders.class,
            description = "Open Google and search 'epam' in google query")
    public void simpleWebTest(String searchString) throws InterruptedException {
        googlePageObject.open();
        // Make sure that page has been loaded completely
        googlePageObject.waitLoading();
        googlePageObject.search(searchString);
        boolean resultIsDisplayed = googlePageObject
                .getResultPage()
                .resultIsDisplayed();
        Assert.assertTrue(resultIsDisplayed);
    }

}
