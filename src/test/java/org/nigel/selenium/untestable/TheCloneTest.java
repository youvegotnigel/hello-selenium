package org.nigel.selenium.untestable;

import org.openqa.selenium.*;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TheCloneTest {

    private WebDriver driver;
    private static final By CHICAGO_BUTTON = By.xpath("//button[@id='visit-chicago-btn']");

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://untestable.site/the_clone");
        driver.manage().window().maximize();
    }

    @Test
    public void click_on_visit_chicago() throws InterruptedException {
        findElement(CHICAGO_BUTTON).click();
        Thread.sleep(2000);
    }

    @Test
    public void use_actions_class_to_click_on_visit_chicago() throws InterruptedException {

        Rectangle rect = findElement(CHICAGO_BUTTON).getRect();
        int x = rect.x + rect.width/2;
        int y = rect.y + rect.height/2;

        Actions actions = new Actions(driver);
        actions.moveToLocation(x,y).click().perform();
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
