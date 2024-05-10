package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By fullNameField = By.id("FullName");
    private By emailField = By.id("Email");
    private By enquiryTextarea = By.id("Enquiry");
    private By submitBtn = By.cssSelector("button.button-1.contact-us-button");
    private By result = By.cssSelector("div.result");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public ContactUsPage contactUs(String name, String email,String message){
        elementActions.type(fullNameField,name,true)
                .type(emailField,email,true)
                .type(enquiryTextarea,message,true)
                .click(submitBtn);
        return this;
    }
    public String getResultMessage(){
        return elementActions.getText(result);
    }
}
