package com.coherent.training.selenium.kapitsa.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExceptionListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("The name of the testcase failed is :"+ result.getName());
        System.out.println("The reason - Expected and actual result mismatch");
    }
}
