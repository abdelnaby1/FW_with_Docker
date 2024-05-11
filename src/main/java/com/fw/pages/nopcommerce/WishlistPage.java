package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishlistPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private  String recentlyAddedProduct;
    private By productNames = By.cssSelector("td.product .product-name");
    private By removeBtns = By.cssSelector("button.remove-btn");
    private By noDataLabel = By.cssSelector("div.no-data");
    public WishlistPage(WebDriver driver ) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }
    public WishlistPage(WebDriver driver, String recentlyAddedProductName) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
        this.recentlyAddedProduct = recentlyAddedProductName;
    }
    public  String getProductName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productNames));
        return driver.findElements(productNames).get(0).getText();
    }
    public  String getRecentlyAddedProduct(){
        return recentlyAddedProduct;
    }
    public WishlistPage removeRecentlyAddedProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(removeBtns));
        driver.findElements(removeBtns).get(0).click();
        return this;
    }
    public String getMessageAfterDeleting(){
        return elementActions.getText(noDataLabel);
    }
}
