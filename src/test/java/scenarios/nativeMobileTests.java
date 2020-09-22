package scenarios;

import domain.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nativePages.HomePageObject;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    HomePageObject homePageObject;

    @BeforeClass(groups = {"native"})
    public void beforeCheck() {
        homePageObject = new HomePageObject(getDriver());
    }

    @Test(groups = {"native"},
            dataProvider = "UserProvider",
            dataProviderClass = DataProviders.class,
            description = "This test register new user and then sign in")
    public void registerAndSignInTest(User user) {
        homePageObject.getRegisterNewUserBtn().click();
        homePageObject.getRegistrationPage()
                .register(user);
        homePageObject.login(user);

        String activityName = homePageObject
                .getBugetPageObject()
                .getTitle();
        Assert.assertTrue(activityName.startsWith("Budget"));
    }

}
