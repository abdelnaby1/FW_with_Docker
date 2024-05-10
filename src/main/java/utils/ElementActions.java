package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.testng.Assert.*;

public class ElementActions {
    private final WebDriver driver;
    public enum SelectType {
        TEXT, VALUE
    }
    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }
    public ElementActions waitForVisibility(By elementLocator){
        Helper.getExplicitWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        return this;
    }
    public ElementActions waitForVisibilityOfAll(By elementLocator){
        Helper.getExplicitWait(driver)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementLocator));
        return this;
    }

    public ElementActions waitForInvisibility(WebElement element){
        Helper.getExplicitWait(driver)
                .until(ExpectedConditions.invisibilityOf(element));
        return this;
    }
    public ElementActions waitForButtonTobNotDisabled(By elementLocator){
       waitForVisibility(elementLocator);
       try {
           WebElement button = driver.findElement(elementLocator);
           ExpectedCondition<Boolean> buttonEnabled = new ExpectedCondition<Boolean>() {
               public Boolean apply(WebDriver driver) {
                   return button.isEnabled();
               }
           };
           Helper.getExplicitWait(driver).until(buttonEnabled);
       } catch (Exception e) {
           fail(e.getMessage());
       }


        return this;
    }
    public ElementActions waitForUrlToContainsText(String text){
        Helper.getExplicitWait(driver)
                .until(ExpectedConditions.urlContains("cart"));
        return this;
    }
    public ElementActions waitForElementToContainsText(By elementLocator,String text){
        try {
            Helper.getExplicitWait(driver)
                    .until(ExpectedConditions.textToBePresentInElementLocated(elementLocator,text));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        return this;
    }

    public ElementActions click(By elementLocator) {
        click(driver, elementLocator);
        return this;
    }
    private void click(WebDriver driver, By elementLocator) {
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            new Actions(driver).moveToElement(driver.findElement(elementLocator)).perform();
            driver.findElement(elementLocator).click();
        } catch (Exception exception) {

            try {
                ((JavascriptExecutor) driver).executeScript("arguments[arguments.length - 1].click();",
                        driver.findElement(elementLocator));
            } catch (Exception rootCauseException) {
                fail("Couldn't click on the element:" + elementLocator, rootCauseException);
            }
        }
    }
    public ElementActions type(By elementLocator, String text) {
        type(driver, elementLocator, text, true);
        return this;
    }
    public ElementActions type(By elementLocator, String text, boolean clearBeforeTyping) {
        type(driver, elementLocator, text, clearBeforeTyping);
        return this;
    }
    public void type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
        try {
            if (!driver.findElement(elementLocator).getAttribute("value").isBlank() && clearBeforeTyping) {
                driver.findElement(elementLocator).clear();
                driver.findElement(elementLocator).sendKeys(text);
                if (!driver.findElement(elementLocator).getAttribute("value").equals(text)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text + "')",
                            driver.findElement(elementLocator));
                }
            } else {
                driver.findElement(elementLocator).sendKeys(text);
                if (!driver.findElement(elementLocator).getAttribute("value").contains(text)) {
                    String currentValue = driver.findElement(elementLocator).getAttribute("value");
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].setAttribute('value', '" + currentValue + text + "')",
                            driver.findElement(elementLocator));
                }
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertTrue(driver.findElement(elementLocator).getAttribute("value").contains(text),
                "The data is not inserted successfully to the field, the inserted data should be: [" + text
                        + "]; But the current field value is: ["
                        + driver.findElement(elementLocator).getAttribute("value") + "]");
    }

    public String getText( By elementLocator) {
       return getText(driver,elementLocator);
    }

    private String getText(WebDriver driver, By elementLocator) {
        try {
            Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (Exception e) {
            fail(e.getMessage());
        }
        try {

            return driver.findElement(elementLocator).getText();
        } catch (Exception e) {
            fail(e.getMessage());
            return null;
        }

    }
    public Boolean isElementDisplayed(By elementLocator){
        try {
            // Wait for the element to be visible
            Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            // Scroll the element into view to handle some browsers cases
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
                    driver.findElement(elementLocator));
            // Check if the element is displayed
            if (!driver.findElement(elementLocator).isDisplayed()) {
                fail("The element [" + elementLocator + "] is not Displayed");
            }else{
                return true;
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return false;
    }
}
