package ru.yandex.praktikum.stellarburgers.test;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.stellarburgers.page.ConstructorPage;
import ru.yandex.praktikum.stellarburgers.page.MainPage;
import static org.junit.Assert.assertEquals;

public class IngredientsTest {
    private WebDriver driver;
    @Test
    @DisplayName("Переход к разделу булки")
    public void goToBunSection(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.clickSaucesSection();
        objConstructorPage.clickBunSection();
        objConstructorPage.getTextBun();
        String expected = "Булки";
        String actual = objConstructorPage.getTextBun();
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Переход к разделу соусы")
    public void goToSaucesSection(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.clickSaucesSection();
        objConstructorPage.getTextSauces();
        String expected = "Соусы";
        String actual = objConstructorPage.getTextSauces();
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Переход к разделу начинки")
    public void goToStuffingSection(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.clickStuffingSection();
        objConstructorPage.getTextStuffing();
        String expected = "Начинки";
        String actual = objConstructorPage.getTextStuffing();
        assertEquals(expected,actual);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
