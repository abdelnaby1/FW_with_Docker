package com.fw.tests.nopcommerce;


import com.fw.pages.nopcommerce.HomePage;
import com.fw.pages.nopcommerce.SearchPage;
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

public class All {
    private WebDriver driver;


    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeMethod
    public void setDriver() {

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
    @Test()
    public void testChangePassword(){
        Date date = new Date();
        String email = "test" + date.getTime() + "@test.com";


        new HomePage(driver)
                .goToUrl()
                .openRegistrationPage()
                .register("ahmed","test",email,"123456789")
                .clickContinueBtn();

        String successMsg =
                new HomePage(driver)
                        .openMyAccountPage()
                        .openChangePasswordView()
                        .changePasswrod("123456789","1234567")
                        .getSuccessMsg();
        assertEquals(successMsg,"Password was changed");
    }
    @AfterMethod
    public void quitDriver(){
        WebDriverManager.quitDriver();
//        this.driver.quit();
    }
}
