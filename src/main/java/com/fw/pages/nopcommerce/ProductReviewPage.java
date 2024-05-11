package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductReviewPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By titleField = By.id("AddProductReview_Title");
    private By textFiled = By.id("AddProductReview_ReviewText");
    private By rating;
    private By submitReviewBtn = By.name("add-review");
    private By result = By.cssSelector("div.result");

    public ProductReviewPage( WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }
    public ProductReviewPage rate(String title,String text,String rating){
        elementActions.type(titleField,title)
                .type(textFiled,text)
                .click(By.cssSelector("input[aria-label='"+rating+"']"))
                .click(submitReviewBtn);
        return  this;
    }
    public String getMessage(){
        return elementActions.getText(result);
    }
}
