package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DownloadProgressPage extends BasePageObject {
    @CacheLookup
    @FindBy(id = "cricle-btn")
    private WebElement downloadButton;
    @CacheLookup
    @FindBy(xpath = "//div[@class='percenttext']")
    private WebElement percentText;

    public DownloadProgressPage(WebDriver driver) {
        super(driver);
    }

    public void clickDownload(){
        clickOn(downloadButton);
    }

    public void refreshPageIfProgressBarMorThan(String percent){
        clickDownload();

        waitUntilTextToBePresentedInElementLocated(percentText, percent);

        refreshPage();
    }
}
