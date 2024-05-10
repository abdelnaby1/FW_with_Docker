package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private final WebDriver driver;
    private ElementActions elementActions;

    private final By changePassLink = By.linkText("Change password");
    private final By oldPasswordField = By.id("OldPassword");
    private final By newPasswordField = By.id("NewPassword");
    private final By confirmPasswordField = By.id("ConfirmNewPassword");
    private final By changePassBtn = By.cssSelector("button.button-1.change-password-button");
    private final By notificationBar = By.cssSelector("div.bar-notification.success p");

    public MyAccountPage( WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }
    public MyAccountPage openChangePasswordView(){
        elementActions.click(changePassLink);
        return this;
    }
    public MyAccountPage changePasswrod(String oldPass, String newPass){
        elementActions.type(oldPasswordField,oldPass)
                .type(newPasswordField,newPass)
                .type(confirmPasswordField,newPass)
                .click(changePassBtn);
        return this;
    }
    public String getSuccessMsg(){
        return elementActions.getText(notificationBar);
    }
}
