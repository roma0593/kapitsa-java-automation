package com.coherent.training.selenium.kapitsa.web.utils;

import com.coherent.training.selenium.kapitsa.web.base.TestUtilities;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends TestUtilities implements ITestListener, TestLifecycleListener {

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
    public void beforeTestStop(TestResult result) {
        if(result.getStatus().name().equalsIgnoreCase("FAILED")){
            takeScreenshot();
            getBrowserName();
            getBrowserVersion();
        }
    }
}
