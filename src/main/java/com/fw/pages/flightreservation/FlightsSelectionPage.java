package com.fw.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private By departureFlightsOptions = By.name("departure-flight");

    private By arrivalFlightsOptions = By.name("arrival-flight");

        private By confirmFlightsButton = By.id("confirm-flights");

    public FlightsSelectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(confirmFlightsButton));
        return driver.findElement(confirmFlightsButton).isDisplayed();
    }
    public void selectFlights(){
        List<WebElement> departureFlightsOptionsList = driver.findElements(departureFlightsOptions);
        int random = ThreadLocalRandom.current().nextInt(0, departureFlightsOptionsList.size());
        departureFlightsOptionsList.get(random).click();
        List<WebElement> arrivalFlightsOptionsList = driver.findElements(arrivalFlightsOptions);
        arrivalFlightsOptionsList.get(random).click();
    }

    public void confirmFlights(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(confirmFlightsButton));
        jse.executeScript("arguments[0].click();", driver.findElement(confirmFlightsButton));
    }
}
