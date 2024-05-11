package com.fw.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    public static synchronized WebDriver getDriver() {
        if (driverThreadLocal.get() == null){
            return initializeDriver();
        }
        return driverThreadLocal.get();
    }

    public static WebDriver initializeDriver() {
        ITestResult result = Reporter.getCurrentTestResult();
        ITestContext context = result.getTestContext();

        if ( Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))){
            Capabilities caps = new ChromeOptions();
            if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
                caps = new FirefoxOptions();
            }
            else if (Constants.EDGE.equalsIgnoreCase(Config.get(Constants.BROWSER))){
                caps = new EdgeOptions();
            }
            String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
            String hubHost = Config.get(Constants.GRID_HUB_HOST);
            String url = String.format(urlFormat, hubHost);
            try {
                driverThreadLocal.set(ThreadGuard.protect(new RemoteWebDriver(new URL(url),caps)));
            } catch ( MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }else{
            if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
                driverThreadLocal.set(ThreadGuard.protect(new FirefoxDriver()));
            }
            else if (Constants.EDGE.equalsIgnoreCase(Config.get(Constants.BROWSER))){
                driverThreadLocal.set(ThreadGuard.protect(new EdgeDriver()));
            }
            //default is chrome
            driverThreadLocal.set(ThreadGuard.protect(new ChromeDriver()));
        }
        context.setAttribute(Constants.DRIVER, driverThreadLocal.get());
        driverThreadLocal.get().manage().window().maximize();
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (null !=  driverThreadLocal.get()) {
            try {
                driverThreadLocal.get().quit(); // First quit WebDriver session gracefully
                driverThreadLocal.remove(); // Remove WebDriver reference from the ThreadLocal variable.
            } catch (Exception e) {
                System.err.println("Unable to gracefully quit WebDriver."+ e);
            }finally {
                driverThreadLocal.remove();
            }

        }
    }
}
