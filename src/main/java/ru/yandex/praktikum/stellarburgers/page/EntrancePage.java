package ru.yandex.praktikum.stellarburgers.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntrancePage { // Страница Вход
    private WebDriver driver;
    private final By emailField = By.xpath(".//fieldset[1]/div/div/input[@class='text input__textfield text_type_main-default' and @name='name']");
    private final By passwordField = By.xpath(".//fieldset[2]/div/div/input[@class='text input__textfield text_type_main-default' and @name= 'Пароль']");
    private final By loginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()= 'Войти']");
    private final By registerLink = By.xpath(".//p[1]/a[@class='Auth_link__1fOlj' and text()= 'Зарегистрироваться']");
    private final By restorePasswordLink = By.xpath(".//p[2]/a[@class='Auth_link__1fOlj' and text()= 'Восстановить пароль']");
    private final By textInput = By.xpath("//*[@id=\"root\"]/div/main/div/h2");

    public EntrancePage(WebDriver driver) {

        this.driver = driver;
    }
    public void loginFields(String email, String password){
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void clickRestorePasswordLink(){

        driver.findElement(restorePasswordLink).click();
    }
    public void clickRegisterLink(){

        driver.findElement(registerLink).click();
    }
    public String getTextInput(){

        return driver.findElement(textInput).getText();
    }
}
