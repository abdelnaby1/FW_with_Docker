package com.fw.tests.vodafoneshop;

import com.fw.pages.vodafoneshop.CartPage;
import com.fw.pages.vodafoneshop.HomePage;
import com.fw.tests.Common;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.Config;
import utils.Constants;

import java.net.MalformedURLException;

import static org.testng.Assert.assertTrue;

public class AddToCartTests2 extends Common {
    private WebDriver driver;

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
    public void addToCartFromHomeTest()   {
        new HomePage(driver)
                .gotToUrl()
                .acceptAllCookies()
                .openProfilePage()
                .login("01061336022","Abdelnaby298082621@")
                .clickOnProduct(2)
                .addProductToCart();

    }






    @AfterClass
    public void quitDriver(){
        this.driver.quit();
    }
}
