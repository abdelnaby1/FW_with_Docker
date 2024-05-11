package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By searchKeywordField = By.id("q");
    private By prodcutSearchList = By.className("search-results");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }
    public String getSearchedKeyword(){
        return elementActions.getAttributeValue(searchKeywordField,"value");
    }
    public ProductDetailsPage openProductDetails(int productNumber){
        List<WebElement> products = driver.findElements(prodcutSearchList);
        int numOfProductsReturned = products.size();
        System.out.println(numOfProductsReturned);
        if(numOfProductsReturned > 0 && productNumber <= numOfProductsReturned ){

            products.get(productNumber - 1).findElement(By.cssSelector(".product-item a")).click();
        }
        return new ProductDetailsPage(driver);
    }
}
