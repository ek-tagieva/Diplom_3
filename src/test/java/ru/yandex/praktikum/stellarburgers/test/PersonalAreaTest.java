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
import ru.yandex.praktikum.stellarburgers.client.UserClient;
import ru.yandex.praktikum.stellarburgers.page.*;
import ru.yandex.praktikum.stellarburgers.pojo.UserRegistrationPojo;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;

public class PersonalAreaTest {
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
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccount(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        objMainPage.clickPersonalAccountButton();
        PersonalAreaPage objPersonalAreaPage = new PersonalAreaPage(driver);
        objPersonalAreaPage.getTextProfile();
        String expected = "Профиль";
        String actual = objPersonalAreaPage.getTextProfile();
        assertEquals(expected,actual);
        driver.quit();
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transitionFromPersonalAccountToDesigner(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
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
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();
    }

    @Test
    @DisplayName("Переход из личного кабинета на логотип Stellar Burgers")
    public void transitionFromPersonalAccountToStellarBurgers(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
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
        objConstructorPage.getTextAssembleBurger();
        String expected = "Соберите бургер";
        String actual  = objConstructorPage.getTextAssembleBurger();
        MatcherAssert.assertThat(actual, startsWith(expected));
        driver.quit();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logout(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        objMainPage.clickLoginButton();
        EntrancePage objEntrancePage = new EntrancePage(driver);
        objEntrancePage.loginFields(this.email, this.password);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        objMainPage.clickPersonalAccountButton();
        PersonalAreaPage objPersonalAreaPage = new PersonalAreaPage(driver);
        objPersonalAreaPage.clickExitButton();
        objEntrancePage.getTextInput();
        String expected = "Вход";
        String actual = objEntrancePage.getTextInput();
        assertEquals(expected,actual);
        driver.quit();
    }

    @After
    public void cleanUp(){
        UserClient.deleteUser();
    }
}
