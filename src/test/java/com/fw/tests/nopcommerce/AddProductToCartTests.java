package com.fw.tests.nopcommerce;

import com.fw.pages.nopcommerce.HomePage;
import com.fw.pages.nopcommerce.SearchPage;
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
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddProductToCartTests {
    private WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeClass
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        driver = WebDriverManager.getDriver();


    }
    @Test()
    public void testAddProductToCart(){
        String product = "Apple MacBook Pro 13-inch";
        new HomePage(driver)
                .goToUrl();
        String toastMsg =
                new SearchPage(driver)
                        .searchByProductName(product)
                        .openProductDetails(1)
                        .addProductToCart()
                        .getToastMessage();
        assertEquals(toastMsg,"The product has been added to your shopping cart");

        String productName =
                new HomePage(driver)
                        .openCart()
                        .getProductName();
        assertEquals(productName,product);
    }


    @AfterClass
    public void quitDriver(){
        WebDriverManager.quitDriver();

    }
}
