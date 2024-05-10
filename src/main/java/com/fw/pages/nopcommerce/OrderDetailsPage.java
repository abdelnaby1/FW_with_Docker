package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderDetailsPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By printBtn = By.cssSelector(".button-2.print-order-button");
    private By printInvoicBtn = By.cssSelector(".button-2.pdf-invoice-button");

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.elementActions = new ElementActions(driver);
    }
    public OrderDetailsPage downloadPDFOrderInvoice(){
        elementActions.click(printInvoicBtn);
        return this;
    }
    public OrderDetailsPage printOrderDetails(){
        elementActions.click(printBtn);
        return this;
    }
}
