package ru.yandex.praktikum.stellarburgers.test;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.stellarburgers.page.ConstructorPage;
import ru.yandex.praktikum.stellarburgers.page.MainPage;
import static org.junit.Assert.assertEquals;
public class IngredientsTest {
    private WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }
    @Test
    @DisplayName("Переход к разделу начинки")
    public void goToStuffingSection(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.clickStuffingSection();
        assertEquals("Начинки", objConstructorPage.getTextStuffing());
    }
    @Test
    @DisplayName("Переход к разделу соусы")
    public void goToSaucesSection(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.clickSaucesSection();
        assertEquals("Соусы", objConstructorPage.getTextSauces());
    }
    @Test
    @DisplayName("Переход к разделу булки")
    public void goToBunSection(){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.clickSaucesSection();
        objConstructorPage.clickBunSection();
        assertEquals("Булки", objConstructorPage.getTextBun());
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
