package com.fw.pages.vendorportal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    private By monthlyEarningElement = By.id("monthly-earning");

    private By annualEarningElement = By.id("annual-earning");

    private By profitMarginElement = By.id("profit-margin");

    private By availableInventoryElement = By.id("available-inventory");

    private By searchInput = By.cssSelector("#dataTable_filter input");

    private By searchResultsCountElement = By.id("dataTable_info");

    private By userProfilePictureElement = By.cssSelector("img.img-profile");

    // prefer id / name / css
    private By logoutLink = By.linkText("Logout");

    private By modalLogoutButton = By.cssSelector("#logoutModal a");

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(monthlyEarningElement));
        return driver.findElement(monthlyEarningElement).isDisplayed();
    }
    public String getMonthlyEarning(){
        return  driver.findElement(monthlyEarningElement).getText();
    }

    public String getAnnualEarning(){
        return  driver.findElement(annualEarningElement).getText();
    }

    public String getProfitMargin(){
        return  driver.findElement(profitMarginElement).getText();
    }

    public String getAvailableInventory(){
        return  driver.findElement(availableInventoryElement).getText();
    }

    public void searchOrderHistoryBy(String keyword){
        driver.findElement(searchInput).sendKeys(keyword);
    }
    public int getSearchResultsCount(){
        String resultsText = driver.findElement(searchResultsCountElement).getText();
        String[] arr = resultsText.split(" ");
        // if we do not have 5th item, it would throw exception.
        // that's fine. we would want our test to fail anyway in that case!
        int count = Integer.parseInt(arr[5]);
        log.info("Results count: {}", count);
        return count;
    }

    public void logout(){
        driver.findElement(userProfilePictureElement).click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        driver.findElement(logoutLink).click();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(modalLogoutButton));
        driver.findElement(modalLogoutButton).click();
    }
}
