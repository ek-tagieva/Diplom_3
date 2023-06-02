package ru.yandex.praktikum.stellarburgers.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class ConstructorPage { // Страница Конструктор
    private WebDriver driver;
    private final By bunSection = By.xpath(".//div[@style='display: flex;']/div[1]/span");
    private final By saucesSection = By.xpath(".//div[@style='display: flex;']/div[2]/span");
    private final By stuffingSection = By.xpath(".//div[@style='display: flex;']/div[3]/span");
    private final By textAssembleBurger = By.xpath(".//section[1]/h1[@class='text text_type_main-large mb-5 mt-10' and text()= 'Соберите бургер']");
    private final By textBun = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10'][1]");
    private final By textSauces = By.xpath(".//h2[@class='text text_type_main-medium mb-6 mt-10'][2]");
    private final By textStuffing = By.xpath(".//div[@style='display: flex;']/div[3]/span");
    public ConstructorPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickBunSection(){driver.findElement(bunSection).click();}
    public void clickSaucesSection(){driver.findElement(saucesSection).click();}
    public void clickStuffingSection(){driver.findElement(stuffingSection).click();}
    public String getTextAssembleBurger(){return driver.findElement(textAssembleBurger).getText();}
    public String getTextBun(){
        String text = driver.findElement(textBun).getText();
        return text;
    }
    public String getTextSauces(){
        String text = driver.findElement(textSauces).getText();
        return text;
    }
    public String getTextStuffing(){
        String text = driver.findElement(textStuffing).getText();
        return text;
    }
}
