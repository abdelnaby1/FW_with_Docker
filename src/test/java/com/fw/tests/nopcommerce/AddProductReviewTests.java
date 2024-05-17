package com.fw.tests.nopcommerce;

import com.fw.pages.nopcommerce.HomePage;
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

public class AddProductReviewTests {
    private WebDriver driver;
    Date date;
    String email;
    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeClass
    public void setDriver() {
        driver = WebDriverManager.getDriver();

        date = new Date();
        email = "test" + date.getTime() + "@test.com";


        new HomePage(driver)
                .goToUrl()
                .openRegistrationPage()
                .register("ahmed","test",email,"123456789")
                .clickContinueBtn()
                .openLoginPage()
                .login(email,"123456789");

    }
    @Test()
    public void testAddProductReview(){
        String message =
                new SearchPage(driver)
                        .searchByKeyword("laptop")
                        .openProductReviewPage()
                        .rate("Bad product","this is un profissional product","Bad")
                        .getMessage();
        assertEquals(message, "Product review is successfully added.");
    }



    @AfterClass
    public void quitDriver(){
        WebDriverManager.quitDriver();

    }
}
