package ru.yandex.praktikum.stellarburgers.test;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.stellarburgers.client.UserClient;
import ru.yandex.praktikum.stellarburgers.page.*;
import ru.yandex.praktikum.stellarburgers.pojo.UserRegistrationPojo;
import java.util.Locale;
import static org.hamcrest.CoreMatchers.*;

public class UserLoginTest {
    private WebDriver driver;
    private String email;
    private String password;
    private String name;

    @Before
    public void userCreate(){// Создание  пользователя
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        name = new Faker(Locale.forLanguageTag("ru")).name().firstName();
        UserRegistrationPojo userRegistrationPojo = UserRegistrationPojo.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
        UserClient.createUser(userRegistrationPojo);
    }
    @Test
    @DisplayName("Вход по кнпке Войти в аккаунт на главной странице с помощью Chrome браузера")
    public void loginUsingLoginButtonMainPageChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();

    }
    @Test
    @DisplayName("Вход по кнпке Войти в аккаунт на главной странице с помощью Yandex браузера")
    public void loginUsingLoginButtonMainPageYandex(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();

    }
    @Test
    @DisplayName("Вход через кнопку личный кабинет с помощью Chrome браузера")
    public void loginThroughButtonPersonalAccountChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickPersonalAccountButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();

    }
    @Test
    @DisplayName("Вход через кнопку личный кабинет с помощью Yandex браузера")
    public void loginThroughButtonPersonalAccountYandex(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickPersonalAccountButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();

    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации с помощью Chrome браузера")
    public void loginThroughButtonInRegistrationFormChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRegisterLink();
        RegistrationPage objregistrationPage = new RegistrationPage(driver);
        objregistrationPage.clickLoginButton();
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();

    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации с помощью Yandex браузера")
    public void loginThroughButtonInRegistrationFormYandex(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRegisterLink();
        RegistrationPage objregistrationPage = new RegistrationPage(driver);
        objregistrationPage.clickLoginButton();
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();

    }
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля с помощью Chrome браузера")
    public void loginThroughButtonInPasswordRecoveryFormChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRestorePasswordLink();
        PasswordRecoveryPage objPasswordRecoveryPage = new PasswordRecoveryPage(driver);
        objPasswordRecoveryPage.clickLoginButton();
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();
    }
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля с помощью Yandex браузера")
    public void loginThroughButtonInPasswordRecoveryFormYandex(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRestorePasswordLink();
        PasswordRecoveryPage objPasswordRecoveryPage = new PasswordRecoveryPage(driver);
        objPasswordRecoveryPage.clickLoginButton();
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();
    }
    @After
    public void cleanUp(){
        UserClient.deleteUser();
    }
}
