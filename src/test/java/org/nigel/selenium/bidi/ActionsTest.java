package org.nigel.selenium.bidi;

import org.nigel.selenium.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.module.Input;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ActionsTest extends BaseTest {

    private String windowHandle;
    private Input input;

    @BeforeTest
    public void startTest(){
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
        windowHandle = driver.getWindowHandle();
        input = new Input(driver);
    }


    @Test
    void canPerformInputActions() {
        driver.get("https://www.selenium.dev/selenium/web/formSelectionPage.html");
        driver.manage().window().maximize();

        List<WebElement> options = driver.findElements(By.tagName("option"));

        Actions actions = new Actions(driver);
        Actions selectThreeOptions =
                actions.click(options.get(1)).keyDown(Keys.SHIFT).click(options.get(3)).keyUp(Keys.SHIFT);

        input.perform(windowHandle, selectThreeOptions.getSequences());

        WebElement showButton = driver.findElement(By.name("showselected"));
        showButton.click();

        WebElement resultElement = driver.findElement(By.id("result"));
        Assert.assertTrue(resultElement.getText().contains("roquefort parmigiano cheddar"));
    }

    @AfterTest
    public void stopTest() {
        tearDown();
    }
}
