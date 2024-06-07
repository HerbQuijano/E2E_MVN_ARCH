package org.hquijano.pageobjects;

import org.hquijano.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement loginButton;

    public void open() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public void enterLoginDetails() {
        userEmail.sendKeys("ivmora@test.com");
        userPassword.sendKeys("ivetteMoral4!");
    }

    public CatalogPage goToCatalogPage() {
        loginButton.click();
        return new CatalogPage(driver);
    }
}
