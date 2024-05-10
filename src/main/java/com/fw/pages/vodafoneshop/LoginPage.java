package com.fw.pages.vodafoneshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fw.utils.ElementActions;

public class LoginPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By submitBtn = By.id("submitBtn");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(this.driver);
    }

    public HomePage login(String username, String password){
        elementActions.type(usernameField,username)
                .type(passwordField,password)
                .click(submitBtn);
        return new HomePage(driver);
    }

}
