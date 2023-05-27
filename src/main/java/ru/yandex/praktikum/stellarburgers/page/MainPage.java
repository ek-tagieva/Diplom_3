package ru.yandex.praktikum.stellarburgers.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class MainPage { // Главная страница
    private WebDriver driver;
    private final By loginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()= 'Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//a/p[@class='AppHeader_header__linkText__3q_va ml-2' and text()= 'Личный Кабинет']");
    public MainPage(WebDriver driver) {

        this.driver = driver;
    }
    public void generalAction(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void clickLoginButton(){

        driver.findElement(loginButton).click();
    }
    public void clickPersonalAccountButton(){
        driver.findElement(personalAccountButton).click();
    }

}
