package ru.yandex.praktikum.stellarburgers.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Random;
public class RegistrationPage {// Страница Регистрация
    private WebDriver driver;
    private final By nameField = By.xpath(".//fieldset[1]/div/div/input[@class='text input__textfield text_type_main-default' and @name='name']");
    private final By emailField = By.xpath(".//fieldset[2]/div/div/input[@class='text input__textfield text_type_main-default' and @name='name']");
    private final By passwordField = By.xpath(".//fieldset[3]/div/div/input[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    private By passwordError = By.xpath(".//fieldset[3]/div/p[@class='input__error text_type_main-default' and text()= 'Некорректный пароль']");
    private final By registerButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()= 'Зарегистрироваться']");
    private final By loginButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    private String getGenerateUsername() {
        String[] usernames = {"john", "mary", "peter", "emma", "alex", "olivia", "david", "sophia"};
        Random random = new Random();
        int randomNum = random.nextInt(usernames.length);
        return usernames[randomNum];
    }
    private String getGenerateEmail() {
        String email = "";
        String[] domains = {"gmail.com", "yandex.com", "hotmail.com", "outlook.com"};
        Random random = new Random();
        int randomNum = random.nextInt(1000);
        email = "user" + randomNum + "@" + domains[random.nextInt(domains.length)];
        return email;
    }
    private String getGeneratePassword() {
        String password = "";
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()_+-=";
        String allChars = upperChars + lowerChars + numbers + specialChars;
        Random random = new Random();
        int passwordLength = 6 + random.nextInt(4);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allChars.length());
            sb.append(allChars.charAt(randomIndex));
        }
        password = sb.toString();
        return password;
    }
    public void fieldForRegistration() {
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(this.getGenerateUsername());
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(this.getGenerateEmail());
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(this.getGeneratePassword());
        driver.findElement(registerButton).click();
    }
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
    private String getGeneratePasswordError(){
        String password = "";
        String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
        Random random = new Random();
        int passwordLength = 1 + random.nextInt(5);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allChars.length());
            sb.append(allChars.charAt(randomIndex));
        }
        password = sb.toString();
        return password;
    }
    public void fieldForRegistrationError(){
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(this.getGenerateUsername());
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(this.getGenerateEmail());
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(this.getGeneratePasswordError());
        driver.findElement(registerButton).click();
    }
    public String getTextIncorrectPassword(){return driver.findElement(passwordError).getText();}
}

