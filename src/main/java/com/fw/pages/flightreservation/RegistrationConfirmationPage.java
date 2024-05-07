package com.fw.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationConfirmationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private By goToFlightsSearchButton = By.id("go-to-flights-search");
    private By firstNameElement = By.cssSelector("#registration-confirmation-section p b");
    public RegistrationConfirmationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(goToFlightsSearchButton));
        return driver.findElement(goToFlightsSearchButton).isDisplayed();
    }
    public String getFirstName(){
        return driver.findElement(firstNameElement).getText();
    }

    public void goToFlightsSearch(){
        driver.findElement(goToFlightsSearchButton).click();
    }
}
