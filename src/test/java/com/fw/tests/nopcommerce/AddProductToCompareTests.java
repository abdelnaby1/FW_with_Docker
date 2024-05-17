package com.fw.tests.nopcommerce;

import com.fw.pages.nopcommerce.ComparePage;
import com.fw.pages.nopcommerce.HomePage;
import com.fw.pages.nopcommerce.ProductDetailsPage;
import com.fw.pages.nopcommerce.SearchPage;
import com.fw.utils.Config;
import com.fw.utils.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class AddProductToCompareTests {
    private WebDriver driver;
    List productsName = new ArrayList();
    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }
    @BeforeClass
    public void setDriver() {
        driver = WebDriverManager.getDriver();
        new HomePage(driver)
                .goToUrl();

    }
    @DataProvider(name = "searchKeywords")
    public Object[][] getKeywords() {
        return new Object [][] {
                {"mac"},
                {"samsung"}
        };
    }
    @Test(dataProvider = "searchKeywords")
    public void testAddToCompare(String keyword) {
        String toastMsg =
                new SearchPage(driver)
                        .searchByKeyword(keyword)
                        .addProductToCompare()
                        .getToastMessage();
        productsName.add(new ProductDetailsPage(driver).getProductName());
        assertTrue(toastMsg.equalsIgnoreCase("The product has been added to your product comparison"));

    }
    @Test(dependsOnMethods = "testAddToCompare")
    public void testComparePage() {
        driver.get("https://demo.nopcommerce.com/compareproducts");
        var productsCompareName = new ComparePage(driver).getProductsName();
        assertEquals(productsCompareName.get(1),productsName.get(0));
        assertEquals(productsCompareName.get(0),productsName.get(1));
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/screenshots/compare.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test(dependsOnMethods = "testComparePage")
    public void testClearList() {
        String noDataText =
                new ComparePage(driver)
                        .clearList()
                        .getNoDataText();
        assertEquals(noDataText,"You have no items to compare.");

    }


    @AfterClass
    public void quitDriver(){
        WebDriverManager.quitDriver();

    }
}
