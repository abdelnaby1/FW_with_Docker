package com.fw.pages.vodafoneshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Config;
import utils.Constants;
import utils.ElementActions;

public class HomePage {
    private final WebDriver driver;
    private By acceptCookiesBtn = By.id("onetrust-accept-btn-handler");
    private By productLoc (int productNum){
        return By.xpath("(//div[contains(@class,'product-card')])["+productNum+"]");
    }
    private ElementActions elementActions;


    public HomePage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }

    public HomePage gotToUrl(){
        driver.get(Config.get(Constants.VODAFONE_SHOP_URL));
        return this;
    }
    public int getCartCount(){
        return new Navbar(driver).getCartCount();
    }

    public HomePage acceptAllCookies(){
        elementActions.click(acceptCookiesBtn);
        return this;
    }
    public LoginPage openProfilePage(){
        new Navbar(driver).clickOnProfileBtn();
        return new LoginPage(driver);
    }
    public CartPage openCartPage(){
        new Navbar(driver).clickOnCartBtn();
        return new CartPage(driver);
    }
    public ProductDetailsPage clickOnProduct(int productNum){
        elementActions.click(productLoc((productNum)));
        return new ProductDetailsPage(driver);
    }

}
