package com.fw.pages.vodafoneshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class ProductDetailsPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By addToCartBtn = By.xpath("//button[contains(@class,'add-to-cart')]");



    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }

    public ProductDetailsPage addProductToCart(){
        elementActions.click(addToCartBtn)
                .waitForButtonTobNotDisabled(addToCartBtn);
        return this;
    }

    public CartPage openCartPage(){
        new Navbar(driver).clickOnCartBtn();
        return new CartPage(driver);
    }
    public int getCartCount(){
        return new Navbar(driver).getCartCount();
    }
}
