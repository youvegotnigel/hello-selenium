package org.nigel.selenium.hello;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium {

    @Test
    public void mainTest() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://cloudtesting.contosotraders.com/");
        driver.manage().window().maximize();
        String title = driver.getTitle();

        Assert.assertEquals(title, "Contoso Traders");
        driver.quit();
    }
}
