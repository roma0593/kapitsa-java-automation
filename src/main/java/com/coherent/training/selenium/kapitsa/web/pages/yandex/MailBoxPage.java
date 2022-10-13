package com.coherent.training.selenium.kapitsa.web.pages.yandex;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MailBoxPage extends BasePageObject {
    @CacheLookup
    @FindBy(css = "a[role='button'] span[class='user-account__name']")
    private WebElement userAccName;
    @CacheLookup
    @FindBy(xpath = "//a[@aria-label='Log out']")
    private WebElement logoutButton;

    public MailBoxPage(WebDriver driver) {
        super(driver);
    }

    public String getProfileNickname(){
        waitForElementToBeVisibleAt(userAccName);
        return userAccName.getText();
    }

    @Step("click on logout")
    public MainPage logout(){
        clickOn(userAccName);
        clickOn(logoutButton);

        return new MainPage(driver);
    }
}
