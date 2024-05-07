package com.fw.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class FlightConfirmationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    private By flightConfirmationElement = By.cssSelector("#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)");

    private By totalPriceElement = By.cssSelector("#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)");

    public FlightConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(flightConfirmationElement));
        return driver.findElement(flightConfirmationElement).isDisplayed();
    }

    public String getPrice(){
        String confirmation = driver.findElement(flightConfirmationElement).getText();
        String price = driver.findElement(totalPriceElement).getText();
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Total price : {}", price);
        return price;
    }
}
