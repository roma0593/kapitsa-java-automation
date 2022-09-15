package com.coherent.training.selenium.kapitsa.web.yandex;

import com.coherent.training.selenium.kapitsa.web.base.BaseTest;
import com.coherent.training.selenium.kapitsa.web.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Parameters({"login", "pass"})
    @Test
    public void login(String login, String pass){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://mail.yandex.com/");
        mainPage = new MainPage(driver);

        loginPage = mainPage.getLoginPage();
        mailBoxPage = loginPage.loginWithValidCredentials(login, pass);

        Assert.assertEquals(login, mailBoxPage.getProfileNickname(), "Expected and actual nickname mismatch");
    }
}
