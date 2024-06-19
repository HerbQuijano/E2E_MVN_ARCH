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

    @FindBy(css = ".ng-trigger-flyInOut")
    WebElement loginError;

    public CatalogPage login(String username, String password) {
        driver.get("https://rahulshettyacademy.com/client");
        userEmail.sendKeys(username);
        userPassword.sendKeys(password);
        loginButton.click();
        return new CatalogPage(driver);
    }

    public String getErrorMessage(){
        waitForVisibilityOfElement(loginError);
        return loginError.getText();
    }
}
