package com.fw.tests.nopcommerce;


import com.fw.pages.nopcommerce.HomePage;
import com.fw.tests.Common;
import com.fw.utils.Config;
import com.fw.utils.Constants;
import com.fw.utils.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactUsTests  {
    private WebDriver driver;


    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeClass
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        driver = WebDriverManager.getDriver();


    }
    @Test
    public void testContactUs() {
        Date date = new Date();
        String email = "test" + date.getTime() + "@test.com";
        String name = "test test";
        String message = " Hello There!";
        String successMessage =
                new HomePage(driver)
                        .goToUrl()
                        .openContactUsPage()
                        .contactUs(name,email,message)
                        .getResultMessage();

        assertTrue(successMessage.contains("Your enquiry has been successfully sent to the store owner."));
    }

    @AfterClass
    public void quitDriver(){
        WebDriverManager.quitDriver();

    }
}
