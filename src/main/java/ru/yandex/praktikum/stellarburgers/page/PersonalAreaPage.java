package ru.yandex.praktikum.stellarburgers.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class PersonalAreaPage {// Страница Личный кабинет
    private WebDriver driver;
    private final By exitButton = By.xpath(".//ul/li[3]/button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and text()= 'Выход']");
    private By constructorButton = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a");
    private final By logoStellarBurgers = By.xpath("//*[@id=\"root\"]/div/header/nav/div");
    private final By textProfile = By.xpath(".//ul/li[1]/a[@class='Account_link__2ETsJ text text_type_main-medium text_color_inactive Account_link_active__2opc9' and text()= 'Профиль']");
    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }
    public String  getTextProfile(){
     return driver.findElement(textProfile).getText();
    }
    public void clickLogoStellarBurgers(){
        driver.findElement(logoStellarBurgers).click();
    }
    public void clickConstructorButton(){driver.findElement(constructorButton).click();}
    public void clickExitButton(){
        driver.findElement(exitButton).click();
    }
}
