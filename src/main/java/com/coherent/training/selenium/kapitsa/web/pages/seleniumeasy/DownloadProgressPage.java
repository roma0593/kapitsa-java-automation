package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DownloadProgressPage extends BasePageObject {
    private final By downloadButton = By.id("cricle-btn");
    private final By percentText = By.xpath("//div[@class='percenttext']");

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
