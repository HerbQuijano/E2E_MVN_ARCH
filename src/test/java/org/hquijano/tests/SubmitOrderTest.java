package org.hquijano.tests;

import org.hquijano.pageobjects.*;
import org.hquijano.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test
    public void submitOrder() {
        String productToAdd = "ZARA COAT 3";
        String creditCardNumber = "4242 4242 4242 4242";
        int expiryDay = 14;
        int expiryMonth = 5;
        String cvvCode = "123";
        String nameOnCard = "Ivette Morales";
        String userEmail = "ivmora@test.com";
        String userCountryShort = "in";
        String userCountry = "Martinique";
        String expectedMessage = "THANKYOU FOR THE ORDER.";

        CatalogPage catalogPage = landingPage.goToCatalogPage();
        List<WebElement> products = catalogPage.getProductsList();
        catalogPage.getProductToAdd(productToAdd);
        catalogPage.addProductToCart(productToAdd);

        MyCartPage myCartPage = catalogPage.goToCartPage();
        Assert.assertTrue(myCartPage.assertProductAddedToCart(productToAdd));

        CheckoutPage checkoutPage = myCartPage.goToCheckoutPage();
        checkoutPage.setCardNumber(creditCardNumber);
        checkoutPage.setCvvCode(cvvCode);
        checkoutPage.selectExpMonth(expiryMonth);
        checkoutPage.selectExpDay(expiryDay);
        checkoutPage.setCvvCode(cvvCode);
        checkoutPage.setNameOnCard(nameOnCard);
        checkoutPage.setUserEmail(userEmail);
        checkoutPage.sendCountryShort(userCountryShort);
        checkoutPage.selectCountry(userCountry);

        ConfirmationPage confirmationPage = checkoutPage.goToConfirmationPage();
        Assert.assertTrue(confirmationPage.assertOrderConfirmation(expectedMessage), "Expected message does not match with current message");
    }
}
