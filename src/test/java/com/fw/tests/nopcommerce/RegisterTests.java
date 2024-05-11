package com.fw.tests.nopcommerce;


import com.fw.pages.nopcommerce.HomePage;
import com.fw.pages.nopcommerce.ProductDetailsPage;
import com.fw.pages.nopcommerce.RegisterResultPage;
import com.fw.pages.nopcommerce.SearchPage;
import com.fw.tests.Common;
import com.fw.utils.Config;
import com.fw.utils.Constants;
import com.fw.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegisterTests{
    private WebDriver driver;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeClass
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        driver = WebDriverManager.getDriver();
        new HomePage(driver)
                .goToUrl();

    }
    @DataProvider(name = "userData")
    public static Object[][] userData(){
        return new Object[][]{
                {"ahmed","test", "test1" + new Date().getTime() + "@test.com","123456789"},
                {"eslam","test", "test2" + new Date().getTime() + "@test.com","1234567890"},
                {"mohamed","test", "test3" + new Date().getTime() + "@test.com","12345678900"},

        };
    }
    @Test(dataProvider = "userData")
    public void testValidRegister(String fName, String lName, String email, String password){
        String successMsg =
                new HomePage(driver)
                        .openRegistrationPage()
                        .register(fName,lName,email,password)
                        .getSuccessMessage();
        assertTrue(successMsg.contains("Your registration completed"));


        new HomePage(driver)
                .logout();

        assertTrue(new HomePage(driver).isLoginLinkDisplayed());
    }



    @AfterClass
    public void quitDriver(){
        WebDriverManager.quitDriver();

    }
}
