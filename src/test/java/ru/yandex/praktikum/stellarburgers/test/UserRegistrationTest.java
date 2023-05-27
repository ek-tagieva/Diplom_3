package ru.yandex.praktikum.stellarburgers.test;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.stellarburgers.client.UserClient;
import ru.yandex.praktikum.stellarburgers.page.EntrancePage;
import ru.yandex.praktikum.stellarburgers.page.MainPage;
import ru.yandex.praktikum.stellarburgers.page.RegistrationPage;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;


public class UserRegistrationTest {
    private WebDriver driver;
    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successfulUserRegistration(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRegisterLink();
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.fieldForRegistration();
        objEntrancePage.getTextInput();
        String expected = "Вход";
        String actual = objEntrancePage.getTextInput();
        assertEquals(expected,actual);
        driver.quit();

    }
    @Test
    @DisplayName("Ошибка пароля при регистрации пользователя")
    public void errorPasswordUserRegistration(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRegisterLink();
        RegistrationPage objRegistrationPage = new RegistrationPage(driver);
        objRegistrationPage.fieldForRegistrationError();
        objRegistrationPage.getTextIncorrectPassword();
        String expected = "Некорректный пароль";
        String actual  = objRegistrationPage.getTextIncorrectPassword();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();
    }

    @After
    public void cleanUp(){
        UserClient.deleteUser();
    }
}
