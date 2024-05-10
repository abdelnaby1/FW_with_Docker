package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;
    private ElementActions elementActions;

    private final By genderField = By.id("gender-male");
    private final By firstNameFiled = By.id("FirstName");
    private final By lastNameField = By.id("LastName");
    private final By emailField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By confirmPasswordField = By.id("ConfirmPassword");
    private final By registerBtn = By.id("register-button");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(this.driver);

    }
    public RegisterResultPage register(String fName, String lName, String email, String pass){
        elementActions.click(genderField)
                .type(firstNameFiled,fName)
                .type(lastNameField,lName)
                .type(emailField,email)
                .type(passwordField,pass)
                .type(confirmPasswordField,pass)
                .click(registerBtn);

        return new RegisterResultPage(driver);
    }

}
