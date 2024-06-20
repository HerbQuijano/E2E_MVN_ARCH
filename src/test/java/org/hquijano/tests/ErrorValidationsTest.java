package org.hquijano.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hquijano.pageobjects.CatalogPage;
import org.hquijano.pageobjects.MyCartPage;
import org.hquijano.resources.JsonDataProvider;
import org.hquijano.testcomponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorValidationsTest extends BaseTest {
    String url = "https://rahulshettyacademy.com/client";
    String filepath = System.getProperty("user.dir") + "//src//main//java//org//hquijano//resources//testdata.json";

    @Test(groups = {"ErrorHandling", "LoginTest"}, dataProvider = "invalidLoginData")
    public void loginErrorValidation(String username, String password) {
        CatalogPage catalogPage = landingPage.login(username, password);
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.", "Expected error message does not match with current message");
    }

    @Test(groups = {"LoginTest"}, dataProvider = "invalidLoginDataMap")
    public void loginErrorValidationMap(HashMap<String, String> input) {
        CatalogPage catalogPage = landingPage.login(input.get("user"), input.get("password"));
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.", "Expected error message does not match with current message");
    }

    @Test(groups = {"LoginTest"}, dataProvider = "invalidLoginDataJson")
    public void loginErrorValidationJson(String username, String password) {
        CatalogPage catalogPage = landingPage.login(username, password);
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.", "Expected error message does not match with current message");
    }

    @Test(groups = {"Products", "ErrorHandling"})
    @Parameters({"user", "pass", "productToAdd"})
    public void productErrorValidation(String user, String pass, String productToAdd) {
        String expectedProduct = "ZARA COAT 4";

        CatalogPage catalogPage = landingPage.login(user, pass);
        List<WebElement> products = catalogPage.getProductsList();
        catalogPage.getProductToAdd(productToAdd);
        catalogPage.addProductToCart(productToAdd);

        MyCartPage myCartPage = catalogPage.goToCartPage();
        Assert.assertFalse(myCartPage.assertProductAddedToCart(expectedProduct), "Expected product does not match with current added product");
    }

    @DataProvider
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"ivmora@test.com", "divetteMoral4!"},
                {"tessalonian22@test.com", "xTess4lon3!"}
        };

    }

    @DataProvider
    public Object[][] invalidLoginDataMap() {
        HashMap<String, String> user1 = new HashMap<String, String>();
        user1.put("user","ivmora@test.com");
        user1.put("password","divetteMoral4!");

        HashMap<String, String> user2 = new HashMap<String, String>();
        user2.put("user","tessalonian22@test.com");
        user2.put("password","xTess4lon3!");


        return new Object[][] {
                {user1}, {user2}
        };
    }

    @DataProvider
    public Object[][] invalidLoginDataJson() throws IOException {
        List<Map<String, String>> testData = JsonDataProvider.readJsonFile(filepath);

        Object[][] data = new Object[testData.size()][2]; // Two-dimensional array for username and password

        for (int i = 0; i < testData.size(); i++) {
            Map<String, String> dataMap = testData.get(i);
            data[i][0] = dataMap.get("username");
            data[i][1] = dataMap.get("password");
        }

        return data;
    }

}
