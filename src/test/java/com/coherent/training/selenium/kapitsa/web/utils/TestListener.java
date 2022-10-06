package com.coherent.training.selenium.kapitsa.web.utils;

import com.coherent.training.selenium.kapitsa.web.base.TestUtilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
public class TestListener extends TestUtilities implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        testSuiteName = context.getSuite().getName();
        testName = context.getCurrentXmlTest().getName();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testMethodName = result.getMethod().getMethodName();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result.getName());
    }
}
