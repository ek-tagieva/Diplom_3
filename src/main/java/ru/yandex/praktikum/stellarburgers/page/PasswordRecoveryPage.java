package ru.yandex.praktikum.stellarburgers.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {// Страница Восстановления пароля
    private WebDriver driver;
    private final By loginButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()= 'Войти']");
    public PasswordRecoveryPage(WebDriver driver) {

        this.driver = driver;
    }
    public void clickLoginButton(){

        driver.findElement(loginButton).click();
    }
}
