package org.hquijano.pageobjects;

import org.hquijano.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CatalogPage extends AbstractComponent {
    WebDriver driver;

    public CatalogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = "button[routerlink='/dashboard/cart']")
    WebElement cartButton;

    By productsByList = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastContainer = By.cssSelector("#toast-container");
    By spinner = By.cssSelector(".ngx-spinner-overlay");

    public List<WebElement> getProductsList() {
        waitForElementsPresence(productsByList);
        return products;
    }

    public WebElement getProductToAdd(String productToAdd) {
        return getProductsList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productToAdd)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement productToAdd = getProductToAdd(productName);
        productToAdd.findElement(addToCart).click();
        waitForInvisibilityOfElement(spinner);
    }

    public MyCartPage goToCartPage() {
        cartButton.click();
        return new MyCartPage(driver);
    }
}
