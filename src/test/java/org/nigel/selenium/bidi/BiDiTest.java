package org.nigel.selenium.bidi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BiDiTest {

    protected WebDriver driver;
    protected URL gridUrl;

    @BeforeTest
    public void startGrid() {
        gridUrl = startStandaloneGrid();
        System.out.println("GRID URL ::: " + gridUrl);
    }


    protected URL startStandaloneGrid() {
        int port = PortProber.findFreePort();
        try {
            Main.main(
                    new String[] {
                            "standalone",
                            "--port",
                            String.valueOf(port),
                            "--selenium-manager",
                            "true",
                            "--enable-managed-downloads",
                            "true",
                            "--log-level",
                            "WARNING"
                    });
            return new URL("http://localhost:" + port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void runRemote() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("webSocketUrl", true);
        driver = new RemoteWebDriver(gridUrl, options);

        driver.get("https://www.selenium.dev/");
        System.out.println("PAGE TITLE ::: " + driver.getTitle());

        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.ALL);
        Arrays.stream(logger.getHandlers()).forEach(handler -> {
            handler.setLevel(Level.ALL);
        });
    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
