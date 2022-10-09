package com.coherent.training.selenium.kapitsa.web.pages.herokuapp;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DownloadPage extends BasePageObject {
    @CacheLookup
    @FindBy(xpath = "//a[text()='a.jpeg']")
    private WebElement jpegLink;

    public DownloadPage(WebDriver driver) {
        super(driver);
    }

    public String getJpegHrefAttr() {
        return jpegLink.getAttribute("href");
    }
}
