package com.fw.pages.nopcommerce;

import com.fw.utils.Config;
import com.fw.utils.Constants;
import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private ElementActions elementActions;
    private final By registerLink = By.linkText("Register");
    private static By loginLink = By.linkText("Log in");
    private static By logoutLink = By.linkText("Log out");
    private  final By myAccountLink = By.linkText("My account");

    private  final  By contactUsLink = By.linkText("Contact us");
    private final By currencySelect = By.id("customerCurrency");
    private final By cartLink = By.cssSelector("a.ico-cart");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }
    public HomePage goToUrl(){
        driver.get(Config.get(Constants.NOPCOMMERCE_URL));
        return this;
    }
    public RegisterPage openRegistrationPage(){
        elementActions.click(registerLink);
        return new RegisterPage(driver);
    }
    public LoginPage openLoginPage(){
        elementActions.click(loginLink);
        return new LoginPage(driver);
    }
    public HomePage logout(){
        elementActions.click(logoutLink);
        return new HomePage(driver);
    }
    public MyAccountPage openMyAccountPage(){
        elementActions.click(myAccountLink);
        return new MyAccountPage(driver);
    }
    public ContactUsPage openContactUsPage(){
        elementActions.click(contactUsLink);
        return new ContactUsPage(driver);
    }
    public HomePage changeCurrency(String currency){
        elementActions.select(currencySelect, ElementActions.SelectType.TEXT,currency);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    public SubCategoryPage clickOnMenu(String category,String subcategory){
        elementActions.mouseHover(By.xpath("//div[contains(@class,'header-menu')]//ul[contains(@class,'notmobile')]/li[1]/a")).click(By.linkText(subcategory));
        return new SubCategoryPage(driver,subcategory);
    }
    public CartPage openCart(){
        elementActions.click(cartLink);
        return new CartPage(driver);
    }
    public Boolean isLoginLinkDisplayed(){
        return elementActions.isElementDisplayed(loginLink);
    }
    public  Boolean isLogoutLinkDisplayed(){
        return elementActions.isElementDisplayed(logoutLink);
    }
}