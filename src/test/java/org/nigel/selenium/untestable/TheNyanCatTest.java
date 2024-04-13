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

public class TheNyanCatTest {

    private WebDriver driver;
    private static final By NYAN_CAT = By.xpath("//div[@id='nyan-cat-frame']");

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://untestable.site/the_nyan_cat");
        driver.manage().window().maximize();
    }


    @Test
    public void click_on_nyan_cat() throws InterruptedException {
        findElement(NYAN_CAT).click();
        Thread.sleep(2000);
        findElement(NYAN_CAT).click();
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
