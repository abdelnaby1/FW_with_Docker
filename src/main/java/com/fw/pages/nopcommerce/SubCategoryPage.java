package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.WebDriver;

public class SubCategoryPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private String subcategoryName;

    public SubCategoryPage(WebDriver driver,String subcategoryName) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
        this.subcategoryName = subcategoryName;
    }
    public String getSubcategoryName(){
        return this.subcategoryName;
    }
}
