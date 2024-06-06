package org.hquijano.pageobjects;

import org.hquijano.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyCartPage extends AbstractComponent {
    WebDriver driver;
    //WebDriverWait wait;

    public MyCartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy (css = ".totalRow .btn")
    WebElement checkoutButton;

    @FindBy(css = ".items h3")
    List<WebElement> productsInCart;

    public CheckoutPage goToCheckoutPage(){
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

    public boolean assertProductAddedToCart(String productName){
        return productsInCart.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    // List<WebElement> cartProducts = driver.findElements(By.cssSelector(".items h3"));
    // boolean isMatch = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productToAdd));

}
