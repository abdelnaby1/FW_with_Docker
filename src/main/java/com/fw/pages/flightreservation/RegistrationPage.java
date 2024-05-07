package com.fw.pages.flightreservation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    public By firstNameInput = By.id("firstName");
    public By lastNameInput = By.id("lastName");
    public By emailInput = By.id("email");
    public By passwordInput = By.id("password");
    public By streetInput = By.name("street");
    public By cityInput = By.name("city");
    public By zipInput = By.name("zip");
    public By registerButton = By.id("register-btn");

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public void goTo(String url){
        this.driver.get(url);
    }
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        return driver.findElement(firstNameInput).isDisplayed();
    }
    public void enterUserDetails(String firstName, String lastName){
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterUserCredentials(String email, String password){
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void enterAddress(String street, String city, String zip){
        driver.findElement(streetInput).sendKeys(street);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(zipInput).sendKeys(zip);
    }

    public void register(){
        driver.findElement(registerButton).click();
    }
}
