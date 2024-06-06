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
    //WebDriverWait wait;

    public CheckoutPage(WebDriver driver){
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


    public void setCardNumber(String creditCardNumber){
        creditCardField.clear();
        creditCardField.sendKeys(creditCardNumber);
    }

    public void setCvvCode(String cvvCode){
        cvvCodeField.clear();
        cvvCodeField.sendKeys(cvvCode);
    }

    public void selectExpMonth(int month){
        Select expMonthDrop = new Select(expMonth);
        expMonthDrop.selectByIndex(month);
    }

    public void selectExpDay(int day){
        Select expDayDrop = new Select(expDay);
        expDayDrop.selectByIndex(day);
    }

    public void setNameOnCard(String nameOnCard){
        nameOnCardField.clear();
        nameOnCardField.sendKeys(nameOnCard);
    }

    public void setUserEmail(String userEmail) {
        userEmailField.clear();
        userEmailField.sendKeys(userEmail);
    }

    public void sendCountryShort(String userCountryShort) {
        Actions a = new Actions(driver);
        a.sendKeys(userCountryField, userCountryShort).build().perform();
    }

    public void selectCountry(String countryName) {
    Actions a = new Actions(driver);
    waitForVisibilityOfElement(By.cssSelector(".ta-results"));
    int locationX = countriesList.stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null).getLocation().getX();
    int locationY = countriesList.stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null).getLocation().getY();
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    String jsExecutorString = "window.scrollTo("+locationX+","+locationY+")";
    jsExecutor.executeScript(jsExecutorString);
    a.moveToElement(countriesList.stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null)).click().build().perform();
    }

    public ConfirmationPage goToConfirmationPage(){
        placeOrderButton.click();
        return new ConfirmationPage(driver);
    }



}
