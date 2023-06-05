package ru.yandex.praktikum.stellarburgers.test;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.praktikum.stellarburgers.client.UserClient;
import ru.yandex.praktikum.stellarburgers.page.*;
import ru.yandex.praktikum.stellarburgers.pojo.UserRegistrationPojo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import static org.hamcrest.CoreMatchers.startsWith;
@RunWith(Parameterized.class)
public class UserLoginTest {
    WebDriver driver;
    private final boolean chrome;
    private final boolean yandex;
    private String email;
    private String password;
    private String name;
    ValidatableResponse response;
    String accessToken;
    public UserLoginTest(boolean chrome, boolean yandex) {
        this.chrome = chrome;
        this.yandex = yandex;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{true, false}, {false, true}};
        return Arrays.asList(data);
    }
    @Before
    public void setUp(){
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        name = new Faker(Locale.forLanguageTag("ru")).name().firstName();
        UserRegistrationPojo userRegistrationPojo = UserRegistrationPojo.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
        response = UserClient.createUser(userRegistrationPojo);
        accessToken = response.extract().path("accessToken");
        if (chrome) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (yandex) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            driver = new ChromeDriver(options);
        }
        driver.get("https://stellarburgers.nomoreparties.site/");
    }
    @Test
    @DisplayName("Вход по кнпке Войти в аккаунт на главной странице")
    public void loginUsingLoginButtonMainPage(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
       String expected = "Соберите бургер";
       String actual  = objConstructorPage.getTextAssembleBurger();
       MatcherAssert.assertThat(actual, startsWith(expected));
    }
    @Test
    @DisplayName("Вход через кнопку личный кабинет")
    public void loginThroughButtonPersonalAccount(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickPersonalAccountButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginThroughButtonInRegistrationForm(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRegisterLink();
        RegistrationPage objregistrationPage = new RegistrationPage(driver);
        objregistrationPage.clickLoginButton();
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
    }
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginThroughButtonInPasswordRecoveryForm(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.clickRestorePasswordLink();
        PasswordRecoveryPage objPasswordRecoveryPage = new PasswordRecoveryPage(driver);
        objPasswordRecoveryPage.clickLoginButton();
        objEntrancePage.loginFields(this.email, this.password);
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
    }
    @After
    public void cleanUp(){
        driver.quit();
        UserClient.deleteUser(accessToken);
    }
}
