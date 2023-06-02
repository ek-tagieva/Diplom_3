package ru.yandex.praktikum.stellarburgers.test;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.stellarburgers.page.EntrancePage;
import ru.yandex.praktikum.stellarburgers.page.MainPage;
import ru.yandex.praktikum.stellarburgers.page.RegistrationPage;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
public class UserRegistrationTest {
    private WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }
    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successfulUserRegistration(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRegisterLink();
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.fieldForRegistration();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        EntrancePage entrancePage = new EntrancePage(driver);
        assertEquals(entrancePage.getLoginPageUrl(), driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Ошибка пароля при регистрации пользователя")
    public void errorPasswordUserRegistration(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRegisterLink();
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.fieldForRegistrationError();
        String expected = "Некорректный пароль";
        String actual  = objRegistrationPage.getTextIncorrectPassword();
        MatcherAssert.assertThat(actual, startsWith(expected));
    }
    @After
    public void cleanUp(){
        driver.quit();
    }
}
