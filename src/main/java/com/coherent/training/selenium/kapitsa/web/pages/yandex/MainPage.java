package com.coherent.training.selenium.kapitsa.web.pages.yandex;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePageObject {
    @CacheLookup
    @FindBy(xpath = "//div[@class='PSHeader-Right']/./button")
    private WebElement loginButton;


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage getLoginPage(){
        clickOn(loginButton);
        return new LoginPage(driver);
    }

    public String getPageTitle(){
        return getTitle();
    }
}
