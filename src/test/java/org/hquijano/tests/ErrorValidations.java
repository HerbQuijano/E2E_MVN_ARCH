package org.hquijano.tests;

import org.hquijano.pageobjects.CatalogPage;
import org.hquijano.pageobjects.CheckoutPage;
import org.hquijano.pageobjects.ConfirmationPage;
import org.hquijano.pageobjects.MyCartPage;
import org.hquijano.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidations extends BaseTest {

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
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.", "Expected error message does not match with current message");
    }
}
