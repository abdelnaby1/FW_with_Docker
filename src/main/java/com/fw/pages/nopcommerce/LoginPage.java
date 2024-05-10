package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private ElementActions elementActions;

    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private final By loginBtn = By.cssSelector("button.button-1.login-button");
    private By rememberMeField = By.id("RememberMe");
    private By forgotPassLink = By.xpath("//a[contains(text(),'Forgot password?')]");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public HomePage login(String email, String pass){
        elementActions.type(emailField,email)
                .type(passwordField,pass)
                .click(loginBtn);
        return  new HomePage(driver);
    }
}
