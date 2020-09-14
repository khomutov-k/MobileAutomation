package pageObjects.nativePages;

import domain.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HomePageObject extends NativePageObject implements ILogin {

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    WebElement loginField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    WebElement passwordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    WebElement registerNewUserBtn;

    public IRegister getRegistrationPage() {
        return new RegistrationPageObject(appiumDriver);
    }

    public BudgetPageObject getBugetPageObject() {
        return new BudgetPageObject(appiumDriver);
    }

    public HomePageObject(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public void login(User user) {
        loginField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        signInBtn.click();
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }

    public WebElement getLoginField() {
        return loginField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getRegisterNewUserBtn() {
        return registerNewUserBtn;
    }

}
