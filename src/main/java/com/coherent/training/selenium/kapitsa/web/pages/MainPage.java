package com.coherent.training.selenium.kapitsa.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;
    private final By loginButton = By.xpath("//div[@class='PSHeader-Right']/./button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }
}
