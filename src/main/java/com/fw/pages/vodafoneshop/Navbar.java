package com.fw.pages.vodafoneshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fw.utils.ElementActions;

public class Navbar {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By profileBtn = By.cssSelector("#userProfileMenu button");
    private By carBtn = By.cssSelector("button.cart-btn");

    private By cartCountLoc = By.cssSelector("button.cart-btn span");

    public Navbar(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }

    public void clickOnProfileBtn(){
        elementActions.click(profileBtn);
    }
    public void clickOnCartBtn(){
        elementActions.click(carBtn).waitForUrlToContainsText("cart");
    }

    public int getCartCount(){
        String cartCount = elementActions.getText(cartCountLoc);
        if (cartCount == null || cartCount.isEmpty()){
            return 0;
        }
        return Integer.parseInt(cartCount);
    }
}
