package ru.yandex.praktikum.stellarburgers.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
public class ConstructorPage { // Страница Конструктор
    private WebDriver driver;
    private final By bunTab = By.xpath(".//div[1][@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");
    private final By saucesTab = By.xpath(".//div[2][@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");
    private final By stuffingTab = By.xpath(".//div[3][@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");
    private final By textAssembleBurger = By.xpath(".//section[1]/h1[@class='text text_type_main-large mb-5 mt-10' and text()= 'Соберите бургер']");
    public ConstructorPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickBunTab(){
        driver.findElement(bunTab).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void clickSaucesTab(){
        driver.findElement(saucesTab).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void clickStuffingTab(){
        driver.findElement(stuffingTab).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public String getBunSectionClass(){
        WebElement bunSection = driver.findElement(By.xpath(".//div[1][contains(@class, 'tab_tab__1SPyG')]"));
        return bunSection.getAttribute("class");
    }
    public String getSaucesSectionClass(){
        WebElement saucesSection = driver.findElement(By.xpath(".//div[2][@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']"));
        return saucesSection.getAttribute("class");
    }
    public String getStuffingSectionClass(){
        WebElement stuffingSection = driver.findElement(By.xpath(".//div[3][@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']"));
        return stuffingSection.getAttribute("class");
    }
    public String getTextAssembleBurger(){return driver.findElement(textAssembleBurger).getText();}
}