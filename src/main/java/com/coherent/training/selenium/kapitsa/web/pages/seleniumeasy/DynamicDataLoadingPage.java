package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DynamicDataLoadingPage extends BasePageObject {
    @CacheLookup
    @FindBy(id = "save")
    private WebElement getNewUser;
    @CacheLookup
    @FindBy(xpath = "//div[@id='loading']//img")
    private WebElement userImg;

    public DynamicDataLoadingPage(WebDriver driver) {
        super(driver);
    }

    public void getRandomUser(){
        clickOn(getNewUser);

        waitUntilAttributeToBeNotEmpty(userImg, "src");
    }
}
