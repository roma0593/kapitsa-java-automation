package com.coherent.training.selenium.kapitsa.web.yandex;

import com.coherent.training.selenium.kapitsa.web.base.BaseTest;
import com.coherent.training.selenium.kapitsa.web.pages.MainPage;
import com.coherent.training.selenium.kapitsa.web.providers.UrlProvider;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.coherent.training.selenium.kapitsa.web.providers.UrlProvider.*;

public class LoginTest extends BaseTest {

    @Parameters({"login", "pass"})
    @Test
    public void login(String login, String pass){
        driver.get(YANDEX_MAIL.getUrl());
        mainPage = new MainPage(driver);

        loginPage = mainPage.getLoginPage();
        mailBoxPage = loginPage.login(login, pass);

        Assert.assertEquals(login, mailBoxPage.getProfileNickname(), "Expected and actual nickname mismatch");
    }
}
