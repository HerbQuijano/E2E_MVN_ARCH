package org.hquijano.pageobjects;

import org.hquijano.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;

    @FindBy(css = ".em-spacer-1 label.ng-star-inserted")
    WebElement orderConfirmation;

    public String getOrderConfirmation() {
        return orderConfirmation.getText();
    }

    public boolean assertOrderConfirmation(String message) {
        return confirmationMessage.getText().equalsIgnoreCase(message);
    }
}
