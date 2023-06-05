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
    private String expectedActiveSection = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.generalAction();
    }
    @Test
    @DisplayName("Переход к разделу булки")
    public void goToBunSection(){
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.clickSaucesTab();
        objConstructorPage.clickBunTab();
        String actualActiveSection = objConstructorPage.getBunSectionClass();
        assertEquals(expectedActiveSection, actualActiveSection);
    }
    @Test
    @DisplayName("Переход к разделу соусы")
    public void goToSaucesSection(){
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.clickSaucesTab();
        String actualActiveSection = objConstructorPage.getSaucesSectionClass();
        assertEquals(expectedActiveSection, actualActiveSection);
    }
    @Test
    @DisplayName("Переход к разделу начинки")
    public void goToStuffingSection(){
        ConstructorPage objConstructorPage = new ConstructorPage(driver);
        objConstructorPage.clickStuffingTab();
        String actualActiveSection = objConstructorPage.getStuffingSectionClass();
        assertEquals(expectedActiveSection, actualActiveSection);
    }
    @After
    public void teardown(){driver.quit();}
}
