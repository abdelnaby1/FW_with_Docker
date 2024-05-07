package com.fw.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FlightsSearchPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private By passengerSelect = By.id("passengers");

    private By searchFlightsButton = By.id("search-flights");

    public FlightsSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(passengerSelect));
        return driver.findElement(passengerSelect).isDisplayed();
    }
    public void selectPassengers(String noOfPassengers){
        Select passengers = new Select(driver.findElement(passengerSelect));
        passengers.selectByValue(noOfPassengers);
    }

    public void searchFlights(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(searchFlightsButton));
        jse.executeScript("arguments[0].click();", driver.findElement(searchFlightsButton));

    }
}
