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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddToCartTests extends Common {
    private WebDriver driver;
    int cartCountAfterAddingProduct;
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

        int cartCountBeforeAddingProduct =
                new HomePage(driver)
                        .gotToUrl()
                        .acceptAllCookies()
                        .openProfilePage()
                        .login("01061336022","Abdelnaby298082621@")
                        .clickOnProduct(1)
                        .getCartCount();

        System.out.println("before" + cartCountBeforeAddingProduct);
        cartCountAfterAddingProduct =
                new ProductDetailsPage(driver)
                .addProductToCart()
                        .openCartPage()
                        .getCartCount();
        System.out.println("after" + cartCountAfterAddingProduct);

        assertEquals(cartCountAfterAddingProduct,cartCountBeforeAddingProduct + 1,"The CartPage Count should be increased by 1");

    }

    @Test(dependsOnMethods = "addToCartFromHomeTest")
    public void removeProductFromCartTest() throws InterruptedException {
        int cartCountAfterRemovingProduct = new HomePage(driver)
                .openCartPage()
                .removeProductFromCart(2)
                        .getCartCount();

        System.out.println("after removing" + cartCountAfterRemovingProduct);

        assertEquals(cartCountAfterRemovingProduct,cartCountAfterAddingProduct - 1,"The CartPage Count should be decreased by 1");


    }



    @AfterClass
    public void quitDriver(){
        this.driver.quit();
    }
}
