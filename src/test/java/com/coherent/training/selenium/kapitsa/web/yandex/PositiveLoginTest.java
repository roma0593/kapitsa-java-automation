package com.coherent.training.selenium.kapitsa.web.yandex;

import com.coherent.training.selenium.kapitsa.web.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PositiveLoginTest extends BaseTest {
    @Parameters({"login", "pass"})
    @Test
    public void validLoginTest(String login, String pass){
        loginPage = mainPage.getLoginPage();
        mailBoxPage = loginPage.loginWithValidCredentials(login, pass);

        Assert.assertEquals(login, mailBoxPage.getProfileNickname(), "Expected and actual nickname mismatch");
    }
}
