package pageObjects.nativePages;

import domain.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.PageObject;

public class HomePageObject extends PageObject implements ILogin {

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    WebElement loginField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    WebElement passwordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    WebElement registerNewUserBtn;

    AppiumDriver appiumDriver;

    public IRegister getRegistrationPage() {
        return new RegistrationPageObject(appiumDriver);
    }

    public BudgetPageObject getBugetPageObject() {
        return new BudgetPageObject(appiumDriver);
    }

    public HomePageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        this.appiumDriver = appiumDriver;
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
