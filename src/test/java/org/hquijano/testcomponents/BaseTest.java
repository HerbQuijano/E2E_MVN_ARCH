package org.hquijano.testcomponents;

import org.hquijano.pageobjects.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest{
    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver(){

        Properties prop = new Properties();
        try {
            //FileInputStream fis = new FileInputStream("src/main/java/org/hquijano/resources/globaldata.properties");
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//org//hquijano//resources//globaldata.properties");
            prop.load(fis);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("headless")){
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication(){
        driver  = initializeDriver();
        landingPage = new LandingPage(driver);
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        //driver.close();
        driver.quit();
    }

}