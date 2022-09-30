package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicDataLoadingPage extends BasePageObject {
    private final By getNewUser = By.id("save");
    private final By userImg = By.xpath("//div[@id='loading']//img");

    public DynamicDataLoadingPage(WebDriver driver) {
        super(driver);
    }

    public void getRandomUser(){
        clickOn(getNewUser);

        waitUntilAttributeToBeNotEmpty(userImg, "src");
    }
}
