package com.coherent.training.selenium.kapitsa.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MailBoxPage {
    private WebDriver driver;
    private final By userAccName = By.xpath("(//div[@class='PSHeader-User']//ancestor::span)[1]");

    public MailBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProfileNickname(){
        return driver.findElement(userAccName).getText();
    }
}
