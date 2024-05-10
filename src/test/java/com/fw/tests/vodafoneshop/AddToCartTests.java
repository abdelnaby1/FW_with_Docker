package com.fw.tests.vodafoneshop;

import com.fw.pages.vodafoneshop.CartPage;
import com.fw.pages.vodafoneshop.HomePage;
import com.fw.pages.vodafoneshop.Navbar;
import com.fw.pages.vodafoneshop.ProductDetailsPage;
import com.fw.tests.Common;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.Config;
import utils.Constants;

import java.net.MalformedURLException;

import static org.testng.Assert.*;

public class AddToCartTests extends Common {
    private WebDriver driver;
    int cartCountAfterAddingProduct;
    String productNameInProductDetailsPage;
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
    public void addToCartFromHomeTest() {

        productNameInProductDetailsPage =
                new HomePage(driver)
                        .gotToUrl()
                        .acceptAllCookies()
                        .openProfilePage()
                        .login("01061336022","Abdelnaby298082621@")
                        .clickOnProduct(1)
                        .addProductToCart()
                        .getProductName();

        Boolean isProductExistedOnCart =
                new ProductDetailsPage(driver)
                        .openCartPage()
                        .verifyProductExistedOnCart(productNameInProductDetailsPage);

        System.out.println("product name " + productNameInProductDetailsPage);
        assertTrue(isProductExistedOnCart,"The product should be in the cart page after being added");
    }

    @Test(dependsOnMethods = "addToCartFromHomeTest")
    public void removeProductFromCartTest()  {
        Boolean isProductExistedOnCart =
                new CartPage(driver)
                        .removeProductFromCart(2)
                        .verifyProductExistedOnCart(productNameInProductDetailsPage);
        assertFalse(isProductExistedOnCart,"The product should not be in the cart page after being removed");


    }



    @AfterClass
    public void quitDriver(){
        this.driver.quit();
    }
}
