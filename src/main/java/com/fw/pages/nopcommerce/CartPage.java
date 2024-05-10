package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By productName = By.cssSelector(".product-name");
    private By qtyInput = By.className("qty-input");
    private By updateCartBtn = By.cssSelector("button#updatecart");
    private By removeProductBtn = By.cssSelector(".remove-btn");
    private By noDataText = By.cssSelector(".no-data");

    private By termsAndConditionsBtn = By.id("termsofservice");
    private By checkoutBtn = By.id("checkout");
    public CartPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public String getProductName() {
        return elementActions.getText(productName);
    }

    public CartPage updateQty(String qtr) {
        elementActions
                .type(qtyInput,qtr,true)
                .click(updateCartBtn);
        return  this;
    }

    public String getQtyInput() {
        return elementActions.getAttributeValue(qtyInput,"value");
    }

    public CartPage removeprodcut() {
        elementActions.click(removeProductBtn);
        return this;
    }
    public String getNoDataText() {
        return elementActions.getText(noDataText);
    }
    public CheckoutPage checkout(){
        elementActions.click(termsAndConditionsBtn)
                .click(checkoutBtn);
        return new CheckoutPage(driver);
    }
}
