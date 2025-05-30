package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    @FindBy(id = "username")
    private WebElement inputUsername;

    @FindBy(name = "password")
    private WebElement inputPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage isPageOpened() {
        wait.until(d -> btnLogin.isDisplayed());
        return this;
    }

    public LoginPage navigateTo() {
        driver.get("https://idemo.bspb.ru");
        return this.isPageOpened();
    }

    public LoginPage fillUsername(String username) {
        inputUsername.clear();
        inputUsername.sendKeys(username);
        return this;
    }

    public LoginPage fillPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return this;
    }

    public TwoFactorAuthPage submitLogin() {
        btnLogin.click();
        return new TwoFactorAuthPage(driver).isPageOpened();
    }

    public TwoFactorAuthPage performLogin(String username, String password) {
        return fillUsername(username)
                .fillPassword(password)
                .submitLogin();
    }
}
