package com.coherent.training.selenium.kapitsa.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected void clickOn(By locator){
        find(locator).click();
    }

    protected void type(By locator, String value){
        find(locator).sendKeys(value);
    }
}
