package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By productTitle = By.cssSelector("strong.current-item");
    private By emailAFrientBtn = By.xpath("//button[text()='Email a friend']");
    private By priceLable = By.id("price-value-4");
    private By addReviewBtn = By.xpath("//a[text()='Add your review']");
    private By addToWishlistBtn = By.id("add-to-wishlist-button-5");
    private By toastMessage = By.xpath("//p[text()='The product has been added to your ']");
    private  By wishlistLink = By.xpath("//a[text()='wishlist']");

    private By addToCompareBtn = By.xpath("//div[contains(@class,'compare-products')]//button[text()='Add to compare list']");

    private By toastMsg = By.cssSelector(".bar-notification .content");
    private By addToCartBtn = By.xpath("//div[@class='add-to-cart']//button[text()='Add to cart']");
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
        setProductName(elementActions.getText(productTitle));

    }
    private String ProductName;

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getProductTitle(){
//        setProductName(eleActions.getText(productTitle));
        return getProductName();
    }
    public EmailAFriendPage clickEmailAFriend(){
        elementActions.click(emailAFrientBtn);
        return new EmailAFriendPage(driver);
    }
    public String getPrice(){
        return elementActions.getText(priceLable);
    }
    public ProductReviewPage openProductReviewPage(){
        elementActions.click(addReviewBtn);
        return new ProductReviewPage(driver);
    }
    public ProductDetailsPage addProductToWishlist(){
        elementActions.click(addToWishlistBtn);
        return this;
    }
    private Boolean isToastMessageAppeared(){
        return elementActions.isElementDisplayed(toastMessage);
    }
    public WishlistPage openWishlist(){
        if(isToastMessageAppeared()){
            elementActions.click(wishlistLink);
        }
        return new WishlistPage(driver,getProductName());
    }
    public ProductDetailsPage addProductToCompare(){
        elementActions.click(addToCompareBtn);
        return this;
    }

    public String getToastMessage() {
        return elementActions.getText(toastMessage);
    }
    public ProductDetailsPage addProductToCart(){
        elementActions.click(addToCartBtn);
        return this;
    }
}
