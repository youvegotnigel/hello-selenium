package org.nigel.selenium.untestable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TheRussianDollTest {

    private WebDriver driver;
    private static final By COUNTRY_OF_RESIDENCE = By.xpath("//*[@id='input-country-of-residence-button']");
    private static final By CONUNTRY_UKRAINE = By.xpath("//*[@id='input-country-of-residence-menu']/li/div[text()='Ukraine']");

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://untestable.site/the_russian_doll");
        driver.manage().window().maximize();
    }

    @Test
    public void scroll_and_click() throws InterruptedException {

        findElement(COUNTRY_OF_RESIDENCE).click();
        Thread.sleep(2000);
        findElement(CONUNTRY_UKRAINE).click();
        Thread.sleep(2000);
    }

    private WebElement findElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
