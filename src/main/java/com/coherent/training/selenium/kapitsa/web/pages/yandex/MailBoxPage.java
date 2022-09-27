package com.coherent.training.selenium.kapitsa.web.pages.yandex;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MailBoxPage extends BasePageObject {
    private final By userAccName = By.cssSelector("a[role='button'] span[class='user-account__name']");

    public MailBoxPage(WebDriver driver) {
        super(driver);
    }

    public String getProfileNickname(){
        waitForElementToBeVisibleAt(userAccName);
        return find(userAccName).getText();
    }
}
