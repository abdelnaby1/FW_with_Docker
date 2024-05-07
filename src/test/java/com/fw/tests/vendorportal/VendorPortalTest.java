package com.fw.tests.vendorportal;

import com.fw.listener.TestListener;
import com.fw.pages.vendorportal.DashboardPage;
import com.fw.pages.vendorportal.LoginPage;
import com.fw.tests.Common;
import com.fw.tests.vendorportal.model.VendorPortalTestData;
import com.fw.util.Config;
import com.fw.util.Constants;
import com.fw.util.JsonUtil;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;

import static io.qameta.allure.SeverityLevel.CRITICAL;

@Listeners(TestListener.class)
public class VendorPortalTest extends Common {
    private WebDriver driver;
    private VendorPortalTestData testData;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))
                ? getRemoteDriver() : getLocalDriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);
        this.driver.manage().window().maximize();

    }
    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }
    @Test
    @Description("This test attempts to log into the website using a login and a password.")
    @Severity(CRITICAL)
    @Owner("Abdelnaby")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    @Description("This test attempts to search and verify count.")
    @Severity(CRITICAL)
    @Owner("Abdelnaby")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-124")
    @TmsLink("TMS-457")
    public void dashboardTest(){
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    @Description("This test attempts to logout.")
    @Severity(CRITICAL)
    @Owner("Abdelnaby")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-125")
    @TmsLink("TMS-458")
    public void logoutTest(){
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
