package com.fw.tests.nopcommerce;


import com.fw.pages.nopcommerce.HomePage;
import com.fw.pages.nopcommerce.ProductDetailsPage;
import com.fw.pages.nopcommerce.RegisterResultPage;
import com.fw.pages.nopcommerce.SearchPage;
import com.fw.tests.Common;
import com.fw.utils.Config;
import com.fw.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegisterTests extends Common {
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
                        .goToUrl()
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
        this.driver.quit();
    }
}
