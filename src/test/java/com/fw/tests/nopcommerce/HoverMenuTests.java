package com.fw.tests.nopcommerce;


import com.fw.pages.nopcommerce.HomePage;
import com.fw.tests.Common;
import com.fw.utils.Config;
import com.fw.utils.Constants;
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

public class HoverMenuTests {
    private WebDriver driver;
    private WebDriver getLocalDriver(){

        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            return new FirefoxDriver();
        }
        return new ChromeDriver();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
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
        return new RemoteWebDriver(new URL(url),caps);

    }
    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeClass
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))
                ? getRemoteDriver() : getLocalDriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);
        this.driver.manage().window().maximize();

    }
    @Test
    public void testHoverMenu() {
        String category = "Computers";
        String subcategory = "Notebooks";

        String subcategoryName =
                new HomePage(driver)
                        .goToUrl()
                        .clickOnMenu(category,subcategory)
                        .getSubcategoryName();

        assertTrue(driver.getCurrentUrl().contains(subcategoryName.toLowerCase()));
    }

    @AfterClass
    public void quitDriver(){
        this.driver.quit();
    }
}
