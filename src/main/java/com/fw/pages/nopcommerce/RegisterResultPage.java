package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterResultPage {
    private final WebDriver driver;
    private ElementActions elementActions;

    private final By successMessage = By.cssSelector("div.result");

    private final By continueBtn = By.cssSelector("a.button-1.register-continue-button");


    public RegisterResultPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }
    public HomePage clickContinueBtn(){
        elementActions.click(continueBtn);
        return new HomePage(driver);
    }
    public String getSuccessMessage(){
        return elementActions.getText(successMessage);

    }
}
