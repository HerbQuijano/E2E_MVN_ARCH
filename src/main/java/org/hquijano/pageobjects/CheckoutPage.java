package org.hquijano.pageobjects;

import org.hquijano.abstractcomponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    String creditCardNumber = "4242 4242 4242 4242";
    int expiryDay = 14;
    int expiryMonth = 5;
    String cvvCode = "123";
    String nameOnCard = "Ivette Morales";
    String userEmail = "ivmora@test.com";
    String userCountryShort = "in";
    String userCountry = "Martinique";
    String expectedMessage = "THANKYOU FOR THE ORDER.";

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".form__cc .input.input.input.txt.text-validated")
    WebElement creditCardField;

    @FindBy(css = ".input.ddl:first-of-type")
    WebElement expMonth;

    @FindBy(css = ".input.ddl:nth-of-type(2)")
    WebElement expDay;

    @FindBy(xpath = ("(//input[@type='text'])[2]"))
    WebElement cvvCodeField;

    @FindBy(xpath = ("(//input[@type='text'])[3]"))
    WebElement nameOnCardField;

    @FindBy(css = ".input.txt.text-validated.ng-valid")
    WebElement userEmailField;

    @FindBy(css = ".user__name.mt-5 input:first-child")
    WebElement userCountryField;

    @FindBy(css = "button.ng-star-inserted")
    private WebElement selectCountry;

    @FindBy(css = ".ta-item")
    List<WebElement> countriesList;

    @FindBy(css = ".actions a.btnn.action__submit.ng-star-inserted")
    WebElement placeOrderButton;

    private By results = By.cssSelector(".ta-results");


    public void enterCardData() {
        creditCardField.clear();
        creditCardField.sendKeys(creditCardNumber);
        cvvCodeField.clear();
        cvvCodeField.sendKeys(cvvCode);
        Select expMonthDrop = new Select(expMonth);
        expMonthDrop.selectByIndex(expiryMonth);
        Select expDayDrop = new Select(expDay);
        expDayDrop.selectByIndex(expiryDay);
        nameOnCardField.clear();
        nameOnCardField.sendKeys(nameOnCard);
        userEmailField.clear();
        userEmailField.sendKeys(userEmail);
        Actions a = new Actions(driver);
        a.sendKeys(userCountryField, userCountryShort).build().perform();
        waitForElementsPresence(results);
        int locationX = countriesList.stream().filter(country -> country.getText().equalsIgnoreCase(userCountry)).findFirst().orElse(null).getLocation().getX();
        int locationY = countriesList.stream().filter(country -> country.getText().equalsIgnoreCase(userCountry)).findFirst().orElse(null).getLocation().getY();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String jsExecutorString = "window.scrollTo(" + locationX + "," + locationY + ")";
        jsExecutor.executeScript(jsExecutorString);
        a.moveToElement(countriesList.stream().filter(country -> country.getText().equalsIgnoreCase(userCountry)).findFirst().orElse(null)).click().build().perform();
    }

    public ConfirmationPage goToConfirmationPage() {
        placeOrderButton.click();
        return new ConfirmationPage(driver);
    }


}
