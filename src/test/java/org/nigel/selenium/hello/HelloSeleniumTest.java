package org.nigel.selenium.hello;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSeleniumTest {

    WebDriver driver;
    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable");
        driver = new ChromeDriver();
    }


    @Test
    public void mainTest() {

        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        String title = driver.getTitle();

        Assert.assertEquals(title, "Google");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
