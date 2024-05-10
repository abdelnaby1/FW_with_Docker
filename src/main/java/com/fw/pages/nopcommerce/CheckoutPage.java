package com.fw.pages.nopcommerce;

import com.fw.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver driver;
    private ElementActions elementActions;
    private By countyField = By.id("BillingNewAddress_CountryId");
    private By cityField = By.id("BillingNewAddress_City");
    private By address1Field = By.id("BillingNewAddress_Address1");
    private By zipCodeField = By.id("BillingNewAddress_ZipPostalCode");
    private By phoneNumberField = By.id("BillingNewAddress_PhoneNumber");
    private By continueBtn = By.cssSelector("#billing-buttons-container .button-1.new-address-next-step-button");
    private By shippingMethodContinueBtn = By.cssSelector("#shipping-method-buttons-container .button-1.shipping-method-next-step-button");
    private By paymentContinueBtn = By.cssSelector("#payment-method-buttons-container .button-1.payment-method-next-step-button");
    private By paymentIfoContinueBtn = By.cssSelector("#payment-info-buttons-container .button-1.payment-info-next-step-button");
    private By confirmCheckoutBtn = By.cssSelector("#confirm-order-buttons-container .button-1.confirm-order-next-step-button");
    private By title = By.cssSelector(".section.order-completed .title strong");
    private By orderNumber = By.cssSelector(".order-number strong");
    private By continueBtnAfterOrderCompleted = By.id("button-1 order-completed-continue-button");
    private By orderDetailsBtn = By.cssSelector(".details-link a");
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public CheckoutPage fillTheAddressDetails(String county, String city, String address, String zipcode, String phone){
        elementActions.select(countyField, ElementActions.SelectType.TEXT,county)
                .type(cityField,city)
                .type(address1Field,address)
                .type(zipCodeField,zipcode)
                .type(phoneNumberField,phone);
        if (elementActions.isElementDisplayed(continueBtn)){
            elementActions.mouseHover(continueBtn).click(continueBtn);
        }
        return this;
    }
    public CheckoutPage chooseDefaultShippingMethod(){
        elementActions.click(shippingMethodContinueBtn);
        return this;
    }
    public CheckoutPage chooseDefaultPaymentMethod(){
        elementActions.click(paymentContinueBtn);
        return this;
    }
    public CheckoutPage continueOnPaymentInfo(){
        elementActions.click(paymentIfoContinueBtn);
        return this;
    }
    public CheckoutPage confirmCheckout(){
        elementActions.click(confirmCheckoutBtn);
        return this;
    }

    public String getTitle() {
        return elementActions.getText(title);
    }
    public String getOrderNumber() {
        return elementActions.getText(orderNumber);
    }

    public HomePage continueBtnAfterOrderCompleted() {
        elementActions.click(continueBtnAfterOrderCompleted);
        return new HomePage(driver);
    }
    public OrderDetailsPage openOrderDetails(){
        elementActions.click(orderDetailsBtn);
        return new OrderDetailsPage(driver);
    }
}
