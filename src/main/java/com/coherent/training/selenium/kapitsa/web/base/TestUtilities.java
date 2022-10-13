package com.coherent.training.selenium.kapitsa.web.base;

import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.coherent.training.selenium.kapitsa.web.base.BaseTest.driver;

public class TestUtilities {
    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;

    @SneakyThrows
    protected void saveScreenshot(String fileName){
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodaysDate()
                + File.separator + testSuiteName
                + File.separator + testName
                + File.separator + testMethodName
                + File.separator + getSystemTime()
                + " " + fileName + ".png";

        FileUtils.copyFile(scrFile, new File(path));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] takeScreenshot(){
        return (((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }

    @Attachment(value = "browser name", type = "plain/text")
    public String getBrowserName(){
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        return capabilities.getBrowserName();
    }

    @Attachment(value = "browser version", type = "plain/text")
    protected String getBrowserVersion(){
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        return capabilities.getBrowserVersion();
    }

    private String getTodaysDate() {
        return (new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    private String getSystemTime() {
        return (new SimpleDateFormat("HHmmssSSS").format(new Date()));
    }
}
