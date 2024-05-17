package com.fw.tests.nopcommerce;

import com.fw.pages.nopcommerce.HomePage;
import com.fw.pages.nopcommerce.ProductDetailsPage;
import com.fw.pages.nopcommerce.SearchPage;
import com.fw.utils.Config;
import com.fw.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ChangeCurrencyTests {
    private WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeClass
    public void setDriver()  {
        driver = WebDriverManager.getDriver();
        new HomePage(driver)
                .goToUrl();


    }
    @Test
    public void testSearchByAutoSuggestions() {
        new HomePage(driver)
                .changeCurrency("Euro");

        String keyword = "mac";
        String productTitle =
                new SearchPage(driver)
                        .searchByKeyword(keyword)
                        .getProductTitle();

        assertTrue(productTitle.toLowerCase().contains(keyword.toLowerCase()));

        String price =
                new ProductDetailsPage(driver)
                        .getPrice();
        assertTrue(price.contains("€"));
    }




    @AfterClass
    public void quitDriver(){
        WebDriverManager.quitDriver();

    }
}
