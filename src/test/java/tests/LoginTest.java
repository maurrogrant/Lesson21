package tests;

import config.ConfigTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.TwoFactorAuthPage;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginTest extends ConfigTest {
    private WebDriver driver;

    @Before
    public void init() {
        ConfigTest.initConfig();
        driver = ConfigTest.driver;
    }

    @Test
    public void testFullScenario() {

        TwoFactorAuthPage twoFactorAuthPage = new LoginPage(driver)
                .navigateTo()
                .performLogin("demo", "demo");

        assertNotNull("Страница двухфакторной аутентификации не отображается", twoFactorAuthPage.isPageOpened());

        MainPage mainPage = twoFactorAuthPage.completeAuth("0000");

        mainPage.isCardsSectionDisplayed()
                .isFinancialFreedomBlockDisplayed();

        assertNotNull("Главная страница не отображается после входа", mainPage.isPageOpened());
        assertTrue("Сумма в блоке 'Финансовая свобода' не соответствует формату",
                mainPage.isFinancialFreedomAmountValid());
        System.out.println("Сумма финансовой свободы: " + mainPage.getFinancialFreedomAmount());
        assertTrue("Отсутствует секция с картами", mainPage.getCardsCount() >= 3);
        assertTrue("Неправильный формат всплывающей подсказки по карте Travel",
                mainPage.hoverOverTravelCard().verifyTravelCardTooltip());
        System.out.println("Карта Travel: " + mainPage.getTravelCardTooltip());
        assertTrue("Неправильный формат всплывающей подсказки по карте Gold",
                mainPage.hoverOverGoldCard().verifyGoldCardTooltip());
        System.out.println("Карта Gold: " + mainPage.getGoldCardTooltip());
    }

    @After
    public void tearDown() {
        ConfigTest.quit();
    }
}
