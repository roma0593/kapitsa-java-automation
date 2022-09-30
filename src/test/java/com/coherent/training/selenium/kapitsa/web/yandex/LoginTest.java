package com.coherent.training.selenium.kapitsa.web.yandex;

import com.coherent.training.selenium.kapitsa.web.base.BaseTest;
import com.coherent.training.selenium.kapitsa.web.pages.yandex.MainPage;
import com.coherent.training.selenium.kapitsa.web.utils.DataUtilization;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.coherent.training.selenium.kapitsa.web.providers.UrlProvider.YANDEX_MAIL;

public class LoginTest extends BaseTest {

    @SneakyThrows
    @Test(dataProviderClass = DataUtilization.class, dataProvider = "credProvider")
    public void login(String username, String pass) {
        driver.get(YANDEX_MAIL.getUrl());
        mainPage = new MainPage(driver);

        loginPage = mainPage.getLoginPage();

        Thread.sleep(3_000);
    /*  this is the worst type of explicit wait because
        program will always be stopped executing for the time
        specified in method parameter */

        mailBoxPage = loginPage.login(username, pass);

        Assert.assertEquals(username, mailBoxPage.getProfileNickname(), "Expected and actual nickname mismatch");
    }
}
