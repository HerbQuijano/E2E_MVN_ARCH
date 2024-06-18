package org.hquijano.tests;

import org.hquijano.pageobjects.CatalogPage;
import org.hquijano.pageobjects.MyCartPage;
import org.hquijano.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidationsTest extends BaseTest {
    String url = "https://rahulshettyacademy.com/client";

    @Test(groups = {"ErrorHandling"})
    public void loginErrorValidation() {
        String username = "ivmora@test.com";
        String password = "divetteMoral4!";
        CatalogPage catalogPage = landingPage.login(url, username, password);
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.", "Expected error message does not match with current message");
    }

    @Test
    public void productErrorValidation(){
        String username = "ivmora@test.com";
        String password = "ivetteMoral4!";
        String productToAdd = "ZARA COAT 3";
        String expectedProduct = "ZARA COAT 4";

        CatalogPage catalogPage = landingPage.login(url, username, password);
        List<WebElement> products = catalogPage.getProductsList();
        catalogPage.getProductToAdd(productToAdd);
        catalogPage.addProductToCart(productToAdd);

        MyCartPage myCartPage = catalogPage.goToCartPage();
        Assert.assertFalse(myCartPage.assertProductAddedToCart(expectedProduct), "Expected product does not match with current added product");
    }
}
