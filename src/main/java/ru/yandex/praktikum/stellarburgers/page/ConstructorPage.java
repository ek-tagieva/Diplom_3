package ru.yandex.praktikum.stellarburgers.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage { // Страница Конструктор
    private WebDriver driver;
    private final By bunSection = By.xpath(".//div[1][@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");
    private final By saucesSection = By.xpath(".//div[2][@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");
    private final By stuffingSection = By.xpath(".//div[3][@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");
    private final By textAssembleBurger = By.xpath(".//section[1]/h1[@class='text text_type_main-large mb-5 mt-10' and text()= 'Соберите бургер']");
    private final By textBun = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[1]");
    private final By textSauces = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[2]");
    private final By textStuffing = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]");


    public ConstructorPage(WebDriver driver) {

        this.driver = driver;
    }
    public void clickBunSection(){
        driver.findElement(bunSection).click();
    }
    public void clickSaucesSection(){
        driver.findElement(saucesSection).click();
    }
    public void clickStuffingSection(){
        driver.findElement(stuffingSection).click();
    }

    public String getTextAssembleBurger(){
        return driver.findElement(textAssembleBurger).getText();
    }

    public String getTextBun(){
        return driver.findElement(textBun).getText();
    }
    public String getTextSauces(){
        return driver.findElement(textSauces).getText();
    }
    public String getTextStuffing(){
        return driver.findElement(textStuffing).getText();
    }
}
