package com.fw.utils;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    public static synchronized  WebDriver getDriver() {
        initializeDriver();
        return driverThreadLocal.get();
    }

    public static  void initializeDriver() {
        ITestResult result = Reporter.getCurrentTestResult();
        ITestContext context = result.getTestContext();
        Capabilities caps;
        if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))){
            if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                caps = options;
            }
            else if (Constants.EDGE.equalsIgnoreCase(Config.get(Constants.BROWSER))){
                EdgeOptions options = new EdgeOptions();
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                caps =  options;
            }else{
                ChromeOptions options = new ChromeOptions();
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                caps =  options;
            }
            String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
            String hubHost = Config.get(Constants.GRID_HUB_HOST);
            String url = String.format(urlFormat, hubHost);

            try {
                driverThreadLocal.set(ThreadGuard.protect(new RemoteWebDriver(new URI(url).toURL(),caps)));
            } catch (URISyntaxException | MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }else{
            if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
                driverThreadLocal.set(ThreadGuard.protect(new FirefoxDriver()));
            }
            else if (Constants.EDGE.equalsIgnoreCase(Config.get(Constants.BROWSER))){
                driverThreadLocal.set(ThreadGuard.protect(new EdgeDriver()));
            }
            else{
                driverThreadLocal.set(ThreadGuard.protect(new ChromeDriver()));
            }
            //default is chrome
        }
        context.setAttribute(Constants.DRIVER, driverThreadLocal.get());
        driverThreadLocal.get().manage().window().maximize();
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
