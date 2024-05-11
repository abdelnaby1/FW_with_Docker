package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailAFriendPage {
    private final WebDriver driver;
    private ElementActions elementActions;

    private By frinedEmailField = By.id("FriendEmail");
    private By yourEmailField = By.id("YourEmailAddress");
    private By messageTextarea = By.id("PersonalMessage");
    private By sendEmailBtn = By.name("send-email");
    private By result = By.cssSelector("div.result");

    public EmailAFriendPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }
    public EmailAFriendPage emailYourFriend(String friendMail, String message){
        elementActions.type(frinedEmailField,friendMail)
                .type(messageTextarea,message)
                .click(sendEmailBtn);
        return this;
    }
    public String getMessage(){
        return elementActions.getText(result);
    }
}
