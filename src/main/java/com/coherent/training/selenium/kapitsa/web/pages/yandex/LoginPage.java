package com.coherent.training.selenium.kapitsa.web.pages.yandex;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class LoginPage extends BasePageObject {
    @CacheLookup
    @FindBy(xpath = "//span[@class='passp-add-account-page-title']")
    private WebElement title;
    @CacheLookup
    @FindBy(xpath = "//span[text()='Mail']//ancestor::button")
    private WebElement mailButton;
    @CacheLookup
    @FindBy(xpath = "//span[text()='Phone number']//ancestor::button")
    private WebElement phoneNumberButton;
    @CacheLookup
    @FindBy(xpath = "//input[@id='passp-field-login']")
    private WebElement loginField;
    @CacheLookup
    @FindBy(xpath = "//input[@id='passp-field-passwd']")
    private WebElement passField;
    @CacheLookup
    @FindBy(xpath = "//a[normalize-space()='I forgot']")
    private WebElement forgotLink;
    @CacheLookup
    @FindBy(xpath = "//input[@id='passp-field-phone']")
    private WebElement phoneNumberField;
    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement loginButton;
    @CacheLookup
    @FindBy(xpath = "//a[@id='passp:exp-register']")
    private WebElement createIDButton;
    @CacheLookup
    @FindBy(xpath = "//button[@aria-label='Facebook']")
    private WebElement facebookIcon;
    @CacheLookup
    @FindBy(xpath = "//button[@aria-label='Google']")
    private WebElement googleIcon;
    @CacheLookup
    @FindBy(xpath = "//button[@aria-label='QR code']")
    private WebElement QRCodeIcon;
    @CacheLookup
    @FindBy(xpath = "//button[@aria-label='Twitter']")
    private WebElement twitterIcon;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter credentials and click login")
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

    public void clickOnLoginButton(WebElement loginButton){
        clickOn(loginButton);
    }
}
