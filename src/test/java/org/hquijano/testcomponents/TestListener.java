package org.hquijano.testcomponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Capture screenshot on test failure
        System.out.println("Test Case Failed");
        // Capture screenshot on test failure
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).driver;
        ((BaseTest) currentClass).captureScreenshot(result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {
        // Code for test start
        System.out.println("Test Starting");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Code for test success
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Code for test skipped
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Code for test failed but within success percentage
    }

    @Override
    public void onStart(ITestContext context) {
        // Code for start
    }

    @Override
    public void onFinish(ITestContext context) {
        // Code for finish
    }
}
