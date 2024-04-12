package org.nigel.selenium.untestable;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class GlassDoorTest {

    private WebDriver driver;
    private static final By CHICAGO_BUTTON = By.xpath("//button[@id='visit-chicago-btn']");

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://untestable.site/the_glass_door");
        driver.manage().window().maximize();
    }

    @Test
    public void click_on_visit_chicago() throws InterruptedException {
        findElement(CHICAGO_BUTTON).click();
        Thread.sleep(2000);
    }

    @Test
    public void use_top_element_to_click_on_visit_chicago() throws InterruptedException {

        Rectangle rect = findElement(CHICAGO_BUTTON).getRect();
        int x = rect.x + rect.width/2;
        int y = rect.y + rect.height/2;

        System.out.println("X = " + x);
        System.out.println("Y = " + y);

        WebElement topElement = getTopElement(x,y);
        System.out.println("TOP Element :: " + topElement.getTagName());
        topElement.click();
        Thread.sleep(2000);
    }

    private WebElement findElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    private WebElement getTopElement(int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return (WebElement) js.executeScript(String.format("return document.elementFromPoint(%d,%d)",x,y));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
