package com.fw.pages.vodafoneshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.fw.utils.ElementActions;

import java.util.List;

public class CartPage {
    private final WebDriver driver;

    private ElementActions elementActions;
    private By cartProductRemoveBtn(int productNum){
        return By.xpath("(//div[contains(@class,'cart-card-container')])["+productNum+"]//div[contains(@class,'cart-actions')]//p");
    }
    private By cartProductNameLoc(int productNum){
        return By.xpath("(//div[contains(@class,'cart-card-container')])["+productNum+"]//*[contains(@class,'cartProduct-name')]");
    }
    private By cartProductsNamesLoc = By.xpath("//*[contains(@class,'cartProduct-name')]");
    private By toastMsgLoc = By.xpath("//*[contains(text(),'Item Removed Successfully')]");

    public CartPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }
    public CartPage removeProductFromCart(int productNum){
        elementActions.click(cartProductRemoveBtn(productNum)).waitForElementToContainsText(By.cssSelector("button.cart-btn span"), String.valueOf(getCartCount() -1));
        return this;
    }
    public Boolean isDeletedToastMsgDisplayed(){
        return elementActions.isElementDisplayed(toastMsgLoc);
    }
    public int getCartCount(){
        return new Navbar(driver).getCartCount();
    }
    public Boolean verifyProductExistedOnCart(String productName){
        elementActions.waitForVisibilityOfAll(cartProductsNamesLoc);

        List<String> productsNames =
                driver.findElements(cartProductsNamesLoc).stream()
                .map(WebElement::getText)
                .toList();
        System.out.println("all names " + productsNames);
        return productsNames.stream()
                .anyMatch(s -> s.equals(productName));
    }

}
