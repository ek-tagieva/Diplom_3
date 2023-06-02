package ru.yandex.praktikum.stellarburgers.test;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.stellarburgers.client.UserClient;
import ru.yandex.praktikum.stellarburgers.page.*;
import ru.yandex.praktikum.stellarburgers.pojo.UserRegistrationPojo;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertEquals;
public class PersonalAreaTest {
    private WebDriver driver;
    private String email;
    private String password;
    private String name;
    String accessToken;
    ValidatableResponse response;
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
       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
       driver.get("https://stellarburgers.nomoreparties.site/");
    }
    @Test
    @DisplayName("Переход в личный кабинет")
     public void goToPersonalAccount(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        objMainPage.clickPersonalAccountButton();
        PersonalAreaPage objPersonalAreaPage = new PersonalAreaPage(driver);
        String expected = "Профиль";
        String actual = objPersonalAreaPage.getTextProfile();
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transitionFromPersonalAccountToDesigner(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        objMainPage.clickPersonalAccountButton();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PersonalAreaPage objPersonalAreaPage = new PersonalAreaPage(driver);
        objPersonalAreaPage.clickConstructorButton();
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
    }
    @Test
    @DisplayName("Переход из личного кабинета на логотип Stellar Burgers")
    public void transitionFromPersonalAccountToStellarBurgers(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        objMainPage.clickPersonalAccountButton();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PersonalAreaPage objPersonalAreaPage = new PersonalAreaPage(driver);
        objPersonalAreaPage.clickLogoStellarBurgers();
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
    }
    @Test
    @DisplayName("Выход из аккаунта")
    public void logout(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        objMainPage.clickPersonalAccountButton();
        PersonalAreaPage objPersonalAreaPage = new PersonalAreaPage(driver);
        objPersonalAreaPage.clickExitButton();
        String expected = "Вход";
        String actual = objEntrancePage.getTextInput();
        assertEquals(expected,actual);
    }
    @After
    public void cleanUp(){
        driver.quit();
        UserClient.deleteUser(accessToken);
    }
}
