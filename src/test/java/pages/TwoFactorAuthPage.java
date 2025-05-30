package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TwoFactorAuthPage extends BasePage {

    @FindBy(id = "otp-code")
    private WebElement otpCodeField;

    @FindBy(id = "login-otp-button")
    private WebElement btnLogininOtp;

    @FindBy(xpath = "//div[contains(text(),'Отправили СМС код на ваш номер телефона')]")
    private WebElement confirmText;

    public TwoFactorAuthPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public TwoFactorAuthPage isPageOpened() {
        wait.until(d -> confirmText.isDisplayed() && btnLogininOtp.isDisplayed());
        return this;
    }

    public TwoFactorAuthPage enterOtpCode(String code) {
        otpCodeField.clear();
        otpCodeField.sendKeys(code);
        return this;
    }

    public MainPage clickLoginOtpButton() {
        btnLogininOtp.click();
        return new MainPage(driver).isPageOpened();
    }

    public MainPage completeAuth(String code) {
        return enterOtpCode(code).clickLoginOtpButton();
    }
}
