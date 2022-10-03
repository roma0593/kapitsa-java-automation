package com.coherent.training.selenium.kapitsa.web.pages.yandex;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage extends BasePageObject {
    private final By title = By.xpath("//span[@class='passp-add-account-page-title']");
    private final By mailButton = By.xpath("//span[text()='Mail']//ancestor::button");
    private final By phoneNumberButton = By.xpath("//span[text()='Phone number']//ancestor::button");
    private final By loginField = By.xpath("//input[@id='passp-field-login']");
    private final By passField = By.xpath("//input[@id='passp-field-passwd']");
    private final By forgotLink = By.xpath("//a[normalize-space()='I forgot']");
    private final By phoneNumberField = By.xpath("//input[@id='passp-field-phone']");
    private final By loginButton = By.xpath("//button[@id='passp:sign-in']");
    private final By createIDButton = By.xpath("//a[@id='passp:exp-register']");
    private final By facebookIcon = By.xpath("//button[@aria-label='Facebook']");
    private final By googleIcon = By.xpath("//button[@aria-label='Google']");
    private final By QRCodeIcon = By.xpath("//button[@aria-label='QR code']");
    private final By twitterIcon = By.xpath("//button[@aria-label='Twitter']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MailBoxPage login(String login, String pass){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        enterCredentials(login, pass);
        clickOnLoginButton(loginButton);

        return new MailBoxPage(driver);
    }

    public void enterCredentials(String login, String pass){
        type(loginField, login);
        clickOn(loginButton);
        type(passField, pass);
    }

    public void clickOnLoginButton(By loginButton){
        clickOn(loginButton);
    }
}
