package org.nigel.selenium;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeTest;

public class MyFirstTest extends BaseTest{

    private Input input;
    private String windowHandle;

    @BeforeTest
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
        windowHandle = driver.getWindowHandle();
        input = new Input(driver);
    }
}
