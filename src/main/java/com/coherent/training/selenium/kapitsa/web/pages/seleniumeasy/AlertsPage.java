package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class AlertsPage extends BasePageObject {
    @CacheLookup
    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement alertButton;
    @CacheLookup
    @FindBy(xpath = "//button[@class='btn btn-default btn-lg'][text()='Click me!']")
    private WebElement confirmAlertButton;
    @CacheLookup
    @FindBy(xpath = "//button[text()='Click for Prompt Box']")
    private WebElement promptAlertButton;
    @CacheLookup
    @FindBy(id = "confirm-demo")
    private WebElement alertMessage;

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    public Alert callAlert(){
        return openAlert(alertButton);
    }

    public Alert callConfirmAlert(){
        return openAlert(confirmAlertButton);
    }

    public Alert callPromptAlert(){
        return openAlert(promptAlertButton);
    }

    public String acceptAlert(){
        confirmAlert(confirmAlertButton);

        return alertMessage.getText();
    }

    public String dismissAlert(){
        declineAlert(confirmAlertButton);

        return alertMessage.getText();
    }
}
