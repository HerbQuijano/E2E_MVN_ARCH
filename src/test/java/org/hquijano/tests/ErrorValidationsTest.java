package org.hquijano.tests;

import org.hquijano.pageobjects.CatalogPage;
import org.hquijano.pageobjects.MyCartPage;
import org.hquijano.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidationsTest extends BaseTest {
    String url = "https://rahulshettyacademy.com/client";

    @Test(groups = {"ErrorHandling", "LoginTest"}, dataProvider = "loginData")
    public void loginErrorValidation(String username, String password) {
        CatalogPage catalogPage = landingPage.login(username, password);
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.", "Expected error message does not match with current message");
    }

    @Test(groups = {"Products", "ErrorHandling"})
    @Parameters({"username", "password", "productToAdd"})
    public void productErrorValidation(String username, String password, String productToAdd) {
        String expectedProduct = "ZARA COAT 4";

        CatalogPage catalogPage = landingPage.login(username, password);
        List<WebElement> products = catalogPage.getProductsList();
        catalogPage.getProductToAdd(productToAdd);
        catalogPage.addProductToCart(productToAdd);

        MyCartPage myCartPage = catalogPage.goToCartPage();
        Assert.assertFalse(myCartPage.assertProductAddedToCart(expectedProduct), "Expected product does not match with current added product");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                {"ivmora@test.com", "divetteMoral4!"},
                {"tessalonian22@test.com", "xTess4lon3!"}
        };

    }
}
