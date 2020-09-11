package pageObjects.nativePages;

import domain.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPageObject implements IRegister{

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    WebElement emailField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    WebElement usernameField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    WebElement passwordField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    WebElement passwordConfirmationField;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    WebElement registerBtn;
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_cancel_button")
    WebElement cancelBtn;

    public RegistrationPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
    }

    public void register(User user) {
        emailField.sendKeys(user.getEmail());
        usernameField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        passwordConfirmationField.sendKeys((user.getPassword()));
        registerBtn.click();
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getPasswordConfirmationField() {
        return passwordConfirmationField;
    }

    public WebElement getRegisterBtn() {
        return registerBtn;
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }
}
