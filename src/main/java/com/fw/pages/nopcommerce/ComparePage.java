package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ComparePage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By clearBtn = By.xpath("//a[text()='Clear list']");
    private By notData = By.className("no-data");
    private By productsName = By.cssSelector("tr.product-name td a");

    public ComparePage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }
    public ComparePage clearList(){
        elementActions.click(clearBtn);
        return this;
    }
    public String getNoDataText(){
        return elementActions.getText(notData);
    }
    public List getProductsName(){
        List names = new ArrayList<>();
        var pNames = driver.findElements(productsName);
        for (var name: pNames) {
            names.add(name.getText());
        }
        return names;
    }
}
