package com.coherent.training.selenium.kapitsa.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePageObject{
    private final By loginButton = By.xpath("//div[@class='PSHeader-Right']/./button");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage getLoginPage(){
        clickOn(loginButton);
        return new LoginPage(driver);
    }
}
