package org.hquijano.tests;

import org.hquijano.pageobjects.*;
import org.hquijano.testcomponents.BaseTest;
import org.hquijano.testcomponents.TestListener;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(TestListener.class)
public class SubmitOrderTest extends BaseTest {

    @Test(groups = "Products", dataProvider = "getData")
    public void submitOrder(String userName, String password, String product) {

        CatalogPage catalogPage = landingPage.login(userName, password);
        List<WebElement> products = catalogPage.getProductsList();
        catalogPage.getProductToAdd(product);
        catalogPage.addProductToCart(product);

        MyCartPage myCartPage = catalogPage.goToCartPage();
        Assert.assertTrue(myCartPage.assertProductAddedToCart(product));

        CheckoutPage checkoutPage = myCartPage.goToCheckoutPage();
        checkoutPage.enterCardData();

        ConfirmationPage confirmationPage = checkoutPage.goToConfirmationPage();
        Assert.assertTrue(confirmationPage.assertOrderConfirmation("THANKYOU FOR YOUR ORDER."), "Expected message does not match with current message");
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][] {
                {"ivmora@test.com", "ivetteMoral4!", "ZARA COAT 3"},
                {"tessalonian22@test.com", "Tess4lon3!", "ADIDAS ORIGINAL"}
        };

    }


}
