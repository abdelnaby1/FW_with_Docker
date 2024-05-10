package com.fw.pages.vodafoneshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementActions;

public class CartPage {
    private final WebDriver driver;

    private ElementActions elementActions;
    private By cartProductRemoveBtn(int productNum){
        return By.xpath("(//div[contains(@class,'cart-card-container')])["+productNum+"]//div[contains(@class,'cart-actions')]//p");
    }
    private By cartProductNameLoc(int productNum){
        return By.xpath("(//div[contains(@class,'cart-card-container')])["+productNum+"]//*[contains(@class,'cartProduct-name')]");
    }
    private By toastMsgLoc = By.xpath("//*[contains(text(),'Item Removed Successfully')]");

    public CartPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }
    public CartPage removeProductFromCart(int productNum){
        String productName = elementActions.getText(cartProductNameLoc(productNum));
        WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'"+productName+"')]"));
        elementActions.click(cartProductRemoveBtn(productNum)).waitForInvisibility(ele);
        return this;
    }
    public Boolean isDeletedToastMsgDisplayed(){
        return elementActions.isElementDisplayed(toastMsgLoc);
    }
    public int getCartCount(){
        return new Navbar(driver).getCartCount();
    }

}
