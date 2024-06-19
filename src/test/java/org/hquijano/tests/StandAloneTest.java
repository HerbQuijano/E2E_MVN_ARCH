package org.hquijano.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

    public static void main(String[] args) {
        String productToAdd = "ZARA COAT 3";
        String creditCardNumber = "4242 4242 4242 4242";
        int expiryDay = 14;
        int expiryMonth = 5;
        String cvvCode = "123";
        String nameOnCard = "Ivette Morales";
        String userEmail = "ivmora@test.com";
        String userCountryShort = "aus";
        String userCountry = "Australia";

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys("ivmora@test.com");
        driver.findElement(By.id("userPassword")).sendKeys("ivetteMoral4!");
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        //La siguiente linea selecciona por medio de stream().filter() el elemento de la lista que coincida con el nombre del producto
        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productToAdd)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://rahulshettyacademy.com/client/dashboard/cart", "URL does not match");

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".items h3"));
        boolean isMatch = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productToAdd));

        Assert.assertTrue(isMatch, "Product added to cart");

        driver.findElement(By.cssSelector(".totalRow .btn")).click();

        driver.findElement(By.cssSelector(".form__cc .input.input.input.txt.text-validated")).clear();
        driver.findElement(By.cssSelector(".form__cc .input.input.input.txt.text-validated")).sendKeys(creditCardNumber);
        Select expMonth = new Select(driver.findElement(By.cssSelector(".input.ddl:first-of-type")));
        expMonth.selectByIndex(expiryMonth);
        Select expDay = new Select(driver.findElement(By.cssSelector(".input.ddl:nth-of-type(2)")));
        expDay.selectByIndex(expiryDay);
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(cvvCode);
        driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(nameOnCard);

        driver.findElement(By.cssSelector(".details__user input.input.txt.text-validated.ng-untouched.ng-pristine.ng-valid")).sendKeys(userEmail);

        //driver.findElement(By.cssSelector(".user__name.mt-5 input:first-child")).sendKeys(userCountry);
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector(".user__name.mt-5 input:first-child")), userCountryShort).build().perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));

        List<WebElement> countriesList = driver.findElements(By.cssSelector("button .ng-star-inserted"));

        countriesList.stream().filter(country -> country.getText().equalsIgnoreCase(userCountry)).findFirst().orElse(null).click();

        //button .ng-star-inserted

        driver.findElement(By.cssSelector(".actions a.btnn.action__submit.ng-star-inserted")).click();

        String orderConfirmation = driver.findElement(By.cssSelector(".em-spacer-1 label.ng-star-inserted")).getText();
        System.out.println(orderConfirmation);


        driver.quit();

        //ivette morales
        //ivmora@test.com
        //9986326598
        //Dr
        //ivetteMoral4!

//        Tessa Smith
//        tessalonian22@test.com
//        9986326599
//        Student
//        Female
//        Tess4lon3!

    }

}
