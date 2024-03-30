package org.nigel.selenium.troubleshooting;

import org.nigel.selenium.BaseTest;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingTest extends BaseTest {


    @Test
    public void logging() throws IOException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.FINE);
        Arrays.stream(logger.getHandlers()).forEach(handler -> {
            handler.setLevel(Level.FINE);
        });

        Handler handler = new FileHandler("selenium.xml");
        logger.addHandler(handler);

        Logger.getLogger(RemoteWebDriver.class.getName()).setLevel(Level.FINEST);
        Logger.getLogger(SeleniumManager.class.getName()).setLevel(Level.SEVERE);

        Logger localLogger = Logger.getLogger(this.getClass().getName());
        localLogger.warning("this is a warning");
        localLogger.info("this is useful information");
        localLogger.fine("this is detailed debug information");

        byte[] bytes = Files.readAllBytes(Paths.get("selenium.xml"));
        String fileContent = new String(bytes);

        Assert.assertTrue(fileContent.contains("this is a warning"));
        Assert.assertTrue(fileContent.contains("this is useful information"));
        Assert.assertTrue(fileContent.contains("this is detailed debug information"));
    }


}
