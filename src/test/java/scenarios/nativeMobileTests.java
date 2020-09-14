package scenarios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.nativePages.HomePageObject;
import setup.BaseTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class nativeMobileTests extends BaseTest {

    HomePageObject homePageObject;

    @BeforeClass(groups = {"native"})
    public void beforeCheck() {
        homePageObject = (HomePageObject) getHomePage();
    }

    @Test(groups = {"native"},
            dataProvider = "UserProvider",
            description = "This test register new user and then Sign In")
    public void registerAndSignInTest(User user) {

        homePageObject.getRegisterNewUserBtn().click();
        homePageObject.getRegistrationPage()
                .register(user);
        homePageObject.login(user);

        String activityName = homePageObject
                .getBugetPageObject()
                .getTitle();
        Assert.assertEquals(activityName, "BudgetActivity");
    }

    @DataProvider(name = "UserProvider")
    public Iterator<Object> provideUser() throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(getUserFilePath());
        List<Object> users = gson.fromJson(reader, new TypeToken<List<User>>() {
        }.getType());
        return users.iterator();
    }

}
