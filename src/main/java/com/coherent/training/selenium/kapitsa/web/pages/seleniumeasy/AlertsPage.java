package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlertsPage extends BasePageObject {
    private final By alertButton = By.xpath("//button[@class='btn btn-default']");
    private final By confirmAlertButton = By.xpath("//button[@class='btn btn-default btn-lg'][text()='Click me!']");
    private final By promptAlertButton = By.xpath("//button[text()='Click for Prompt Box']");

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

    public WebElement acceptAlert(){
        confirmAlert(confirmAlertButton);

        return find(By.id("confirm-demo"));
    }

    public WebElement dismissAlert(){
        declineAlert(confirmAlertButton);

        return find(By.id("confirm-demo"));
    }
}
