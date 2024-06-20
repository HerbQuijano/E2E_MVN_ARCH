package org.hquijano.testcomponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Case Failed");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Code for test start
        System.out.println("Test Starting");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Code for test success
        System.out.println("Test Success");
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

    }

    @Override
    public void onFinish(ITestContext context) {
        // Code for finish
    }

}
