package org.nigel.selenium.untestable;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TheDogeBallTest {

    private WebDriver driver;
    private static final By LOGIN_BUTTON = By.xpath("//button[@id='nav-login-btn']");

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://untestable.site/the_dodgeball");
        driver.manage().window().maximize();
    }

    @Test
    public void click_on_login_button() throws InterruptedException {
        findElement(LOGIN_BUTTON).click();
        Thread.sleep(2000);
    }

    @Test
    public void use_actions_class_to_click_on_login_button() throws InterruptedException {

        Rectangle rect = findElement(LOGIN_BUTTON).getRect();
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
