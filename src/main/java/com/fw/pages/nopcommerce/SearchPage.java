package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By searchField = By.id("small-searchterms");
    private By searchBtn = By.xpath("//button[text()='Search']");
    private By suggestionsList = By.id("ui-id-1");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }
    public SearchResultPage searchByProductName(String productName){
        elementActions.type(searchField,productName)
                .click(searchBtn);
        return new SearchResultPage(driver);
    }
    public ProductDetailsPage searchByKeyword(String keyword){
        elementActions.type(searchField,keyword);
        elementActions.waitForVisibility(suggestionsList);
        WebElement suggestion = driver.findElement(suggestionsList);
        suggestion.findElements(By.cssSelector("li a")).get(0).click();
        return new ProductDetailsPage(driver);
    }
}
