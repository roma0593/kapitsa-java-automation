package com.coherent.training.selenium.kapitsa.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MailBoxPage extends BasePageObject{
    private final By userAccName = By.xpath("(//span[@class='user-account__name'])[1]");

    public MailBoxPage(WebDriver driver) {
        super(driver);
    }

    public String getProfileNickname(){
        return find(userAccName).getText();
    }
}
