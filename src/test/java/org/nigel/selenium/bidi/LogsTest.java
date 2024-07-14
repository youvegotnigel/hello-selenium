package org.nigel.selenium.bidi;

import org.nigel.selenium.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.bidi.log.JavascriptLogEntry;
import org.openqa.selenium.bidi.module.LogInspector;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LogsTest extends BaseTest {

    @BeforeMethod
    public void startTest(){
        ChromeOptions options = new ChromeOptions();
        options.setCapability("webSocketUrl", true);
        driver = new ChromeDriver(options);
    }


    @Test
    public void consoleLogTest() throws ExecutionException, InterruptedException, TimeoutException {

        try (LogInspector logInspector = new LogInspector(driver)) {

            CompletableFuture<ConsoleLogEntry> future = new CompletableFuture<>();
            logInspector.onConsoleEntry(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.manage().window().maximize();
            driver.findElement(By.id("consoleLog")).click();

            ConsoleLogEntry logEntry = future.get(5, TimeUnit.SECONDS);
            System.out.println(logEntry.getText());
        }
    }

    @Test
    public void consoleErrorTest() throws ExecutionException, InterruptedException, TimeoutException {

        try (LogInspector logInspector = new LogInspector(driver)) {

            CompletableFuture<ConsoleLogEntry> future = new CompletableFuture<>();
            logInspector.onConsoleEntry(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.manage().window().maximize();
            driver.findElement(By.id("consoleError")).click();

            ConsoleLogEntry logEntry = future.get(5, TimeUnit.SECONDS);
            System.out.println(logEntry.getText());
        }
    }

    @Test
    public void jsExceptionTest() throws ExecutionException, InterruptedException, TimeoutException {

        try (LogInspector logInspector = new LogInspector(driver)) {

            CompletableFuture<JavascriptLogEntry> future = new CompletableFuture<>();
            logInspector.onJavaScriptLog(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.manage().window().maximize();
            driver.findElement(By.id("jsException")).click();

            JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);
            System.out.println(logEntry.getText());
        }
    }

    @Test
    public void logWithStacktraceTest() throws ExecutionException, InterruptedException, TimeoutException {

        try (LogInspector logInspector = new LogInspector(driver)) {

            CompletableFuture<JavascriptLogEntry> future = new CompletableFuture<>();
            logInspector.onJavaScriptException(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.manage().window().maximize();
            driver.findElement(By.id("logWithStacktrace")).click();

            JavascriptLogEntry logEntry = future.get(5, TimeUnit.SECONDS);
            System.out.println(logEntry.getText());
        }
    }


    @AfterMethod
    public void stopTest() {
        tearDown();
    }

}
